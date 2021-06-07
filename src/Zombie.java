import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

abstract public class Zombie
{
    int x;
    int y;
    int Health;
    int picture;
    int speed=2;
    int time=0;//是否被减速
    int type;
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     *
     * 1.前进2.啃食3.死亡4.待清除5.爆炸死亡6.减速7.道具丢失
     */
    public int state;

    void go_foward()
    {
        state=1;
        speed=2;
    }

    abstract void go_forward_display(Graphics g);

    abstract void go_forward_ChangePicture();

    public void go_forward_action(Plant[][] zws)
    {
        x-=speed;
        for(  int h= 0 ; h<= 4  ; h=h+ 1   )
        {
            for(  int l= 0 ; l<= 8  ; l=l+ 1   )
            {
                if (touch_plant(zws[h][l]))
                {
                    eat();
                }
            }
        }
        if(Health<=0)
        {
            die();
        }
    }

    void eat()
    {
        state=2;
        picture=0;
    }

    abstract void eat_display(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return Health;
    }

    public int getState() {
        return state;
    }

    public void eat_action(Plant[][] zws)
    {
        for (int h = 0; h < 5; h++)
        {
            for (int l = 0; l < 9; l++)
            {
                if(touch_plant(zws[h][l]))
                {
                    zws[h][l].Health--;
                }
            }
        }

        if(!touch_any_plants(zws))
        {
            go_foward();
        }

        if(Health<=0)
        {
            die();
        }
    }

    public boolean touch_any_plants(Plant[][] zws)
    {
        for (int h = 0; h < 5; h++)
        {
            for (int l = 0; l < 9; l++)
            {
                if(zws[h][l]!=null&&new Rectangle(x+100, y+81, 80, 100).intersects(zws[h][l].x+34, zws[h][l].y+81, 70,70))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean touch_plant(Plant zw)
    {
        if(zw!=null&&new Rectangle(x+100, y+81, 80, 100).intersects(34+zw.x, 81+zw.y, 80,100) )
            return true;
        return false;
    }

    abstract void eat_ChangePicture();

    void die()
    {
        state=3;
        picture=0;
    }


    void die_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieDie/Frame"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
        Image tu1 = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieHead/Frame"+picture+".png")).getImage();
        g.drawImage(tu1, x, y, null);//绘制图片API
    }

    void die_ChangePicture()
    {
        if(picture==17)
        {
            to_be_cleared();
        }
        else
        {
            picture++;
        }
    }

    public void bomb()
    {
        state=5;
        picture =0;
    }

    void bomb_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/炸死/Frame"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
    }

    void bomb_ChangePicture()
    {
        if(picture==19)
        {
            to_be_cleared();
        }
        else
        {
            picture++;
        }
    }

    void to_be_cleared()
    {
        state=4;
    }

    public void jiansu(){
        state=6;
        int s=speed;
        if(speed>2)
            speed=3;
        if(speed==2){
            speed=1;
        }
    }
    public void Display(Graphics g)
    {
        if(state==1||state==6)
        {
            go_forward_display(g);
        }

        if(state==2)
        {
            eat_display(g);
        }

        if(state==3)
        {
            die_display(g);
        }

        if(state==5)
        {
            bomb_display(g);
        }
    }

    public void action(Plant[][] zws)
    {

        if(state==1||state==6)
        {
            go_forward_ChangePicture();
            go_forward_action(zws);
        }

        if(state==2)
        {
            eat_ChangePicture();
            eat_action(zws);
        }

        if(state==3)
        {
            die_ChangePicture();
        }

        if(state==5)
        {
            bomb_ChangePicture();
        }
    }
}
