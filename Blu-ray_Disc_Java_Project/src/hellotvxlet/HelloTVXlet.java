package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.event.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import org.havi.ui.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;


public class HelloTVXlet  implements Xlet, UserEventListener, HActionListener
{
    int initx = 0;
    int inity = 0;
    
    int rows = 32;
    int cols = 32;
    int squaresize = 18;
    
    int initsnakelength = 10;
    int snakelength = initsnakelength;
    int score = 0;
    int direction = 3;
   
    int intervalat = 500; //ms
    int counttointerval = 0;
    int timerinterval = 100;
    
    boolean holdkey = false;
    boolean holdtimer = false;
    
    //classes
    Block[] snake;
    Grid[] grid;
    Block food;

        
    //MHP Stuff
    private HScene scene;
    private HStaticText lblPoints = new HStaticText("score: 0");  
    private HStaticText lblGrid[] = new HStaticText[rows*cols];
    private HTextButton resetbutton = new HTextButton("reset");
    
    Font myfont = new Font("Serif", Font.BOLD, 22);    
       
    DVBColor backgroundcolor = new DVBColor(new DVBColor(0,100,0,255));
    DVBColor snakecolor = new DVBColor(new DVBColor (0,0,0,255));
    DVBColor foodcolor = new DVBColor(new DVBColor (0,0,0,255));
    DVBColor gridcolor = new DVBColor(new DVBColor(0,150,0,255));
   
    Timer t;
    
    
    
    
    
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
        for(int i = 0; i < rows*cols; i ++)
        {
            lblGrid[i] = new HStaticText("");
            lblGrid[i].setSize(squaresize-1,squaresize-1);
        
            lblGrid[i].setLocation(initx,inity);
        
        
            initx += squaresize;

                if(initx >= cols * squaresize)
                {
                    inity += squaresize;
                    initx = 0;
                }
             lblGrid[i].setBackground(gridcolor);
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
        
        //init virtual grid
        initx = 0;
        inity = 0;
        grid = new Grid[cols*rows];
        for (int i = 0; i < grid.length; i++)
        {
            grid[i] = new Grid(initx,inity);
            initx += squaresize;

                if(initx >= cols * squaresize)
                {
                    inity += squaresize;
                    initx = 0;
                }
        }
        
        //init snake
        snake = new Block[cols*rows];

        for (int i = 0; i < snakelength; i++)
        {
            snake[i] = new Block((squaresize+(i*squaresize)) + (cols*squaresize)/2, (rows*squaresize)/2);
        }
        
        //initialize food
        food = new Block(0, 0);
      
        
        UserEventRepository uev = new UserEventRepository("mijn verzameling");
        uev.addAllArrowKeys();
        EventManager.getInstance().addUserEventListener(this, uev);
        
         t = new Timer();
        tmrGame mtt = new tmrGame();
        mtt.setCallback(this);
        t.scheduleAtFixedRate(mtt,0,timerinterval);
       
        
    }
    public void startXlet() 
    {
        scene.validate();
        scene.setVisible(true);
    }
    
    public void Update ()
    {
        System.out.println("update begin");
         {
        switch (direction) //move snake
            {
                case 0:
                    for (int i = snakelength - 1; i >= 1; i--) //reverse loop: set bodypart to location of bodypart before it
                    {
                        snake[i].x = snake[i - 1].x;
                        snake[i].y = snake[i - 1].y;
                    }
                    snake[0].y -= squaresize; //move snakehead in right direction

                    break;
                    case 1:
                    for (int i = snakelength - 1; i >= 1; i--)
                    {
                        snake[i].x = snake[i - 1].x;
                        snake[i].y = snake[i - 1].y;
                    }
                    snake[0].x += squaresize;

                    break;
                case 2:
                    for (int i = snakelength - 1; i >= 1; i--)
                    {
                        snake[i].x = snake[i - 1].x;
                        snake[i].y = snake[i - 1].y;
                    }
                    snake[0].y += squaresize;

                    break;
                case 3:
                    for (int i = snakelength - 1; i >= 1; i--)
                    {
                        snake[i].x = snake[i - 1].x;
                        snake[i].y = snake[i - 1].y;
                    }
                    snake[0].x -= squaresize;

                    break;
                
            }
            
            //check if snake is on itself -> game over
            for (int i = 1; i < snakelength; i++)
            {
                if (snake[0].x == snake[i].x && snake[0].y == snake[i].y)
                {
                    // snake is on it self
                }
            }

            //check if snakehead is on food
           if (snake[0].x == food.x && snake[0].y == food.y)
            {
                Spawnfood();
                score++;
                snakelength++;
                lblPoints.setTextContent("score: " + Integer.toString(score),HVisible.NORMAL_STATE);
                snake[snakelength - 1] = new Block(snake[snakelength - 2].x, snake[snakelength - 2].y);
                    //initialize next bodypart (location will be right in next updatecyce)

            }
            
            //loop snake when out of grid
            for (int i = 0; i < snakelength; i++)
            {
                if (snake[i].x < 0)
                {
                    snake[i].x = cols*squaresize - squaresize;
                }

                if (snake[i].y < 0)
                {
                    snake[i].y = rows*squaresize - squaresize;
                }

                if (snake[i].x >= cols*squaresize)
                {
                    snake[i].x = 0;
                }

                if (snake[i].y >= rows*squaresize)
                {
                    snake[i].y = 0;
                }
            }
          
            //update speed according to score
            
            }
         System.out.println("update einde");
    }       
    
    public void Paint()
    {
         System.out.println("paint begin");
        for (int i = 0; i < grid.length; i++)
            {
                //put snake on grid
                boolean showsnake = false;
                for (int j = 0; j < snakelength; j++)
		{
                    if(grid[i].x == snake[j].x && grid[i].y == snake[j].y)
                    {
                        showsnake = true;
                    }
		}
               
                if(showsnake) // snake
                {
                    grid[i].issnake = true;
                    lblGrid[i].setBackground(snakecolor);

                }
                 else if(food.x == grid[i].x && food.y == grid[i].y)
                {
                    grid[i].isfood = true;
                    lblGrid[i].setTextContent("X", HVisible.NORMAL_STATE);
                    lblGrid[i].setBackground(foodcolor);
                }
                else
                {
                    
                    lblGrid[i].setTextContent("", HVisible.NORMAL_STATE);
                    lblGrid[i].setBackground(gridcolor);
                }
            }
          System.out.println("paint einde");
    }
    
    public void Reset()
    {
       
    }
    
     public void callback()
    {
            
            //mechanism to control updatespeed
            if(counttointerval < intervalat) //wait for next update
            {
                counttointerval += timerinterval;
            }

            if (counttointerval >= intervalat && !holdtimer) //update now
            {
                Update();
                counttointerval = 0;
            }
            Paint(); // update picbox even if not updated
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
                    
                    if(direction != 2)
                    {
                    direction = 0;
                    holdkey = true;
                    }
                    break;
                    
                case HRcEvent.VK_DOWN:
                    if(direction != 0)
                    {
                    direction = 2;
                    holdkey = true;
                    }
                    break;
                
                case HRcEvent.VK_LEFT:
                    holdkey = true;
                    if(direction != 1)
                    {
                    direction = 3;
                    holdkey = true;
                    }
                    break;
                case HRcEvent.VK_RIGHT:
                    holdkey = true;
                    if(direction != 3)
                    {
                    direction = 1;
                    holdkey = true;
                    }
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
   
    public void Spawnfood()
    {
        boolean spawnable = false;
           while(!spawnable)
           {
               //put food somewhere random
               food.x = squaresize * (int)(Math.random()* cols);
               food.y = squaresize * (int)(Math.random()* rows);

               spawnable = true;
               for (int i = 0; i < snakelength; i++)
               {
                   //if food is on snake, do loop again
                   if (food.x == snake[i].x && food.y == snake[i].y)
                   {
                       spawnable = false;
                   }
               }
           }
    }
    
    
}
