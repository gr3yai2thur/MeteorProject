import javax.swing.JPanel;
import java.awt.Toolkit;
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
    private int[] meteorTypes;
    private boolean[] alive;
    private Image bomb, bg;

    public GamePanel(int meteorCount){

        // Array รูปอุกาบาต
        meteorImg[0] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor1.png"
        );
        meteorImg[1] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor2.png"
        );
        meteorImg[2] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor3.png"
        );
        meteorImg[3] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor4.png"
        );
        meteorImg[4] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor5.png"
        );
        meteorImg[5] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor6.png"
        );
        meteorImg[6] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor7.png"
        );
        meteorImg[7] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor8.png"
        );
        meteorImg[8] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor9.png"
        );
        meteorImg[9] = Toolkit.getDefaultToolkit().getImage(
            System.getProperty("user.dir") + File.separator + "images"
                + File.separator + "meteor10.png"
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
        alive = new boolean[meteorCount];
        meteorTypes = new int[meteorCount];

        for(int i=0; i<meteorCount; i++){
            setPosX(i, rnd().nextInt(1200));
            setPosY(i, rnd().nextInt(650));
            alive[i] = true;
            meteorTypes[i] = randomMeteor();
        }
        for(int i=0; i<meteorCount; i++){
            new Meteors(this, i, meteorCount).start();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
        for(int i=0;i<meteorCount; i++){
            if (!alive[i]) continue;
            g.drawImage(meteorImg[meteorTypes[i]], posX[i], posY[i], this);
        }
    }

    public int randomMeteor(){
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
    public boolean isAlive(int i){
        return alive[i];
    }

    // Setter Method
    public void setPosX(int i, int x){
        posX[i] = x;
    }
    public void setPosY(int i, int x){
        posY[i] = x;
    }
    public void setAlive(int i, boolean x){
        alive[i] = x;
    }
}