import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird implements ActionListener, KeyListener {
    private final int WIDTH = 800, HEIGHT = 600;
    private JFrame frame;
    private JPanel panel;
    private Timer timer;
    private ArrayList<Rectangle> pipes;
    private int ticks, yMotion, score;
    private boolean gameOver, started;
    private Rectangle bird;

    public FlappyBird() {
        frame = new JFrame("Flappy Bird");
        panel = new GamePanel();
        timer = new Timer(20, this);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        pipes = new ArrayList<>();
        addPipe(true);
        addPipe(true);
        addPipe(true);
        addPipe(true);

        timer.start();
    }

    private void addPipe(boolean start) {
        int space = 200;
        int pipeWidth = 100;
        int pipeHeight = 50 + (int) (Math.random() * 300);

        if (start) {
            pipes.add(new Rectangle(WIDTH + pipeWidth + pipes.size() * 300, HEIGHT - pipeHeight - 120, pipeWidth, pipeHeight));
            pipes.add(new Rectangle(WIDTH + pipeWidth + (pipes.size() - 1) * 300, 0, pipeWidth, HEIGHT - pipeHeight - space));
        } else {
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, HEIGHT - pipeHeight - 120, pipeWidth, pipeHeight));
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, pipeWidth, HEIGHT - pipeHeight - space));
        }
    }

    private void paintPipe(Graphics g, Rectangle pipe) {
        g.setColor(Color.green.darker());
        g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
    }

    private void jump() {
        if (gameOver) {
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            pipes.clear();
            yMotion = 0;
            score = 0;

            addPipe(true);
            addPipe(true);
            addPipe(true);
            addPipe(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 10;

        if (started) {
            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);

                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);

                    if (pipe.y == 0) {
                        addPipe(false);
                    }
                }
            }

            bird.y += yMotion;

            for (Rectangle pipe : pipes) {
                if (pipe.intersects(bird)) {
                    gameOver = true;

                    if (bird.x <= pipe.x) {
                        bird.x = pipe.x - bird.width;
                    } else {
                        if (pipe.y != 0) {
                            bird.y = pipe.y - bird.height;
                        } else if (bird.y < pipe.height) {
                            bird.y = pipe.height;
                        }
                    }
                }
            }

            if (bird.y > HEIGHT - 120 || bird.y < 0) {
                gameOver = true;
            }

            if (bird.y + yMotion >= HEIGHT - 120) {
                bird.y = HEIGHT - 120 - bird.height;
            }
        }

        panel.repaint();
        ticks++;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.cyan);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.orange);
            g.fillRect(0, HEIGHT - 120, WIDTH, 120);

            g.setColor(Color.green);
            g.fillRect(0, HEIGHT - 120, WIDTH, 20);

            g.setColor(Color.red);
            g.fillRect(bird.x, bird.y, bird.width, bird.height);

            for (Rectangle pipe : pipes) {
                paintPipe(g, pipe);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));

            if (!started) {
                g.drawString("Press SPACE to Start", 200, HEIGHT / 2 - 50);
            }

            if (gameOver) {
                g.drawString("Game Over!", 300, HEIGHT / 2 - 50);
            }

            if (!gameOver && started) {
                g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
            }
        }
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}