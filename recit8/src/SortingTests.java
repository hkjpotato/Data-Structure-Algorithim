import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class SortingTests {
	private int seed = 42;

	@Test(timeout = 200)
	public void testOneItem() {
		Comparable[] arr = CustomInt.toArray(5);
		SelectionSort.selectionSort(arr);
		assertTrue(arr.length == 1);
		assertEquals(arr[0], new CustomInt(5));
		assertTrue(CustomInt.totalComparisions((CustomInt[])arr) == 0);
	}

	@Test(timeout = 200)
	public void testAlreadySorted() {
		Comparable[] arr = CustomInt.toArray(1,2,3,4,5,6);
		SelectionSort.selectionSort(arr);
		assertTrue(CustomInt.totalComparisions((CustomInt[])arr) == 15);
		assertTrue(isSorted(arr));
	}

	@Test(timeout = 200)
	public void testEmpty() {
		SelectionSort.selectionSort(new CustomInt[0]);
	}

	@Test(timeout = 200)
	public void testReverse() {
		Comparable[] arr = CustomInt.toArray(10,9,8,7,6,5,4,3,2,1);
		SelectionSort.selectionSort(arr);
		assertTrue(CustomInt.totalComparisions((CustomInt[])arr) == 45);
		assertTrue(isSorted(arr));
	}

	@Test(timeout = 200)
	public void testSmall() {
		Comparable[] arr = CustomInt.toArray(712,36,69,93,14,843);
		SelectionSort.selectionSort(arr);
		assertTrue(CustomInt.totalComparisions((CustomInt[])arr) == 15);
		assertTrue(isSorted(arr));
	}

	@Test(timeout = 200)
	public void testGeneral() {
		int[] arr =  new int[50];
		Random r = new Random(seed);
		for (int i=0; i<arr.length; i++){
			arr[i] = r.nextInt();
		}
		Comparable[] arr1 = CustomInt.toArray(arr);
		SelectionSort.selectionSort(arr1);
		assertTrue(CustomInt.totalComparisions((CustomInt[])arr1) == 1225);
		assertTrue(isSorted(arr1));
	}

	private boolean isSorted(Comparable[] arr){
		for(int i=1; i<arr.length; i++){
			if(arr[i-1].compareTo(arr[i]) > 0){
				return false;
			}
		}
		return true;
	}
}