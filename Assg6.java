package assg6;

import java.util.Scanner;
/**
 * 
 * @author geneivaocampo
 */

public class Assg6 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userResponse = "yes";

        // Loop to keep the program running until the user decides to stop
        while (true) {
            int size, sortingMethod;

            // Display sorting method choices
            System.out.println("Choose a sorting routine:");
            System.out.println("1. Insertion Sort");
            System.out.println("2. Shell Sort");
            System.out.println("3. MergeSort");
            System.out.println("4. QuickSort");
            sortingMethod = input.nextInt();

            // Input size of the array
            System.out.println("Enter the number of integers: ");
            size = input.nextInt();
            Integer[] array = new Integer[size];

            // Input integers into the array with validation
            boolean validInput = false;
            while (!validInput) {
                System.out.printf("Enter the %d integers: ", size);
                int count = 0;
                for (int i = 0; i < size; i++) {
                    if (input.hasNextInt()) {
                        array[i] = input.nextInt();
                        count++;
                    } else {
                        input.next(); // Clear invalid input
                        break;
                    }
                }
                if (count == size) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter exactly " + size + " integers.");
                    input.nextLine(); // Clear buffer
                }
            }

            // Sort the array based on user choice
            switch (sortingMethod) {
                case 1:
                    insertionSort(array);
                    break;
                case 2:
                    shellSort(array);
                    break;
                case 3:
                    mergeSort(array);
                    break;
                case 4:
                    quickSort(array);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Restart the loop
            }

            // Remove duplicates and print the result
            Integer[] uniqueArray = removeDuplicates(array);
            System.out.print("The resulting array is: ");
            for (int num : uniqueArray) {
                System.out.print(num + " ");
            }
            System.out.println();

            // Ask if the user wants to continue
            System.out.print("Do you want to sort another array? (yes/no): ");
            userResponse = input.next();
            if (!userResponse.equalsIgnoreCase("yes") && !userResponse.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to continue
            }
        }

        input.close();
        System.out.println("Program terminated.");
    }

    public static Integer[] removeDuplicates(Integer[] array) {
        int n = array.length;
        if (n == 0) return new Integer[0];

        Integer[] duplicates = new Integer[n]; // To store duplicates
        int duplicateCount = 0;

        int newLength = 1; // First element is always unique
        for (int i = 1; i < n; i++) {
            if (!array[i].equals(array[i - 1])) {
                array[newLength++] = array[i];
            } else {
                duplicates[duplicateCount++] = array[i];
            }
        }

        // Print duplicates removed
        if (duplicateCount > 0) {
            System.out.print("Duplicates removed: ");
            for (int i = 0; i < duplicateCount; i++) {
                System.out.print(duplicates[i]);
                if (i < duplicateCount - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("No duplicates found.");
        }

        Integer[] newArray = new Integer[newLength];
        System.arraycopy(array, 0, newArray, 0, newLength);
        return newArray;
    }

    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            int j = p;
            while (j > 0 && tmp.compareTo(a[j - 1]) < 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = tmp;

            // Show the array after each iteration
            System.out.print("After iteration " + p + ": ");
            printArray(a);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
        int n = a.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                AnyType tmp = a[i];
                int j = i;
                while (j >= gap && tmp.compareTo(a[j - gap]) < 0) {
                    a[j] = a[j - gap];
                    j -= gap;
                }
                a[j] = tmp;
            }

            // Show the array after each gap iteration
            System.out.print("After gap " + gap + ": ");
            printArray(a);

            gap = (gap == 2) ? 1 : (int) (gap / 2.2);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);

            // Show the array after merging
            System.out.print("After merging range (" + left + ", " + right + "): ");
            printArray(a);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
    System.out.println("Starting QuickSort:");
    quickSort(a, 0, a.length - 1);
}

private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int low, int high) {
    if (low < high) { // Ensure there is a range to partition
        int pivotIndex = partition(a, low, high);

        // Print the array after partitioning
        System.out.print("After partitioning with pivot " + a[pivotIndex] + " (range: " + low + " to " + high + "): ");
        printArray(a);

        // Recursive calls for left and right partitions
        quickSort(a, low, pivotIndex - 1);
        quickSort(a, pivotIndex + 1, high);
    }
}

private static <AnyType extends Comparable<? super AnyType>> int partition(AnyType[] a, int low, int high) {
    AnyType pivot = a[high]; // Choose the last element as the pivot
    int i = low - 1; // Pointer for the smaller element

    for (int j = low; j < high; j++) {
        if (a[j].compareTo(pivot) <= 0) {
            i++;
            swapReferences(a, i, j);
        }
    }

    swapReferences(a, i + 1, high); // Place pivot in the correct position
    return i + 1; // Return the pivot index
}

private static <AnyType> void swapReferences(AnyType[] a, int i, int j) {
    AnyType temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

public static <AnyType> void printArray(AnyType[] a) {
    for (AnyType item : a) {
        System.out.print(item + " ");
    }
    System.out.println();
}

}
