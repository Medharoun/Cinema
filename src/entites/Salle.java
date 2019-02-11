package entites;

import exceptions.NoMorePlaceException;

public class Salle {
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



}
