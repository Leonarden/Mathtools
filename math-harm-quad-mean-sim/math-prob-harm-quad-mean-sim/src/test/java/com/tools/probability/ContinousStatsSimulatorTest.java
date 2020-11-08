package com.tools.probability;

import java.util.LinkedList;
import java.util.List;



import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ContinousStatsSimulatorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ContinousStatsSimulatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ContinousStatsSimulatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
         List<StatDataTable> dataTablesList = null;
    	
    	GenerateSimulationData<Double> gsd = new GenerateSimulationData<Double>();
    	gsd.setLengthOfLists(10);
    	gsd.setNumOfLists(1);
    	gsd.setNumOfDigits(2);
    	
    	try {
    		gsd.generateDataTablesList();
 
    		dataTablesList = gsd.getDataTableList();
    		
    		assertTrue(dataTablesList.size()>0);
    		
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
 
    }
}
