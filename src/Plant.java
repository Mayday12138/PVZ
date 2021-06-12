import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
//import java.util.LinkedList;

import javax.swing.ImageIcon;

abstract public class Plant
{
    int x=0;
    int y=0;
    /**
     * 记录植物的攻击状态，anger=20表示发出喷子弹的声音，anger=25生成子弹
     */
    int anger=0;
    /**
     * 记录当前对象图片已经更新到的张数，图片命名采用……1.png通过picture增加使得数字变化图像更新
     */
    int picture;
    int Health;
    /**
     * 用于定义图片换张的上限，不同对象对应的图片库不同
     *
     * */
    int max_picture;
    musicplayer musicplayerA;
    /**
     *
     * 1.摇摆,2.攻击4.待清除6显示虚影
     */
    public int state;

    public Plant() { }

    /**
     * 清除植物
     */
    public void shawdow_erase_exit()
    {
        state=4;
    }

    /**
     * 虚影初始化
     */
    public void shadow_display_entry()
    {
        state=6;
    }

    /**
     * 显示虚影
     * @param g
     */
    abstract public void shadow_display(Graphics g);

    /**
     * 显示植物
     * @param g
     */
    abstract void display(Graphics g);

    /**
     * 植物活动
     * @param jsList
     * @param zdList
     * @param ygList
     */
    abstract public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList);

}
