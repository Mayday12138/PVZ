import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Paper_zombie extends Zombie{

    int type;//0代表有报纸，1代表没有报纸
    int max_picture;
    //state==7时为丢失报纸的问号状态
    public Paper_zombie( int x,int y)
    {
        this.x = x ;
        this.y = y ;
        go_foward();
        Health=100;
        type=0;
    }

    @Override
    void go_foward() {
        if(type==0){
            state=1;
            speed=2;
            max_picture=18;
        }
        else if(type==1){
            state=1;
            speed=4;
            max_picture=13;
        }
    }

    public void go_forward_action(Plant[][] zws)
    {
        super.go_forward_action(zws);
        if(Health<=50&&Health>0&&type==0){
            state=7;
            picture=0;
            max_picture=10;
        }

    }
    void go_forward_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报僵尸走/"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
        if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报僵尸暴走/"+picture+".png")).getImage();
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
            max_picture=7;
        }
        else if(type==1){
            max_picture=6;
        }
        state=2;
        picture=0;
    }

    void eat_display(Graphics g)
    {
        if(type==0){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报吃有报纸/"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
        else if(type==1){
            Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报吃无报纸/"+picture+".png")).getImage();
            g.drawImage(tu, x, y, null);//绘制图片API
        }
    }

    public void eat_action(Plant[][] zws)
    {
        super.eat_action(zws);
        if(Health<=50&&Health>0&&type==0){
            state=7;
            picture=0;
            max_picture=10;
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
        max_picture=10;

    }
    void die_display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报僵尸的尸体/"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
        Image tu1 = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/读报僵尸的头/"+picture+".png")).getImage();
        g.drawImage(tu1, x, y, null);//绘制图片API
    }

    void wenhao_Changepicture(){
        if(picture==max_picture) {
            type=1;
            picture=0;
            go_foward();
        }
        else
        {
            picture++;
        }
    }

    void wenhao_display(Graphics g){
        Image tu = (new ImageIcon("植物大战僵尸/僵尸/读报僵尸/丢失的报纸/"+picture+".png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
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
        if(state==7){
            wenhao_display(g);
        }
    }

    public void action(Plant[][] zws)
    {
        super.action(zws);
        if(state==7){
            wenhao_Changepicture();
        }
    }
}