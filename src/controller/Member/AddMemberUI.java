package controller.Member;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.impl.MemberServiceImpl;

public class AddMemberUI extends JFrame {

    private static MemberDaoImpl memberdaoimpl = new MemberDaoImpl();
    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private JTextField name;
    private JTextField username;
    private JTextField password;
    private JTextField phone;
    private JTextField address;
    private JTextField email;

    // 參數設定：時鐘寬150、高30，與右下角間隔10px
    private final int clockWidth = 150;
    private final int clockHeight = 30;
    private final int clockMargin = 10;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddMemberUI frame = new AddMemberUI();
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
    public AddMemberUI() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 419);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        // 使用絕對布局
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ----------------------------
        // 利用 glass pane 加入時鐘，不影響 contentPane 的佈局
        // ----------------------------
        // 建立自訂的 glass pane，不攔截滑鼠事件
        JPanel glass = new JPanel(null) {
            @Override
            public boolean contains(int x, int y) {
                // 讓所有滑鼠事件直接傳遞到下層元件
                return false;
            }
        };
        glass.setOpaque(false);
        setGlassPane(glass);
        glass.setVisible(true);

        // 建立時鐘 Label（設定邊框、背景為白色）
        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setOpaque(true);
        clockLabel.setBackground(Color.WHITE);
        clockLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        // 初始位置暫設 0,0；稍後依照視窗尺寸重新計算
        clockLabel.setBounds(0, 0, clockWidth, clockHeight);
        glass.add(clockLabel);

        // Timer 每秒更新時鐘
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            @Override
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();

        // ----------------------------
        // 其他 UI 元件（例如 header 區與各個輸入欄位）
        // ----------------------------
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(162, 216, 242));
        headerPanel.setBounds(56, 10, 507, 51);
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);

        JLabel lblTitle = new JLabel("會員註冊系統");
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        lblTitle.setBounds(194, 10, 153, 36);
        headerPanel.add(lblTitle);

        JButton btnBack = new JButton("回主畫面");
        btnBack.setBounds(412, 22, 85, 23);
        headerPanel.add(btnBack);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                LoginUI loginui = new LoginUI();
                loginui.setVisible(true);
                dispose();
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(193, 208, 240));
        panel_1.setBounds(55, 86, 508, 270);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblName = new JLabel("姓名:");
        lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblName.setBounds(22, 23, 55, 23);
        panel_1.add(lblName);

        JLabel lblUsername = new JLabel("帳號:");
        lblUsername.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblUsername.setBounds(22, 56, 55, 23);
        panel_1.add(lblUsername);

        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblPassword.setBounds(22, 94, 55, 23);
        panel_1.add(lblPassword);

        JLabel lblPhone = new JLabel("電話:");
        lblPhone.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblPhone.setBounds(22, 130, 55, 23);
        panel_1.add(lblPhone);

        JLabel lblAddress = new JLabel("地址:");
        lblAddress.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblAddress.setBounds(22, 163, 55, 23);
        panel_1.add(lblAddress);

        JLabel lblEmail = new JLabel("電子信箱:");
        lblEmail.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblEmail.setBounds(22, 200, 81, 23);
        panel_1.add(lblEmail);

        name = new JTextField();
        name.setBounds(97, 25, 125, 21);
        panel_1.add(name);
        name.setColumns(10);

        username = new JTextField();
        username.setColumns(10);
        username.setBounds(97, 61, 125, 21);
        panel_1.add(username);

        password = new JTextField();
        password.setColumns(10);
        password.setBounds(97, 99, 125, 21);
        panel_1.add(password);

        phone = new JTextField();
        phone.setColumns(10);
        phone.setBounds(97, 135, 125, 21);
        panel_1.add(phone);

        address = new JTextField();
        address.setColumns(10);
        address.setBounds(97, 169, 176, 21);
        panel_1.add(address);

        email = new JTextField();
        email.setColumns(10);
        email.setBounds(97, 205, 186, 21);
        panel_1.add(email);

        JButton btnRegister = new JButton("註冊");
        btnRegister.setBounds(338, 204, 85, 23);
        panel_1.add(btnRegister);
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String Username = username.getText();
                if (new MemberServiceImpl().isUsernameBeenUse(Username)) {
                    AddMemberErrorUI addmembererror = new AddMemberErrorUI();
                    addmembererror.setVisible(true);
                    dispose();
                } else if (!validateInput(name, username, password, phone, address, email)) {
                    // 驗證失敗，錯誤訊息由 validateInput() 顯示
                } else {
                    String Name = name.getText();
                    String Password = password.getText();
                    String Address = address.getText();
                    String Phone = phone.getText();
                    String Email = email.getText();

                    Member m = new Member(Name, Username, Password, Phone, Address, Email);
                    memberdaoimpl.add(m);
                    AddMemberSuccess addmembersuccess = new AddMemberSuccess();
                    addmembersuccess.setVisible(true);
                    dispose();
                }
            }
        });

        // ----------------------------
        // 更新時鐘位置至右下角
        // ----------------------------
        updateClockPosition(clockLabel);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateClockPosition(clockLabel);
            }
        });
    }

    /**
     * 根據 glass pane 的大小，將時鐘放置在右下角，
     * 與右下邊緣各留 clockMargin 的間距
     */
    private void updateClockPosition(JLabel clockLabel) {
        int glassWidth = getGlassPane().getWidth();
        int glassHeight = getGlassPane().getHeight();
        int newX = glassWidth - clockWidth - clockMargin;
        int newY = glassHeight - clockHeight - clockMargin;
        clockLabel.setBounds(newX, newY, clockWidth, clockHeight);
    }

    /**
     * 驗證輸入欄位是否符合規範
     */
    private boolean validateInput(JTextField name, JTextField username, JTextField password,
                                  JTextField phone, JTextField address, JTextField email) {
        String name_v = name.getText().trim();
        String username_v = username.getText().trim();
        String password_v = password.getText().trim();
        String phone_v = phone.getText().trim();
        String address_v = address.getText().trim();
        String email_v = email.getText().trim();

        if (name_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "姓名 欄位不能為空");
            return false;
        }
        if (username_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "帳號 欄位不能為空");
            return false;
        }
        if (password_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "密碼 欄位不能為空");
            return false;
        }
        if (phone_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "電話 欄位不能為空");
            return false;
        }
        if (address_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "地址 欄位不能為空");
            return false;
        }
        if (email_v.isEmpty()) {
            JOptionPane.showMessageDialog(this, "電子信箱 欄位不能為空");
            return false;
        }

        // 正規表示式檢查
        String namePattern = "^[A-Za-z\\u4e00-\\u9fa5]+$";
        String usernamePattern = "^[A-Za-z]+$";
        // 修改密碼驗證：必須至少包含一個大寫字母且長度大於六個字（至少7個字）
        String passwordPattern = "^(?=.*[A-Z]).{7,}$";
        String phonePattern = "^\\d{4}-\\d{3}-\\d{3}$";
        String emailPattern = "^[A-Za-z0-9._%+-]+@gmail\\.com$";

        if (!Pattern.matches(namePattern, name_v)) {
            JOptionPane.showMessageDialog(this, "姓名 欄位必須為中文或英文");
            return false;
        }
        if (!Pattern.matches(usernamePattern, username_v)) {
            JOptionPane.showMessageDialog(this, "帳號 欄位只能輸入英文");
            return false;
        }
        if (!Pattern.matches(passwordPattern, password_v)) {
            JOptionPane.showMessageDialog(this, "密碼 必須包含至少一個大寫字母且長度大於六個字", "錯誤", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Pattern.matches(phonePattern, phone_v)) {
            JOptionPane.showMessageDialog(this, "電話 欄位格式錯誤，必須是 09xx-xxx-xxx 的格式");
            return false;
        }
        if (!Pattern.matches(emailPattern, email_v)) {
            JOptionPane.showMessageDialog(this, "電子信箱 欄位格式錯誤，必須是類似 xxxxx@gmail.com 的格式");
            return false;
        }
        return true;
    }
}
