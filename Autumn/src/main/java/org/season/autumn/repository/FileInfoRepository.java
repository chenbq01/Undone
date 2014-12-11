package org.season.autumn.repository;

import org.season.autumn.domain.FileInfo;
import org.springframework.data.repository.Repository;

public interface FileInfoRepository extends Repository<FileInfo, Long> {

	public FileInfo save(FileInfo fileInfo);

	public void delete(Long id);

	public void delete(FileInfo fileInfo);

	public FileInfo findOne(Long id);

	boolean exists(Long id);

}
