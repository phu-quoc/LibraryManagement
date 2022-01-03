package library.management;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class MainFrame extends JFrame{
	
	BookModify bookModify = new BookModify();
	ReaderModify readerModify = new ReaderModify();
	LoanModify loanModify = new LoanModify();
	PunishmentModify punishmentModify = new PunishmentModify();
	StatisticModify statisticModify = new StatisticModify();
	private Connection conn;
	private Container cont;
	private ButtonGroup gr;
	private DefaultComboBoxModel cbbModel;
	private DefaultTableModel tblModel;
	private JPasswordField pfPassword;
	private JTextField tfSearchBook, tfBookName, tfPageNo, tfLanguage, tfPrice, tfAmount, tfAuthor, 
						tfPublisher, tfReaderName, tfIdentityCard, tfPhoneNumber, tfSurname, tfReaderId,
						tfBookId, tfSearchLoan, tfDay, tfMonth, tfYear, tfSearchReader, tfDateTime;
	
	private JButton btnSearchBook, btnAddBook, btnResetBook, btnUpdateBook, btnDeleteBook,
						btnCheckReaderId, btnCheckBookId, btnLoanBook, btnReturnBook, btnSearchLoan,
						btnResetLoan, btnPunish, btnAddReader, btnUpdateReader, btnDeleteReader, btnResetReader, btnSearchReader;
	private JLabel lblBookName, lblPageNo, lblPrice, lblAmount, lblPublishYear,
					lblType, lblAuthor, lblPublisher, lblLanguage, lblReaderName, lblIdentityCard, 
					lblPhoneNumber, lblPosition, lblSurname, lblReaderId, lblBookId, lblReturnAppointmentDate, 
					lblOutputReader, lblOutputBook, lblStatisticTotalBook, lblStatisticLoan, lblStatisticPunish, lblStatisticTotalLoan;
	private JScrollPane scrollPane;
	private JComboBox cbbFindBy, cbbPublishYear, cbbSort, cbbType, cbbLoanNo;
	private JTable table;
	private JPanel pnlBookManagement, pnlReaderManagement, pnlLoan, pnlStatistical; 
	private JTabbedPane tabbedPane;
	private JRadioButton rdoLecturer, rdoStudent;
	private JDateChooser dc;
	private DateFormat df;

	public MainFrame() {
		cont = this.getContentPane();
		cont.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
	
		//scrollpane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 321, 1166, 362);
			//table
		table = new JTable();
		table.setCellSelectionEnabled(true);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		cont.add(scrollPane);
		
		//tabbedpane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1166, 301);
		
			//quan li sach
		pnlBookManagement = new JPanel();
		pnlBookManagement.setBounds(10, 41, 1166, 270);
		pnlBookManagement.setLayout(null);

		lblBookName = new JLabel("Tên sách:");
		lblPageNo = new JLabel("Số trang:");
		lblLanguage = new JLabel("Ngôn ngữ:");
		lblPrice = new JLabel("Giá trị:");
		lblAmount = new JLabel("Số lượng:");
		lblPublishYear = new JLabel("Năm xuất bản:");
		lblType = new JLabel("Thể loại:");
		lblAuthor = new JLabel("Tác giả:");
		lblPublisher = new JLabel("Nhà xuất bản:");
	
		tfBookName = new JTextField(null);
		tfPageNo = new JTextField(null);
		tfLanguage = new JTextField(null);
		tfPrice = new JTextField();
		tfPrice.setText("0");
		tfAmount = new JTextField(null);
		tfSearchBook = new JTextField();

		cbbPublishYear = new JComboBox();
		cbbType = new JComboBox();
		cbbSort = new JComboBox();
		cbbSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortAZPageNo();
				
			}
		});
		cbbFindBy = new JComboBox();
		
		tfAuthor = new JTextField(null);
		tfPublisher = new JTextField(null);
		
		btnAddBook = new JButton("Thêm sách");
		btnDeleteBook = new JButton("Xóa sách");
		btnUpdateBook = new JButton("Cập nhật");
		btnResetBook = new JButton("Nhập lại");
		btnSearchBook = new JButton("Tìm kiếm");
		
		
		lblBookName.setBounds(10, 17, 73, 26);
		lblPageNo.setBounds(10, 53, 73, 26);
		lblLanguage.setBounds(10, 89, 73, 26);
		lblPrice.setBounds(10, 125, 81, 26);
		lblAmount.setBounds(10, 161, 73, 19);
		lblPublishYear.setBounds(245, 21, 81, 19);
		lblType.setBounds(245, 53, 81, 26);
		lblAuthor.setBounds(245, 89, 86, 26);
		lblPublisher.setBounds(245, 125, 86, 26);
		tfBookName.setBounds(70, 21, 123, 19);
		tfPageNo.setBounds(70, 57, 123, 19);
		tfLanguage.setBounds(70, 93, 123, 19);
		tfPrice.setBounds(70, 129, 123, 19);
		tfAmount.setBounds(70, 161, 123, 19);
		tfAuthor.setBounds(336, 93, 123, 19);
		tfPublisher.setBounds(336, 129, 123, 19);
		btnAddBook.setBounds(10, 215, 103, 26);
		cbbType.setBounds(336, 55, 123, 23);
		btnDeleteBook.setBounds(136, 215, 96, 26);
		btnUpdateBook.setBounds(252, 215, 99, 26);
		cbbSort.setBounds(570, 155, 191, 30);
		cbbFindBy.setBounds(570, 195, 113, 30);
		tfSearchBook.setBounds(570, 235, 470, 30);
		btnSearchBook.setBounds(1048, 234, 103, 30);
		btnResetBook.setBounds(373, 215, 86, 26);
		cbbPublishYear.setBounds(336, 20, 123, 20);
		
		cbbFindBy.setMaximumRowCount(7);
		cbbPublishYear.setMaximumRowCount(13);
		
		cbbPublishYear.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009"}));
		cbbType.setModel(new DefaultComboBoxModel(new String[] {"Giáo trình", "Tài liệu tham khảo", "Luận án", "Luận văn", "Sách bài tập"}));
		cbbSort.setModel(new DefaultComboBoxModel(new String[] {"Sắp xếp", "Tăng dần theo số trang", "Giảm dần theo số trang"}));
		cbbFindBy.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Tên sách", "Tác giả", "Ngôn ngữ", "Năm xuất bản", "Thể loại", "NXB"}));

		pnlBookManagement.add(lblBookName);
		pnlBookManagement.add(lblPageNo);
		pnlBookManagement.add(lblLanguage);
		pnlBookManagement.add(lblPrice);
		pnlBookManagement.add(lblAmount);
		pnlBookManagement.add(lblPublishYear);
		pnlBookManagement.add(lblType);
		pnlBookManagement.add(lblAuthor);
		pnlBookManagement.add(lblPublisher);
		pnlBookManagement.add(tfBookName);
		pnlBookManagement.add(tfPageNo);
		pnlBookManagement.add(tfLanguage);		
		pnlBookManagement.add(tfAmount);
		pnlBookManagement.add(tfPrice);
		pnlBookManagement.add(cbbPublishYear);
		pnlBookManagement.add(tfAuthor);
		pnlBookManagement.add(tfPublisher);
		pnlBookManagement.add(btnAddBook);
		pnlBookManagement.add(cbbType);
		pnlBookManagement.add(btnDeleteBook);
		pnlBookManagement.add(btnUpdateBook);
		pnlBookManagement.add(cbbSort);
		pnlBookManagement.add(cbbFindBy);
		pnlBookManagement.add(tfSearchBook);
		pnlBookManagement.add(btnSearchBook);
		pnlBookManagement.add(btnResetBook);
		findAllBook();
		tabbedPane.addTab("Quản lý sách", pnlBookManagement);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(tabbedPane.getSelectedIndex())
				{
					case 0: 
						findAllBook();
						break;
					case 1:
						findAllReader();
						break;
					case 2:
						findAllLoan();
						break;
					case 3:
						tblModel = new DefaultTableModel();
						table.setModel(tblModel);
						getStatistic();
						break;
				}
			}
		});
		
			//quan ly doc gia
		pnlReaderManagement = new JPanel();
		pnlReaderManagement.setBounds(9, 40, 1166, 270);
		pnlReaderManagement.setLayout(null);
		
		lblReaderName = new JLabel("Tên:");
		lblIdentityCard = new JLabel("CMND:");
		lblPhoneNumber = new JLabel("Số điện thoại:");
		lblPosition = new JLabel("Chức vụ:");
		lblSurname = new JLabel("Họ đệm: ");
		
		rdoLecturer = new JRadioButton("Giảng viên");
		rdoStudent = new JRadioButton("Sinh viên");
		
		tfIdentityCard = new JTextField();
		tfReaderName = new JTextField();
		tfPhoneNumber = new JTextField();
		tfSurname = new JTextField();
		tfSearchReader = new JTextField();
		
		btnAddReader = new JButton("Thêm độc giả");
		btnSearchReader = new JButton("Tìm kiếm");
		
		btnUpdateReader = new JButton("Cập nhật");
		btnUpdateReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				btnUpdateReaderActionPerformed(evt);
			}
		});
		btnDeleteReader = new JButton("Xóa độc giả");
		btnDeleteReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDeleteReaderActionPerformed(evt);
			}
		});
		btnResetReader = new JButton("Nhập lại");
		btnResetReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnResetReaderActionPerformed(evt);
			}
		});
		
		lblReaderName.setBounds(64, 55, 80, 26);
		lblIdentityCard.setBounds(64, 88, 80, 26);
		lblPhoneNumber.setBounds(64, 124, 80, 29);
		lblPosition.setBounds(64, 163, 80, 13);
		lblSurname.setBounds(64, 19, 80, 26);
		rdoLecturer.setBounds(170, 159, 90, 21);
		rdoStudent.setBounds(290, 159, 90, 21);
		tfReaderName.setBounds(170, 55, 246, 26);
		tfIdentityCard.setBounds(170, 91, 246, 26);
		tfPhoneNumber.setBounds(170, 127, 246, 26);
		tfSearchReader.setBounds(570, 233, 470, 31);
		tfSurname.setBounds(170, 19, 246, 26);
		btnAddReader.setBounds(64, 196, 153, 26);
		btnUpdateReader.setBounds(272, 196, 141, 26);
		btnDeleteReader.setBounds(64, 238, 153, 26);
		btnResetReader.setBounds(272, 238, 140, 26);
		rdoStudent.setSelected(true);
		
		gr = new ButtonGroup();
		gr.add(rdoStudent);
		gr.add(rdoLecturer);
		
		pnlReaderManagement.add(lblReaderName);
		pnlReaderManagement.add(lblIdentityCard);
		pnlReaderManagement.add(lblPhoneNumber);
		pnlReaderManagement.add(lblPosition);
		
		
		
		pnlReaderManagement.add(lblSurname);
		pnlReaderManagement.add(rdoLecturer);
		pnlReaderManagement.add(rdoStudent);
		pnlReaderManagement.add(tfReaderName);
		pnlReaderManagement.add(tfIdentityCard);
		pnlReaderManagement.add(tfPhoneNumber);
		pnlReaderManagement.add(tfSurname);
		
		
		
		pnlReaderManagement.add(tfSearchReader);
		pnlReaderManagement.add(btnAddReader);
		pnlReaderManagement.add(btnUpdateReader);
		pnlReaderManagement.add(btnDeleteReader);
		pnlReaderManagement.add(btnResetReader);
		
		tabbedPane.addTab("Quản lý độc giả", pnlReaderManagement);
		
		
		btnSearchReader.setBounds(1050, 233, 102, 31);
		pnlReaderManagement.add(btnSearchReader);
		
		
		//muon tra sach
		pnlLoan = new JPanel();
		pnlLoan.setLayout(null);
		tabbedPane.addTab("Mượn trả sách", pnlLoan);
		
		
		lblReaderId = new JLabel("Mã độc giả:");
		lblBookId = new JLabel("Mã sách:");
		lblReturnAppointmentDate = new JLabel("Ngày hẹn trả:");
		lblOutputReader = new JLabel();
		lblOutputBook = new JLabel();
		tfReaderId = new JTextField();
		tfBookId = new JTextField();
		tfSearchLoan = new JTextField();
		
		tfDay = new JTextField();
		tfMonth = new JTextField();
		tfYear = new JTextField();
		tfDateTime = new JTextField();
		
		
		
		dc = new JDateChooser();
		pnlLoan.add(dc);
		dc.setBounds(175, 130, 148, 20);
		dc.setDateFormatString("yyyy-MM-dd");	
		df = new SimpleDateFormat("yyyy-MM-dd");
		
		btnCheckReaderId = new JButton("Kiểm tra");
		btnCheckReaderId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnTestReaderIdActionPerformed(evt);
				
			}
		});
		btnCheckBookId = new JButton("Kiểm tra");
		btnCheckBookId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnTestBookIdActionPerformed(evt);
			}
		});
		btnLoanBook = new JButton("Mượn sách");
		btnLoanBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
					btnAddLoanActionPerformed(evt);
			}
		});
		btnReturnBook = new JButton("Trả sách");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnReturnBookActionPerformed(evt);
			}
		});
		btnSearchLoan = new JButton("Tìm kiếm");
		btnSearchLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSearchLoanActionPerformed(evt);
			}
		});
		btnResetLoan = new JButton("Nhập lại");
		btnResetLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnResetLoanActionPerformed(evt);
			}
		});
		
		btnPunish = new JButton("DS Phạt");
		btnPunish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				btnPunishActionPerformed(evt);
			}
		});
		cbbLoanNo = new JComboBox();
		
		lblReaderId.setBounds(65, 22, 100, 20);
		lblBookId.setBounds(65, 75, 100, 20);
		lblReturnAppointmentDate.setBounds(65, 130, 148, 20);
		lblOutputReader.setBounds(175, 46, 221, 20);
		lblOutputBook.setBounds(175, 100, 221, 20);
		tfReaderId.setBounds(175, 23, 138, 20);
		tfBookId.setBounds(175, 76, 138, 20);
		tfDateTime.setBounds(217,127,55,28);
		btnCheckReaderId.setBounds(323, 22, 90, 21);
		btnCheckBookId.setBounds(323, 75, 90, 20);
		btnLoanBook.setBounds(65, 183, 100, 28);
		btnReturnBook.setBounds(200, 183, 87, 28);
		tfSearchLoan.setBounds(570, 237, 470, 28);
		btnSearchLoan.setBounds(1051, 236, 100, 28);
		tfDay.setBounds(217,127,55,28);
		tfMonth.setBounds(282,127,55,28);
		tfYear.setBounds(347,127,49,28);
		btnResetLoan.setBounds(319,183,94,28);
		btnPunish.setBounds(200,223,87,28);
		
		
		

		
		pnlLoan.add(lblReaderId);
		pnlLoan.add(lblBookId);
		pnlLoan.add(lblReturnAppointmentDate);
		pnlLoan.add(tfReaderId);
		pnlLoan.add(tfBookId);
		pnlLoan.add(btnCheckReaderId);
		pnlLoan.add(btnCheckBookId);
		pnlLoan.add(btnLoanBook);
		pnlLoan.add(btnReturnBook);

		pnlLoan.add(tfSearchLoan);
		pnlLoan.add(btnSearchLoan);

		pnlLoan.add(lblOutputReader);
		pnlLoan.add(lblOutputBook);
		pnlLoan.add(btnResetLoan);
		pnlLoan.add(btnPunish);

		
		
		//thong ke
		
		
		pnlStatistical = new JPanel();
		pnlStatistical.setLayout(null);
		tabbedPane.addTab("Thống kê", pnlStatistical);
		cont.add(tabbedPane);
		
		lblStatisticTotalBook = new JLabel();
		lblStatisticTotalLoan = new JLabel();
		lblStatisticLoan = new JLabel();
		lblStatisticPunish = new JLabel(); 
		
		lblStatisticTotalBook.setBounds(389,10,200,28);
		lblStatisticTotalLoan.setBounds(389,51,200,28);
		lblStatisticLoan.setBounds(389,89,200,28);
		lblStatisticPunish.setBounds(389,127,200,28);
		
		pnlStatistical.add(lblStatisticTotalBook);
		pnlStatistical.add(lblStatisticTotalLoan);
		pnlStatistical.add(lblStatisticLoan);
		pnlStatistical.add(lblStatisticPunish);
		
		
		
		btnResetBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				
				btnResetBookActionPerformed(evt);
			}
		});
		
		btnSearchBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				btnSearchBookActionPerformed(evt);
			}});
		btnDeleteBook.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				btnDeleteBookActionPerformed(evt);
			}
		});
		
		btnUpdateBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				btnUpdateBookActionPerformed(evt);
			}
		});
		
		btnAddBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				btnAddBookActionPerformed(evt);
			}
		});
		
		btnSearchReader.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				findAllReader();
				
			}
		});
		
		btnAddReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAddReaderActionPerformed(evt);
			}
		});

		setVisible(true);

	}


	
	

	
	private void btnSearchBookActionPerformed(ActionEvent evt)
	{
		String sql;
		String parameter;
		if(tfSearchBook.getText().equals(""))
		{
			findAllBook();
		} else
		{
			if(cbbFindBy.getSelectedItem().equals("Tên sách"))
			{
				sql = "call sp_findBookByName(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Tác giả"))
			{
				sql = "call sp_findBookByAuthor(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Ngôn ngữ"))
			{
				sql = "call sp_findBookByLanguage(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Năm xuất bản"))
			{
				sql = "call sp_findBookByPublishYear(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Thể loại"))
			{
				sql = "call sp_findBookByType(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("NXB"))
			{
				sql = "call sp_findBookByPublisher(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Tất cả"))
			{
				sql = "call sp_findByAllBook(?)";
				parameter = tfSearchBook.getText();
				findBookBy(sql, parameter);
			}
		}	
	}
	
	private void btnResetBookActionPerformed(ActionEvent evt)
	{
		tfBookName.setText(null);
		tfPageNo.setText(null);
		tfLanguage.setText(null);
		tfPrice.setText("0");
		tfAmount.setText(null);
		cbbPublishYear.setSelectedIndex(0);
		cbbType.setSelectedIndex(0);
		tfAuthor.setText(null);
		tfPublisher.setText(null);
	}
	
	private void btnDeleteBookActionPerformed(ActionEvent evt)
	{
		try
		{
			bookModify.deleteBook(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			findAllBook();
		} catch(IndexOutOfBoundsException e)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng chọn hàng cần xóa");
		}
	}
	
	private void btnUpdateBookActionPerformed(ActionEvent evt)
	{
		Book book = new Book();
		if(tfBookName.getText().length()==0  || tfAmount.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Tên sách và số lượng sách không được để trống");
		} 
		else
		{
			book.setBookName(tfBookName.getText());
			if(tfPageNo.getText().length()==0)
			{
				book.setPageNo(null);
			}
			else
			{
				book.setPageNo(tfPageNo.getText());
			}
			
			if(tfLanguage.getText().length()==0)
			{
				book.setLanguage(null);
			}
			else
			{
				book.setLanguage(tfLanguage.getText());
			}
			book.setPrice(Integer.parseInt(tfPrice.getText()));
			book.setAmount(Integer.parseInt(tfAmount.getText()));
			book.setPublishYear(String.valueOf(cbbPublishYear.getSelectedItem()));
			book.setType(String.valueOf(cbbType.getSelectedItem()));
			if(tfAuthor.getText().length()==0)
			{
				book.setAuthor(null);
			}
			else
			{
				book.setAuthor(tfAuthor.getText());
			}
			
			if(tfPublisher.getText().length()==0)
			{
				book.setPublisher(null);
			}
			else
			{
				book.setPublisher(tfPublisher.getText());
			}
			book.setBookId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			bookModify.updateBook(book);
			findAllBook();
		}
		
		
	}
	
	private void btnAddBookActionPerformed(ActionEvent evt)
	{
		Book book = new Book();
		if(tfBookName.getText().length()==0  || tfAmount.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Tên sách và số lượng sách không được để trống");
		} 
		else
		{
			book.setBookName(tfBookName.getText());
			if(tfPageNo.getText().length()==0)
			{
				book.setPageNo(null);
			}
			else
			{
				book.setPageNo(tfPageNo.getText());
			}
			
			if(tfLanguage.getText().length()==0)
			{
				book.setLanguage(null);
			}
			else
			{
				book.setLanguage(tfLanguage.getText());
			}
			book.setPrice(Integer.parseInt(tfPrice.getText()));
			book.setAmount(Integer.parseInt(tfAmount.getText()));
			book.setPublishYear(String.valueOf(cbbPublishYear.getSelectedItem()));
			book.setType(String.valueOf(cbbType.getSelectedItem()));
			if(tfAuthor.getText().length()==0)
			{
				book.setAuthor(null);
			}
			else
			{
				book.setAuthor(tfAuthor.getText());
			}
			
			if(tfPublisher.getText().length()==0)
			{
				book.setPublisher(null);
			}
			else
			{
				book.setPublisher(tfPublisher.getText());
			}
			bookModify.addBook(book);
			findAllBook();
		}
	}
	
	


	
	

	
	
	private void btnAddReaderActionPerformed(ActionEvent evt)
	{
		Reader reader = new Reader();
		if(tfReaderName.getText().length()==0  || tfIdentityCard.getText().length()==0 || tfPhoneNumber.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng điền đầy đủ thông tin");
		} 
		else
		{
			if(tfSurname.getText().length()==0)
			{
				reader.setSurname(null);
			}
			else
			{
				reader.setSurname(tfSurname.getText());
			}
			reader.setName(tfReaderName.getText());
			reader.setIdentityCard(tfIdentityCard.getText());
			reader.setPhoneNo(tfPhoneNumber.getText());
			if(rdoLecturer.isSelected())
			{
				reader.setJob(rdoLecturer.getText());
			} else if(rdoStudent.isSelected())
			{
				reader.setJob(rdoStudent.getText());
			}
			readerModify.addReader(reader);
			findAllReader();
		}

	}
	
	private void btnUpdateReaderActionPerformed(ActionEvent evt)
	{
		Reader reader = new Reader();
		if(tfReaderName.getText().length()==0  || tfIdentityCard.getText().length()==0 || tfPhoneNumber.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng điền đầy đủ thông tin");
		} 
		else
		{
			if(tfSurname.getText().length()==0)
			{
				reader.setSurname(null);
			}
			else
			{
				reader.setSurname(tfSurname.getText());
			}
			reader.setName(tfReaderName.getText());
			reader.setIdentityCard(tfIdentityCard.getText());
			reader.setPhoneNo(tfPhoneNumber.getText());
			if(rdoLecturer.isSelected())
			{
				reader.setJob(rdoLecturer.getText());
			} else if(rdoStudent.isSelected())
			{
				reader.setJob(rdoStudent.getText());
			}
			reader.setReaderId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			readerModify.updateReader(reader);
			findAllReader();
		}
		
	}
	
	private void btnDeleteReaderActionPerformed(ActionEvent evt)
	{
		try
		{
			readerModify.deleteReader(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			findAllReader();
		} catch(IndexOutOfBoundsException e)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng chọn hàng cần xóa");
		}
	}
	
	private void btnResetReaderActionPerformed(ActionEvent evt)
	{
		tfSurname.setText(null);
		tfReaderName.setText(null);
		tfIdentityCard.setText(null);
		tfPhoneNumber.setText(null);
		rdoStudent.setSelected(true);
		
	}
	
	private void btnSearchLoanActionPerformed(ActionEvent evt)
	{
		findAllLoan();
	}
	
	private void btnAddLoanActionPerformed(ActionEvent evt)
	{
		Loan loan = new Loan();
		try
		{
			loan.setReaderId(Integer.parseInt(tfReaderId.getText()));
			loan.setBookId(Integer.parseInt(tfBookId.getText()));

			try
			{

				loan.setBookReturnAppointmentDate(df.format(dc.getDate()));
				if(lblOutputBook.getText().equals("Không tìm thấy sách") || lblOutputReader.getText().equals("Không tìm thấy độc giả"))
				{
					JOptionPane.showInternalMessageDialog(cont, "Mượn sách thất bại\nVui lòng kiểm tra mã độc giả và mã sách");
				} else
				{

				String checkTimeSpace = loanModify.checkTimeSpace(tfReaderId.getText());
				if(checkTimeSpace == null || Integer.parseInt(checkTimeSpace)>=7)
				{
					int addLoan = loanModify.addLoan(loan);
					if(addLoan ==0 )
					{
						JOptionPane.showMessageDialog(this, "Sách này đã hết trong kho");
					}
				} 
				else
				{
					JOptionPane.showInternalMessageDialog(cont, "Mỗi độc giả chỉ được mượn tối đa 3 quyển sách trong 1 tuần");
				}
				}
				
				findAllLoan();
			} catch(Exception e)
			{
				JOptionPane.showInternalMessageDialog(cont, "Ngày hẹn trả không hợp lệ");
			}



		} catch(Exception e)
		{
			JOptionPane.showInternalMessageDialog(cont, "Vui lòng nhập đầy đủ thông tin");
		}
	}
	
	private void btnReturnBookActionPerformed(ActionEvent evt)
	{
		try
		{
		loanModify.returnBook(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
		findAllLoan();
		} catch(Exception e)
		{
			JOptionPane.showInternalMessageDialog(cont, "Vui lòng chọn mã mượn của sách cần trả");
		}
	}
	
	private void btnTestReaderIdActionPerformed(ActionEvent evt)
	{
		try
		{
			Reader reader = loanModify.testReaderId(Integer.parseInt(tfReaderId.getText()));
			if(reader.getName().equals(""))
			{
				lblOutputReader.setText("Không tìm thấy độc giả");
				lblOutputReader.setForeground(Color.RED);
			}
			else
			{
				lblOutputReader.setText(reader.getName());
				lblOutputReader.setForeground(Color.GREEN);
			}
		} catch(Exception e)
		{
			lblOutputReader.setText("Không tìm thấy độc giả");
			lblOutputReader.setForeground(Color.RED);
		}
	}
	
	private void btnTestBookIdActionPerformed(ActionEvent evt)
	{
		try
		{
			Book book = loanModify.testBookId(Integer.parseInt(tfBookId.getText()));
			if(book.getBookName().equals(""))
			{
				lblOutputBook.setText("Không tìm thấy sách");
				lblOutputBook.setForeground(Color.GREEN);
			}
			lblOutputBook.setText(book.getBookName());
			lblOutputBook.setForeground(Color.GREEN);
		} catch(Exception e)
		{
			lblOutputBook.setText("Không tìm thấy sách");
			lblOutputBook.setForeground(Color.RED);
		}
	}
	
	
	private void btnResetLoanActionPerformed(ActionEvent evt)
	{
		tfReaderId.setText(null);
		tfBookId.setText(null);

		lblOutputReader.setText(null);
		lblOutputBook.setText(null);
//		dc.setCalendar(null);
	}
	
	private void btnPunishActionPerformed(ActionEvent evt)
	{
		punish();
	}
	
	
	public void sortAZPageNo()
	{
		Vector<Book> bookList = new Vector();
		if(cbbSort.getSelectedItem().equals("Tăng dần theo số trang"))
		{
			bookList = bookModify.sortAZPageNo("call sp_sortAZPageNo");
		}
		else
		if(cbbSort.getSelectedItem().equals("Giảm dần theo số trang"))
		{
			bookList = bookModify.sortAZPageNo("call sp_sortZAPageNo");
		}
		String[] columnNames = {"Mã sách","Tên sách","Số trang","Ngôn ngữ","Giá trị","Số lượng còn","Năm xuất bản","Thể loại","Tác giả","Nhà xuất bản"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Book book: bookList)
		{
			tblModel.addRow(new Object[] {book.getBookId(),book.getBookName(),book.getPageNo(),book.getLanguage(),book.getPrice(),
					book.getAmount(),book.getPublishYear(),book.getType(),book.getAuthor(),book.getPublisher()});
		}
		
	}
	//find information
	
	public void findAllBook()
	{
		Vector<Book> bookList = bookModify.getBookList();
		String[] columnNames = {"Mã sách", "Tên sách", "Số trang", "Ngôn ngữ", "Giá trị", "Số lượng còn", "Năm xuất bản", "Thể loại", "Tác giả", "Nhà xuất bản"}; 
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);	
		for(Book book: bookList)
		{
			tblModel.addRow(new Object[] {book.getBookId(),book.getBookName(),book.getPageNo(),book.getLanguage(),book.getPrice(),
											book.getAmount(),book.getPublishYear(),book.getType(),book.getAuthor(),book.getPublisher()});
		}
	}
	
	
	public void findBookBy(String sql, String parameter)
	{
		Vector<Book> bookList = bookModify.findBookBy(sql, parameter);
		String[] columnNames = {"Mã sách", "Tên sách", "Số trang", "Ngôn ngữ", "Giá trị", "Số lượng còn", "Năm xuất bản", "Thể loại", " Tác giả", " Nhà xuất bản"}; 
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		
		for(Book book: bookList)
		{
			tblModel.addRow(new Object[] {book.getBookId(),book.getBookName(),book.getPageNo(),book.getLanguage(),book.getPrice(),
											book.getAmount(),book.getPublishYear(),book.getType(),book.getAuthor(),book.getPublisher()});
		}
	}
	
	public void findAllReader()
	{
		Vector<Reader> readerList = null;
		if(btnSearchReader.getText().equals(""))
		{
			readerList = readerModify.getReaderList();
		} else
		{
			readerList = readerModify.findReaderByAll(tfSearchReader.getText());
		}
		String[] columnNames = {"Mã độc giả", "Họ đệm", 
				"Tên", "CMND", "SĐT", "Ngày cấp thẻ", "Chức vụ"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Reader reader: readerList)
		{
			tblModel.addRow(new Object[] {reader.getReaderId(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhoneNo(), reader.getCardIssueDate(), reader.getJob()});
		}
		
		
	}
	
	public void findAllLoan()
	{
		Vector<Loan> loanList = null;
		if(tfSearchLoan.getText().equals(""))
		{
			loanList = loanModify.getLoanlist();
		} else
		{
			loanList = loanModify.findLoanByAll(tfSearchLoan.getText());
		}
		String[] columnNames = {"Mã mượn","Mã độc giả","Mã sách", "Số lượng mượn", "Ngày mượn", "Ngày hẹn trả", "Ngày trả", "Trạng thái"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Loan loan: loanList)
		{
			tblModel.addRow(new Object[] {loan.getLoanId(), loan.getReaderId(), loan.getBookId(), loan.getLoanNo(), loan.getLoanDate(), loan.getBookReturnAppointmentDate(), loan.getBookReturnDate(), loan.getStatus()});
		}
	}
	
	public void punish()
	{
		Vector<Punishment> punishmentList1 = punishmentModify.getPunishmentList();
		Vector<Punishment> punishmentList2 = punishmentModify.getPunishmentListReturnYet();
		String[] columnNames = {"Mã mượn","Mã độc giả","Tên độc giả","Mã sách","Tên sách","Số lượng mượn","Quá hạn (ngày)","Thành tiền (đồng)", "Trạng thái sách"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Punishment punishment : punishmentList1)
		{
			tblModel.addRow(new Object[] {punishment.getLoanId(),punishment.getReaderId(),punishment.getFullname(),punishment.getBookId(),punishment.getBook(),punishment.getLoanNo(),punishment.getDaysLate(),punishment.getTotal(),punishment.getStatus()});
		}
		
		for(Punishment punishment: punishmentList2)
		{
			tblModel.addRow(new Object[] {punishment.getLoanId(),punishment.getReaderId(),punishment.getFullname(),punishment.getBookId(),punishment.getBook(),punishment.getLoanNo(),punishment.getDaysLate(),punishment.getTotal(),punishment.getStatus()});
		}
	}
	
	public void getStatistic()
	{
		lblStatisticTotalBook.setText("Tổng số sách: "+statisticModify.getStatisticTotalBook());
		lblStatisticTotalLoan.setText("Tổng số phiếu mượn sách: "+statisticModify.getStatisticTotalLoan());
		lblStatisticLoan.setText("Sách đang cho mượn: "+statisticModify.getStatisticLoan());
		lblStatisticPunish.setText("Sách bị trễ hạn trả: "+statisticModify.getStatisticPunish());
	}
	

	public static void main(String[] args)
	{
		new MainFrame();
	}
}
