import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a class that will allow you to iterate through the first n
 * Fibonacci elements
 * @author kushagramansingh
 *
 */
public class FibonacciIterator implements Iterator<Integer> {

	//Do not add any instance variables, you may not need to use all of them though
	private Integer n;
	private Integer current;
	private Integer runningValue = 1;
	private Integer previousValue = 0;
	
	public FibonacciIterator(Integer n) {
		this.n = n;
		this.current = 1;
	}
	
	@Override
	public boolean hasNext() {
		return current <= n;
	}

	@Override
	public Integer next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}

//		if (current == 0) {
//			current++;
//			return previousValue;
//		} else if (current ==1) {
//			current++;
//			return runningValue;
//		} else {
//			Integer tem = runningValue;
//			runningValue += previousValue;
//			previousValue = tem;
//			current ++;
//			return runningValue;
//		}


		if (current == 1) {
			current++;
			return 0;
		} else if (current == 2) {
			current++;
			return 1;
		} else {
			Integer temp = runningValue;
			runningValue += previousValue;
			previousValue = temp;
			current++;
			return runningValue;
		}
	}
}
