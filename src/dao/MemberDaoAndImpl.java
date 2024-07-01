package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member;

interface MemberDao {
//Create,
	void addMember(String name,String userID,String password,String account,String address,String phone);
	void addMember(Member m);
//Read,
	List<Member> findAll();//全部
	Member findByIDpw(String userID,String password);//null或ID&pw正確時RT
	Member findByID(String userID);//null或ID正確時RT
//Update,
	void updateMember(Member m);
	void updateBanking(Member m);
//Delete,
	void deleteMember(Member m);
}
/**
 * this are implements of MemberDao list above,
 */
public class MemberDaoAndImpl implements MemberDao {
//Create,
	@Override
	public void addMember(String name,String userID,String password,String account,String address,String phone){
		Connection conn = DbConnection.getDb();
		String sql="insert into members(name,userID,password,account,address,phone)values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, userID);
			ps.setString(3, password);
			ps.setString(4, account);
			ps.setString(5, address);
			ps.setString(6, phone);
			ps.executeUpdate();
		} catch(SQLException e) { e.printStackTrace(); }
	}
	@Override
	public void addMember(Member m) {
		Connection conn = DbConnection.getDb();
		String sql="insert into members(name,userID,password,account,address,phone)values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getUserID());
			ps.setString(3, m.getPassword());
			ps.setString(4, m.getAccount());
			ps.setString(5, m.getAddress());
			ps.setString(6, m.getPhone());
			ps.executeUpdate();
		} catch(SQLException e) { e.printStackTrace(); }
	}
//Read,
	@Override
	public List<Member> findAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from members";
		List<Member> listMemberAll = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setUserID(rs.getString("userID"));
				m.setPassword(rs.getString("password"));
				m.setAccount(rs.getString("account"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setSavings(rs.getDouble("savings"));
				listMemberAll.add(m);
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return listMemberAll;
	}
	@Override
	public Member findByIDpw(String userID, String password) {
		Connection conn=DbConnection.getDb();
		String sql="select * from members where userID=? and password=?";
		Member memberSearch=null;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userID);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				memberSearch=new Member();
				memberSearch.setId(rs.getInt("id"));
				memberSearch.setName(rs.getString("name"));
				memberSearch.setUserID(rs.getString("userID"));
				memberSearch.setPassword(rs.getString("password"));
				memberSearch.setAccount(rs.getString("account"));
				memberSearch.setAddress(rs.getString("address"));
				memberSearch.setPhone(rs.getString("phone"));
				memberSearch.setSavings(rs.getDouble("savings"));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return memberSearch;
	}
	@Override
	public Member findByID(String userID) {
		Connection conn=DbConnection.getDb();
		String sql="select * from members where userID=?";
		Member memberSearch=null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				memberSearch=new Member();
				memberSearch.setId(rs.getInt("id"));
				memberSearch.setName(rs.getString("name"));
				memberSearch.setUserID(rs.getString("userID"));
				memberSearch.setPassword(rs.getString("password"));
				memberSearch.setAccount(rs.getString("account"));
				memberSearch.setAddress(rs.getString("address"));
				memberSearch.setPhone(rs.getString("phone"));
				memberSearch.setSavings(rs.getDouble("savings"));
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return memberSearch;
	}
//Update,
	@Override
	public void updateMember(Member m) {
		Connection conn=DbConnection.getDb();
		String sql="update members set name=?,password=?,account=?,address=?,phone=? where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getAccount());
			ps.setString(4, m.getAddress());
			ps.setString(5, m.getPhone());
			ps.setInt(6, m.getId());
			ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	@Override
	public void updateBanking(Member m) {
		Connection conn=DbConnection.getDb();
		String sql="update members set savings=? where account=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setDouble(1, m.getSavings());
			ps.setString(2, m.getAccount());
			ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
//Delete,
	@Override
	public void deleteMember(Member m) {
		Connection conn=DbConnection.getDb();
		String sql="delete from members where userID=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, m.getUserID());
			ps.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
}
