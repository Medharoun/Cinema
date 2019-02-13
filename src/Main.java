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
        String choix;
        do {
            menuPrincipal();
            do {
                System.out.println("Votre choix : ");
                choix = sc.next();
            } while (!choix.matches("[\\d]") || Integer.parseInt(choix) <= 0 || Integer.parseInt(choix) >= 6 );
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
                    } while (!choixSalle.matches("[\\d]") || Integer.parseInt(choixSalle) <= 0 || Integer.parseInt(choixSalle) >= 6 );
                    int intChoixSalle = Integer.parseInt(choixSalle);
                    switch (intChoixSalle) {
                        case 1: {
                            System.out.println(" Ajouter une salle ");
                            String nbPlaceN;
                            do {
                                System.out.println("Nombre de place normales: ");
                                nbPlaceN = sc.next();
                            } while (!nbPlaceN.matches("[\\d][\\d]*") || Integer.parseInt(nbPlaceN) <= 0);
                            String nbPlaceR;
                            do {
                                System.out.println("Nombre de place reduites: ");
                                nbPlaceR = sc.next();
                            } while (!nbPlaceR.matches("[\\d][\\d]*") || Integer.parseInt(nbPlaceR) <= 0);
                            String nbPlaceG;
                            do {
                                System.out.println("Nombre de place gratuites: ");
                                nbPlaceG = sc.next();
                            } while (!nbPlaceG.matches("[\\d][\\d]*") || Integer.parseInt(nbPlaceG) <= 0);
                            int idSalle = 1;
                            if (salleService.findAll().size() > 0)
                                idSalle = salleService.findAll().get(salleService.findAll().size() - 1).getId() + 1;
                            Salle salle = new Salle(idSalle, Integer.parseInt(nbPlaceN), Integer.parseInt(nbPlaceR), Integer.parseInt(nbPlaceG));
                            salleService.addSalle(salle);
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
                            do {
                                System.out.println("numero de salle a supprimer: ");
                                idSalleS = sc.next();
                            } while (!idSalleS.matches("[\\d][\\d]*") || Integer.parseInt(idSalleS) <= 0);
                            salleService.deleteSalle(Integer.parseInt(idSalleS));
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
                                } while (!idSalleM.matches("\\d+") || Integer.parseInt(idSalleM) <= 0);
                                Salle salle = salleService.findByID( Integer.parseInt(idSalleM));
                                if (salle == null) {
                                    System.out.println("Salle de numero " + idSalleM + " est introuvable \n");
                                    isNull = true;
                                }
                            } while (isNull);
                            String nn;
                            String nr;
                            String ng;
                            do {
                                System.out.println("Nouveau nombre de places normales");
                                nn = sc.next();
                            } while (!nn.matches("[\\d][\\d]*") || Integer.parseInt(nn) <= 0);
                            do {
                                System.out.println("Nouveau nombre de places reduites");
                                nr = sc.next();
                            } while (!nr.matches("[\\d][\\d]*") || Integer.parseInt(nr) <= 0);
                            do {
                                System.out.println("Nouveau nombre de places gratuits");
                                ng = sc.next();
                            } while (!ng.matches("[\\d][\\d]*") || Integer.parseInt(ng) <= 0);
                            Salle salle = new Salle(Integer.parseInt(idSalleM), Integer.parseInt(nn), Integer.parseInt(nr), Integer.parseInt(ng));
                            salleService.updateSalle(salle);
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
                    } while (!choixFilm.matches("[\\d]") || Integer.parseInt(choixFilm) <= 0 || Integer.parseInt(choixFilm) >= 6 );
                    int intChoixFilm = Integer.parseInt(choixFilm);
                    switch (intChoixFilm) {
                        case 1: {
                            System.out.println(" Ajouter un film ");
                            String title;
                            String[] input;
                            Scanner scFilm = new Scanner(System.in);

                            do {
                                System.out.println("Titre de film: ");
                                title = scFilm.nextLine();
                            } while (title.matches("\\d[\\w|\\s]*"));
                            String realisator;
                            do {
                                System.out.println("Realisateur de film: ");
                                realisator = scFilm.nextLine();
                            } while (realisator.matches("\\d[\\w|\\s]*"));
                            String description;
                            do {
                                System.out.println("Description de film: ");
                                description = scFilm.nextLine();
                            } while (description.matches("\\d[\\w|\\s]*"));
                            String duration;
                            do {
                                System.out.println("Duree du film (en minute): ");
                                duration = scFilm.next();
                            } while (!duration.matches("[\\d][\\d]*") || Integer.parseInt(duration) <= 0);
                            int idFilm = 1;
                            if (filmService.findAll().size() > 0)
                                idFilm = filmService.findAll().get(filmService.findAll().size() - 1).getId() + 1;
                            Film film = new Film(idFilm, title, realisator, description, Integer.parseInt(duration));
                            filmService.addFilm(film);
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
                            do {
                                System.out.println("numero de film a supprimer: ");
                                idFilmS = sc.next();
                            } while (idFilmS.length() == 0 || !idFilmS.matches("\\d") || Integer.parseInt(idFilmS) <= 0);
                            filmService.deleteFilm(Integer.parseInt(idFilmS));
                            System.out.println("succes !");
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
                            } while (!isNull);
                            String nTitle;
                            String nRealisator;
                            String nDescription;
                            String nDuree;
                            do {
                                System.out.println("Nouveau titre[ " + film.getTitle() + " ]: ");
                                nTitle = sc.next();
                            } while (nTitle.length() == 0 || !nTitle.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouveau realisateur[ " + film.getRealisator() + " ]: ");
                                nRealisator = sc.next();
                            } while (nRealisator.length() == 0 || !nRealisator.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouvelle description[ " + film.getDescription() + " ]: ");
                                nDescription = sc.next();
                            } while (nDescription.length() == 0 || !nDescription.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouvelle duree du film (en minute)[ " + film.getDuration() + " ]: ");
                                nDuree = sc.next();
                            } while (nDuree.length() == 0 || !nDuree.matches("[\\d][\\d]*") || Integer.parseInt(nDuree) <= 0);
                            Film film1 = new Film(Integer.parseInt(idFilmM), nTitle, nRealisator, nDescription, Integer.parseInt(nDuree));
                            filmService.updateFilm(film1);
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
                    System.out.println("2 - Supprimer une seance ");
                    System.out.println("3 - Modifier un seance ");
                    System.out.println("4 - Afficher tout les film");
                    System.out.println("5 - Retour au menu pricipal ");
                    String choixFilm;
                    do {
                        System.out.println("Votre choix : ");
                        choixFilm = sc.next();
                    } while (!choixFilm.matches("[\\d]") || Integer.parseInt(choixFilm) <= 0 || Integer.parseInt(choixFilm) >= 6 );
                    int intChoixFilm = Integer.parseInt(choixFilm);
                    switch (intChoixFilm) {
                        case 1: {
                            System.out.println(" Ajouter un film ");
                            String title;
                            String[] input;
                            Scanner scFilm = new Scanner(System.in);

                            do {
                                System.out.println("Titre de film: ");
                                title = scFilm.nextLine();
                            } while (title.matches("\\d[\\w|\\s]*"));
                            String realisator;
                            do {
                                System.out.println("Realisateur de film: ");
                                realisator = scFilm.nextLine();
                            } while (realisator.matches("\\d[\\w|\\s]*"));
                            String description;
                            do {
                                System.out.println("Description de film: ");
                                description = scFilm.nextLine();
                            } while (description.matches("\\d[\\w|\\s]*"));
                            String duration;
                            do {
                                System.out.println("Duree du film (en minute): ");
                                duration = scFilm.next();
                            } while (!duration.matches("[\\d][\\d]*") || Integer.parseInt(duration) <= 0);
                            int idFilm = 1;
                            if (filmService.findAll().size() > 0)
                                idFilm = filmService.findAll().get(filmService.findAll().size() - 1).getId() + 1;
                            Film film = new Film(idFilm, title, realisator, description, Integer.parseInt(duration));
                            filmService.addFilm(film);
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
                            do {
                                System.out.println("numero de film a supprimer: ");
                                idFilmS = sc.next();
                            } while (idFilmS.length() == 0 || !idFilmS.matches("\\d") || Integer.parseInt(idFilmS) <= 0);
                            filmService.deleteFilm(Integer.parseInt(idFilmS));
                            System.out.println("succes !");
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
                            } while (!isNull);
                            String nTitle;
                            String nRealisator;
                            String nDescription;
                            String nDuree;
                            do {
                                System.out.println("Nouveau titre[ " + film.getTitle() + " ]: ");
                                nTitle = sc.next();
                            } while (nTitle.length() == 0 || !nTitle.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouveau realisateur[ " + film.getRealisator() + " ]: ");
                                nRealisator = sc.next();
                            } while (nRealisator.length() == 0 || !nRealisator.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouvelle description[ " + film.getDescription() + " ]: ");
                                nDescription = sc.next();
                            } while (nDescription.length() == 0 || !nDescription.matches("\\w[\\w|\\s]*"));
                            do {
                                System.out.println("Nouvelle duree du film (en minute)[ " + film.getDuration() + " ]: ");
                                nDuree = sc.next();
                            } while (nDuree.length() == 0 || !nDuree.matches("[\\d][\\d]*") || Integer.parseInt(nDuree) <= 0);
                            Film film1 = new Film(Integer.parseInt(idFilmM), nTitle, nRealisator, nDescription, Integer.parseInt(nDuree));
                            filmService.updateFilm(film1);
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

                case 5: {
                    break;
                }
            }
        } while (Integer.parseInt(choix) != 5);


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
