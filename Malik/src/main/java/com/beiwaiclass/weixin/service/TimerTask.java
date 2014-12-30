package com.beiwaiclass.weixin.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.beiwaiclass.weixin.helper.AccessTokenHelper;

@Component
public class TimerTask {

	@Scheduled(cron = "0 0 0-23 * * ?")
	public void returnAccessToken() {
		AccessTokenHelper.requestAccessToken();
	}

}
