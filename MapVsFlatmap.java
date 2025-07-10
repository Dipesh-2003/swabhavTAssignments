package javaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapVsFlatmap {
	public static void main(String[] args) {
		List<String> sentences = Arrays.asList(
	           ( "Java is fun"),
	           ( "Streams are powerful"),
	            ("Fun with Java streams")
	        );

	        List<List<String>> mapped = sentences.stream().map(sentence -> Arrays.asList(sentence.split(" ")))
	        		.collect(Collectors.toList());
	        System.out.println("Mapped: " + mapped);

	        List<String> flatMapped = sentences.stream().flatMap(sentence -> Arrays.stream(sentence.split(" "))).distinct()
	            .sorted()
	            .collect(Collectors.toList());
	        System.out.println("FlatMapped: " + flatMapped);

	}
}
