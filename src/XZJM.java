import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class XZJM
{
    /**
     * 判断是否已经选择完毕
     */
    int state=0;
    /**
     * 图片转场时的参数
     */
    int i=0;
    musicplayer       bfq;
    Play		play;
    /**
     * 所有可以选择的卡片
     */
    ArrayList<Card> kpList1;
    /**
     * 已选择的卡片
     */
    ArrayList<Card> kpList2;
    ArrayList<Zombie> jsList;
    XZJM(Play	play){
        this.play=play;
        bfq = new musicplayer("植物大战僵尸/声音/choose.wav");
        bfq.start_play();
    }

    /**
     * 初始化
     */
    public void init(){
        kpList1=new ArrayList<>();
        kpList2=new ArrayList<>();
        jsList=new ArrayList<>();
        kpList1.add(new Card(0, "射手"));
        kpList1.add(new Card(1, "向日葵"));
        kpList1.add(new Card(2, "坚果"));
        kpList1.add(new Card(3, "炸弹"));
        kpList1.add(new Card(4, "寒冰射手"));
        kpList1.add(new Card(5, "火爆辣椒"));
        kpList1.add(new Card(6, "食人花"));
        kpList1.add(new Card(7, "杨桃"));
        kpList1.add(new Card(8, "机关枪"));

        Random random=new Random();
        int i=random.nextInt(5)+1;
        for(int j=0;j<i;j++){
            jsList.add(new Common_zombie(random.nextInt(300)+400,random.nextInt(400)+100));
        }

    }
    void Draw(Graphics g)
    {
        if(state==0){
            Image tu = (new ImageIcon("植物大战僵尸/背景/background1.jpg")).getImage();
            g.drawImage(tu, -600, 0, null);//绘制图片API
            Image tu1 = (new ImageIcon("植物大战僵尸/卡片/卡片选择/加长.png")).getImage();
            g.drawImage(tu1, 10, 0, null);//绘制图片API

            Image tu2 = (new ImageIcon("植物大战僵尸/卡片/卡片选择/卡片选择区.png")).getImage();
            g.drawImage(tu2, 10, 87, null);//绘制图片API


            for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                Image tu5 = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/Zombie"+jsList.get(i).type+"/Frame"+jsList.get(i).picture+".png")).getImage();
                g.drawImage(tu5, jsList.get(i).x, jsList.get(i).y, null);//绘制图片API
            }
            for (int i = 0; i <= kpList1.size() - 1; i = i + 1) {
                kpList1.get(i).display2(g);
            }
            for (int i = 0; i <= kpList2.size() - 1; i = i + 1) {
                kpList2.get(i).display3(g,i);
            }
        }
        if(state==1){
            Image tu = (new ImageIcon("植物大战僵尸/背景/background1.jpg")).getImage();
            g.drawImage(tu, -600+i, 0, null);//绘制图片API
            for (int j = 0; j <= jsList.size() - 1; j = j + 1) {
                Image tu5 = (new ImageIcon("植物大战僵尸/僵尸/普通僵尸/Zombie"+jsList.get(j).type+"/Frame"+jsList.get(j).picture+".png")).getImage();
                g.drawImage(tu5, jsList.get(j).x+i, jsList.get(j).y, null);//绘制图片API
            }
        }


    }


    public void Timing_processing()
    {
        for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
            jsList.get(i).go_forward_ChangePicture();

        }
        if(state==1){
            i=i+20;
            if(i-600==-220){
                play.yxjm = new YXJM( play ,kpList2);
                play.xzjm=null;
                play.yxjm.init();
            }
        }
    }

    public void Mouse_clicked(int mx, int my)
    {
        for (int i = 0; i < kpList1.size(); i = i + 1) {
            if (kpList1.get(i).picture == 1) {
                if (kpList1.get(i).whether_press(mx, my)) {
                    kpList1.get(i).picture = 0;
                    kpList2.remove(kpList1.get(i));
                }
            }
            else if (kpList1.get(i).whether_press(mx, my) && kpList1.get(i).picture==0) {
                kpList1.get(i).picture = 1;
                kpList2.add(kpList1.get(i));
            }
            }


        if(new Rectangle(165,552,155,60).contains(mx, my))
        {
            bfq.Stop_Player();
            state=1;
        }

    }


}