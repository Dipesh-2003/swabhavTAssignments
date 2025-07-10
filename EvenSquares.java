package javaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenSquares {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenSquares = nums.stream().filter(n -> n % 2 == 0).map(n -> n * n).collect(Collectors.toList());
        System.out.println("Squares of even numbers: " + evenSquares);
	}
}
