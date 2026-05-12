package model.dao;

import java.util.List;

import model.entities.OrderItem;

public interface OrderItemDao {

	void insert(OrderItem obj);
	void update(OrderItem ob);
	void deleteById(Integer id);
	List<OrderItem> findAll();
	

}
