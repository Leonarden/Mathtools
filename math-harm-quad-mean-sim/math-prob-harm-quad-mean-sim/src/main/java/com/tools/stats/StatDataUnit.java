package com.tools.stats;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatDataUnit<N extends Number,T> extends AbstractStatData<N,T>{
	
private static Logger log = LogManager.getLogger(StatDataUnit.class.getCanonicalName());
	
	/* As working with variables, this value represents  X axis and will be also Key in
  HashMap: Changing of variable will recalculate all StatTable and also this value*/
private N rowKey;
private Integer unitIndex;
private Integer unitWeight;
private Integer totalUnits; //Count total for each key unit
private boolean isComputable;
/* We allow working with more than one variable that will follow order:X,Y,Z...*/
private List<N> variables;



public N getRowKey() {
	return rowKey;
}

public void setRowKey(N rowKey) {
	this.rowKey = rowKey;
}

public Integer getUnitIndex() {
	return unitIndex;
}

public void setUnitIndex(Integer unitIndex) {
	this.unitIndex = unitIndex;
}

public Integer getUnitWeight() {
	return unitWeight;
}

public void setUnitWeight(Integer unitWeight) {
	this.unitWeight = unitWeight;
}

public Integer getTotalUnits() {
	return totalUnits;
}

public void setTotalUnits(Integer totalUnits) {
	this.totalUnits = totalUnits;
}

public boolean isComputable() {
	return isComputable;
}

public void setComputable(boolean isComputable) {
	this.isComputable = isComputable;
}

public List<N> getVariables() {
	return variables;
}

public void setVariables(List<N> variables) {
	this.variables = variables;
}

@Override
public int computeStats() throws Exception {
	// TODO Auto-generated method stub
	return 0;
}





	
	
	
	
}
