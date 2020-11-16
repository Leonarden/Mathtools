package com.tools.stats;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatDataExperiment {
	
	private static Logger log = LogManager.getLogger(StatDataExperiment.class.getCanonicalName());
	
	/*Provides methods to generate data from:
	 * a) A csv text file containing double values
	 * b) Data Randomly generated
	 */
     private GenerateStatData generator;
	/* code corresponding to the experiment*/
	private String code;
	private List<StatDataTable> dataTables;
	private Date lastAccessed;
	
	
	
	
}
