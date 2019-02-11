import entites.Film;
import entites.Salle;
import entites.Seance;
import exceptions.DuplicatedFilm;
import exceptions.DuplicatedSalle;
import exceptions.NoAvailableDate;
import services.FilmService;
import services.SalleService;
import services.SeanceService;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Bienvenue a notre Cinema \n");
		System.out.println("Selectionner votre choix");
		System.out.println("1 - Gestion des salles ");
		System.out.println("2 - Gestion des films ");
		System.out.println("3 - Gestion des seances ");
		System.out.println("4 - Calculer chiffre d'affaire et taux de remplissage  ");
		System.out.println("5 - Quitter l'application \n");
		int choix;
		do {
			System.out.println("Votre choix : ");
			choix = sc.nextInt();
		}while (choix <= 0 || choix >= 5);
		Film film = new Film(1,"789","aaa","azerty");
		Salle salle = new Salle(1,20,15,5);
		SalleService salleService = new SalleService();
		Seance seance = new Seance(film,salle,new Date());
		SeanceService seanceService = new SeanceService();
		try {
			seanceService.projectFilm(seance);
		} catch (NoAvailableDate noAvailableDate) {
			noAvailableDate.printStackTrace();
		}

		try {
			salleService.addSalle(salle);
		} catch (DuplicatedSalle duplicatedSalle) {
			duplicatedSalle.printStackTrace();
		}


	}

}
