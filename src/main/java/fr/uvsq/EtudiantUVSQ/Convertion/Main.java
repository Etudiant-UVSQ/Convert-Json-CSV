package fr.uvsq.EtudiantUVSQ.Convertion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import Exceptions.IncorrectFileExtensionException;
import Exceptions.NotValidPathException;


public class Main {

	public static void main(String[] args) throws NotValidPathException {
		System.out.println("Saisissez l'URL (PATH) du fichier à convertir");
		Scanner s = new Scanner(System.in);
		String url;
		url=s.nextLine();
		try {
			if (url.toLowerCase().endsWith(".json")) traitementJson(url);
			else {
				if (url.toLowerCase().endsWith(".csv"))traitementCSV(url);
				else
					throw new IncorrectFileExtensionException("Le type de fichier entré n'est pas un Json un CSV !! Veillez entrer un fichier valide");
			}
		}catch (NotValidPathException e){
			throw new NotValidPathException("Le Chemin d'accès n'est pas valide");
		}



	}
	public static void traitementJson(String cheminfichier) throws NotValidPathException{

		String fichierOut;
		Scanner s = new Scanner(System.in);
		System.out.println("Saisissez le chemin absolu suivis du nom du fichier cible: ");
		fichierOut = s.nextLine();

		try (Scanner file = new Scanner(new File(cheminfichier))) {
			if (file.hasNextLine()) {
				if (ConvertJsonToCSV.convertir(new File(cheminfichier),fichierOut)){
					System.out.println("Conversion réussi");
				}else
					System.out.println("Echec de la conversion Veillez réessayer");
			}
		} catch (FileNotFoundException err) {
			if (!isCorrectFileName(cheminfichier)) {
				throw new NotValidPathException("Le chemin Saisi n'est pas Valide", err);
			}
		}

	}

	public static void traitementCSV(String cheminfichier) throws NotValidPathException {

		String fichierOut;
		Scanner s = new Scanner(System.in);
		System.out.println("Saisissez le chemin absolu suivis du nom du fichier cible: ");
		fichierOut = s.nextLine();

		try (Scanner file = new Scanner(new File(cheminfichier))) {
			if (file.hasNextLine()) {
				if (ConvertJsonToCSV.convertir(new File(cheminfichier),fichierOut)){
					System.out.println("Conversion réussi");
				}else
					System.out.println("Echec de la conversion Veillez réessayer");
			}
		} catch (FileNotFoundException err) {
			if (!isCorrectFileName(cheminfichier)) {
				throw new NotValidPathException("Le chemin Saisi n'est pas Valide", err);
			}
		}

	}



	public static boolean isCorrectFileName(String fileName){
		if (!fileName.isEmpty()) return true;
		return false;
	}

}
