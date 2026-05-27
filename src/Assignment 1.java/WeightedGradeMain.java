
import java.util.Scanner; // Import Scanner so we can read user input from the keyboard

class WeightedGrade {

    
    // We use 'double' instead of 'int' because grades often have decimals
    private double pointTotal;          // Max points the assignment is worth
    private double earnedPoints;        // Points the student actually earned
    private double assignmentPercentage; // What % of the final grade this assignment is worth
    private double totalWeightedGrade;  // Calculated result — % points earned in the class

    // ── SETTERS ──
    // Setters let outside code (like main) give values to private attributes
    // Think of them as "controlled doors" into the class

    public void setPointTotal(double pointTotal) {
        this.pointTotal = pointTotal; // 'this' refers to THIS object's attribute
    }

    public void setEarnedPoints(double earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public void setAssignmentPercentage(double assignmentPercentage) {
        this.assignmentPercentage = assignmentPercentage;
    }

    // ── GETTERS ──
    // Getters let outside code READ the private attributes (read-only access)

    public double getPointTotal() {
        return pointTotal;
    }

    public double getEarnedPoints() {
        return earnedPoints;
    }

    public double getAssignmentPercentage() {
        return assignmentPercentage;
    }

    public double getTotalWeightedGrade() {
        return totalWeightedGrade;
    }

    // ── CALCULATE METHOD ──
    // Formula: (earnedPoints / pointTotal) * (assignmentPercentage / 100) * 100
    // Which simplifies to: earnedPoints / pointTotal * assignmentPercentage
    public void calculateWeightedGrade() {
        // Divide earned by total to get the raw score ratio (e.g. 175/200 = 0.875)
        // Multiply by assignmentPercentage to scale it to the class weight
        // e.g. 0.875 * 35 = 30.625
        totalWeightedGrade = (earnedPoints / pointTotal) * assignmentPercentage;
    }
}

// CLASS 2: WeightedGradeMain
// This is where the program STARTS (via main method).
// It talks to the user, passes data to WeightedGrade, and prints the result.
// ─────────────────────────────────────────────
public class WeightedGradeMain {

    public static void main(String[] args) {

        // Create a Scanner to read what the user types
        Scanner scanner = new Scanner(System.in);

        // Create a WeightedGrade object — our "grade calculator"
        WeightedGrade grade = new WeightedGrade();

        // ── GET INPUT FROM USER ──
        System.out.println("=== Weighted Grade Calculator ===");

        System.out.print("Enter the total points for the assignment: ");
        double pointTotal = scanner.nextDouble(); // Read a decimal number
        grade.setPointTotal(pointTotal);          // Store it in our object

        System.out.print("Enter the points you earned: ");
        double earnedPoints = scanner.nextDouble();
        grade.setEarnedPoints(earnedPoints);

        System.out.print("Enter the assignment percentage (e.g. 35 for 35%): ");
        double assignmentPercentage = scanner.nextDouble();
        grade.setAssignmentPercentage(assignmentPercentage);

        // ── DO THE MATH ──
        grade.calculateWeightedGrade(); // Trigger the calculation inside WeightedGrade

        // ── DISPLAY RESULTS ──
        System.out.println("\n=== Results ===");
        System.out.println("Point Total:         " + grade.getPointTotal());
        System.out.println("Earned Points:       " + grade.getEarnedPoints());
        System.out.println("Assignment %:        " + grade.getAssignmentPercentage() + "%");
        System.out.println("Total Weighted Grade: " + grade.getTotalWeightedGrade());

        scanner.close();
    }
}