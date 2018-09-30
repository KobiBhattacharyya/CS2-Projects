public class InsertionSort {

	public static void sort(Comparable[] list) {
		// Go through the unsorted part of the array
		for (int i = 1; i < list.length; i++) {
			Comparable key = list[i];
			// Find the right location for the key
			int insertion_point = i;
			while (insertion_point > 0 && (list[insertion_point-1].compareTo(key) > 0)) {
				// System.out.println("insertion_point: " + insertion_point);

				list[insertion_point] = list[insertion_point-1];
				insertion_point--;
			}
			list[insertion_point] = key;
		}
	}
}
