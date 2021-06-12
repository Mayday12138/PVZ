import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Card {
    int picture;
    /**
     * 判断卡片在窗口中显示的位置
     */
    int num_of_picture;
    String name;
    int x = 0;
    int y = 0;

    public Card(int num_of_picture, String name) {
        this.name = name;
        this.num_of_picture = num_of_picture;
        picture = 0;
    }
    public Card(String name){
        this.name = name;
        x=600;
        y=8;
    }


    public void display(Graphics g) {
        Image tu2 = (new ImageIcon("植物大战僵尸/卡片/卡片图/" + name + picture + ".png")).getImage();
        g.drawImage(tu2, 95 + 54 * num_of_picture, 8, null);//绘制图片API
        x = 95 + 54 * num_of_picture;
        y = 8;
    }

    /**
     * 选卡界面使用
     * @param g
     * @param i 判断在选卡界面卡槽第几个
     */
    public void display3(Graphics g, int i) {
        Image tu2 = (new ImageIcon("植物大战僵尸/卡片/卡片图/" + name + 0 + ".png")).getImage();
        g.drawImage(tu2, 95 + 54 * i, 8, null);//绘制图片API
        x = 95 + 54 * i;
        y = 8;
    }

    /**
     * 选卡界面中可选卡区域的展示
     * @param g
     */
    public void display2(Graphics g) {
        Image tu2 = (new ImageIcon("植物大战僵尸/卡片/卡片图/" + name + picture + ".png")).getImage();
        g.drawImage(tu2, 30 + 60 * num_of_picture - (num_of_picture / 7) * 7 * 60, (num_of_picture / 7) * 90 + 120, null);//绘制图片API
        x = 30 + 60 * num_of_picture - (num_of_picture / 7) * 7 * 60;
        y = (num_of_picture / 7) * 90 + 120;
    }

    /**
     * 保龄球模式使用
     * @param g
     * @param cards 更新卡槽内容
     */
    public void display4(Graphics g, ArrayList<Card> cards){
        Image tu2 = (new ImageIcon("植物大战僵尸/卡片/卡片图/" + name + picture + ".png")).getImage();
        g.drawImage(tu2, x, y, null);//绘制图片API

    }

    /**
     * 记录卡片是否被点击，mby，mbx为鼠标坐标
     * @param mbx
     * @param mby
     * @return
     */
    public boolean whether_press(int mbx, int mby) {
        if (new Rectangle(x, y, 50, 70).contains(mbx, mby)) {
            return true;
        }
        return false;
    }
}

