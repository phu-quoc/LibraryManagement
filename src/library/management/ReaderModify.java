package library.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ReaderModify {
	Connection conn = null;
	public ReaderModify()
	{
		
	}
	
	public Vector<Reader> getReaderList()
	{
		Vector<Reader> readerList = new Vector();
		String sql = "call sp_findAllReader";
		conn = ConnectDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reader reader = new Reader();
				reader.setReaderId(rs.getInt(1));
				reader.setSurname(rs.getString(2));
				reader.setName(rs.getString(3));
				reader.setIdentityCard(rs.getString(4));
				reader.setPhoneNo(rs.getNString(5));
				reader.setCardIssueDate(rs.getString(6));
				reader.setJob(rs.getString(7));
				readerList.add(reader);
			}
		} catch (Exception e) {
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
		return readerList;
	}
	
	public void addReader(Reader reader)
	{
		conn = ConnectDB.getConnection();
//		Reader reader = new Reader();
		String sql = "call sp_addReader(?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, reader.getSurname());
			stmt.setString(2, reader.getName());
			stmt.setString(3, reader.getIdentityCard());
			stmt.setString(4, reader.getPhoneNo());
			stmt.setString(5, reader.getJob());
			stmt.execute();
		} catch (SQLException e) {
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
	}
	
	public void updateReader(Reader reader)
	{
		//		Reader reader = new Reader();
		conn = ConnectDB.getConnection();
		String sql = "call sp_updateReader(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, reader.getSurname());
			stmt.setString(2, reader.getName());
			stmt.setString(3, reader.getIdentityCard());
			stmt.setString(4, reader.getPhoneNo());
			stmt.setString(5, reader.getJob());
			stmt.setInt(6, reader.getReaderId());
			stmt.execute();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
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
	}
	
	public void deleteReader(int readerId)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_deleteReader(?)";
		PreparedStatement stmt = null;
		try 
		{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,readerId);
			stmt.execute();
		} catch (SQLException e) {
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
	}
	
	public Vector<Reader> findReaderByAll(String parameter)
	{
		Vector<Reader> readerList = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_findByAllReader(?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+parameter+"%");
			stmt.execute();
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Reader reader = new Reader(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				readerList.add(reader);
			}

		} catch (Exception e) {
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
		return readerList;
	}
}
