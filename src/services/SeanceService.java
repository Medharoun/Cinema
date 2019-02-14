package services;

import entites.Film;
import entites.Salle;
import entites.Seance;
import exceptions.NoAvailableRoomException;
import exceptions.NoMorePlaceException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeanceService extends Service<Seance>{
    File file = new File("seance");
    FilmService filmService = new FilmService();
    SalleService salleService = new SalleService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");




    @Override
    public void add(Seance seance) throws IOException, ParseException, NoAvailableRoomException {
        List<Seance> seances = findAll();
        for (Seance seance1 : seances) {
            if ((seance.getDateHeureDiff().after(seance1.getDateHeureDiff())) && ((seance.getDateHeureDiff().before(Date.from(seance1.getDateHeureDiff().toInstant().plus(Duration.ofMinutes(seance1.getFilm().getDuration()))))) && (seance.getSalle().getId() == seance1.getSalle().getId())))
                throw new NoAvailableRoomException("Room not available");
        }
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, true));
        fichier.write(findAll().size() + 1 + "," + seance.getFilm().getId() + "," + seance.getSalle().getId() + "," + sdf.format(seance.getDateHeureDiff()) + "," + seance.getAvailableNormal() + "," + seance.getAvailableReduit() + "," + seance.getAvailableGratuit());
        fichier.newLine();
        fichier.close();
    }

    @Override
    public void update(Seance seance) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuffer inputBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(",");
            if (seance.getNum() != Integer.parseInt(tab[0])) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            } else {
                inputBuffer.append(seance.getNum() + "," + seance.getFilm().getId() + "," + seance.getSalle().getId() + "," + sdf.format(seance.getDateHeureDiff()) + "," + seance.getAvailableNormal() + "," + seance.getAvailableReduit() + "," + seance.getAvailableGratuit());
                inputBuffer.append('\n');
            }
        }
        reader.close();
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, false));
        fichier.write(inputBuffer.toString());
        fichier.close();
    }

    @Override
    public void delete(int id) throws IOException {

    }

    @Override
    public List<Seance> findAll() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Seance> seances = new ArrayList<Seance>();
        String line;
        Seance seance;
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(",");
            Film film = filmService.findByID(Integer.parseInt(tab[1]));
            Salle salle = salleService.findByID(Integer.parseInt(tab[2]));
            seance = new Seance(Integer.parseInt(tab[0]), film, salle, sdf.parse(tab[3]),Integer.parseInt(tab[4]),Integer.parseInt(tab[5]),Integer.parseInt(tab[6]));
            seances.add(seance);
        }
        reader.close();
        return seances;
    }


    public List<Seance> getSeancesOfThisWeek() throws IOException, ParseException {
        SimpleDateFormat dateOnly = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);
        LocalDate sunday = today.plusDays(today.getDayOfWeek().getValue() - 1);
        Date mondayDate = dateOnly.parse(monday.getDayOfMonth()+"-"+monday.getMonthValue()+"-"+monday.getYear());
        Date sundayDate = dateOnly.parse(sunday.getDayOfMonth()+"-"+sunday.getMonthValue()+"-"+sunday.getYear());
        List<Seance> seances = findAll();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if ((dateOnly.parse(sdf.format(seance.getDateHeureDiff())).after(mondayDate)) && (dateOnly.parse(sdf.format(seance.getDateHeureDiff())).before(sundayDate))){
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public List<Seance> getSeancesProchaines() throws IOException, ParseException {
        List<Seance> seances = findAll();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getDateHeureDiff().after(new Date())) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }


    public List<Seance> getSeancesByFilm(Film film) throws IOException, ParseException {
        List<Seance> seances = findAll();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getFilm().getId() == film.getId()) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public List<Seance> getSeancesBySalle(Salle salle) throws IOException, ParseException {
        List<Seance> seances = findAll();
        List<Seance> seancesResult = new ArrayList<>();
        for (Seance seance : seances) {
            if (seance.getSalle().getId() == salle.getId()) {
                seancesResult.add(seance);
            }
        }
        return seancesResult;
    }

    public void reserverReduit(Seance seance) throws NoMorePlaceException, IOException {
        if (seance.getAvailableReduit() == 0)
            throw new NoMorePlaceException(
                    "No available reduced places at this time , Please check another type of places");
        seance.setAvailableReduit(seance.getAvailableReduit() - 1);
        update(seance);
    }

    public void reserverGratuit(Seance seance) throws NoMorePlaceException, IOException {
        if (seance.getAvailableGratuit() == 0)
            throw new NoMorePlaceException(
                    "No available free places at this time , Please check another type of places");
        seance.setAvailableGratuit(seance.getAvailableGratuit() - 1);
        update(seance);

    }

    public void reserverNormal(Seance seance) throws NoMorePlaceException, IOException {
        if (seance.getAvailableNormal() == 0)
            throw new NoMorePlaceException(
                    "No available normal places at this time , Please check another type of places");
        seance.setAvailableNormal(seance.getAvailableNormal() - 1);
        update(seance);
    }



    public Seance findByID(int id) throws IOException, ParseException {
        List<Seance> seances = findAll();
        Seance seance = null;
        for(Seance s : seances) {
            if(id == s.getNum()) {
                seance = s;
            }
        }
        return seance;
    }

    public int chiffreDaffaire() throws IOException, ParseException {
        int total=0;
        List<Seance> seances = findAll();
        for(Seance seance : seances ){
            total+= (seance.getSalle().getNbPlaceNormal()-seance.getAvailableNormal())*10 + (seance.getSalle().getNbPlaceReduit()-seance.getAvailableReduit())*6 ;
        }
        return total;
    }


}
