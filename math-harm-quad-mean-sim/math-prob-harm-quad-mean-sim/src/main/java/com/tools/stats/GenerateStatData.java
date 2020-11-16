package com.tools.stats;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateStatData<N extends Number> {
	

	
	public static Logger log = LogManager.getLogger(GenerateStatData.class.getCanonicalName());
	
	
	private List<StatDataTable> dataTableList;

	/*Random generation parameters*/
	private int numOfLists = 0;
	
	private int lengthOfLists = 0;
	
	private int numOfDigits = 0;
	/*1: indicates that 1 Table will be of type="Sample", the rest type="Randomized"*/
	private int numOfTargetSamples = 1;
	/**/
	/*File generated parameters*/
	private String fileName = "";
	
	public GenerateStatData() {
		dataTableList = new LinkedList<StatDataTable>();
	}
	
	public List<StatDataTable> getDataTableList(){
		
		return dataTableList;
	}
	public void addDataTableList(StatDataTable sdt) {
		try {
			
			dataTableList.add(sdt);
			
			if(sdt.getType().equalsIgnoreCase("Target")) {
				numOfTargetSamples++;
				log.debug("Adding a Target sample, total Target:" + numOfTargetSamples+ " Total samples:" + dataTableList.size());
			}else {
				log.debug("Adding sample of type:" + sdt.getType() + " Total samples:" + dataTableList.size());
			}
			
			
		}catch(Exception ex) {
			log.debug("AddDataTable exception:" + ex.getCause());
			ex.printStackTrace();
		}
	}
	/**
	 * Random generation Parameters Scanned from console
	 */
	
	public void scanParameters() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Generating data for simulation---");
		System.out.println("How many arrays of random numbers(1,2,3,4..)");
		numOfLists = scanner.nextInt();
		System.out.println();
	
		System.out.println("How many Target sample for experiment (1,2,3,4)");
		numOfTargetSamples = scanner.nextInt();
		System.out.println();
	
		System.out.print("Size of the Sample?(4,5,10...");
		lengthOfLists = scanner.nextInt();
		
		System.out.print("?(1,2,3,4,5,..8");
		numOfDigits = scanner.nextInt();
		
		
		if(numOfLists>0 && numOfLists<100 && lengthOfLists>0 && lengthOfLists<400) {
			System.out.println("Generating arrays");
	
		}else {
			System.out.println("Input values were to low or to high");
		}
	
	}
	
	public void generateRandomDataTablesList() throws Exception{
		List values = null;
		StatDataTable data = null;
		for(int i=0;i<numOfLists;i++) {
			values = new LinkedList<Double>();
			data = new StatDataTable();
			data.setType("Randomized");
			values = generateRandomValues(values);
			
			
			data.setDataTableValues(values);
			
			dataTableList.add(data);
		}
		
		
		
		
	}
	
	public List<Double> generateRandomValues(List vlist){
		
		Integer seed;
		String str ="",digit="";
		
		Random random = new Random(10);
		/*
		
		try {
			for(int i=0;i<this.numOfDigits;i++) {
				digit = "" +Math.abs((random.nextInt()));
				str = str + digit.charAt(0);
				
			}
		
		  seed = Integer.valueOf(str);
			
		}catch(NumberFormatException ife) {
			seed = 10+ (int) Math.abs((random.nextInt()));
		}
		*/
		random = new Random(this.numOfDigits);
		
		
		for(int i=0; i< this.lengthOfLists;i++) {
			double sd = random.nextDouble();
			vlist.add(i,sd);
			
			
		}
		
		
		return vlist;
		
		
	}


	public int getNumOfLists() {
		return numOfLists;
	}


	public void setNumOfLists(int numOfLists) {
		this.numOfLists = numOfLists;
	}


	public int getLengthOfLists() {
		return lengthOfLists;
	}


	public void setLengthOfLists(int lengthOfLists) {
		this.lengthOfLists = lengthOfLists;
	}


	public int getNumOfDigits() {
		return numOfDigits;
	}


	public void setNumOfDigits(int numOfDigits) {
		this.numOfDigits = numOfDigits;
	}

	public int getNumOfTargetSamples() {
		return numOfTargetSamples;
	}

	public void setNumOfTargetSamples(int numOfTargetSamples) {
		this.numOfTargetSamples = numOfTargetSamples;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
	
	
	
}