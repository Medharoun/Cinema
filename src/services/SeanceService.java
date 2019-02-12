package services;

import entites.Film;
import entites.Salle;
import entites.Seance;
import exceptions.NoAvailableRoomException;
import exceptions.NoMorePlaceException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeanceService {
    File file = new File("seance");
    FilmService filmService = new FilmService();
    SalleService salleService = new SalleService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public void projectFilm(Seance seance) throws NoAvailableRoomException, IOException, ParseException{
        List<Seance> seances = getAllSeances();
        for(Seance seance1 : seances){



        }
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, true));
        fichier.write(seance.getFilm().getId() + "," + seance.getSalle().getId() + "," + sdf.format(seance.getDateHeureDiff()));
        fichier.newLine();
        System.out.println("succes!");
        fichier.close();
    }

    public List<Seance> getAllSeances() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Seance> seances = new ArrayList<Seance>();
        String line;
        Seance seance;
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(",");
            Film film = filmService.findByID(Integer.parseInt(tab[0]));
            Salle salle = salleService.findByID(Integer.parseInt(tab[1]));
            seance = new Seance(film, salle, sdf.parse(tab[2]));
            seances.add(seance);
        }
        reader.close();
        return seances;
    }

    public List<Seance> getSeancesByDate(Instant date) throws IOException, ParseException {
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        List<Seance> seances = getAllSeances();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (dateOnly.parse(sdf.format(seance.getDateHeureDiff())).toInstant() == date) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public List<Seance> getSeancesBetween2Dates(Date dateInf, Date dateSup) throws IOException, ParseException {
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        List<Seance> seances = getAllSeances();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if ((dateOnly.parse(sdf.format(seance.getDateHeureDiff())).after(dateInf)) && (dateOnly.parse(sdf.format(seance.getDateHeureDiff())).before(dateSup))) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public List<Seance> getSeancesByFilm(Film film) throws IOException, ParseException {
        List<Seance> seances = getAllSeances();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getFilm().getId() == film.getId()) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public List<Seance> getSeancesBySalle(Salle salle) throws IOException, ParseException {
        List<Seance> seances = getAllSeances();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getSalle().getId() == salle.getId()) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public void reserverReduit(Seance seance) throws NoMorePlaceException {
        if (seance.getAvailableReduit() == 0)
            throw new NoMorePlaceException(
                    "No available reduced places at this time , Please check another type of places");
        seance.setAvailableReduit(seance.getAvailableReduit() - 1);
    }

    public void reserverGratuit(Seance seance) throws NoMorePlaceException {
        if (seance.getAvailableGratuit() == 0)
            throw new NoMorePlaceException(
                    "No available free places at this time , Please check another type of places");
        seance.setAvailableGratuit(seance.getAvailableGratuit() - 1);
    }

    public void reserverNormal(Seance seance) throws NoMorePlaceException {
        if (seance.getAvailableNormal() == 0)
            throw new NoMorePlaceException(
                    "No available normal places at this time , Please check another type of places");
        seance.setAvailableNormal(seance.getAvailableNormal() - 1);
    }


}
