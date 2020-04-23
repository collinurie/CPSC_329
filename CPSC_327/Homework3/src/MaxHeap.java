import java.util.ArrayList;

public class MaxHeap {
	
	ArrayList<Double> heap;
	
	public MaxHeap(){
		heap = new ArrayList<Double>();
	}// end constructor

	public void insert(double x){
		heap.add(x);
		int index = heap.size()-1;
		while(index > 0) {
			if(index % 2 == 1) {
				if(heap.get(index/2) < x) {
					//System.out.println("**"+index);
					double temp = heap.get(index/2);
					heap.set(index/2, x);
					heap.set(index, temp);
					index = index/2;
				}
				else
					break;
			
			}
			else {
				if(heap.get(index/2 - 1) < x) {
					//System.out.println("***"+index);
					double temp = heap.get(index/2-1);
					heap.set(index/2 - 1, x);
					heap.set(index, temp);
					index = index/2 - 1;
				}
				else
					break;
			}
		}
		
		
	}// end insert 
	
	
	public double removeMax(){
		double max = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		heap = maxHeapify(heap, 0, heap.size());
		
		return max;
	}// end removeMax
	

	public  static ArrayList<Double> maxHeapify(ArrayList<Double> A, int root, int n){
		while(true) {
			int left = 2*root + 1;
			int right = 2*root + 2;
			
			if(left >= n){ // root has no children 
				break;
			}
			int bigChild = left;// the index of the larger child of root
			if(right < n && A.get(right) > A.get(bigChild) ){
				bigChild = right;
			}
			if(A.get(bigChild) <= A.get(root)){
				break;
			}
			double temp = A.get(root);
			A.set(root, A.get(bigChild));
			A.set(bigChild, temp);
			root = bigChild;
			
			
		}// end while
		
		return A;
	}// end maxHeapify
	
	
}
