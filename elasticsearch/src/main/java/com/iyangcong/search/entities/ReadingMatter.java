package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "readingmaterial", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class ReadingMatter {

	@Id
	private String _id;

	private String type;

	private String title_zh;

	private String title_en;

	private String author_zh;

	private String author_en;

	private String category;

	private String cover;

	private String price;

	private String priceUSD;

	private String special_price;

	private String price_type;

	private String language;

	private String terminal;

	private String status;

	private String intro_zh;

	private String intro_en;

	private String translator;

	private String publishing_zh;

	private String publishing_en;

	@Version
	private Long _version;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle_zh() {
		return title_zh;
	}

	public void setTitle_zh(String title_zh) {
		this.title_zh = title_zh;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public String getAuthor_zh() {
		return author_zh;
	}

	public void setAuthor_zh(String author_zh) {
		this.author_zh = author_zh;
	}

	public String getAuthor_en() {
		return author_en;
	}

	public void setAuthor_en(String author_en) {
		this.author_en = author_en;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceUSD() {
		return priceUSD;
	}

	public void setPriceUSD(String priceUSD) {
		this.priceUSD = priceUSD;
	}

	public String getSpecial_price() {
		return special_price;
	}

	public void setSpecial_price(String special_price) {
		this.special_price = special_price;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntro_zh() {
		return intro_zh;
	}

	public void setIntro_zh(String intro_zh) {
		this.intro_zh = intro_zh;
	}

	public String getIntro_en() {
		return intro_en;
	}

	public void setIntro_en(String intro_en) {
		this.intro_en = intro_en;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPublishing_zh() {
		return publishing_zh;
	}

	public void setPublishing_zh(String publishing_zh) {
		this.publishing_zh = publishing_zh;
	}

	public String getPublishing_en() {
		return publishing_en;
	}

	public void setPublishing_en(String publishing_en) {
		this.publishing_en = publishing_en;
	}

	public Long get_version() {
		return _version;
	}

	public void set_version(Long _version) {
		this._version = _version;
	}

	@Override
	public String toString() {
		return "ReadingMatter [_id=" + _id + ", type=" + type + ", title_zh=" + title_zh + ", title_en=" + title_en + ", author_zh=" + author_zh + ", author_en=" + author_en + ", category=" + category + ", cover=" + cover + ", price=" + price + ", priceUSD=" + priceUSD + ", special_price=" + special_price + ", price_type=" + price_type + ", language=" + language + ", terminal=" + terminal
				+ ", status=" + status + ", intro_zh=" + intro_zh + ", intro_en=" + intro_en + ", translator=" + translator + ", publishing_zh=" + publishing_zh + ", publishing_en=" + publishing_en + ", _version=" + _version + "]";
	}

}
