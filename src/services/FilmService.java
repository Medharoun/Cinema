package services;

import entites.Film;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmService extends Service<Film> {
    File file = new File("film");


    @Override
    public void add(Film film) throws IOException {
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, true));
        fichier.write(film.getId() + ":" + film.getTitle() + ":" + film.getRealisator() + ":" + film.getDescription() + ":" + film.getDuration());
        fichier.newLine();
        fichier.close();
    }

    @Override
    public void update(Film film) throws IOException {
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

    @Override
    public void delete(int id) throws IOException {
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

    @Override
    public List<Film> findAll() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Film> films = new ArrayList<Film>();
        String line;
        Film film;
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            film = new Film(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], Integer.parseInt(tab[4]));
            films.add(film);
        }
        reader.close();
        return films;
    }

    @Override
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
