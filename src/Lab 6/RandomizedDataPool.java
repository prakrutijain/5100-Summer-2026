
// Prakruti Jain

import java.util.ArrayList;
import java.util.Random;

public class RandomizedDataPool {

    ArrayList<Integer> pool;        // stores ALL values including duplicates
    ArrayList<Integer> uniqueVals;  // stores only UNIQUE values
    Random random;

    public RandomizedDataPool() {
        pool = new ArrayList<>();
        uniqueVals = new ArrayList<>();
        random = new Random();
    }

    public boolean addItem(int val) {
        // check if val is new
        boolean isNew = !uniqueVals.contains(val);

        // if new, add to uniqueVals
        if (isNew) {
            uniqueVals.add(val);
        }

        // always add to pool (duplicates allowed)
        pool.add(val);

        return isNew;
    }

    public boolean deleteItem(int val) {
        // if val not in pool, return false
        if (!pool.contains(val)) {
            return false;
        }

        // remove one occurrence from pool
        pool.remove(Integer.valueOf(val));

        // if no more occurrences left, remove from uniqueVals too
        if (!pool.contains(val)) {
            uniqueVals.remove(Integer.valueOf(val));
        }

        return true;
    }

    public int pickRandom() {
        // pick a random index from pool
        int randomIndex = random.nextInt(pool.size());
        return pool.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedDataPool pool = new RandomizedDataPool();

        // add items
        System.out.println("addItem(1): " + pool.addItem(1)); // true
        System.out.println("addItem(1): " + pool.addItem(1)); // false - duplicate
        System.out.println("addItem(2): " + pool.addItem(2)); // true
        System.out.println("addItem(3): " + pool.addItem(3)); // true

        // pick random
        System.out.println("pickRandom: " + pool.pickRandom()); // 1, 1, 2, or 3

        // delete items
        System.out.println("deleteItem(1): " + pool.deleteItem(1)); // true
        System.out.println("deleteItem(1): " + pool.deleteItem(1)); // true - duplicate
        System.out.println("deleteItem(1): " + pool.deleteItem(1)); // false - not in pool

        // pick random after deletions
        System.out.println("pickRandom: " + pool.pickRandom()); // 2 or 3

        // edge case - empty pool after deleting everything
        pool.deleteItem(2);
        pool.deleteItem(3);
        System.out.println("deleteItem from empty: " + pool.deleteItem(5)); // false

        // dynamic test using a loop
        System.out.println("\nDynamic test - adding 1 to 5:");
        RandomizedDataPool pool2 = new RandomizedDataPool();
        for (int i = 1; i <= 5; i++) {
            System.out.println("addItem(" + i + "): " + pool2.addItem(i));
        }
        System.out.println("pickRandom: " + pool2.pickRandom());
    }
}

// Time Complexity: O(n) for addItem and deleteItem
// - addItem: O(n) - contains() scans uniqueVals to check if value is new
// - deleteItem: O(n) - contains() and remove() both scan the lists
// - pickRandom: O(1) - direct index access on pool

// Space Complexity: O(n) where n = total number of elements in the pool
// - pool stores all values including duplicates
// - uniqueVals stores only unique values, at most n entries

// Note: Test cases include both hardcoded edge cases and a dynamic loop test
// that adds values 1 to 5 using a for loop to demonstrate scalability