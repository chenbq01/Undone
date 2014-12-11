package org.season.autumn.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.season.autumn.domain.FileInfo;
import org.season.autumn.exception.BaseException;
import org.season.autumn.exception.FileAccessException;
import org.season.autumn.exception.ServerAccessException;
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

@Service("fastDFSFileService")
public class FastDFSFileService implements FileService {

	private static final Logger logger = LoggerFactory
			.getLogger(FastDFSFileService.class);

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
		TrackerServer trackerServer = getTrackerServer();
		StorageServer storageServer = getStorageServer();

		StorageClient1 client = new StorageClient1(trackerServer, storageServer);

		NameValuePair[] metaList = new NameValuePair[1];
		metaList[0] = new NameValuePair("File Name", filename);

		String extention = FileHelper.getExtension(filename);

		org.season.autumn.domain.FileInfo fileInfo2db = new org.season.autumn.domain.FileInfo();

		try {
			fileID = client.upload_file1(bFile, extention, metaList);
		} catch (IOException e) {
			throw new FileAccessException("文件上传FastDFS服务器出错", e);
		} catch (Exception e) {
			throw new BaseException("文件上传FastDFS服务器出错", e);
		}

		fileInfo2db.setFilename(filename);
		fileInfo2db.setFileid(fileID);
		fileInfo2db = fileInfoRepository.save(fileInfo2db);

		if (logger.isDebugEnabled()) {
			logger.debug("upload success. file in db id is: "
					+ fileInfo2db.getId());
		}

		closeTrackServer(trackerServer);

		return fileID;
	}

	@Override
	@Transactional
	public void delete(String fileid) {
		Long id = Long.valueOf(fileid);
		FileInfo fileInfo = fileInfoRepository.findOne(id);
		if (fileInfo != null) {
			fileInfoRepository.delete(id);

//			messageQueueService.sendMessage(destination,
//					"删除文件名为[" + fileInfo.getFilename() + "]且File ID为["
//							+ fileInfo.getFileid() + "]的文件");
			Map<String,String> mapmessage = new HashMap<String,String>();
			mapmessage.put("fileid", fileInfo.getFileid());
			messageQueueService.sendMessage(destination, mapmessage);

			TrackerServer trackerServer = getTrackerServer();
			StorageServer storageServer = getStorageServer();
			StorageClient1 client = new StorageClient1(trackerServer,
					storageServer);

			try {
				client.delete_file1(fileInfo.getFileid());
			} catch (IOException e) {
				throw new FileAccessException("根据fileID从FastDFS服务器删除文件出错", e);
			} catch (Exception e) {
				throw new BaseException("根据fileID从FastDFS服务器删除文件出错", e);
			}

			closeTrackServer(trackerServer);
		}
	}

	@Override
	public byte[] download(String fileid) {
		byte[] bFile = null;
		if (fileid != null) {
			TrackerServer trackerServer = getTrackerServer();
			StorageServer storageServer = getStorageServer();
			StorageClient1 client = new StorageClient1(trackerServer,
					storageServer);

			try {
				bFile = client.download_file1(fileid);
			} catch (IOException e) {
				throw new FileAccessException("根据fileID从FastDFS服务器下载文件出错", e);
			} catch (Exception e) {
				throw new BaseException("根据fileID从FastDFS服务器下载文件出错", e);
			}

			closeTrackServer(trackerServer);
		}
		return bFile;
	}

	@Override
	public FileInfo find(String fileid) {
		Long id = Long.valueOf(fileid);
		return fileInfoRepository.findOne(id);
	}

	private void closeTrackServer(TrackerServer trackerServer) {
		try {
			trackerServer.close();
		} catch (IOException e) {
			throw new ServerAccessException("无法正常关闭与FastDFS Tracker Server的连接",
					e);
		}
	}

	private TrackerServer getTrackerServer() {
		TrackerServer trackerServer = null;
		String[] trackerServers = tracker_servers.split(",");
		String[] parts;
		InetSocketAddress[] tracker_servers = new InetSocketAddress[trackerServers.length];
		for (int j = 0; j < trackerServers.length; j++) {
			parts = trackerServers[j].split("\\:", 2);
			if (parts.length != 2) {
				throw new IllegalArgumentException(
						"配置项\"tracker_server\"的格式不正确，正确格式为\"地址:端口\"");
			}

			tracker_servers[j] = new InetSocketAddress(parts[0].trim(),
					Integer.parseInt(parts[1].trim()));
		}
		ClientGlobal.g_tracker_group = new TrackerGroup(tracker_servers);
		ClientGlobal.g_connect_timeout = connect_timeout;
		ClientGlobal.g_network_timeout = network_timeout;
		ClientGlobal.g_charset = charset;
		ClientGlobal.g_tracker_http_port = tracker_http_port;
		ClientGlobal.g_anti_steal_token = anti_steal_token;
		ClientGlobal.g_secret_key = secret_key;

		TrackerClient tracker = new TrackerClient();
		try {
			trackerServer = tracker.getConnection();
		} catch (IOException e) {
			throw new ServerAccessException("无法连接设置的FastDFS Tracker Server", e);
		}
		return trackerServer;
	}

	private StorageServer getStorageServer() {
		return null;
	}

	@Value("#{file['connect_timeout']}")
	public void setConnect_timeout(int connect_timeout) {
		this.connect_timeout = connect_timeout;
	}

	@Value("#{file['network_timeout']}")
	public void setNetwork_timeout(int network_timeout) {
		this.network_timeout = network_timeout;
	}

	@Value("#{file['charset']}")
	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Value("#{file['tracker_http_port']}")
	public void setTracker_http_port(int tracker_http_port) {
		this.tracker_http_port = tracker_http_port;
	}

	@Value("#{file['anti_steal_token']}")
	public void setAnti_steal_token(boolean anti_steal_token) {
		this.anti_steal_token = anti_steal_token;
	}

	@Value("#{file['secret_key']}")
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	@Value("#{file['tracker_servers']}")
	public void setTracker_servers(String tracker_servers) {
		this.tracker_servers = tracker_servers;
	}

	private int connect_timeout;

	private int network_timeout;

	private String charset;

	private int tracker_http_port;

	private boolean anti_steal_token;

	private String secret_key;

	private String tracker_servers;

}
