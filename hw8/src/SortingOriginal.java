import java.util.*;

public class SortingOriginal {

    /**
     * Swap two elements in a given array
     *
     * @param <T> data type to swap
     * @param arr the given array
     * @param index1 index for the first element
     * @param index2 index for the second element
     */
    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

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
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubblesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null");
        }
        // At each round, find the maximum one, shift it to the right where sorted
        // continue on the rest element
        // keep doing so until all are sorted, meaning that
        // 1. only
        // 2. no swap is needed

        int numOfRestElement = arr.length;
        boolean needSwap = true;

        while (numOfRestElement > 1 && needSwap) {
            needSwap = false;

            // find the maximum one, shift it to the right
            for (int j = 0 ; j <= numOfRestElement - 2; j++) {
                if (comparator.compare(arr[j], arr[j+1]) > 0) {
                    swap(arr, j, j + 1);
                    needSwap = true;
                }
            }

            numOfRestElement--;
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
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null");
        }

        for (int i = 1; i < arr.length; i++) {
            int position = i;
            //FIXME unlike wiki, I do more swap, wiki just do one swap
            //FIXME if it is OK?
            while (position > 0 && comparator.compare(arr[position - 1], arr[position]) > 0) {
                swap(arr, position - 1, position);
                position--;
            }
        }
    }

    /**
     * Implement shell sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log(n))
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void shellsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null");
        }

        //gap set to n/2
        int gap = arr.length / 2;
        while (gap > 0) {
            //i = 0, i + gap -> i < length
            //i = 1
            //i = 2
            //... i = gap - 1
            for (int i = 0; i < gap; i++) {

                for (int j = i + gap; j < arr.length; j+=gap) {
                    int position = j;
                    while (position > i  && comparator.compare(arr[position - gap], arr[position]) > 0) {
                        swap(arr, position - gap, position);
                        position-=gap;
                    }
                }
            }

            gap = gap / 2;
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
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quicksort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Input is null");
        }
        myQuicksort(arr, 0, arr.length - 1, comparator, rand);


    }

    /**
     * Quicksort helper method for sorting elements within a given array
     *
     * @param <T> data type to swap
     * @param arr the given array
     * @param low the lowest bound of the array elements to be sorted
     * @param high the highest bound of the array elements to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    private static <T> void myQuicksort(T[] arr, int low, int high, Comparator<T> comparator,
                                        Random rand) {


        if (low < high) {
            int pivotIndex = low + rand.nextInt(high - low);
            T pivot = arr[pivotIndex];
            swap(arr, pivotIndex, high);

            int start = low;
            int end = high;



            while (low < high) {
                while (low < high && comparator.compare(arr[low], pivot) <= 0) {
                    low++;
                }

                if (low < high) {
                    arr[high] = arr[low];
                    high--;
                }


                while (low < high && comparator.compare(arr[high], pivot) >= 0) {
                    high--;
                }

                if (low < high) {
                    arr[low] = arr[high];
                    low++;
                }
            }

            arr[high] = pivot;

            myQuicksort(arr, start, high - 1, comparator, rand);
            myQuicksort(arr, high + 1, end, comparator, rand);


        }
    }

// TODO already passed
//    if (low < high) {
//        int start = low;
//        int end = high;
//
//        int pivotIndex = low + rand.nextInt(high - low);
//        T pivot = arr[pivotIndex];
//
//        int storeIndex = pivotIndex;
//
//        while (start < end) {
//            while (comparator.compare(arr[start], pivot) <= 0 && start < storeIndex) {
//                start++;
//            }
//
//            arr[storeIndex] = arr[start];
//            storeIndex = start;
//
//            while (comparator.compare(arr[end], pivot) >= 0 && end > storeIndex) {
//                end--;
//            }
//
//            arr[storeIndex] = arr[end];
//            storeIndex = end;
//        }
//
//        arr[storeIndex] = pivot;

    //TODO already passed
//if (low < high) {

//        int pivotIndex = low + rand.nextInt(high - low);
//        T pivot = arr[pivotIndex];
//
//        //first swap pivot value to the high
//        swap(arr, pivotIndex, high);
//
//        //ready to store it at low, later should swap storeIndex with high
//        //the storeIndex currently store sth larger than pivot
//        int storeIndex = low;
//
//        for (int i = low; i < high; i++) {
//        if (comparator.compare(arr[i], pivot) < 0) {
//        swap(arr, storeIndex, i);
//        storeIndex++;
//        }
//        }
//
//        swap(arr, high, storeIndex);
//
//        myQuicksort(arr, low, storeIndex - 1, comparator, rand);
//
//        myQuicksort(arr, storeIndex + 1, high, comparator, rand);


    private static <T> void myQuicksort2 (T[] arr, int low, int high, Comparator<T> comparator,
                                          Random rand) {
        if (low < high) {
            //need to do partition
            //first find the pivot

            int pivotIndex = low + rand.nextInt(high - low);


            //swap pivot to the right
            swap(arr, pivotIndex, high);
            //now store pivot at index of high now

            pivotIndex = partition(arr, low, high, comparator);

            myQuicksort2(arr, low, pivotIndex - 1, comparator, rand);
            myQuicksort2(arr, pivotIndex + 1, high, comparator, rand);
        }

    }

    private static <T> int partition(T[]arr, int low, int high, Comparator<T> comparator) {
        //Now store pivot at high
        T pivot = arr[high];
        int storeIndex = high;

        while (low < high) {
            //check if all the left smaller than the pivot
            while (comparator.compare(arr[low], pivot) <= 0 && low < high) {
                low++;
            }

            if (high < low) {
                arr[high] = arr[low];
                // now store pivot at low
                storeIndex = low;
                high--;

            }

            //check if all the right larger than the pivot
            while (comparator.compare(arr[high], pivot) >= 0 && low < high) {
                high--;
            }
            if (low < high) {
                arr[low] = arr[high];
                // now store pivot at high
                storeIndex = high;
                low++;
            }
        }

        arr[storeIndex] = pivot;
        return storeIndex;
    }


//        if (low < high) {
//            int pivotIndex = low + rand.nextInt(high - low);
//            T pivot = arr[pivotIndex];
//
//            swap(arr, pivotIndex, high);
//            int storeIndex = low;
//
//            for (int i = low; i < high; i++) {
//                if (comparator.compare(arr[i], pivot) < 0) {
//                    swap(arr, storeIndex, i);
//                    storeIndex++;
//                }
//            }
//
//            swap(arr, high, storeIndex);



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
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int n = arr.length;
        T[] tempArr = (T[]) new Object[n];
        myMergesort(arr, tempArr, 0, n - 1, comparator);

    }

    private static <T> void myMergesort(T[] arr, T[] tempArr, int beg, int end, Comparator<T> comparator) {
        if (beg < end) {
            int mid = (beg + end) / 2;
            myMergesort(arr, tempArr, beg, mid, comparator);
            myMergesort(arr, tempArr, mid + 1, end, comparator);
            combine(arr, tempArr, beg, mid, end, comparator);
        }
    }

    /**
     * Combine two parts of an given array together in order
     *
     * @param <T> data type to swap
     * @param arr the given array
     * @param beg the start index of the the first part
     * @param mid the (end index + 1) of the first part as well as 
     *            the (start index - 1) of the second part
     * @param end the end index of the second part
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void combine(T[] arr, T[] tempArr, int beg, int mid, int end, Comparator<T> comparator) {
        for (int i = beg; i <= end; i++) {
            tempArr[i] = arr[i];
        }

        int i = beg;
        int j = mid + 1;
        int k = beg;

        while (i <= mid && j <= end) {
            if (comparator.compare(tempArr[i], tempArr[j]) > 0) {
                arr[k] = tempArr[j];
                j++;
            } else {
                arr[k] = tempArr[i];
                i++;
            }
            k++;
        }

        while (i <= mid) {
            arr[k] = tempArr[i];
            i++;
            k++;
        }
    }


    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings.
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
     * sorting as they were before sorting.
     *
     * You may use an ArrayList or LinkedList if you wish,
     * but it may only be used inside radixsort and any radix sort helpers
     * Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixsort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        List<Integer>[] bucket = new ArrayList[10];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        //sort the input arr into the bucket created


        //create a boolean value to check if getMaxLength k
        boolean getMaxLength = false;

        //start from least significant digit
        int digit = 1;


        while (!getMaxLength) {
            getMaxLength = true;
            for (int i : arr) {
                int currentDigitValue  = (Math.abs(i) / digit) % 10;
                bucket[currentDigitValue].add(i);

                if (!(Math.abs(i) / digit <10)) {
                    getMaxLength = false;
                }
            }

            //update original arr with sorted data in the bucket
            int index = 0;
            for (int i = 0; i < 10; i++) {
                if (bucket[i] != null) {
                    for (Integer e : bucket[i]) {
                        arr[index] = e;
                        index++;
                    }
                    bucket[i].clear();
                }
            }

            digit*=10;
        }

        List<Integer> negativeNum = new ArrayList<>();
        List<Integer> nonNegativeNum = new ArrayList<>();

        for(int i : arr) {
            if(i < 0) {
                negativeNum.add(i);
            } else {
                nonNegativeNum.add(i);
            }
        }

        for (int i = 0; i < negativeNum.size(); i++) {
            arr[i] = negativeNum.get(negativeNum.size() - 1 - i);
        }

        int nextIndex = negativeNum.size();
        for (int i = 0; i < nonNegativeNum.size(); i++) {
            arr[nextIndex + i] = nonNegativeNum.get(i);
        }

        return arr;

    }
}
