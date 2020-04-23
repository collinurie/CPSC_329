import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Homework3Runner {
	public static void main(String[] args) {
		Random rand = new Random();
		double[] A = new double[1000000];
		ArrayList<Double> a = new ArrayList<Double>();
		ArrayList<Double> b = new ArrayList<Double>();
		ArrayList<Double> c = new ArrayList<Double>();
		for(int i = 0; i < A.length; i++) {
			    double randomNum = rand.nextDouble();
			    A[i] = randomNum;
			    a.add(randomNum);
			    b.add(randomNum);
			    c.add(randomNum);
			    
     	 }
		double aTime = 0;
		double bTime = 0;
		double cTime = 0;
		double dTime = 0;
		long startA , endA, startB, endB, startC, endC, startD, endD;
			
		
			 startA = System.nanoTime();
			heapSort(a,a.size());
			 endA = System.nanoTime();
			
			 startB = System.nanoTime();
			addRemoveSort(b);
			 endB = System.nanoTime();
			
			 startC = System.nanoTime();
			randomQuickSort(c,0,c.size()-1);
			 endC = System.nanoTime();
			
			 startD = System.nanoTime();
			Arrays.sort(A);
			 endD = System.nanoTime();
		
		
		
			 aTime += (double)(endA - startA)/ 1_000_000_000.0;
			 bTime += (double)(endB - startB)/ 1_000_000_000.0;
			 cTime += (double)(endC - startC)/ 1_000_000_000.0;
			 dTime += (double)(endD - startD)/ 1_000_000_000.0;
		
		
		
		
		
		System.out.println("heapSort :: "+ aTime);
		System.out.println("addRemoveSort :: "+ bTime);
		System.out.println("randomQuickSort :: "+ cTime);
		System.out.println("JavaSort :: "+ dTime);
		
		


		
	}// end main
	
	
	/**
	 * 
	 * @param A
	 * @param n
	 * @return
	 */
	public static ArrayList<Double> heapSort(ArrayList<Double> A, int n){
			
			for(int i = n/2-1; i >= 0; i--) {
				A = MaxHeap.maxHeapify(A, i, n);
			}
			for(int size = n -1; size >= 0; size--) {
				double x = A.get(0);
				A.set(0, A.get(size));
				A.set(size, x);
				A = MaxHeap.maxHeapify(A,0,size);
				
			}
			
			return A;
	}// end heapSort
	
	/**
	 * 
	 * @param A
	 * @return
	 */
	public static ArrayList<Double> addRemoveSort(ArrayList<Double> A){
		
		MaxHeap heap = new MaxHeap();
		for(double d: A) 
			heap.insert(d);
		
		for(int i = A.size()-1; i >= 0; i--) {
			A.set(i, heap.removeMax());
		}
		
		return A;
	}// end addremoveSort
	
	
	/**
	 * 
	 * @param A
	 * @param low
	 * @param high
	 * @return
	 */
	public static void quickSort(ArrayList<Double> A, int low, int high){

		if(low >= high) {
			return ;
		}
		else {
			int mid = partition(A,high,low);
			quickSort(A,low,mid-1);
			quickSort(A,mid+1, high);
		}
		
		
		return ;
	}// end quicksort 
	
	/**
	 * 
	 * @param A
	 * @param low
	 * @param high
	 */
	public static void randomQuickSort(ArrayList<Double> A, int low, int high) {
		if(low < high) {
			Random rand = new Random();
			int r = rand.nextInt(high-low)+low;
			double temp = A.get(r);
			A.set(r, A.get(high));
			A.set(high, temp);
			int mid = partition(A,high,low);
			randomQuickSort(A,low,mid-1);
			randomQuickSort(A,mid+1,high);
		}
		
	}
	
	/**
	 * 
	 * @param A
	 * @param high
	 * @param low
	 * @return
	 */
	public static int partition(ArrayList<Double> A, int high, int low){

		double pivot = A.get(low);

		int j = low;
		for(int i = low+1; i <= high; i++) {
			if(A.get(i) <= pivot) {
				A.set(j, A.get(i));
				j++;
				A.set(i, A.get(j));
			}
		}
		A.set(j, pivot);
	
		
		return j;
	}// end partition
	

}
