public class QuickSort
{

/***************************************************************************/
	// Quicksort
	/***************************************************************************/
	public static <T extends Comparable<T>> void quickSort(T[] list) {
		quickSortHelper(list, 0, list.length-1);
	}

	public static <T extends Comparable<T>> void quickSortHelper(T[] list, int start_range, int end_range) {

      // Empty range, so return
      if (start_range >= end_range) return;

      // Set up the pointers that will move in and look for out-of-place values
      int left = start_range;
      int right = end_range;

      // Set the partition point to be the middle of the range
      int pivot_index = start_range + (end_range - start_range) / 2;
      T pivot = list[pivot_index];

      // Continue while there are more swaps to be made
      while (left < right) {
				// System.out.println(left + " " + right + " ");
				// System.out.println("\t" + elements[left] + " " + elements[pivot_index] + " " + elements[right]);

        // Find something on the left to swap...even if it is the pivot
        while (list[left].compareTo(pivot) < 0 && left < pivot_index) {
          left++;
        }

        // Find something on the right to swap...even if it is the pivot
        while (list[right].compareTo(pivot) >= 0 && right > pivot_index) {
          right--;
        }

        // Now we have found the elements, so swap them
        // Swap element at left with element at right
        T tmp = list[left];
        list[left] = list[right];
        list[right] = tmp;

        // If one of the swapped values was the pivot,
        // then we need to update the pivot index
        if (left == pivot_index) {
            pivot_index = right;
        }
        else if (right == pivot_index) {
            pivot_index = left;
        }
      }

      // pivot index is sorted, now sort each other side recursively
      quickSortHelper(list, start_range, pivot_index-1);
      quickSortHelper(list, pivot_index+1, end_range);

		}
	}
