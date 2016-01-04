package strategy.refactored;

//merge of BubbleSort and LinearSort interfaces


interface Sort{
	public void sort(int[] numbers, SortingContext ctxt);
}

class BubbleSortImpl implements Sort{
	public void sort(int[] numbers, SortingContext ctxt) {
		System.out.print("Sorting by BubbleSort...");		
		for (int end = numbers.length; end > 1; end --) {
			for (int current = 0; current < end - 1; current ++) {
				if (numbers[current] > numbers[current+1]) {
					ctxt.exchange(numbers, current, current+1);
				}
			}
		}
		System.out.println("done.");
	}
}

class LinearSortImpl implements Sort{
	public void sort(int[] numbers, SortingContext ctxt) {
		System.out.print("Sorting by LinearSort...");
		int lowest  = 0;  
		
		for (int start = 0; start < numbers.length; start ++) {
			lowest = start;
			
			for (int current = start; current < numbers.length; current ++) {
				if (numbers[current] < numbers[lowest]) {
					lowest = current;
				}
			}
    		ctxt.exchange(numbers, start, lowest);
		}
		System.out.println("done.");
	}
}

class SortingContext { 
   public void methodUnrelatedWithTheSorting(){
		//do other things unrelated with the sorting
		// for example, it could manage the sorted array
		// the point is that the sorting is a tool for the context and not a part of it : 
		//delegation should be clearly the natural solution rather than inheritance 
	}
	
   public SortingContext(Sort algo, int[] numbers)	{
		System.out.println("\nPreparing sort...");
		System.out.println("original: "+show(numbers)); 
		sort(algo, numbers);
		System.out.println("sorted:   "+show(numbers));  
		System.out.println("Done sorting.");
	}

	private static String show(int[] numbers) {
		String out = "";
		for (int i=0; i<numbers.length; i++)
		{
			out += (numbers[i] + " ");
		}  
		return out;
	}
	
	void exchange(int[] numbers, int pos1, int pos2) {
		int tmp = numbers[pos1];
		numbers[pos1] = numbers[pos2];
		numbers[pos2] = tmp;
	}
	
	public void sort(Sort algo, int[] numbers) {
		algo.sort(numbers, this);
	}
}


public class StrategyDemo {	
    
  	public static void main(String[] args)	{
		int[] numbers = {3, 2, 9, 8, 1, 5, 6, 4, 7, 0};

		
		if (args.length == 0) {
			new SortingContext(new LinearSortImpl(), numbers);
		} 
		else {
			new SortingContext(new BubbleSortImpl(), numbers);
		}
	}
}										
	
	
