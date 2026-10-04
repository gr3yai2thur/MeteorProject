import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

// 1 อุกกาบาต = 1 Thread (สร้างหลายตัวจาก GamePanel)
public class Meteors extends Thread {
    private final GamePanel gamePanel;
    private final int index;
    private final int meteorCount;
    private final Random rnd = new Random();
    private double vX;
    private double vY;
    private double pX;
    private double pY;              

    public Meteors(GamePanel gamePanel, int index, int meteorCount) {
        this.gamePanel = gamePanel;
        this.index = index;
        this.meteorCount = meteorCount;

        pX = gamePanel.getPosX(index);
        pY = gamePanel.getPosY(index);

        if(rnd.nextBoolean()) vX = rnd.nextDouble() * 2 + 1;
        else vX = -rnd.nextDouble() * 2 + 1;

        if(rnd.nextBoolean()) vY = rnd.nextDouble() * 2 + 1;
        else vY = -rnd.nextDouble() * 2 + 1;
    }

    @Override
    public void run() {
        while (gamePanel.isAlive(index)) {
            int panelWidth = gamePanel.getWidth();
            int panelHeight = gamePanel.getHeight();

            if (panelWidth > 0 && panelHeight > 0) {
                int maxX = panelWidth - 100;
                int maxY = panelHeight - 120;

                pX += vX;
                pY += vY;

                boolean hitWall = false;
                if (pX < 0 || pX > maxX) {

                    // มุมสะท้อน = มุมตกกระทบ by ครูสามารถ cws
                    vX = -vX;
                    pX = Math.max(0, Math.min(pX, maxX));
                    hitWall = true;
                }
                if (pY < 0 || pY > maxY) {

                    // มุมสะท้อน = มุมตกกระทบ by ครูสามารถ cws
                    vY = -vY;
                    pY = Math.max(0, Math.min(pY, maxY));
                    hitWall = true;
                }

                if (hitWall) speedUp();

                gamePanel.setPosX(index, (int) pX);
                gamePanel.setPosY(index, (int) pY);

                if (checkCollision()) {return;}

                gamePanel.repaint();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }

    private void speedUp() {
        vX = Math.min(vX * 1.3, 7);
        vY = Math.min(vY * 1.3, 7);
    }

    private boolean checkCollision() {
        if (!gamePanel.isAlive(index)) return true;

        // ตรงกลางของอุกาบาต
        double cx = pX + 100 / 2.0;
        double cy = pY + 100 / 2.0;

        for (int j = index + 1; j < meteorCount; j++) {
            if (!gamePanel.isAlive(j)) continue;

            //
            double dx = cx - (gamePanel.getPosX(j) + 100 / 2.0);
            double dy = cy - (gamePanel.getPosY(j) + 100 / 2.0);

            // 100 มาจากรูปอุกาบาตเพราะมีขนาด 100x100
            if (Math.abs(dx) < 100 && Math.abs(dy) < 100) {
                gamePanel.setAlive(index, false);
                int ex = (int) (cx - dx / 2);  
                int ey = (int) (cy - dy / 2);
                explode(ex, ey);
                return true;
            }
        }
        return false;
    }

    private void explode(int cx, int cy) {
        new Thread(() -> {
            ImageIcon icon = new ImageIcon(gamePanel.getBombEffect());
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();

            JLabel boom = new JLabel(icon);

            // วาดที่ซ้ายบน
            boom.setBounds(cx - w / 2, cy - h / 2, w, h);

            SwingUtilities.invokeLater(() -> {
                gamePanel.add(boom);
                gamePanel.repaint();
            });

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            SwingUtilities.invokeLater(() -> {
                gamePanel.remove(boom);
                gamePanel.repaint();
            });
        }).start();
    }
}