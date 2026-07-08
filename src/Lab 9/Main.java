// Prakruti Jain

public class Main {
    public static void main(String[] args) {

        ContainerWithMostWater solution = new ContainerWithMostWater();

        // --- provided test cases ---
        int[] heights1 = {1,8,6,2,5,4,8,3,7};
        int[] heights2 = {1,1};
        int[] heights3 = {4,3,2,1,4};

        System.out.println("heights1 brute:   " + solution.maxAreaBruteForce(heights1)); // 49
        System.out.println("heights1 optimal: " + solution.maxAreaOptimal(heights1));    // 49
        System.out.println("heights2 brute:   " + solution.maxAreaBruteForce(heights2)); // 1
        System.out.println("heights2 optimal: " + solution.maxAreaOptimal(heights2));    // 1
        System.out.println("heights3 brute:   " + solution.maxAreaBruteForce(heights3)); // 16
        System.out.println("heights3 optimal: " + solution.maxAreaOptimal(heights3));    // 16

        // --- additional test case 1: all same height ---
        int[] heights4 = {5,5,5,5};
        System.out.println("\nheights4 brute:   " + solution.maxAreaBruteForce(heights4)); // 15
        System.out.println("heights4 optimal: " + solution.maxAreaOptimal(heights4));      // 15

        // --- additional test case 2: two bars only ---
        int[] heights5 = {3,9};
        System.out.println("\nheights5 brute:   " + solution.maxAreaBruteForce(heights5)); // 3
        System.out.println("heights5 optimal: " + solution.maxAreaOptimal(heights5));      // 3

        // --- additional test case 3: dynamic test using a loop ---
        System.out.println("\nDynamic test - increasing heights:");
        int[] heights6 = new int[5];
        for (int i = 0; i < 5; i++) {
            heights6[i] = i + 1; // heights: 1,2,3,4,5
        }
        System.out.println("heights6 brute:   " + solution.maxAreaBruteForce(heights6)); // 6
        System.out.println("heights6 optimal: " + solution.maxAreaOptimal(heights6));    // 6
    }
}

// ---------- ContainerWithMostWater ----------
class ContainerWithMostWater {

    // Brute Force Solution - check every pair of lines
    public int maxAreaBruteForce(int[] height) {

        int maxArea = 0;

        // try every possible pair (i, j)
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {

                // width is the distance between the two lines
                int width = j - i;

                // height is limited by the shorter line
                int h = Math.min(height[i], height[j]);

                // calculate area and update max
                int area = width * h;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    // Optimal Solution - two pointers starting from both ends
    public int maxAreaOptimal(int[] height) {

        int maxArea = 0;

        // start from both ends
        int left = 0;
        int right = height.length - 1;

        while (left < right) {

            // width is the distance between the two pointers
            int width = right - left;

            // height is limited by the shorter line
            int h = Math.min(height[left], height[right]);

            // calculate area and update max
            int area = width * h;
            maxArea = Math.max(maxArea, area);

            // move the pointer with the smaller height inward
            // because moving the taller one can only decrease width
            // without any chance of increasing height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

// Time Complexity:
// maxAreaBruteForce: O(n²) - n is the number of lines in height array
//   I use two nested loops to check every possible pair
// maxAreaOptimal: O(n) - n is the number of lines in height array
//   I use two pointers that move toward each other, each moving at most n times

// Space Complexity:
// maxAreaBruteForce: O(1) - only a few variables, no extra data structures
// maxAreaOptimal: O(1) - only left, right pointers and a few variables