
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Wall leftWall;
    private boolean leftUp = false;
    private boolean leftDown = false;

    private Wall rightWall;
    private boolean rightUp = false;
    private boolean rightDown = false;


    public KeyInput(Wall w1, Wall w2) {
        this.leftWall = w1;
        this.rightWall = w2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightWall.swapDirections(-1);
            rightUp = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightWall.swapDirections(1);
            rightDown = true;
        }
        if (key == KeyEvent.VK_A) {
            leftWall.swapDirections(-1);
            leftUp = true;
        }
        if (key == KeyEvent.VK_Z) {
            leftWall.swapDirections(1);
            leftDown = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightUp = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightDown = false;
        }
        if (key == KeyEvent.VK_A) {
            leftUp = false;
        }
        if (key == KeyEvent.VK_Z) {
            leftDown = false;
        }

        if (!leftUp && !leftDown)
            leftWall.stop();
        if (!rightUp && !rightDown)
            rightWall.stop();

    }
}