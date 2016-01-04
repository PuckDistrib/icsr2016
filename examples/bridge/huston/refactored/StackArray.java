package bridge2.refactored;

//code from Huston

interface Stack {
	public void push(int i );
    public boolean isEmpty();
    public int top();
    public int pop();
}
class StackArray implements Stack {
    private int[] items = new int[12];
    private int   total = -1;
    public void push( int i ) { if ( ! isFull()) items[++total] = i; }
    public boolean isEmpty()  { return total == -1; }
    public boolean isFull()   { return total == 11; }
    public int top() {
        if (isEmpty()) return -1;
        return items[total];
    }
    public int pop() {
        if (isEmpty()) return -1;
        return items[total--];
}   }

class StackList implements Stack{
    private Node last;
    public void push( int i ) {
        if (last == null)
            last = new Node( i );
        else {
            last.next = new Node( i );
            last.next.prev = last;
            last = last.next;
    }   }
    public boolean isEmpty() { return last == null; }
    public boolean isFull()  { return false; }
    public int top() {
        if (isEmpty()) return -1;
        return last.value;
    }
    public int pop() {
        if (isEmpty()) return -1;
        int ret = last.value;
        last = last.prev;
        return ret;
}   }

abstract class AbstractStack implements Stack{
	private Stack impl = StackFactory.make("StackArray");
	
	public void push(int i) { impl.push(i); }
	
	public int pop() { return impl.pop(); }
	
	public boolean isEmpty() { return impl.isEmpty(); }

	public int top() { return impl.top(); } 
}

class StackFIFO extends AbstractStack{
    private Stack impl = StackFactory.make("StackArray");
	private Stack temp = StackFactory.make("StackArray");
    
	public int pop() {
        while ( ! isEmpty())
            temp.push( impl.pop() );
        int ret =  temp.pop();
        while ( ! temp.isEmpty())
            push( temp.pop() );
        return ret;
    }

}

//interface ReportRejected{
//	public int reportRejected();
//}

class StackHanoi extends AbstractStack {
	private Stack impl = StackFactory.make("StackArray");
    private int totalRejected = 0;
    public int reportRejected()   { return totalRejected; }
    public void push( int in ) {
        if ( ! isEmpty()  &&  in > top())
            totalRejected++;
        else impl.push( in );
    }
}

class StackFactory{
	public static Stack make(String s){
		if(s.equals("StackArray"))
			return new StackArray();
		else if(s.equals("StackFIFO"))
			return new StackFIFO();
		else if(s.equals("StackHanoi"))
			return new StackHanoi();
		else if(s.equals("StackList"))
			return new StackList();
		else	
			return null;
		 
	}
}

class BridgeDisc {
    public static void main( String[] args ) {
    	
        Stack[] stacks = { StackFactory.make("StackArray"), StackFactory.make("StackFIFO"), StackFactory.make("StackHanoi") };
        Stack    stack2 = StackFactory.make("StackList");
        for (int i=1, num; i < 15; i++) {
            stacks[0].push( i );
            stack2.push( i );
            stacks[1].push( i );
        }
        java.util.Random rn = new java.util.Random();
        for (int i=1, num; i < 15; i++)
            stacks[2].push( rn.nextInt(20) );
        while ( ! stacks[0].isEmpty())
            System.out.print( stacks[0].pop() + "  " );
        System.out.println();
        while ( ! stack2.isEmpty())
            System.out.print( stack2.pop() + "  " );
        System.out.println();
        while ( ! stacks[1].isEmpty())
            System.out.print( stacks[1].pop() + "  " );
        System.out.println();
        while ( ! stacks[2].isEmpty())
            System.out.print( stacks[2].pop() + "  " );
        System.out.println();
        System.out.println( "total rejected is "
            + ((StackHanoi)stacks[2]).reportRejected() );
}   }

// 12  11  10  9  8  7  6  5  4  3  2  1
// 14  13  12  11  10  9  8  7  6  5  4  3  2  1
// 1  2  3  4  5  6  7  8  9  10  11  12
// 0  0  1  12  16  18
// total rejected is 8

class Node {
    public int  value;
    public Node prev, next;
    public Node( int i ) { value = i; }
}