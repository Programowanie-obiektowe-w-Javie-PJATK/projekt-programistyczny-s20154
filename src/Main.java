import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main implements ActionListener, KeyListener {
    public static final int WINDOW_WIDTH  = 320;
    public static final int WINDOW_HEIGHT = 480;
    public static final int PIPE_WIDTH = 40;
    private JFrame  frame;
    private JPanel  panel;
    private Timer   tick;
    private int scroll;
    public  Boolean isPaused;

    private Bird bird;
    private ArrayList<Rectangle> pipes;
    private int score;

    public Main(){
        frame = new JFrame("Flappy Bird the Game");
        bird  = new Bird();
        pipes = new ArrayList<Rectangle>();
        panel = new Overlay(this, bird, pipes);
        tick  = new Timer(24, this);
        isPaused = true;

        frame.add(panel);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.addKeyListener(this);

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

        // Create new pipe
        if(scroll % 90 == 0) {
            Rectangle upperPipe = new Rectangle(WINDOW_WIDTH, 0, PIPE_WIDTH, 100);
            Rectangle lowerPipe = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGHT-100, PIPE_WIDTH, 100);
            pipes.add(upperPipe);
            pipes.add(lowerPipe);
        }

        ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
        for(Rectangle r : pipes) {
            // Move pipe
            r.x -= 2;

            // Does pipe reach left part of screen
            if(r.x + PIPE_WIDTH*10 <= 0) {
                toRemove.add(r);
            }

            // Does bird collide with pipe
            if(r.contains(bird.x, bird.y)) {
                gameOver();
                return;
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
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_SPACE:
                bird.jump();
                isPaused = false;
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
