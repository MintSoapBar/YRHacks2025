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

    static int screenWidth = 400;
    static int screenHeight = 600;

    static Schedule schedule = new Schedule();
    static ArrayList<TaskGroup> taskGroups = new ArrayList<>();

    static Frame mainFrame = new Frame(0, 0, screenWidth, screenHeight);
    static Button b1;

    static Frame f1;

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
      
        g.drawString(System.currentTimeMillis() + "", 10, 25);

        mainFrame.render(g);
        f1.x = (int) (System.currentTimeMillis()/10 % 100 + 10);
        
        int x = 0;
        int y = 0;
        
        Point mousePos = getMousePosition();
        if (mousePos != null) {
            x = (int) mousePos.getX();
            y = (int) mousePos.getY();
        }

        g.drawString(x + "", 10, 300);
        g.drawString(y + "", 40, 300);
        g.drawString(b1.isPosInBounds(x, y) + "", 10, 400);
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
        
        f1 = new Frame(50, 10, 200, 100, new Color(255, 0, 0));
        f1.addFrame(new Frame(20, 10, 50, 50, new Color(255, 255, 0)));
        b1 = new Button(10, 70, 50, 20, new Color(0, 0, 255), "Hey!!");
        f1.addButton(b1);
        mainFrame.addFrame(f1);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}