package com.tools.stats.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tools.stats.StatDataTable;
import com.tools.stats.util.StatDataGeneratorUtil;
import com.tools.stats.util.StatDataTableType;

/*Provides methods to generate data from:
 * a) A csv text file containing double values (pending)
 * b) Data Randomly generated
 */

public class StatDataTableGenerator<N extends Number,T> extends StatDataGenerator<N,T>{
	

	
	public static Logger log = LogManager.getLogger(StatDataTableGenerator.class.getCanonicalName());
	
	
	private StatDataRowGenerator<N,T> rowGenerator;
	/*Random generation parameters*/
	private int numOfLists = 0;
	
	private int lengthOfLists = 0;
	
	private int numOfDigits = 0;
	/**/
	/*File generated parameters*/
	private String fileName = "";
	
	
	public StatDataTableGenerator() {
	
	}
	
	/**
	 * Random generation Parameters Scanned from console
	 */
	
	public void scanParameters() {
		Scanner scanner = new Scanner(System.in);
	try {
		System.out.println("Generating data for simulation---");
		System.out.println("How many arrays of random numbers(1,2,3,4..)");
		numOfLists = scanner.nextInt();
		System.out.println();
	
		/*System.out.println("How many Target sample for experiment (1,2,3,4)");
		numOfTargetSamples = scanner.nextInt();
		*/
		System.out.println();
	
		System.out.print("Size of the Sample (4,5,10...)");
		lengthOfLists = scanner.nextInt();
		
		System.out.print("?(1,2,3,4,5,..8");
		numOfDigits = scanner.nextInt();
		
		
		if(numOfLists>0 && numOfLists<100 && lengthOfLists>0 && lengthOfLists<400) {
			System.out.println("Generating arrays");
	
		}else {
			System.out.println("Input values were to low or to high");
		}
	
	  }catch(Exception ex) {
		log.debug("Problem scanning parameters exception: " + ex.getLocalizedMessage());
		ex.printStackTrace();
	  }
	}
	/**
	 * @return A new StatDataTable with values of each input table
	 */
	public StatDataTable createFromDataTables(List<StatDataTable> dataTables,
			String tID,StatDataTableType type) throws Exception {
			StatDataTable nwTable = new StatDataTable();
			StatDataTable tmp = null;
			StatDataGeneratorUtil sdgu = new StatDataGeneratorUtil<N>();
			if(tID==null || tID.isEmpty())
				nwTable.setId(sdgu.generateRandomId(3000,"#"));
			else
				nwTable.setId(tID);
			
			if(type == null)
				nwTable.setType(StatDataTableType.MIXED);
			else
				nwTable.setType(type);
			
				
		int length = sdgu.computeMaxLength(dataTables);
	    
		for(int i=0;i<length;i++) {
			tmp = getRandomTable(dataTables);
			N d = getRandomValueFromTable(tmp);
			nwTable.addDataTableValue(d);
			log.debug("Added numeric value :" + d.toString());
		}
		
		return nwTable;
	}
	
	protected StatDataTable getRandomTable(List<StatDataTable> dtables) {
		Random random = new Random((int)(10000*Math.random()*dtables.size()));
		
		return dtables.get(random.nextInt(dtables.size()));
	}
	/**
	 * 
	 */
	public N getRandomValueFromTable(StatDataTable dataTable) throws Exception {
		List<N> values = dataTable.getDataTableValues();
		int seed = (int) (10000*Math.random()*values.size());
		Random random = new Random(seed);
		
		int index = random.nextInt(values.size());
		
		return values.get(index);
		
	}
	/**
	 * 
	 * @throws Exception
	 */
	public List<StatDataTable> generateRandomDataTables() throws Exception{
		List<StatDataTable> dataTables = new LinkedList<StatDataTable>();
		List values = null;
		StatDataTable data = null;
		StatDataGeneratorUtil sdgu = new StatDataGeneratorUtil<N>();
		for(int i=0;i<numOfLists;i++) {
			values = new LinkedList<Double>();
			data = new StatDataTable();
			data.setType(StatDataTableType.RANDOMIZED);
	        data.setId(sdgu.generateRandomId(i,"#"));
			values = generateRandomValues(values);
			
			
			data.addDataTableValues(values);
			
			dataTables.add(data);
		}
		
		
		return dataTables;
		
	}
	/**
	 * 
	 */
	public List<StatDataTable> generateRandomDataTables(List<String> ids,List<StatDataTableType> types) throws Exception {
		List<StatDataTable> dataTables = new LinkedList<StatDataTable>();
		List values = null;
		StatDataTable data = null;
		int length = 0;
		if(ids==null && types==null)
			dataTables = this.generateRandomDataTables();
		else {
			if(ids.size()<=types.size())
				length = ids.size();
			else
				length = types.size();
		
			for(int i=0;i<length;i++) {
				values = new LinkedList<Double>();
				data = new StatDataTable();
				data.setType(types.get(i));
	        data.setId(ids.get(i));
			values = generateRandomValues(values);
			
			
			data.addDataTableValues(values);
			
			dataTables.add(data);
		
			log.debug("Generated Table #"+ i + " ID:"+ data.getId());
			}
		
		}
		return dataTables;
	}
	
	/**
	 * @see samples.csv for input for file format
	 * 
	 * @return number of tables created
	 */
	public List<StatDataTable> generateDataTablesFromCSV(String csvFileName, N type) throws Exception{
		List<StatDataTable> dataTables = new LinkedList<StatDataTable>();
		StatDataTable dataTable = new StatDataTable();
		BufferedReader breader = null;
		String[]mtokens = null;
		List<N> values = new LinkedList<N>();
		StatDataTableType tType = null;
		N number = null;
		String tId = "";
		StatDataGeneratorUtil sdgu = new StatDataGeneratorUtil<N>();
		String line = "";
		int ncreated = 0;
	    int endTable = 0;
		try {
			breader = new BufferedReader(new FileReader(new File(csvFileName)));
			line = breader.readLine();
			while(line!=null) {
				
				mtokens = line.split(",");
				if(mtokens.length>0 && !sdgu.tokensEmpty(mtokens)) {
					//read many lines
					for(int i=0; i< mtokens.length; i++) {
						if( (number = (N)sdgu.readInputNumber(mtokens[i])) != null)
							values.add(number);
						else if((tId= sdgu.readTableId(mtokens[i]))!=null) {
							
							endTable++;
							log.debug("Table ID readed from file, value: " + tId);
						
						}
						else {
							tType= sdgu.readTableType(mtokens[i]);
							
							endTable++;
							log.debug("Table Type readed from file, value: " + tType);
					        
						}
							
							
					}
		
				}
				if(endTable>=1 && values.size()>0) {
						if(tId==null || tId.isEmpty())
							tId = sdgu.generateRandomId(4000,"#");
						if(tType==null)
							tType = StatDataTableType.CONTROL;
						dataTable.setId(tId);
						dataTable.setType(tType);
						dataTable.addDataTableValues(values);
						dataTables.add(dataTable);
					    ncreated++;
					    endTable = 0;
				    	dataTable = new StatDataTable();
					    values = new LinkedList<N>();

				}
				
				line = breader.readLine();
				
				
			}
			
		}catch(IOException ioe) {
			log.debug("IOException reading CSV file: " + ioe.getLocalizedMessage());
			ioe.printStackTrace();
		}catch(Exception ex) {
			log.debug("Exception  reading CSV file: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}finally {
			breader.close();
		}
	
		return dataTables;
	}

	
	/**
	 * 
	 * @param vlist
	 * @return
	 */
	public List<Double> generateRandomValues(List vlist){
		
		Integer seed;
		String str ="",digit="";
		
		Random random = new Random();
		
		
		try {
			for(int i=0;i<this.numOfDigits;i++) {
				digit = "" +random.nextInt(this.numOfDigits+1);
				str = str + digit.charAt(0);
				
			}
		
		  seed = Integer.valueOf(str);
			
		}catch(NumberFormatException ife) {
			seed = random.nextInt(10);
		}
		
		random = new Random(seed);
		
		
		for(int i=0; i< this.lengthOfLists;i++) {
			double sd = (10*(this.numOfDigits+1)*random.nextInt(this.numOfDigits+1)* random.nextDouble());
			sd = Math.floor(sd);
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
