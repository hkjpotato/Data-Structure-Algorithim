/**
  * Class that provides a static selection sort method
  */
public class SelectionSort {

	/*
     * Sorts the supplied array of integers using the selection
     * sort algorithm.  The function should run in O(n^2)
     *
     * @param arr The integer array to sort
     * 
     */
	public static void selectionSort(Comparable[] arr){
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				int comp = arr[minIndex].compareTo(arr[j]);
				if (comp > 0) {
					minIndex = j;
				}
			}
			Comparable temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}
}