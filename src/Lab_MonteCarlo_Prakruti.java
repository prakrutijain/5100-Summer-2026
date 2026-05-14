
// Prakruti Jain

import java.util.Random;
import java.util.Scanner;

public class Lab_MonteCarlo_Prakruti {

    // Represents a single random point in the unit square (-1 to +1)
    static class Point {
        private final double x;
        private final double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // Returns true if this point falls inside the unit circle (radius = 1)
        boolean isInsideCircle() {
            return (x * x + y * y) <= 1.0;
        }
    }

    // Handles the simulation logic
    static class MonteCarloSimulator {
        private final int totalPoints;
        private final Random random;
        private int pointsInsideCircle;

        MonteCarloSimulator(int totalPoints) {
            this.totalPoints = totalPoints;
            this.pointsInsideCircle = 0;
            this.random = new Random();
        }

        // Generates a random number between -1 (inclusive) and +1 (exclusive)
        private double randomCoordinate() {
            return (random.nextDouble() * (1 - (-1))) + (-1);
        }

        void runSimulation() {
            for (int i = 0; i < totalPoints; i++) {
                double x = randomCoordinate();
                double y = randomCoordinate();
                Point p = new Point(x, y);
                if (p.isInsideCircle()) {
                    pointsInsideCircle++;
                }
            }
        }

        double estimatePi() {
            // Full circle: ratio of circle area (π) to square area (4) is π/4
            // So π = 4 * (points inside circle / total points)
            return 4.0 * pointsInsideCircle / totalPoints;
        }

        int getPointsInsideCircle() {
            return pointsInsideCircle;
        }

        int getTotalPoints() {
            return totalPoints;
        }
    }

    // Handles user input and output
    static class SimulationRunner {
        void run() {
            int n;

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter the number of random points to generate: ");
                n = scanner.nextInt();
            }

            MonteCarloSimulator sim = new MonteCarloSimulator(n);
            sim.runSimulation();

            System.out.println("\n--- Monte Carlo π Estimation ---");
            System.out.println("Total points generated : " + sim.getTotalPoints());
            System.out.println("Points inside circle   : " + sim.getPointsInsideCircle());
            System.out.printf("Estimated value of π   : %.6f%n", sim.estimatePi());
            System.out.printf("Actual value of π      : %.6f%n", Math.PI);
            System.out.printf("Error                  : %.6f%%%n",
                Math.abs(sim.estimatePi() - Math.PI) / Math.PI * 100);
        }
    }

    public static void main(String[] args) {
        new SimulationRunner().run();
    }
}