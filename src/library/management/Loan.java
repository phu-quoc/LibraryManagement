package library.management;

public class Loan
{
	private int loanId;
	private int bookId;
	private int readerId;
	private int loanNo;
	private String loanDate;
	private String bookReturnAppointmentDate;
	private String bookReturnDate;
	private String status;
	
	public Loan()
	{
		
	}
	
	public Loan(int loanId, int bookId, int readerId, int loanNo, String loanDate, String bookReturnAppointmentDate,
			String bookReturnDate, String status)
	{
		this.loanId = loanId;
		this.bookId = bookId;
		this.readerId = readerId;
		this.loanNo = loanNo;
		this.loanDate = loanDate;
		this.bookReturnAppointmentDate = bookReturnAppointmentDate;
		this.bookReturnDate = bookReturnDate;
		this.status = status;
	}

	public int getLoanId()
	{
		return loanId;
	}
	
	public void setLoanId(int loanId)
	{
		this.loanId = loanId;
	}
	public int getBookId() 
	{
		return bookId;
	}

	public void setBookId(int bookId) 
	{
		this.bookId = bookId;
	}

	public int getReaderId() 
	{
		return readerId;
	}

	public void setReaderId(int readerId)
	{
		this.readerId = readerId;
	}

	public int getLoanNo()
	{
		return loanNo;
	}

	public void setLoanNo(int loanNo) 
	{
		this.loanNo = loanNo;
	}

	public String getLoanDate()
	{
		return loanDate;
	}

	public void setLoanDate(String loanDate) 
	{
		this.loanDate = loanDate;
	}

	public String getBookReturnAppointmentDate()
	{
		return bookReturnAppointmentDate;
	}

	public void setBookReturnAppointmentDate(String bookReturnAppointmentDate) 
	{
		this.bookReturnAppointmentDate = bookReturnAppointmentDate;
	}

	public String getBookReturnDate()
	{
		return bookReturnDate;
	}

	public void setBookReturnDate(String bookReturnDate) 
	{
		this.bookReturnDate = bookReturnDate;
	}	
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
