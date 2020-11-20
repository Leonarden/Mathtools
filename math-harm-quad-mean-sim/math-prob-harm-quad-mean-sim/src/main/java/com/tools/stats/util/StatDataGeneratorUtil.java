package com.tools.stats.util;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tools.stats.StatDataTable;
import com.tools.stats.generator.StatDataTableGenerator;

public class StatDataGeneratorUtil<N extends Number> {
	public static Logger log = LogManager.getLogger(StatDataGeneratorUtil.class.getCanonicalName());
	
	
	public  int computeMaxLength(List<StatDataTable> dtTables) throws Exception {
		int l = -1;
		for(StatDataTable sdt:dtTables) {
			if(sdt.getDataTable().size()> l)
				l = sdt.getDataTable().size();
		
		}
		return l;
	}
	
	
	public static boolean tokensEmpty(String[]ts) {
		boolean tempty = true;
		for(int i=0;i<ts.length;i++)
			if(!ts[i].isEmpty()) {
				tempty = false;
				break;
				}
		return tempty;
	}
	
	public  N readInputNumber(String t) {
		N val = null;
		try { 
			Double d = Double.valueOf(t);
			val = (N) d;
		}catch(Exception ex ) {
			log.debug("Double not accepted");
			try {
				Float f = Float.valueOf(t);
				val = (N) f;
			}catch(Exception ex1) {
				log.debug("Float not accepted");		
				val = null;
			}
		}
		
		return val;
	}
	
	public String readTableId(String t) {
		
		String id = null;
		if(t.lastIndexOf("ID:")>=0) {
			id = t.substring(t.lastIndexOf("ID:")+"ID:".length(), t.length());
		}
		
		return id;
		
	}
	
	public StatDataTableType readTableType(String t) {
		StatDataTableType type = null;
		
		if(t.contains("TARGET"))
			type = StatDataTableType.TARGET;
		
		else if(t.contains("CONTROL"))
			type = StatDataTableType.CONTROL;
			
		else if(t.contains("TEMP"))
			type = StatDataTableType.TEMPORARY;
		
		
		return type;
	}
	/**
	 * 
	 */
	public String generateRandomId(int prefix,String separator) {
		Random rnd = new Random(prefix);
		String c = "" + prefix +separator;
		long l = Math.abs(rnd.nextLong()%100000);
		c = c +l;
		return c;
	}
	


}
