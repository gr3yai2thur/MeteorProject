import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

public class GamePanel extends JPanel{
    private Image[] meteorImg = new Image[10];
    public Random rnd = new Random();
    private int meteorCount;
    private int[] posX;
    private int[] posY;
    private Image bomb, bg;

    public GamePanel(int meteorCount){
        // พื้นหลังดำ
        setBackground(Color.BLACK);

        // Array รูปอุกาบาต
        meteorImg[0] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid1.png"
        );
        meteorImg[1] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid2.png"
        );
        meteorImg[2] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid3.png"
        );
        meteorImg[3] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid4.png"
        );
        meteorImg[4] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid5.png"
        );
        meteorImg[5] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid6.png"
        );
        meteorImg[6] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid7.png"
        );
        meteorImg[7] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid8.png"
        );
        meteorImg[8] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid9.png"
        );
        meteorImg[9] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "asteroid10.png"
        );
        bg = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "background.jpg"
        );
        bomb = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "explosion.gif"
        );

        // ส่วนกำหนด Positon
        this.meteorCount = meteorCount;
        posX = new int[meteorCount];
        posY = new int[meteorCount];

        for(int i=0; i<meteorCount; i++){
            posX[i] = setPosX(i, rnd().nextInt(1340));
            posY[i] = setPosY(i, rnd().nextInt(800));;
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
        for(int i=0;i<meteorCount; i++){
            g.drawImage(meteorImg[randomMeteor()], posX[i], posY[i], this);
        }
    }

    public int randomMeteor(){
        Random rnd = new Random();
        return rnd.nextInt(10);
    }

    // Getter Method
    public Image getMeteorImages(int i){
        return meteorImg[i];
    }
    public Image getBombEffect(){
        return bomb;
    }
    public Random rnd(){
        return rnd;
    }
    public int getPosX(int i){
        return posX[i];
    }
    public int getPosY(int i){
        return posY[i];
    }

    // Setter Method
    public int setPosX(int i, int x){
        return posX[i] = x;
    }
    public int setPosY(int i, int x){
        return posY[i] = x;
    }
}
