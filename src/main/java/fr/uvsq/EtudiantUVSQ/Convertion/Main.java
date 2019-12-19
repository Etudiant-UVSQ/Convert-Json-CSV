package fr.uvsq.EtudiantUVSQ.Convertion;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;


import Exceptions.IncorrectFileExtensionException;
import Exceptions.NotValidPathException;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Main {

	public static void main(String[] args) throws NotValidPathException {
		System.out.println("Saisissez l'URL (PATH) du fichier à convertir");
		Scanner s = new Scanner(System.in);
		String url;
		url=s.nextLine();
		try {
			if (url.toLowerCase().endsWith(".json")) {
				try {
					traitementJson(url);
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
			else {
				if (url.toLowerCase().endsWith(".csv"))traitementCSV(url);
				else
					throw new IncorrectFileExtensionException("Le type de fichier entré n'est pas un Json un CSV !! Veillez entrer un fichier valide");
			}
		}catch (NotValidPathException e){
			throw new NotValidPathException("Le Chemin d'accès n'est pas valide");
		}



	}
	/**
	 * Traitement des fichier Json
	 * sens de conversion Json vers CSV
	 *
	 * @param cheminfichier
	 *
	 */
	public static void traitementJson(String cheminfichier) throws NotValidPathException, JSONException, IOException, URISyntaxException {
		String confPath=generationConf(cheminfichier);
		if(!confPath.isEmpty()){
			System.out.println("Nous avons déteter un fichier Identifier un fichier! Voulez-vous modifier le fichier de configuration? OUI/NON");
			Scanner s = new Scanner(System.in);
			String choix = s.nextLine();
			if (choix.toLowerCase().equals("oui")){
				System.out.println("Path du fichier de configuration; "+ confPath+" " +
						"\n*[Reportez cous au fichier README pour mieux comprendre.]" +
						"\n*[ Une fois le fichier modifié et enregistrer appuyer sur unr touche pour continuer... ]");
			}
		}
		
		String fichierOut;
		Scanner s = new Scanner(System.in);
		System.out.println("Saisissez le chemin absolu suivis du nom du fichier cible: ");
		fichierOut = s.nextLine();

		try (Scanner file = new Scanner(new File(cheminfichier))) {
			if (file.hasNextLine()) {
				if (ConvertJsonToCSV.convertir(new File(cheminfichier),"/root/IdeaProjects/Convert-Json-CSV/files/test2")){
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
	/**
	 * Traitement des fichier CSV
	 * sens de conversion CSV vers Json
	 *
	 * !!!Attention pour ce cas il faut prealablement que le fichier java object correspondant
	 * soit rennomé "Temp.java" et placé dans le même repertoire que ce fichier.
	 *
	 * Il est implementé une fonction du POJO correspondant mais il faudra executé le program deux fois,
	 * une fois pour la generation et la seconde pour la conversion
	 *
	 * @param cheminfichier
	 *

	 */

	public static void traitementCSV(String cheminfichier) throws NotValidPathException {

		System.out.print(" * Traitement des fichier CSV: sens de conversion CSV vers Json * \n" +
				"\t * !!!Attention pour ce cas il faut prealablement (avant l'execution) que le fichier java object correspondant\n" +
				"\t * soit rennomé \"Temp.java\" et placé dans le même repertoire que ce fichier.\n"
				+"\t* Il est implementé une fonction du POJO correspondant mais il faudra executé le program deux fois,\n" +
				"\t * une fois pour la generation et la seconde pour la conversion.");
		String fichierOut;
		String rep;
		Scanner s = new Scanner(System.in);
		System.out.println("Saisissez le chemin absolu (uniquement) du fichier cible: ");
		rep = s.nextLine();

		System.out.println("Saisissez le nom du fichier cible: ");
		fichierOut = s.nextLine();

		try (Scanner file = new Scanner(new File(cheminfichier))) {
			if (file.hasNextLine()) {
				if (ConvertCSVToJson.convertir(cheminfichier,fichierOut,rep)){
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

	public static String generationConf(String cheminfichier) throws IOException, URISyntaxException, JSONException {
		try{


		JsonArray object = Json.parse(new FileReader(cheminfichier)).asArray();
		JsonObject elem = object.get(0).asObject();
		JsonObject conf = Json.parse(new FileReader("/root/IdeaProjects/Convert-Json-CSV/files/fichierConf.json")).asObject();

		JsonObject attr = conf.get("Json2CSV").asObject();

        for (int i = 0; i <=object.size() ; i++) {

        	attr.add(elem.names().get(i),elem.names().get(i));
        }
        Writer out = new FileWriter("/root/IdeaProjects/Convert-Json-CSV/files/fichierConf.json");
        JSONObject o = new JSONObject(conf.toString());
		out.write(o.toString(4));
			return "/root/IdeaProjects/Convert-Json-CSV/files/fichierConf.json";
		}catch (Exception e){
			return "null";
		}


	}

}
