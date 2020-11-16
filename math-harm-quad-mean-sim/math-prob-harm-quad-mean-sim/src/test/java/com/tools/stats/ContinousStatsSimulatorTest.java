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
         List<StatDataTable<Double,String>> dataTablesList = null;
    	
    	  GenerateStatData<Double> gsd = new GenerateStatData<Double>();
    	
    	
    	gsd.setLengthOfLists(10);
    	gsd.setNumOfLists(1);
    	gsd.setNumOfDigits(2);
    	
    	try {
    		gsd.generateDataTablesList();
 
    		dataTablesList = ( List ) gsd.getDataTableList();
    		
    		
    		assertTrue(dataTablesList.size()>0);
    		
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
 
    }
}
