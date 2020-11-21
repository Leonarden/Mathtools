package com.tools.stats.compute;

import java.util.List;
/**
 * Strategy pattern
 */
public abstract class AbstractMath {
	
	
	public abstract Double computeMean(List<Double> data) throws Exception;
	
	public abstract Double computeHarmonicMean(List<Double> data) throws Exception;
	
	public abstract Double computeSTDev(List<Double> data) throws Exception;
	
	public abstract List<Double> normalize(List<Double> data) throws Exception;
	
	
	
}
