package model.dao.impl;

import java.sql.Connection;

public class OrderDaoJDBC {

	private Connection conn;
	
	public OrderDaoJDBC(Connection conn) {
		this.conn = conn;
	}
}
