package fr.uvsq.EtudiantUVSQ.Convertion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;

public class ConvertJsonToCSV {

    public static boolean convertir(String fichierIn, String fichierOut) {

        fichierIn = "/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.json";
        fichierOut = "." + File.separator + fichierOut + "CSV.csv";
        try {
            JsonNode jsonTree = new ObjectMapper().readTree(new File(fichierIn));
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
