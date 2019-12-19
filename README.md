#### Outil de conversion

Conversion de JSON à CSV et vice Versa

Ce programme est un outil de conversion de fichier Json vers CSV et vice-Versa
 Il permet entre autre de traduire en POJO un fichier fournit dans les différents format cité plus haut.
   Il génère un fichier de configuration qui permet aux utilisateurs d'écrire des règles de conversions.
   
   Il a été réaliser en Java 8; il utilise principalement des bibliothèques de traitements de fichiers Json (Jackson & Gson). 
   
    

#### Usage de l'application:

1 - Utilisation du fichier Json:

- format du fichier Json:
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
- Fichier de Configuration
A l'aide du fichier de configuration l'utilisateur peut choisir l'attribut source du fichier source et les mettre dans le fichier destination par exemple en prend la convertion du json to Csv l'utilisateur veut afficher sur son fichier le produit et la quantité du produit. 
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
- JAVA code:
```java
/*
 * Parse a JSON String and convert it to CSV
 */
List<Map<String, String>> flatJson = JSONFlattener.parseJson(jsonString);
// Using the default separator ','
// If you want to use an other separator like ';' or '\t' use
// CSVWriter.getCSV(flatJSON, separator) method
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "files/sample_string.csv");
```

- CSV output:
```csv
item,quantity,unitPrice
"No. 9 Sprockets",12,1.23
"Widget (10mm)",4,3.45

```
2 - Using a JSON file:

- JSON file:

considering the file `simple.json` in `/files` directory, contains the following JSON
```json
[
    {
        "studentName": "Foo",
        "Age": "12",
        "subjects": [
            {
                "name": "English",
                "marks": "40"
            },
            {
                "name": "History",
                "marks": "50"
            }
        ]
    },
    {
        "studentName": "Bar",
        "Age": "12",
        "subjects": [
            {
                "name": "English",
                "marks": "40"
            },
            {
                "name": "History",
                "marks": "50"
            },
            {
                "name": "Science",
                "marks": "40"
            }
        ]
    },
    {
        "studentName": "Baz",
        "Age": "12",
        "subjects": []
    }
]
```

- JAVA code:
```java
/*
 *  Parse a JSON File and convert it to CSV
 */
// There is 2 methods to parse the JSON file
// 1- JSONFlattener#parseJson(File file):
//    This will use the default encoding UTF-8 to parse the given file.
// 2- JSONFlattener#parseJson(File file, String encoding):
//    This will parse the file using the specified character encoding.
flatJson = JSONFlattener.parseJson(new File("files/simple.json"), "UTF-8");
// Using ';' as separator
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, ";"), "files/sample_file.csv");
```
- CSV output (see [/files/sample_file.csv](https://github.com/Arkni/json-to-csv/blob/master/files/sample_file.csv) file):
```csv
Age;subjects[1].marks;subjects[1].name;subjects[2].marks;subjects[2].name;studentName;subjects[3].marks;subjects[3].name
12;40;English;50;History;Foo;;
12;40;English;50;History;Bar;40;Science
12;;;;;Baz;;
```

3 - Using a JSON returned from a URL:

- JAVA code:
```java
/*
 *  Parse JSON from URL and convert it to CSV
 */
// There is 2 methods to parse a JSON returned from a URI
// 1- JSONFlattener#parseJson(URI uri):
//    This will use the default encoding UTF-8 to parse the JSON returned from the given uri.
// 2- JSONFlattener#parseJson(URI uri, String encoding):
//    This will parse the JSON returned from the uri using the specified character encoding.
flatJson = JSONFlattener.parseJson(new URI("http://echo.jsontest.com/firstname/Brahim/lastName/Arkni"));
// Using '\t' as separator
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, "\t"), "files/sample_uri.csv");
```

- JSON:

for this example, I used the web service [echo.jsontest.com](http://echo.jsontest.com) to echo a JSON object like the following
```json
{
	"firstName": "Brahim",
	"lastName": "Arkni"
}
```

- CSV output (see [/files/sample_uri.csv](https://github.com/Arkni/json-to-csv/blob/master/files/sample_file.csv) file):
```csv
lastName	firstname
Arkni		Brahim
```

#### N.B:
- The column names would dynamically be generated based on the keys in the JSON object.
- The order of the JSON keys will not preserved during JSON conversion to CSV, See this [StackOverFlow question](http://stackoverflow.com/questions/4515676/keep-the-order-of-the-json-keys-during-json-conversion-to-csv) for more information.


The sample output files can be seen [here](https://github.com/Arkni/json-to-csv/blob/master/files).

#### Licence
Ce projet est réaliser par EL KADI KAMAL et OUEDRAOGO Hamid-Abdoul Hakim.
