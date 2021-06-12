
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class YXJM2 {
    Play play;
    musicplayer musicplayerA;
    int creat = 20;
    int count_z = 0;
    int check = 20;
    boolean ready=true;
    int totalnum_zombie=0;
    int wintime=0;      //用于卡住系统时间，在展示胜利前杀死当前场景所有僵尸
    int time=0;
    int def=0;
    int vic=0;//同模式一
    boolean judge_end=false;
    int all_killed=0;
    boolean victary=false;
    boolean pause=false;
    public YXJM2(Play play) {
        this.play = play;
    }

    ArrayList<Zombie> jsList;
    ArrayList<Card> kpList;
    ArrayList<Plant> plants;
    Background bj;
    Plant plant;
    musicplayer bfq;

    void init() {
        kpList =new ArrayList<>();
        jsList = new ArrayList<Zombie>();
        plants = new ArrayList<>();
        bj = new Background();
        bfq = new musicplayer("植物大战僵尸/声音/BGM2.wav");
        bfq.state=1;
        bfq.start_play();
    }

    void Mouse_clicked(int mx, int my) {
        for (int i = 0; i < kpList.size(); i = i + 1) {
            if (kpList.get(i).picture == 1) {
                int ah = (my - 81) / 100;
                int al = (mx - 34) / 80;
                if(al>8){
                    al=8;
                }
                if (kpList.get(i).whether_press(mx, my)) {
                    kpList.get(i).picture = 0;
                    plant = null;
                } else {
                    musicplayerA = new musicplayer("植物大战僵尸/声音/放卡.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                    if (kpList.get(i).name.equals("坚果")) {
                        Plant newplant = new nut001(al * 80, ah * 100,0);
                        plants.add(newplant);
                        kpList.remove(i);
                    }
                    else if(kpList.get(i).name.equals("马保国")) {
                        Plant newplant = new nut001(al * 80, ah * 100,1);
                        plants.add(newplant);
                        int random = new Random().nextInt(8);
                        if(random==1||random==5)
                        {
                            musicplayerA = new musicplayer("植物大战僵尸/声音/发生什么事了.wav");
                            musicplayerA.state=1;
                            musicplayerA.start_play_once();
                        }else if(random==2||random==6)
                        {
                            musicplayerA = new musicplayer("植物大战僵尸/声音/有备而来.wav");
                            musicplayerA.state=1;
                            musicplayerA.start_play_once();
                        }
                        else if(random==4||random==3||random==0)
                        {
                            musicplayerA = new musicplayer("植物大战僵尸/声音/来骗来偷袭.wav");
                            musicplayerA.state=1;
                            musicplayerA.start_play_once();
                        }
                        kpList.remove(i);
                    }
                    else if(kpList.get(i).name.equals("爆炸坚果")) {
                        Plant newplant = new nut001(al * 80, ah * 100,2);
                        plants.add(newplant);
                        kpList.remove(i);
                    }

                    plant = null;
                }
            } else if (kpList.get(i).name.equals("坚果") && kpList.get(i).whether_press(mx, my) ) {
                kpList.get(i).picture = 1;
                plant = new nut001(mx, my, 6,0);
            } else if (kpList.get(i).name.equals("马保国") && kpList.get(i).whether_press(mx, my) ) {
                kpList.get(i).picture = 1;
                    plant = new nut001(mx, my,6,1);
            }else if (kpList.get(i).name.equals("爆炸坚果") && kpList.get(i).whether_press(mx, my) ) {
                kpList.get(i).picture = 1;
                    plant = new nut001(mx, my, 6, 2);
            }
        }


        if(judge_end&&def==1)
        {
            play.yxjm2=null;
            play.ksjm=new ZBJM(play);
            play.ksjm.jiemian=2;
        }
        if(new Rectangle(750, 5, 30, 30).contains( mx, my)){
            pause=!pause;
            musicplayerA = new musicplayer("植物大战僵尸/声音/pause.wav");
            musicplayerA.state=1;
            musicplayerA.start_play_once();
        }
        if(victary){
            play.yxjm2=null;
            play.ksjm=new ZBJM(play);
            play.ksjm.jiemian=2;
        }
    }

    void Mouse_move(int mx, int my) {
        if (plant != null && plant.state == 6) {
            plant.x = mx;
            plant.y = my;
        }
    }

    void Draw(Graphics g) {
        if (!judge_end)
        //背景显示
        {
            if (all_killed == 2&&jsList.size()==0) {
                if(vic==0){
                    bfq.Stop_Player();
                    musicplayerA = new musicplayer("植物大战僵尸/声音/victory.wav");//测试播放时机问题
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                    vic++;
                }
                Image tu = (new ImageIcon("植物大战僵尸/背景/victory.png")).getImage();
                g.drawImage(tu, 317, 233, null);//绘制图片API
                victary = true;

            } else {
                if (totalnum_zombie == 120&&all_killed!=2) {

                    all_killed=1;
                }
                bj.Display2(g);
                //卡片显示
                for (int i = 0; i <= kpList.size() - 1; i = i + 1) {
                    kpList.get(i).display4(g,kpList);
                }
                //植物显示
                for (int i = 0; i <= plants.size() - 1; i = i + 1) {
                    plants.get(i).display(g);
                }
                //影子显示
                if (plant != null) {
                    plant.shadow_display(g);
                }
                //僵尸显示
                for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                    jsList.get(i).Display(g);
                }
                Image tu = (new ImageIcon("植物大战僵尸/植物/CoffeeBean/CoffeeBean-0.png")).getImage();
                g.drawImage(tu, 750, 5, null);//绘制图片API
                g.setFont(new Font("楷体", 0, 15));
                if (all_killed == 1) {
                    for (int i = 0; i < jsList.size(); i++) {
                        jsList.get(i).die();
                    }
                    all_killed = 2;
                }
            }
        }
        else  {
            Image tu = (new ImageIcon("植物大战僵尸/背景/游戏结束.png")).getImage();
            g.drawImage(tu, 118, 59, null);//绘制图片API
        }
    }

    void Timing_processing() {
        //0.随机生产僵尸================
        if(ready){
            musicplayerA = new musicplayer("植物大战僵尸/声音/僵尸进场.wav");
            musicplayerA.state=1;
            musicplayerA.start_play_once();
            ready=false;
        }
        if(all_killed==2){
            for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                jsList.get(i).action(null);
                if (jsList.get(i).state == 4) {
                    jsList.remove(i);
                }
            }
        }

        else  if (!pause) {
            if (new Random().nextInt(1000) < creat) {//1.造僵尸
                int x = 800;
                int y = 100 * new Random().nextInt(5);
                //2.装入容器
                int random = new Random().nextInt(8);
                Zombie newzombie;
                if (random == 2)
                    newzombie = new Paper_zombie(x, y);
                else if(random==3||random==4)
                    newzombie =new Buckethead_zombie(x,y);
                else newzombie = new Common_zombie(x, y);
                jsList.add(newzombie);
                count_z++;
                if (count_z % check == 0) {
                    creat = creat + 30;
                    check = 5 * check;
                }
            }
            //2.植物活动====================
            for(int i=0;i<plants.size();i++){
                plants.get(i).action(jsList, null, null);
                if (plants.get(i).state == 4)
                    plants.remove(i);
            }
            //3.僵尸换张与行走====================
            for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                jsList.get(i).action(null);
                if (jsList.get(i).state == 4) {
                    jsList.remove(i);
                    totalnum_zombie++;
                } else if (jsList.get(i).x < -80) {
                    judge_end = true;
                    if(def==0){
                        bfq.Stop_Player();
                        musicplayerA = new musicplayer("植物大战僵尸/声音/defeat.wav");//测试播放时机问题
                        musicplayerA.state=1;
                        musicplayerA.start_play_once();
                        def++;
                    }

                }
            }
            //4.卡片随机生成与平移=====================
            time++;
            Random rand=new Random();
            if(time>30+rand.nextInt(300)&&kpList.size()<=6){
                Random random=new Random();
                int a=random.nextInt(3);
                if(a==0)
                    kpList.add(new Card( "坚果"));
                if(a==1)
                    kpList.add(new Card( "马保国"));
                if(a==2)
                    kpList.add(new Card( "爆炸坚果"));
                time=0;
            }
            for(int j=0;j<kpList.size();j++){
                int a=-1;
                for(int i=0;i<kpList.size();i++){
                    if(kpList.get(i).x==kpList.get(j).x){
                        continue;
                    }
                    else if((kpList.get(j).x-kpList.get(i).x<60&&kpList.get(j).x-kpList.get(i).x>0)){
                        a=i;
                    }
                }
                if(a==-1&&kpList.get(j).x>100){
                    kpList.get(j).x -= 4;
                }
            }
        }
    }
}
