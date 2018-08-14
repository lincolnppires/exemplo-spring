package br.exemplo.spring.models;

public class ShoppingItem {

	private Product product;
	private BookType bookType;

	
	public ShoppingItem(Product product, BookType bookType) {
		this.product = product;
		this.bookType = bookType;
	}

}
