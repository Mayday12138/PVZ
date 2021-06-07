import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class yangguang
{
    int x;
    int y;
    int maxy;
    int state;//1是活动2是待清除
    int Sun_num;
    int picture;
    int time;
    int a=10;

    public yangguang()
    {
        state=1;
        this.y=-80;
        this.x=80*new Random().nextInt(9);
        maxy=100*new Random().nextInt(5);
    }
    public yangguang(int x,int y)
    {
        state=1;
        this.y=y;
        this.x=x;
    }
    public void Display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/阳光/Frame"+picture+".png")).getImage();
        g.drawImage(tu, 34+x,81+ y, null);//绘制图片API
    }

    public void ChangePicture()
    {
        if(picture==21)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
    }

    public void fall()
    {
        if(state==1){
            if(y<maxy)
            {
                y+=5;
            }
            else
            {
                time++;
                if(time>50)
                {
                    state=2;
                }
            }
        }

    }

    boolean  is_touch(int mx,int my)
    {
        if(new Rectangle(34+x,81+ y, 78, 78).contains(mx, my))
        {
            return true;
        }
        return false;
    }

    public void movetoend(){
        int xx=x+10;
        int yy=y+60;
        if(xx<1&&yy<1){
            state=2;
        }
        x=x-xx/a;
        y=y-yy/a;
        if(a!=1){
            a--;
        }

    }
}
