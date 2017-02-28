package com.teng.entity;

/**
 * 书籍实体类
 * @author TENG
 *
 */
public class BookEntity {
	
	/**
	 * 书名
	 */
	private String subject;
	
	/**
	 * 评分
	 */
	private String rating;
	
	/**
	 * 评价人数
	 */
	private String raterNum;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 出版社
	 */
	private String publisher;
	
	/**
	 * 出版时间
	 */
	private String publishDate;
	
	/**
	 * 价格
	 */
	private String price;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRaterNum() {
		return raterNum;
	}

	public void setRaterNum(String raterNum) {
		this.raterNum = raterNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
