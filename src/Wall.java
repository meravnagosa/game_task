import java.awt.*;

public class Wall {

    private int position1, position2;
    private int velocity = 0;
    private int speed = 10;
    private int width = 22, height = 85;
    private int points = 0;
    private Color color;
    private boolean left;

    public Wall(Color color, boolean left) {
        this.color = Color.red;
        this.left = left;

        if (left)
            position1 = 0;
        else
            position1 = Game.WIDTH - width;

        position2 = Game.HEIGHT / 2 - height / 2;
    }
    public void addScore() {
        points++;
    }
    public void draw(Graphics graphics) {

        graphics.setColor(color);
        graphics.fillRect(position1, position2, width, height);

        int txtPosition;
        int padding = width * 10;
        String scoreText = Integer.toString(points);
        Font font = new Font("David", Font.PLAIN, 50);

        if (left) {
            int strWidth = graphics.getFontMetrics(font).stringWidth(scoreText);
            txtPosition = Game.WIDTH / 2 - padding - strWidth;
        } else {
            txtPosition = Game.WIDTH / 2 + padding;
        }

        graphics.setFont(font);
        graphics.drawString(scoreText, txtPosition, 50);
    }

    public void update(Ball ball) {
        position2 = Game.range(position2 + velocity, 0, Game.HEIGHT - height);
        int ballX = ball.getX();
        int ballY = ball.getY();
        if (left) {
            if (ballX <= width + position1 && ballY + Ball.SIZE >= position2 && ballY <= position2 + height)
                ball.changeXDirection();
        } else {

            if (ballX + Ball.SIZE >= position1 && ballY + Ball.SIZE >= position2 && ballY <= position2 + height)
                ball.changeXDirection();
        }
    }

    public void swapDirections(int direction) {
        velocity = speed * direction;
    }
    public void stop() {
        velocity = 0;
    }
}

