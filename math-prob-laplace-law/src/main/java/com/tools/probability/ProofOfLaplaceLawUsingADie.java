package com.tools.probability;

import java.util.LinkedList;
import java.util.List;

/**
 * Laplace's law
 *
 */
public class ProofOfLaplaceLawUsingADie 
{    int[] header = {1,2,3,4,5,6};
	List<double[]> results;
	int r,c;
	int[] suites;
	
	public ProofOfLaplaceLawUsingADie() {
		r=0;c=0;
		results = new LinkedList<double[]>();
	}
	
	
	public void simulateLaplace(Die d, int[] suites) {
		this.suites = suites;
		for(int i=0;i<suites.length;i++) {
			compute(d,suites[i]);
			
		}
	
	}
	
	public void compute(Die d, int s) {
		double[] row = null;
		row = new double[6];
		c=0;
		
		
		if(results==null||results.size()==0) {
			row = new double[6];
			c=0;
		}
		for(int i=0;i<s;i++) { //we start simulation with a frequency of s
			d.thrown();
			int k = d.get();
		   
			c = k-1;
			if(c>=0 && c<6)
		    row[c] = row[c]+1;    /// 1,2,3,4,5,6
		}
		for(int i=0;i<row.length;i++)
			row[i] = row[i]/s;
	    results.add(r,row);
	    r++;
	 
	 	
		
	
	}
	
	
	public void display() {
	 System.out.format("Laplace's Law Results for a 6 faces Die:\n");
	 for(int i=0;i<header.length;i++) {
		 System.out.format(" %d ", header[i]);
	 }	 
	 System.out.println();
		 for(int i=0;i<results.size();i++) {
			 System.out.format("Number of throwns: %d \n",suites[i]);
			 System.out.println("Relative Frequencies");
			 for(int j=0;j<results.get(i).length;j++)
				 System.out.format(" %f ", results.get(i)[j] );
			 
			 System.out.println();
			 System.out.println("----------------------------------------------------");
		 }
	 
	 
	}
	
    public static void main( String[] args )
    {
     
    	Die die = new Die();
    	ProofOfLaplaceLawUsingADie proofLaplace = new ProofOfLaplaceLawUsingADie();
    	int[] suites = {25,100,1000};
    	
    	proofLaplace.simulateLaplace(die, suites);
    	
    	proofLaplace.display();
    	
    	
    	
    	
    	
    }
}
