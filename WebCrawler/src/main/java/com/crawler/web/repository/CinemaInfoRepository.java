package com.crawler.web.repository;

import org.springframework.data.repository.Repository;

import com.crawler.web.domain.CinemaInfo;

public interface CinemaInfoRepository extends Repository<CinemaInfo, Long> {

	public CinemaInfo save(CinemaInfo cinemaInfo);

}
