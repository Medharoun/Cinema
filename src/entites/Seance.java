package entites;

import exceptions.NoMorePlaceException;

import java.util.Date;

public class Seance {
    private int num;
    private Film film;
    private Salle salle;
    private Date dateHeureDiff;
    private int availableNormal;
    private int availableReduit;
    private int availableGratuit;

    public Seance(Film film, Salle salle, Date dateHeureDiff) {
        this.film = film;
        this.salle = salle;
        this.dateHeureDiff = dateHeureDiff;
        this.availableNormal = salle.getNbPlaceNormal();
        this.availableReduit = salle.getNbPlaceReduit();
        this.availableGratuit = salle.getNbPlaceGratuit();
    }

    public Seance(int num, Film film, Salle salle, Date dateHeureDiff) {
        this.num = num;
        this.film = film;
        this.salle = salle;
        this.dateHeureDiff = dateHeureDiff;
        this.availableNormal = salle.getNbPlaceNormal();
        this.availableReduit = salle.getNbPlaceReduit();
        this.availableGratuit = salle.getNbPlaceGratuit();
    }

    public Seance(int num, Film film, Salle salle, Date dateHeureDiff, int availableNormal, int availableReduit, int availableGratuit) {
        this.num = num;
        this.film = film;
        this.salle = salle;
        this.dateHeureDiff = dateHeureDiff;
        this.availableNormal = availableNormal;
        this.availableReduit = availableReduit;
        this.availableGratuit = availableGratuit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
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

    public Date getDateHeureDiff() {
        return dateHeureDiff;
    }

    public void setDateHeureDiff(Date dateHeureDiff) {
        this.dateHeureDiff = dateHeureDiff;
    }
}
