import javax.swing.JOptionPane;

public class AssEx1 {
	
	public static void main(String arg[]){
		double n = 0; //this variable is to convert initial amount from string to double
		boolean a = false;//this variable is to make sure initial amount is a number
		
		//to take customer's name
		String name = JOptionPane.showInputDialog(null,"Please enter customer name.");
		
		
		//checks if user clicked 'cancel'
		if(name == null)
			System.exit(0);
		
		//checks if input is empty and terminates program if so
		if(name.trim().isEmpty())
			System.exit(0);
		
		//to take initial balance
		while(!a)
		{
			String init_balance = JOptionPane.showInputDialog(null, "What's the initial balance?");
			
			//checks if user clicked 'cancel'
			if(init_balance == null)
				System.exit(0);
			
			//if user didn't click cancel, it'll change entered string to double or ask again if value is not a number
			try		
				{
				n = Double.parseDouble(init_balance.trim());
				a = true;
				}	
			
			catch(NumberFormatException x)
				{
				JOptionPane.showMessageDialog(null, "Sorry you have to enter a number", "Initial Balance Entry Error", JOptionPane.ERROR_MESSAGE);
				}
		}//end of while
		
		
		
		//creates an object with the name and initial balance
		CustomerAccount ca  = new CustomerAccount(name, n);
		
		LWMGUI lw = new LWMGUI(ca);//creates the main GUI object
		lw.setTitle("Lillybank Wine Merchants: " + name); //sets title
		
		
		
	}//end of main

}//end of AssEx1
