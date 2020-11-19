package com.tools.stats;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatDataExperiment<N extends Number,T> {
	
	private static Logger log = LogManager.getLogger(StatDataExperiment.class.getCanonicalName());
	
	/**/
	private String id;
	
	 private StatDataGenerator<N> generator;
	/* code for the experiment*/
	private String code;
	private List<StatDataTable<N,T>> dataTables;
	private Date lastAccessed;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public StatDataGenerator<N> getGenerator() {
		return generator;
	}
	public void setGenerator(StatDataGenerator<N> generator) {
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
	public Date getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
	/**
	 * Generates random Tables, 
	 */
	public int generateRandomDataTables() throws Exception {
		int status = 0;
		try {
			
			this.generator.generateRandomDataTables();
			this.dataTables = (List) this.generator.getDataTables();
		
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
			
			this.generator.generateRandomDataTables(ids,types);
			this.dataTables = (List) this.generator.getDataTables();
		
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
			
			s=1;
		}catch(Exception e) {
			log.debug("Exception while adding data table : " + e.getLocalizedMessage());
			s=0;
		}
		return s;
	}
	
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
				ncreated = generator.generateDataTablesFromCSV(filename, type);
			    this.dataTables = (List) generator.getDataTables();
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
	 */
	public void computeStatDataTable(int pos) throws Exception{
		
		this.dataTables.get(pos).computeStats();
	
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
	public void computeAllStatDataTable() throws Exception {
		for(int i=0;i<this.dataTables.size();i++)
			computeStatDataTable(i);
	}
	
	
	
	
	
}
