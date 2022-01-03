package library.management;

public class Punishment
{
	private int loanId;
	private int readerId;
	private String fullname;
	private int bookId;
	private String book;
	private int loanNo;
	private int daysLate;
	private int total;
	private String status;
	
	
	public Punishment() 
	{
		
	}

	

	public Punishment(int loanId, int readerId, String fullname, int bookId, String book, int loanNo, int daysLate,
			int total, String status)
	{
		this.loanId = loanId;
		this.readerId = readerId;
		this.fullname = fullname;
		this.bookId = bookId;
		this.book = book;
		this.loanNo = loanNo;
		this.daysLate = daysLate;
		this.total = total;
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

	public int getReaderId() 
	{
		return readerId;
	}

	public void setReaderId(int readerId) 
	{
		this.readerId = readerId;
	}

	public String getFullname() 
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	public int getBookId() 
	{
		return bookId;
	}

	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}

	public String getBook()
	{
		return book;
	}

	public void setBook(String book)
	{
		this.book = book;
	}

	public int getLoanNo() 
	{
		return loanNo;
	}

	public void setLoanNo(int loanNo) 
	{
		this.loanNo = loanNo;
	}

	public int getDaysLate()
	{
		return daysLate;
	}

	public void setDaysLate(int daysLate) 
	{
		this.daysLate = daysLate;
	}

	public int getTotal() 
	{
		return total;
	}

	public void setTotal(int total) 
	{
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


	
	
}
