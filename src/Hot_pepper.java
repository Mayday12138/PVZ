import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Hot_pepper extends Plant{
    int picture2;

    /**
     * 火爆辣椒初始化
     * @param x
     * @param y
     */
    public Hot_pepper( int x , int y)
    {
        this.x=x;
        this.y=y;
        Hot_pepper_bigger_entry();
    }
    public Hot_pepper( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }

    /**
     * 变大初始化
     */
    private void Hot_pepper_bigger_entry()
    {
        picture=0;
        picture2=0;
        state=1;
        Health=500;
    }

    /**
     * 变大展示
     * @param g
     */
    private void Hot_pepper_bigger_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/辣椒/Jalapeno/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 变大活动
     */
    private void Hot_pepper_bigger_action()
    {
        if(picture==7)
        {
            Hot_pepper_explore_entry();
        }
        else
        {
            picture++;
        }

    }

    /**
     * 爆炸初始化
     */
    private void Hot_pepper_explore_entry()
    {
        state=2;
    }

    /**
     * 爆炸展示
     * @param g
     */
    private void Hot_pepper_explore_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/辣椒/JalapenoAttack/Frame"+picture2+".png")).getImage();
        g.drawImage(tu,40, 51+y, null);//绘制图片API?
    }

    /**
     * 爆炸活动
     * @param jsList
     */
    private void Hot_pepper_explore_action(ArrayList<Zombie> jsList)
    {
        picture2++;
        for(  int zhi= 0 ; zhi<= jsList.size()-1  ; zhi=zhi+ 1   )
        {
            Zombie thiszombie = jsList.get(zhi);
            if(whether_hurt_zombie(thiszombie))
            {
                thiszombie.bomb();
            }
        }
        if ( picture2 ==7  )
        {
            shawdow_erase_exit();
        }
    }

    /**
     * 判断是否攻击刀僵尸
     * @param mb
     */
    private boolean whether_hurt_zombie(Zombie mb)
    {
        if(new Rectangle(0, 81+y, 1200, 70).intersects(mb.getX()+34, mb.getY() +81, 80, 100))
        {
            return true;
        }
        return false;
    }

    void display(Graphics g)
    {
        if(state==1)
        {
            Hot_pepper_bigger_display(g);
        }
        if(state==2)
        {
            Hot_pepper_explore_display(g);
        }

    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            Hot_pepper_bigger_action();
        }
        if(state==2)
        {
            Hot_pepper_explore_action(jsList);
        }

    }


    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/辣椒/Jalapeno/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/辣椒.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }
}
