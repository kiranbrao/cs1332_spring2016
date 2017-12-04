import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Your implementation of various sorting algorithms.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            boolean switched = false;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                    T tempVal = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tempVal;
                    switched = true;
                }
            }
            if (!switched) {
                i = 0;
            }
        }
    }
    
    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        for (int i = 1; i < arr.length; i++) {
            T currentVal = arr[i];
            int j = i - 1;
            while ((j >= 0) && (comparator.compare(arr[j], currentVal) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = currentVal;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        for (int i = 0; i < (arr.length - 1); i++) {
            int min = i;
            for (int j = (i + 1); j < arr.length; j++) {
                if (comparator.compare(arr[min], arr[j]) > 0) {
                    min = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     * 
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if ((arr == null) || (comparator == null) || (rand == null)) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        quickSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Recursively implements quick sort by calling itself on either side
     * of a randomly chosen pivot
     *
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start the first index of the array being sorted
     * @param end the last index of the array being sorted
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int start, int end) {
        int pivotIndex = rand.nextInt((end - start) + 1);
        pivotIndex = start + pivotIndex;
        T pivot = arr[pivotIndex];
        T temp1 = arr[start];
        arr[start] = pivot;
        arr[pivotIndex] = temp1;
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (comparator.compare(arr[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(arr[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        T temp2 = arr[j];
        arr[j] = pivot;
        arr[start] = temp2;
        if ((start < j) && ((j - start) > 1)) {
            quickSort(arr, comparator, rand, start, j);
        }
        if ((i < end) && ((end - i) > 1)) {
            quickSort(arr, comparator, rand, i, end);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        mergeSort(arr, comparator, 0, arr.length - 1);
    }

    /**
     * Recursively implements quick sort by calling itself on either side
     * of a randomly chosen pivot
     *
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param lowIndex the lower bound of the array being sorted
     * @param highIndex the upper bound of the array being sorted
     */
    private static <T> void mergeSort(T[] arr, Comparator<T> comparator,
                                      int lowIndex, int highIndex) {
        if (highIndex > lowIndex) {
            int midIndex = lowIndex + ((highIndex - lowIndex) / 2);
            mergeSort(arr, comparator, lowIndex, midIndex);
            mergeSort(arr, comparator, midIndex + 1, highIndex);
            T[] tempArr = (T[]) new Object[arr.length];
            for (int i = lowIndex; i <= highIndex; i++) {
                tempArr[i] = arr[i];
            }
            int a = lowIndex;
            int b = midIndex + 1;
            int c = lowIndex;
            while ((a <= midIndex) && (b <= highIndex)) {
                if (comparator.compare(tempArr[a], tempArr[b]) < 0) {
                    arr[c] = tempArr[a];
                    a++;
                } else {
                    arr[c] = tempArr[b];
                    b++;
                }
                c++;
            }
            while (a <= midIndex) {
                arr[c] = tempArr[a];
                a++;
                c++;
            }
            while (b <= highIndex) {
                arr[c] = tempArr[b];
                b++;
                c++;
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        LinkedList<Integer>[] bucketList = new LinkedList[19];
        int multFactor = 1;
        boolean sorted = false;
        for (int i = 0; i < 19; i++) {
            bucketList[i] = new LinkedList<Integer>();
        }
        while (!sorted) {
            sorted = true;
            for (int val : arr) {
                int bucketInd;
                if (val < 0) {
                    bucketInd = ((val * -1) / multFactor) % 10;
                } else {
                    bucketInd = 9 + ((val / multFactor) % 10);
                }
                if (bucketInd != 9) {
                    sorted = false;
                }
                bucketList[bucketInd].add(val);
            }
            multFactor *= 10;
            int i = 0;
            for (LinkedList<Integer> currentList: bucketList) {
                while (!(currentList.isEmpty())) {
                    arr[i] = currentList.removeFirst();
                    i++;
                }
            }
        }
        return arr;
    }
    
    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        int currentMax = arr[0];
        for (int current : arr) {
            if (current > currentMax) {
                currentMax = current;
            }
        }
        int maxDigits = numInts(currentMax);
        List<Integer> sortedList = msdRadixSort(arr, maxDigits);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedList.get(i);
        }
        return arr;
    }

    /**
     * Recursively implements most significant digit radix sort
     *
     *
     * @param arr the array that must be sorted after the method runs
     * @param maxDigits the length in digits of the highest or lowest number
     * @return  A list of sorted elementes of the original array
     */
    private static List<Integer> msdRadixSort(int[] arr, int maxDigits) {
        //System.out.println(arr.length);
        LinkedList<Integer>[] bucketList = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            bucketList[i] = new LinkedList<Integer>();
        }
        int multFactor = pow(10, maxDigits - 1);
        for (int val : arr) {
            int bucketInd;
            if (val < 0) {
                bucketInd = ((val * -1) / multFactor) % 10;
            } else {
                bucketInd = 9 + ((val / multFactor) % 10);
            }
            bucketList[bucketInd].add(val);
        }
        List<Integer> tempAL = new ArrayList<>();
        for (LinkedList<Integer> currentList: bucketList) {
            System.out.println(currentList.size());
            if (!currentList.isEmpty()) {
                List<Integer> tempArr = new ArrayList<>();
                if (currentList.size() > 1) {
                    int[] currentArr = new int[currentList.size()];
                    for (int k = 0; k < currentList.size(); k++) {
                        currentArr[k] = currentList.get(k);
                        //System.out.println(currentArr[k]);
                    }
                    //System.out.println(maxDigits);
                    tempArr = msdRadixSort(currentArr, maxDigits - 1);
                    //System.out.println(tempArr.size());
                    //System.out.println(maxDigits);
                }
                if (tempArr.isEmpty()) {
                    tempAL.addAll(tempArr);
                } else if (currentList.size() == 1) {
                    tempAL.add(currentList.getFirst());
                }
            }
        }
        return tempAL;
    }

    /**
     * Determines the number of digits in an inputted int. The conditional
     * values of this method are capable of representing up to the highest
     * value int capable of being represented by Java
     *
     * @param number the number whose length is being determined
     * @return  an int representing number of digits (or length)
     * of the inputted int
     */
    private static int numInts(int number) {
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                if (number < 100000000) {
                    return 8;
                } else {
                    if (number < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
