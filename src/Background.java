import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;




public class Background
{
    public void Display(Graphics g)
    {
        Image tu = (new ImageIcon("植物大战僵尸/背景/background1.jpg")).getImage();
        g.drawImage(tu, -220, 0, null);//绘制图片API
        Image tu1 = (new ImageIcon("植物大战僵尸/卡片/卡片选择/加长.png")).getImage();
        g.drawImage(tu1, 10, 0, null);//绘制图片API
    }

}