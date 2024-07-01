package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;
import model.Order;
import util.myTool;

interface OrderDao {
//Create,
	//void addOrder(String name,String userID,String password,String account,String address,String phone);
	void addOrder(Order order);
//Read,
	List<Order> findAll();//全部
	//Order findByIDpw(String userID,String password);//null或ID&pw正確時RT
	//Order findByID(String userID);//null或ID正確時RT
//Update,
	void updateOrder(Order order);
	//void updateBanking(Order order);
//Delete,
	void deleteOrder(Order order);
}
/**
 * this are implements of MemberDao list above,
 */
public class OrderDaoAndImpl implements OrderDao{
//Create,
	@Override
	public void addOrder(Order order) {
		Connection conn = DbConnection.getDb();
		String sql="insert into orders(stockNo,委託狀態,盤別,交易別,條件,委託價,委託股數,委託書號,交易日期,幣別,member_id)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, order.getStockNo());
			ps.setString(2, order.get委託狀態());
			ps.setString(3, order.get盤別());
			ps.setString(4, order.get交易別());
			ps.setString(5, order.get條件());
			ps.setDouble(6, order.get委託價());
			ps.setInt(7, order.get委託股數());
			ps.setString(8, order.get委託書號());
			ps.setString(9, order.get交易日期());
			ps.setString(10, order.get幣別());
			ps.setInt(11, order.getMember_id());
			ps.executeUpdate();
		} catch(SQLException e) { e.printStackTrace(); }
	}
//Read,
	@Override
	public List<Order> findAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from orders where member_id=?";
		List<Order> listOrderAll = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, myTool.userNow.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setStockNo(rs.getString("stockNo"));
				order.set委託狀態(rs.getString("委託狀態"));
				order.set盤別(rs.getString("盤別"));
				order.set交易別(rs.getString("交易別"));
				order.set條件(rs.getString("條件"));
				order.set委託價(rs.getDouble("委託價"));
				order.set委託股數(rs.getInt("委託股數"));
				order.set成交股數(rs.getInt("成交股數"));
				order.set委託時間(rs.getString("委託時間"));
				order.set委託書號(rs.getString("委託書號"));
				order.set交易日期(rs.getString("交易日期"));
				order.set幣別(rs.getString("幣別"));
				order.setMember_id(rs.getInt("member_id"));
				listOrderAll.add(order);
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return listOrderAll;
	}
//Update,
	@Override
	public void updateOrder(Order order) {
		Connection conn=DbConnection.getDb();
		String sql="update orders set 委託股數=? where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, order.get委託股數());
			ps.setInt(2, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
		
	}
//Delete,
	@Override
	public void deleteOrder(Order order) {
		Connection conn=DbConnection.getDb();
		String sql="delete from orders where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
		
	}

}
