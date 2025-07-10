package javaTest;

import java.util.Arrays;
import java.util.List;

public class DistinctWordCount {
	public static void main(String[] args) {
		 List<String> words = Arrays.asList("Dipesh", "dipesh", "JAVA", "java");
	        List<String> distinctWords = words.stream().map(String::toLowerCase).distinct().toList();
	        System.out.println(distinctWords);
	        
	        int count = 0;
	        for (int i = 0; i < words.size(); i++) {
	            String word1 = words.get(i).toLowerCase();
	            for (int j = i + 1; j < words.size(); j++) {
	            String word2 = words.get(j).toLowerCase();
	            if (word1.equals(word2)) {
	                count++;
	                break;
	            }
	            }
	        }
	        System.out.println("Count of distinct words): " + count);
	}
}
