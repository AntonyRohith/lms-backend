package org.capestart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOOK_ID")
	private Integer id;
	
	@Column(name="BOOK_NAME")
	private String name;
	
	@Column(name="IMAGE_PATH")
	private String img;
	
	@Column(name="USER_ID")
	private Integer user;
	
	@Column(name="PUBLISHER_ID")
	private Integer publisher;
	
	@Column(name="AUTHOR_ID")
	private Integer author;
	
	@Column(name="ACTIVE")
	private Character active;
	
	@Column(name="IS_AVAILABLE")
	private Character isAvailable;
	
	@Column(name="AVAILABLE_TIME")
	private String availableTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", updatable = false, insertable = false)
	private User userDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "PUBLISHER_ID", updatable = false, insertable = false)
	private Publisher publisherDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID", updatable = false, insertable = false)
	private Author authorDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getPublisher() {
		return publisher;
	}

	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public Character getActive() {
		return active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public Character getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Character isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public User getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(User userDetail) {
		this.userDetail = userDetail;
	}

	public Publisher getPublisherDetail() {
		return publisherDetail;
	}

	public void setPublisherDetail(Publisher publisherDetail) {
		this.publisherDetail = publisherDetail;
	}

	public Author getAuthorDetail() {
		return authorDetail;
	}

	public void setAuthorDetail(Author authorDetail) {
		this.authorDetail = authorDetail;
	}
	
	
	
}
