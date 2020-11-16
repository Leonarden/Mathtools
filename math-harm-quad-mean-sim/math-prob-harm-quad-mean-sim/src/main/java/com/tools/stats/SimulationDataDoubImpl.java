package com.tools.stats;

import java.util.List;

public class SimulationDataDoubImpl implements SimulationData<Double,Double> {
private List<Double> values;
private Double aritMean;
private Double harmonicMean;
private Double quadraticMean;
private Double median;
private Double aritHarmQuadMean;

	public List<Double> getList() {
		// TODO Auto-generated method stub
		return values;
	}

	public void addToList(Double d) {
		
		values.add(d);
	}

	
	
	public void setList(List<Double> l) {
	
         this.values = l;
		
	}

	public void setAritMean(Double m) {
		
		this.aritMean = m;
	}

	public Double getAritMean() {
		
		
		
		// TODO Auto-generated method stub
		return null;
	
	}

	public void setHarmonicMean(Double hm) {
		// TODO Auto-generated method stub
		
	}

	public Double getHarmonicMean() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double getQuadraticMean() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setQuadraticMean(Double qm) {
		// TODO Auto-generated method stub
		
	}

	public void setMedian(Double med) {
		// TODO Auto-generated method stub
		
	}

	public Double getMedian() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAritHarmQuadMean(Double ahq) {
		// TODO Auto-generated method stub
		
	}

	public Double getAritHarmQuadMean() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
}
