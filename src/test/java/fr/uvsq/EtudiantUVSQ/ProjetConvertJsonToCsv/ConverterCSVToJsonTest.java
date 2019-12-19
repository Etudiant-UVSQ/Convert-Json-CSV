package fr.uvsq.EtudiantUVSQ.ProjetConvertJsonToCsv;

import org.junit.*;

import fr.uvsq.EtudiantUVSQ.Convertion.ConvertCSVToJson;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;


public class ConverterCSVToJsonTest {
    
    private String fichierInCsv;
    private String fichierOutJson;
    private String reperDest;


    private static String readFile(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String newline = System.getProperty("line.separator");
        String out = "";

        try {
            while(scanner.hasNextLine()) {
                out += scanner.nextLine() + newline;
            }
            return out;
        } finally {
            scanner.close();
        }
    }

    @Before
    public void setUp() {
        try {
        	fichierInCsv = readFile("/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.json");
        	fichierOutJson = readFile("/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.csv");
        } catch(IOException e) {}
    }

    @Test
    public void testConvertCSVtoJSON() {
        assertEquals(ConvertCSVToJson.convertir(fichierInCsv, fichierOutJson, reperDest),fichierOutJson);
    }

    
}
