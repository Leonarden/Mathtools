package com.tools.stats;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatDataExperiment<N extends Number,T> {
	
	private static Logger log = LogManager.getLogger(StatDataExperiment.class.getCanonicalName());
	
	 private GenerateStatData<N> generator;
	/* code for the experiment*/
	private String code;
	private List<StatDataTable<N,T>> dataTables;
	private Date lastAccessed;
	
	
	
	
}
