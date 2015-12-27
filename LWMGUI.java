import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LWMGUI extends JFrame implements ActionListener{
	
	private JButton bsale, breturn; 
	private JTextField tname, tquantity, tprice, ttrans, tbalance;
	private JLabel lname, lquantity, lprice, lwine, ltrans, lbalance;
	private JPanel pan, pan2, pan3;
	String inputname;
	int quant;
	double price;
	CustomerAccount cus;
	
	
	public LWMGUI(CustomerAccount x)
		{
		cus=x;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//size and location
		setSize(550, 180);
		setLocation(100, 200);
		
		//name label and text field and listener 
		lname = new JLabel("Name:");
		tname = new JTextField(15);
		tname.addActionListener(this);
		
		//quantity label and text field and action listener
		lquantity = new JLabel("Quantity:");
		tquantity = new JTextField(6);
		tquantity.addActionListener(this);
		
		//price label and text field
		lprice = new JLabel("Price: £");
		tprice = new JTextField(6);
		tprice.addActionListener(this);
		
		//adding panel to the north for text fields
		pan = new JPanel(); 
		
		pan.add(lname);
		pan.add(tname);
		
		pan.add(lquantity);
		pan.add(tquantity);
		
		pan.add(lprice);
		pan.add(tprice);
		
		add(pan, "North");
		
		
		//BUTTONS + action listeners 
		
		bsale = new JButton("Process Sale");
		breturn = new JButton("Process return");
		
		bsale.addActionListener(this);
		breturn.addActionListener(this);
		
		//adding sale and return buttons to a panel in the center
		
		pan2 = new JPanel();
		pan2.add(bsale);
		pan2.add(breturn);
		add(pan2, "Center");
		
		
		//wine type label
		lwine = new JLabel(); //
		add(lwine, "West");
		
		//transaction amount
		ltrans = new JLabel("Amount of Transaction: £");
		ttrans = new JTextField(10);
		ttrans.setEditable(false);
		
		//balance
		lbalance = new JLabel("Current balance: £");
		tbalance = new JTextField(10);
		tbalance.setEditable(false);
		
		//shows balance + checks if balance is negative and adds negative sign to the front (otherwise it'll show '-' two times)
		if(cus.getBalance()<0)
			tbalance.setText(String.format("%d.%2d CR", cus.getBalance()/100*-1, cus.getBalance()%100*-1));
		else
			tbalance.setText(String.format("%d.%2d", cus.getBalance()/100, cus.getBalance()%100));
		
		
		//add transaction and balance to a panel in the south
		pan3 = new JPanel();
		
		pan3.add(ltrans);
		pan3.add(ttrans);
		pan3.add(lbalance);
		pan3.add(tbalance);
		
		add(pan3, "South");
		setVisible(true);
	}//end of LWMGUI constructor
	
	public void actionPerformed(ActionEvent e)
		{
				
			//wine name is entered
			inputname = tname.getText();
				
				//error if wine name is not entered
				if(inputname.trim().isEmpty())
				JOptionPane.showMessageDialog(null, "Sorry you have to enter the wine's name", "Wine Name Entry Error", JOptionPane.ERROR_MESSAGE);
			
			
			
		
			//quantity is entered.
			String inputquant = tquantity.getText();
			
			//try statement to check if value is a whole number
			try
				{
				quant=Integer.parseInt(inputquant.trim());
					
				if(quant<0)//to check if number is positive if not --> clear field and keep value 0
					{
					JOptionPane.showMessageDialog(null, "Sorry you have to enter a positive number", "Quantity Entry Error", JOptionPane.ERROR_MESSAGE);
					tquantity.setText("");
					quant=0;
					}
					
				}
			catch(NumberFormatException x)
				{	
				//when entered value is not a number or when not an integer (for quantity)
				JOptionPane.showMessageDialog(null, "Sorry you have to enter a whole number", "Quantity Entry Error", JOptionPane.ERROR_MESSAGE);
				tquantity.setText("");
				quant=0;
				}
			
			
		
				//when price is entered.
				String input_price = tprice.getText();
				
				//try statement to check if value is a number
				try
				{
					price=Double.parseDouble(input_price.trim());
				
				//to check if number is positive if not --> clear field and keep price zero
					if(price<0)
						{
						JOptionPane.showMessageDialog(null, "Sorry you have to enter a positive number", "Price Entry Error", JOptionPane.ERROR_MESSAGE);
						tprice.setText("");
						price=0;
							
				   		}	
				}
				catch(NumberFormatException x)
				{	
						//when entered value is not a number
						JOptionPane.showMessageDialog(null, "Sorry you have to enter a number for price", "Price Entry Error", JOptionPane.ERROR_MESSAGE);
				}
					
		
		//when sale button is clicked --> clear fields and call sale method
		if(e.getSource() == bsale)
			{
			
			tname.setText("");
			tquantity.setText("");
			tprice.setText("");
			this.processSale();
			}
		
		//when return button is clicked --> clear fields and call return method
		if(e.getSource() == breturn)
			{
			tname.setText("");
			tquantity.setText("");
			tprice.setText("");
			this.processReturn();
			}
		
		}//end of action listener
	
	
	//create wine object and initialize it
	public Wine createWine()
	{
		Wine w = new Wine(inputname,price,quant);
		return w;
	}
	
	
	//to handle sale
	public void processSale()
		{
		
		//to calculate sales transaction and update balance --> pass values we obtained from user to the sales method in Customer Account class
		double trans = cus.sales_trans(this.createWine().getBottQuant(), this.createWine().getBottPrice());
		
		//set transaction field to transaction amount
		ttrans.setText(String.format("%.02f", trans));
			
		//if balance < 0 --> remove negative sign and add CR
		if(cus.getBalance()<0)
			tbalance.setText(String.format("%d.%2d CR", cus.getBalance()/100*-1, cus.getBalance()%100*-1));
		else
			tbalance.setText(String.format("%d.%2d", cus.getBalance()/100, cus.getBalance()%100));
		
		//show wine name label
		lwine.setText("Wine purchased: "+this.createWine().getWineName());
		}
		
	//to handle return
	public void processReturn()
	
		{
		//to calculate transaction and update balance--> pass values we obtained from user to the return method in Customer Account class
		double trans = cus.return_trans(this.createWine().getBottQuant(), this.createWine().getBottPrice());
		
		//set transaction field to transaction amount
		ttrans.setText(String.format("%.02f", trans));
		
		//if balance < 0 --> remove negative sign and add CR
		if(cus.getBalance()<0)
			tbalance.setText(String.format("%d.%02d CR", cus.getBalance()/100*-1, cus.getBalance()%100*-1));
		else
			tbalance.setText(String.format("%d.%20d", cus.getBalance()/100, cus.getBalance()%100));
			
		//show wine name label
		lwine.setText("Wine returned: "+this.createWine().getWineName());
		}

}

