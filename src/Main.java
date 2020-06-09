import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main implements ActionListener, KeyListener {
    public static final int WINDOW_WIDTH  = 640;
    public static final int WINDOW_HEIGHT = 480;
    private JFrame  frame;
    private JPanel  panel;
    private Timer   tick;
    public Boolean isPaused;

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
        score = 0;

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
