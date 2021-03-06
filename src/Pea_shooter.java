import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pea_shooter extends Plant{

    /**
     * 豌豆射手初始化
     * @param x
     * @param y
     */
    public Pea_shooter( int x , int y)
    {
        this.x=x;
        this.y=y;
        Health=50;
        Pea_shooter_swing_entry();
        max_picture=12;
    }
    public Pea_shooter( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }

    /**
     * 摇摆初始化
     */
    public void Pea_shooter_swing_entry()
    {
        state=1;
    }

    /**
     * 摇摆显示
     * @param g
     */
    public void Pea_shooter_swing_diplay(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/豆瓣/Peashooter/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    /**
     * 摇摆活动
     * @param jsList
     */
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

    /**
     * 攻击初始化
     */
    public void Pea_shooter_attack_entry()
    {
        state =2 ;
    }

    /**
     * 攻击活动
     * @param jsList
     * @param zdList
     */
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
        if(anger==20){
            musicplayerA = new musicplayer("植物大战僵尸/声音/shoot.wav");
            musicplayerA.state=1;
            musicplayerA.start_play_once();
        }
        if(Pea_shooter_judge_XuLi())
        {
            //造子弹
            Bullet bullet = new Bullet(x+30,y,0,0);

            //装入容器
            zdList.add(bullet);
            Pea_shooter_XieLi();
        }

    }

    /**
     * 攻击展示
     * @param g
     */
    public void Pea_shooter_attack_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/豆瓣/Peashooter/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }


    void display(Graphics g)
    {
        if(state==1)
        {
            Pea_shooter_swing_diplay(g);
        }
        if(state==2)
        {
            Pea_shooter_attack_display(g);
        }
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
            Image tu = (new ImageIcon("植物大战僵尸/植物/豆瓣/Peashooter/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/豆瓣.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }

    /**
     * 判断该路上是否有僵尸
     * @param jsList
     */
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

    /**
     * 蓄力
     */
    public void Pea_shooter_XuLi()
    {
        anger++;
    }

    /**
     * 判断是否已经蓄好力
     * @return
     */
    public boolean Pea_shooter_judge_XuLi()
    {
        return anger>=25;
    }

    /**
     * 卸力
     */
    public void Pea_shooter_XieLi()
    {
        anger=0;
    }

}
