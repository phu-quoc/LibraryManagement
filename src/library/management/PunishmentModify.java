package library.management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class PunishmentModify
{
	Connection conn = null;
	public PunishmentModify()
	{
		
	}
	
	public Vector<Punishment> getPunishmentList()
	{
		Vector<Punishment> punishmentList = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_punish";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Punishment punishment = new Punishment();
				punishment.setLoanId(rs.getInt(1));
				punishment.setReaderId(rs.getInt(2));
				punishment.setFullname(rs.getString(3));
				punishment.setBookId(rs.getInt(4));
				punishment.setBook(rs.getString(5));
				punishment.setLoanNo(rs.getInt(6));
				punishment.setDaysLate(rs.getInt(7));
				punishment.setTotal(rs.getInt(8));
				punishment.setStatus(rs.getString(9));
				punishmentList.add(punishment);
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return punishmentList;
	}
	
	public Vector<Punishment> getPunishmentListReturnYet()
	{
		Vector<Punishment> punishmentListReturnYet = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_punishReturnYet";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Punishment punishment = new Punishment();
				punishment.setLoanId(rs.getInt(1));
				punishment.setReaderId(rs.getInt(2));
				punishment.setFullname(rs.getString(3));
				punishment.setBookId(rs.getInt(4));
				punishment.setBook(rs.getString(5));
				punishment.setLoanNo(rs.getInt(6));
				punishment.setDaysLate(rs.getInt(7));
				punishment.setTotal(rs.getInt(8));
				punishment.setStatus(rs.getString(9));
				punishmentListReturnYet.add(punishment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				conn.close();
				stmt.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return punishmentListReturnYet;
	}
}
