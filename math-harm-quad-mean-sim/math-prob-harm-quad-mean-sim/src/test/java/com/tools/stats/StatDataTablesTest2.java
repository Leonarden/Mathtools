package com.tools.stats;

import java.util.LinkedList;
import java.util.List;

import com.tools.stats.StatDataTable;
import com.tools.stats.generator.StatDataRowGenerator;
import com.tools.stats.generator.StatDataTableGenerator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StatDataTablesTest2 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StatDataTablesTest2( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StatDataTablesTest2.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
         List<StatDataTable<Double,Double>> dataTables = null;
    	
    	StatDataTableGenerator<Double,Double> gsdt = new StatDataTableGenerator<Double,Double>();
    	StatDataRowGenerator<Double,Double> gsdtr = new StatDataRowGenerator<Double,Double>();
    	int status = 1;
    	long start, end;
    	gsdt.setLengthOfLists(5);
    	gsdt.setNumOfLists(2);
    	gsdt.setNumOfDigits(3);
    	
    	try {
    		start = System.currentTimeMillis();
    		System.out.println("Starting at:" + start);
    		
 
    		dataTables = (List)gsdt.generateRandomDataTables();
    		
    		
    		assertTrue(dataTables.size()>0);
    		
    		for(int i=0;i<dataTables.size();i++) {
    			StatDataTable<Double,Double> sdt = dataTables.get(i);
    			System.out.println("Table #: " + i + " of type:"+ sdt.getType());
    			try {
    				sdt.setMomentum(2);
    				status = sdt.computeStats();
    				System.out.println("Computed stats: " + status);
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    			System.out.println("");
    			List<Double> values = sdt.getDataTableValues();
    			System.out.println("Table values:");
    			
    			for(int j=0;j<values.size();j++) {
    				System.out.print(" " + values.get(j).toString() + " ");
    			}
    			System.out.println("-------------------------------------");
    			System.out.println("Table Rows:");
    		    for(StatDataRow row: sdt.getDataTable().values()) {
    		    	System.out.println("Id:" + row.getId() + " Value: "+ row.getValue() + " AbsoluteFreq: " + row.getAbsoluteFreq() +
    		    			" RelativeFreq: " + row.getRelativeFreq() + " CumulativeAbsFreq: " + row.getCumulativeFreq() + " CumulativeRelativeFreq : "+ row.getCumulativeRelativeFreq());
    		    } 	
    			System.out.println("---------------------------------------");
    			System.out.println("Table Statistics:");
    			System.out.println(" Median:" + sdt.getMedian());
    			System.out.println(" Arith. Mean:" + sdt.getArithMean());
    			System.out.println(" Harmonic. Mean:" + sdt.getHarmMean());
    			System.out.println(" Geometric. Mean:" + sdt.getGeomMean());
    			System.out.println("***********************************");
    			
    			
    			
    			
    		}
    		end = System.currentTimeMillis();
    		System.out.println("Ended at:" + end);
    		
    		System.out.println("Total seconds : " + (end-start)/1000);
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
 
    }
}
