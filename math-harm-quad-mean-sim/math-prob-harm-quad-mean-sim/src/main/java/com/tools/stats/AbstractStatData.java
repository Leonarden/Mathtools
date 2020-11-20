package com.tools.stats;

import java.util.Date;

/*
 * This is the root class for model classes
 */
public abstract class AbstractStatData<N extends Number,T> {
	
	/**/
	String id;
	boolean isComputed = false;
	Date lastAccessed;

	
	
	
	
	public abstract int computeStats() throws Exception;





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public boolean isComputed() {
		return isComputed;
	}





	public void setComputed(boolean isComputed) {
		this.isComputed = isComputed;
	}





	public Date getLastAccessed() {
		return lastAccessed;
	}





	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	//Add shared Collection methods
	
	
}
