import java.io.*;
import java.util.*;

class StockDB { 

    private Map<String, String> companylist;
    private Map<String, String> pricelist; 
    public Map<String, LinkedList<BidData>> dataList; 
    private String [] fields; 
	
    public StockDB(String cvsFile, String key, String name,String price)  { 
	FileReader fileRd=null; 
	BufferedReader reader=null; 

	try { 
	    fileRd = new FileReader(cvsFile); 
	    reader = new BufferedReader(fileRd); 

	    /* read the CSV file's first line which has 
	     * the names of fields. 
	     */
	    String header = reader.readLine(); 
	    fields = header.split(",");// keep field names 

	    // find where the key and the value are 
	    int keyIndex = findIndexOf(key); 
	    int comIndex = findIndexOf(name); 
		int priIndex = findIndexOf(price); 

	if(keyIndex == -1 || comIndex == -1 || priIndex == -1) 
		throw new IOException("CVS file does not have data"); 
	  

	    // get a new hash map
	    companylist = new HashMap<String, String>(); 
		pricelist = new HashMap<String, String>(); 
		dataList = new HashMap<String, LinkedList<BidData>>(); 
	    /* read each line, getting it split by , 
	     * use the indexes to get the key and value 
	     */
	    String [] tokens; 
	    for(String line = reader.readLine(); line != null; line = reader.readLine()) { 
			tokens = line.split(","); 
			companylist.put(tokens[keyIndex], tokens[comIndex]); 
			pricelist.put(tokens[keyIndex], tokens[priIndex]); 
			dataList.put(tokens[keyIndex], new LinkedList<BidData>());
		}
	    
	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
	    
	  
	} catch (IOException e) { 
	    System.out.println(e);
	    System.exit(-1); 
	} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println("Malformed CSV file");
	    System.out.println(e);
	}
    }

    private int findIndexOf(String key) { 
	for(int i=0; i < fields.length; i++) 
	    if(fields[i].equals(key)) return i; 
	return -1; 
    }
	
	//to get company name
    public String findComName(String key) { 
	return companylist.get(key); 
    }
    //to get preasant price
     public String findPrice(String key) { 
	return pricelist.get(key); 
    }
    //to get correspoing linked list according to the SYMbol
     public synchronized LinkedList<BidData> getLinkedList(String key) { 
        
        return dataList.get(key);
    
    }
    // to update the new biding value of corresponding symbol in csv file
     public  synchronized boolean updatePrice(String key,String price){
        String temp;   
		temp=pricelist.get(key); 
		if(temp.equals(price))
			return false;
		else if (Double.parseDouble(temp)>Double.parseDouble(price))
			return false;
			else
		pricelist.put(key,price); 
		  
        return true;
     }
  
   public boolean updatePriceAdmin(String key,String price){
        String temp;   
		temp=pricelist.get(key); 
		if(temp.equals(price))
			return false;
		else
		pricelist.put(key,price); 
		 return true;
     }
    
    
    
}
	    
