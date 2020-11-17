package com.tools.stats;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatDataExperiment<N extends Number,T> {
	
	private static Logger log = LogManager.getLogger(StatDataExperiment.class.getCanonicalName());
	
	 private StatDataGenerator<N> generator;
	/* code for the experiment*/
	private String code;
	private List<StatDataTable<N,T>> dataTables;
	private Date lastAccessed;
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
	 * Generates random Tables, it will be necessary to add
	 */
	public int generateRandomDataTables() throws Exception {
		int status = 0;
		try {
			
			this.generator.generateRandomDataTablesList();
			this.dataTables = (List) this.generator.getDataTableList();
		
		}catch(Exception e) {
			log.debug("Exception ocurred in statDataExperiment :" + e.getLocalizedMessage());
			status = -1;
		}
		
		return status;
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public StatDataTable<N,T> getStatDataTable(int pos){
		
		
		return this.dataTables.get(pos);
		
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
