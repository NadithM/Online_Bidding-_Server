import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class Display1 extends JFrame implements ActionListener{

	private static StockDB mainDB = new StockDB("stocks.csv","Symbol","Security Name","Price");
	//databaseObject to handel biding  history and registered symbols
	private static MainServer server = new MainServer(MainServer.BASE_PORT,mainDB); 
	//Mainserver object to handel the functionalities given by Mainserver 
	
	//set of private varibles for handel the functionalities of SERVERGUI
	private BidData data;
	private  LinkedList<BidData> tempLink1;
	private LinkedList<String > tempLink2= new LinkedList<String>();
	private int count=0,a=0;
	private String temp1,Updatebid , pre,Symbolname; ;
	private String temp [];	
	private Timer timer;
	
	//initializing componant in GUI
    public Display1() {
        initComponents();
        timer = new Timer(500, this); 
		timer.start();
    }

    //Refreshing the GUI 
    public void actionPerformed(ActionEvent e) {
			String Symbolname = Symbol.getText(); 
		    HistoryofBids(Symbolname);
			repaint();
	  }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        SymbolPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Symbol = new javax.swing.JTextField();
        SymbolSel = new javax.swing.JComboBox();
        SymbolSubmit = new javax.swing.JButton();
        DetailPanel = new javax.swing.JPanel();
        ComName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        Updateprice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        UpriceSubmit = new javax.swing.JButton();
        HistoryPanel = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaSym = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SymbolPanel.setBackground(new java.awt.Color(51, 51, 51));
        SymbolPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
       
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setLabelFor(SymbolPanel);
        jLabel1.setText("Symbol :");
        jLabel1.setAlignmentX(5);
        jLabel1.setAlignmentY(0.0F);

        Symbol.setBackground(new java.awt.Color(153, 153, 153));
        Symbol.setForeground(new java.awt.Color(255, 255, 255));
        Symbol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Symbol.setText("FB");
        Symbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SymbolActionPerformed(evt);
            }
        });

        SymbolSel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN" }));
        SymbolSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SymbolSelActionPerformed(evt);
            }
        });

        SymbolSubmit.setBackground(new java.awt.Color(102, 102, 102));
        SymbolSubmit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        SymbolSubmit.setForeground(new java.awt.Color(255,255,255));
        SymbolSubmit.setText("Search");
        SymbolSubmit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        SymbolSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SymbolSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SymbolPanelLayout = new javax.swing.GroupLayout(SymbolPanel);
        SymbolPanel.setLayout(SymbolPanelLayout);
        SymbolPanelLayout.setHorizontalGroup(
            SymbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SymbolPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SymbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Symbol)
                    .addComponent(SymbolSel, 0, 143, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SymbolSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        SymbolPanelLayout.setVerticalGroup(
            SymbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SymbolPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(SymbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SymbolSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SymbolPanelLayout.createSequentialGroup()
                        .addGroup(SymbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Symbol, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SymbolSel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        DetailPanel.setBackground(new java.awt.Color(51, 51, 51));
        DetailPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        DetailPanel.setForeground(new java.awt.Color(255, 255, 255));

        ComName.setBackground(new java.awt.Color(51, 51, 51));
        ComName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ComName.setForeground(new java.awt.Color(255, 255, 255));
        ComName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ComName.setLabelFor(DetailPanel);
        ComName.setText("Facebook Inc. - Class A Common Stock ");
        ComName.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 0), new java.awt.Color(255, 255, 204)));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stock Price");

        Price.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 255, 255));
        
        Price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Price.setText("5.87 ");

        Updateprice.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Updateprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Updateprice.setText("0.0");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Update Price");

        UpriceSubmit.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        UpriceSubmit.setBackground(new java.awt.Color(102, 102, 102));
        UpriceSubmit.setForeground(new java.awt.Color(255,255,255));
        
        UpriceSubmit.setText("UPDATE");
        UpriceSubmit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        UpriceSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpriceSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DetailPanelLayout = new javax.swing.GroupLayout(DetailPanel);
        DetailPanel.setLayout(DetailPanelLayout);
        DetailPanelLayout.setHorizontalGroup(
            DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ComName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Updateprice, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpriceSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        DetailPanelLayout.setVerticalGroup(
            DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanelLayout.createSequentialGroup()
                .addComponent(ComName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Price)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Updateprice, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(UpriceSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        HistoryPanel.setBackground(new java.awt.Color(51, 51, 51));
        HistoryPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        exit.setText("EXIT");
        exit.setBackground(new java.awt.Color(102, 102, 102));
        exit.setForeground(new java.awt.Color(255,255,255));
        
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
                System.exit(0);
            }
        });

        TextAreaSym.setColumns(20);
        TextAreaSym.setForeground(new java.awt.Color(0,0,0));
        TextAreaSym.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        TextAreaSym.setRows(5);
         TextAreaSym.setEditable(false);
        jScrollPane1.setViewportView(TextAreaSym);
        

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OFFERS MADE");

        javax.swing.GroupLayout HistoryPanelLayout = new javax.swing.GroupLayout(HistoryPanel);
        HistoryPanel.setLayout(HistoryPanelLayout);
        HistoryPanelLayout.setHorizontalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(18, 18, 18))
            .addGroup(HistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        HistoryPanelLayout.setVerticalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exit)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SymbolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HistoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SymbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(HistoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

   //Handleing the JCombBox Actions
    private void SymbolSelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        String text=(String)SymbolSel.getSelectedItem(); 
        Symbol.setText(text);   
        TextAreaSym.setText("");
		a=0; count=0;
		//if 
		String CompanyName = mainDB.findComName(text);
		String StockPrice  =mainDB.findPrice(text);
	    ComName.setText(CompanyName);
		Price.setText(StockPrice);
	 }                                         

	//handeling the JtextFeild for entering Symbol name.if it is not valid symbol...
	//no update GUI,HIStory,error massge  shown
    private void SymbolSubmitActionPerformed(java.awt.event.ActionEvent evt) {    
    			String Symbolname = Symbol.getText(); 
                if(server.isAuthorized(Symbolname)){ 
	                String CompanyName = mainDB.findComName(Symbolname);
	                String StockPrice  =mainDB.findPrice(Symbolname);
	                ComName.setText(CompanyName);
	                Price.setText(StockPrice);
				}
                else{
                	String	Msg3="<html><h2>Enter A Valid Symbol :(</h2></html> :)";
					JOptionPane.showMessageDialog (null,Msg3 , "UNKNOWN SYMBOL",JOptionPane.PLAIN_MESSAGE);
				 }
     }                                            

	private void SymbolActionPerformed(java.awt.event.ActionEvent evt) {  }  

	//JButton that Upadte the price of the ADMIN gives Handeling
    private void UpriceSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                             
		Updatebid = Updateprice.getText();
		Symbolname = Symbol.getText(); //get the symbolname
		if(server.isAuthorized(Symbolname)){ //check the valid symbol.if it is....update the history of that symbol price,server gui
			mainDB.updatePriceAdmin(Symbolname,Updatebid);
			tempLink1 = mainDB.getLinkedList(Symbolname); 
			data = new BidData(Updatebid,"ADMIN");
			Price.setText(Updatebid);
			tempLink1.add(data);
			HistoryofBids(Symbolname);
			}
    }                                            

    private void exitActionPerformed(java.awt.event.ActionEvent evt) { }   
	
	//function that converts BidData Data type (String,String) into one Single String
	private String datatoText(BidData data){
			count++;
			if(!data.name.equals("ADMIN"))//when admin changes the stock value
			temp1=count+". "+data.name+" asks for "+data.value+"\n" ;
			else                         //when admin changes the stock value
			temp1=count+". "+data.name+" set the price for "+data.value+"\n" ;
			return temp1;
		}                      

    //Printing function to the JTEXTAREA 
    private void HistoryofBids(String symna){
		tempLink1 = mainDB.getLinkedList(symna); 
		try{
			for (int i = a ; i < tempLink1.size() ; i++){
				data= tempLink1.get(i);
				TextAreaSym.append(datatoText(data) ); 
				if (i==tempLink1.size()-1) a=tempLink1.size();
			}
		}
		catch(Exception e){ }
	}   
	
	//Main for the ACTIONSERVER GUI
    public static void main(String args[])  throws IOException {
        try {
			 for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
		}
		catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Display1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
				new Display1().setVisible(true);
			}
        });
        
        server.server_loop(); 
    }

    // Variables declaration in GUI                  
    private javax.swing.JLabel ComName;
    private javax.swing.JPanel DetailPanel;
    private javax.swing.JPanel HistoryPanel;
    private javax.swing.JLabel Price;
    private javax.swing.JTextField Symbol;
    private javax.swing.JPanel SymbolPanel;
    private javax.swing.JComboBox SymbolSel;
    private javax.swing.JButton SymbolSubmit;
    private javax.swing.JTextArea TextAreaSym;
    private javax.swing.JTextField Updateprice;
    private javax.swing.JButton UpriceSubmit;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
