import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Buckethead_zombie extends Zombie{

    int type;//0代表有铁桶，1代表没有
    int max_picture;
    public Buckethead_zombie( int x,int y)
    {
        this.x = x ;
        this.y = y ;
        go_foward();
        Health=200;
        type=0;
    }

    @Override
    void go_foward() {
        if(type==0){
            state=1;
            speed=2;
            max_picture=14;
        }
        else if(type==1){
            state=1;
            speed=2;
            max_picture=30;
        }
    }

    public void go_forward_action(Plant[][] zws)
    {
        super.go_forward_action(zws);
        if(Health<=100&&Health>0&&type==0){

            picture=0;
            max_picture=14;
            type=1;
        }

    }
    void go_forward_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/铁桶僵尸/铁桶僵尸/铁桶僵尸"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
        if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/Zombie1/Frame"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }


    }
    void go_forward_ChangePicture()
    {
        if(picture==max_picture)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
        if(state==6){
            time++;
        }
        if(time>100){
            go_foward();
            time=0;
        }
    }

    void eat()
    {
        if(type==0){
            max_picture=10;
        }
        else if(type==1){
            max_picture=20;
        }
        state=2;
        picture=0;
    }

    void eat_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/铁桶僵尸/铁桶僵尸吃/铁桶吃"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
        else if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieAttack/Frame"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
    }

    public void eat_action(Plant[][] zws)
    {
        super.eat_action(zws);
        if(Health<=100&&Health>0&&type==0){

            picture=0;
            max_picture=20;
            type=1;
        }
    }

    void eat_ChangePicture()
    {
        if(picture==max_picture)
        {
            picture=0;
        }
        else
        {
            picture++;
        }
    }

    void die()
    {
        state=3;
        picture=0;
        max_picture=11;

    }
    void die_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieDie/Frame"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
        Image tu1 = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/ZombieHead/Frame"+picture+".png")).getImage();
        g.drawImage(tu1, x, y, null);//绘制图片API
    }




    void die_ChangePicture()
    {
        if(picture==max_picture)
        {
            to_be_cleared();
        }
        else
        {
            picture++;
        }
    }

    public void Display(Graphics g)
    {
        super.Display(g);

    }

    public void action(Plant[][] zws)
    {
        super.action(zws);

    }
}