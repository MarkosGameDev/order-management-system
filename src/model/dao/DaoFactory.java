package model.dao;

import db.DB;
import model.dao.impl.ItemDaoJDBC;
import model.dao.impl.OrderDaoJDBC;
import model.dao.impl.OrderItemDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoJDBC(DB.getConnection());
	}
	
	public static OrderDao createOrderDao() {
		return new OrderDaoJDBC(DB.getConnection());
	}
	
	public static OrderItemDao createOrderItemDao() {
		return new OrderItemDaoJDBC(DB.getConnection());
	}
}
