import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Overlay extends JPanel {
    private Main main;
    private Bird bird;
    private ArrayList<Rectangle> pipes;
    private Image pipeHead, pipeBody;
    private Font font;

    public Overlay(Main main, Bird bird, ArrayList<Rectangle> pipes) {
        this.main = main;
        this.bird = bird;
        this.pipes= pipes;
        font = new Font("Arial", Font.ITALIC, 21);

        try {
            pipeHead = ImageIO.read(new File("pipeHead.png"));
            pipeBody = ImageIO.read(new File("pipeBody.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0,0,main.WINDOW_WIDTH,main.WINDOW_HEIGHT);
        bird.draw(g);
        for(Rectangle r : pipes) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform old = g2d.getTransform();
            g2d.setColor(Color.GREEN);
            g2d.fillRect(r.x, r.y, r.width, r.height);
            //g2d.drawImage(pipeHead, -r.width/2, -r.height/2, r.width, 10, null);
            //g2d.drawImage(pipeBody, r.x, r.y, r.width, r.height, null);
            g2d.setTransform(old);
        }

        if(main.isPaused) {
            g.setFont(font);
            g.setColor(new Color(0,0,0,255));
            g.drawString("PRESS SPACE", 50, 50);
        }

    }

}
