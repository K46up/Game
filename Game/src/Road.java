import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;



public class Road extends JPanel implements ActionListener, Runnable {
    Timer mainTimer = new Timer(20,this);
    Image img = new ImageIcon("res/Road.jpg").getImage();
    Player p = new Player();

    // поток для создания соперников
    Thread enemiesFactor = new Thread(this);

    // массив соперников
    List<Enemy> enemies = new ArrayList<Enemy>();

    //конструктор запуска
    public Road () {
        mainTimer.start();
        enemiesFactor.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true); //фокус на дорогу для клавиш
    }

    // настройка поведения ботов
    @Override
    public void run() {
        while (true){
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200, rand.nextInt(450), rand.nextInt(60),this));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // клавиши
    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            p.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }

    }

    // рисуем на панели
    @Override
    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);// первый слой дороги
        g.drawImage(img, p.layer2, 0, null);// второй слой дороги
        g.drawImage(p.img, p.x, p.y, null);// игрок

        // множество ботов
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            // удаление ботов за отрисовкой
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
            }
        }
    }

    //Выполняется по таймеру каждые 20с
    @Override
    public void actionPerformed(ActionEvent e) {
        p.move(); // ехать
        repaint(); // перерисовывать
        testCollisionWithEnemies(); // тест аварии по прямоугольнику
        testWin(); //Конец игры, победа
    }

    //Конец игры, победа
    private void testWin() {
        if (p.s > 50000) {
            JOptionPane.showMessageDialog(null, "Победа");
            System.exit(0);
        }
    }

    // поражение при столкновении прямоугольников
    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){
                JOptionPane.showMessageDialog(null, "Поражение");
                System.exit(1);
            }
        }
    }
}
