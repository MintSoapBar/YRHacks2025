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
    final static int TABS_NUM = 3;

    static int screenWidth = 1000;
    static int screenHeight = 600;

    static int topBarHeight = 20;

    static int tabHeight = 20;
    static int tabWidth = 80;
    static int tabGap = 5;

    static ArrayList<TaskGroup> taskGroups = new ArrayList<>();
    static ArrayList<Activity> activities = new ArrayList<>();
    static ArrayList<Task> tasks = new ArrayList<>(); 
    static ArrayList<TimeBlock> schedule = new ArrayList<>();

    static long dayStart = 28800000;
    static long dayEnd = 79200000;

    static int currentTab = 0;
    static String[] tabNames = new String[] {"Home", "Schedule", "Task List"};
    static Button[] tabButtons = new Button[TABS_NUM];
    static Frame[] tabFrames = new Frame[TABS_NUM];

    static Frame scheduleScrollingFrame;

    static BufferedImage[] backgrounds = new BufferedImage[2];

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

        tabFrames[currentTab].render(g);

        for (int i = 0; i < TABS_NUM; i++) {
            Button b = tabButtons[i];
            b.backgroundColor = i == currentTab? new Color(200, 200, 200): new Color(255, 255, 255);
            System.out.println(i + " " + b.backgroundColor.getRed());
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

    public static void main(String[] arg) {
        try {
            backgrounds[0] = ImageIO.read(new File("images/background0.jpg"));
            backgrounds[1] = ImageIO.read(new File("images/background0.jpg"));
        } catch (IOException e) {
            System.out.println("Unable to load background image(s)");
        }

        for (int i = 0; i < TABS_NUM; i++) {
            tabButtons[i] = new Button(tabGap + i*(tabWidth + tabGap), topBarHeight + tabGap, tabWidth, tabHeight, tabNames[i]);
            tabFrames[i] = new Frame(0, topBarHeight + tabHeight + tabGap, screenWidth, screenHeight - tabHeight, backgrounds[i%2]);
        }


        //Home screen
        Frame welcomeFrame = new Frame(400, 200, 200, 100, new Color(255, 255, 255), "Welcome!");
        welcomeFrame.textFont = new Font("Times New Roman", Font.BOLD, 20);
        tabFrames[0].addFrame(welcomeFrame);

        //schedule
        scheduleScrollingFrame = new Frame(0, 0, screenWidth, screenHeight - tabHeight);
        scheduleScrollingFrame.isScrollingFrame = true;
        tabFrames[1].addFrame(scheduleScrollingFrame);


        
        //create jframe
        JFrame jFrame = new JFrame("to-do list");
        Driver panel = new Driver();
        jFrame.add(panel);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        taskGroups.add(new TaskGroup("Chem Lab", "Design a lab", new Time(System.currentTimeMillis() - 1000000), new Time(System.currentTimeMillis() + 10000), 0.8));

        // long taskDueTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
        taskGroups.get(0).addTask(new Task("Chem Lab", "Graphics", new Time(System.currentTimeMillis() + 1000), 1000000000000l, 0.7));
        taskGroups.get(0).addTask(new Task("Chem Lab", "Procedure", new Time(System.currentTimeMillis() + 1000), 1000000005464l, 0.8));
        // System.out.println(taskGroups.get(0).getTask(0));
        schedule.add(taskGroups.get(0).getTask(0));
        schedule.add(taskGroups.get(0).getTask(1));
        activities.add(new Activity("Swimming", "Swim Apex Fitness", 64800000l, 68400000l, (byte) 0b0010000));
        tasks.add(taskGroups.get(0).getTask(0));
        tasks.add(taskGroups.get(0).getTask(1));

        for (TimeBlock t : schedule) {
            System.out.println(t);
        }
    }

    public void addActivity(String name, String description, long startTime, long endTime, byte daysOfWeek) {
        activities.add(new Activity(name, description, startTime, endTime, daysOfWeek));
        // have to sort after adding to the list
    }

    public void addTask(String name, String description, long dueDate, long length, Double priority) {
        tasks.add(new Task(name, description, new Time(dueDate), length, priority));
        // have to sort after adding to the list
    }

    public void sortSchedule(byte currentDay) {
        schedule.clear();
        long currentTime = System.currentTimeMillis();
        long oneHourMillis = 3600000; // One hour in milliseconds

        ArrayList<Activity> dayActivities = new ArrayList<>();
        for (Activity a : activities) {
            if ((a.daysOfWeek & currentDay) > 0) {
                dayActivities.add(a);
            }
        } // Sort activities when adding new activity

        for (Activity a : dayActivities) {
            schedule.add(a);
        }

        for (int i = 0; i <= dayActivities.size(); i++) {
            if (tasks.size() == 0) { // No tasks to schedule
                break;
            }
            long startTime;
            long endTime;
            if (i == 0) {
                startTime = dayStart;
            }
            else {
                startTime = dayActivities.get(i - 1).endTime;
            }
            if (i == dayActivities.size()) {
                endTime = dayEnd;
            }
            else {
                endTime = dayActivities.get(i).startTime;
            }

            while (tasks.size() > 0 || endTime == startTime) {
                Task task = tasks.get(0);
                // Task longer than time block
                if (task.length > endTime - startTime) {
                    task.length -= endTime - startTime;
                    startTime = endTime;
                    schedule.add(i, new Task(task.name, task.description, task.dueDate, endTime - startTime, task.priority));
                }

                // Task shorter than time block
                else {
                    startTime += task.length;
                    tasks.remove(0);
                    schedule.add(i, task);
                }
            }
        }
    }
  
    public void keyTyped(KeyEvent e) {}
  
    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        Point currentTargetScrollOffset = null;

        for (Frame f: tabFrames[currentTab].frames) {
            if (f.isScrollingFrame) currentTargetScrollOffset = f.targetScrollOffset;
        }

        if (kc == KeyEvent.VK_UP && currentTargetScrollOffset != null) {
            currentTargetScrollOffset.y += 10;
        } else if (kc == KeyEvent.VK_DOWN && currentTargetScrollOffset != null) {
            currentTargetScrollOffset.y -= 10;
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