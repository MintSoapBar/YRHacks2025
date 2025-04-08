package classes;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Driver extends JPanel implements MouseListener, KeyListener, Runnable  {
    final static int FPS = 60;

    Schedule schedule = new Schedule();
    static ArrayList<TaskGroup> taskGroups = new ArrayList<>();

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

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {

            // TASK GROUP
            System.out.println("Enter the task group name: ");
            String name = br.readLine();
            System.out.println("Enter the year of the start date:");
            int year = Integer.parseInt(br.readLine());
            System.out.println("Enter the month of the start date:");
            int month = Integer.parseInt(br.readLine());
            System.out.println("Enter the day of the start date:");
            int day = Integer.parseInt(br.readLine());
            System.out.println("Enter the hour of the start date:");
            int hour = Integer.parseInt(br.readLine());
            System.out.println("Enter the minute of the start date:");
            int minute = Integer.parseInt(br.readLine());
            System.out.println("Enter the second of the start date:");
            int second = Integer.parseInt(br.readLine());
            long startTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
            System.out.println("Enter the year of the due date:");
            year = Integer.parseInt(br.readLine());
            System.out.println("Enter the month of the due date:");
            month = Integer.parseInt(br.readLine());
            System.out.println("Enter the day of the due date:");
            day = Integer.parseInt(br.readLine());
            System.out.println("Enter the hour of the due date:");
            hour = Integer.parseInt(br.readLine());
            System.out.println("Enter the minute of the due date:");
            minute = Integer.parseInt(br.readLine());
            System.out.println("Enter the second of the due date:");
            second = Integer.parseInt(br.readLine());
            long dueTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
            System.out.println("Enter the task group description: ");
            String description = br.readLine();
            System.out.println("Enter the task group priority (0-1): ");
            Double priority = Double.parseDouble(br.readLine());
            taskGroups.add(new TaskGroup(name, new Time(startTime), new Time(dueTime), description, priority));
            System.out.println(taskGroups.get(0));

            // TASK
            System.out.println("Enter the task name: ");
            String taskName = br.readLine();
            System.out.println("Enter the year of the task due date:");
            year = Integer.parseInt(br.readLine());
            System.out.println("Enter the month of the task due date:");
            month = Integer.parseInt(br.readLine());
            System.out.println("Enter the day of the task due date:");
            day = Integer.parseInt(br.readLine());
            System.out.println("Enter the hour of the task due date:");
            hour = Integer.parseInt(br.readLine());
            System.out.println("Enter the minute of the task due date:");
            minute = Integer.parseInt(br.readLine());
            System.out.println("Enter the second of the task due date:");
            second = Integer.parseInt(br.readLine());
            System.out.println("Enter the task description: ");
            String taskDescription = br.readLine();
            System.out.println("Enter the task priority (0-1): ");
            Double taskPriority = Double.parseDouble(br.readLine());
            long taskDueTime = LocalDateTime.of(year, month, day, hour, minute, second).toEpochSecond(ZoneOffset.ofHours(-4)) * 1000;
            taskGroups.get(0).addTask(new Task(taskName, new Time(taskDueTime), taskDescription, taskPriority));
            System.out.println(taskGroups.get(0).getTask(0));
        }
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