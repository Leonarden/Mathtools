package com.tools.stats;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tools.stats.generator.StatDataTableGenerator;
import com.tools.stats.util.StatDataRowType;
import com.tools.stats.util.StatDataTableType;

public class StatDataExperiment<N extends Number,T> extends AbstractStatData<N,T> {
	
	private static Logger log = LogManager.getLogger(StatDataExperiment.class.getCanonicalName());
	
	
	 private StatDataTableGenerator<N,T> generator;
	/* code for the experiment*/
	private String code;
	private List<StatDataTable<N,T>> dataTables;
	
	
	public StatDataExperiment() {
		dataTables = new LinkedList<StatDataTable<N,T>>();
	}
	
	public StatDataTableGenerator<N,T> getGenerator() {
		return generator;
	}
	public void setGenerator(StatDataTableGenerator<N,T> generator) {
		this.generator = generator;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<StatDataTable<N, T>> getDataTables() {
		return dataTables;
	}
	public void setDataTables(List<StatDataTable<N, T>> dataTables) {
		this.dataTables = dataTables;
	}
	
	/**
	 * Generates random Tables, 
	 */
	public int generateRandomDataTables() throws Exception {
		int status = 0;
		try {
			
			for(StatDataTable dataTable:this.generator.generateRandomDataTables())
				this.dataTables.add(dataTable);
		
		}catch(Exception e) {
			log.debug("Exception ocurred in statDataExperiment-generateRandomDataTables :" + e.getLocalizedMessage());
			status = -1;
		}
		
		return status;
	}
	
	/**
	 * Generates random Tables, 
	 */
	public int generateRandomDataTables(List<String> ids, List<StatDataTableType> types) throws Exception {
		int status = 0;
		try {
			
			for(StatDataTable dataTable:this.generator.generateRandomDataTables(ids,types))
				this.dataTables.add(dataTable);
		
		}catch(Exception e) {
			log.debug("Exception ocurred in statDataExperiment-generateRandomDataTables :" + e.getLocalizedMessage());
			status = -1;
		}
		
		return status;
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public StatDataTable<N,T> getStatDataTable(int index){
		StatDataTable dtable = null;
		try {
		
			dtable = this.dataTables.get(index);
		
		}catch(Exception e) {
			log.debug("GetDataTable by index exception" + e.getLocalizedMessage());
			dtable = null;
		}
		return dtable;
	}
	public StatDataTable<N,T> getStatDataTable(String ID){
		StatDataTable dt = null;
		try {
		for(StatDataTable t:this.dataTables)
			if(t.getId().equalsIgnoreCase(ID)) {
				dt = t;
				break;
			}
		}catch(Exception ex) {
			log.debug("GetDataTable by ID exception" + ex.getLocalizedMessage());
			dt = null;
		}
		
		return dt;
	}
	
	public int addStatDataTable(StatDataTable<N,T> sdt) {
		int s = 0;
		try {
			this.dataTables.add(sdt);
		
			log.debug("Adding sample of type:" + sdt.getType() + " Total samples:" + dataTables.size());
			
			s=1;
		}catch(Exception e) {
			log.debug("Exception while adding data table : " + e.getLocalizedMessage());
			s=0;
		}
		return s;
	}
	/**
	 * Precondition: 
	 * Each table has its statistics computed, 
	 * algorith will contrast N source tables to the
	 *  M contrast tables by creating new Normalized tables with composed ids that will be returned (in total NxM tables)
	 * @param sources
	 * @param contrast
	 * @return List of tableIds of the new created tables
	 * @throws Exception
	 */
	public List<String> contrastTables(List<StatDataTable<N, T>> sources, List<StatDataTable<N,T>> contrast)throws Exception {
		List<String> contIds = new LinkedList<String>();
		Double linearDelta = 0.0;
		String contId = "";
		StatDataTable result = null;
		int status=0;
		try {
		for(int i=0;i<sources.size();i++) {
			StatDataTable dtsource = sources.get(i);
			Double mean = dtsource.getArithMean();
			for(int j=0;j<contrast.size();j++) {
				StatDataTable dtcon = contrast.get(j);
				if(dtsource.getId().equalsIgnoreCase(dtcon.getId())) {
				log.debug("Table source and contrast have same Id:"+ dtcon.getId());
					continue;
				}
				Double meancont = dtcon.getArithMean();
				linearDelta = Math.abs(mean - meancont); //abs?
				List<Double> valuescontrast = dtcon.getDataTableValues();
				//we add the new value
				valuescontrast.add(linearDelta);
				//copy values to a new table
				result = new StatDataTable<N,T>();
				
				result.setDataTableValues(valuescontrast);
				StatDataRow row = result.getDataTableRow(linearDelta);
				row.setRtype(StatDataRowType.CONTRAST);
				//see if error now
				result.addDataTableRow(linearDelta, row);
				//setting composed id-
				contId = dtsource.getId()+ "-CONTRAST-"+ dtcon.getId();
				
				result.setId(contId);
				status = result.computeStats();
				log.debug("Contrast tables, computedstats for generated table with status: " + status);
					
				this.addStatDataTable(result);
				//table normalization
				
				
			 status =	this.normalizeDataTables(Arrays.asList(result.getId()));
			if(status>0)	
			log.debug("Normalized transformed data linearDelta: " + linearDelta);
				
			
			
			contIds.add(contId);
			
			}
			
			
			
		}
	
		status = 1;
		
		}catch(Exception ex) {
			log.debug("ContrasTables exception ->"+ ex.getLocalizedMessage());
			ex.printStackTrace();
			contIds = null;
		}
		
		return contIds;
		
	}
	
	
	/**
	 * 
	 * @param ids
	 * @param tid
	 * @param type
	 * @return
	 */
	public int createFromDataTables(List<String> ids,String tid, StatDataTableType type) {
		int status = -1;
		List<StatDataTable> tfrom = new LinkedList<StatDataTable>();
		StatDataTable dt = null;
		try {
			for(String d:ids) {
				dt = getStatDataTable(d);
				if(dt!=null)
					tfrom.add(dt);
			}
			
			if(tfrom.size()<=0)
				throw new Exception("No input dataTables for create");
			
			dt  = generator.createFromDataTables(tfrom, tid, type);
			
			this.dataTables.add(dt);
			
			status = 0;
		}catch(Exception ex) {
			log.debug("Exception createFromDataTables :"+ ex.getLocalizedMessage());
			
		}
		
		return status;
	}
	/**
	 * Generates StatDataTables from CSV file @see CSV format
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	
	public int generateDataTablesFromFile(String filename) throws Exception{
		int ncreated = 0;
		N type = null;
		try {
			if(filename.endsWith(".csv")) {
				
		        for(StatDataTable dt:generator.generateDataTablesFromCSV(filename, type)) {
		        	this.dataTables.add(dt);
		        	ncreated++;
		        }
			}else 
				throw new Exception("File format not supported, must be CSV");
			
			
		}catch(Exception ex) {
			log.debug("Generating data tables from file exception:" + ex.getLocalizedMessage());
			ex.printStackTrace();
			ncreated = -1;
		}
		
		return ncreated;
	}
	/**
	 * 
	 * @param indexes
	 * @return
	 * @throws Exception
	 */
	public int normalizeDataTables(List<?> indexes) throws Exception {
		StatDataTable<N,T> ndt = null;
		String tId;
		int cted = 0;
		try {
		for(Object i:indexes) {
			if(i instanceof Integer)
				ndt = this.dataTables.get((Integer)i);
			else if(i instanceof String)
				ndt = this.getStatDataTable((String) i);
			else
				throw new Exception("Table not found");
			
			tId = ndt.getId();
			
			ndt = ndt.createNormalized();
			tId = tId + "-NORMALIZED";
			ndt.setType(StatDataTableType.NORMALIZED);
			ndt.setId(tId);
			
			this.dataTables.add(ndt);
			
			cted++;
		}
		
		
		}catch(Exception ex) {
			log.debug("Exception normalizing tables :" + ex.getLocalizedMessage());
			ex.printStackTrace();
			cted=-1;
		}
		
		return cted;
		
	}
	
	/**
	 * 
	 */
	public int computeStatDataTable(int index) throws Exception{
		int status = 0;
		status =  this.dataTables.get(index).computeStats();
	
		return status;
	}
   /**
    * 
    * @param momentum
    */
	
	public void setAllStatDataTableMomentum(int momentum) {
		try {
			for(StatDataTable sdt: this.dataTables) {
				sdt.setMomentum(momentum);
			}
		}catch(Exception e) {
			log.debug("Exception while setting momentum for all tables "+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param pos
	 * @param momentum
	 */
	public void setStatDataTableMomentum(int pos, int momentum) {
		try {
			this.dataTables.get(pos).setMomentum(momentum);
			
		}catch(Exception e) {
			log.debug("Exception while setting momentum for table at index:"+ pos +"  "+ e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	/**
    * Computes all tables
    */
	public int computeStats() throws Exception {
		int status = 0;
		for(int i=0;i<this.dataTables.size();i++)
			status = status + computeStatDataTable(i);
		return status;
	}
	
	
	
	
	
}
