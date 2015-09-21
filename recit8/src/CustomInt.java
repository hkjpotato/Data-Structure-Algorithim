/**
  * Integer wrapper class for testing purposes
  */

public class CustomInt implements Comparable<CustomInt> {
	int data;
	int comparisions;

	public CustomInt(int data){
		this.data = data;
	}

	public static Comparable[] toArray(int... args){
		Comparable[] arr = (Comparable[]) new CustomInt[args.length];
		for(int i=0; i<args.length; i++){
			arr[i] = new CustomInt(args[i]);
		}
		return arr;
	}

	public static int totalComparisions(CustomInt[] arr){
		int total = 0;
		for(CustomInt i: arr){
			total+= i.comparisions;
		}
		return total;
	}
	
	@Override
	public int compareTo(CustomInt o){
		comparisions++;
		if(this.data == o.data){
			return 0;
		}
		return this.data > o.data ? 1 : -1;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof CustomInt)){
			return false;
		}
		if(o==this){
			return true;
		}

		return this.data == ((CustomInt)o).data;
	}

	@Override
	public int hashCode(){
		return data;
	}

	@Override
	public String toString(){
		return "" + data;
	}
}