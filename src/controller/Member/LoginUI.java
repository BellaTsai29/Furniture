package controller.Member;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Member;
import service.impl.MemberServiceImpl;

public class LoginUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField username;
    private JTextField password;
    
    // 新增驗證碼相關元件
    private JTextField captchaInput;
    private CaptchaPanel captchaPanel;
    private String currentCaptcha;
    
    // 時鐘參數：寬150、高30，右下邊距10px
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
                    LoginUI frame = new LoginUI();
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
    public LoginUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 584, 386);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        // 原有布局採用絕對定位，不作改動
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        // ----------------------------
        // 利用 glass pane 加入時鐘（右下角） 不影響 contentPane 佈局
        // ----------------------------
        JPanel glass = new JPanel(null) {
            @Override
            public boolean contains(int x, int y) {
                // 不攔截滑鼠事件，讓事件傳到下層
                return false;
            }
        };
        glass.setOpaque(false);
        setGlassPane(glass);
        glass.setVisible(true);
        
        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setOpaque(true);
        clockLabel.setBackground(Color.WHITE);
        clockLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        // 初始位置暫設，稍後調整
        clockLabel.setBounds(0, 0, clockWidth, clockHeight);
        glass.add(clockLabel);
        
        Timer timer = new Timer(1000, new ActionListener(){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            @Override
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                updateClockPosition(clockLabel);
            }
        });
        updateClockPosition(clockLabel);
        
        // ----------------------------
        // 以下為原有的 UI 元件（保持不變）
        // ----------------------------
        JPanel panel = new JPanel();
        panel.setBackground(new Color(31, 205, 224));
        panel.setBounds(98, 31, 388, 58);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("匠心家具");
        lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        lblNewLabel.setBounds(139, 10, 114, 43);
        panel.add(lblNewLabel);
        
        JLabel lblAccount = new JLabel("帳號:");
        lblAccount.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        lblAccount.setBounds(58, 125, 71, 45);
        contentPane.add(lblAccount);
        
        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        lblPassword.setBounds(58, 207, 71, 45);
        contentPane.add(lblPassword);
        
        username = new JTextField();
        username.setBounds(139, 137, 117, 29);
        contentPane.add(username);
        username.setColumns(10);
        
        password = new JTextField();
        password.setColumns(10);
        password.setBounds(139, 219, 117, 29);
        contentPane.add(password);
        
        JButton btnLogin = new JButton("登入");
        btnLogin.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String user = username.getText();
                String pass = password.getText();
                String captchaVal = captchaInput.getText().trim();
                if(user.isEmpty() || pass.isEmpty() || captchaVal.isEmpty()){
                    JOptionPane.showMessageDialog(LoginUI.this, "請填寫所有欄位", "錯誤", JOptionPane.ERROR_MESSAGE);
                } else {
                    // 驗證驗證碼（不區分大小寫）
                    if(!captchaVal.equalsIgnoreCase(currentCaptcha)){
                        JOptionPane.showMessageDialog(LoginUI.this, "驗證碼錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
                        // 重新產生驗證碼
                        currentCaptcha = generateCaptcha();
                        captchaPanel.setCaptchaText(currentCaptcha);
                    } else {
                    	// 當登入成功後
                    	Member member = new MemberServiceImpl().Login(user, pass);
                    	if(member != null){
                    	    LoginSuccess loginsuccess = new LoginSuccess(member);
                    	    loginsuccess.setVisible(true);
                    	    dispose();
                    	} else {
                    	    LoginError loginerror = new LoginError();
                    	    loginerror.setVisible(true);
                    	    dispose();
                    	}
                    }
                }
            }
        });
        btnLogin.setBackground(new Color(41, 162, 214));
        btnLogin.setBounds(387, 254, 85, 23);
        contentPane.add(btnLogin);
        
        JButton btnRegister = new JButton("註冊");
        btnRegister.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                AddMemberUI addmemberui = new AddMemberUI();
                addmemberui.setVisible(true);
                dispose();
            }
        });
        btnRegister.setBackground(new Color(41, 162, 214));
        btnRegister.setBounds(387, 210, 85, 23);
        contentPane.add(btnRegister);
        
        // ----------------------------
        // 新增驗證碼元件（放在與登入／註冊按鈕同一垂直線上）
        // ----------------------------
        // 將驗證碼顯示區放在 x = 387，與「登入／註冊」按鈕對齊，
        // 並將驗證碼輸入欄位放在顯示區下方
        captchaPanel = new CaptchaPanel();
        captchaPanel.setBounds(387, 137, 100, 29);
        captchaPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                currentCaptcha = generateCaptcha();
                captchaPanel.setCaptchaText(currentCaptcha);
            }
        });
        contentPane.add(captchaPanel);
        
        captchaInput = new JTextField();
        captchaInput.setBounds(387, 171, 100, 29);
        contentPane.add(captchaInput);
        captchaInput.setColumns(10);
        
        currentCaptcha = generateCaptcha();
        captchaPanel.setCaptchaText(currentCaptcha);
    }
    
    /**
     * 根據 glass pane 的尺寸，將時鐘放在右下角（右下邊距各留 clockMargin）
     */
    private void updateClockPosition(JLabel clockLabel) {
        int glassWidth = getGlassPane().getWidth();
        int glassHeight = getGlassPane().getHeight();
        int newX = glassWidth - clockWidth - clockMargin;
        int newY = glassHeight - clockHeight - clockMargin;
        clockLabel.setBounds(newX, newY, clockWidth, clockHeight);
    }
    
    /**
     * 產生4位數隨機驗證碼（由大寫英文與數字組成）
     */
    private String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = (int)(Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
    
    /**
     * 自訂 CaptchaPanel 用來顯示含有模糊及馬賽克效果的驗證碼
     * 馬賽克效果調整為 blockSize = 2，確保人眼仍能辨識
     */
    class CaptchaPanel extends JPanel {
        private String captchaText = "";
        
        public void setCaptchaText(String text) {
            this.captchaText = text;
            repaint();
        }
        
        public String getCaptchaText() {
            return captchaText;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int w = getWidth();
            int h = getHeight();
            // 先將驗證碼文字畫到 offscreen image（模糊處理）
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D ig = image.createGraphics();
            ig.setColor(getBackground());
            ig.fillRect(0, 0, w, h);
            ig.setFont(new Font("微軟正黑體", Font.BOLD, 26));
            FontMetrics fm = ig.getFontMetrics();
            int textWidth = fm.stringWidth(captchaText);
            int textHeight = fm.getAscent();
            int x = (w - textWidth) / 2;
            int y = (h + textHeight) / 2 - fm.getDescent();
            // 畫出模糊效果（偏移多次）
            ig.setColor(new Color(150, 150, 150, 100));
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    ig.drawString(captchaText, x + i, y + j);
                }
            }
            ig.setColor(Color.BLACK);
            ig.drawString(captchaText, x, y);
            ig.dispose();
            
            // 馬賽克處理：調整 blockSize 為 2
            int blockSize = 2;
            int smallW = Math.max(1, w / blockSize);
            int smallH = Math.max(1, h / blockSize);
            BufferedImage small = new BufferedImage(smallW, smallH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D sg = small.createGraphics();
            sg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            sg.drawImage(image, 0, 0, smallW, smallH, null);
            sg.dispose();
            
            BufferedImage mosaic = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D mg = mosaic.createGraphics();
            mg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            mg.drawImage(small, 0, 0, w, h, null);
            mg.dispose();
            
            g.drawImage(mosaic, 0, 0, null);
        }
    }
}
