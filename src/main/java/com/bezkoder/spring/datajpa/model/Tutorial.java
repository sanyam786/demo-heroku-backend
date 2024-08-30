package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	//@Column(name = "address")
	@Column(columnDefinition="String default 'office address'")
	private String address;

	@Column(name = "published")
	private boolean published;

	public Tutorial() {

	}

	public Tutorial(String title, String description, String address, boolean published) {
		this.title = title;
		this.description = description;
		this.address = address;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Tutorial{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", address='" + address + '\'' +
				", published=" + published +
				'}';
	}
}
