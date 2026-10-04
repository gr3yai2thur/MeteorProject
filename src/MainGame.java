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
        int meteorCount = 0;
        do {
            UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 16));
            UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));
    
            String input = JOptionPane.showInputDialog(null, "กรุณากรอกจำนวนอุกกาบาต:", "ตั้งค่าจำนวนอุกาบาต", JOptionPane.QUESTION_MESSAGE);
    
            try {
                if (input != null) meteorCount = Integer.parseInt(input);
                else return;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ค่าที่กรอกไม่ถูกต้อง! กรุณากรอกตัวเลข", "ข้อมูลผิดพลาด", JOptionPane.ERROR_MESSAGE);
            }
            
        } while (meteorCount == 0);

        new MainGame(meteorCount);
    }
}