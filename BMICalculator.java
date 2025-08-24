import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] personData = new double[10][2]; // [weight][height]
        
        // Input data for 10 persons
        for (int i = 0; i < 10; i++) {
            System.out.printf("Enter weight (kg) for person %d: ", i + 1);
            personData[i][0] = scanner.nextDouble();
            System.out.printf("Enter height (cm) for person %d: ", i + 1);
            personData[i][1] = scanner.nextDouble();
        }
        
        String[][] result = calculateAllBMI(personData);
        displayResults(result);
        
        scanner.close();
    }
    
    // Calculates BMI and status for all persons
    public static String[][] calculateAllBMI(double[][] personData) {
        String[][] result = new String[personData.length][4];
        
        for (int i = 0; i < personData.length; i++) {
            double weight = personData[i][0];
            double height = personData[i][1] / 100; // Convert cm to m
            double bmi = weight / (height * height);
            String status = determineBMIStatus(bmi);
            
            result[i][0] = String.format("%.1f cm", personData[i][1]);
            result[i][1] = String.format("%.1f kg", weight);
            result[i][2] = String.format("%.1f", bmi);
            result[i][3] = status;
        }
        
        return result;
    }
    
  
    // Determines BMI status category
    public static String determineBMIStatus(double bmi) {
        if (bmi <= 18.4) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 39.9) return "Overweight";
        return "Obese";
    }
    
    // Displays results in tabular format
    public static void displayResults(String[][] result) {
        System.out.println("\nBMI Results for Team Members:");
        System.out.println("+-----------+-----------+-------+-------------+");
        System.out.println("| Height    | Weight    | BMI   | Status      |");
        System.out.println("+-----------+-----------+-------+-------------+");
        
        for (String[] person : result) {
            System.out.printf("| %-9s | %-9s | %-5s | %-11s |\n", 
                person[0], person[1], person[2], person[3]);
        }
        
        System.out.println("+-----------+-----------+-------+-------------+");
    }
}
