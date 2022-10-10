import javax.swing.*;
import java.awt.*;

public class Enemy {
    Image img = new ImageIcon("res/NotGamer.png").getImage();
    int x;
    int y;
    int v;
    Road road;

    //параметры прямоугольника, Желатьльно вынести в абстракт
    public Rectangle getRect () {
        return new Rectangle(x, y, 260, 130);
    }
    //параметры соперников
    public Enemy(int x, int y, int v, Road road){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }
    public void move(){
        x = x - road.p.v + v; // x = положение бота - скорость слоя(скорость плеера) + скорость бота

    }
}
