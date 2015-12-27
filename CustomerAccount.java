
public class CustomerAccount {
	
	private String cName; //customer name
	private int balance; // balance amount
	final double serviceCharge = .20; //fixed service charge
	
	public CustomerAccount(String n, double b)
	{
		cName = n;
		b=Math.round(b*100);
		balance = (int) (b);
	}
	
	//get methods
	public String getName(){return cName;}
	public int getBalance(){return balance;}
	
	//method to handle sale
	public double sales_trans(int numBottles, double costBottle)
		{
			double trans = numBottles * costBottle;
			String s = String.format("%.02f", trans);
			trans = Double.parseDouble(s);//to ensure number is rounded
			
			//update balance
			balance += (int)Math.round(trans*100);
			
			//return transaction amount
			return trans;
		}
	
	//method to handle return
	public double return_trans(int numBottles, double costBottle)
		{
		double trans = numBottles * costBottle *(1-serviceCharge);
		String s = String.format("%.02f", trans);
		trans = Double.parseDouble(s);//to ensure number is rounded
		
		//update balance
		balance -= (int)Math.round(trans*100);
		
		//return transaction amount
		return trans;
		}
}
