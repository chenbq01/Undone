package org.season.autumn.service;

import org.season.autumn.domain.FileInfo;

public interface FileService {

	public String save(String originalfilename, byte[] bFile);

	public void delete(String fileid);

	public byte[] download(String fileid);
	
	public FileInfo find(String fileid);

}
