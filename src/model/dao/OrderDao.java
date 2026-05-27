package model.dao;

import java.util.List;

import model.entities.Order;

public interface OrderDao {

	void insert(Order obj);
	void update(Order obj);
	void deleteById(Integer id);
	Order findById (Integer id);
	List<Order> findAll();
	Order findByUserId(Integer userId);
	
}
