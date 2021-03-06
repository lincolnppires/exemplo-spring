package br.exemplo.spring.models;

import java.awt.ItemSelectable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ShoppingCart {

	private Map<ShoppingItem, Integer> items = new LinkedHashMap();

	public void add(ShoppingItem item) {
		items.put(item, getQuantity(item) + 1);
	}

	public int getQuantity(ShoppingItem item) {
		
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}
	
	public Integer getQuantity() {
		return items.size();
	}
	
	public Collection<ShoppingItem> getList() {
		return items.keySet();
	}
	
	public BigDecimal getTotal(ShoppingItem item) {
		return item.getTotal(getQuantity(item));
	}

	public BigDecimal getTotal() {

		BigDecimal total = BigDecimal.ZERO;
		
		for(ShoppingItem item : items.keySet()){
			total = total.add(getTotal(item));
		}
		return total;
	}


}
