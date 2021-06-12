import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
//import java.awt.event.KeyEvent;
//import java.util.Set;

import javax.swing.ImageIcon;

public class ZBJM
{
    /**
     * 进度条的长度
     */
    int kuan=0;
    /**
     * 区分不同界面，1是加载界面，2是菜单界面
     */
    int	jiemian =1;
    musicplayer           bfq;
    Play			play;
    public ZBJM(	Play			play)
    {
        this.play=play;
        //初始化
        bfq = new musicplayer("植物大战僵尸/声音/Faster.wav");
        bfq.start_play();
    }


    void Draw(Graphics g)
    {
        if(jiemian==1)//进入游戏
        {
            Image tu = (new ImageIcon("植物大战僵尸/背景/游戏开始界面.jpg")).getImage();
            g.drawImage(tu, 0, 0, null);//绘制图片API
            g.setColor(Color.WHITE);
            g.fillRect(252, 540, 292, 25); //绘制实心矩形API
            g.setColor(Color.YELLOW);
            g.fillRect(252, 540, kuan, 25); //绘制实心矩形API
            g.setFont(new Font("宋体", 0, 15));
            g.setColor(Color.GREEN);
            if(kuan<=292)
            {
                g.drawString("游戏准备", 363, 557);
            }
            else
            {
                g.drawString("开始游戏", 363, 557);
            }
        }
        else if(jiemian==2)//操作选择
        {
            Image tu = (new ImageIcon("植物大战僵尸/背景/转化界面.png")).getImage();
            g.drawImage(tu, 0, 0, null);//绘制图片API
        }
    }

    private void jindutiao()
    {
        if(kuan<=292)
        {
            kuan=kuan+10;
        }
    }

    public void Timing_processing()
    {
        jindutiao();
    }

    public void Mouse_clicked(int mx, int my)
    {
        if(jiemian==1&&kuan>292&&new Rectangle(252, 540, 292, 25).contains(mx, my))
        {
            jiemian=2;
        }
        else if(jiemian==2&&new Rectangle(365,65,245,55).contains(mx, my))
        {
            play.ksjm.bfq.Stop_Player();
            play.ksjm=null;
            play.xzjm = new XZJM( play );
            play.xzjm.init();
        }
        else if(jiemian==2&&new Rectangle(371,156,246,55).contains(mx, my))
        {
            play.ksjm.bfq.Stop_Player();
            play.ksjm=null;
            play.yxjm2 = new YXJM2( play );
            play.yxjm2.init();
        }
        else if(jiemian==2&&new Rectangle(370,266,236,55).contains(mx, my))
        {
            System.exit(0);
        }
    }
}