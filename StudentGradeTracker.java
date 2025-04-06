import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        double grade;

        System.out.println("Enter student grades (type -1 to finish):");

        // Collecting grades
        while (true) {
            System.out.print("Enter grade: ");
            grade = scanner.nextDouble();

            if (grade == -1) {
                break;
            }

            if (grade < 0 || grade > 100) {
                System.out.println("Please enter a valid grade between 0 and 100.");
                continue;
            }

            grades.add(grade);
        }

        // Check if any grades were entered
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
            return;
        }

        // Calculating average, highest, and lowest
        double sum = 0;
        double highest = grades.get(0);
        double lowest = grades.get(0);

        for (double g : grades) {
            sum += g;
            if (g > highest) highest = g;
            if (g < lowest) lowest = g;
        }

        double average = sum / grades.size();

        // Display results
        System.out.println("\nGrade Summary:");
        System.out.println("Total students: " + grades.size());
        System.out.println("Average grade: " + average);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
    }
}