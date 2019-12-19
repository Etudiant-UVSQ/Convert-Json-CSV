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

	 */
	public static void convertir(String fichierIn,String fichierOut)  {

		try {
			CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			MappingIterator<OrderLine> orderLines = csvMapper.readerFor(OrderLine.class)
			  .with(orderLineSchema)
			  .readValues(new File(fichierIn));
			new ObjectMapper()
			  .configure(SerializationFeature.INDENT_OUTPUT, true)
			  .writeValue(new File(fichierOut), orderLines.readAll());
		} catch (Exception e) {
			System.out.println("Erreur message : " + e.getMessage());
		}
		
	}
}
