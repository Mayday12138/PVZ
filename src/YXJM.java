import javax.swing.*;
import java.awt.*;
import java.util.*;


public class YXJM {
    Play play;
    /**
     * 播放背景音乐
     */
    musicplayer    bfq;
    /**
     *播放音效
     */
    musicplayer musicplayerA;
    boolean ready=true;
    int creat = 20;
    /**
     * 胜利时只发出一次信号就结束线程
     */
    int vic=0;
    /**
     * 失败时只发出一次信号
     */
    int def=0;
    int count_z = 0;
    int check = 20;
    int totalnum_zombie=0;
    /**
     * 用于卡住系统时间，在展示胜利前杀死当前场景所有僵尸
     */
    int wintime=0;
    /**
     * 判断游戏是否结束
     */
    boolean judge_end=false;
    int all_killed=0;
    /**
     * 判断是否胜利
     */
    boolean victary=false;
    /**
     * 判断是否暂停
     */
    boolean pause=false;
    public YXJM(Play play,ArrayList<Card> a) {

        this.play = play;
        this.kpList=a;
        for(int i=0;i<kpList.size();i++){
            kpList.get(i).num_of_picture=i;
            kpList.get(i).picture=0;
        }
        bfq = new musicplayer("植物大战僵尸/声音/BGM1.wav");
        bfq.start_play();
    }

    ArrayList<yangguang> ygList;
    ArrayList<Zombie> jsList;
    ArrayList<Bullet> zdList;
    ArrayList<Card> kpList;
    ArrayList<Car> car;
    Background bj;
    Plant[][] zws;
    Plant plant;
    Shovel shovel;
    int sunnum;

    void init() {
        ygList = new ArrayList<yangguang>();
        jsList = new ArrayList<Zombie>();
        zdList = new ArrayList<Bullet>();
        car=new ArrayList<Car>();
        bj = new Background();
        shovel = new Shovel(95 + 54 * 10, 8);
        zws = new Plant[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                zws[i][j] = null;
            }
        }
        int y;
        for(int i=0;i<5;i++){
            y=81+100*i;
            Car car1=new Car(0,y);
            car.add(car1);
        }
        sunnum = 600;
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
                    musicplayerA = new musicplayer("植物大战僵尸/声音/放卡.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                }
                else if (zws[ah][al] == null) {
                    if (kpList.get(i).name.equals("射手")) {
                        Plant newplant = new Pea_shooter(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 100;
                    }
                    if (kpList.get(i).name.equals("寒冰射手")) {
                        Plant newplant = new Ice_shooter(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 175;
                    }
                    if (kpList.get(i).name.equals("向日葵")) {
                        Plant newplant = new Sunflower(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 50;
                    }
                    if (kpList.get(i).name.equals("坚果")) {
                        Plant newplant = new Nut(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 50;
                    }
                    if (kpList.get(i).name.equals("炸弹")) {
                        Plant newplant = new Cherrybomb(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 150;
                    }
                    if (kpList.get(i).name.equals("火爆辣椒")) {
                        Plant newplant = new Hot_pepper(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 125;
                    }
                    if (kpList.get(i).name.equals("食人花")) {
                        Plant newplant = new Chomper(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 150;
                    }
                    if (kpList.get(i).name.equals("杨桃")) {
                        Plant newplant = new StarPlant(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 125;
                    }
                    musicplayerA = new musicplayer("植物大战僵尸/声音/放植物.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                    kpList.get(i).picture = 0;
                    plant = null;
                }
                else if(zws[ah][al] instanceof Pea_shooter){
                    if (kpList.get(i).name.equals("机关枪")) {
                        Plant newplant = new Repeater(al * 80, ah * 100);
                        zws[ah][al] = newplant;
                        sunnum -= 250;
                    }
                    musicplayerA = new musicplayer("植物大战僵尸/声音/放植物.wav");
                    musicplayerA.state=1;
                    musicplayerA.start_play_once();
                    kpList.get(i).picture = 0;
                    plant = null;
                }
            }
            else if(kpList.get(i).whether_press(mx, my)){
                if (kpList.get(i).name.equals("射手") && kpList.get(i).whether_press(mx, my) && sunnum >= 100) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Pea_shooter)) {
                        plant = new Pea_shooter(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("寒冰射手") && kpList.get(i).whether_press(mx, my) && sunnum >= 175) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Ice_shooter)) {
                        plant = new Ice_shooter(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("向日葵") && kpList.get(i).whether_press(mx, my) && sunnum >= 50) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Sunflower)) {
                        plant = new Sunflower(mx, my, 6);
                    }

                } else if (kpList.get(i).name.equals("坚果") && kpList.get(i).whether_press(mx, my) && sunnum >= 50) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Nut)) {
                        plant = new Nut(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("炸弹") && kpList.get(i).whether_press(mx, my) && sunnum >= 150) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Cherrybomb)) {
                        plant = new Cherrybomb(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("火爆辣椒") && kpList.get(i).whether_press(mx, my) && sunnum >= 125) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Hot_pepper)) {
                        plant = new Hot_pepper(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("食人花") && kpList.get(i).whether_press(mx, my) && sunnum >= 150) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Chomper)) {
                        plant = new Chomper(mx, my, 6);
                    }
                } else if (kpList.get(i).name.equals("杨桃") && kpList.get(i).whether_press(mx, my) && sunnum >= 125) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof StarPlant)) {
                        plant = new StarPlant(mx, my, 6);
                    }
                }else if (kpList.get(i).name.equals("机关枪") && kpList.get(i).whether_press(mx, my) && sunnum >= 250) {
                    kpList.get(i).picture = 1;
                    if (!(plant instanceof Repeater)) {
                        plant = new Repeater(mx, my, 6);
                    }
                }
                musicplayerA = new musicplayer("植物大战僵尸/声音/取卡.wav");
                musicplayerA.state=1;
                musicplayerA.start_play_once();
            }
        }

        for (int ge = 0; ge <= ygList.size() - 1; ge = ge + 1) {
            if (ygList.get(ge).is_touch(mx, my)&&ygList.get(ge).state!=3) {
                ygList.get(ge).state=3;
                ygList.get(ge).movetoend();
                sunnum += 25;

                musicplayerA = new musicplayer("植物大战僵尸/声音/sun.wav");
                musicplayerA.state=1;
                musicplayerA.start_play_once();
            }
        }

        if (shovel.picture == 1) {
            int ah = (my - 81) / 100;
            int al = (mx - 34) / 80;
            if (zws[ah][al] != null) {
                zws[ah][al] = null;
            }
            shovel.picture = 0;
            shovel.waiting();
        }
        if (shovel.whether_press_mouse(mx, my)) {
            shovel.picture = 1;
            shovel.state = 1;
            shovel.moving(mx, my);
        }
        if(judge_end&&def==3)
        {
            play.yxjm=null;
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
            play.yxjm=null;
            play.ksjm=new ZBJM(play);
            play.ksjm.jiemian=2;
        }
    }

    void Mouse_move(int mx, int my) {
        if (plant != null && plant.state == 6) {
            plant.x = mx;
            plant.y = my;
        }
        if (shovel != null && shovel.state == 1) {
            shovel.moving(mx - 30, my - 30);
        }
    }

    void Draw(Graphics g) {
        if (!judge_end)
            //背景显示
        {

            if (all_killed == 2&&jsList.size()==0) {
                if(vic==0){
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
                    bfq.Stop_Player();
                    /*musicplayer musicplayer=new musicplayer("植物大战僵尸/音乐/defeat.wav");
                    bfq2.add(musicplayer);
                    bfq2.get(bfq2.size()-1).start_play_once();*/
                    all_killed=1;
                }

                bj.Display(g);
                //卡片显示
                for (int i = 0; i <= kpList.size() - 1; i = i + 1) {
                    kpList.get(i).display(g);
                }
                //割草机显示
                for (int i = 0; i <= car.size() - 1; i = i + 1) {
                    car.get(i).Display(g);
                }
                //植物显示
                for (int h = 0; h < 5; h++) {
                    for (int l = 0; l < 9; l++) {
                        if (zws[h][l] != null) {
                            zws[h][l].display(g);
                        }
                    }
                }
                //影子显示
                if (plant != null) {
                    plant.shadow_display(g);
                }
                //铲子显示
                if (shovel != null) {
                    shovel.display(g);
                }
                //僵尸显示
                for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                    jsList.get(i).Display(g);
                }
                //子弹显示
                for (int i = 0; i <= zdList.size() - 1; i = i + 1) {
                    zdList.get(i).Display(g);
                }
                //阳光显示
                for (int ge = 0; ge <= ygList.size() - 1; ge = ge + 1) {
                    ygList.get(ge).Display(g);
                }
                Image tu = (new ImageIcon("植物大战僵尸/植物/CoffeeBean/CoffeeBean-0.png")).getImage();
                g.drawImage(tu, 750, 5, null);//绘制图片API
                g.setFont(new Font("楷体", 0, 15));
                g.drawString("" + sunnum, 40, 80);
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
                jsList.get(i).action(zws);
                if (jsList.get(i).state == 4) {
                    jsList.remove(i);
                }
            }
        }

        else  if (!pause&&!judge_end&&!victary) {
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
            //1.子弹活动===================
            for (int i = 0; i <= zdList.size() - 1; i = i + 1) {
                zdList.get(i).action(jsList);
            }
            //2.植物活动====================
            for (int h = 0; h < 5; h++) {
                for (int l = 0; l < 9; l++) {
                    try {
                        if (zws[h][l] != null) {

                            zws[h][l].action(jsList, zdList, ygList);
                            if (zws[h][l].state == 4)

                                zws[h][l] = null;
                        }
                    } catch (Exception e) {
                        break;
                    }

                }
            }
            //3.僵尸换张与行走====================

            for (int i = 0; i <= jsList.size() - 1; i = i + 1) {
                jsList.get(i).action(zws);
                if (jsList.get(i).state == 4) {
                    jsList.remove(i);
                    totalnum_zombie++;
                } else if (jsList.get(i).x < -80) {
                    judge_end = true;
                    if (def == 0) {
                        musicplayerA = new musicplayer("植物大战僵尸/声音/defeat.wav");//测试播放时机问题
                        musicplayerA.state=1;
                        musicplayerA.start_play_once();
                        def++;
                    }
                    if(def==1){
                        musicplayerA = new musicplayer("植物大战僵尸/声音/咀嚼.wav");//测试播放时机问题
                        musicplayerA.state=1;
                        def++;
                    }
                   if(def==2){
                       def++;
                        musicplayerA = new musicplayer("植物大战僵尸/声音/scream.wav");//测试播放时机问题
                        musicplayerA.state=1;
                        musicplayerA.start_play_once();bfq.Stop_Player();
                    }
                }
            }

            //4.阳光活动=======================
            if (new Random().nextInt(600) < 20) {
                yangguang newyangguang = new yangguang();
                ygList.add(newyangguang);
            }
            for (int ge = 0; ge <= ygList.size() - 1; ge = ge + 1) {
                ygList.get(ge).fall();
                ygList.get(ge).ChangePicture();
                if (ygList.get(ge).state == 3) {
                    ygList.get(ge).movetoend();
                }
                if (ygList.get(ge).state == 2) {
                    ygList.remove(ge);
                }

            }
            //5.割草机活动======================
            for (int i = 0; i <= car.size() - 1; i = i + 1) {
                car.get(i).action(jsList);
                if (car.get(i).state == 2) {
                    car.remove(i);
                }
            }

        }

    }
}