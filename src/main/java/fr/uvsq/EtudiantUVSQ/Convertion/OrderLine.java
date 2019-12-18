package fr.uvsq.EtudiantUVSQ.Convertion;

public class OrderLine {

	private String item;
    private int quantity;
    private double unitPrice;
	
    public OrderLine() {
		
	}

	public OrderLine(String item, int quantity, double unitPrice) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "OrderLine [item=" + item + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
	}
    
    
	
	
}
