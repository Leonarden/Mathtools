package com.tools.probability;

/**
 * 
 * @author david
 *
 *We want to represent a row associated to a "key" in a 
 *HashMap that will contain the value as Double
 *
 */
public class StatDataTableRow {
	private Integer index = 0;
	/* if it's necessary store value */
	private Double value = 0.0;
	/* number of times that value is repeated in the sample*/
	private Integer absoluteFreq = 0;
	/* number of times that value is repeated / by total number of cases= Sum absoluteFreq of all rows */
	private Double relativeFreq = 0.0;
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
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
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
	
	

	
	
	
	
}
