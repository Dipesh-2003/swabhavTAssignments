package javaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageSalary {
	public static class Employee{
		String department;
		double salary;
		public Employee(String department, double salary) {
			super();
			this.department = department;
			this.salary = salary;
		}
	}
	public static void main(String[] args) {
		 List<Employee> staff = Arrays.asList(
		            new Employee("HR", 50000),
		            new Employee("IT", 70000),
		            new Employee("HR", 60000),
		            new Employee("IT", 80000)
		        );

		        Map<String, Double> avgSalaryByDept = staff.stream()
		            .collect(Collectors.groupingBy(
		                    e -> e.department,
		                    java.util.stream.Collectors.averagingDouble(e -> e.salary)
		                )
		            )
		            .entrySet().stream()
		            .collect(
		                java.util.stream.Collectors.toMap(
		                    e -> e.getKey(),
		                    e -> Math.round(e.getValue() * 100.0) / 100.0
		                )
		            );

		        System.out.println(avgSalaryByDept);
	}
}
