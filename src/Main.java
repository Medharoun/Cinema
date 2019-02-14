import entites.Film;
import entites.Salle;
import entites.Seance;
import exceptions.NoMorePlaceException;
import services.FilmService;
import services.SalleService;
import services.SeanceService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, NoMorePlaceException, ParseException {
        // TODO Auto-generated method stub
        FilmService filmService = new FilmService();
        SalleService salleService = new SalleService();
        SeanceService seanceService = new SeanceService();

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String choix;
        do {
            menuPrincipal();
            do {
                System.out.println("Votre choix : ");
                choix = sc.next();
            } while (!choix.matches("(\\d)") || Integer.parseInt(choix) <= 0 || Integer.parseInt(choix) >= 6);
            int intChoix = Integer.parseInt(choix);
            switch (intChoix) {
                case 1: {
                    System.out.println("Gestion des salles \n");

                    System.out.println("1 - Ajouter une salle ");
                    System.out.println("2 - Supprimer une salle ");
                    System.out.println("3 - Modifier une salle ");
                    System.out.println("4 - Afficher tout les salles ");
                    System.out.println("5 - Retour au menu pricipal ");
                    String choixSalle;
                    do {
                        System.out.println("Votre choix : ");
                        choixSalle = sc.next();
                    } while (!choixSalle.matches("(\\d)") || Integer.parseInt(choixSalle) <= 0 || Integer.parseInt(choixSalle) >= 6);
                    int intChoixSalle = Integer.parseInt(choixSalle);
                    switch (intChoixSalle) {
                        case 1: {
                            System.out.println(" Ajouter une salle ");
                            String nbPlaceN;
                            do {
                                System.out.println("Nombre de place normales: ");
                                nbPlaceN = sc.next();
                            } while (!nbPlaceN.matches("\\d(\\d)*") || Integer.parseInt(nbPlaceN) <= 0);
                            String nbPlaceR;
                            do {
                                System.out.println("Nombre de place reduites: ");
                                nbPlaceR = sc.next();
                            } while (!nbPlaceR.matches("\\d(\\d)*") || Integer.parseInt(nbPlaceR) <= 0);
                            String nbPlaceG;
                            do {
                                System.out.println("Nombre de place gratuites: ");
                                nbPlaceG = sc.next();
                            } while (!nbPlaceG.matches("\\d(\\d)*") || Integer.parseInt(nbPlaceG) <= 0);
                            int idSalle = 1;
                            if (salleService.findAll().size() > 0)
                                idSalle = salleService.findAll().get(salleService.findAll().size() - 1).getId() + 1;
                            Salle salle = new Salle(idSalle, Integer.parseInt(nbPlaceN), Integer.parseInt(nbPlaceR), Integer.parseInt(nbPlaceG));
                            salleService.add(salle);
                            System.out.println("succes !");
                            break;
                        }
                        case 2: {
                            System.out.println(" Supprimer une salle \n");
                            System.out.println(" Liste des salles \n");
                            List<Salle> salles = salleService.findAll();
                            for (Salle s : salles) {
                                System.out.println(s.toString());
                            }
                            String idSalleS;
                            boolean isNull = false;
                            do {
                                do {
                                    System.out.println("numero de salle a supprimer: ");
                                    idSalleS = sc.next();
                                } while (!idSalleS.matches("\\d(\\d)*") || Integer.parseInt(idSalleS) <= 0);
                                Salle salle = salleService.findByID(Integer.parseInt(idSalleS));
                                if (salle == null) {
                                    System.out.println("Salle de numero " + idSalleS + " est introuvable \n");
                                    isNull = true;
                                }
                            } while (isNull);


                            salleService.delete(Integer.parseInt(idSalleS));

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
                            String idSalleM;
                            do {
                                do {
                                    System.out.println("numero de salle a modifier: ");
                                    idSalleM = sc.next();
                                } while (!idSalleM.matches("\\d(\\d)*") || Integer.parseInt(idSalleM) <= 0);
                                Salle salle = salleService.findByID(Integer.parseInt(idSalleM));
                                if (salle == null) {
                                    System.out.println("Salle de numero " + idSalleM + " est introuvable \n");
                                    isNull = true;
                                }
                            } while (isNull);
                            String nn;
                            String nr;
                            String ng;
                            do {
                                System.out.println("Nouveau nombre de places normales [" + salleService.findByID(Integer.parseInt(idSalleM)).getNbPlaceNormal() + "]");
                                nn = sc.next();
                            } while (!nn.matches("\\d(\\d)*") || Integer.parseInt(nn) <= 0);
                            do {
                                System.out.println("Nouveau nombre de places reduites [" + salleService.findByID(Integer.parseInt(idSalleM)).getNbPlaceReduit() + "]");
                                nr = sc.next();
                            } while (!nr.matches("\\d(\\d)*") || Integer.parseInt(nr) <= 0);
                            do {
                                System.out.println("Nouveau nombre de places gratuits [" + salleService.findByID(Integer.parseInt(idSalleM)).getNbPlaceGratuit() + "]");
                                ng = sc.next();
                            } while (!ng.matches("\\d(\\d)*") || Integer.parseInt(ng) <= 0);
                            Salle salle = new Salle(Integer.parseInt(idSalleM), Integer.parseInt(nn), Integer.parseInt(nr), Integer.parseInt(ng));
                            salleService.update(salle);
                            System.out.println("succes !");
                            break;
                        }
                        case 4: {
                            System.out.println(" Afficher tout les salles \n");
                            System.out.println(" Liste des salles \n");
                            List<Salle> salles = salleService.findAll();
                            for (Salle s : salles) {
                                System.out.println(s.toString());
                            }
                            break;
                        }
                        case 5: {
                            menuPrincipal();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Gestion des films \n");

                    System.out.println("1 - Ajouter un film ");
                    System.out.println("2 - Supprimer un film ");
                    System.out.println("3 - Modifier un film ");
                    System.out.println("4 - Afficher tout les film");
                    System.out.println("5 - Retour au menu pricipal ");
                    String choixFilm;
                    do {
                        System.out.println("Votre choix : ");
                        choixFilm = sc.next();
                    } while (!choixFilm.matches("\\d") || Integer.parseInt(choixFilm) <= 0 || Integer.parseInt(choixFilm) >= 6);
                    int intChoixFilm = Integer.parseInt(choixFilm);
                    switch (intChoixFilm) {
                        case 1: {
                            System.out.println(" Ajouter un film ");
                            Scanner scFilm = new Scanner(System.in);

                            String title;

                            do {
                                System.out.println("Titre de film: ");
                                title = scFilm.nextLine();
                            } while (!title.matches("\\w(\\w|\\s)*"));
                            String realisator;
                            do {
                                System.out.println("Realisateur de film: ");
                                realisator = scFilm.nextLine();
                            } while (!realisator.matches("\\w(\\w|\\s)*"));
                            String description;
                            do {
                                System.out.println("Description de film: ");
                                description = scFilm.nextLine();
                            } while (!description.matches("\\w(\\w|\\s)*"));
                            String duration;
                            do {
                                System.out.println("Duree du film (en minute): ");
                                duration = scFilm.next();
                            } while (!duration.matches("\\d(\\d)*") || Integer.parseInt(duration) <= 0);
                            int idFilm = 1;
                            if (filmService.findAll().size() > 0)
                                idFilm = filmService.findAll().get(filmService.findAll().size() - 1).getId() + 1;
                            Film film = new Film(idFilm, title, realisator, description, Integer.parseInt(duration));
                            filmService.add(film);
                            System.out.println("succes !");
                            break;
                        }
                        case 2: {
                            System.out.println(" Supprimer un film \n");
                            System.out.println(" Liste des film \n");
                            List<Film> films = filmService.findAll();
                            for (Film f : films) {
                                System.out.println(f.toString());
                            }
                            String idFilmS;
                            boolean isNull = false;
                            Film film;
                            do {
                                do {
                                    System.out.println("numero de film a supprimer: ");
                                    idFilmS = sc.next();
                                } while (idFilmS.length() == 0 || !idFilmS.matches("\\d") || Integer.parseInt(idFilmS) <= 0);
                                film = filmService.findByID(Integer.parseInt(idFilmS));
                                if (film == null) {
                                    System.out.println("Film de numero " + idFilmS + " est introuvable");
                                    isNull = true;
                                }
                            } while (isNull);
                            List<Seance> seances = seanceService.getSeancesByFilm(filmService.findByID(Integer.parseInt(idFilmS)));
                            if (seances.size() > 0) {
                                System.out.println("Safe delete !!! You have to delete all his seances before");
                            } else {
                                filmService.delete(Integer.parseInt(idFilmS));
                                System.out.println("succes !");
                            }
                            break;
                        }
                        case 3: {
                            System.out.println(" Modifier un Film \n");
                            System.out.println(" Liste des films \n");
                            List<Film> films = filmService.findAll();
                            for (Film f : films) {
                                System.out.println(f.toString());
                            }
                            boolean isNull = false;
                            String idFilmM;
                            Film film;
                            do {
                                do {
                                    System.out.println("numero de film a modifier: ");
                                    idFilmM = sc.next();
                                } while (idFilmM.length() == 0 || !idFilmM.matches("\\d") || Integer.parseInt(idFilmM) <= 0);
                                film = filmService.findByID(Integer.parseInt(idFilmM));
                                if (film == null) {
                                    System.out.println("Film de numero " + idFilmM + " est introuvable");
                                    isNull = true;
                                }
                            } while (isNull);
                            String nTitle;
                            String nRealisator;
                            String nDescription;
                            String nDuree;
                            Scanner scFilm1 = new Scanner(System.in);

                            do {
                                System.out.println("Nouveau titre[ " + film.getTitle() + " ]: ");
                                nTitle = scFilm1.nextLine();
                            } while (nTitle.length() == 0 || !nTitle.matches("\\w(\\w|\\s)*"));
                            do {
                                System.out.println("Nouveau realisateur[ " + film.getRealisator() + " ]: ");
                                nRealisator = scFilm1.nextLine();
                            } while (nRealisator.length() == 0 || !nRealisator.matches("\\w(\\w|\\s)*"));
                            do {
                                System.out.println("Nouvelle description[ " + film.getDescription() + " ]: ");
                                nDescription = scFilm1.nextLine();
                            } while (nDescription.length() == 0 || !nDescription.matches("\\w(\\w|\\s)*"));
                            do {
                                System.out.println("Nouvelle duree du film (en minute)[ " + film.getDuration() + " ]: ");
                                nDuree = scFilm1.nextLine();
                            } while (nDuree.length() == 0 || !nDuree.matches("\\d(\\d)*") || Integer.parseInt(nDuree) <= 0);
                            Film film1 = new Film(Integer.parseInt(idFilmM), nTitle, nRealisator, nDescription, Integer.parseInt(nDuree));
                            filmService.update(film1);
                            System.out.println("succes !");
                            break;
                        }
                        case 4: {
                            System.out.println(" Afficher tout les films \n");
                            System.out.println(" Liste des films \n");
                            List<Film> films = filmService.findAll();
                            for (Film f : films) {
                                System.out.println(f.toString());
                            }
                            break;
                        }
                        case 5: {
                            break;
                        }
                    }
                    break;

                }
                case 3: {
                    System.out.println("Gestion des seances \n");

                    System.out.println("1 - Ajouter une seance ");
                    System.out.println("2 - Reserver des places ");
                    System.out.println("3 - Afficher les seances de la semaine en cours ");
                    System.out.println("4 - Afficher toutes les seances ");
                    System.out.println("5 - Retour au menu pricipal ");
                    String choixSeances;
                    do {
                        System.out.println("Votre choix : ");
                        choixSeances = sc.next();
                    } while (!choixSeances.matches("\\d") || Integer.parseInt(choixSeances) <= 0 || Integer.parseInt(choixSeances) >= 7);
                    int intChoixSeances = Integer.parseInt(choixSeances);
                    switch (intChoixSeances) {
                        case 1: {
                            if (filmService.findAll().size() == 0 || salleService.findAll().size() == 0) {
                                System.out.println("Il faut ajouter au moins un film et une salle");
                                break;
                            }
                            System.out.println(" Ajouter une seance ");
                            String numFilm;
                            Scanner scSeance = new Scanner(System.in);
                            boolean filmIsNull = false;
                            for (Film film : filmService.findAll()) {
                                System.out.println(film.toString());
                            }
                            do {
                                do {
                                    System.out.println("num  de film: ");
                                    numFilm = scSeance.nextLine();
                                } while (!numFilm.matches("\\d(\\d)*"));
                                Film film = filmService.findByID(Integer.parseInt(numFilm));
                                if (film == null)
                                    filmIsNull = true;
                            } while (filmIsNull);

                            for (Salle salle : salleService.findAll()) {
                                System.out.println(salle.toString());
                            }
                            String numSalle;
                            boolean salleIsNull = false;
                            do {
                                do {
                                    System.out.println("numero de salle: ");
                                    numSalle = scSeance.nextLine();
                                } while (!numSalle.matches("\\d(\\d)*"));
                                Salle salle = salleService.findByID(Integer.parseInt(numSalle));
                                if (salle == null)
                                    salleIsNull = true;
                            } while (salleIsNull);
                            String date;
                            Date convDate = new Date();
                            boolean valideDate = true;
                            do {
                                valideDate = true;
                                System.out.println("Date de projection [JJ-MM-AAAA HH:mm] : ");
                                date = scSeance.nextLine();
                                try {
                                    convDate = sdf.parse(date);
                                } catch (ParseException e) {
                                    System.out.println("Svp enter la date sous la forme [JJ-MM-AAAA HH:mm]");
                                    valideDate = false;
                                }
                            } while (!valideDate);
                            int idSeance = 1;
                            if (seanceService.findAll().size() > 0)
                                idSeance = salleService.findAll().get(salleService.findAll().size() - 1).getId() + 1;
                            Film film = filmService.findByID(Integer.parseInt(numFilm));
                            Salle salle = salleService.findByID(Integer.parseInt(numSalle));
                            Seance seance = new Seance(idSeance, film, salle, convDate);
                            seanceService.add(seance);
                            System.out.println(" Succes ! \n");
                            break;
                        }
                        case 2: {
                            Scanner scSeance = new Scanner(System.in);
                            System.out.println(" Reserver des places \n");
                            if (seanceService.getSeancesProchaines().size() == 0) {
                                System.out.println("Pas de film pour les voir");
                                break;
                            }
                            System.out.println(" Liste des seances prochaines \n");
                            List<Seance> seances = seanceService.getSeancesProchaines();
                            for (Seance s : seances) {
                                System.out.println(s.toString());
                            }
                            String idSeance;
                            do {
                                System.out.println("numero de seance : ");
                                idSeance = scSeance.next();
                            } while (!idSeance.matches("\\d(\\d)*") || Integer.parseInt(idSeance) <= 0);
                            System.out.println(seanceService.findByID(Integer.parseInt(idSeance)) + "\n");
                            System.out.println("1 - Reserver une place normale");
                            System.out.println("2 - Reserver une place reduite");
                            System.out.println("3 - Reserver une place gratuit");
                            String typePlace;
                            do {
                                System.out.println("votre place : ");
                                typePlace = scSeance.next();
                            } while (!typePlace.matches("\\d") || Integer.parseInt(typePlace) <= 0 || Integer.parseInt(typePlace) >= 4);
                            int intTypePlace = Integer.parseInt(typePlace);
                            switch (intTypePlace) {
                                case 1: {
                                    seanceService.reserverNormal(seanceService.findByID(Integer.parseInt(idSeance)));
                                    break;
                                }
                                case 2: {
                                    seanceService.reserverReduit(seanceService.findByID(Integer.parseInt(idSeance)));
                                    break;
                                }
                                case 3: {
                                    seanceService.reserverGratuit(seanceService.findByID(Integer.parseInt(idSeance)));
                                    break;
                                }
                            }
                            System.out.println("succes !");
                            break;
                        }
                        case 3: {
                            System.out.println(" Afficher les seances de la semaine en cours \n");
                            System.out.println(" Liste des films \n");
                            List<Seance> seances = seanceService.getSeancesOfThisWeek();
                            for (Seance s : seances) {
                                System.out.println(s);
                            }
                            break;
                        }
                        case 4: {
                            System.out.println(" Afficher tout les seances\n");
                            System.out.println(" Liste des films \n");
                            List<Seance> seances = seanceService.findAll();
                            for (Seance s : seances) {
                                System.out.println(s);
                            }
                            break;
                        }
                        case 5: {
                            break;
                        }
                    }
                    break;

                }
                case 4: {
                    System.out.println("Chiffre d'affaire");
                    System.out.println(seanceService.chiffreDaffaire() + "DT");
                    break;
                }
                case 5: {
                    break;
                }
            }
        } while (Integer.parseInt(choix) != 5);
    }

    private static void menuPrincipal() {
        System.out.println("\nBienvenue a notre Cinema \n");
        System.out.println("Selectionner votre choix");
        System.out.println("1 - Gestion des salles ");
        System.out.println("2 - Gestion des films ");
        System.out.println("3 - Gestion des seances ");
        System.out.println("4 - Calculer chiffre d'affaire et taux de remplissage  ");
        System.out.println("5 - Quitter l'application \n");
    }
}
