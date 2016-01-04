package strategy.pattern;

//hannemann

class Sorter { 

	public Sorter(SortingStrategy sort, int[] numbers)	{
		System.out.println("\nPreparing sort...");
		System.out.println("original: "+show(numbers)); 
		sort.sort(numbers);
		System.out.println("sorted:   "+show(numbers));  
		System.out.println("Done sorting.");
	}

	private static String show(int[] numbers) {
		String out = "";
		for (int i=0; i<numbers.length; i++){
			out += (numbers[i] + " ");
		}  
		return out;
	}
}

interface SortingStrategy {
	public void sort(int[] numbers);
}

class LinearSort implements SortingStrategy{
    
 	private void exchange(int[] numbers, int pos1, int pos2) {
		int tmp = numbers[pos1];
		numbers[pos1] = numbers[pos2];
		numbers[pos2] = tmp;
	}


 	public void sort(int[] numbers) {
		System.out.print("Sorting by LinearSort...");
		int lowest  = 0;  
		
		for (int start = 0; start < numbers.length; start ++) {
			lowest = start;
			
			for (int current = start; current < numbers.length; current ++) {
				if (numbers[current] < numbers[lowest]) {
					lowest = current;
				}
			}
    		exchange(numbers, start, lowest);
		}
		System.out.println("done.");
	}
}

class BubbleSort implements SortingStrategy {

	private void exchange(int[] numbers, int pos1, int pos2) {
		int tmp = numbers[pos1];
		numbers[pos1] = numbers[pos2];
		numbers[pos2] = tmp;
	}

	public void sort(int[] numbers) {
		System.out.print("Sorting by BubbleSort...");		
		for (int end = numbers.length; end > 1; end --) {
			for (int current = 0; current < end - 1; current ++) {
				if (numbers[current] > numbers[current+1]) {
					exchange(numbers, current, current+1);
				}
			}
		}
		System.out.println("done.");
	}
}

public class StrategyDemo {	
    
    	public static void main(String[] args)	{
		int[] numbers = {3, 2, 9, 8, 1, 5, 6, 4, 7, 0};
				
		SortingStrategy sort1 = new LinearSort();
		SortingStrategy sort2 = new BubbleSort();
		
		Sorter sorter;
		
		if (args.length == 0) {
			sorter = new Sorter(sort1, numbers);
		} 
		else {
			sorter = new Sorter(sort2, numbers);
		}
	}
}										
	
	
