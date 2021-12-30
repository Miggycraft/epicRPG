package backpack;

import Characters.MainCharacter;

public class Items extends MainCharacter{
	private int quantity;
	private String name;
	
	Items(){
		this(1, "Default");
	}
	
	Items(int quantity, String name){
		this.quantity = quantity;
		this.name = name;
	}
	
	// getters
	public int getQuantity() {
		return quantity;
	}
	
	public String getName() {
		return name;
	}
	
	// setters
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// function
	
	public void addQuantity() {
		quantity++;
	}
	
	public void removeQuantity() {
		quantity--;
	}
	
	

}
