import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        this.pipes = pipes;
        font = new Font("Comic Sans MS", Font.ITALIC, 21);

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

        if(main.isPaused) {
            g.setFont(font);
            g.setColor(new Color(0,0,0,170));
            g.drawString("press space", main.WINDOW_WIDTH/2, main.WINDOW_HEIGHT/2);
        }
    }

}
