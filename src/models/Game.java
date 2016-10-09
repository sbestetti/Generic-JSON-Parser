package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	
	@Id
	private Long id;
	private String title;
	
	@ManyToMany
	private List<Platform> platforms;
	
//	private List<String> genres;
	
	private String description;
	private Date releaseDate;
	
	//Getters & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Platform> getPlataforms() {
		return platforms;
	}
	public void setPlataforms(List<Platform> plataforms) {
		this.platforms = plataforms;
	}
//	public List<String> getGenres() {
//		return genres;
//	}
//	public void setGenres(List<String> genres) {
//		this.genres = genres;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getReleaseDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}	

}