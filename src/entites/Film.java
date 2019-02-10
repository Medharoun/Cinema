package entites;

public class Film {
	private int id;
	private String title;
	private String realisator;
	private String description;

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

	public Film(int id, String title, String realisator, String description) {
		super();
		this.id = id;
		this.title = title;
		this.realisator = realisator;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", realisator=" + realisator + ", description=" + description
				+ "]";
	}

}
