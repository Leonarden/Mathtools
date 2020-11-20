package com.tools.stats;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
public class StatDataRow<N extends Number,T> extends AbstractStatData<N,T>{
	
	private static Logger log = LogManager.getLogger(StatDataRow.class.getCanonicalName());
	
	
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
	private  Integer cumulativeFreq = 0;
	
	/* sum of relativeFreq from first value in the HashMap to currentRow */
	private Double cumulativeRelativeFreq = 0.0;
	
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
	
	public Integer getCumulativeFreq() {
		return cumulativeFreq;
	}
	public void setCumulativeFreq(Integer cumulativeFreq) {
		this.cumulativeFreq = cumulativeFreq;
	}
	public Double getCumulativeRelativeFreq() {
		return cumulativeRelativeFreq;
	}
	public void setCumulativeRelativeFreq(Double cumulativeRelativeFreq) {
		this.cumulativeRelativeFreq = cumulativeRelativeFreq;
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
		Map<N,StatDataRow> dataTable;
		StatDataRow prev=null,next = null;
		try {
		dataTable =   ((StatDataTable) this.getStatData()).getDataTable();
	
	
		this.computeRelativeFrequency();
		
		
		Iterator<StatDataRow> it = dataTable.values().iterator();
		next = it.next();
		while(it.hasNext()) {
		
			if(next==this){
				break;
			}
			next = computeCumulativeStats(prev);
		    prev = next;
			next = it.next();
		}
		return 1;
		}catch(Exception ex) {
			log.debug("Exception computing row statistics computeStats:" + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
			return 0;
	}

	protected int computeRelativeFrequency()throws Exception {
		Map<N,StatDataRow> dataTable;
		StatDataRow row = null;
		Integer sampleSize = 0;
		try {
		dataTable =   ((StatDataTable) this.getStatData()).getDataTable();
		
		Iterator<StatDataRow> it = dataTable.values().iterator();
		while(it.hasNext()) {
		    row = it.next();
	
			sampleSize = sampleSize + row.getAbsoluteFreq(); 
			
		}
		double rfd = (double) (absoluteFreq / sampleSize);
		Double rf = Double.valueOf(rfd);
		
		this.setRelativeFreq(rf);
		return 0;
		}catch(Exception ex) {
			log.debug("Exception computing relative frequency: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		
		}	
		return 1;
	}

	protected StatDataRow computeCumulativeStats(StatDataRow previous) throws Exception {
		try {
			if(previous!=null) {
				
				previous.setCumulativeFreq(this.getAbsoluteFreq()+ previous.getCumulativeFreq());
				previous.setCumulativeRelativeFreq(this.getRelativeFreq() + previous.getCumulativeRelativeFreq());;
			
			}
			
		}catch(Exception ex) {
			log.debug("Exception computing cumulative stats:"+ ex.getLocalizedMessage());
			previous = null;
			ex.printStackTrace();
		}
		return previous;
	}
	
	
	
	
	
}
