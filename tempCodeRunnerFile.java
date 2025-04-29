import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort{

    public static List<Integer> radixSortArray(List<Integer> initialNumbers) {
        if (initialNumbers == null || initialNumbers.isEmpty()) {
            return new ArrayList<>();
        }

        System.out.println("1. Initialize");
        System.out.println("Initial Numbers: " + initialNumbers);

        // Initialize two arrays of ArrayLists for array1 and array2 (buckets)
        ArrayList<Integer>[] array1 = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            array1[i] = new ArrayList<>();
        }
        ArrayList<Integer>[] array2 = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            array2[i] = new ArrayList<>();
        }

        System.out.println("Array1 (Initial Buckets): " + Arrays.toString(array1));
        System.out.println("Array2 (Initial Buckets): " + Arrays.toString(array2));

        int maxVal = 0;
        // Find the maximum value to determine the number of digits
        for (int num : initialNumbers) {
            maxVal = Math.max(maxVal, num);
        }
        int numDigits = String.valueOf(maxVal).length();

        // Initialize the currentlist with the initial numbers and set the first bucket array to array1
        List<Integer> currentList = new ArrayList<>(initialNumbers);
        ArrayList<Integer>[] buckets = array1; 

        System.out.println("\n2. Iteration");
        for (int digitPlace = 0; digitPlace < numDigits; digitPlace++) {
            System.out.println("\nPass " + (digitPlace + 1) + " (Digit Place: " + digitPlace + ")");
            System.out.println("Distributing from: " + currentList);

            // Determine which bucket array to use for this pass
            String bucketArrayName = (buckets == array1) ? "Array1" : "Array2";

            // Distribute numbers into buckets based on the current digit place
            for (int num : currentList) {
                int digit = (num / (int) Math.pow(10, digitPlace)) % 10;
                buckets[digit].add(num);
                System.out.printf("(%s)  Bucket %d -> %03d%n", bucketArrayName, digit, num);
            }

            // Collect numbers from buckets into the next list
            List<Integer> nextList = new ArrayList<>();
            for (ArrayList<Integer> bucket : buckets) {
                nextList.addAll(bucket);
            }
            System.out.println("Collected into: " + nextList);

            // Switch buckets for the next pass
            if (buckets == array1) {
                buckets = array2;
                // Clear array2 for the next distribution
                for (ArrayList<Integer> bucket : array2) {
                    bucket.clear();
                }
            } else {
                buckets = array1;
                // Clear array1 for the next distribution
                for (ArrayList<Integer> bucket : array1) {
                    bucket.clear();
                }
            }

            // Set currentList to the next list for the next pass
            currentList = nextList;
        }

        System.out.println("\n3. Reorder");
        System.out.println("Sorted list: " + currentList);
        return currentList;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(275, 87, 426, 61, 409, 170, 677, 503);
        List<Integer> sortedNumbers = radixSortArray(numbers);
    }
}