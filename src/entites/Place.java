package entites;

public class Place {

	private String type;
	private int price;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Place(String type, int price) {
		super();
		this.type = type;
		this.price = price;
	}

}
