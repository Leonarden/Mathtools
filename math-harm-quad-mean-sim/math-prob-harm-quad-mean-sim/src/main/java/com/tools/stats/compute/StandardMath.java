package com.tools.stats.compute;

import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.*;
import org.apache.commons.math3.stat.descriptive.rank.Median;
public class StandardMath extends AbstractMath {

	@Override
	public Double computeMean(List<Double> data) throws Exception {
		Mean mean = new Mean();
		double[] vals = null;
		Double mresult = null;
		try {
			vals = new double[data.size()];
			for(int i=0;i<data.size();i++) {
				vals[i] = data.get(i).doubleValue();
			}
			mean.setData(vals);
			mresult = mean.evaluate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return mresult;
	
	}

	@Override
	public Double computeHarmonicMean(List<Double> data) throws Exception {
	     Double hmresult = null;
	     int Num = 0;
	     int k = 0;
	     // this will store 1/ni values
	     double[] vals = new double[data.size()];
	     try {
	    	 for(int i = 0;i<data.size();i++) {
	    		 double v = data.get(i);
	    		 if(v!=0) {
	    			 vals[k] = (1/v);
	    			 k++;
	    			 Num = k;
	    		 }
	    	 }
	     //Harmonic mean: N/1/n1 + 1/n2....
	       double hm = 0.0;
	       for(int i=0;i<vals.length;i++)
	    	   hm = hm + vals[i];
	       hm = (Num / hm);
	       hmresult = Double.valueOf(hm);
	     }catch(Exception ex) {
	    	 ex.printStackTrace();
	     }
		return hmresult;
	}
	public Double computeMedian(List dataSet) throws Exception {
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
}
