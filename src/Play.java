import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.awt.event.*;
//import java.util.*;
import javax.swing.*;


public class Play
{

    YXJM		yxjm;
    ZBJM      ksjm;
    XZJM      xzjm;
    /***************************TODO 初始化 在下面**********************************************************/
    void init()
    {
        ksjm = new ZBJM( this );

    }

    /***************************TODO 鼠标按下 在下面**********************************************************/
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
    }

    /***************************TODO 鼠标释放 在下面**********************************************************/
    void Mouse_Realease(MouseEvent e)
    {

    }

    /***************************TODO 鼠标移动 在下面**********************************************************/
    void Mouse_move(MouseEvent e)
    {
        if(yxjm!=null)
        {
            yxjm.Mouse_move(mx, my);
        }
    }


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
    }
    
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
    }

    void keyboard_pressed(KeyEvent e)
    {

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                //左键:

                break;
            case KeyEvent.VK_RIGHT:
                //右键:

                break;
            case KeyEvent.VK_UP:
                //上键:

                break;
            case KeyEvent.VK_DOWN:
                //下键:

                break;
            case KeyEvent.VK_1:
                //大键盘数字1":

                break;
            case KeyEvent.VK_W:
                //W:

                break;
        }
    }
    
    void keyboard_realease(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                //左键:

                break;
            case KeyEvent.VK_RIGHT:
                //右键:

                break;
            case KeyEvent.VK_UP:
                //上键:

                break;
            case KeyEvent.VK_DOWN:
                //下键:

                break;
            case KeyEvent.VK_1:
                //大键盘数字1":

                break;
            case KeyEvent.VK_W:
                //W:

                break;
        }
    }

    window	ck	= null;
    settimer	ds1	= null;
    int	mx;		//<----鼠标的横坐标
    int	my;		//<----鼠标的纵坐标

    Play()
    {
        init();

        ck = new window();
        //窗口 宽+2*立体边, 高+2*立体边+标题栏
        ck.setSize(5 * 2 + 800, 600 + 2 * 5 + 25);
        //设定窗口可见性setVisible  true/false
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
        keyboard_listener						jp					= null;

        window()
        {
            jp = new keyboard_listener();
            this.addKeyListener(jp);

            mb = new mianban();
            this.add(mb);

            exit = new window_listener();
            this.addWindowListener(exit);

            this.repaint();
        }

        class Menu extends JMenuBar
        {
           // private static final long	serialVersionUID	= -3948463724715292167L;
            JMenu						dan;											//菜单
            JMenuItem					xiang1;										//菜单项

            Menu_listener						cdjtq;

            Menu()
            {
                dan = new JMenu("游戏"); //菜单
                xiang1 = new JMenuItem("开局"); //菜单项

                this.add(dan);
                dan.add(xiang1);

                cdjtq = new Menu_listener();
                xiang1.addActionListener(cdjtq);
            }

            class Menu_listener implements ActionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == xiang1)
                    {

                    }
                }
            }
        }

        class window_listener extends WindowAdapter
        {
            public void windowClosing(WindowEvent e)
            {
                //ds1.xc.stop();
                System.exit(0);
            }
        }

        class keyboard_listener implements KeyListener
        {
            public void keyPressed(KeyEvent e)
            {
                keyboard_pressed(e);
                ck.repaint();
            }

            public void keyReleased(KeyEvent e)
            {
                keyboard_realease(e);
                ck.repaint();
            }

            public void keyTyped(KeyEvent e)
            {
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

                public void mouseReleased(MouseEvent e)
                {
                    mx = e.getX();
                    my = e.getY();
                    Mouse_Realease(e);
                    ck.repaint();
                }

                public void mouseClicked(MouseEvent e)//鼠标单击
                {
                }
            }

            public void paint(Graphics g)
            {
                Draw(g);

            }
        }
    }

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

    //main主方法 ，主类的“入口方法”
    public static void main(String[] args)
    {
        new Play();
    }
}