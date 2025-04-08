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

public class Driver extends JPanel implements MouseListener, KeyListener, Runnable  {
    final static int FPS = 60;

    static int screenWidth = 400;
    static int screenHeight = 600;

    static int scrollOffsetX = 0;
    static int scrollOffsetY = 0;

    static ArrayList<TaskGroup> taskGroups = new ArrayList<>();
    static ArrayList<TimeBlock> timeBlocks = new ArrayList<>(); 
    static ArrayList<TimeBlock> schedule = new ArrayList<>(); 

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

        mainFrame.render(g, scrollOffsetX, scrollOffsetY);
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

    public static void main(String[] arg) throws IOException {
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


        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // TASK GROUP
        // System.out.println("Enter the task group name: ");
        // String name = br.readLine();
        // System.out.println("Enter the year of the start date:");
        // int year = Integer.parseInt(br.readLine());
        // System.out.println("Enter the month of the start date:");
        // int month = Integer.parseInt(br.readLine());
        // System.out.println("Enter the day of the start date:");
        // int day = Integer.parseInt(br.readLine());
        // System.out.println("Enter the hour of the start date:");
        // int hour = Integer.parseInt(br.readLine());
        // System.out.println("Enter the minute of the start date:");
        // int minute = Integer.parseInt(br.readLine());
        // System.out.println("Enter the second of the start date:");
        // int second = Integer.parseInt(br.readLine());
        // long startTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
        // System.out.println("Enter the year of the due date:");
        // year = Integer.parseInt(br.readLine());
        // System.out.println("Enter the month of the due date:");
        // month = Integer.parseInt(br.readLine());
        // System.out.println("Enter the day of the due date:");
        // day = Integer.parseInt(br.readLine());
        // System.out.println("Enter the hour of the due date:");
        // hour = Integer.parseInt(br.readLine());
        // System.out.println("Enter the minute of the due date:");
        // minute = Integer.parseInt(br.readLine());
        // System.out.println("Enter the second of the due date:");
        // second = Integer.parseInt(br.readLine());
        // long dueTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
        // System.out.println("Enter the task group description: ");
        // String description = br.readLine();
        // System.out.println("Enter the task group priority (0-1): ");
        // Double priority = Double.parseDouble(br.readLine());
        // taskGroups.add(new TaskGroup(name, description, new Time(startTime), new Time(dueTime), priority));
        taskGroups.add(new TaskGroup("Chem Lab", "Design a lab", new Time(System.currentTimeMillis() - 1000000), new Time(System.currentTimeMillis() + 10000), 0.8));

        // TASK
        // System.out.println("Enter the task name: ");
        // String taskName = br.readLine();
        // System.out.println("Enter the year of the task due date:");
        // year = Integer.parseInt(br.readLine());
        // System.out.println("Enter the month of the task due date:");
        // month = Integer.parseInt(br.readLine());
        // System.out.println("Enter the day of the task due date:");
        // day = Integer.parseInt(br.readLine());
        // System.out.println("Enter the hour of the task due date:");
        // hour = Integer.parseInt(br.readLine());
        // System.out.println("Enter the minute of the task due date:");
        // minute = Integer.parseInt(br.readLine());
        // System.out.println("Enter the second of the task due date:");
        // second = Integer.parseInt(br.readLine());
        // System.out.println("Enter the task description: ");
        // String taskDescription = br.readLine();
        // System.out.println("Enter the task priority (0-1): ");
        // Double taskPriority = Double.parseDouble(br.readLine());
        // long taskDueTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
        // taskGroups.get(0).addTask(new Task(taskName, taskDescription, new Time(taskDueTime), taskPriority));
        taskGroups.get(0).addTask(new Task("Chem Lab", "Graphics", new Time(System.currentTimeMillis() + 1000), 1000000000000l, 0.7));
        taskGroups.get(0).addTask(new Task("Chem Lab", "Procedure", new Time(System.currentTimeMillis() + 1000), 1000000005464l, 0.8));
        // System.out.println(taskGroups.get(0).getTask(0));
        schedule.add(taskGroups.get(0).getTask(0));
        schedule.add(taskGroups.get(0).getTask(1));
        timeBlocks.add(new Activity("Swimming", "Swim Apex Fitness", 64800000l, 68400000l, (byte) 0b0010000));
        timeBlocks.add(taskGroups.get(0).getTask(0));
        timeBlocks.add(taskGroups.get(0).getTask(1));

        for (TimeBlock t : schedule) {
            System.out.println(t);
        }
    }

    public void sortSchedule(byte currentDay) {
        schedule.clear();
        long currentTime = System.currentTimeMillis();
        long oneHourMillis = 3600000; // One hour in milliseconds

        ArrayList<Activity> dayActivities = new ArrayList<>();
        for (TimeBlock t : timeBlocks) {
            if (t instanceof Activity) {
                Activity a = (Activity) t;
                if ((a.daysOfWeek & currentDay) > 0) {
                    dayActivities.add(a);
                }
            }
        }
        Collections.sort(dayActivities);
        for (Activity a : dayActivities) {
            schedule.add(a);
        }

        ArrayList<TimeBlock> openTimeBlocks = new ArrayList<>();



        // // Assign tasks to available time slots
        // ArrayList<Task> tasks = new ArrayList<>(timeBlocks.stream()
        //     .filter(t -> t instanceof Task)
        //     .map(t -> (Task) t)
        //     .toList());
        // tasks.sort(Comparator.comparingDouble(Task::getPriority).reversed());

        // for (Task task : tasks) {
        //     long taskDuration = task.getDuration();
        //     boolean taskScheduled = false;

        //     for (int i = 0; i < schedule.size() - 1; i++) {
        //     TimeBlock current = schedule.get(i);
        //     TimeBlock next = schedule.get(i + 1);

        //     long gapStart = current.getEndTime().getTime();
        //     long gapEnd = next.getStartTime().getTime();

        //     if (gapEnd - gapStart >= taskDuration) {
        //         schedule.add(new Task(task.getName(), task.getDescription(), new Time(gapStart), gapStart + taskDuration, task.getPriority()));
        //         taskScheduled = true;
        //         break;
        //     }
        //     }

        //     if (!taskScheduled) {
        //     long lastEndTime = schedule.isEmpty() ? dayStart : schedule.get(schedule.size() - 1).getEndTime().getTime();
        //     if (dayEnd - lastEndTime >= taskDuration) {
        //         schedule.add(new Task(task.getName(), task.getDescription(), new Time(lastEndTime), lastEndTime + taskDuration, task.getPriority()));
        //     }
        //     }
        // }

        // // Sort the schedule by start time
        // Collections.sort(schedule);
        
    }

    public void keyReleased(KeyEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        if (kc == KeyEvent.VK_UP) {
            scrollOffsetY -= 10;
        } else if (kc == KeyEvent.VK_DOWN) {
            scrollOffsetY += 10;
        }
    }
}