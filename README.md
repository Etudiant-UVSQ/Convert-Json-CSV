#### Outil de conversion

Conversion de JSON à CSV et vice Versa

Ce programme est un outil de conversion de fichier Json vers CSV et vice-Versa
 Il permet entre autre de traduire en POJO un fichier fournit dans les différents format cité plus haut.
   Il génère un fichier de configuration qui permet aux utilisateurs d'écrire des règles de conversions.
   
   Il a été réaliser en Java 8; il utilise principalement des bibliothèques de traitements de fichiers Json (Jackson & Gson). 
   
    

#### Usage de l'application:

1 - Utilisation du fichier Json:

- format du fichier Json : 
considerons le fichier `test.json` dans le dossier `/files`, qui contient le format Json
```json
[ {
  "item" : "No. 9 Sprockets",
  "quantity" : 12,
  "unitPrice" : 1.23
}, {
  "item" : "Widget (10mm)",
  "quantity" : 4,
  "unitPrice" : 3.45
} ]
```
- Fichier de Configuration : 
A l'aide du fichier de configuration l'utilisateur peut choisir l'attribut source du fichier source et les mettre dans le fichier destination par exemple en prend la convertion du json to Csv l'utilisateur veut afficher sur son fichier cible le produit et la quantité du produit. 
```json
{
    "CSV2Json": [],
    "Json2CSV": {
        "item": "item",
        "quantity": "quantity",
        "unitPrice": "unitPrice + quantite"
    },
    "name": "FichierConf"
}
```
- JAVA code : Cette procédure permet de Traitee des fichier JSON sens de conversion JSON vers CSV .
Ce code utilise un fonction definit dans la méthode convertir dans la classe ConvertJsonToCSV qui permet la convertion du fichier JSON to CSV 
```java
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
```

- CSV output :
```csv
item,quantity,unitPrice
"No. 9 Sprockets",12,1.23
"Widget (10mm)",4,3.45

```
2 - Utilisation du fichier CSV:

- Format du fichier Csv :

considerons le fichier `test.csv` dans le dossier `/files`, qui contient le format Csv
```csv
item,quantity,unitPrice
"No. 9 Sprockets",12,1.23
"Widget (10mm)",4,3.45

```

- JAVA code : Cette procédure permet de Traitee des fichier CSV sens de conversion CSV vers JSON .
Ce code utilise un fonction definit dans la méthode convertir dans la classe ConvertCSVtoJson qui permet la convertion du fichier CSV to JSON 
```java
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
```



- JSON output :


```json
[ {
  "item" : "No. 9 Sprockets",
  "quantity" : 12,
  "unitPrice" : 1.23
}, {
  "item" : "Widget (10mm)",
  "quantity" : 4,
  "unitPrice" : 3.45
} ]
```



#### Licence
Ce projet est réaliser par EL KADI KAMAL et OUEDRAOGO Hamid-Abdoul Hakim.
Projet CProg - 2019
