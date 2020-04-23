import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PrimsMST {
	
	public static void main ( String[] args ) throws IOException {
	        
	        primsGraph(readGraphFile("weightedgraph.txt"));
	        primsGraph(randomWeightedGraph(100,300,10,1));
	        primsGraph(randomWeightedGraph(50000,300000,100,8));
	        //primsGraph(randomWeightedGraph(300000,500000,10,1));
	 }// end main 

    private static Graph readGraphFile ( String fileName ) throws IOException {
        Graph fileGraph;
        BufferedReader in = new BufferedReader(new FileReader("weightedgraph.txt"));
        String[] splitLine = new String[3];
        splitLine = in.readLine().split(" ");
        fileGraph = new Graph(Integer.parseInt(splitLine[0]));
        int edgeCt = Integer.parseInt(splitLine[1]);
        while ( fileGraph.getEdgeCount() < edgeCt ) {
            splitLine = in.readLine().split(" ");
            int vertUno = Integer.parseInt(splitLine[0]);
            int vertDos = Integer.parseInt(splitLine[1]);
            Double weight = Double.parseDouble(splitLine[2]);
            fileGraph.addEdge(vertUno,vertDos,weight);
        }
        fileGraph.markAll(0);
        in.close();
        return fileGraph;
    }// end readfile
    
    public static Graph primsGraph(Graph g) {
        Graph t = new Graph(g.getVertexCount());
        
        double weight = 0;
        boolean [] inTree = new boolean[g.getVertexCount()];

        //inTree[0] = true;
        Graph.Edge[] edges = g.getAllEdges();
    	Arrays.sort(edges, (a,b) -> new Double(a.weight).compareTo(new Double(b.weight)));
    	
    	t.addEdge(edges[0].from, edges[0].to, edges[0].weight);
    	inTree[edges[0].from] = true;
    	inTree[edges[0].to] = true;
    	weight += edges[0].weight;
    	//System.out.println(edges.length);

        for(int i = 0; i < g.getVertexCount() -1; i++) {           //Graph.Edge min;
        	
            for(Graph.Edge edge : edges) {
            	//System.out.println(edge.weight);
                if((inTree[edge.from] && !inTree[edge.to]) || (!inTree[edge.from] && inTree[edge.to]) ) { //&& edge.weight < min.weight) {
                    t.addEdge(edge.from, edge.to, edge.weight);
                    weight += edge.weight;
                    inTree[edge.from] = true;
                    inTree[edge.to] = true;
                    //System.out.println(edge.weight);
                        break;
                }// end if
            }// end for each
           
        }// end big for
        
        System.out.println(weight);
        return t;
        
    }// end primsGraph
    
    private static Graph randomWeightedGraph(int v, int e, int maxWeight, long seed) {
        Random rand = new Random(seed);
        Graph g = new Graph(v);
        while (g.getEdgeCount() < e) {
            int x = rand.nextInt(v);
            int y = rand.nextInt(v);
            if (x != y && ! g.edgeExists(x, y))
                g.addEdge(x, y, 1 + rand.nextInt(maxWeight));
        }
        return g;
    }
    
 

}// end class
