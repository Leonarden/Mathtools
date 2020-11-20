package com.tools.stats;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author david
 *
 *We want to represent a row of the table of frequencies associated to the numeric value that is
 *effectively the key in a HashMap Key:0.23 ->DataTableRow->
 *index: if necessary
 *absoluteFreq: Number of times that "key" appears repeated into sample
 *rowSample: (optional) Contains the elements of sample that are associated to the key value
 *relativeFreq:
 * 
 * T is the generic type with which we want to calculate statistics
 */
public class StatDataTableRow<N extends Number,T> extends AbstractStatData<N,T>{
	
	private static Logger log = LogManager.getLogger(StatDataTableRow.class.getCanonicalName());
	
	
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
	
	/* sum of absoluteFreq  in the HashMap to currentRow */
	private  Integer accFreq = 0;
	
	/* sum of relativeFreq from first value in the HashMap to currentRow */
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
	@Override
	public int computeStats() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
	
}
