import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    Image img = new ImageIcon("res/Driver.png").getImage();

    int v = 0;
    int dv = 0;
    int s = 0;
    int x = 30;
    int y = 100;
    int dy = 0;
    int layer1 = 0;
    int layer2 = 1200;
    // ограничения по скорости и выступам за дорогу
    public static final int MAX_V = 100;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM =450;

    //параметры прямоугольника, Желатьльно вынести в абстракт
    public Rectangle getRect () {
        return new Rectangle(x, y, 260, 130);
    }

    //метод для настройки параметров полета
    public void move() {
        s += v;
        v += dv;
        if (v <=0) v = 0;
        if (v >= MAX_V) v = MAX_V;
        y -= dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;

        if (layer2 - v <= 0){
            layer1 = 0;
            layer2 = 1200;
        } else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    // считка клавиш
    // при нажатии
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            dv = 5;
        }
        if (key == KeyEvent.VK_LEFT){
            dv = -5;
        }
        if (key == KeyEvent.VK_UP){
            dy = 10;
        }
        if (key == KeyEvent.VK_DOWN){
            dy = -10;
        }
    }

    // при отпускании
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
}
