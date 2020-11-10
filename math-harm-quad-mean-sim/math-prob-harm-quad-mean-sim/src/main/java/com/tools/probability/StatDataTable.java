package com.tools.probability;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * 
 * @author david
 *
 *A StatDataTable  represents a Sample from a Population
 *Sample.type = "Control"; or "Target"; or "Randomi" for those
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
    
	private Double sumiValxFrequRel = 0.0;
    
    private Double sumiValpow2xFrequRel = 0.0;
	
	
	
	/*Arithmetic mean*/
	private Double aMean;
	
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
