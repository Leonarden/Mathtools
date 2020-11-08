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
public class StatDataTable {
    
	/*Type of sample*/
	private String type = "";
	/* will contain the table of frequencies */
	private SortedMap<Double,StatDataTableRow> dataTable;
	/* min difference between to values that will become key*/
	private  Double deltaError;
    /*Arithmetic mean*/
	private Double aMean;
	
	public StatDataTable() {
		deltaError = 0.00199999;
		dataTable = new TreeMap<Double,StatDataTableRow>();
	}


	
	
	
	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public SortedMap<Double, StatDataTableRow> getDataTable() {
		return dataTable;
	}





	public void setDataTable(SortedMap<Double, StatDataTableRow> dataTable) {
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
	public List<Double> getDataTableValues() throws Exception{
		List<Double> dtList = new LinkedList<Double>();
		int cnt = 0;
		for(Double d:dataTable.keySet()) {
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
	public StatDataTableRow getDataTableRow(Double value)  {
		
		StatDataTableRow sdr =this.dataTable.get(value);
		//check if value is inside record sdr.getValue()==value
		return sdr;
	}
	
	
/**
 * 
 * @param values
 * @throws Exception
 */
	public void setDataTableValues(List<Double> values) throws Exception {
		Double d = 0.0;
		for(int i=0;i<values.size();i++) {
			d = (Double)values.get(i);
			setDataTableValue(d);
		}
		
	}
/**
 * 
 * @param d
 */
public void setDataTableValue(Double d) {
	StatDataTableRow sdr = null;
   	Double nme = 0.0; //number minus tolerated error
	boolean isNk = false; //is new key
	
	sdr = dataTable.get(d);
	
	if(sdr==null) {
	    if(d<0)
		nme = (d + deltaError);
	    else if(d>0)
	    nme = (d - deltaError);
	
	    sdr = dataTable.get(nme);

	    if(sdr==null) {
		isNk = true;
		//sdr.setIndex(i); // review order because values can be not sorted
		sdr = new StatDataTableRow();
		sdr.setAbsoluteFreq(1);
		sdr.setValue(d);
		
	    }else {
	    	d = nme;
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
