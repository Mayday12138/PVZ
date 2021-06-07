import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Shovel {
    int x;
    int y;
    int picture;
    int state;
    public Shovel(int x,int y)
    {
        this.x=x;
        this.y=y;
        picture=0;
        state=0;
    }

    public void display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/卡片/卡片选择/小铲子.png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
    }


    public boolean whether_press_mouse(int mbx,int mby)
    {
        if(new Rectangle(x, y, 50, 70).contains( mbx, mby))
        {
            return true;
        }
        return false;
    }
    public void waiting(){
        this.x=95+54*10;
        this.y=8;
        state=0;
    }
    public void moving(int mx,int my){
        x=mx;
        y=my;
    }
}