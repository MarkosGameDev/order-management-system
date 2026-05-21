package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.OrderDao;
import model.entities.Order;

public class OrderDaoJDBC implements OrderDao{

	private Connection conn;
	
	public OrderDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Order obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
