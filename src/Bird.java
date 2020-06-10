import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

interface Player {
    void jump();
    void pullDown();
    void reset();
    void draw(Graphics g);
    Boolean doesReachWindowBorder();
}

public class Bird implements Player {
    public double x, y;
    private double Vx, Vy;
    private Image body;
    public static final double size = 20;

    public Bird(){
        reset();
        try {
            body = ImageIO.read(new File("birdBody.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void jump(){
        Vy = -11;
    }

    public void pullDown(){
        x += Vx;
        y += Vy;
        Vy += 0.8;
    }

    public void reset(){
        x = Main.WINDOW_WIDTH/2;
        y = Main.WINDOW_HEIGHT/2;
        Vx = 0;
        Vy = 0;
    }

    public void draw(Graphics g){
        g.drawImage(body, (int)(x-size),(int)(y-size),(int)(2*size),(int)(2*size), null);
    }

    public Boolean doesReachWindowBorder(){
        if( y > Main.WINDOW_HEIGHT || y+size < 0 )
            return false;
        else
            return true;
    }

}
