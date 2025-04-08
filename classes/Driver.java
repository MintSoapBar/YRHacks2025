package classes;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Driver extends JPanel implements MouseListener, KeyListener, Runnable {
    final static int FPS = 60;

    Schedule schedule = new Schedule();
    ArrayList<TaskGroup> taskGroups = new ArrayList<>();

    public void run() {
        while(true) {
            repaint();
            try {
                Thread.sleep(1000/FPS);
            }
            catch(Exception e) {}
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString(System.currentTimeMillis() + "", 10, 30);
    }

    public Driver() {
        setPreferredSize(new Dimension(400,600));
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] arg) {
        JFrame frame = new JFrame("to-do list");
        Driver panel = new Driver();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}