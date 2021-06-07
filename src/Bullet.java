import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet
{
    int x;
    int y;
    int type;
    int way;//way用于判别子弹的飞行模式，当前为Star服务，而后可添加抛掷类
    /**
     * 1.飞行2.爆炸3.待清除
     */
    public int state;

    public Bullet(int x,int y,int type,int way)
    {
        this.x=x;
        this.y=y;
        this.type=type;
        this.way=way;
        fly_entry();
    }

    private void fly_entry()
    {
        state=1;
    }

    private void fly_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/炮弹/PB00.png")).getImage();
            g.drawImage(tu, x+34, y+80, null);//绘制图片API
        }
        if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/炮弹/PB-12.png")).getImage();
            g.drawImage(tu, x+34, y+80, null);//绘制图片API
        }
        if(type==2){//杨桃
            Image tu = (new ImageIcon("植物大战僵尸/植物/Starfruit/Star.gif")).getImage();
            g.drawImage(tu, x+34, y+80, null);//绘制图片API
        }
    }

    public void fly_action(ArrayList<Zombie> jsList)
    {
        if(this.way==0)
            x+=20;
        else if(this.way==1)
            y-=20;
        else if(this.way==2)
            x-=20;
        else if(this.way==3)
            y+=20;
        else{
            x+=14;
            y+=14;
        }
        for(  int ge= 0 ; ge<= jsList.size()-1  ; ge=ge+ 1   )
        {
            Zombie zombie = jsList.get(ge);
            if(judge_touchZombie(jsList.get(ge)))
            {
                explode_entry();
                zombie.setHealth(zombie.getHealth()-8);
                if(type==1){
                    zombie.jiansu();
                }
                break;
            }
        }

        if(x>=800||y<0||x<=0||y>=600)
        {
            tobecleared_entry();
        }
    }

    private boolean judge_touchZombie(Zombie zombie)
    {
        if(new Rectangle(x, y, 30, 30).intersects(zombie.getX()+30, zombie.getY(), 80, 100))
            return true;
        return false;
    }

    private void explode_entry()
    {
        state=2;

    }

    private void explode_diplay(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/炮弹/炮弹爆炸.png")).getImage();
        g.drawImage(tu, x+10, y+60, null);//绘制图片API
    }

    private void explode_action(ArrayList<Zombie> jsList)
    {
        tobecleared_entry();
    }

    private void tobecleared_entry()
    {
        state=3;
    }

    public void action(ArrayList<Zombie> jsList)
    {
        if(state==1)
        {
            fly_action(jsList);
        }
        else if(state==2)
        {
            explode_action(jsList);
        }
    }

    void Display(Graphics g)
    {
        if(state==1)
        {
            fly_display(g);
        }
        else if(state==2)
        {
            explode_diplay(g);
        }
    }
}