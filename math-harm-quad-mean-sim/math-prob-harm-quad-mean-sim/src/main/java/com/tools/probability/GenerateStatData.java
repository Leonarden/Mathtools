package com.tools.probability;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GenerateStatData<Double> {
	
	private List<StatDataTable> dataTableList;

	private int numOfLists = 0;
	
	private int lengthOfLists = 0;
	
	private int numOfDigits = 0;
	public GenerateStatData() {
		dataTableList = new LinkedList<StatDataTable>();
	}
	
	public List<StatDataTable> getDataTableList(){
		
		return dataTableList;
	}
	
	
	public void scanParameters() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Generating data for simulation---");
		System.out.print("How many arrays of random numbers(1,2,3,4..)");
		numOfLists = scanner.nextInt();
		System.out.println();
	
		System.out.print("Arrays size?(4,5,10...");
		lengthOfLists = scanner.nextInt();
		
		System.out.print("Number of digits?(1,2,3,4,5,..8");
		numOfDigits = scanner.nextInt();
		
		
		if(numOfLists>0 && numOfLists<100 && lengthOfLists>0 && lengthOfLists<400) {
			System.out.println("Generating arrays");
	
		}else {
			System.out.println("Input values were to low or to hight");
		}
	
	}
	
	public void generateDataTablesList() throws Exception{
		List values = null;
		StatDataTable data = null;
		for(int i=0;i<numOfLists;i++) {
			values = new LinkedList<Double>();
			data = new StatDataTable();
			
			values = generateRandValues(values);
			
			
			data.setDataTableValues(values);
			
			dataTableList.add(data);
		}
		
		
		
		
	}
	
	public List<Double> generateRandValues(List vlist){
		
		Integer seed=null;
		String str ="";
		Random random = new Random(1);
		try {
			for(int i=0;i<this.numOfDigits;i++) {
				str = str + Math.abs((random.nextInt()));
				
			}
		
		  seed = Integer.valueOf(str);
			
		}catch(NumberFormatException ife) {
			seed = 10+ (int) Math.abs((random.nextInt()));
		}
		
		random = new Random(seed);
		
		
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
	
	
	
	
	
	
	
	
}
