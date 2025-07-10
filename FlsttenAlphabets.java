package javaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlsttenAlphabets {
	public static void main(String[] args) {
		List<List<String>> nested = Arrays.asList(
	            Arrays.asList("apple", "", null, "banana"),
	            Arrays.asList("zebra", "cat", " ", "dog"),
	            Arrays.asList(null, "elephant", "bear", "")
	        );
	        List<String> result = nested.stream()
	            .flatMap(list -> list.stream())
	            .filter(s -> s != null && !s.trim().isEmpty())
	            .sorted()
	            .collect(Collectors.toList());
	        System.out.println("Flattened and alphabetised: " + result);
	}
}
