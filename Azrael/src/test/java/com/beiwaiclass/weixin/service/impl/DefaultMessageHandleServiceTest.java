package com.beiwaiclass.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beiwaiclass.weixin.service.MessageHandleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/root-context.xml" })
public class DefaultMessageHandleServiceTest {

	@Autowired
	private MessageHandleService MessageHandleService;

	@Test
	public void testHandle() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("MsgType", "text");
		params.put("ToUserName", "[gh_69920b2e72fc");
		params.put("FromUserName", "oDcuhjhChaRhAqMNzmDtAkb4Iodc");
		params.put("CreateTime", "1420421010");
		params.put("MsgType", "text");
		params.put("Content", "Content");
		System.out.println(MessageHandleService.handle(params));

	}

}
