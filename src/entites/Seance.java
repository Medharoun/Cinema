package entites;

import java.sql.Date;

public class Seance {
	private int idFilm;
	private int idSalle;
	private Date dateHeureDiff;

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public Date getDateHeureDiff() {
		return dateHeureDiff;
	}

	public void setDateHeureDiff(Date dateHeureDiff) {
		this.dateHeureDiff = dateHeureDiff;
	}

	
}
