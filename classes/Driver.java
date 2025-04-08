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
    final static int TABS_NUM = 4;

    static int screenWidth = 1000;
    static int screenHeight = 600;

    static int topBarHeight = 30;

    static int tabHeight = 30;
    static int tabWidth = 100;
    static int tabGap = 5;

    static int timeBlockGap = 10;
    static int timeBlockLeft = 250;
    static int timeBlockWidth = 500;
    static int timeBlockHeight = 100;

    static int taskListGap = 10;
    static int taskListLeft = 100;
    static int taskListWidth = 800;
    static int taskListHeight = 70;

    static int activityListGap = 10;
    static int activityListLeft = 100;
    static int activityListWidth = 800;
    static int activityListHeight = 70;

    static ArrayList<Activity> activities = new ArrayList<>();
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<TimeBlock> schedule = new ArrayList<>();

    static long dayStart = 28800000;
    static long dayEnd = 79200000;
    static int taskIndex = 0;

    static int currentTab = 0;
    static String[] tabNames = new String[] { "Home", "Schedule", "Task List", "Activity List" };
    static Button[] tabButtons = new Button[TABS_NUM];
    static Frame[] tabFrames = new Frame[TABS_NUM];

    static Frame scheduleScrollingFrame;
    static Frame taskListScrollingFrame;
    static Frame activityListScrollingFrame;

    static BufferedImage[] backgrounds = new BufferedImage[2];

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (Exception e) {
            }
        }
    }

    public static void refreshButtons() {
        scheduleScrollingFrame.children.clear();

        for (int i = 0; i < schedule.size(); i++) {
            TimeBlock tb = schedule.get(i);
            tb.refreshButtons();

            Button b = tb.scheduleButton;
            b.y = timeBlockGap + i * (timeBlockGap + timeBlockHeight);
            scheduleScrollingFrame.addChild(b);
        }

        tasks.sort(null);
        taskListScrollingFrame.children.clear();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            Button b = t.taskListButton;
            b.y = taskListGap + i * (taskListGap + taskListHeight);
            taskListScrollingFrame.addChild(b);
        }

        activities.sort(null);
        activityListScrollingFrame.children.clear();
        for (int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);
            Button b = a.activityListButton;
            b.y = activityListGap + i * (activityListGap + activityListHeight);
            activityListScrollingFrame.addChild(b);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tabFrames[currentTab].render(g);

        //topbar background
        g.setColor(new Color(150, 210, 230));
        g.fillRect(0, 0, screenWidth, topBarHeight + tabGap + tabHeight);

        //timer
        g.setColor(new Color(10, 10, 40));
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString(new Time(System.currentTimeMillis())+"", 10, 25);

        for (int i = 0; i < TABS_NUM; i++) {
            Button b = tabButtons[i];
            b.backgroundColor = i == currentTab ? new Color(200, 200, 200) : new Color(255, 255, 255);
            b.render(g);
        }
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
            tabButtons[i] = new Button(tabGap + i * (tabWidth + tabGap), topBarHeight + tabGap, tabWidth, tabHeight,
                    tabNames[i]);
            tabFrames[i] = new Frame(0, topBarHeight + tabHeight + tabGap, screenWidth, screenHeight - tabHeight,
                    backgrounds[i % 2]);
        }

        // Home screen
        Frame welcomeFrame = new Frame(400, 200, 200, 100, new Color(255, 255, 255), "Welcome!");
        welcomeFrame.textFont = new Font("Times New Roman", Font.BOLD, 20);
        tabFrames[0].addChild(welcomeFrame);

        // schedule
        scheduleScrollingFrame = new Frame(0, 0, screenWidth, screenHeight - tabHeight);
        scheduleScrollingFrame.isScrollingFrame = true;
        tabFrames[1].addChild(scheduleScrollingFrame);

        // task list
        taskListScrollingFrame = new Frame(0, 0, screenWidth, screenHeight - tabHeight);
        taskListScrollingFrame.isScrollingFrame = true;
        tabFrames[2].addChild(taskListScrollingFrame);

        // activity list
        activityListScrollingFrame = new Frame(0, 0, screenWidth, screenHeight - tabHeight);
        activityListScrollingFrame.isScrollingFrame = true;
        tabFrames[3].addChild(activityListScrollingFrame);

        // create jframe
        JFrame jFrame = new JFrame("Sto-do");
        Driver panel = new Driver();
        jFrame.add(panel);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // long taskDueTime = LocalDateTime.of(year, month, day, hour, minute,
        // second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
        tasks.add(new Task("Comp sci assignment 4", "its due wednesday help", 0, 0, new Time(1744138800000l), 3456000l,
                0.7));
        tasks.add(new Task("Chem Lab", "Procedure will annihilate me", 0, 0, new Time(1744218000000l), 6912000l, 0.8));
        tasks.add(new Task("Math assignment 3", "Due on Friday", 0, 0, new Time(1744304400000l), 86400000l, 0.6));
        activities.add(new Activity("Swimming", "Swim Apex Fitness", 64800000l, 68400000l, (byte) 0b10010010));
        activities.add(new Activity("obtaining vitamin d", "its beautiful", 72000000l, 75600000l, (byte) 0b11111110));

        sortSchedule((byte) 0b0010000);
        refreshButtons();
    }

    public static void sortSchedule(byte currentDay) {
        schedule.clear();

        ArrayList<Activity> dayActivities = new ArrayList<>();

        for (Activity a : activities) {
            if ((a.daysOfWeek & currentDay) > 0) {

                dayActivities.add(a);
            }
        } // Sort activities when adding new activity

        for (Activity a : dayActivities) {
            schedule.add(a);
        }

        int scheduleIndex = 0;
        for (int i = 0; i <= dayActivities.size(); i++) {
            if (tasks.size() == taskIndex) { // No tasks to schedule
                break;
            }
            long startTime;
            long endTime;
            if (i == 0) {
                startTime = dayStart;
            } else {
                startTime = dayActivities.get(i - 1).endTime;
            }
            if (i == dayActivities.size()) {
                endTime = dayEnd;
            } else {
                endTime = dayActivities.get(i).startTime;
            }

            while (tasks.size() > taskIndex && endTime != startTime) {
                Task task = tasks.get(taskIndex);
                // Task longer than time block
                if (task.length - task.timeDone > endTime - startTime) {
                    task.timeDone += endTime - startTime;
                    schedule.add(scheduleIndex, new Task(task.name, task.description, startTime, endTime, task.dueDate,
                            endTime - startTime, task.priority));
                    startTime = endTime;
                }

                // Task shorter than time block
                else {
                    task.startTime = startTime;
                    task.endTime = startTime + task.length;
                    schedule.add(scheduleIndex, task);
                    startTime += task.length;
                    task.timeDone = task.length;
                    taskIndex++;
                }

                scheduleIndex++;
            }
            scheduleIndex++;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void capScrollOffset(Point scrollOffset) {
        if (currentTab == 1) {
            scrollOffset.y = Math2.clamp(scrollOffset.y, -scheduleScrollingFrame.children.size() * (timeBlockGap + timeBlockHeight), 0);
        } else if (currentTab == 2) {
            scrollOffset.y = Math2.clamp(scrollOffset.y, -taskListScrollingFrame.children.size() * (taskListGap + taskListHeight), 0);
        } else if (currentTab == 3) {
            scrollOffset.y = Math2.clamp(scrollOffset.y, -activityListScrollingFrame.children.size() * (activityListGap + activityListHeight), 0);
        }
    }

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        Point currentTargetScrollOffset = null;

        for (Frame f : tabFrames[currentTab].children) {
            if (f.isScrollingFrame)
                currentTargetScrollOffset = f.targetScrollOffset;
        }

        if (kc == KeyEvent.VK_UP && currentTargetScrollOffset != null) {
            currentTargetScrollOffset.y += 50;
            capScrollOffset(currentTargetScrollOffset);
        } else if (kc == KeyEvent.VK_DOWN && currentTargetScrollOffset != null) {
            currentTargetScrollOffset.y -= 50;
            capScrollOffset(currentTargetScrollOffset);
        } else if (kc == KeyEvent.VK_LEFT) {
            currentTab = (currentTab - 1 + TABS_NUM) % TABS_NUM;
        } else if (kc == KeyEvent.VK_RIGHT) {
            currentTab = (currentTab + 1 + TABS_NUM) % TABS_NUM;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}