
public class Wine {
	
	private String wineName;
	private double bottPrice;
	private int bottQuant;
	
	
	//constructor
	public Wine(String name, double price, int quant)
	{
		wineName = name;
		bottPrice = price;
		bottQuant = quant;
	}
	
	
	//get methods
	public String getWineName(){return wineName;}
	public double getBottPrice(){return bottPrice;}
	public int getBottQuant(){return bottQuant;}		
}
