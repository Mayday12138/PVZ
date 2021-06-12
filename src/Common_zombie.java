import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Common_zombie extends Zombie{

    int max_picture;

    /**
     * 普通僵尸初始化
     * @param x
     * @param y
     */
    public Common_zombie( int x,int y)
    {
        Random random=new Random();
        int a=random.nextInt(3);
        this.x = x ;
        this.y = y ;
        go_foward();
        Health=50;
        type=a;
        if(type==0){
            max_picture=21;
        }
        if(type==1){
            max_picture=30;
        }
        if(type==2){
            max_picture=17;
        }
    }

    /**
     * 行走展示
     * @param g
     */
    void go_forward_display(Graphics g)
    {
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/Zombie"+type+"/Frame"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API

    }

    /**
     * 行走换图
     */
    void go_forward_ChangePicture()
    {
        if(state==6){
            time++;
        }
        if(picture==max_picture)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
        if(time>100){
            go_foward();
            time=0;
        }
    }

    /**
     * 吃展示
     * @param g
     */
    void eat_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieAttack/Frame"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
    }

    /**
     * 吃换图
     */
    void eat_ChangePicture()
    {
        if(picture==20)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
    }
}
