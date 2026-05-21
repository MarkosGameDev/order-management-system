package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId;
	private Integer itemId;
	private Integer quantity;
	
	public OrderItem() {
	}

	public OrderItem(Integer id, Integer orderId, Integer itemId, Integer quantity) {
		this.id = id;
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderItem [id: " + id + ", orderId: " + orderId + ", itemId: " + itemId + ", quantity: " + quantity + "]";
	}
	
	
	
}
