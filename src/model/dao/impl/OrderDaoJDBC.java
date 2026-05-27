package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.OrderDao;
import model.entities.Order;

public class OrderDaoJDBC implements OrderDao{

	private Connection conn;
	
	public OrderDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Order obj) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					"INSERT INTO orders " +
					"(userId, description, totalAmount) " +
					"VALUES " +
					"(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getUserId());
			st.setString(2, obj.getDescription());
			st.setDouble(3, obj.getTotalAmount());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} 
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}	
	}

	@Override
	public void update(Order obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE orders " +
					"SET description = ?, totalAmount = ? " +
					"WHERE id = ?");
			
			st.setString(1, obj.getDescription());
			st.setDouble(2, obj.getTotalAmount());
			st.setInt(3, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM orders WHERE id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("ID not found");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}	
	}

	@Override
	public List<Order> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM orders ORDER by id");
			
			rs = st.executeQuery();
			
			List<Order> listOrder = new ArrayList<>();
			while(rs.next()) {
				Order obj = instantiateOrder(rs);
				listOrder.add(obj);
			}
			return listOrder;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
	}

	@Override
	public Order findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			
			st = conn.prepareStatement("SELECT * FROm orders WHERE id = ? ");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Order obj = instantiateOrder(rs);
				return obj;
			}
			
			return null;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Order findByUserId(Integer userId) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM orders WHERE userId = ?");
			st.setInt(1, userId);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Order obj = instantiateOrder(rs);
				return obj;
			}
			
			return null;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
	}
	
	private Order instantiateOrder(ResultSet rs) throws SQLException{
		
		Order obj = new Order();
		
		obj.setId(rs.getInt("id"));
		obj.setUserId(rs.getInt("userId"));
		obj.setDescription(rs.getString("description"));
		obj.setTotalAmount(rs.getDouble("totalAmount"));
		
		return obj;
	}
}
