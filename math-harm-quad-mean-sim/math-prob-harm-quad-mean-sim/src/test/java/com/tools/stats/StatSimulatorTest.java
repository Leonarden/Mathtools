package com.tools.stats;

import java.util.LinkedList;
import java.util.List;

import com.tools.stats.GenerateStatData;
import com.tools.stats.StatDataTable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StatSimulatorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StatSimulatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StatSimulatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
         List<StatDataTable<Double,String>> dataTablesList = null;
    	
    	GenerateStatData<Double> gsd = new GenerateStatData<Double>();
    	int status = 1;
    	long start, end;
    	gsd.setLengthOfLists(5);
    	gsd.setNumOfLists(2);
    	gsd.setNumOfDigits(3);
    	
    	try {
    		start = System.currentTimeMillis();
    		System.out.println("Starting at:" + start);
    		gsd.generateRandomDataTablesList();
 
    		dataTablesList = ( List ) gsd.getDataTableList();
    		
    		
    		assertTrue(dataTablesList.size()>0);
    		
    		for(int i=0;i<dataTablesList.size();i++) {
    			StatDataTable<Double,String> sdt = dataTablesList.get(i);
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
    			System.out.println("");
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
