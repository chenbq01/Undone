package org.season.autumn.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.season.autumn.domain.FileInfo;
import org.season.autumn.exception.FileAccessException;
import org.season.autumn.helper.FileHelper;
import org.season.autumn.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	@Qualifier("fastDFSFileService")
	// @Qualifier("fsFileService")
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) {

		if (logger.isDebugEnabled()) {
			logger.debug("--上传文件--");
		}

		List<MultipartFile> files = request.getFiles("file");

		byte[] bFile = null;

		for (MultipartFile file : files) {
			if (file.isEmpty())
				continue;
			String originalfilename = null;

			if (file.getOriginalFilename() != null
					&& file.getOriginalFilename().length() > 0) {
				originalfilename = file.getOriginalFilename();
				if (logger.isDebugEnabled()) {
					logger.debug("文件名：" + originalfilename);
				}
				try {
					bFile = file.getBytes();
				} catch (IOException e) {
					throw new FileAccessException("获取文件字节流出错", e);
				}
				fileService.save(originalfilename, bFile);
			}

		}
		return "redirect:/account/home";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String fileid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--根据File ID删除文件--");
			logger.debug("文件ID：" + fileid);
		}
		fileService.delete(fileid);
		return "redirect:/account/home";
	}

	@RequestMapping(value = "/download/{fileid}", method = RequestMethod.GET)
	public void download(@PathVariable String fileid,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("--根据File ID下载文件--");
			logger.debug("文件ID：" + fileid);
		}
		FileInfo fileInfo = null;
		fileInfo = fileService.find(fileid);
		if (fileInfo != null) {
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(response.getOutputStream());
			} catch (IOException e) {
				throw new FileAccessException("HttpServletResponse获取输出流出错", e);
			}
			byte[] bFile = fileService.download(fileInfo.getFileid());
			// response.setContentType("application/x-msdownload;");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="
					+ FileHelper.encodingFileName(fileInfo.getFilename()));

			try {
				bos.write(bFile);
			} catch (IOException e) {
				throw new FileAccessException("写入输出文件流时出错", e);
			}
			try {
				bos.close();
			} catch (IOException e) {
				throw new FileAccessException("关闭文件输出流时出错", e);
			}
		}

	}

}
