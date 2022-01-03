package library.management;

public class Reader 
{
	private int readerId;
	private String surname;
	private String name;
	private String identityCard;
	private String phoneNo;
	private String cardIssueDate;
	private String job;
	
	public Reader() 
	{

	}

	public Reader(int readerId, String surname, String name, String identityCard, String phoneNo,
			String cardIssueDate, String job)
	{
		this.readerId = readerId;
		this.surname = surname;
		this.name = name;
		this.identityCard = identityCard;
		this.phoneNo = phoneNo;
		this.cardIssueDate = cardIssueDate;
		this.job = job;
	}

	public int getReaderId() 
	{
		return readerId;
	}

	public void setReaderId(int readerId) 
	{
		this.readerId = readerId;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIdentityCard()
	{
		return identityCard;
	}

	public void setIdentityCard(String identityCard)
	{
		this.identityCard = identityCard;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public String getCardIssueDate()
	{
		return cardIssueDate;
	}

	public void setCardIssueDate(String cardIssueDate) 
	{
		this.cardIssueDate = cardIssueDate;
	}
	

	public String getJob() 
	{
		return job;
	}
	

	public void setJob(String job) 
	{
		this.job = job;
	}
	
	
	
	
	
	
	
}
