import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Nut extends Plant{
    /**
     * 坚果初始化
     * @param x
     * @param y
     */
    public Nut( int x , int y)
    {
        this.x=x;
        this.y=y;
        Nut_full_healthy_entry();

    }

    public Nut( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }

    /**
     * 满状态初始化
     */
    private void Nut_full_healthy_entry()
    {
        state=1;
        Health=500;
    }

    /**
     * 满状态展示
     * @param g
     */
    private void Nut_full_healthy_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/WallNut/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 满状态活动
     */
    private void Nut_full_healthy_action()
    {
        if(picture==15)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if( Health<=300 )
        {
            Nut_hurt_entry();
        }
    }

    /**
     * 受伤初始化
     */
    private void Nut_hurt_entry()
    {
        state=2;
        picture=0;
    }

    /**
     * 受伤展示
     * @param g
     */
    private void Nut_hurt_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/Wallnut_cracked1/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 受伤活动
     */
    private void Nut_hurt_action()
    {
        if(picture==10)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if( Health<=150 )
        {
            Nut_badlyhurt_entry();
        }
    }

    /**
     * 重伤初始化
     */
    private void Nut_badlyhurt_entry()
    {
        state=3;
        picture=0;
    }

    /**
     * 重伤展示
     * @param g
     */
    private void Nut_badlyhurt_diaplay(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/Wallnut_cracked2/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 重伤活动
     */
    private void Nut_badlyhurt_action()
    {
        if(picture==14)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if( Health<=0 )
        {
            shawdow_erase_exit();
        }
    }

    void display(Graphics g)
    {
        if(state==1)
        {
           Nut_full_healthy_display(g);
        }
        if(state==2)
        {
            Nut_hurt_display(g);
        }
        if(state==3)
        {
            Nut_badlyhurt_diaplay(g);
        }
    }

    public void action(ArrayList<Zombie> jsList, ArrayList<Bullet> zdList, ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            Nut_full_healthy_action();
        }
        if(state==2)
        {
           Nut_hurt_action();
        }
        if(state==3)
        {
           Nut_badlyhurt_action();
        }

    }


    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/WallNut/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/坚果.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }
}
