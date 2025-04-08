package classes;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Driver extends JPanel implements MouseListener, KeyListener, Runnable {
    final static int FPS = 60;
    final static int TABS_NUM = 2;

    static int screenWidth = 1000;
    static int screenHeight = 600;

    static int tabHeight = 20;
    static int tabWidth = 80;
    static int tabGap = 5;

    static ArrayList<TaskGroup> taskGroups = new ArrayList<>();

    static int currentTab = 0;
    static Point[] scrollOffsets = new Point[TABS_NUM];
    static String[] tabNames = new String[] {"Schedule", "Task List"};
    static Button[] tabButtons = new Button[TABS_NUM];
    static Frame[] tabFrames = new Frame[TABS_NUM];

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

        int scrollX = scrollOffsets[currentTab].x;
        int scrollY = scrollOffsets[currentTab].y;
        tabFrames[currentTab].render(g, scrollX, scrollY);

        for (Frame f: tabFrames[0].frames) {
            f.x = (int) (System.currentTimeMillis() % 1000 / 10 + 10);
        }

        for (Button b: tabButtons) {
            b.render(g);
        }
        
        Point mousePos = getMousePosition();
    }

    public Driver() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] arg) throws IOException {
        for (int i = 0; i < TABS_NUM; i++) {
            scrollOffsets[i] = new Point();
            tabButtons[i] = new Button(tabGap + i*(tabWidth + tabGap), tabGap, tabWidth, tabHeight, tabNames[i]);
            tabFrames[i] = new Frame(0, tabHeight, screenWidth, screenHeight - tabHeight);
        }

        JFrame frame = new JFrame("to-do list");
        Driver panel = new Driver();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Frame f1 = new Frame(50, 10, 200, 100, new Color(255, 0, 0));
        f1.addFrame(new Frame(20, 10, 50, 50, new Color(255, 255, 0)));
        Button b1 = new Button(10, 70, 50, 20, new Color(0, 0, 255), "Hey!!");
        f1.addButton(b1);
        tabFrames[0].addFrame(f1);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        Point currentScreenScrollOffset = scrollOffsets[currentTab];
        if (kc == KeyEvent.VK_UP) {
            currentScreenScrollOffset.y -= 10;
        } else if (kc == KeyEvent.VK_DOWN) {
            currentScreenScrollOffset.y += 10;
        } else if (kc == KeyEvent.VK_LEFT) {
            currentTab = (currentTab - 1 + TABS_NUM) % TABS_NUM;
        } else if (kc == KeyEvent.VK_RIGHT) {
            currentTab = (currentTab + 1 + TABS_NUM) % TABS_NUM;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}