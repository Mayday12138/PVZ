import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Play
{

    YXJM	  yxjm;//模式1的游戏界面
    YXJM2     yxjm2;//模式2的游戏界面
    ZBJM      ksjm;//开始界面
    XZJM      xzjm;//选择卡片的界面

    /**
     * 初始化
     */
    void init()
    {
        ksjm = new ZBJM( this );

    }

    /**
     * 鼠标按下监听
     * @param e
     */
    void Mouse_Pressed(MouseEvent e)
    {
        if(ksjm!=null)
        {
            ksjm.Mouse_clicked(mx, my);
        }
        if(yxjm!=null)
        {
            yxjm.Mouse_clicked(mx, my);
        }
        if(xzjm!=null)
        {
            xzjm.Mouse_clicked(mx, my);
        }
        if(yxjm2!=null){
            yxjm2.Mouse_clicked(mx, my);
        }
    }

    /**
     * 鼠标移动监听
     * @param e
     */
    void Mouse_move(MouseEvent e)
    {
        if(yxjm!=null)
        {
            yxjm.Mouse_move(mx, my);
        }
        if(yxjm2!=null)
        {
            yxjm2.Mouse_move(mx, my);
        }
    }

    /**
     * 显示
     * @param g
     */
    void Draw(Graphics g)
    {
        if(yxjm!=null)
        {
            yxjm.Draw(g);
        }
        if(ksjm!=null)
        {
            ksjm.Draw(g);
        }
        if(xzjm!=null)
        {
            xzjm.Draw(g);
        }
        if(yxjm2!=null)
        {
            yxjm2.Draw(g);
        }
    }

    /**
     * 执行函数
     */
    void Timing_processing()
    {
        if(yxjm!=null)
        {
            yxjm.Timing_processing();
        }
        if(ksjm!=null)
        {
            ksjm.Timing_processing();
        }
        if(xzjm!=null)
        {
            xzjm.Timing_processing();
        }
        if(yxjm2!=null)
        {
            yxjm2.Timing_processing();
        }
    }

    window	ck	= null;
    settimer	ds1	= null;
    int	mx;		//鼠标的横坐标
    int	my;		//鼠标的纵坐标

    Play()
    {
        init();
        ck = new window();
        //窗口 宽+2*立体边, 高+2*立体边+标题栏
        ck.setSize(5 * 2 + 800, 600 + 2 * 5 + 25);
        ck.setVisible(true);
        //延时的毫秒
        ds1 = new settimer(70);
    }

    class window extends JFrame
    {
        private static final long	serialVersionUID	= -3162312491185895161L;
        Menu							cd					= null;
        mianban							mb					= null;
        window_listener						exit				= null;

        window()
        {
            mb = new mianban();
            this.add(mb);
            exit = new window_listener();
            this.addWindowListener(exit);
            this.repaint();
        }

        class window_listener extends WindowAdapter
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        }

        class mianban extends JPanel
        {
            private static final long	serialVersionUID	= 2085542735926784158L;
            Mouse_listener						sb					= null;
            mianban()
            {
                sb = new Mouse_listener();
                this.addMouseListener(sb);
                this.addMouseMotionListener(sb);
            }

            /**
             * 鼠标监听器
             */
            class Mouse_listener extends MouseAdapter implements MouseMotionListener
            {
                public void mousePressed(MouseEvent e)
                {
                    mx = e.getX();
                    my = e.getY();
                    Mouse_Pressed(e);
                    ck.repaint();
                }
                public void mouseMoved(MouseEvent e)
                {
                    mx = e.getX();
                    my = e.getY();
                    Mouse_move(e);
                    ck.repaint();
                }
            }

            /**
             * 画图
             * @param g
             */
            public void paint(Graphics g)
            {
                Draw(g);
            }
        }
    }

    /**
     * 游戏线程
     */
    class settimer implements Runnable//实现Runnable接口
    {
        Thread	xc	= null;
        long	jianGe;
        settimer(long jianGe)
        {
            this.jianGe = jianGe;
            xc = new Thread(this);
            xc.start();
        }
        public void run()
        {
            while (true)
            {
                try
                {
                    Thread.sleep(jianGe);
                    if (this == ds1)
                    {
                        Timing_processing();
                        ck.repaint();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args)
    {
        new Play();
    }
}