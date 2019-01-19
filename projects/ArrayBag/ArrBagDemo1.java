import java.io.*;
import java.util.*;

public class ArrBagDemo1
{
 
    public static void main(String a[]) {

		ArrBag<String> bag1 = new ArrBag<String>();
		ArrBag<String> bag2 = new ArrBag<String>();
		String[] words = {"alpha", "bravo", "charlie",  "delta",  "echo" };

		for ( String word : words )
			bag1.add( word );
		String[] newWords = {"test1", "test2", "test3", "test1", "test1", "bravo", "test4", "echo", "test1", "test5", "test6" };
		for ( String word : newWords )
			bag2.add( word );

		System.out.println("**************");
		System.out.println(" ");

		System.out.println("Testing Union");

		ArrBag<String> unionBag = bag1.union(bag2);

		String expectedUnion = "alpha bravo charlie delta echo test1 test2 test3 test4 test5 test6";
		System.out.println(expectedUnion + " <-- expected");
		System.out.println(unionBag.toString());

		System.out.println(" ");
		System.out.println("**************");
		System.out.println(" ");

		System.out.println("Testing Intersection");

		ArrBag<String> intBag = bag1.intersection(bag2);

		String expectedInt = "bravo echo";
		System.out.println(expectedInt + " <-- expected");
		System.out.println(intBag.toString());

		System.out.println(" ");
		System.out.println("**************");
		System.out.println(" ");

		System.out.println("Testing Difference");

		ArrBag<String> diffBag = bag1.difference(bag2);

		String expectedDiff = "alpha charlie delta";
		System.out.println(expectedDiff + " <-- expected");
		System.out.println(diffBag.toString());

		System.out.println(" ");
		System.out.println("**************");
		System.out.println(" ");

		System.out.println("Testing Xor");

		ArrBag<String> xBag = bag1.xor(bag2);

		// String expectedDiff = "alpha charlie delta";
		// System.out.println(expectedDiff + " <-- expected");
		System.out.println(xBag.toString());
    }
}

class ArrBag<T> {	
	final int NOT_FOUND = -1;
	final int INITIAL_CAPACITY = 10;
	private int count; // LOGICAL SIZE

	@SuppressWarnings("unchecked") 
	T[] theArray = (T[]) new Object[INITIAL_CAPACITY]; 

	public int size() {
		return count; 
	}
	
	// DEFAULT C'TOR
	public ArrBag( )
	{
		count = 0; // i.e. logical size, actual number of elems in the array
	}
	
    // THIS C'TOR ACCEPTS FILENAME TO LOAD THE ARRAY FROM
	
	@SuppressWarnings("unchecked")
	public ArrBag( String filename ) throws Exception
	{
		count = 0; // i.e. logical size, actual number of elems in the array
		Scanner infile = new Scanner( new File( filename ) );
		while ( infile.hasNext() )
			this.add( (T) infile.next() ); // HAD TO CAST OR JAVA WHINES
		infile.close();
	}

	public boolean add( T element )
	{	if (element == null ) return false;
		if (size() == theArray.length) upSize(); // DOUBLES PHYSICAL CAPACITY
		theArray[ count++] = element; // WE RELAX RULE ABOUT ASSIGNING INTO COUNT OTHER THAN IN SETTER
		return true; // success. it was added
	}
	
	public T get( int index )
	{
		if ( index < 0 || index >=size() ) 
		{	System.out.println( "attept to get elem at non-existent index (" + index + ")\n" );
			System.exit(0);
		}
		return theArray[index];
	}
	
	// SEARCHES FOR THE KEY. TRUE IF FOUND, OTHERWISE FALSE
	public boolean contains( T key )
	{	if (key == null) return false;
		for ( int i=0 ; i < size() ; ++i )
			if ( get(i).equals( key ) ) // WE ARE MAKING AN ASSUMPTION ABOUT TYPE T... WHAT IS IT?
				return true;
		return false;
	}

	public String toString()
	{
		String toString  = ""; // YES YOU ARE ALLOWED TO NAME VAR SAME AS METHOD IT's IN
		for ( int i=0 ; i < size() ; ++i  )
		{
			toString += get(i);
			if ( i < size() - 1 ) 	// DONT PUT SPACE AFTER LAST ELEM
				toString += " ";
		}
		return toString;
	}	



	// **********MINE***********

	public void clear() {
		count = 0; 
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void upSize() {
		@SuppressWarnings("unchecked") 
        T[] tempArray = (T[]) new Object[size() * 2];
        for (int i = 0; i < size(); i++)
            tempArray[i] = get(i);
		theArray = tempArray;
	}

	public ArrBag<T> union( ArrBag<T> other ) {	
		ArrBag<T> unionBag = new ArrBag<T>();
		for(int i = 0; i < size(); i++) unionBag.add(get(i));
		for (int i = 0; i < other.size(); i++)
			if (!unionBag.contains(other.get(i))) unionBag.add(other.get(i));
		return unionBag;
	}

	public ArrBag<T> intersection(ArrBag<T> other) {
		ArrBag<T> intBag = new ArrBag<T>();
		for(int i = 0; i < size(); i++)
			if(other.contains(get(i))) intBag.add(get(i));
		return intBag;
	}

	public ArrBag<T> difference(ArrBag<T> other) {
		ArrBag<T> diffBag = new ArrBag<T>();
		for (int i = 0; i < size(); i++) 
			if (!other.contains(get(i))) diffBag.add(get(i));
		return diffBag;
	}

	// RETURNS A THIRD ARRBAG CONTAINING ONLY ONE COPY (NO DUPES) OF ALL THE ELEMENTS 
	// CONTAINED IN THE UNION OF THIS AND OTHER - INTERSECTION OF THIS AND OTHER
	// DOES -NOT- MODIFY THE THIS OR OTHER
	public ArrBag<T> xor( ArrBag<T> other ) {
		// no loops allowed in this one  going to be some kind of call. probably a one liner code reuse <3

		return union(other).difference(other);
	}
} 
