package cn.sofamovie.admin.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StaticHtmlHelper {

	/**
	 * 
	 * @param templatePath
	 *            模板文件存放路径
	 * @param templateName
	 *            模板文件名称
	 * @param fileName
	 *            生成的文件名称
	 * @param data
	 *            一个Map的数据结果集
	 */
	/*
	 * private static void createFileByFreemarker(String templatePath, String
	 * templateName, String fileName, Map<?, ?> data) { try { Configuration
	 * config = new Configuration(); // 设置要解析的模板所在的目录，并加载模板文件
	 * config.setDirectoryForTemplateLoading(new File(templatePath)); //
	 * 设置包装器，并将对象包装为数据模型 config.setObjectWrapper(new DefaultObjectWrapper()); //
	 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致 // 否则会出现乱码 Template template =
	 * config.getTemplate(templateName, "UTF-8"); // 合并数据模型与模板 FileOutputStream
	 * fos = new FileOutputStream(fileName); Writer out = new
	 * OutputStreamWriter(fos, "UTF-8"); template.process(data, out);
	 * out.flush(); out.close(); } catch (IOException e) { e.printStackTrace();
	 * } catch (TemplateException e) { e.printStackTrace(); } }
	 */

	/**
	 * 生成静态页面主方法
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            一个Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静态页面的路径
	 */
	private static void createFileByFreemarker(ServletContext context,
			String templatePath, String fileName, Map<?, ?> data) {
		Configuration config = new Configuration();
		// 加载模版
		config.setServletContextForTemplateLoading(context, "/");
		config.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版路径
			Template template = config.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");

			// 合并数据模型与模板
			FileOutputStream fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

	public static void createIndexPage(ServletContext context, Map<?, ?> data) {

		Resource resource = new ClassPathResource(
				"/META-INF/freemarker/path.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 下面是模板的名称
		String templateFile = props.getProperty("ftl.index");
		//System.out.println(templateFile);
		// 下面是静态页面输出路径
		String htmlFile = props.getProperty("html.index");
		//System.out.println(htmlFile);
		// 根据模板生成静态页面
		createFileByFreemarker(context, templateFile, htmlFile, data);
	}

	public static void createKefuPage(ServletContext context, Map<?, ?> data) {

		Resource resource = new ClassPathResource(
				"/META-INF/freemarker/path.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 下面是模板的名称
		String templateFile = props.getProperty("ftl.kefu");
		//System.out.println(templateFile);
		// 下面是静态页面输出路径
		String htmlFile = props.getProperty("html.kefu");
		//System.out.println(htmlFile);
		// 根据模板生成静态页面
		createFileByFreemarker(context, templateFile, htmlFile, data);
	}

	public static void createSousuoPage(ServletContext context, Map<?, ?> data) {

		Resource resource = new ClassPathResource(
				"/META-INF/freemarker/path.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 下面是模板的名称
		String templateFile = props.getProperty("ftl.sousuo");
		//System.out.println(templateFile);
		// 下面是静态页面输出路径
		String htmlFile = props.getProperty("html.sousuo");
		//System.out.println(htmlFile);
		// 根据模板生成静态页面
		createFileByFreemarker(context, templateFile, htmlFile, data);
	}

	public static void createZhinanPage(ServletContext context, Map<?, ?> data) {

		Resource resource = new ClassPathResource(
				"/META-INF/freemarker/path.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 下面是模板的名称
		String templateFile = props.getProperty("ftl.zhinan");
		//System.out.println(templateFile);
		// 下面是静态页面输出路径
		String htmlFile = props.getProperty("html.zhinan");
		//System.out.println(htmlFile);
		// 根据模板生成静态页面
		createFileByFreemarker(context, templateFile, htmlFile, data);
	}

}
