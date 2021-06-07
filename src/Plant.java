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
    int anger;
    int picture;
    int Health;
    int max_picture;
    /**
     *
     * 1.摇摆,2.攻击4.待清除6显示虚影
     */
    public int state;

    public Plant() { }

    public void shawdow_erase_exit()
    {
        state=4;
    }

    public void shadow_display_entry()
    {
        state=6;
    }

    abstract public void shadow_display(Graphics g);

    abstract void display(Graphics g);

    abstract public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList);

}
