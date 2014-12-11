package cn.sofamovie.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.sofamovie.admin.domain.BoxImageInfo;
import cn.sofamovie.admin.service.BoxImageInfoService;
import cn.sofamovie.admin.service.BoxInfoService;
import cn.sofamovie.admin.service.CardImageInfoService;
import cn.sofamovie.admin.service.CardInfoService;
import cn.sofamovie.admin.service.RegionInfoService;
import cn.sofamovie.admin.util.StaticHtmlHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/boximageinfo")
public class BoxImageInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(BoxImageInfoController.class);

	private String uploadpath;

	private String downloadpath;

	@Autowired
	private RegionInfoService regionInfoService;

	@Autowired
	private CardInfoService cardInfoService;

	@Autowired
	private CardImageInfoService cardImageInfoService;

	@Autowired
	private BoxInfoService boxInfoService;

	@Autowired
	private BoxImageInfoService boxImageInfoService;

	@Value("${image.upload.path}")
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	@Value("${image.download.path}")
	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = "_json=true")
	public Object list(String boxid) {
		if (logger.isDebugEnabled()) {
			logger.debug("--查询机顶盒[" + boxid + "]的图--");
		}
		List<BoxImageInfo> list = boxImageInfoService
				.findAllImagesByBoxID(boxid);
		return list;
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public ModelAndView uploadfile(HttpSession session,
			MultipartHttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("--上传文件--");
		}

		Map<String, String> map = new HashMap<String, String>();
		List<MultipartFile> files = request.getFiles("file");

		String fileName = "";
		String boxid = request.getParameter("boxid");
		String boxname = request.getParameter("boxname");
		String regionid = request.getParameter("regionid");
		String sequence = request.getParameter("sequence");
		String folder = boxid + "_" + boxname;

		String view = "/box/imagelist";

		for (MultipartFile file : files) {
			if (file.isEmpty())
				continue;
			// fileName=new Date().toString().replaceAll(" ",
			// "_").replaceAll(":", "")+"_"+ file.getOriginalFilename();

			String originalfilename = null;
			// String name = null;
			String extention = null;
			if (file.getOriginalFilename() != null
					&& file.getOriginalFilename().length() > 0) {
				int i = file.getOriginalFilename().lastIndexOf(".");
				if (i > -1 && i < file.getOriginalFilename().length()) {
					originalfilename = file.getOriginalFilename();
					// name = originalfilename.substring(0, i); // --文件名
					extention = originalfilename.substring(i + 1); // --扩展名
				}
			}
			fileName = UUID.randomUUID() + "." + extention;
			File filePath = new File(uploadpath + "/" + folder);
			if (!filePath.isDirectory()) {
				filePath.mkdirs();
			}
			FileOutputStream fileOS;
			try {
				fileOS = new FileOutputStream(uploadpath + "/" + folder + "/"
						+ fileName);
				fileOS.write(file.getBytes());
				fileOS.close();
			} catch (FileNotFoundException e) {
				map.put("msg", "系统错误，无法上传！");
				break;
			} catch (IOException e) {
				map.put("msg", "系统错误，无法上传！");
				break;
			}
			// 保存到数据库
			BoxImageInfo boxImageInfo = new BoxImageInfo();
			boxImageInfo.setBoxid(Long.valueOf(boxid));
			boxImageInfo.setImagename(fileName);
			boxImageInfo.setImageoriginalname(originalfilename);
			boxImageInfo.setImageurl(downloadpath + "/" + folder + "/"
					+ fileName);
			boxImageInfo.setSequence(Integer.valueOf(sequence == null ? "1"
					: sequence));
			boxImageInfo = boxImageInfoService.saveBoxImage(boxImageInfo);
			if (boxImageInfo == null || boxImageInfo.getId() == null) {
				map.put("msg", "系统错误，无法保存上传信息！");
				return new ModelAndView(view, map);
			}
		}
		generateZhinanPage(session);
		map.put("msg", "图片上传成功！");
		map.put("boxid", boxid);
		map.put("regionid", regionid);
		map.put("boxname", boxname);

		map.put("_ACTIVED_MENU", "3");
		map.put("_MENU_TITLE", boxname);
		return new ModelAndView(view, map);
	}

	@RequestMapping(value = "/removeImage", method = RequestMethod.GET)
	public ModelAndView removeImage(HttpSession session, String boxid,
			String regionid, String boxname, String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除图片--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = boxImageInfoService.removeImage(id);
		if (flag) {
			generateZhinanPage(session);
			map.put("msg", "图片删除成功！");
		} else {
			map.put("msg", "图片删除失败！");
		}
		map.put("boxid", boxid);
		map.put("regionid", regionid);
		map.put("boxname", boxname);

		map.put("_ACTIVED_MENU", "3");
		map.put("_MENU_TITLE", boxname);
		return new ModelAndView("/box/imagelist", map);
	}

	private void generateZhinanPage(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("regions", regionInfoService.findAllRegions());
		data.put("boxes", boxInfoService.findAllBoxes());
		data.put("cards", cardInfoService.findAllCards());
		data.put("boximages", boxImageInfoService.findAllImages());
		data.put("cardimages", cardImageInfoService.findAllImages());
		StaticHtmlHelper.createZhinanPage(session.getServletContext(), data);
	}

}
