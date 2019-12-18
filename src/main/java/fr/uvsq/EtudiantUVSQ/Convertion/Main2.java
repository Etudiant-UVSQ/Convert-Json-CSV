package fr.uvsq.EtudiantUVSQ.Convertion;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Main2 {
public static void main(String[] args) {
		
		int n;
		 Scanner s = new Scanner(System.in);
	
		 System.out.println("\n----------------------------------------------------\n");
		 System.out.println("Veuillez entrer un option de convertion : ");
		 System.out.println("\n1 : pour convertir du json en csv");
		 System.out.println("2 : pour convertir du csv en json");
		 System.out.println("3 : pour sortir");
		 System.out.println("\n----------------------------------------------------\n");
		 n=s.nextInt();
		 switch (n) {
		case 1:
			  

	        
	        System.out.println("Vous venez de convertir le fichier JSON 'file.json' to CSV 'file.csv ");
			break;
		case 2:System.out.println("Vous venez de convertir le fichier CSV 'file.csv' to JSON 'file.json'");
			break;

		default:System.out.println("Good bye!!!!!");
			break;
		}
		
	}
}
