package controller.Porder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import dao.impl.MemberDaoImpl;
import dao.impl.PorderDaoImpl;
import model.Member;
import model.Porder;
import util.Tool;


public class AddPorderUI extends JFrame {

	private static PorderDaoImpl porderdaoimpl = new PorderDaoImpl();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField chair;
	private JTextField table;
	private JTextField bed;
	private JTextField shoe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPorderUI frame = new AddPorderUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddPorderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(249, 189, 205));
		panel.setBounds(73, 10, 287, 44);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("新增訂單");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(209, 248, 247));
		panel_1.setBounds(37, 72, 350, 231);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("椅子:");
		lblNewLabel_1.setBounds(10, 20, 41, 25);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("桌子:");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 72, 41, 25);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("床墊:");
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 122, 41, 25);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("鞋櫃:");
		lblNewLabel_1_3.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 181, 41, 25);
		panel_1.add(lblNewLabel_1_3);
		
		chair = new JTextField();
		chair.setBounds(61, 20, 96, 21);
		panel_1.add(chair);
		chair.setColumns(10);
		((javax.swing.text.AbstractDocument) chair.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		table = new JTextField();
		table.setColumns(10);
		table.setBounds(61, 79, 96, 21);
		panel_1.add(table);
		((javax.swing.text.AbstractDocument) table.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		bed = new JTextField();
		bed.setColumns(10);
		bed.setBounds(61, 129, 96, 21);
		panel_1.add(bed);
		((javax.swing.text.AbstractDocument) bed.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		shoe = new JTextField();
		shoe.setColumns(10);
		shoe.setBounds(61, 181, 96, 21);
		panel_1.add(shoe);
		((javax.swing.text.AbstractDocument) shoe.getDocument()).setDocumentFilter(new NumericDocumentFilter());
		
		JButton btnNewButton = new JButton("回主畫面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PorderMainUI pordermainui = new PorderMainUI();
				pordermainui.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(229, 22, 85, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("確定");

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//	String Name=Member.getName();
				int Chair=parseIntOrZero(chair.getText());
				int Bed=parseIntOrZero(bed.getText());
				int Shoe=parseIntOrZero(shoe.getText());
				int Table=parseIntOrZero(table.getText());

                Porder pp = new Porder(Chair , Bed, Shoe, Table);
                porderdaoimpl.add(pp);
                
                JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				
			//	Porder p=new Porder(Name,chair,bed,shoe,table);
				
			//	porderserviceimpl.addPorder(p);
				
			}
		});
		btnNewButton_1.setBounds(229, 187, 85, 23);
		panel_1.add(btnNewButton_1);
	}
	
	private int parseIntOrZero(String text) {
	    try {
	        text = text.trim();
	        if(text.isEmpty()){
	            return 0;
	        }
	        return Integer.parseInt(text);
	    } catch(NumberFormatException e) {
	        return 0;
	    }
	}
	
	public class NumericDocumentFilter extends DocumentFilter {
	    @Override
	    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	            throws BadLocationException {
	        if (string != null && string.matches("[0-9]*")) {
	            super.insertString(fb, offset, string, attr);
	        }
	    }
	    
	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	            throws BadLocationException {
	        if (text != null && text.matches("[0-9]*")) {
	            super.replace(fb, offset, length, text, attrs);
	        }
	    }
	}
}
