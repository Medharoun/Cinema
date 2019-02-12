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

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println("Bienvenue a notre Cinema \n");
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
		Film film = new Film("789","aaa","azerty", 120);
		Salle salle = new Salle(1,20,15,5);
		SalleService salleService = new SalleService();
		Seance seance = new Seance(film,salle,sdf.parse(sdf.format(new Date())));
		Seance seance1 = new Seance(film,salle,Date.from(sdf.parse(sdf.format(new Date())).toInstant().plus(Duration.ofMinutes(200))));
		SeanceService seanceService = new SeanceService();
		FilmService filmService = new FilmService();
//		try {
//			filmService.addFilm(film);
//		} catch (DuplicatedFilmException duplicatedFilm) {
//			System.out.println(duplicatedFilm.getMessage());
//		}
		try {
//			seanceService.projectFilm(seance);
			seanceService.projectFilm(seance1);
		} catch (NoAvailableRoomException noAvailableDate) {
			System.out.println(noAvailableDate.getMessage());
		} catch (ParseException e) {
            e.printStackTrace();
        }
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
