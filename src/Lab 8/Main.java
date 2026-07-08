
// Prakruti Jain

public class Main {
    public static void main(String[] args) {

        // --- Test Trapping Rain Water ---
        TrappingRainWater trw = new TrappingRainWater();

        // normal case from example
        System.out.println("Rain Water normal: " +
            trw.trapRainWater(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        // Expected: 6

        // no water trapped - already sorted
        System.out.println("Rain Water sorted: " +
            trw.trapRainWater(new int[]{1,2,3,4,5}));
        // Expected: 0

        // empty array
        System.out.println("Rain Water empty: " +
            trw.trapRainWater(new int[]{}));
        // Expected: 0

        // dynamic test using a loop - flat bars, no water
        System.out.println("\nRain Water dynamic test (all same height):");
        int[] flatBars = new int[5];
        for (int i = 0; i < 5; i++) {
            flatBars[i] = 3;
        }
        System.out.println("All height 3: " + trw.trapRainWater(flatBars));
        // Expected: 0

        // --- Test Largest Rectangle in Histogram ---
        LargestRectangleInHistogram lrh = new LargestRectangleInHistogram();

        // normal case from example
        System.out.println("\nLargest Rectangle normal: " +
            lrh.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        // Expected: 10

        // all same height
        System.out.println("Largest Rectangle same height: " +
            lrh.largestRectangleArea(new int[]{3,3,3,3}));
        // Expected: 12

        // single bar
        System.out.println("Largest Rectangle single bar: " +
            lrh.largestRectangleArea(new int[]{5}));
        // Expected: 5

        // dynamic test using a loop - increasing heights
        System.out.println("\nLargest Rectangle dynamic test:");
        int[] increasing = new int[5];
        for (int i = 0; i < 5; i++) {
            increasing[i] = i + 1;
        }
        System.out.println("Heights 1-5: " +
            lrh.largestRectangleArea(increasing));
        // Expected: 9
    }
}

// ---------- TrappingRainWater ----------
class TrappingRainWater {
    public int trapRainWater(int[] height) {

        // handle empty array
        if (height.length == 0) return 0;

        int totalWater = 0;

        // for each bar, find max height on left and right
        for (int i = 0; i < height.length; i++) {

            // find tallest bar to the left
            int leftMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // find tallest bar to the right
            int rightMax = 0;
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // water at this bar = min of two sides minus bar height
            totalWater += Math.min(leftMax, rightMax) - height[i];
        }

        return totalWater;
    }
}

// ---------- LargestRectangleInHistogram ----------
class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {

        int maxArea = 0;

        // check every possible rectangle
        for (int i = 0; i < heights.length; i++) {

            // find minimum height from i to j
            int minHeight = heights[i];

            for (int j = i; j < heights.length; j++) {

                // update minimum height as we expand right
                minHeight = Math.min(minHeight, heights[j]);

                // area = min height * width
                int width = j - i + 1;
                int area = minHeight * width;

                // update max area if this is bigger
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}

// Time Complexity:
// trapRainWater: O(n²) - n is the number of bars in the height array
//   I use two inner loops for each bar to find leftMax and rightMax
// largestRectangleArea: O(n²) - n is the number of bars in heights array
//   I use two nested loops to check every possible rectangle

// Space Complexity:
// trapRainWater: O(1) - only a few extra variables, no new data structures
// largestRectangleArea: O(1) - just a few variables to track min height and max area