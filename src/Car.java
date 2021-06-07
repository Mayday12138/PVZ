import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Car{
    int x;
    int y;
    int state;
    Car(int x,int y)
    {
        this.x=x;
        this.y=y;
        state=0;
    }

   public void display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/小车.png")).getImage();
        g.drawImage(tu, x, y, null);//绘制图片API
    }

    public void rush_action(ArrayList<Zombie> jsList)
    {
        if(this.state==1)
        {
            x+=50;
         ArrayList<Zombie> zomblist=run_touchZombie(jsList);
         if(zomblist!=null){
             for(int i=0;i<zomblist.size();i++){
                 zomblist.get(i).die();
             }
         }
        }
        if(x>=800)
        {
            tobecleared_entry();
        }
    }
    private ArrayList<Zombie> run_touchZombie(ArrayList<Zombie> jsList){

        ArrayList<Zombie> zb=new ArrayList<Zombie>();
        for(  int ge= 0 ; ge<= jsList.size()-1  ; ge=ge+ 1   )
        {
            Zombie zombie= jsList.get(ge);
            if(new Rectangle(x, y, 30, 20).intersects(zombie.getX()+30, zombie.getY()+81, 80, 20))
                zb.add(zombie);
        }
        if(zb.isEmpty())
        return null;
        else return zb;
    }
    public void Display(Graphics g)
    {
            display(g);
    }
    public void action(ArrayList<Zombie> jsList)
    {
        if(state==1)
        {
            rush_action(jsList);
            state=1;
        }
        else if(state==0)
        {
            quiet_action(jsList);
        }

    }
    public void quiet_action(ArrayList<Zombie> jsList){
        if(run_touchZombie(jsList)!=null){
            state=1;
        }
    }
    public void tobecleared_entry(){
        state=2;
    }
}