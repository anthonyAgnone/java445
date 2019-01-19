import java.io.*;
import java.util.*;

public class ArrBag<T> {
	final int NOT_FOUND = -1;
	final int INITIAL_CAPACITY = 10;
	private int count;

	@SuppressWarnings("unchecked")
	T[] theArray = (T[]) new Object[INITIAL_CAPACITY];

	public int size() {
		return count;
	}

	public ArrBag() {
		count = 0;
	}

	@SuppressWarnings("unchecked")
	public ArrBag(String filename) throws Exception {
		count = 0;
		Scanner infile = new Scanner(new File(filename));
		while (infile.hasNext())
			this.add((T) infile.next());
		infile.close();
	}

	public boolean add(T element) {
		if (element == null)
			return false;
		if (size() == theArray.length)
			upSize();
		theArray[count++] = element;
		return true;
	}

	public T get(int index) {
		if (index < 0 || index >= size()) {
			System.out.println("attept to get elem at non-existent index (" + index + ")\n");
			System.exit(0);
		}
		return theArray[index];
	}

	public boolean contains(T key) {
		if (key == null)
			return false;
		for (int i = 0; i < size(); ++i)
			if (get(i).equals(key))
				return true;
		return false;
	}

	public String toString() {
		String toString = "";
		for (int i = 0; i < size(); ++i) {
			toString += get(i);
			if (i < size() - 1)
				toString += " ";
		}
		return toString;
	}

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

	public ArrBag<T> union(ArrBag<T> other) {
		ArrBag<T> unionBag = new ArrBag<T>();
		for (int i = 0; i < size(); i++)
			unionBag.add(get(i));
		for (int i = 0; i < other.size(); i++)
			if (!unionBag.contains(other.get(i)))
				unionBag.add(other.get(i));
		return unionBag;
	}

	public ArrBag<T> intersection(ArrBag<T> other) {
		ArrBag<T> intBag = new ArrBag<T>();
		for (int i = 0; i < size(); i++)
			if (other.contains(get(i)))
				intBag.add(get(i));
		return intBag;
	}

	public ArrBag<T> difference(ArrBag<T> other) {
		ArrBag<T> diffBag = new ArrBag<T>();
		for (int i = 0; i < size(); i++)
			if (!other.contains(get(i)))
				diffBag.add(get(i));
		return diffBag;
	}

	public ArrBag<T> xor(ArrBag<T> other) {
		return union(other).difference(intersection(other));
	}

}