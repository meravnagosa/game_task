
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public final static int WIDTH = 1000;
    public final static int HEIGHT = WIDTH * 10 / 20;

    public boolean running = false;
    private Thread gameThread;

    private Ball ball;
    private Wall leftWall;
    private Wall rightWall;
    private OnStartMenu mainMenu;

    public Game() {

        canvas();
        new Window("Game", this);
        init();

        this.addKeyListener(new KeyInput(leftWall, rightWall));
        this.addMouseListener(mainMenu);
        this.addMouseMotionListener(mainMenu);
        this.setFocusable(true);
    }

    private void init() {
        this.ball = new Ball();
        this.leftWall = new Wall(Color.white, true);
        this.rightWall = new Wall(Color.white, false);
        this.mainMenu = new OnStartMenu(this);
    }

    private void canvas() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void run() {
        this.requestFocus();
        // game timer
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSec = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSec;
            lastTime = now;
            if (delta >= 1) {
                update();
                delta--;
                draw();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }

        stop();
    }
    public void draw() {

        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();
        drawBackground(graphics);

        if (mainMenu.active)
            mainMenu.draw(graphics);

        ball.draw(graphics);
        leftWall.draw(graphics);
        rightWall.draw(graphics);
        graphics.dispose();
        buffer.show();

    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public void stop() {
        try {
            gameThread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void drawBackground(Graphics graphics) {

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.white);
        Graphics2D graphics2D = (Graphics2D) graphics;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
        graphics2D.setStroke(dashed);
        graphics.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }
    public void update() {

        if (!mainMenu.active) {
            ball.update(leftWall, rightWall);
            leftWall.update(ball);
            rightWall.update(ball);
        }
    }

    public static int range(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    public static int sign(double d) {
        if (d <= 0)
            return -1;
        return 1;
    }
    public static void main(String[] args) {
        System.out.println("To move the right wall press A to up Or Z to down\nto move the left wall use the arrows");
        new Game();
    }
}