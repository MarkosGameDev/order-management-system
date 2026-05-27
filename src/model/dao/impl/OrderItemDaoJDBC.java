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
import model.dao.OrderItemDao;
import model.entities.OrderItem;

public class OrderItemDaoJDBC implements OrderItemDao{

	private Connection conn;
	
	public OrderItemDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(OrderItem obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO order_items "
					+ "(order_id, item_id, quantity) "
					+ "VALUES"
					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getOrderId());
			st.setInt(2, obj.getItemId());
			st.setInt(3, obj.getQuantity());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(OrderItem obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE order_items "
					+ "SET quantity = ?"
					+ "WHERE id = ?");
			
			st.setInt(1, obj.getQuantity());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}	
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderItem findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM order_items WHERE id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				OrderItem obj = instantiateOrderItem(rs);
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
	public List<OrderItem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM order_items ORDER BY id");
			rs = st.executeQuery();
			
			List<OrderItem> listOrderItem = new ArrayList<>();
			while(rs.next()) {
				OrderItem obj = instantiateOrderItem(rs);
				listOrderItem.add(obj);
			}
			return listOrderItem;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

		
	}

	@Override
	public OrderItem findByUserId(Integer userId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM order_items WHERE userId = ?");
			st.setInt(1, userId);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				OrderItem obj = instantiateOrderItem(rs);
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

	private OrderItem instantiateOrderItem(ResultSet rs) throws SQLException {
		OrderItem obj = new OrderItem();
		
		obj.setId(rs.getInt("id"));
		obj.setOrderId(rs.getInt("orderId"));
		obj.setItemId(rs.getInt("itemId"));
		obj.setQuantity(rs.getInt("quantity"));
		
		return obj;
	}
}
