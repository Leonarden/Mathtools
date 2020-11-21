package com.tools.stats.compute;

import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.*;
/**
 * 
 * @author david
 *
 *
 *GeometricMath:
 *
 *
 *
 */
public class GeometricMath extends AbstractMath{
private double pow;
	public GeometricMath(double pow) {
		this.pow = pow;
		//or momentum
	}
	@Override
	public Double computeMean(List<Double> data) throws Exception {
	
		double[] vals = null;
		Double mresult = null;
		double cvalue = 0.0;
		try {
			vals = new double[data.size()];
			if(pow<1d)
				throw new Exception("Incorrect POW value (must be >=1 : "+ pow );
			for(int i=0;i<data.size();i++) {
				
    			vals[i] = data.get(i).doubleValue();
				cvalue = cvalue + Math.pow( vals[i],this.pow);
					
			}
			if(data.size()>0) {
			cvalue = cvalue /( data.size() );
			cvalue = Math.pow(cvalue, (1/pow));
			
			mresult = Double.valueOf(cvalue);
			}
			/*
			gMean.setData(vals);
			gMean.evaluate();
			mresult = Double.valueOf(gMean.getResult());
			*/
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return mresult;
		
	}

	
	
	@Override
	public Double computeHarmonicMean(List<Double> data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Double computeSTDev(List<Double> data) throws Exception {
		double[] vals = null;
		Double mresult = null;
		double cvalue = 0.0;
		Double mean = 0.0;
		double item = 0.0;
		try {
			vals = new double[data.size()];
			if(pow<1d)
				throw new Exception("Incorrect POW value (must be >=1 : "+ pow );
			mean = this.computeMean(data);
			
			for(int i=0;i<data.size();i++) {
				
    			vals[i] = data.get(i).doubleValue();
				item = (vals[i]-mean);
    			cvalue = cvalue + Math.pow( item,this.pow);
					
			}
			if(data.size()>0) {
			cvalue = cvalue /( data.size() );
			cvalue = Math.pow(cvalue, (1/pow));
			
			mresult = Double.valueOf(cvalue);
			}
			/*
			gMean.setData(vals);
			gMean.evaluate();
			mresult = Double.valueOf(gMean.getResult());
			*/
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return mresult;
		
	}
	@Override
	public List<Double> normalize(List<Double> data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
