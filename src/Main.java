import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main implements ActionListener, KeyListener {
    public static final int WINDOW_WIDTH  = 640;
    public static final int WINDOW_HEIGHT = 480;
    public static final int PIPE_WIDTH = 55;
    private JFrame  frame;
    private JPanel  panel;
    private Timer   tick;
    private int scroll;
    public  Boolean isPaused;

    private Bird bird;
    private ArrayList<Rectangle> pipes;
    private int score;
    private Difficulty difficulty;
    private Random rand;

    public Main(){
        frame = new JFrame("Flappy Bird the Game");
        bird  = new Bird();
        pipes = new ArrayList<Rectangle>();
        panel = new Overlay(this, bird, pipes);
        tick  = new Timer(24, this);
        rand  = new Random();
        difficulty = new Difficulty();
        isPaused = true;
        rand.setSeed((int)new Date().getTime());

        frame.add(panel);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(200,200,200,255));

        tick.start();
    }

    public static void main(String[] args){
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent a){
        panel.repaint();
        if(isPaused == true)
            return;

        bird.pullDown();

        difficulty.increase(score);

        // Create new pipe
        if(scroll % difficulty.getSpeed() == 0) {
            int gap = rand.nextInt(3)*40+120;
            int upperHeight = rand.nextInt(4)*50+50;
            int lowerHeight = WINDOW_HEIGHT-(upperHeight+gap);
            Rectangle upperPipe = new Rectangle(WINDOW_WIDTH, 0, PIPE_WIDTH, upperHeight);
            Rectangle lowerPipe = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGHT-lowerHeight, PIPE_WIDTH, lowerHeight);
            pipes.add(upperPipe);
            pipes.add(lowerPipe);
        }

        ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
        for(Rectangle r : pipes) {
            // Move pipe
            r.x -= 4;

            // Does pipe reach left part of screen
            if(r.x + PIPE_WIDTH*10 <= 0) {
                toRemove.add(r);
            }

            // Does bird collide with pipe
            if(r.contains(bird.x, bird.y)) {
                gameOver();
                return;
            }

            // Add point ot score
            if(r.x == WINDOW_WIDTH/2){
                score++;
            }
        }


        pipes.removeAll(toRemove);
        scroll++;

        if(!bird.doesReachWindowBorder()) {
            gameOver();
        }

    }

    private void gameOver(){
        pipes.clear();
        bird.reset();
        isPaused = true;
        scroll = 0;
        score = 0;
        difficulty.reset();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_SPACE:
                bird.jump();
                isPaused = false;
                break;
            case KeyEvent.VK_Q:
                System.exit(1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // This method has to be implemented for project to run
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // This method has to be implemented for project to run
    }

}
