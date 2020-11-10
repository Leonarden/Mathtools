package com.tools.probability;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author david
 *
 *We want to represent a row associated to a "key" in a 
 *HashMap that will contain the value as 
 *
 */
public class StatDataTableRow<N extends Number,T> {
	private Integer index = 0;
	/* if it's necessary store value, so that we will have key==value */
	private N value = null;
	/* number of elements contained in this part of sample and associated to key-value
	 * it must be equal to rowSample.size()*/
	private Integer absoluteFreq = 0;
	/* We give the option  to include the part of the sample 
	 * corresponding to this key or value*/
	private List<StatDataUnit<N,T>> rowSample = new LinkedList<StatDataUnit<N,T>>();
	
	
	/* number of times that value is repeated / by total number of cases= Sum absoluteFreq of all rows */
	private Double relativeFreq = 0.0;
	
	/* product of value(i)*relativeFreq(i)
	 * 
	 */
	private Double vXrelativeFreq = 0.0;
	/*
	 * product of value(i)pow2*relativeFreq(i)
	 * 
	 */
	private Double v2XrelativeFreq = 0.0;
	
	/* sum of absoluteFreq from 0 to currentRow */
	private  Integer accFreq = 0;
	
	/* sum of relativeFreq from 0 to currentRow */
	private Double accRelativeFreq = 0.0;
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getAbsoluteFreq() {
		return absoluteFreq;
	}
	public void setAbsoluteFreq(Integer absoluteFreq) {
		this.absoluteFreq = absoluteFreq;
	}
	public Double getRelativeFreq() {
		return relativeFreq;
	}
	public void setRelativeFreq(Double relativeFreq) {
		this.relativeFreq = relativeFreq;
	}
	public Integer getAccFreq() {
		return accFreq;
	}
	public void setAccFreq(Integer accFreq) {
		this.accFreq = accFreq;
	}
	public Double getAccRelativeFreq() {
		return accRelativeFreq;
	}
	public void setAccRelativeFreq(Double accRelativeFreq) {
		this.accRelativeFreq = accRelativeFreq;
	}
	public N getValue() {
		return value;
	}
	public void setValue(N value) {
		this.value = value;
	}
	public List<StatDataUnit<N, T>> getRowSample() {
		return rowSample;
	}
	public void setRowSample(List<StatDataUnit<N, T>> rowSample) {
		this.rowSample = rowSample;
	}
	public Double getvXrelativeFreq() {
		return vXrelativeFreq;
	}
	public void setvXrelativeFreq(Double vXrelativeFreq) {
		this.vXrelativeFreq = vXrelativeFreq;
	}
	public Double getV2XrelativeFreq() {
		return v2XrelativeFreq;
	}
	public void setV2XrelativeFreq(Double v2XrelativeFreq) {
		this.v2XrelativeFreq = v2XrelativeFreq;
	}


	
	
	
	
}
