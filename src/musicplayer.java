import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class musicplayer extends Thread
{

    public String location;

    /**
     * 1.尚未开始 2.播放中3.结束
     */
    public int state;
    private AudioClip clip;

    public musicplayer(String location)
    {
        this.location=location;
        state = 1;

    }

    public void start_play()
    {
        if(state != 1 ) return;
        state =2;
        //----------------------播放音频API  以下-------------------------------------
        new Thread(new Runnable()
        {
            public void run()
            {
                while (state!=3){
                    try
                    {
                        File file = new File(location);
                        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                        AudioFormat format = stream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                        byte[] buf = new byte[1024];
                        line.open();
                        line.start();
                        int nbytes = 0;
                        while (nbytes != -1)
                        {
                            if(  state==3  ){
                                break;
                            }
                            nbytes = stream.read(buf, 0, buf.length);
                            if (nbytes >= 0)
                                line.write(buf, 0, nbytes);
                        }
                        line.drain();
                        line.close();
                    }
                    catch (UnsupportedAudioFileException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (LineUnavailableException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        //----------------------播放音频API  以上-------------------------------------
    }

    public void start_play_once()
    {
        if(state != 1 ) return;
        state =2;
        //----------------------播放音频API  以下-------------------------------------
        new Thread(new Runnable()
        {
            public void run()
            {
                    try
                    {
                        File file = new File(location);
                        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                        AudioFormat format = stream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                        byte[] buf = new byte[1024];
                        line.open();
                        line.start();
                        int nbytes = 0;
                        while (nbytes != -1)
                        {
                            if(  state==3  ){
                                break;
                            }
                            nbytes = stream.read(buf, 0, buf.length);
                            if (nbytes >= 0)
                                line.write(buf, 0, nbytes);
                        }
                        line.drain();
                        line.close();
                    }
                    catch (UnsupportedAudioFileException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (LineUnavailableException e)
                    {
                        e.printStackTrace();
                    }
            }
        }).start();
        //----------------------播放音频API  以上-------------------------------------
    }


    public void Stop_Player()
    {
        state=3;
    }
}
