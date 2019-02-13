package entites;

import services.SalleService;

import java.io.IOException;

public class Salle {
	private static int inc = 0;
	private int id;
	private int nbPlaceNormal;
	private int nbPlaceReduit;
	private int nbPlaceGratuit;

	public Salle(int id, int nbPlaceNomral, int nbPlaceReduit, int nbPlaceGratuit) {
		this.id = id;
		this.nbPlaceNormal = nbPlaceNomral;
		this.nbPlaceReduit = nbPlaceReduit;
		this.nbPlaceGratuit = nbPlaceGratuit;
	}

	public Salle(int nbPlaceNormal, int nbPlaceReduit, int nbPlaceGratuit) {
		this.id = ++inc;
		this.nbPlaceNormal = nbPlaceNormal;
		this.nbPlaceReduit = nbPlaceReduit;
		this.nbPlaceGratuit = nbPlaceGratuit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbPlaceNormal() {
		return nbPlaceNormal;
	}

	public void setNbPlaceNormal(int nbPlaceNormal) {
		this.nbPlaceNormal = nbPlaceNormal;
	}

	public int getNbPlaceReduit() {
		return nbPlaceReduit;
	}

	public void setNbPlaceReduit(int nbPlaceReduit) {
		this.nbPlaceReduit = nbPlaceReduit;
	}

	public int getNbPlaceGratuit() {
		return nbPlaceGratuit;
	}

	public void setNbPlaceGratuit(int nbPlaceGratuit) {
		this.nbPlaceGratuit = nbPlaceGratuit;
	}

	@Override
	public String toString() {
		return "Salle{" +
				"id=" + id +
				", nbPlaceNormal=" + nbPlaceNormal +
				", nbPlaceReduit=" + nbPlaceReduit +
				", nbPlaceGratuit=" + nbPlaceGratuit +
				'}';
	}
}
