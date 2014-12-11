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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.sofamovie.admin.domain.WebImage;
import cn.sofamovie.admin.service.WebImageService;
import cn.sofamovie.admin.util.StaticHtmlHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/webimage")
public class WebImageController {

	private static final Logger logger = LoggerFactory
			.getLogger(WebImageController.class);

	private String uploadpath;

	private String downloadpath;

	@Autowired
	private WebImageService webImageService;

	@Value("${image.upload.path}")
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	@Value("${image.download.path}")
	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}

	@RequestMapping(value = "/mainimage", method = RequestMethod.GET)
	public String mainimage(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问设置氛围图页--");
		}
		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", "氛围图");
		return "/webimage/mainimage";
	}

	@ResponseBody
	@RequestMapping(value = "/mainimagelist", method = RequestMethod.GET, params = "_json=true")
	public Object mainimagelist() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查询所有氛围图--");
		}
		List<WebImage> list = webImageService.findAllMainImages();
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/deletedmainimagelist", method = RequestMethod.GET, params = "_json=true")
	public Object deletedmainimagelist() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查询所有删除的氛围图--");
		}
		List<WebImage> list = webImageService.findAllDeletedMainImages();
		return list;
	}

	@RequestMapping(value = "/focusimage", method = RequestMethod.GET)
	public String focusimage(Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("--访问设置焦点图页--");
		}
		model.addAttribute("_ACTIVED_MENU", "2");
		model.addAttribute("_MENU_TITLE", "焦点图");
		return "/webimage/focusimage";
	}

	@ResponseBody
	@RequestMapping(value = "/focusimagelist", method = RequestMethod.GET, params = "_json=true")
	public Object focusimagelist() {
		if (logger.isDebugEnabled()) {
			logger.debug("--查询所有焦点图--");
		}
		List<WebImage> list = webImageService.findAllFocusImages();
		return list;
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public ModelAndView uploadfile(MultipartHttpServletRequest request,
			HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("--上传文件--");
		}
		String view = null;
		Map<String, String> map = new HashMap<String, String>();
		List<MultipartFile> files = request.getFiles("file");

		String fileName = "";
		String folder = request.getParameter("imagetype");
		String sequence = request.getParameter("sequence");
		String linkurl = request.getParameter("linkurl");

		if ("FWT".equals(folder)) {
			map.put("_ACTIVED_MENU", "2");
			map.put("_MENU_TITLE", "氛围图");
			view = "/webimage/mainimage";
		}
		if ("JDT".equals(folder)) {
			map.put("_ACTIVED_MENU", "2");
			map.put("_MENU_TITLE", "焦点图");
			view = "/webimage/focusimage";
		}

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
			WebImage webImage = new WebImage();
			webImage.setImagetype(folder);
			webImage.setImagename(fileName);
			webImage.setImageoriginalname(originalfilename);
			webImage.setImageurl(downloadpath + "/" + folder + "/" + fileName);
			webImage.setLinkurl(linkurl);
			webImage.setSequence(Integer.valueOf(sequence == null ? "1"
					: sequence));
			webImage = webImageService.saveWebImage(webImage);
			if (webImage == null || webImage.getId() == null) {
				map.put("msg", "系统错误，无法保存上传信息！");
				return new ModelAndView(view, map);
			}
		}
		generateIndexPage(request.getSession());
		map.put("msg", "图片上传成功！");
		return new ModelAndView(view, map);
	}

	private void generateIndexPage(HttpSession session) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("main", webImageService.findAllMainImages());
		data.put("focus", webImageService.findAllFocusImages());
		StaticHtmlHelper.createIndexPage(session.getServletContext(), data);
	}

	@RequestMapping(value = "/removeImage", method = RequestMethod.GET)
	public ModelAndView removeImage(HttpSession session, String sequence) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除图片--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = webImageService.removeImage(sequence);
		if (flag) {
			map.put("msg", "图片删除成功！");
		} else {
			map.put("msg", "图片删除失败！");
		}
		generateIndexPage(session);
		map.put("_ACTIVED_MENU", "2");
		map.put("_MENU_TITLE", "焦点图");
		return new ModelAndView("/webimage/focusimage", map);
	}

	@RequestMapping(value = "/deletemainimage", method = RequestMethod.GET)
	public ModelAndView deletemainimage(HttpSession session, String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("--删除图片--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = webImageService.deleteMainImage(id);
		if (flag) {
			map.put("msg", "图片删除成功！");
		} else {
			map.put("msg", "图片删除失败！");
		}
		generateIndexPage(session);
		map.put("_ACTIVED_MENU", "2");
		map.put("_MENU_TITLE", "焦点图");
		return new ModelAndView("/webimage/mainimage", map);
	}

	@RequestMapping(value = "/resetmainimage", method = RequestMethod.GET)
	public ModelAndView resetmainimage(HttpSession session, String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("--重置氛围图--");
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = webImageService.resetMainImage(id);
		if (flag) {
			map.put("msg", "操作成功！");
		} else {
			map.put("msg", "操作失败！");
		}
		generateIndexPage(session);
		map.put("_ACTIVED_MENU", "2");
		map.put("_MENU_TITLE", "焦点图");
		return new ModelAndView("/webimage/mainimage", map);
	}

}
