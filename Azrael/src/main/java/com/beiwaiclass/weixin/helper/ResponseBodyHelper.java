package com.beiwaiclass.weixin.helper;

import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beiwaiclass.weixin.domain.WeiXinArticle;

public final class ResponseBodyHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(ResponseBodyHelper.class);

	private ResponseBodyHelper() {
		throw new UnsupportedOperationException();
	}

	public static String buildMsgTextResponseXML(String sToUserName,
			String sFromUserName, String sContent) {
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("text");

		Element content = root.addElement("Content");
		content.addCDATA(sContent);
		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgTextResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static String buildMsgImageResponseXML(String sToUserName,
			String sFromUserName, String sMediaId) {
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("image");

		Element mediaid = root.addElement("Image").addElement("MediaId");
		mediaid.addCDATA(sMediaId);

		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgImageResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static String buildMsgVoiceResponseXML(String sToUserName,
			String sFromUserName, String sMediaId) {
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("voice");

		Element mediaid = root.addElement("Voice").addElement("MediaId");
		mediaid.addCDATA(sMediaId);

		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgVoiceResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static String buildMsgVideoResponseXML(String sToUserName,
			String sFromUserName, String sMediaId, String sTitle,
			String sDescription) {
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("video");

		Element video = root.addElement("Video");
		Element mediaid = video.addElement("MediaId");
		mediaid.addCDATA(sMediaId);
		Element title = video.addElement("Title");
		title.addCDATA(sTitle);
		Element description = video.addElement("Description");
		description.addCDATA(sDescription);

		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgVideoResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static String buildMsgMusicResponseXML(String sToUserName,
			String sFromUserName, String sThumbMediaId, String sTitle,
			String sDescription, String sMusicUrl, String sHQMusicUrl) {
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("music");

		Element music = root.addElement("Music");
		Element title = music.addElement("Title");
		title.addCDATA(sTitle);
		Element description = music.addElement("Description");
		description.addCDATA(sDescription);
		Element musicurl = music.addElement("MusicUrl");
		musicurl.addCDATA(sMusicUrl);
		Element hqmusicurl = music.addElement("HQMusicUrl");
		hqmusicurl.addCDATA(sHQMusicUrl);
		Element thumbmediaid = music.addElement("ThumbMediaId");
		thumbmediaid.addCDATA(sThumbMediaId);

		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgMusicResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static String buildMsgNewsResponseXML(String sToUserName,
			String sFromUserName, List<WeiXinArticle> listArticles) {
		if (listArticles == null || listArticles.size() == 0) {
			return "";
		}
		String sMsgResponseXML = null;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		Element tousername = root.addElement("ToUserName");
		tousername.addCDATA(sToUserName);

		Element fromusername = root.addElement("FromUserName");
		fromusername.addCDATA(sFromUserName);

		Element createtime = root.addElement("CreateTime");
		createtime.addText(String.valueOf(new Date().getTime()));

		Element msgtype = root.addElement("MsgType");
		msgtype.addCDATA("news");

		Element articlecount = root.addElement("ArticleCount");
		articlecount.addText(String.valueOf(listArticles.size()));

		Element articles = root.addElement("Articles");
		Element item = null;
		Element title = null;
		Element description = null;
		Element picurl = null;
		Element url = null;
		for (WeiXinArticle article : listArticles) {
			item = articles.addElement("item");
			title = item.addElement("Title");
			title.addCDATA(article.getTitle());
			description = item.addElement("Description");
			description.addCDATA(article.getDescription());
			picurl = item.addElement("PicUrl");
			picurl.addCDATA(article.getPicurl());
			url = item.addElement("Url");
			url.addCDATA(article.getUrl());
		}

		sMsgResponseXML = doc.getRootElement().asXML();
		if (logger.isInfoEnabled()) {
			logger.info("MsgNewsResponseXML:" + sMsgResponseXML);
		}
		return sMsgResponseXML;
	}

	public static void main(String[] args) {
		System.out.println(buildMsgImageResponseXML("to", "from", "mediaid"));
		System.out
				.println(buildMsgVoiceResponseXML("to", "from", "meimediaid"));

		System.out.println(buildMsgVideoResponseXML("to", "from", "meimediaid",
				"title", "description"));
	}
}
