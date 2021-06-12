import sun.security.provider.Sun;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sunflower extends Plant{
    /**
     * 向日葵初始化
     * @param x
     * @param y
     */

    public Sunflower( int x , int y)
    {
        this.x=x;
        this.y=y;
        sunflower_swing_entry();
    }
    public Sunflower( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }

    /**
     * 摇摆初始化
     */
    private void sunflower_swing_entry()
    {
        state=1;
        Health=50;
    }

    /**
     * 摇摆展示
     * @param g
     */
    private void sunflower_swing_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/向日葵/SunFlower/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 摇摆活动，生产阳光
     * @param ygList
     */
    private void sunflower_swing_action(ArrayList<yangguang>   ygList)
    {
        if(picture==17)
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

        sunflower_Xuli();
        if(sunflower_Xuli_tofull())
        {
            //造阳光
            yangguang sun=new yangguang(x,y);
            //装入容器
            ygList.add(sun);
            sunflower_reset();
        }
    }

    void display(Graphics g)
    {
        if(state==1)
        {
            sunflower_swing_display(g);
        }
    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            sunflower_swing_action(ygList);
        }
    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/向日葵/SunFlower/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/向日葵.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }

    public void sunflower_Xuli()
    {
        anger++;
    }

    public boolean sunflower_Xuli_tofull()
    {
        return anger>=100;

    }

    public void sunflower_reset()
    {
        anger=0;
    }

}
