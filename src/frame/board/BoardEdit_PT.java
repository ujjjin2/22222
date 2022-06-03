package frame.board;
//PT게시판 댓글 - 202045016 허유진

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import frame.db.DB;
import frame.db.DBPT;


public class BoardEdit_PT extends JFrame implements ActionListener, WindowListener{
	private Font mainFont;
	private JTextArea taboard, tacomment;
	private JScrollPane sp,sp2;
	private Color skyblue;
	private JButton btnedit, btnDelete, btnSend;
	private JPanel panelCup, panelCN, panelS1, panelS2, panelS3, TextPanel, SouthPanel, NorthPanel;
	private JLabel lblcomment;
	private JTextField tfcomment;
	private JTextField[] TxField;
	private ArrayList<String> alpt;
	private Object bdi;
	private String namept, ID;
	private ImageIcon img1,imgcomment;
	
	
	public BoardEdit_PT(ArrayList<String> alpt, String ID) {
		this.alpt = alpt;
		this.ID = ID;
		//this.namept = namept;
		
		setTitle("게시물 댓글 및 삭제");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(450, 643);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("210 맨발의청춘 L", 0, 16);
		
		addWindowListener(this);
		setResizable(false); 
		
		setNorth();
		setCenter();
		setSouth();
		
		setVisible(true);
	}


	private void setNorth() {
		 NorthPanel = new JPanel();
	      NorthPanel.setPreferredSize(new Dimension(300, 150));
	      NorthPanel.setLayout(null);
	      NorthPanel.setPreferredSize(new DimensionUIResource(100,160));
	      NorthPanel.setBackground(skyblue);
	      
	      String[] name = {"제목", "작성일자", "작성자"}; 
	      
	      JLabel[] TxValue = new JLabel[3];
	   
	      int x = 20;
	      int y = 10;
	      
	      for(int i = 0; i < TxValue.length; i++, y+=60) {
	         TxValue[i] = new JLabel(name[i]);
	         TxValue[i].setBounds(x, y, 100, 25);
	         TxValue[i].setFont(mainFont);
	         NorthPanel.add(TxValue[i]);
	      }
	      
  
	      TxField = new JTextField[3];//tf 텍스트
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length  ; i++, y+=60) {
	         TxField[i] =  new JTextField(15);
	         TxField[i].setBounds(x, y, 310, 20);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
	         TxField[i].setText(alpt.get(i+1));//여기
	         TxField[i].setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
	         TxField[i].setEditable(false);//변경불가
	         NorthPanel.add(TxField[i]);
	      }
	      
	      
	    img1 = new ImageIcon("imges/textimage_edit.png");
		JLabel lbl1 = new JLabel(img1);
		JLabel lbl2 = new JLabel(img1);
		JLabel lbl3 = new JLabel(img1);
		//JLabel lbl4 = new JLabel(img1);
	      
	      JLabel[] lblimg  ={lbl1 , lbl2 , lbl3};
	      
	      x = 90;
	      y = 1;
	      
	      for(int i = 0; i < lblimg.length; i++, y+=60) {
	    	  lblimg[i].setBounds(x, y, 330, 40);
	    	  NorthPanel.add(lblimg[i]);
	      }
	      
	          
	      add(NorthPanel, BorderLayout.NORTH);
		
	}

	private void setCenter() {
		TextPanel = new JPanel();
		TextPanel.setBackground(skyblue);
	    TextPanel.setLayout(new BorderLayout()); 
		
		panelCup = new JPanel();//글쓰기 파트
		panelCup.setBackground(skyblue);
		taboard =new  JTextArea(8,30);
		taboard.setText(alpt.get(4));//여기
		taboard.setEditable(false);//변경불가
		taboard.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		taboard.setLineWrap(true);
		sp = new JScrollPane(taboard, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelCup.add(sp);
		TextPanel.add(panelCup,BorderLayout.CENTER);
		
		
		panelCN = new JPanel();
		panelCN.setBackground(skyblue);
		panelCN.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//수정버튼
		btnedit = new JButton(new ImageIcon("imges/edit2.png"));
		btnedit.addActionListener(this);
		btnedit.setBorderPainted(false);
		btnedit.setContentAreaFilled(false);

		panelCN.add(btnedit);
		
		//삭제버튼
		btnDelete = new JButton(new ImageIcon("imges/delete2.png"));
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(this);
		
		panelCN.add(btnDelete);
		TextPanel.add(panelCN, BorderLayout.NORTH);
		
	      add(TextPanel, BorderLayout.CENTER);
		
	}
	
	private void setSouth() {
		SouthPanel = new JPanel();
		SouthPanel.setLayout(new BorderLayout());
		
		panelS1 = new JPanel();
		panelS1.setBackground(skyblue);
		panelS1.setLayout(new FlowLayout(FlowLayout.LEFT,20,7));
		lblcomment = new JLabel("댓글");
		lblcomment.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 13));
		panelS1.add(lblcomment);
		SouthPanel.add(panelS1, BorderLayout.NORTH);
		
		panelS2 = new JPanel();
		panelS2.setBackground(skyblue);
		tacomment =new  JTextArea(8,30);
		tacomment.setEditable(false);//변경불가
		tacomment.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		tacomment.setLineWrap(true);
		sp2 = new JScrollPane(tacomment, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS2.add(sp2);
		SouthPanel.add(panelS2, BorderLayout.CENTER);
		
		
		//마지막 부분 답글 텍스트 있는 부분!
		panelS3 = new JPanel();
		panelS3.setLayout(null);
		panelS3.setPreferredSize(new DimensionUIResource(100, 70));
		panelS3.setBackground(skyblue);
		
		tfcomment = new JTextField(20);
		tfcomment.setBounds(15, 20, 300, 20);
		tfcomment.setBorder(BorderFactory.createEmptyBorder());
		tfcomment.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		tfcomment.addActionListener(this);
		panelS3.add(tfcomment);
		
		//답글 이미지
		imgcomment = new ImageIcon("imges/textimage_edit.png");
		JLabel lblcomment = new JLabel(imgcomment);
		lblcomment.setBounds(8, 3, 330, 50);
		panelS3.add(lblcomment);
		
		btnSend = new JButton(new ImageIcon("imges/comment2_3.png"));	
		btnSend.setBounds(330, 8, 100, 40);
		btnSend.setBorderPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.addActionListener(this);
		
		panelS3.add(btnSend);
		SouthPanel.add(panelS3, BorderLayout.SOUTH);
		
		
		SouthPanel.setBackground(skyblue);
		add(SouthPanel, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//Boardwrite2 bw2 = new Boardwrite2(null);
		if(obj == tfcomment || obj == btnSend) {
			tacomment.append(tfcomment.getText() + "\n");
			//tfcomment.setText("");
			
			//현재 선택한 게시물의 글번호를 가져옴
			bdi = Integer.parseInt(alpt.get(0).toString());
			System.out.println(bdi);
		
			DBPT dbpt = new DBPT(this);
			//int in = Integer.parseInt(bd.getAl().get(0));
			System.out.println(alpt.get(1));
			
			dbpt.BDCMT(alpt.get(0), tfcomment.getText(),namept);
			tfcomment.setText("");
			dbpt.DisplayCMT(alpt.get(0));
		
	}else if (btnedit == obj) {
		BoardWrite_PT2 bw2 = new BoardWrite_PT2(alpt, ID);
		
	}else if (btnDelete == obj) //삭제 기능 - 확인하는 메세지창
	{
		if (JOptionPane.showConfirmDialog (this, 
					"게시물을 삭제하시겠습니까?",
					"글삭제",
					JOptionPane.YES_NO_OPTION
				) == JOptionPane.YES_OPTION) {
			
			DBPT dbpt = new DBPT(null);
			dbpt.PT_DELETE(alpt.get(0));
			this.dispose();
		}
		
	}
}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		Board2_PT bd = new Board2_PT(null);
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JTextArea getTa2() {
		return tacomment;
	}
	
}