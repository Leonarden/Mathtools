package com.tools.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import com.tools.stats.compute.AbstractMath;
import com.tools.stats.compute.GeometricMath;
import com.tools.stats.compute.StandardMath;
/**
 * 
 * @author david
 *
 *A StatDataTable  represents a Sample from a Population
 *Sample.type = "Control"; or "Target"; or "Randomized" for those
 *randomly created  mixing randomly "control" and "target"
 *This last process will allow us to determine if the values obtained
 * were due to luck or are genuine and valid.
 *
 */
public class StatDataTable<N extends Number,T> {
    
	/*Type of sample*/
	private String type = "";
	/* will contain the table of frequencies */
	private SortedMap<N,StatDataTableRow<N,T>> dataTable;
	/* min difference between to values that will become key*/
	private  Double deltaError = 0.001;
	/* Momentum for geometric Mean */
	private int momentum = 2;
	/* Flag that indicates if table has generated its statistics */
	private boolean statistics = false;
	
	/*Arithmetic mean*/
	private Double arithMean;
	/*Median */
	private Double median;
	/*Harmonic mean */
	private Double harmMean;
	/*  geometric mean  */
    private Double geomMean = 0.0;
	
	
    
	/**/
	private AbstractMath statCalc = null;
	
	public StatDataTable() {
	
		dataTable = new TreeMap<N,StatDataTableRow<N,T>>();
	}


	
	
	
	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public SortedMap<N, StatDataTableRow<N,T>> getDataTable() {
		return dataTable;
	}





	public void setDataTable(SortedMap<N, StatDataTableRow<N,T>> dataTable) {
		this.dataTable = dataTable;
	}





	public Double getDeltaError() {
		return deltaError;
	}





	public void setDeltaError(Double deltaError) {
		this.deltaError = deltaError;
	}





	public boolean isStatistics() {
		return statistics;
	}





	public void setStatistics(boolean statistics) {
		this.statistics = statistics;
	}






	/**
	 * Computes all statistic parameters, aMean, Quadratic mean,.
	 */


	public int computeStats() throws Exception {
		List<N> dataSet = null;
        try {
    	   	
    		dataSet = this.getDataTableValues();
    		this.median = this.computeMedian(dataSet);
    		//Arithmetic mean
    		statCalc = new StandardMath();
    		this.arithMean = statCalc.computeMean((List<Double>) dataSet);
    		//Harmonic mean
    		this.harmMean = statCalc.computeHarmonicMean((List<Double>) dataSet);
    		//Geometric mean
    		
    		statCalc = new GeometricMath(this.momentum); 
    		this.geomMean = statCalc.computeMean((List<Double>) dataSet);
    		/*
    		dataSet = new ArrayList<Double>();
    		dataSet.add(mH); dataSet.add(mG);
    		
    		mXGH =  stdMath.computeMean(dataSet);
    	   */
    		statistics = true;
    		return 0;
        }catch(Exception ex) {
        	ex.printStackTrace();
    
        }
		return 1;   
	
	}
	
	/*
	 * 
	 */
	private Double computeMedian(List<N> dataSet) throws Exception {
 		//median
		Double d = null;
		double arr[] = new double[dataSet.size()];
		for(int i=0;i<dataSet.size();i++) {
			d = (Double) dataSet.get(i);
			arr[i] = d.doubleValue();
		}
		Median median = new Median();
		
		return median.evaluate(arr);

	}
	
	/**
	 * 
	 * @return List of key numbers that correspond to the original numeric input sample 
	 * 
	 * @throws Exception
	 */
	public List<N> getDataTableValues() throws Exception{
		List<N> dtList = new LinkedList<N>();
		int cnt = 0;
		for(N d:dataTable.keySet()) {
			StatDataTableRow sdr = dataTable.get(d);
			
			for(int j =0;j<sdr.getAbsoluteFreq();j++) { //Absolute freq must at leat be 1 or higher
				dtList.add(cnt,d); 
				cnt++;
			}
			
		}
		
		return dtList;
		
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public StatDataTableRow<N,T> getDataTableRow(N value)  {
		
		StatDataTableRow<N,T> sdr =this.dataTable.get(value);
		//check if value is inside record sdr.getValue()==value
		return sdr;
	}
	
	
/**
 * 
 * @param values
 * @throws Exception
 */
	public void setDataTableValues(List<?> values) throws Exception {
		N d;
		for(int i=0;i<values.size();i++) {
			d = (N)values.get(i);
			setDataTableValue(d);
		}
		
	}
/**
 * 
 * @param d
 */
public void setDataTableValue(N d) {
	StatDataTableRow<N,T> sdr = null;
   	Double nme = 0.0; //number minus tolerated error
	boolean isNk = false; //is new key
	
	sdr = dataTable.get(d);
	
	if(sdr==null) {
	    if(d.doubleValue()< 0)
		nme = Math.abs(d.doubleValue() + deltaError.doubleValue());
	    else if(d.doubleValue()>0)
	    nme = Math.abs(d.doubleValue() - deltaError.doubleValue());
	
	    sdr = dataTable.get(nme);

	    if(sdr==null) {
		isNk = true;
		//sdr.setIndex(i); // review order because values can be not sorted
		sdr = new StatDataTableRow();
		sdr.setAbsoluteFreq(1);
		sdr.setValue(d);
		
	    }else {
	    	d = (N) nme;
	    }
	}
	
	if(!isNk) {

		Integer af = sdr.getAbsoluteFreq();
		af = af +1;
		sdr.setAbsoluteFreq(af);
		//sdr.setIndex(i);
	
	}
	
    dataTable.put(d, sdr);
	
}
	






}
