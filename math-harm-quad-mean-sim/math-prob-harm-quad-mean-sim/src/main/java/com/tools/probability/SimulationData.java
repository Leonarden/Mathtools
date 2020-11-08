package com.tools.probability;

import java.util.List;

public interface SimulationData<T,D> {

	public List<T> getList();
	public void setList(List<T> l);
	
	public void addToList(T d);
	
	public void setAritMean(D m);
	
	public D getAritMean();
	
	public void setHarmonicMean(D hm);
	
	public D getHarmonicMean();
	
	public D getQuadraticMean();
	
	public void setQuadraticMean(D qm);
	
	public void setMedian(D med);
	
	public D getMedian();
	
	public void setAritHarmQuadMean(D ahq);
	
	public D getAritHarmQuadMean();
	
}
