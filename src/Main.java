import entites.Film;
import entites.Salle;
import entites.Seance;
import exceptions.DuplicatedFilmException;
import exceptions.NoAvailableRoomException;
import services.FilmService;
import services.SalleService;
import services.SeanceService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, NoAvailableRoomException {
        // TODO Auto-generated method stub
        FilmService filmService = new FilmService();
        SalleService salleService = new SalleService();
        SeanceService seanceService = new SeanceService();

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("\nBienvenue a notre Cinema \n");
        System.out.println("Selectionner votre choix");
        System.out.println("1 - Gestion des salles ");
        System.out.println("2 - Gestion des films ");
        System.out.println("3 - Gestion des seances ");
        System.out.println("4 - Calculer chiffre d'affaire et taux de remplissage  ");
        System.out.println("5 - Quitter l'application \n");

        int choix;
        do {
            do {
                System.out.println("Votre choix : ");
                choix = sc.nextInt();
            } while (choix <= 0 || choix >= 6);

            switch (choix) {
                case 1: {
                    System.out.println("Gestion des salles \n");

                    System.out.println("1 - Ajouter une salle ");
                    System.out.println("2 - Supprimer une salle ");
                    System.out.println("3 - Modifier une salle ");
                    System.out.println("4 - Afficher tout les salles ");
                    System.out.println("5 - Retour au menu pricipal ");
                    int choixSalle;
                    do {
                        System.out.println("Votre choix : ");
                        choixSalle = sc.nextInt();
                    } while (choix <= 0 || choix >= 6);

                    switch (choixSalle) {
                        case 1: {
                            System.out.println(" Ajouter une salle ");
                            int nbPlaceN;
                            do {
                                System.out.println("Nombre de place normales: ");
                                nbPlaceN = sc.nextInt();
                            } while (nbPlaceN < 0);
                            int nbPlaceR;
                            do {
                                System.out.println("Nombre de place reduites: ");
                                nbPlaceR = sc.nextInt();
                            } while (nbPlaceR < 0);
                            int nbPlaceG;
                            do {
                                System.out.println("Nombre de place gratuites: ");
                                nbPlaceG = sc.nextInt();
                            } while (nbPlaceG < 0);
                            int idSalle = salleService.findAll().size() + 1;
                            Salle salle = new Salle(idSalle, nbPlaceN, nbPlaceR, nbPlaceG);
                            salleService.addSalle(salle);
                            break;
                        }
                        case 2: {
                            System.out.println(" Supprimer une salle \n");

                            System.out.println(" Liste des salles \n");
                            List<Salle> salles = salleService.findAll();
                            for (Salle s : salles) {
                                System.out.println(s.toString());
                            }
                            int idSalleS;
                            do {
                                System.out.println("numero de salle a supprimer: ");
                                idSalleS = sc.nextInt();
                            } while (idSalleS < 0);
                            salleService.deleteSalle(idSalleS);
                            break;
                        }
                        case 3: {
                            System.out.println(" Modifier une salle \n");
                            System.out.println(" Liste des salles \n");
                            List<Salle> salles = salleService.findAll();
                            for (Salle s : salles) {
                                System.out.println(s.toString());
                            }
                            boolean isNull = false;
                            int idSalleM;
                            do {
                                do {
                                    System.out.println("numero de salle a modifier: ");
                                    idSalleM = sc.nextInt();
                                } while (idSalleM < 0);
                                Salle salle = salleService.findByID(idSalleM);
                                if (salle == null) {
                                    System.out.println("Salle de numero " + idSalleM + " est introuvable");
                                    isNull = true;
                                }
                            } while (!isNull);
                            int nn;
                            int nr;
                            int ng;
                            do {
                                System.out.println("Nouveau nombre de places normales");
                                nn = sc.nextInt();
                            } while (nn < 0);
                            do {
                                System.out.println("Nouveau nombre de places reduites");
                                nr = sc.nextInt();
                            } while (nr < 0);
                            do {
                                System.out.println("Nouveau nombre de places gratuits");
                                ng = sc.nextInt();
                            } while (ng < 0);
                            Salle salle = new Salle(idSalleM,nn,nr,ng);
                            salleService.updateSalle(salle);
                            break;
                        }
                        case 4:{
                            System.out.println(" Afficher tout les salles \n");
                            System.out.println(" Liste des salles \n");
                            List<Salle> salles = salleService.findAll();
                            for (Salle s : salles) {
                                System.out.println(s.toString());
                            }
                            break;
                        }
                        case 5:{

                        }
                    }

                    break;
                }
                case 5: {
                    break;
                }
            }
            System.out.println("succes!");
        } while (choix != 5);


        /**##############################*/
//        SalleService salleService = new SalleService();
//        SeanceService seanceService = new SeanceService();
//        FilmService filmService = new FilmService();
//
//        Film film = new Film(filmService.findAll().size() + 1, "789", "aaa", "azerty", 120);
//        Salle salle = new Salle(salleService.findAll().size() + 1, 20, 15, 5);
//        filmService.addFilm(film);
//        salleService.addSalle(salle);
//
//        Seance seance = new Seance(seanceService.getAllSeances().size() + 1, film, salle, sdf.parse(sdf.format(new Date())));
//        Seance seance1 = new Seance(seanceService.getAllSeances().size() + 1, film, salle, Date.from(sdf.parse(sdf.format(new Date())).toInstant().plus(Duration.ofMinutes(200))));
//
//
//        seanceService.projectFilm(seance);
//        seanceService.projectFilm(seance1);
//        seanceService.reserverReduit(seance);
//        seanceService.reserverReduit(seance);
//        seanceService.reserverReduit(seance);
//        seanceService.reserverGratuit(seance);
//        seanceService.reserverGratuit(seance);
//        seanceService.reserverNormal(seance);

        /**##############################*/

//		Date d = new Date();
//		Date d1 = Date.from(d.toInstant().plus(Duration.ofMinutes(2)));
//		System.out.println(d);
//		System.out.println(d1);
//		try {
//			salleService.addSalle(salle);
//		} catch (DuplicatedSalleException duplicatedSalle) {
//			duplicatedSalle.printStackTrace();
//		}
//

    }

}
