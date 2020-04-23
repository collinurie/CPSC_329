import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
	
	
	public static void main(String[] args){
		
		// test first "random" graph
		Graph g = randomGraph(100,100,1);
		System.out.println(getLargestComponant(g));
		// test second "random" graph 
		Graph gg = randomGraph(250000,300000,1);
		System.out.println(getLargestComponant(gg));
		
	
		
		
	}// end main 
	
	public static int getLargestComponant(Graph g) {
		Queue<Integer> q = new LinkedList<>();
		
		int max = 0;
		for(int i = 0; i < g.getVertexCount(); i++) {
			int count = 0;
			if(g.getMark(i) == 0) {
				g.mark(i, 1);
				q.add(i);
				count++;
				
			
				while(!q.isEmpty()) {
					int v = q.remove();
					int [] vList = g.getConnectedVertices(v);
					for(int j= 0; j <vList.length; j++) {
						if(g.getMark(vList[j]) == 0) {
							count++;
							g.mark(vList[j], 1);
							q.add(vList[j]);
						}
					}// end for 
					g.mark(v, 2);
					
				}// end while
				
				
				if(count > max)
					max = count;
				if( max > (g.getVertexCount()/2))
					return max;
				
			}
		}// end for
		
		
		return max;
	}// end getGraphSize
	
	

	/**
     * Creates a random undirected graph with specified numbers of vertices
     * and edges, using a pseudo-random number generator initialized with a
     * given seed.
     */
    private static Graph randomGraph( int vertexCt, int edgeCt, int seed ) {
        Random rand = new Random(seed);
        Graph grph;
        grph = new Graph(vertexCt);
        while (grph.getEdgeCount() < edgeCt) {
            int a = rand.nextInt(vertexCt);
            int b = rand.nextInt(vertexCt);
            if ( a != b && ! grph.edgeExists(a, b) ) {
                grph.addEdge(a, b);
            }
        }
        grph.markAll(0);
        return grph;
    }// end randomGraph
    
    
    public static void prims(Graph g) {
    	/******************************************
    	 *** TODO: make prims algorithm work!!!!!!!
    	 ******************************************/
    }// end prims
    
    
	
}// end class 
