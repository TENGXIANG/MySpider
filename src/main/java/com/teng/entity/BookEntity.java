package com.teng.entity;

/**
 * �鼮ʵ����
 * @author TENG
 *
 */
public class BookEntity {
	
	/**
	 * ����
	 */
	private String subject;
	
	/**
	 * ����
	 */
	private String rating;
	
	/**
	 * ��������
	 */
	private String raterNum;
	
	/**
	 * ����
	 */
	private String author;
	
	/**
	 * ������
	 */
	private String publisher;
	
	/**
	 * ����ʱ��
	 */
	private String publishDate;
	
	/**
	 * �۸�
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
