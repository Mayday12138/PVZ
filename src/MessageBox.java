import javax.swing.*;
import com.sun.awt.AWTUtilities;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
public class MessageBox {

    /**
     * swing弹窗提示,
     *
     * @param fontSize
     * @param msg
     * @return
     */
    public static JFrame alert(int fontSize, String msg) {

        JFrame frame = new JFrame("");// 新建窗体
        Window win = new Window(frame);// 设置圆角
        // JFrame.setDefaultLookAndFeelDecorated(true);//设置为swing默认窗体
        AWTUtilities.setWindowShape(win,
                new RoundRectangle2D.Double(0.0D, 0.0D, win.getWidth(), win.getHeight(), 26.0D, 26.0D));
        Color color = new Color(0, 0, 0, 50);// 黑色背景，透明度为50的color；透明的取值范围0~255；
        frame.setAlwaysOnTop(true);// 设置窗口置顶
        frame.setLayout(new GridBagLayout());// 设置网格包布局
        frame.setUndecorated(true);// 设置无边框
        frame.setBackground(color);// 设置背景色

        JLabel label = new JLabel(msg);
        label.setForeground(Color.white);
        label.setFont(new Font("黑体", 0, fontSize));
        frame.setSize(msg.length() * fontSize, fontSize + 50);
        // 长度为字符大小*字符数量，宽度为字体大小+50像素
        frame.add(label);// 添加到窗体

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
}
