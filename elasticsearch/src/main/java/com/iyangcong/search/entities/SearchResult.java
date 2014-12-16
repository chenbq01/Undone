package com.iyangcong.search.entities;

import java.util.List;

public class SearchResult<T> {

	private long totalElements;

	private int totalPages;

	private int size;

	private int page;

	private List<T> content;

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SearchResult [totalElements=" + totalElements + ", totalPages="
				+ totalPages + ", size=" + size + ", page=" + page
				+ ", content=" + content + "]";
	}

}
