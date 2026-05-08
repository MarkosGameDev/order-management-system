package model.entities;

import java.util.Objects;

public class Order {

	private Integer id;
	private Integer userId;
	private String description;
	private Double totalAmount;
	
	public Order() {
	}

	public Order(Integer id, Integer userId, String description, Double totalAmount) {
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.totalAmount = totalAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id: " + id + ", userId: " + userId + ", description: " + description + ", totalAmount: "
				+ totalAmount + "]";
	}
	
	
	
}
