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
    public double Vy;
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
        Vy = -8;
    }

    public void pullDown(){
        y += Vy;
        Vy += 0.6;
    }

    public void reset(){
        x = Main.WINDOW_WIDTH/2;
        y = Main.WINDOW_HEIGHT/2;
        Vy = 0;
    }

    public void draw(Graphics g){
        g.drawImage(body, (int)(x-size),(int)(y-size),(int)(2*size),(int)(2*size), null);
    }

    public Boolean doesReachWindowBorder(){
        if( y-size > Main.WINDOW_HEIGHT || y+size < 0 )
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "x=" + x +
                ", y=" + y +
                ", Vy=" + Vy +
                '}';
    }
}

class BirdTest{
    private Bird bird;

    public static void main(String[] args){
        new BirdTest();
    }

    public BirdTest(){
        bird = new Bird();
        System.out.println(bird.toString());
        testGravitation();
        System.out.println(bird.toString());
        testJump();
        System.out.println(bird.toString());
        testReset();
        System.out.println(bird.toString());
    }

    public void testGravitation(){
        bird.pullDown();
        bird.pullDown();
        System.out.println("Pull down...");
    }

    public void testJump(){
        bird.jump();
        System.out.println("Jump...");
    }

    public void testReset(){
        bird.reset();
        System.out.println("Reset...");
    }


}
