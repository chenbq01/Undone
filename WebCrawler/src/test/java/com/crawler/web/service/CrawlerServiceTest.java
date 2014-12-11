package com.crawler.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-base.xml",
		"classpath:/META-INF/spring/data/jpa/data-jpa.xml" })
public class CrawlerServiceTest {

	@Autowired
	@Qualifier("gewaraCrawlerService")
	private CrawlerService gewaraCrawlerService;

	@Autowired
	@Qualifier("wangPiaoCrawlerService")
	private CrawlerService wangPiaoCrawlerService;

	@Autowired
	@Qualifier("spiderCrawlerService")
	private CrawlerService spiderCrawlerService;

	@Autowired
	@Qualifier("mTimeCrawlerService")
	private CrawlerService mTimeCrawlerService;

	@Autowired
	@Qualifier("koMovieCrawlerService")
	private CrawlerService koMovieCrawlerService;

	@Test
	public void testGetGewaraAllCinemaInformation() {
		gewaraCrawlerService.getAllCinemaInformation();
	}

	@Test
	public void testGetWangPiaoAllCinemaInformation() {
		wangPiaoCrawlerService.getAllCinemaInformation();
	}

	@Test
	public void testGetSpiderAllCinemaInformation() {
		spiderCrawlerService.getAllCinemaInformation();
	}

	@Test
	public void testGetMTimeAllCinemaInformation() {
		mTimeCrawlerService.getAllCinemaInformation();
	}

	@Test
	public void testGetKoMovieAllCinemaInformation() {
		koMovieCrawlerService.getAllCinemaInformation();
	}

}
