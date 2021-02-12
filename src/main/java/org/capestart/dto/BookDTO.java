package org.capestart.dto;

public class BookDTO {
	
	private Integer id;
	
	private String book;
	
	private String img;
	
	private String author;
	
	private String publisher;
	
	private Character available;
	
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public Character getAvailable() {
		return available;
	}

	public void setAvailable(Character available) {
		this.available = available;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
