package cn.com.uwoa.system.frame.po;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础框架 - 翻页Po
 * @author liyue
 */
public class PagePo {
	/**
	 * 页号
	 */
	private int page = 1;
	
	/**
	 * 每页行数
	 */
	private int pageRow = 20;
	
	/**
	 * 总行数
	 */
	private int rowCount = 0;
	
	/**
	 * 总页数
	 */
	private int pageCount = 1;
	
	/**
	 * URL
	 */
	private String url;

	/**
	 * 构造函数
	 */
	private PagePo(){
		
	}
	
	/**
	 * 构造函数
	 * @param page 页号
	 * @param rowCount 总行数
	 */
	public PagePo(int page,int rowCount){
		//记录总行数
		this.rowCount=rowCount;
		//计算总页数
		this.pageCount = rowCount/pageRow+(rowCount%pageRow==0?0:1);
		//校验页号
		if(page<1){
			this.page=1;
		}
		else if(page>pageCount){
			if(pageCount<1){
				this.page=1;
			}
			else{
				this.page=pageCount;
			}
		}
		else{
			this.page=page;
		}
	}
	
	/**
	 * 获得页号
	 * @return 页号
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 获得每页行数
	 * @return 每页行数
	 */
	public int getPageRow() {
		return pageRow;
	}

	/**
	 * 获得总行数
	 * @return 总行数
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * 获得总页数
	 * @return 总页数
	 */
	public int getpageCount() {
		return pageCount;
	}
	
	/**
	 * 输出HTML代码
	 * @return HTML代码
	 */
	public String toHtml(){
		//声明HTML串
		StringBuffer html = new StringBuffer();
		html.append("<table><tr><td>");
		//1页以上的显示翻页
		if(pageCount>1){
			//非第1页显示前翻页
			if(page>1){
				html.append("&nbsp;<a href=\"javascript:search(1)\">首页</a>&nbsp;");
				html.append("&nbsp;<a href=\"javascript:search("+(page-1)  +")\">上一页</a>&nbsp;");
			}
			//遍历前置页
			for(int i=page-4;i<page;i++){
				if(i>=1){
					html.append("&nbsp;<a href=\"javascript:search("+i  +")\">"+i+"</a>&nbsp;");
				}
			}
			//当前页
			html.append("&nbsp;<b>" + page + "</b>&nbsp;");
			//遍历后置页
			for(int i=page+1;i<=page+4;i++){
				if(i<=pageCount){
					html.append("&nbsp;<a href=\"javascript:search("+i  +")\">"+i+"</a>&nbsp;");
				}
			}
			//非最后1页显示后翻页
			if(page<pageCount){
				html.append("&nbsp;<a href=\"javascript:search("+(page+1)  +")\">下一页</a>&nbsp;");
				html.append("&nbsp;<a href=\"javascript:search("+pageCount  +")\">尾页</a>&nbsp;");
			}
		}
		//数据量信息
		html.append("&nbsp;共有&nbsp;<font color=\"#FF0000\">"+pageCount+"</font>&nbsp;页&nbsp;<font color=\"#FF0000\">"+rowCount+"</font>&nbsp;条&nbsp;");
		//1页以上的显示跳转
		if(pageCount>1){
			html.append("&nbsp跳转到</td><td><input name=\"turnPage\" style=\"width:30px\" /></td><td><img src=\"/uwoa/resources/normal/images/but_go.gif\" onclick=\"if($('input[name=turnPage]').val()){search($('input[name=turnPage]').val());}\"></td><td>页");
		}
		html.append("</td></tr></table>");
		
		//返回HTML代码
		return html.toString();
	}
}
