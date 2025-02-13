package controller.Porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import dao.impl.PorderDaoImpl;
import model.Porder;

//import service.impl.PorderServiceImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class PorderManagerUI extends JFrame {

	private static PorderDaoImpl porderdaoimpl = new PorderDaoImpl();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id_field;
	private JTextField shoe;
	private JTextField chair;
	private JTextField table;
	private JTextField bed;
	private JTextField delId;
	private JTable output;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PorderManagerUI frame = new PorderManagerUI();
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
	public PorderManagerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 408);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(193, 237, 120));
		panel_4.setBounds(0, 213, 535, 93);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 10, 46, 15);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("桌子:");
		lblNewLabel_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(206, 35, 46, 15);
		panel_4.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("床墊:");
		lblNewLabel_1_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(206, 68, 46, 15);
		panel_4.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("鞋櫃:");
		lblNewLabel_1_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 68, 46, 15);
		panel_4.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("椅子:");
		lblNewLabel_1_1.setBounds(206, 10, 46, 15);
		panel_4.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		
		id_field = new JTextField();
		id_field.setBounds(55, 11, 96, 21);
		panel_4.add(id_field);
		id_field.setColumns(10);
		
		shoe = new JTextField();
		shoe.setColumns(10);
		shoe.setBounds(55, 62, 96, 21);
		panel_4.add(shoe);
		
		chair = new JTextField();
		chair.setColumns(10);
		chair.setBounds(267, 10, 96, 21);
		panel_4.add(chair);
		
		table = new JTextField();
		table.setColumns(10);
		table.setBounds(267, 36, 96, 21);
		panel_4.add(table);
		
		bed = new JTextField();
		bed.setColumns(10);
		bed.setBounds(267, 69, 96, 21);
		panel_4.add(bed);
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Id=Integer.parseInt(id_field.getText());
				int Bed=Integer.parseInt(bed.getText());
				int Table=Integer.parseInt(table.getText());
				int Shoe=Integer.parseInt(shoe.getText());
				int Chair=Integer.parseInt(chair.getText());
				
				Porder pp = new Porder(Chair , Bed, Shoe, Table);
				pp.setId(Id);
				
				porderdaoimpl.update(pp);
				
				JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			    
			}
		});
		btnNewButton_1.setBounds(429, 61, 85, 23);
		panel_4.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(137, 181, 111));
		panel.setBounds(0, 0, 535, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("訂單管理系統");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		lblNewLabel.setBounds(183, 10, 239, 35);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 64, 535, 113);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(182, 224, 158));
		panel_3.setBounds(0, 71, 535, 138);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnPrint = new JButton("列印");
		btnPrint.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // 取得 JTable 模型，檢查是否有資料
		        DefaultTableModel model = (DefaultTableModel) output.getModel();
		        if (model.getRowCount() == 0) {
		            JOptionPane.showMessageDialog(null, "無查詢資料，無法列印", "錯誤", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        try {
		            // 利用 JTable 的 print 方法進行列印
		            // FIT_WIDTH: 表格內容會根據列印紙張寬度自動調整
		            boolean complete = output.print(JTable.PrintMode.FIT_WIDTH, 
		                new java.text.MessageFormat("Porder Data"), 
		                null);
		            if (complete) {
		                JOptionPane.showMessageDialog(null, "列印完成", "提示", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "列印已取消", "提示", JOptionPane.INFORMATION_MESSAGE);
		            }
		        } catch (java.awt.print.PrinterException pe) {
		            JOptionPane.showMessageDialog(null, "列印失敗: " + pe.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		btnPrint.setBounds(334, 105, 85, 23);
		panel_3.add(btnPrint);

		
		
		// 請確保已 import javax.swing.text.AbstractDocument;
		((javax.swing.text.AbstractDocument) id_field.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());
		((javax.swing.text.AbstractDocument) shoe.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());
		((javax.swing.text.AbstractDocument) chair.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());
		((javax.swing.text.AbstractDocument) table.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());
		((javax.swing.text.AbstractDocument) bed.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());

		
		JButton btnNewButton = new JButton("查詢");
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int id = 0;
		        try {
		            id = Integer.parseInt(id_field.getText().trim());
		        } catch (NumberFormatException ex) {
		            id = 0;
		        }
		        
		        List<Porder> list;
		        if (id == 0) {
		            // 當 id 為 0 時，查詢全部
		            list = porderdaoimpl.selectAll();
		        } else {
		            // 否則直接呼叫 selectById(id)
		            list = porderdaoimpl.selectById(id);
		        }
		        
		        // 建立表格模型並設定欄位名稱
		        DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("ID");
		        model.addColumn("Chair");
		        model.addColumn("Bed");
		        model.addColumn("Shoe");
		        model.addColumn("Table");
		        
		        // 將每筆資料加入模型中
		        for (Porder p : list) {
		            model.addRow(new Object[]{
		                p.getId(),
		                p.getChair(),
		                p.getBed(),
		                p.getShoe(),
		                p.getTable()
		            });
		        }
		        
		        // 將模型設定到 output (JTable) 上
		        output.setModel(model);
		        
		        JOptionPane.showMessageDialog(null, "查詢成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		    }
		});




		btnNewButton.setBounds(428, 105, 85, 23);
		panel_3.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 515, 85);
		panel_3.add(scrollPane);
		
		output = new JTable();
		scrollPane.setViewportView(output);
		
		JButton btnPrint_1_1 = new JButton("回主頁");
		btnPrint_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PorderMainUI pordermainui = new PorderMainUI();
				pordermainui.setVisible(true);
                dispose();
			}
		});
		btnPrint_1_1.setBounds(10, 105, 85, 23);
		panel_3.add(btnPrint_1_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(140, 238, 180));
		panel_5.setBounds(0, 309, 535, 62);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID:");
		lblNewLabel_1_2.setBounds(188, 26, 53, 23);
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		panel_5.add(lblNewLabel_1_2);
		
		delId = new JTextField();
		delId.setColumns(10);
		delId.setBounds(228, 31, 96, 21);
		panel_5.add(delId);
		
		// 請確保已 import javax.swing.text.AbstractDocument;
		((javax.swing.text.AbstractDocument) delId.getDocument()).setDocumentFilter(new NumericAndSpaceDocumentFilter());
		
		JButton btnNewButton_1_1 = new JButton("刪除");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Id=Integer.parseInt(delId.getText());
				//PorderServiceImpl.deletePorderById(id);
				porderdaoimpl.delete(Id);
				
				JOptionPane.showMessageDialog(null, "刪除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1_1.setBounds(428, 29, 85, 23);
		panel_5.add(btnNewButton_1_1);
		
		JButton btnPrint_1 = new JButton("退出");
		btnPrint_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnPrint_1.setBounds(10, 30, 85, 23);
		panel_5.add(btnPrint_1);
	}
	
	public class NumericAndSpaceDocumentFilter extends DocumentFilter {
	    @Override
	    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	            throws BadLocationException {
	        if (string != null && string.matches("[0-9\\s]*")) {
	            super.insertString(fb, offset, string, attr);
	        }
	    }
	    
	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	            throws BadLocationException {
	        if (text != null && text.matches("[0-9\\s]*")) {
	            super.replace(fb, offset, length, text, attrs);
	        }
	    }
	    
	    
	}
}
