import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StarPlant extends Plant{

    public StarPlant(int x , int y)
    {
        this.x=x;
        this.y=y;
        StarPlant_swing_entry();
    }
    public StarPlant( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }
    private void StarPlant_swing_entry()
    {
        Health=75;
        state=1;
    }

    private void StarPlant_swing_diplay(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/Starfruit/杨桃/Starfruit-"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    private void StarPlant_swing_action(ArrayList<Zombie> jsList)
    {
        if(picture==12)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if(judge_Zombies(jsList))
        {
            StarPlant_attack_entry();
        }

        if( Health<=0 )
        {
            shawdow_erase_exit();
        }
    }

    private void StarPlant_attack_entry()
    {
        state =2 ;
    }

    private void StarPlant_attack_action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList)
    {
        if(picture==12)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if(!judge_Zombies(jsList))
        {
            StarPlant_swing_entry();
        }
        if( Health<=0 )
        {
            shawdow_erase_exit();
        }

        StarPlant_XuLi();
        if(StarPlant_judge_XuLi())
        {
            //造子弹
            Bullet bullet = new Bullet(x+30,y,2,0);
            Bullet bullet_up=new Bullet(x,y-30,2,1);
            Bullet bullet_back=new Bullet(x-30,y,2,2);
            Bullet bullet_down=new Bullet(x,y+30,2,3);
            Bullet bullet_xie=new Bullet(x+30,y+30,2,4);
            //装入容器
            zdList.add(bullet_up);
            zdList.add(bullet_back);
            zdList.add(bullet_xie);
            zdList.add(bullet_down);
            zdList.add(bullet);
            StarPlant_XieLi();
        }

    }

    private void StarPlant_attack_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/Starfruit/杨桃/Starfruit-"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }


    void display(Graphics g)
    {
        if(state==1)
        {
            StarPlant_swing_diplay(g);
        }
        if(state==2)
        {
            StarPlant_attack_display(g);
        }
    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            StarPlant_swing_action(jsList);
        }
        else if(state==2)
        {
            StarPlant_attack_action( jsList,zdList);
        }

    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/Starfruit/杨桃/Starfruit-0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/五角星.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }



    public boolean judge_Zombies(ArrayList<Zombie> jsList)
    {
        for(  int i= 0 ; i< jsList.size(); i++)
        {
            Zombie zombie = jsList.get(i);

            if(new Rectangle(x-80, y-50, 720-x, 300).intersects(zombie.getX(), zombie.getY(), 80, 100))
            {
                return true;
            }
        }
        return false;
    }

    public void StarPlant_XuLi()
    {
        anger++;
    }

    public boolean StarPlant_judge_XuLi()
    {
        return anger>=25;
    }

    public void StarPlant_XieLi()
    {
        anger=0;
    }

}
