package com.tools.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tools.stats.generator.StatDataTableGenerator;
import com.tools.stats.util.StatDataTableType;

/**
 *
 *
 */
public class ExperimentSimulator 
{ private static Logger log = LogManager.getLogger(ExperimentSimulator.class.getCanonicalName());
    public static void main( String[] args )
    {
    	
    	StatDataTableGenerator<Double,Double> generator = new StatDataTableGenerator<Double,Double>();
    	StatDataExperiment<Double,Double> experiment = new StatDataExperiment<Double,Double>();
    
    	int status = 1;
    	long start, end;
    	
    	String filename = "./src/main/resources/samples.csv";
    	
    	experiment.setId("1-Experiment221");
    	experiment.setCode("YYYY-BBBBA");
    	
    	generator.setLengthOfLists(5);
    	generator.setNumOfLists(1);
    	generator.setNumOfDigits(3);
    	
    	
    	
    	try {
    		start = System.currentTimeMillis();
    		System.out.println("Starting at:" + start);
    		
    		experiment.setGenerator(generator);
    		/* Generate random tables */
    		//status = experiment.generateRandomDataTables();
    		
    		//log.debug("Random Tables Generated with status "+ status);
	        /* Generate tables from file */
    		
    		status = experiment.generateDataTablesFromFile(filename);
     		log.debug(" Tables Generated from File #: with status "+ status);
     		 		
    		/*Generate mixed tables*/
    		
    		List<StatDataTable<Double,Double>> dataTables = experiment.getDataTables();
    		
    		Set<String> ids = new HashSet<String>();
    		for(int i=0;i<dataTables.size();i++)
    			ids.add(dataTables.get(i).getId());
    		
    		List<String> idls = new LinkedList<String>(ids);
    		
    		
    		/*Mixing data from all existing tables */
    		status = experiment.createFromDataTables(idls, null, StatDataTableType.MIXED);	
    		
    		
    		
    		
    		/*Compute statistical values */
    		experiment.setAllStatDataTableMomentum(2);  //M:2 for geometric mean and standard deviation
    		status = experiment.computeStats();
    		log.debug(" Statistics  Generated with status : "+ status);
         		
    		/*Contrasting list(0) against list(1) and List(2)
     	    * 
     	    */
         	List<StatDataTable<Double,Double>> source = Arrays.asList(experiment.getStatDataTable(0));
         	List<StatDataTable<Double,Double>> contrasts = Arrays.asList(experiment.getStatDataTable(1),experiment.getStatDataTable(2)); 	
         	List<String> contrastids =experiment.contrastTables(source, contrasts);
         	
     		log.debug("Contrasted tables, result :" + contrastids.toArray());
     		
     		/*Normalizing CONTRASTED tables N0,1*/
     	    	status = experiment.normalizeDataTables(contrastids);
     	    	log.debug(" Tables normalized  with status : "+ status);
     	         	
     		
     		
     		/*Compute statistical values */
    		experiment.setAllStatDataTableMomentum(2);  //M:2 for geometric mean and standard deviation
    		status = experiment.computeStats();
    		log.debug(" Statistics  Generated with status : "+ status);
         	
    		
    		
    		for(int i=0;i<dataTables.size();i++) {
    			StatDataTable<Double,Double> sdt =  dataTables.get(i);
    			System.out.println("Table#: " + i + " ID: "+ sdt.getId() +" of type:"+ sdt.getType());
    			System.out.println("");
    			List<Double> values = sdt.getDataTableValues();
    			System.out.println("Table values:");
    			
    			for(int j=0;j<values.size();j++) {
    				System.out.print(" " + values.get(j).toString() + " ");
    			}
    			System.out.println("");
    			System.out.println("-------------------------------------");
    			System.out.println("Table Rows:");
    		    for(StatDataRow row: sdt.getDataTable().values()) {
    		    	System.out.println("Id:" + row.getId() + " Value: "+ row.getValue() + " AbsoluteFreq: " + row.getAbsoluteFreq() +
    		    			" RelativeFreq: " + row.getRelativeFreq() + " CumulativeAbsFreq: " + row.getCumulativeFreq() + " CumulativeRelativeFreq : "+ row.getCumulativeRelativeFreq()+ " ROWTYPE:"+ row.getRtype());
    		    } 	
    			System.out.println("---------------------------------------");
    		
    			System.out.println("Table Statistics:");
    			System.out.println(" Median:" + sdt.getMedian());
    			System.out.println(" Arith. Mean:" + sdt.getArithMean());
    			System.out.println(" Harmonic. Mean:" + sdt.getHarmMean());
    			System.out.println(" Geometric Mean:" + sdt.getGeomMean());
   
    			System.out.println(" Standard Deviation:" + sdt.getStandardDev());
    			
    			System.out.println(" Momentum:" + sdt.getMomentum());
    			
    			System.out.println("***********************************");
    			
    			
    			
    			
    		}
    		end = System.currentTimeMillis();
    		System.out.println("Ended at:" + end);
    		
    		System.out.println("Total seconds : " + (end-start)/1000);
    		status = 0;
    	}catch(Exception ex) {
    		status = -1;
    		log.debug("Exception while executing main application :" + ex.getLocalizedMessage());
    		ex.printStackTrace();
    		
    	}
    	
    	
    System.exit(status);
    
    
    }
}
