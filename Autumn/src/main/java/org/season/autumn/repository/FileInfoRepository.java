package org.season.autumn.repository;

import javax.persistence.QueryHint;

import org.season.autumn.domain.FileInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FileInfoRepository extends Repository<FileInfo, Long> {

	public FileInfo save(FileInfo fileInfo);

	public void delete(Long id);

	public void delete(FileInfo fileInfo);

	public FileInfo findOne(Long id);

	boolean exists(Long id);

	@Query("from FileInfo f where f.filename = :filename")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public FileInfo findByFilename(@Param("filename") String filename);

}
