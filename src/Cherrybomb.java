import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cherrybomb extends Plant{
    public Cherrybomb( int x , int y)
    {
        this.x=x;
        this.y=y;
        bomb_bigger_entry();
    }
    public Cherrybomb( int x , int y,int state)
    {
        this.x=x;
        this.y=y;
        this.state=state;
        shadow_display_entry();
    }
    /**
     * 变大初始化
     */
    private void bomb_bigger_entry()
    {
        picture=0;
        state=1;
        Health=500;
    }
    /**
     * 变大展示
     * @param g
     */
    private void bomb_bigger_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/爆炸草莓/CherryBomb/Frame"+picture+".png")).getImage();
        g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
    }
    /**
     * 变大活动
     */
    private void bomb_bigger_action()
    {
        if(picture==6)
        {
            bomb_explore_entry();
        }
        else
        {
            picture++;
        }
    }
    /**
     * 爆炸初始化
     */
    private void bomb_explore_entry()
    {
        state=2;
    }

    /**
     * 爆炸展示
     * @param g
     */
    private void bomb_explore_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/植物/爆炸草莓/Boom.gif")).getImage();
        g.drawImage(tu,x-33, 51+y, null);//绘制图片API
    }

    /**
     * 爆炸展示
     * @param jsList 僵尸数组
     */
    private void bomb_explore_action(ArrayList<Zombie> jsList)
    {
        anger++;
        for(  int zhi= 0 ; zhi<= jsList.size()-1  ; zhi=zhi+ 1   )
        {
            Zombie thiszombie = jsList.get(zhi);
            if(whether_hurt_zombie(thiszombie))
            {
                thiszombie.bomb();
            }
        }
        if ( anger == 3  )
        {
            shawdow_erase_exit();
        }
    }
    /**
     * 判断是否炸到僵尸
     * @param mb 僵尸
     */
    private boolean whether_hurt_zombie(Zombie mb)
    {
        if(new Rectangle(x-46, y-19, 240, 300).intersects(mb.getX()+34, mb.getY() +81, 80, 100))
        {
            return true;
        }
        return false;
    }

    void display(Graphics g)
    {
        if(state==1)
        {
            bomb_bigger_display(g);
        }
        if(state==2)
        {
            bomb_explore_display(g);
        }
    }

    public void action(ArrayList<Zombie> jsList,ArrayList<Bullet> zdList,ArrayList<yangguang> ygList)
    {
        if(state==1)
        {
            bomb_bigger_action();
        }
        if(state==2)
        {
            bomb_explore_action(jsList);
            musicplayerA = new musicplayer("植物大战僵尸/声音/bomb.wav");//测试播放时机问题
            musicplayerA.state=1;
            musicplayerA.start_play_once();
        }
    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            Image tu = (new ImageIcon("植物大战僵尸/植物/爆炸草莓/CherryBomb/Frame0.png")).getImage();
            g.drawImage(tu,x, y, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/爆炸草莓.png")).getImage();
            g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
        }
    }
}
