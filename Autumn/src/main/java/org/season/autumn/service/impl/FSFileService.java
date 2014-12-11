package org.season.autumn.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.jms.Destination;

import org.season.autumn.domain.FileInfo;
import org.season.autumn.exception.FileAccessException;
import org.season.autumn.helper.FileHelper;
import org.season.autumn.jms.service.MessageQueueService;
import org.season.autumn.repository.FileInfoRepository;
import org.season.autumn.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fsFileService")
public class FSFileService implements FileService {

	private static final Logger logger = LoggerFactory
			.getLogger(FSFileService.class);

	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	
	@Qualifier("activeMQService")
	@Autowired
	private MessageQueueService messageQueueService;

	@Override
	@Transactional
	public String save(String filename, byte[] bFile) {

		String fileID = null;
		String extention = FileHelper.getExtension(filename);
		String fileName = UUID.randomUUID() + "." + extention;
		File filePath = new File(uploadpath);
		if (!filePath.isDirectory()) {
			filePath.mkdirs();
		}
		FileOutputStream fileOS = null;

		org.season.autumn.domain.FileInfo fileInfo2db = new org.season.autumn.domain.FileInfo();

		try {
			fileOS = new FileOutputStream(uploadpath + "/" + fileName);
		} catch (FileNotFoundException e) {
			throw new FileAccessException("在服务器上创建文档出错", e);
		}
		try {
			fileOS.write(bFile);
		} catch (IOException e) {
			try {
				fileOS.close();
			} catch (IOException ioe) {
				throw new FileAccessException("由于在服务器上写入文档出错 在关闭文件输出流出错", ioe);
			}
			throw new FileAccessException("在服务器上写入文档出错", e);
		}
		try {
			fileOS.close();
		} catch (IOException e) {
			throw new FileAccessException("关闭文件输出流出错", e);
		}

		fileID = uploadpath + "/" + fileName;
		fileInfo2db.setFilename(filename);
		fileInfo2db.setFileid(fileID);
		fileInfo2db = fileInfoRepository.save(fileInfo2db);

		if (logger.isDebugEnabled()) {
			logger.debug("upload success. file in db id is: "
					+ fileInfo2db.getId());
		}

		return fileID;

	}

	@Override
	@Transactional
	public void delete(String fileid) {
		Long id = Long.valueOf(fileid);
		FileInfo fileInfo = fileInfoRepository.findOne(id);
		if (fileInfo != null) {
			fileInfoRepository.delete(id);
			
			//messageQueueService.sendMessage(destination, "删除文件名为["+fileInfo.getFilename()+"]且File ID为["+fileInfo.getFileid()+"]的文件");
			Map<String,String> mapmessage = new HashMap<String,String>();
			mapmessage.put("fileid", fileInfo.getFileid());
			messageQueueService.sendMessage(destination, mapmessage);
			
			File file = new File(fileInfo.getFileid());
			if (file.exists()) {
				file.delete();
			}
		}
	}

	@Override
	public byte[] download(String fileid) {
		byte[] bFile = null;
		if (fileid != null) {
			File file = new File(fileid);
			if (file.exists()) {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					throw new FileAccessException("无法在服务器上找到文件", e);
				}
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
				byte[] b = new byte[1000];
				int n = -1;
				try {
					while ((n = fis.read(b)) != -1) {
						bos.write(b, 0, n);
					}
				} catch (IOException e) {
					throw new FileAccessException("无法在将文件写入输出流", e);
				}
				try {
					fis.close();
				} catch (IOException e) {
					throw new FileAccessException("关闭文件输入流时出错", e);
				}
				try {
					bos.close();
				} catch (IOException e) {
					throw new FileAccessException("关闭文件输出流时出错", e);
				}
				bFile = bos.toByteArray();
			}
		}
		return bFile;
	}

	@Override
	public FileInfo find(String fileid) {
		Long id = Long.valueOf(fileid);
		return fileInfoRepository.findOne(id);
	}

	private String uploadpath;

	@Value("#{file['uploadpath']}")
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

}
