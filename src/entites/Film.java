package entites;

import java.time.Duration;

public class Film {
	private int id;
	private String title;
	private String realisator;
	private String description;
	private Duration duration;

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRealisator() {
		return realisator;
	}

	public void setRealisator(String realisator) {
		this.realisator = realisator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Film(int id, String title, String realisator, String description, Duration duration) {
		this.id = id;
		this.title = title;
		this.realisator = realisator;
		this.description = description;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Film{" +
				"id=" + id +
				", title='" + title + '\'' +
				", realisator='" + realisator + '\'' +
				", description='" + description + '\'' +
				", duration=" + duration +
				'}';
	}
}
