package com.tools.stats;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tools.stats.compute.AbstractMath;
import com.tools.stats.compute.GeometricMath;
import com.tools.stats.compute.StandardMath;
import com.tools.stats.generator.StatDataRowGenerator;
import com.tools.stats.util.StatDataGeneratorUtil;
import com.tools.stats.util.StatDataTableType;
import com.tools.stats.util.StatDataUtil;
/**
 * 
 * @author david
 *
 *A StatDataTable  represents a Sample from a Population
 *Sample.type = "Control"; or "Target"; or "Randomized" for those
 *randomly created  mixing randomly "control" and "target"
 *This last process will allow us to determine if the values obtained
 * were due to luck or are genuine and valid.
 *
 */
public class StatDataTable<N extends Number,T> extends AbstractStatData<N,T>{
	
	private static Logger log = LogManager.getLogger(StatDataTable.class.getCanonicalName());
	/*Type of sample*/
	private StatDataTableType type = StatDataTableType.RANDOMIZED;
	/* will contain the table of frequencies */
	private SortedMap<N,StatDataRow<N,T>> dataTable;
	/* min difference between to values that will become key*/
	private  Double deltaError = 0.001;
	/* Momentum for geometric Mean */
	private int momentum = 2;
	
	/*Arithmetic mean*/
	private Double arithMean;
	/*Median */
	private Double median;
	/*Harmonic mean */
	private Double harmMean;
	/*  geometric mean  */
    private Double geomMean = 0.0;
	/**/
    private Double standardDev = 0.0;
	/* */
	private StatDataRowGenerator<N,T> rowGenerator;
	 
	
    
	/* Math computation*/
	private AbstractMath statCalc = null;
	
	public StatDataTable() {
	
		dataTable = new TreeMap<N,StatDataRow<N,T>>();
	}




	public StatDataTableType getType() {
		return type;
	}





	public void setType(StatDataTableType type) {
		this.type = type;
	}





	public SortedMap<N, StatDataRow<N,T>> getDataTable() {
		return dataTable;
	}





	public void setDataTable(SortedMap<N, StatDataRow<N,T>> dataTable) {
		this.dataTable = dataTable;
	}





	public Double getDeltaError() {
		return deltaError;
	}





	public void setDeltaError(Double deltaError) {
		this.deltaError = deltaError;
	}




	public int getMomentum() {
		return momentum;
	}





	public void setMomentum(int momentum) {
		this.momentum = momentum;
	}





	public Double getArithMean() {
		return arithMean;
	}





	public void setArithMean(Double arithMean) {
		this.arithMean = arithMean;
	}





	public Double getMedian() {
		return median;
	}





	public void setMedian(Double median) {
		this.median = median;
	}





	public Double getHarmMean() {
		return harmMean;
	}





	public void setHarmMean(Double harmMean) {
		this.harmMean = harmMean;
	}





	public Double getGeomMean() {
		return geomMean;
	}





	public void setGeomMean(Double geomMean) {
		this.geomMean = geomMean;
	}





	public Double getStandardDev() {
		return standardDev;
	}




	public void setStandardDev(Double standardDev) {
		this.standardDev = standardDev;
	}




	public StatDataRowGenerator<N, T> getRowGenerator() {
		return rowGenerator;
	}




	public void setRowGenerator(StatDataRowGenerator<N, T> rowGenerator) {
		this.rowGenerator = rowGenerator;
	}




	/**
	 * Computes all statistic parameters, aMean, Quadratic mean,.
	 */


	public int computeStats() throws Exception {
		List<N> dataSet = null;
		int status = 0;
        try {
    	   	
    		dataSet = this.getDataTableValues();
    		statCalc = new StandardMath();
    		
    		this.median = ((StandardMath)statCalc).computeMedian(dataSet);
    		//Arithmetic mean
    		this.arithMean = statCalc.computeMean((List<Double>) dataSet);
    		//Harmonic mean
    		this.harmMean = statCalc.computeHarmonicMean((List<Double>) dataSet);
            //Standard deviation
    		if(this.momentum<=2)
    		this.standardDev = statCalc.computeSTDev((List<Double>) dataSet);
    		
    		//Geometric mean
       		statCalc = new GeometricMath(this.momentum); 
    		
       		this.geomMean = statCalc.computeMean((List<Double>) dataSet);
    		if(this.momentum>2)
    			this.standardDev = statCalc.computeSTDev((List<Double>) dataSet);
    	    		
    		/*
    		dataSet = new ArrayList<Double>();
    		dataSet.add(mH); dataSet.add(mG);
    		
    		mXGH =  stdMath.computeMean(dataSet);
    	   */
    		status = this.computeRowStats();
    		isComputed = true;
    		return status;
        }catch(Exception ex) {
        	log.debug("Exception computing table statistics"+ ex.getLocalizedMessage());
        	ex.printStackTrace();
    
        }
		return status;   
	
	}
	/**
	 * 
	 */
	protected int computeRowStats() throws Exception {
		int n = -1;
		List<StatDataRow> dataRows = null;
		try {
		  
			
				this.dataTable.values().stream().forEach(r-> {
					try {
						r.computeStats();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
			n=1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return n;
	}
	
	/**
	 * 
	 */
	public StatDataRow<N,T> getDataTableRow(Double key){
		StatDataRow dtr = null;
		Double dKey = null;
		try {
			if(!dataTable.containsKey(key)) {
					dKey = key-this.deltaError;
					if(dataTable.containsKey(dKey))
						key = dKey;
					else {
						dKey = key+this.deltaError;
						key = dKey;
					}
			
			
					log.debug("Applied deltaError contains Key:" + dataTable.containsKey(key) +" for: " + key);
			}
			
			 dtr = dataTable.get(key);	
				
		}catch(Exception e) {
			dtr = null;
			log.debug("Exception in getDataTableRow: " + e.getLocalizedMessage());
		}
	
		return dtr;
	}
	
	/**
	 * 
	 */
	
	public int addDataTableRow(N key, StatDataRow<N,T> dtrow) {
		int added = 0;
		Double dKey, tk;
		try {
			//we should check deltaError
			tk = Double.valueOf( key.toString());
			dKey = tk - this.deltaError;
			if(this.dataTable.containsKey(dKey)) {
				this.dataTable.put((N)dKey, dtrow);
				added = 1;
				log.debug("Add dataTableRow: Existing - delta Error for key:" + key);
			}else {
				dKey = tk + this.deltaError;
				if(this.dataTable.containsKey(dKey)) {
					this.dataTable.put((N) dKey,dtrow);
					added = 1;
					log.debug("Add dataTableRow: Existing + delta Error for key:" + key);
				}else {
					this.dataTable.put(key, dtrow);
					added = 1;
					log.debug("Add dataTableRow: No delta Error for key:" + key);
				}
			}
		
		}catch(Exception e) {
			log.debug("Exception addingDTableRow:" + e.getLocalizedMessage());
			added = 0;
		}
		
		return added;
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
			StatDataRow sdr = dataTable.get(d);
			
			for(int j =0;j<sdr.getAbsoluteFreq();j++) { //Absolute freq must at least be 1 or higher
				dtList.add(cnt,d); 
				cnt++;
			}
			
		}
		
		return dtList;
		
	}
	public StatDataTable<N,T> createNormalized() throws Exception{
		StatDataTable dataTNorm = null;
		List<N> normvs = null;
		try {
			if(this.momentum<=2) 
			statCalc = new StandardMath();
			else
				statCalc = new GeometricMath(this.momentum);
			
			normvs = statCalc.normalize((List)this.getDataTableValues());
			
			if(normvs.size()>0) {
				dataTNorm = new StatDataTable<N,T>();
				dataTNorm.addDataTableValues(normvs);
			}
			
			
			
		}catch(Exception ex) {
			
			log.debug("StatDataTable exception normalizing table: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		return dataTNorm;
	}

	
	
/**
 * 
 * @param values
 * @throws Exception
 */
	public List<N> addDataTableValues(List<?> values) throws Exception {
		N d;
		List<N> val = new ArrayList<N>();
		for(int i=0;i<values.size();i++) {
			d = (N)values.get(i);
			d = addDataTableValue(d);
			val.add(d);
		}
		return val;
	}
/**
 * 
 * @param d
 */
public N addDataTableValue(N d) {
	StatDataRow<N,T> sdr = null;
	StatDataUtil sdu = new StatDataUtil();
	Double nme = 0.0; //number minus tolerated error
	boolean isNk = false; //is new key
	String rId = "";
	
	try {
		
	
	sdr = dataTable.get(d);
	
	if(sdr==null) {
	    nme = Math.abs(d.doubleValue() + deltaError.doubleValue());
	    if(!this.dataTable.containsKey(nme))
	    nme = Math.abs(d.doubleValue() - deltaError.doubleValue());
	
	    sdr = dataTable.get(nme);

	    if(sdr==null) {
		isNk = true;
		sdr = new StatDataRow();
		rId = sdu.generateRandomId(Double.valueOf(Math.ceil((double)d)).intValue(),"#row@");
		sdr.setId(rId);
		sdr.setStatData(this);
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
	return d;
	}catch(Exception ex) {
		log.debug("Exception adding Value to table: " + ex.getLocalizedMessage());
		ex.printStackTrace();
	}
	return null;
}
	






}
