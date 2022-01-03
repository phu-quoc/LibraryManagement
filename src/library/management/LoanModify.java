package library.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class LoanModify
{
	Connection conn = null;
	public LoanModify()
	{
		
	}
	
	public Vector<Loan> getLoanlist()
	{
		conn = ConnectDB.getConnection();
		Vector<Loan> loanList = new Vector();
		String sql = "call sp_findAllLoan";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Loan loan = new Loan();
				loan.setLoanId(rs.getInt(1));
				loan.setReaderId(rs.getInt(2));
				loan.setBookId(rs.getInt(3));
				loan.setLoanNo(rs.getInt(4));
				loan.setLoanDate(rs.getString(5));
				loan.setBookReturnAppointmentDate(rs.getString(6));
				loan.setBookReturnDate(rs.getString(7));
				loan.setStatus(rs.getString(8));
				loanList.add(loan);
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
		return loanList;
	}
	
	public int addLoan(Loan loan)
	{
		int rowInserted = 0;
		conn = ConnectDB.getConnection();
		String sql = "call sp_addLoan(?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, loan.getReaderId());
			stmt.setInt(2, loan.getBookId());
//			stmt.setInt(3, loan.getLoanNo());
			stmt.setString(3, loan.getBookReturnAppointmentDate());
			rowInserted = stmt.executeUpdate();
//			rowInserted = stmt.executeUpdate();
//			System.out.println(stmt.execute());
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
		return rowInserted; 
	}
	
	public void returnBook(int LoanId)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_returnBook(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, LoanId);
			stmt.execute();
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
	}
	
	public Reader testReaderId(int ReaderId)
	{
		Reader reader = new Reader();
		conn = ConnectDB.getConnection();
		String sql = "call sp_testReaderId(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ReaderId);
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				reader.setName(rs.getString(1));
			}
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
		return reader;
	}
	
	public Book testBookId(int BookId)
	{
		Book book = new Book();
		conn = ConnectDB.getConnection();
		String sql = "call sp_testBookId(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BookId);
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				book.setBookName(rs.getString(1));
			}
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
		return book;
	}
	
	public Vector<Loan> findLoanByAll(String parameter)
	{
		Vector<Loan> loanList = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_findByAllLoan(?)";
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
				Loan loan = new Loan(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7),rs.getString(8));
				loanList.add(loan);
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
		return loanList;
	}
	
	public String checkTimeSpace(String madocgia)
	{
		String timeSpace = null;
		conn = ConnectDB.getConnection();
		String sql = "select fc_time_space(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(madocgia));
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				timeSpace = rs.getString(1);
				System.out.println(timeSpace);
			}
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
		return timeSpace;
	}
}
