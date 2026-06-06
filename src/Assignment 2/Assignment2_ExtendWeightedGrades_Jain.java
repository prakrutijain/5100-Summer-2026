
// Prakruti Jain
// Assignment 2: Extended Weighted Grades
// This program calculates a total weighted grade across multiple assignments
// and determines the letter grade using If-Then-Else logic.

import java.util.Scanner;

// ─────────────────────────────────────────────
// CLASS 1: WeightedGrade
// Holds data for all assignments and does the math.
// main() is NOT here — it stays in the other class.
// ─────────────────────────────────────────────
class WeightedGrade {

    // Arrays to store data for each assignment
    // Think of each as a row of boxes, one box per assignment
    private double[] pointTotals;
    private double[] earnedPoints;
    private double[] weights;

    private int numAssignments;       // how many assignments
    private double totalWeightedGrade; // final calculated result

    // Constructor — sets up the arrays based on how many assignments
    // 'n' tells us how big to make each array
    public WeightedGrade(int n) {
        this.numAssignments = n;
        this.pointTotals  = new double[n]; // create n empty boxes
        this.earnedPoints = new double[n];
        this.weights      = new double[n];
    }

    // ── SETTERS ──
    // These let main() fill in each box of the arrays
    // index = which assignment (0, 1, 2 ...)

    public void setPointTotal(int index, double val) {
        this.pointTotals[index] = val;
    }

    public void setEarnedPoints(int index, double val) {
        this.earnedPoints[index] = val;
    }

    public void setWeight(int index, double val) {
        this.weights[index] = val;
    }

    // ── GETTER ──
    public double getTotalWeightedGrade() {
        return totalWeightedGrade;
    }

    // ── CHECK WEIGHTS ──
    // Adds up all weights and returns the sum.
    // main() uses this to check if they add up to 100.
    public double sumOfWeights() {
        double sum = 0;
        for (int i = 0; i < numAssignments; i++) {
            sum += weights[i]; // += means sum = sum + weights[i]
        }
        return sum;
    }

    // ── CALCULATE TOTAL WEIGHTED GRADE ──
    // For each assignment: (earned / total) * weight
    // Add them all up for the final grade
    public void calculateTotalWeightedGrade() {
        totalWeightedGrade = 0; // start at zero
        for (int i = 0; i < numAssignments; i++) {
            totalWeightedGrade += (earnedPoints[i] / pointTotals[i]) * weights[i];
        }
    }

    // ── LETTER GRADE ──
    // Uses If-Then-Else to convert number to letter
    // Returns the letter as a String
    public String getLetterGrade() {
        if (totalWeightedGrade >= 90) {
            return "A";
        } else if (totalWeightedGrade >= 80) {
            return "B";
        } else if (totalWeightedGrade >= 70) {
            return "C";
        } else if (totalWeightedGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

// ─────────────────────────────────────────────
// CLASS 2: Assignment2_ExtendWeightedGrades_Jain
// Contains main() — talks to the user.
// ─────────────────────────────────────────────
public class Assignment2_ExtendWeightedGrades_Jain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ── STEP 1: Ask how many assignments ──
        System.out.print("Number of assignments: ");
        int n = scanner.nextInt();

        // Create the WeightedGrade object sized for n assignments
        WeightedGrade grade = new WeightedGrade(n);

        // ── STEP 2: Read all point totals ──
        System.out.print("Total points (enter " + n + " values): ");
        for (int i = 0; i < n; i++) {
            grade.setPointTotal(i, scanner.nextDouble());
        }

        // ── STEP 3: Read all earned points ──
        System.out.print("Earned points (enter " + n + " values): ");
        for (int i = 0; i < n; i++) {
            grade.setEarnedPoints(i, scanner.nextDouble());
        }

        // ── STEP 4: Read weights — re-ask if they don't sum to 100 ──
        // This is a loop that keeps running UNTIL weights are valid
        while (true) {
            System.out.print("Weights in % (enter " + n + " values, must sum to 100): ");
            for (int i = 0; i < n; i++) {
                grade.setWeight(i, scanner.nextDouble());
            }

            // Check if weights add up to 100
            // We use Math.abs() to handle tiny decimal rounding issues
            // e.g. 50 + 30 + 20 = 100.0000001 sometimes in computers
            if (Math.abs(grade.sumOfWeights() - 100) < 0.001) {
                break; // weights are valid, exit the loop
            } else {
                System.out.println("Weights do not sum to 100. Please try again.");
            }
        }

        // ── STEP 5: Calculate ──
        grade.calculateTotalWeightedGrade();

        // ── STEP 6: Output results ──
        System.out.println("\nTOTAL weighted grade: " + grade.getTotalWeightedGrade());
        System.out.println("Letter grade: " + grade.getLetterGrade());

        scanner.close();
    }
}
