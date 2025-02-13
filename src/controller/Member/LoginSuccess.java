package controller.Member;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import model.Member;
import controller.Porder.PorderMainUI;

public class LoginSuccess extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Member member;  // 儲存傳入的 Member 物件

    /**
     * Create the frame.
     */
    public LoginSuccess(Member member) {
        this.member = member; // 儲存傳入的登入會員資料

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // 在畫面上顯示歡迎訊息，例如："{name}，你好"
        JLabel welcomeLabel = new JLabel(member.getName() + "，你好");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        welcomeLabel.setBounds(90, 30, 250, 35);
        contentPane.add(welcomeLabel);

        // 原有的 "登入成功" 標題可以保留或移除
        JLabel lblNewLabel = new JLabel("登入成功");
        lblNewLabel.setBounds(159, 76, 106, 35);
        lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("繼續");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PorderMainUI pordermainui = new PorderMainUI();
                pordermainui.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(248, 180, 85, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("回主畫面");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginUI loginui = new LoginUI();
                loginui.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setBounds(112, 180, 85, 23);
        contentPane.add(btnNewButton_1);
    }

    // 如果需要 main 方法來測試，請自行傳入一個測試用的 Member
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 測試用 Member 物件
                    Member testMember = new Member();
                    testMember.setName("John");
                    LoginSuccess frame = new LoginSuccess(testMember);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
