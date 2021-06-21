

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    public static final int SIZE = 15;
    private int ballPositionX;
    int ballPositionY;
    private int xVelocity, yVelocity;
    private int speed = 5;

    public Ball() {
        reset();
    }


    private void reset() {

        xVelocity = Game.sign(Math.random() * 2.0 - 1);
        yVelocity = Game.sign(Math.random() * 2.0 - 1);
        ballPositionX = Game.WIDTH / 2 - SIZE / 2;
        ballPositionY = Game.HEIGHT / 2 - SIZE / 2;
    }
    public int getX() {
        return ballPositionX;
    }
    public int getY() {
        return ballPositionY;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(ballPositionX, ballPositionY, SIZE, SIZE);
    }
    public void update(Wall left, Wall right) {

        ballPositionX += xVelocity * speed;
        ballPositionY += yVelocity * speed;
        if (ballPositionY + SIZE >= Game.HEIGHT || ballPositionY <= 0)
            changeYDirection();
        if (ballPositionX + SIZE >= Game.WIDTH) {
            left.addScore();
            reset();
        }
        if (ballPositionX <= 0) {
            right.addScore();
            reset();
        }
    }

    public void changeXDirection() {
        xVelocity *= -1;
    }

    public void changeYDirection() {
        yVelocity *= -1;
    }
}