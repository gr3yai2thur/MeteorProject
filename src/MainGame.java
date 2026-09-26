import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MainGame extends JFrame{
    public MainGame(int meteorCount){
        setTitle("Meteor Simulator");
        setSize(1440, 900);
        setLayout(null);

        GamePanel gamePanel = new GamePanel(meteorCount);
        gamePanel.setBounds(0,0,1440,900);
        add(gamePanel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

        String input = JOptionPane.showInputDialog(null, "กรุณากรอกจำนวนอุกกาบาต:", "ตั้งค่าเกม", JOptionPane.QUESTION_MESSAGE);

        int meteorCount = 10;
        try {
            if (input != null) {
                meteorCount = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "กรอกไม่ถูกต้อง! ใช้ค่าเริ่มต้น = 10", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }

        new MainGame(meteorCount);
    }
}