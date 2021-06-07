import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Repeater extends Plant{
    int time=0;
    public Repeater( int x , int y)
    {
        this.x=x;
        this.y=y;
        Health=50;
        Pea_shooter_swing_entry();
        max_picture=12;
    }
    public Repeater( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }
    public void Pea_shooter_swing_entry()
    {
        state=1;
        time=0;
    }

    public void Pea_shooter_swing_action(ArrayList<Zombie> jsList)
    {
        if(picture==max_picture)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if(judge_Zombies(jsList))
        {
            Pea_shooter_attack_entry();
        }

        if( Health<=0 )
        {
            shawdow_erase_exit();
        }
    }

    public void Pea_shooter_attack_entry()
    {
        state =2 ;
        time=8;
    }

    public void Pea_shooter_attack_action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList)
    {
        if(picture==max_picture)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if(!judge_Zombies(jsList))
        {
            Pea_shooter_swing_entry();
        }
        if( Health<=0 )
        {
            shawdow_erase_exit();
        }

        Pea_shooter_XuLi();
        if(Pea_shooter_judge_XuLi()&& (time%2)==0)
        {
            //造子弹
            Bullet bullet = new Bullet(x+30,y,0,0);

            //装入容器
            zdList.add(bullet);
            if(time==2){
                Pea_shooter_XieLi();
                time=8;
            }

        }
        if(Pea_shooter_judge_XuLi()){
            time--;
        }
    }

    void display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/机关枪/GatlingPea/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            Pea_shooter_swing_action(jsList);
        }
        else if(state==2)
        {
            Pea_shooter_attack_action( jsList,zdList);
        }

    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/机关枪/GatlingPea/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/机关枪.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }

    public boolean judge_Zombies(ArrayList<Zombie> jsList)
    {
        for(  int i= 0 ; i< jsList.size(); i++)
        {
            Zombie zombie = jsList.get(i);

            if(new Rectangle(x+80, y+50, 720-x, 30).intersects(zombie.getX(), zombie.getY(), 80, 100))
            {
                return true;
            }
        }
        return false;
    }

    public void Pea_shooter_XuLi()
    {
        anger++;

    }

    public boolean Pea_shooter_judge_XuLi()
    {
        return anger>=25;
    }

    public void Pea_shooter_XieLi()
    {
        anger=0;
    }
}
