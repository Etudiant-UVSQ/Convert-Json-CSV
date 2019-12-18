package fr.uvsq.EtudiantUVSQ.Convertion;

import java.io.File;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConvertCSVToJson {

	public static void main(String[] args)  {
		try {
			CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			MappingIterator<OrderLine> orderLines = csvMapper.readerFor(OrderLine.class)
			  .with(orderLineSchema)
			  .readValues(new File("files/test.csv"));
			new ObjectMapper()
			  .configure(SerializationFeature.INDENT_OUTPUT, true)
			  .writeValue(new File("files/test1.json"), orderLines.readAll());
		} catch (Exception e) {
			System.out.println("Erreur message : " + e.getMessage());
		}
		
	}
}
