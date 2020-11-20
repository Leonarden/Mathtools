package com.tools.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import com.tools.stats.StatDataTable;
import com.tools.stats.compute.GeometricMath;
import com.tools.stats.compute.StandardMath;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.math3.stat.descriptive.rank.*;
/**
 * Unit test for simple App.
 */
public class ExperimentMeanTest1 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExperimentMeanTest1( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExperimentMeanTest1.class );
    }

    /**
     * Mean experiment test 1
     */
    public void testApp()
    {   Double pow = 3d;
    	Double[] sample = {-30d,10d,10d,20d,3d,30d};
    	double[] sampleCpy = {-30d,10d,10d,20d,3d,30d};
    	
    	List<Double> dataSet;
        Double mX,mG,mH,mXGH; //mX=arithmetic, mG=geometric, mH=harmonic     	
    	Double mE; //median
        StandardMath stdMath = new StandardMath();
    	GeometricMath geomMath = new GeometricMath(pow);
        
        try {
    	   	
    		dataSet = Arrays.asList(sample);
    		//median
    		Median median = new Median();
    		mE = median.evaluate(sampleCpy);
    		//Arithmetic mean
    		mX = stdMath.computeMean(dataSet);
    		//Harmonic mean
    		mH = stdMath.computeHarmonicMean(dataSet);
    		//Geometric mean
    		mG = geomMath.computeMean(dataSet);
    		
    		dataSet = new ArrayList<Double>();
    		dataSet.add(mH); dataSet.add(mG);
    		
    		mXGH =  stdMath.computeMean(dataSet);
    		
    		
    		System.out.println("Different means------");
    		System.out.println("Applied to this sample:");
    		for(int i=0;i<sample.length;i++)
    			System.out.format("  %s  ",   sample[i].toString());
    		
    		System.out.println("Computed means:");
    		System.out.format("Median: %s, Arithmetic: %s , Harmonic %s , "
    				+ "		Geometric %s , Arith from Harm and Geo: %s ",
    				mE.toString(),mX.toString(),mH.toString(),mG.toString(),mXGH.toString());
    		
    		  		
    		
    		
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
   
    		assertTrue(2==4);
    	}
    	
        assertTrue(0==0);
    }
}
