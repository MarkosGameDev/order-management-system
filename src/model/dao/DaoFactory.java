package model.dao;

import db.DB;
import model.dao.impl.ItemDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
	
	public static ItemDao createItemDao() {
		return new ItemDaoJDBC(DB.getConnection());
	}
}
