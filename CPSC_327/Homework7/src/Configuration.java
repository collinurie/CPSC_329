import java.util.Random;

/**
 * @author Collin Urie
 */
public class Configuration{
	
	int[] configList;
	private int k;
	private int fitness;
	
	/**
	 * Creates a new Configuration object 
	 * @param g
	 * @param n
	 * @param k
	 */
	public Configuration(Graph g, int n, int k) {
		configList = new int[n];
		this.k = k;
		
		for(int i = 0; i < n; i++){
			int rand = (int)(Math.random() * k); 
			  
			configList[i] = rand;
		}
		assignFitness(g);
	}// end constructor 
	
	/**
	 * Creates a new Configuration object 
	 * @param g
	 * @param n
	 * @param k
	 */
	public Configuration(Graph g, int[] list, int k) {
		configList = list;
		this.k = k;
		assignFitness(g);
	}// end constructor
	
	
	/**
	 * Looks through a configuration and assigns a fitness
	 * based on the graph.
	 * @param g
	 */
	public void assignFitness(Graph g){
		int[] conV;
		int fit = 0;
		for(int i = 0; i < g.getVertexCount(); i++) {
			conV = g.getConnectedVertices(i);
			for(int j = 0; j < conV.length; j++){
				// see if any connected vertices have the same color 
				if(configList[i] == configList[conV[j]])
					fit++;
			}
		}
		 fitness = fit;
	}// end assignFitness 
	
	
	/**
	 * returns the fitness of the Configuration 
	 * @return
	 */
	public int getFitness() {
		return fitness;
	}// end getFitness 
	
	/**
	 * returns the global K
	 * @return
	 */
	public int getK() {
		return k;
	}// end getK

	
}// end class
