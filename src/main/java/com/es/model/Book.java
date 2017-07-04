package com.es.model;

import java.util.List;

public class Book extends BaseModel{
	private String author;
	private List<String> characters;
	private Long copies;
	private String otitle;
	private List<String> tags;
	private String title;
	private Long year;
	private Boolean available;
	private Integer section;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getCharacters() {
		return characters;
	}
	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}
	public Long getCopies() {
		return copies;
	}
	public void setCopies(Long copies) {
		this.copies = copies;
	}
	public String getOtitle() {
		return otitle;
	}
	public void setOtitle(String otitle) {
		this.otitle = otitle;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Integer getSection() {
		return section;
	}
	public void setSection(Integer section) {
		this.section = section;
	}
}
