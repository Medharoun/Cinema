package entites;

import exceptions.NoMorePlaceException;

public class Salle {
	private int id;
	private int nbPlaceNormal;
	private int nbPlaceReduit;
	private int nbPlaceGratuit;
	private int availableNormal = nbPlaceNormal;
	private int availableReduit = nbPlaceReduit;
	private int availableGratuit = nbPlaceGratuit;
	private Boolean[] pNormal;
	private Boolean[] pReduit;
	private Boolean[] pGratuit;

	public Salle(int id, int nbPlaceNomral, int nbPlaceReduit, int nbPlaceGratuit) {
		this.id = id;
		this.nbPlaceNormal = nbPlaceNomral;
		this.nbPlaceReduit = nbPlaceReduit;
		this.nbPlaceGratuit = nbPlaceGratuit;
		pNormal = new Boolean[nbPlaceNomral];
		pReduit = new Boolean[nbPlaceReduit];
		pGratuit = new Boolean[nbPlaceGratuit];
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

	public int getAvailableNormal() {
		return availableNormal;
	}

	public void setAvailableNormal(int availableNormal) {
		this.availableNormal = availableNormal;
	}

	public int getAvailableReduit() {
		return availableReduit;
	}

	public void setAvailableReduit(int availableReduit) {
		this.availableReduit = availableReduit;
	}

	public int getAvailableGratuit() {
		return availableGratuit;
	}

	public void setAvailableGratuit(int availableGratuit) {
		this.availableGratuit = availableGratuit;
	}

	public void reserverReduit() throws NoMorePlaceException {
		if (availableReduit == 0)
			throw new NoMorePlaceException(
					"No available reduit places at this time , Please check another type of places");
		availableReduit--;
		int i;
		for (i = 0; i < availableReduit; i++) {
			if (!pReduit[i])
				break;
		}
		pReduit[i] = true;

	}

	public void reserverGratuit() throws NoMorePlaceException {
		if (availableGratuit == 0)
			throw new NoMorePlaceException(
					"No available free places at this time , Please check another type of places");
		availableGratuit--;
		int i;
		for (i = 0; i < nbPlaceGratuit; i++) {
			if (!pGratuit[i])
				break;
		}
		pGratuit[i] = true;

	}

	public void reserverNormal() throws NoMorePlaceException {
		if (availableNormal == 0)
			throw new NoMorePlaceException(
					"No available normal places at this time , Please check another type of places");
		availableNormal--;
		int i;
		for (i = 0; i < nbPlaceNormal; i++) {
			if (!pNormal[i])
				break;
		}
		pNormal[i] = true;

	}

}
