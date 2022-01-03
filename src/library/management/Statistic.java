package library.management;

public class Statistic {
	private int totalBook;
	private int totalLoan;
	private int loan;
	private int punish;
	
	public Statistic()
	{
		
	}
	
	public Statistic(int totalBook, int totalLoan, int loan, int punish)
	{
		this.totalBook = totalBook;
		this.totalLoan = totalLoan;
		this.loan = loan;
		this.punish = punish;
	}

	public int getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(int totalBook) {
		this.totalBook = totalBook;
	}

	public int getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}

	public int getLoan() {
		return loan;
	}

	public void setLoan(int loan) {
		this.loan = loan;
	}

	public int getPunish() {
		return punish;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}
}
