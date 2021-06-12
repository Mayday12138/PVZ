import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class nut001 extends Plant{
    int random;
    /**
     * 0是普通坚果，1是马保果，2是爆炸坚果
     */
    int type=0;
    /**
     * 用来记录上一个撞到的僵尸
     */
    Zombie zombie=null;
    ArrayList<Zombie> zombies=new ArrayList<>();

    /**
     * 保龄球初始化
     * @param x
     * @param y
     * @param type
     */
    public nut001( int x , int y,int type)
    {
        this.x=x;
        this.y=y;
        this.type=type;
        state=1;
    }
    public nut001( int x , int y,int state,int type)
    {
        this.x=x;
        this.y=y;
        this.type=type;
        this.state=state;
        shadow_display_entry();
    }

    /**
     * 保龄球展示
     * @param g
     */
    private void Nut_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/wallnut_001/Frame"+picture+".png")).getImage();
            g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
        }
        else if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/植物/马保国/mabaoguo/Frame"+picture+".png")).getImage();
            g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
        }
        else if(type==2&&anger==0){
            Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/bombnut/Frame"+picture+".png")).getImage();
            g.drawImage(tu,34 +x, 81+y, null);//绘制图片API
        }
        else if(type==2&&anger>0){
            musicplayerA = new musicplayer("植物大战僵尸/声音/bomb.wav");//测试播放时机问题
            musicplayerA.state=1;
            musicplayerA.start_play_once();
            Image tu = (new ImageIcon("植物大战僵尸/植物/爆炸草莓/Boom.gif")).getImage();

            g.drawImage(tu,x-33, 51+y, null);//绘制图片API
        }
    }

    /**
     * 保龄球活动
     * @param jsList
     */
    private void Nut_action(ArrayList<Zombie> jsList)
    {
        if(picture==11)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
        if(state==1){
            x=x+20;
        }

        if(state==2){
            if(random==0){
                if(type==0){
                    x=x+14;
                    y=y+14;
                }
                if(type==1){
                    x=x+7;
                    y=y+28;
                }
                if(y>450){
                    random=1;
                    zombie=null;
                }
            }
            if(random==1){
                if(type==0){
                    x=x+14;
                    y=y-14;
                }
                if(type==1){
                    x=x+7;
                    y=y-28;
                }
                if(y<0){
                    random=0;
                    zombie=null;
                }
            }
        }
        if(x>800){
            state=4;
        }
        Zombie zombie;
        if((zombie=touch_any_zombie(jsList))!=null){
            if(state==1){

                zombie.Health-=101;
                state=2;
                Random ran=new Random();
                random= ran.nextInt(2);
            }
            else if(state==2){
                int a=new Random().nextInt(8);
                if((a==3||a==6||a==7)&&type==1){
                    musicplayerA = new musicplayer("植物大战僵尸/声音/一个左正蹬.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                }
                if((a==0||a==1||a==2)&&type==1){
                    musicplayerA = new musicplayer("植物大战僵尸/声音/一个右边腿.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                }
                zombie.Health-=101;
                if(random==0){
                    random=1;
                }
                else if(random==1){
                    random=0;
                }
            }
        }
    }

    /**
     * 爆炸活动
     * @param jsList
     */
    private void bomb_action(ArrayList<Zombie> jsList)
    {
        if(state==1){
            if(picture==11)
            {
                picture=0;
            }
            else
            {
                picture++;
            }
            x=x+20;
            if(touch_any_zombie(jsList)!=null){
                for(  int zhi= 0 ; zhi<= jsList.size()-1  ; zhi=zhi+ 1   )
                {
                    Zombie thiszombie = jsList.get(zhi);
                    if(whether_hurt_zombie(thiszombie))
                    {
                        thiszombie.bomb();
                    }
                }
                anger=1;
                state=3;
            }
        }
        else if(state==3){
            anger++;
        }
        if(anger>5){
            state=4;
        }
    }

    /**
     * 判断是否攻击到僵尸
     * @param mb
     * @return
     */
    private boolean whether_hurt_zombie(Zombie mb)
    {
        if(new Rectangle(x-46, y-19, 240, 300).intersects(mb.getX()+34, mb.getY() +81, 80, 100))
        {
            return true;
        }
        return false;
    }

    /**
     * 保龄球每次改变方向仅可以对一个僵尸造成固定伤害
     * @param jsList
     * @return 被撞到的僵尸
     */
    public Zombie touch_any_zombie(ArrayList<Zombie> jsList)
    {
        for (int i = 0; i < jsList.size(); i++)
            {
                if(jsList.get(i).state!=3&&new Rectangle(jsList.get(i).getX()+100, jsList.get(i).getY()+81, 70, 80).intersects(x+54, y+101, 30,30))
                {
                    int j;
                    for(j = 0;j<zombies.size();j++){
                        if(jsList.get(i)==zombies.get(j)) {
                            if(zombie==jsList.get(i)){
                                break;
                            }
                            else{
                                zombie=jsList.get(i);
                                return jsList.get(i);
                            }
                        }
                    }
                    if(j==zombies.size()){
                        zombies.add(jsList.get(i));
                        zombie=jsList.get(i);
                        return jsList.get(i);
                    }


                }
            }
        return null;
    }

    void display(Graphics g)
    {
        Nut_display(g);
    }

    public void action(ArrayList<Zombie> jsList, ArrayList<Bullet>zdlist, ArrayList<yangguang> ygList)
    {
        if(type!=2){
            Nut_action(jsList);
        }
        else{
            bomb_action(jsList);
        }
    }

    public void shadow_display(Graphics g)
    {
        if(state==6)
        {
            if (type==0){
                Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/WallNut/Frame0.png")).getImage();
                g.drawImage(tu,x, y, null);//绘制图片API
                Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/坚果.png")).getImage();
                g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
            }
            else if(type==1){
                Image tu = (new ImageIcon("植物大战僵尸/植物/马保国/mabaoguo/Frame0.png")).getImage();
                g.drawImage(tu,x, y, null);//绘制图片API
                Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/马保国.png")).getImage();
                g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
            }
            else if(type==2){
                Image tu = (new ImageIcon("植物大战僵尸/植物/坚果/bombnut/Frame0.png")).getImage();
                g.drawImage(tu,x, y, null);//绘制图片API
                Image tu1 = (new ImageIcon("植物大战僵尸/卡片/准备种植/爆炸坚果.png")).getImage();
                g.drawImage(tu1,34+((x-34)/80)*80, 81+((y-81)/100)*100, null);//绘制图片API
            }

        }
    }
}