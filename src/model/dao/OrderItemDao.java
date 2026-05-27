package model.dao;

import java.util.List;

import model.entities.OrderItem;

public interface OrderItemDao {

	void insert(OrderItem obj);
	void update(OrderItem obj);
	void deleteById(Integer id);
	OrderItem findById(Integer id);
	List<OrderItem> findAll();
	OrderItem findByUserId(Integer userId);
	

}
