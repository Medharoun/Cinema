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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, NoAvailableRoomException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("\nBienvenue a notre Cinema \n");
        System.out.println("Selectionner votre choix");
        System.out.println("1 - Gestion des salles ");
        System.out.println("2 - Gestion des films ");
        System.out.println("3 - Gestion des seances ");
        System.out.println("4 - Calculer chiffre d'affaire et taux de remplissage  ");
        System.out.println("5 - Quitter l'application \n");
//		int choix;
//		do {
//			System.out.println("Votre choix : ");
//			choix = sc.nextInt();
//		}while (choix <= 0 || choix >= 5);

        /**##############################*/
        SalleService salleService = new SalleService();
        SeanceService seanceService = new SeanceService();
        FilmService filmService = new FilmService();

        Film film = new Film(filmService.findAll().size() + 1, "789", "aaa", "azerty", 120);
        Salle salle = new Salle(salleService.findAll().size() + 1, 20, 15, 5);
        filmService.addFilm(film);
        salleService.addSalle(salle);

        Seance seance = new Seance(seanceService.getAllSeances().size() + 1, film, salle, sdf.parse(sdf.format(new Date())));
        Seance seance1 = new Seance(seanceService.getAllSeances().size() + 1, film, salle, Date.from(sdf.parse(sdf.format(new Date())).toInstant().plus(Duration.ofMinutes(200))));


        seanceService.projectFilm(seance);
        seanceService.projectFilm(seance1);
        seanceService.reserverReduit(seance);
        seanceService.reserverReduit(seance);
        seanceService.reserverReduit(seance);
        seanceService.reserverGratuit(seance);
        seanceService.reserverGratuit(seance);
        seanceService.reserverNormal(seance);

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
