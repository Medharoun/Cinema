package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import entites.Film;
import exceptions.DuplicatedFilmException;

public class FilmService {
    File file = new File("film");

    public void addFilm(Film film) throws IOException {

        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, true));
        fichier.write(findAll().size()+1 + ":" + film.getTitle() + ":" + film.getRealisator() + ":" + film.getDescription() + ":" + film.getDuration());
        fichier.newLine();
        System.out.println("succes!");
        fichier.close();
    }

    public void updateFilm(Film film) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuffer inputBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            if (film.getId() != Integer.parseInt(tab[0])) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            } else {
                inputBuffer.append(film.getId() + ":" + film.getTitle() + ":" + film.getRealisator() + ":"
                        + film.getDescription() + ":" + film.getDuration());
                inputBuffer.append('\n');
            }
        }
        reader.close();
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, false));
        fichier.write(inputBuffer.toString());
        fichier.close();
    }

    public void deleteFilm(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuffer inputBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            if (id != Integer.parseInt(tab[0])) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
        }
        reader.close();
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, false));
        fichier.write(inputBuffer.toString());
        fichier.close();
    }

    public List<Film> findAll() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Film> films = new ArrayList<Film>();
        String line;
        Film film;
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            film = new Film(tab[1], tab[2], tab[3] ,Integer.parseInt(tab[4]));
            films.add(film);
        }
        reader.close();
        return films;
    }


    public Film findByTitle(String title) throws IOException {
        List<Film> films = findAll();
        Film film = null;
        for (Film f : films) {
            if (title == f.getTitle()) {
                film = f;
            }
        }
        return film;
    }

    public Film findByID(int id) throws IOException {
        List<Film> films = findAll();
        Film film = null;
        for (Film f : films) {
            if (id == f.getId()) {
                film = f;
            }
        }
        return film;
    }

}
