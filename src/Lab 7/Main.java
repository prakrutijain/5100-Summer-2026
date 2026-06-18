
// Prakruti Jain

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // --- Test TwoSum ---
        TwoSum ts = new TwoSum();

        // normal case
        int[] nums1 = {2, 7, 11, 15};
        System.out.println("TwoSum normal: " + Arrays.toString(ts.findTwoSum(nums1, 9)));
        // Expected: [0, 1]

        // no pair found
        int[] nums2 = {1, 2, 3};
        System.out.println("TwoSum no pair: " + Arrays.toString(ts.findTwoSum(nums2, 10)));
        // Expected: [-1, -1]

        // dynamic test using a loop
        System.out.println("\nTwoSum dynamic test:");
        int[] nums3 = {1, 2, 3, 4, 5};
        for (int target = 3; target <= 7; target++) {
            System.out.println("target " + target + ": " + Arrays.toString(ts.findTwoSum(nums3, target)));
        }

        // --- Test ThreeSum ---
        ThreeSum ths = new ThreeSum();

        // normal case
        int[] nums4 = {-1, 0, 1, 2, -1, -4};
        System.out.println("\nThreeSum normal: " + ths.findThreeSum(nums4));
        // Expected: [[-1, -1, 2], [-1, 0, 1]]

        // all zeros
        int[] nums5 = {0, 0, 0};
        System.out.println("ThreeSum all zeros: " + ths.findThreeSum(nums5));
        // Expected: [[0, 0, 0]]

        // no triplet found
        int[] nums6 = {1, 2, 3};
        System.out.println("ThreeSum no triplet: " + ths.findThreeSum(nums6));
        // Expected: []
    }
}

// ---------- TwoSum ----------
class TwoSum {
    public int[] findTwoSum(int[] nums, int target) {

        // check every pair using two loops
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        // no pair found
        return new int[]{-1, -1};
    }
}

// ---------- ThreeSum ----------
class ThreeSum {
    public List<List<Integer>> findThreeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // sort the array first
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // skip duplicates for first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // two pointers
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // found a triplet!
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // skip duplicates for second number
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // skip duplicates for third number
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;   // need bigger number
                } else {
                    right--;  // need smaller number
                }
            }
        }

        return result;
    }
}

// Time Complexity:
// TwoSum: O(n²) - n is the number of elements in nums
//   I use two nested loops to check every possible pair
// ThreeSum: O(n²) - n is the number of elements in nums
//   outer loop runs n times, two pointer loop runs n times inside

// Space Complexity:
// TwoSum: O(1) - I only use a fixed number of variables, no extra data structures
// ThreeSum: O(n) - n is the number of triplets stored in the result list