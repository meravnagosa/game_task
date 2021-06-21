

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OnStartMenu extends MouseAdapter {
    private Rectangle playButton;
    private String onStartText = "Start";
    private boolean hoverPlayBtn = false;
    private Rectangle QuitButton;
    public boolean active;
    private String exitText = "Exit";
    private boolean hoverQuit = false;
    private Font font;

    public OnStartMenu(Game game) {

        int position1, position2, width, height;
        active = true;
        game.start();
        width = 400;
        height = 150;
        position2 = Game.HEIGHT / 2 - height / 2;
        position1 = Game.WIDTH / 4 - width / 2;
        playButton = new Rectangle(position1, position2, width, height);
        position1 = Game.WIDTH * 3 / 4 - width / 2;
        QuitButton = new Rectangle(position1, position2, width, height);

        font = new Font("David", Font.PLAIN, 100);
    }

    public void mouseMoved(MouseEvent event) {
        Point point = event.getPoint();
        hoverPlayBtn = playButton.contains(point);
        hoverQuit = QuitButton.contains(point);
    }

    public void draw(Graphics graphics) {
        Graphics2D graphics1 = (Graphics2D) graphics;
        graphics.setFont(font);

        graphics.setColor(Color.black);
        if (hoverPlayBtn)
            graphics.setColor(Color.blue);
        graphics1.fill(playButton);

        graphics.setColor(Color.black);
        if (hoverQuit)
            graphics.setColor(Color.white);
        graphics1.fill(QuitButton);

        graphics.setColor(Color.white);
        graphics1.draw(playButton);
        graphics1.draw(QuitButton);

        int strWidth, strHeight;
        strWidth = graphics.getFontMetrics(font).stringWidth(onStartText);
        strHeight = graphics.getFontMetrics(font).getHeight();

        graphics.setColor(Color.white);
        graphics.drawString(onStartText, (int) (playButton.getX() + playButton.getWidth() / 2 - strWidth / 2),
                (int) (playButton.getY() + playButton.getHeight() / 2 + strHeight / 4));
        strWidth = graphics.getFontMetrics(font).stringWidth(exitText);
        strHeight = graphics.getFontMetrics(font).getHeight();

        graphics.setColor(Color.red);
        graphics.drawString(exitText, (int) (QuitButton.getX() + QuitButton.getWidth() / 2 - strWidth / 2),
                (int) (QuitButton.getY() + QuitButton.getHeight() / 2 + strHeight / 4));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point p = e.getPoint();
        if (playButton.contains(p))
            active = false;
        else if (QuitButton.contains(p))
            System.exit(0);

    }

}