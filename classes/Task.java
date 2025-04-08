package classes;

import java.awt.*;

public class Task extends TimeBlock implements Comparable<Task> {
    Time dueDate;
    long length;
    long timeDone;
    Double priority;

    Button taskListButton;

    public void createButtons() {
        scheduleButton = new Button(Driver.timeBlockLeft, Driver.taskListGap, Driver.timeBlockWidth, Driver.timeBlockHeight, new Color(255, 255, 255), name);
        Driver.scheduleScrollingFrame.addChild(scheduleButton);

        taskListButton = new Button(Driver.taskListLeft, Driver.taskListGap, Driver.taskListWidth, Driver.taskListHeight, new Color(255, 255, 255));
        Driver.taskListScrollingFrame.addChild(taskListButton);
        Frame titlelabel = new Frame(10, 0, 0, Driver.taskListHeight, name);
        titlelabel.textAlignX = -1;
        taskListButton.addChild(titlelabel);
    }

    public Task(String name, String description, long startTime, long endTime, Time dueDate, long length, Double priority) {
        super(name, description, startTime, endTime);
        this.dueDate = dueDate;
        this.length = length;
        this.priority = priority;

        createButtons();
    }

    public String toString() {
        return String.format("Name: %s%nDescription: %s%nStart Time: %02d:%02d%nEnd Time: %02d:%02d%nDue Date: %s%nLength: %d%nPriority: %.2f%n", 
            name, description, startTime / 3600000, startTime / 60000 % 60, endTime / 3600000, endTime / 60000 % 60, dueDate, length, priority);
    }

    public int compareTo(Task o) {
        long curTime = System.currentTimeMillis();
        if (dueDate.time - curTime < 0 && o.dueDate.time - curTime < 0) {
            return -Double.compare(priority, o.priority);
        } else if (dueDate.time - curTime < 0) {
            return -1;
        } else if (o.dueDate.time - curTime < 0) {
            return 1;
        }

        int c1 = -Double.compare(((System.currentTimeMillis() - this.dueDate.time) / this.priority), ((System.currentTimeMillis() - o.dueDate.time) / o.priority));
        if (c1 != 0) return c1;
        return name.compareTo(o.name);
    }
}
