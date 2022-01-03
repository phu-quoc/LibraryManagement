package library.management;

public class Book
{
	private int bookId;
	private String bookName;
	private String pageNo;
	private String language;
	private int price;
	private int amount;
	private String publishYear;
	private String type;
	private String author;
	private String publisher;
	
	public Book() 
	{
		
	}
	
	public Book(int bookId, String bookName, String pageNo, String language, int price, int amount, String publishYear,
			String type, String author, String publisher) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.pageNo = pageNo;
		this.language = language;
		this.price = price;
		this.amount = amount;
		this.publishYear = publishYear;
		this.type = type;
		this.author = author;
		this.publisher = publisher;
	}
	
	

	public Book(int bookId, String bookName, String pageNo, String language, int price, int amount,
			String publishYear) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.pageNo = pageNo;
		this.language = language;
		this.price = price;
		this.amount = amount;
		this.publishYear = publishYear;
	}

	public int getBookId() 
	{
		return bookId;
	}

	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}

	public String getBookName()
	{
		return bookName;
	}

	public void setBookName(String bookName) 
	{
		this.bookName = bookName;
	}

	public String getPageNo() 
	{
		return pageNo;
	}

	public void setPageNo(String pageNo) 
	{
		this.pageNo = pageNo;
	}

	public String getLanguage() 
	{
		return language;
	}

	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	public String getPublishYear() 
	{
		return publishYear;
	}

	public void setPublishYear(String publishYear) 
	{
		this.publishYear = publishYear;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}
	
	
	
}
