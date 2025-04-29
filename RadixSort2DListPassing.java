import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RadixSort2DListPassing {

    public static void radixSortAndDisplay(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        int maxVal = Arrays.stream(data).max().orElse(0);
        int numDigits = String.valueOf(maxVal).length();
        int n = data.length;

        // Initialize two 2D lists for array1 and array2 (buckets)
        List<List<Integer>> array1 = new ArrayList<>(10);
        List<List<Integer>> array2 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            array1.add(new ArrayList<>());
            array2.add(new ArrayList<>());
        }

        System.out.println("Initialization:");
        System.out.println("Array1 (Buckets1): " + array1);
        System.out.println("Array2 (Buckets2): " + array2);
        System.out.println("Input elements: " + Arrays.toString(data));
        System.out.println();

        int[] currentArray = Arrays.copyOf(data, n);
        List<List<Integer>> sourceBuckets = array1;
        List<List<Integer>> destinationBuckets = array2;

        for (int digitPlace = 0; digitPlace < numDigits; digitPlace++) {
            System.out.println("Iteration " + (digitPlace + 1) + " (Digit Place: " + digitPlace + ")");

            // Distribution from currentArray to sourceBuckets
            for (int num : currentArray) {
                int digit = (num / (int) Math.pow(10, digitPlace)) % 10;
                sourceBuckets.get(digit).add(num);
            }

            System.out.println("Array1 (Buckets1) after distribution: " + sourceBuckets);
            System.out.println("Array2 (Buckets2) before receiving: " + destinationBuckets);

            // Collection from sourceBuckets and prepare for next distribution
            int index = 0;
            List<Integer> collectedList = new ArrayList<>();
            for (List<Integer> bucket : sourceBuckets) {
                collectedList.addAll(bucket);
                bucket.clear(); // Clear for the next potential use as source
            }
            currentArray = collectedList.stream().mapToInt(i -> i).toArray();

            System.out.println("Array2 (Buckets2) will be populated in the next iteration.");
            System.out.println("Current Array after collection: " + Arrays.toString(currentArray));
            System.out.println();

            // Swap source and destination for the next pass
            List<List<Integer>> temp = sourceBuckets;
            sourceBuckets = destinationBuckets;
            destinationBuckets = temp;
        }

        System.out.println("Reorder:");
        System.out.println("Sorted list: " + Arrays.toString(currentArray));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter space-separated integers to sort:");
        String input = scanner.nextLine();
        String[] numStrs = input.split("\\s+");
        int[] numbers = new int[numStrs.length];

        try {
            for (int i = 0; i < numStrs.length; i++) {
                numbers[i] = Integer.parseInt(numStrs[i]);
            }
            radixSortAndDisplay(numbers);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
}