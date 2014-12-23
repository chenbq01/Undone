package cn.com.uwoa.business.demo.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.uwoa.business.demo.service.ITemplateService;
import cn.com.uwoa.global.exception.ControllerException;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.tools.UUID;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	private String uploadpath;

	@Autowired
	private ITemplateService templateService;

	@Value("${demo.attachment.path}")
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {

		model.addAttribute("list", templateService.getAllUsers());

		return "/demo/users";

	}

	@ResponseBody
	@RequestMapping(value = "/users", params = "json", method = RequestMethod.GET)
	public Object getAllUsers4Json(Model model) {

		return templateService.getAllUsers();

	}

	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public String getUserByID(@PathVariable("userid") String userid, Model model) {

		model.addAttribute("user", templateService.getUserByID(userid));

		return "/demo/user";

	}

	@ResponseBody
	@RequestMapping(value = "/user/{userid}", params = "json", method = RequestMethod.GET)
	public Object getUserByID4Json(@PathVariable("userid") String userid,
			Model model) {

		logger.info(userid);

		return templateService.getUserByID(userid);

	}

	@RequestMapping(value = "/upload/uploadFile", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request)
			throws Exception {

		List<MultipartFile> files = request.getFiles("file");

		String fileName="";
		
		for (MultipartFile file : files) {
			if (file.isEmpty())
				continue;
			//fileName=new Date().toString().replaceAll(" ", "_").replaceAll(":", "")+"_"+ file.getOriginalFilename();
			
			String name = "";
			String extention = "";
			if(file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
				int i = file.getOriginalFilename().lastIndexOf(".");
				if(i>-1 && i<file.getOriginalFilename().length()){
					name = file.getOriginalFilename().substring(0, i); //--文件名
					extention = file.getOriginalFilename().substring(i+1); //--扩展名
				}
			} 
			fileName=UUID.randomUUID()+"."+extention;
			File filePath = new File(uploadpath+"/"+SecurityHelper.getRestId());
	        if(!filePath.isDirectory()){
	        	filePath.mkdirs();
	        }
			FileOutputStream fileOS = new FileOutputStream(uploadpath+"/"+SecurityHelper.getRestId() +  "/"+fileName);
			fileOS.write(file.getBytes());
			fileOS.close();
		}
		request.setAttribute("fileName",fileName);
		return "/base/uploadOk";
	}

	@ResponseBody
	@RequestMapping(value = "/download/{fileID}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("fileID") String fileID,
			HttpServletResponse response) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "uwoa.log");

		String fileName = map.get(fileID);

		try {
			File file = new File(uploadpath + "/" + fileName);
			InputStream inputStream = new FileInputStream(file);
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ fileName);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			throw new ControllerException("未找到文件", e);
		} catch (IOException e) {
			throw new ControllerException("I/O错误", e);
		}

	}

	@RequestMapping(value = "/log/logmsg/{msg}", method = RequestMethod.GET)
	public String getLogFromSolrByLogMsg(@PathVariable("msg") String msg,
			Model model) {

		model.addAttribute("list", templateService.getLogFromSolrByLogMsg(msg));

		return "/demo/log";

	}

}
