package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.event.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import org.havi.ui.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class HelloTVXlet  implements Xlet, UserEventListener, HActionListener
{
    
    //classes
    Grid[] grid;
    Block[] snake;
    Block food;

        
    //MHP Stuff
    private HScene scene;
    private HStaticText lblPoints = new HStaticText("score: 0");  
    private HStaticText lblGrid[] = new HStaticText[4096];
    private HTextButton resetbutton = new HTextButton("reset");
    
    Font myfont = new Font("Serif", Font.BOLD, 22);    
       
    DVBColor backgroundcolor = new DVBColor(new DVBColor(0,100,0,255));
    DVBColor snakecolor = new DVBColor(new DVBColor (0,0,0,255));
    DVBColor foodcolor = new DVBColor(new DVBColor (0,0,0,255));
    
    
    
    
    
    
    public void initXlet(XletContext context) 
    {
        //MHP Stuff
        HSceneTemplate sceneTemplate = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, 
                new HScreenDimension(1.0f,1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
                new HScreenPoint(0.0f,0.0f), HSceneTemplate.REQUIRED);
        
        scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        scene.setBackground(backgroundcolor);
        scene.setBackgroundMode(1);
        
        //init lblPoints
        lblPoints.setSize(125,40);
        lblPoints.setLocation(585,20);
        lblPoints.setBackground(new DVBColor(255,255,255,0));
        lblPoints.setBackgroundMode(HVisible.BACKGROUND_FILL);
        lblPoints.setFont(myfont);        
        scene.add(lblPoints);
        
        //init grid
        for(int i = 0; i < 4096; i ++)
        {
        lblGrid[i] = new HStaticText("");
        lblGrid[i].setSize(10,10);
        
        lblGrid[i].setLocation(0,0);
        
        lblGrid[i].setBackground(backgroundcolor);
        lblGrid[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
        lblGrid[i].setForeground(new DVBColor(0,0,0,255));
        lblGrid[i].setFont(myfont);        
        scene.add(lblGrid[i]);
        }
        
        //init resetbutton
        resetbutton = new HTextButton("Reset");
        resetbutton.setSize(125,50);
        resetbutton.setLocation(585,70);
        scene.add(resetbutton);
        resetbutton.requestFocus();
        resetbutton.setActionCommand("resetbutton");
        resetbutton.addHActionListener(this);
        
      
        
        UserEventRepository uev = new UserEventRepository("mijn verzameling");
        uev.addAllArrowKeys();
        EventManager.getInstance().addUserEventListener(this, uev);
        
       
        
    }
    public void startXlet() 
    {
        scene.validate();
        scene.setVisible(true);
    }
    
    public void Paint()
    {
       
    }
    
    public void Reset()
    {
       
    }

    public void pauseXlet() {
     System.out.println("pauseXlet");
    }

    public void destroyXlet(boolean unconditional) {
        System.out.println("destroyXLet");
       }
    
    public void userEventReceived(UserEvent e) {
        
        if(e.getType() == HRcEvent.KEY_PRESSED){
            switch(e.getCode())
            {
                 case HRcEvent.VK_UP:
                    
                   
                    break;
                    
                case HRcEvent.VK_DOWN:
                    
                    break;
                
                case HRcEvent.VK_LEFT:
                    
                    break;
                case HRcEvent.VK_RIGHT:
                   
                    break;
               
            }
            
            }
            
        }
    
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand() == "resetbutton")
        {
          //Reset();
        }
    }
   
    
    
}
