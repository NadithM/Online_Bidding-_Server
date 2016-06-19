import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;


public class MainServer { 

    /* Some constants */     
    public static final int BASE_PORT = 2000;  // do not change    


    /* local data for the server 
     * Every main server is defined in terms of the port it 
     * listens and the database of allowed users 
     */
     
    // private variable needed for Main server 
    private ServerSocket serverSocket=null;  //need a server Socket for main server 
    private StockDB stock_DB=null;    		// need a database 
    private LinkedList<BidData> tempLink;	//need a datastrucure to store the data in the database
	
	//
    public MainServer(int socket, StockDB mainDB) {
		this.stock_DB = mainDB; //assign the server database
		try { 
			this.serverSocket = new ServerSocket(socket); 
		} 
		catch (IOException e) { 
			System.out.println(e); 
		}
    }

    /* each server will provide the following functions to 
     * the public. there for Note that these are non-static 
     */ 
     
     //no  need of synchronized. manyuser can bid on one symbol 
    public boolean isAuthorized(String symNo) { 
	return this.stock_DB.findComName(symNo) != null;
    }    

	//no  need of synchronized below 3. if the function call at  the same time in two thread one is queing untill first finished ..buffer.stack handel the case   
    public String getName(String symNo) { 
		return this.stock_DB.findComName(symNo);
    }	
    
    public String getPrice(String symNo) { 
		return this.stock_DB.findPrice(symNo);
    }	
    
    //to add biding values and the user name to corresponding linked list in MainDB
     public synchronized boolean addToLink (String Symbol ,BidData data ,String presantbid) { 
		try{
			if(this.stock_DB.updatePrice(Symbol, presantbid)){//to be true bid values shoul dbe grater than  what is presant....and also if true update the currnt bid values
			tempLink = stock_DB.getLinkedList(Symbol);	//get the corresponding LINked list of the symbol
			tempLink.add(data);							//adding data to linked list
			}
			else return false;
		}
		catch(NullPointerException e){//not having
			return false;
			
		}//update the main database with new bid values
		return true; 
    }
    
	//lisning to the socket. 
    public void server_loop() { 
	try { 
	    while(true) { 
		Socket socket = this.serverSocket.accept(); //socket accepted
		ConnectionServer worker = new ConnectionServer(this); //created worker to handel new conncetion
		worker.handleConnection(socket);  // he is going to handel the work..dont know work is threded or single 
	    }
	} catch(IOException e) { 
	    System.out.println(e);
	}
    }// end server_loop 
}


	




