package fr.uvsq.EtudiantUVSQ.Convertion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;

public class ConvertJsonToCSV {

    /**
     * Cette methode permet de convertir
     * un fichier CSV en Json

     * @param fichierIn
     * @param fichierOut
     * @return true or false

     */
    public static boolean convertir(File fichierIn, String fichierOut) {

        //fichierIn = new File("/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.json");
        //fichierOut = "." + File.separator + fichierOut + "CSV.csv";
        try {
            JsonNode jsonTree = new ObjectMapper().readTree(fichierIn);
            CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
            JsonNode firstObject = jsonTree.elements().next();

            firstObject.fieldNames().forEachRemaining(fieldName -> {
                csvSchemaBuilder.addColumn(fieldName);
            });
            CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(new File(fichierOut), jsonTree);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur message : " + e.getMessage());
            return false;
        }
    }
}
