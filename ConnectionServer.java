import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ConnectionServer implements Runnable { 
    // some constants 
    //state of the net connecting user,before name,before symbol, after sybol
    public static final int WAIT4_NAME = 3; 
    public static final int WAIT4_SYM = 2; 
    public static final int USER_IN = 1;

	// some messages to netconnect users
    public static final String INVALID_SYMBOL = "Please Enter Valid Symbol Name\n"; 
    public static final String SYM_MSG = "Enter The Symbol: "; 
    public static final String USER_IN_MSG = "You are authorised to Bid\n"; 
    public static final String VALID_MSG   = "Bid Again or type Quit to Exit\nEnter A bid : "; 
	public static final String INVAID_MSG   = "Your Bid is invalid\n"; 

    // per connection variables
    private Socket mySocket; // connection socket per thread 
    private int currentState; 
    private String clientName; 
    private MainServer mainServer; 
	private String symbol;

	//threaded handeling
	
	
    public ConnectionServer(MainServer mainServer) { 
	this.mySocket = null; // we will set this later 
	this.currentState = WAIT4_NAME; 
	this.clientName = null; 
	this.mainServer = mainServer; //everyone connect to one mainserver at a time
	this.symbol =null;
	// who created me. He should give some interface 
    }

	
    public boolean handleConnection(Socket socket) { 
	this.mySocket = socket; //
	Thread newThread = new Thread(this);//cloning the worker to handel the socket connectionat the same time
	newThread.start();
	return true; 
    }
    
    
	//assign the work to each workerto handel with the new connection that it's assign 
    public void run() { 
	BufferedReader in=null; 
	PrintWriter out=null; 
	String line, outline,companyname,presantbid;  
	double bid;
	BidData temp;
	boolean stat;
	try { 
	    in = new 
		BufferedReader(new InputStreamReader(mySocket.getInputStream()));
	    out = new 
		PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
		
		for(line = in.readLine(); line != null && !line.equals("quit"); line = in.readLine()) { 	
	
			switch(this.currentState) { 
		
			case WAIT4_NAME://before name
				this.clientName=line;
				outline = "Hello "+ this.clientName +", "+SYM_MSG;
				this.currentState =WAIT4_SYM;
				break;
		
			case WAIT4_SYM: //after name before symbol
				
				if(this.mainServer.isAuthorized(line)) { //check valid symbol
					this.currentState = USER_IN; 
					symbol= line;
					outline = USER_IN_MSG; 
					out.print(outline);
					companyname=this.mainServer.getName(symbol);
					presantbid=this.mainServer.getPrice(symbol);
					outline = "Current price of the Stock of the:"+ companyname +" is: " + presantbid +"\nEnter A bid : ";
				}
				else { 
					outline = INVALID_SYMBOL; 
				}
				break;
				/*****************************/
			case USER_IN: //after symbol
			  try{
				bid=Double.parseDouble(line);
			  }
			  catch (NumberFormatException e) {
					outline="Please Enter a Valid Bid!\n";
					break;
			 }
					presantbid=String.valueOf(bid);
					temp=new BidData(presantbid,this.clientName);
					stat=mainServer.addToLink(this.symbol,temp,presantbid);
					if(stat)
					{
						outline="Your Bid is posted.Latest Bid is :"+this.mainServer.getPrice(symbol)+"\n";
						out.print(outline);
						outline = VALID_MSG;
					}
					else {
						outline="Please Enter a Valid Bid!\n";
						out.print(outline);
						outline="Latest Bid is :"+this.mainServer.getPrice(symbol)+"\n";
					}
			break;
			default: 
				outline="Undefine state\n";
				break;
		} // case 
		out.print(outline);
		out.flush(); // flush to network
		} // for 

	    // close everything 
	    out.close(); 
	    in.close(); 
	    this.mySocket.close(); 
	} // try 	     
	catch (IOException e) { 
	    System.out.println(e); 
	} 
    }
}

    
    

