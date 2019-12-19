package fr.uvsq.EtudiantUVSQ.ProjetConvertJsonToCsv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq.EtudiantUVSQ.Convertion.ConvertCSVToJson;
import fr.uvsq.EtudiantUVSQ.Convertion.ConvertJsonToCSV;

public class ConvertJsonToCSVTest {
	private File fichierInJson;
	private String fichierOutCsv;

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
	        	fichierInJson = new File("/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.csv");
	        	fichierOutCsv = readFile("/root/IdeaProjects/ConvertJsonCSV/src/main/java/fr/uvsq/test.json");
	        } catch(IOException e) {}
	    }

	    @Test
	    public void testConvertJSONtoCSV() {
	    	assertEquals(ConvertJsonToCSV.convertir(fichierInJson, fichierOutCsv),fichierOutCsv);
	    }
}
