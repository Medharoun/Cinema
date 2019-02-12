package services;

import entites.Salle;
import exceptions.DuplicatedSalleException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalleService {
    File file = new File("salle");

    public void addSalle(Salle salle) throws IOException , DuplicatedSalleException {
        if(findByID(salle.getId())!= null) throw new DuplicatedSalleException("This room is already saved");
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, true));
        fichier.write(salle.getId() + ":" + salle.getNbPlaceNormal()+ ":" + salle.getNbPlaceReduit() + ":" + salle.getNbPlaceGratuit());
        fichier.newLine();
        System.out.println("succes!");
        fichier.close();
    }


    public void updateSalle(Salle salle) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuffer inputBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            if (salle.getId() != Integer.parseInt(tab[0])) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            } else {
                inputBuffer.append(salle.getId() + ":" + salle.getNbPlaceNormal()+ ":" + salle.getNbPlaceReduit() + ":" + salle.getNbPlaceGratuit());
                inputBuffer.append('\n');
            }
        }
        reader.close();
        BufferedWriter fichier = new BufferedWriter(new FileWriter(file, false));
        fichier.write(inputBuffer.toString());
        fichier.close();
    }

    public List<Salle> findAll() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Salle> salles = new ArrayList<Salle>();
        String line;
        Salle salle;
        while ((line = reader.readLine()) != null) {
            String[] tab = line.split(":");
            salle = new Salle(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
            salles.add(salle);
        }
        reader.close();
        return salles;
    }

    public Salle findByID(int id) throws IOException{
        List<Salle> salles = findAll();
        Salle salle = null;
        for(Salle s : salles) {
            if(id == s.getId()) {
                salle = s;
            }
        }
        return salle;
    }




}
