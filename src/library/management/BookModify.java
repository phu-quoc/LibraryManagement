package library.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BookModify
{
	Connection conn = null;
	public BookModify()
	{
		
	}
	
	public Vector<Book> getBookList()
	{
		Vector<Book> bookList = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_findAllBook";
		Statement stmt = null;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
									rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				bookList.add(book);
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
		return bookList;
	}
	
	public void addBook(Book book)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_insertBook(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getBookName());
			stmt.setString(2, book.getPageNo());
			stmt.setString(3, book.getLanguage());
			stmt.setInt(4, book.getPrice());
			stmt.setInt(5, book.getAmount());
			stmt.setString(6, book.getPublishYear());
			stmt.setString(7, book.getType());
			stmt.setString(8, book.getAuthor());
			stmt.setString(9, book.getPublisher());
			stmt.execute();
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
	}
	
	public void deleteBook(int bookId)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_deleteBook(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bookId);
			stmt.execute();
			
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
	}
	
	public void updateBook(Book book)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_updateBook(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getBookName());
			stmt.setString(2, book.getPageNo());
			stmt.setString(3, book.getLanguage());
			stmt.setInt(4, book.getPrice());
			stmt.setInt(5, book.getAmount());
			stmt.setString(6, book.getPublishYear());
			stmt.setString(7, book.getType());
			stmt.setString(8, book.getAuthor());
			stmt.setString(9, book.getPublisher());
			stmt.setInt(10, book.getBookId());
			stmt.execute();
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
	}
	

//	
	public Vector<Book> findBookBy(String sql, String parameter)
	{
		Vector<Book> bookList = new Vector();
		conn = ConnectDB.getConnection();
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
//				System.out.println(rs.getString(1)+ rs.getString(2)+ rs.getString(3)+ rs.getString(4)+ rs.getInt(5)+
//						rs.getInt(6)+ rs.getString(7)+ rs.getString(8)+ rs.getString(9)+ rs.getString(10));
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				bookList.add(book);
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
		return bookList;
	}
	
	public Vector<Book> sortAZPageNo(String sql)
	{
		Vector<Book> bookList = new Vector();
//		String sql = "call sp_sortAZ";
		conn = ConnectDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				bookList.add(book);
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
		return bookList;
		
	}
}

