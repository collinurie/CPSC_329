import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Collin Urie
 */
public class GeneticAlgorithm {
	
	/********************************INSTRUCTIONS******************************** 
	 * To change the graph that this runs on, change Graph g
	 * this program runs a genetic algorithm that takes a graph and
	 * number of colors and outputs a configuration that has fitness 0
	 * if it can find that configuration in the set amount of attempts.
	 * 
	 * 
	 * To change the number of attempts you need to go into the method 
	 * "genetic(Graph g, int k)" and change the value in the while loop.
	 ********************************************************************************/
	public static void main(String[] args) {
		
		//**** CHANGE COLOR NUMBER HERE
		int colorNum = 4;
		
		//**** CHANGE GRAPH HERE ( can make grid with block of code commented out below) 
		Graph g = randomGraph( 100, 300, 1 );
		//Graph g = makeGrid(30,30);
		
		
		// the configuration
		Configuration c = genetic(g,colorNum);
		
		// output results 
		if(c == null) {
			System.out.println("\nNo configuration found..." );
		}
		else {
			System.out.println("\nConfiguration found: ");
			for(int i: c.configList) {
				System.out.print(i+", ");
			}
		}
		
	
	}// end main 
	
	
	/**
	 * Performes a genetic algorithm 
	 * @param g
	 * @param k
	 * @return
	 */
	public static Configuration genetic(Graph g, int k){
		Configuration bestEver;
		int bestEverFit;
		int ct = 0;

		Configuration[] population = new Configuration[1000];

		int vc = g.getVertexCount();
		
		// set up random population 
		for(int i = 0; i < population.length; i++) {
			population[i] = new Configuration(g,vc, k);
		}// end forEach
	
		bestEverFit = population[0].getFitness();
		bestEver = population[0];
		
		//**** CHANGE NUMBER OF TIMES IT CHECKS FOR A CONFIG BEFORE QUITTING
		while(ct < 10000) {
			Arrays.sort(population, (a,b) -> new Integer(a.getFitness()).compareTo(new Integer(b.getFitness())));			

			int bestFit = population[0].getFitness();
			
			Configuration[] newPop = new Configuration[population.length];
			for(int i = 0; i < newPop.length; i++) {

					Configuration con1 = population[i];
					// combines w/ config from the first half of the array
					Configuration con2 = population[(int)(Math.random() *
													(population.length/2))];
					
					
					// create new config
					int[] newArr = new int[vc];
					int rand = (int)(Math.random() * vc); 
					for(int j = 0; j < vc; j++) {
						  if(j <= rand) 
							  newArr[j] = con1.configList[j];
						  
						  else
							  newArr[j] = con2.configList[j];
							  
					}
					
					//mutates the new configuration 80% of the time 
					if(prob(80)) {
						int r = (int)(Math.random()*newArr.length);
						
						newArr[r] = (int)(Math.random()*k);
					}
					
					// creates the new configuration from the array 
					Configuration newCon = new Configuration(g,newArr,k);
					
					//places config in new population 
					newPop[i] = newCon;
					
					// see if it is the best 
					if(newCon.getFitness() <= bestFit) {
						bestFit = newCon.getFitness();
						if(bestFit < bestEverFit) {
							bestEverFit = bestFit;
							bestEver = newCon;
						}
					}
			}// end big for
			System.out.println("Best after "+ct+":\t"
								+bestFit+"\tBest Ever:\t"+bestEverFit);
			if(bestEverFit == 0) {
				return bestEver;
			}
			ct++;
			population = newPop;
		}// end while
		
	
		return null;
	}// end genetic 
	
	
	/**
     * Make a graph that is a rectangular grid with the specified
     * number of rows and columns.  A vertex is connected to its
     * neighbors to the left, to the right, above, and below.
     */
    static Graph makeGrid(int rows, int cols) {
        Graph g = new Graph(rows*cols);
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows-1; r++) {
                int a = r*cols + c;
                int b = a + cols;
                g.addEdge(a,b);
            }
        }
        for (int c = 0; c < cols-1; c++) {
            for (int r = 0; r < rows; r++) {
                int a = r*cols + c;
                int b = a + 1;
                g.addEdge(a,b);
            }
        }
        return g;
    }// end makeGrid

    
    /**
     * Given a percentage it returns true that percent of the time.
     * @param pct
     * @return
     */
    public static boolean prob(int pct) {
    	int random =(int)(Math.random()*100);
    	
    	if(random <= pct)
    		return true;
    	else
    		return false;
    }// end prob
    
    
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
        return grph;
    }// end randomGraph

}// end class
