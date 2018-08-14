package br.exemplo.spring.models;

import java.math.BigDecimal;

public class ShoppingItem {

	private Product product;
	private BookType bookType;

	
	public ShoppingItem(Product product, BookType bookType) {
		this.product = product;
		this.bookType = bookType;
	}


	public BigDecimal getTotal(int quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getPrice(){
		return product.priceFor(bookType);
	}
	
	public BigDecimal getTotal(Integer quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookType == null) ? 0 : bookType.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (bookType != other.bookType)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	
	

}
