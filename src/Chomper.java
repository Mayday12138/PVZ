import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chomper extends Plant{

    //1.摇摆2.攻击3.咀嚼
    public Chomper( int x , int y)
    {
        this.x=x;
        this.y=y;
        Chomper_swing_entry();
    }
    public Chomper( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }
    private void Chomper_swing_entry()
    {
        Health=250;
        state=1;
    }

    private void Chomper_swing_diplay(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/食人花/Chomper/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }

    private void Chomper_swing_action(ArrayList<Zombie> jsList)
    {
        if(picture==12)
        {
            picture=0;
        }
        else
        {
            picture++;
        }

        if(judge_Zombies(jsList)!=null)
        {
            Chomper_attack_entry();
        }
        if( Health<=0 )
        {
            shawdow_erase_exit();
        }
    }

    private void Chomper_attack_entry()
    {
        state =2 ;
        picture=0;
    }

    private void Chomper_attack_action(ArrayList<Zombie> jsList)
    {
        if(picture==8)
        {
            Zombie zombie=judge_Zombies(jsList);
            if(zombie!=null){
                Chomper_chew_entry();
                jsList.remove(judge_Zombies(jsList));
            }
            else{
                Chomper_swing_entry();
            }
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

    private void Chomper_attack_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/食人花/ChomperAttack/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }
    private void Chomper_chew_entry()
    {
        state =3 ;
        picture=0;
    }

    private void Chomper_chew_action(ArrayList<Zombie> jsList)
    {
        if(picture==5)
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

        anger++;
        if(anger>300)
        {
            anger=0;
            state=1;
        }

    }

    private void Chomper_chew_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/食人花/ChomperDigest/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }
    void display(Graphics g)
    {
        if(state==1)
        {
            Chomper_swing_diplay(g);
        }
        if(state==2)
        {
            Chomper_attack_display(g);
        }
        if(state==3)
        {
            Chomper_chew_display(g);
        }
    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            Chomper_swing_action(jsList);
        }
        else if(state==2)
        {
            Chomper_attack_action(jsList);
        }
        else if(state==3){
            Chomper_chew_action(jsList);
        }

    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/食人花/Chomper/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/食人花.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }

    public Zombie judge_Zombies(ArrayList<Zombie> jsList)
    {
        for(  int i= 0 ; i< jsList.size(); i++)
        {
            Zombie zombie = jsList.get(i);
            if(new Rectangle(x, y+50, 80, 30).intersects(zombie.getX(), zombie.getY(), 80, 100))
            {
                if(zombie.state!=3&&zombie.state!=4&&zombie.state!=5)
                return zombie;
            }
        }
        return null;
    }

}