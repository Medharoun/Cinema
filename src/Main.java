import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Bienvenue a notre Cinema \n");
		System.out.println("Selectionner votre choix");
		System.out.println("1 - Gestion des salles ");
		System.out.println("2 - Gestion des films ");
		System.out.println("3 - Gestion des séances ");
		System.out.println("4 - Calculer chiffre d'affaire et taux de remplissage  ");
		System.out.println("5 - Quitter l'application \n");
		int choix;
		do {
			System.out.println("Votre choix : ");
			choix = sc.nextInt();
		}while (choix <= 0 || choix >= 5);
		
	}

}
