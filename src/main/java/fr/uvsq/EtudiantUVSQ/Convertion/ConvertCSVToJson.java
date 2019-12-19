package fr.uvsq.EtudiantUVSQ.Convertion;

import java.io.File;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConvertCSVToJson {

	/**
	 * Cette methode permet de convertir
	 * un fichier CSV en Json

	 * @param fichierIn
	 * @param fichierOut
	 * @param repDest repetoire de destination
     * @return true or false
	 */
	public static boolean convertir(String fichierIn,String fichierOut,String repDest){

		String fichierOutJson = repDest  + fichierOut + "Json.json";
		try {
			CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			File file=new File(fichierIn);
			CsvToPojo.pojofromCsv(file);

			MappingIterator<Class> orderLines = csvMapper.readerFor(Temp.class)
					.with(orderLineSchema)
					.readValues(new File(fichierIn));
			new ObjectMapper()
					.configure(SerializationFeature.INDENT_OUTPUT, true)
					.writeValue(new File(fichierOutJson), orderLines.readAll());
			System.out.println(orderLines.readAll());

			return true;
		} catch (Exception e) {
			System.out.println("Erreur message : " + e.getMessage());

			return false;
		}

	}
}
