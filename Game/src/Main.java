
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame f = new JFrame("F1");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрытие на крестик
        f.setSize(1100,600); //размер окна
        f.add(new Road()); // добавление дороги и всего, что на ней
        f.setVisible(true); //видимость фрейма

    }
}
