
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


class Panel extends JPanel implements ActionListener{
    public static Image block = new ImageIcon("images/block.png").getImage();
    public static Image greenBlock = new ImageIcon("images/greenBlock.png").getImage();
    public static Image orangeBlock = new ImageIcon("images/orangeBlock.png").getImage();
    public static Image blueBlock = new ImageIcon("images/blueBlock.png").getImage();
    public static Image redBlock = new ImageIcon("images/redBlock.png").getImage();
    public static Image blueBlock2 = new ImageIcon("images/blueBlock2.png").getImage();
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public static Timer timer;
    Random random = new Random();
    
    public static int xVelocity=block.getWidth(null);
    public static int yVelocity=0;
    public static int x_fruit;
    public static int y_fruit;
    public static int x_redBlock=5000;
    public static int y_redBlock=5000;
    public static int x_blueBlock2=5000;
    public static int y_blueBlock2=5000;
    
    public static int speed=200;
    public static boolean gameOver=false;
    public static int score=0;
    public static int width=block.getWidth(null);
    public static int height = block.getHeight(null);
    public static int x=50;
    public static int y=60+height*4;
    static int c=0;
    public static int counter;
    public static int randomX;
    public static int randomY;
    public static int randomWidth;
    public static int randomHeight;
    
    Panel(){
        randomWidth = random.nextInt(16);
        randomHeight = random.nextInt(8);
        randomX=50+width*randomWidth;
        randomY=60+height*randomHeight;
        x_fruit= randomX;
        y_fruit = randomY;
        timer = new Timer(speed,this);
        timer.start();
        new Node(0,0);
        System.out.println(redBlock.getWidth(null));
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setPaint(new Color(150,200,200));
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.drawRect(50, 60,width*16 , height*8);
        graphics2D.setPaint(Color.BLACK);
        if(!gameOver){
            graphics2D.setFont(new Font("Normal",Font.BOLD,50));
            graphics2D.drawString("score \n"+score, 600, 50);
        }
        graphics2D.drawImage(orangeBlock,x_fruit, y_fruit, null);
        graphics2D.drawImage(redBlock,x_redBlock, y_redBlock, null);
        graphics2D.drawImage(blueBlock2,x_blueBlock2, y_blueBlock2, null);

        c=-1;
        for (Node i : nodes) {
            graphics2D.drawImage(i.image,i.x,i.y,null); 
            c++;
            if (c!=0){
            nodes.get(c).image=block;
            if (c==nodes.size()-1){
                nodes.get(c).image=blueBlock;
            }}
        }
        
        graphics2D.drawImage(nodes.get(0).image,nodes.get(0).x+5, nodes.get(0).y,null);
        graphics2D.drawImage(nodes.get(0).image,nodes.get(0).x-5, nodes.get(0).y,null);
        graphics2D.drawImage(nodes.get(0).image,nodes.get(0).x, nodes.get(0).y-5,null);
        graphics2D.drawImage(nodes.get(0).image,nodes.get(0).x, nodes.get(0).y+5,null);
        
        if(gameOver){
            graphics2D.drawImage(nodes.get(0).image,nodes.get(0).x, nodes.get(0).y,null);
            graphics2D.setFont(new Font("Normal",Font.BOLD,100));
            graphics2D.drawString("Game Over", 400, 300);
            graphics2D.drawString("score "+score, 450, 400);
        }
        
       repaint();
    }

    public void randomPlacement(){
        while (true){
            randomWidth = random.nextInt(16);
            randomHeight = random.nextInt(8);
            randomX=50+width*randomWidth;
            randomY=60+height*randomHeight;
            counter=1;
            for(Node r: nodes){
                if ((Math.abs(r.x-randomX)<width && Math.abs(r.y-randomY)<height)){
                    break;
                    }
                if(counter==nodes.size()){
                return;}
                counter++;
            }
        }
    }
    
    
    public void gameOverCheck(){
        counter=0;
        for(Node g: nodes){
            if (counter<=4){
            }
            else if (Math.abs(g.x-x)<50 && Math.abs(g.y-y)<50){
                gameOver = true;
                return;
            }
            counter++;
        }
        gameOver = false;
    }
    
    public static void setSpeed(int speed){
        timer.setDelay(speed);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!gameOver){
        if (Math.abs(x-x_fruit)<width && Math.abs(y-y_fruit)<height){
            randomPlacement();
            x_fruit= randomX;
            y_fruit = randomY;
            new Node(x,y);
            if(score%5==0){
                randomPlacement();
                x_redBlock= randomX;
                y_redBlock = randomY;
            }
            else{
                x_redBlock= 5000;
                y_redBlock = 5000;
            }
            
            if(score%10==0){
                randomPlacement();
                x_blueBlock2 = randomX;
                y_blueBlock2 = randomY;
            }
            else{
                x_blueBlock2= 5000;
                y_blueBlock2 = 5000;
            }
         }
        
        if (Math.abs(x-x_redBlock)<width && Math.abs(y-y_redBlock)<height){
            if(speed>50){
            speed-=50;}
            setSpeed(speed);
            x_redBlock= 5000;
            y_redBlock = 5000;
         }
        
        
        if (Math.abs(x-x_blueBlock2)<width && Math.abs(y-y_blueBlock2)<height){
            if(speed<200){
            speed+=50;
            setSpeed(speed);}
            x_blueBlock2= 5000;
            y_blueBlock2 = 5000;
         }

        nodes.get(0).prevX=x;
        nodes.get(0).prevY=y;
        
        x=x+xVelocity;
        y=y+yVelocity;
        
        nodes.get(0).x=x;
        nodes.get(0).y=y;
        
        gameOverCheck();
        if(gameOver)
        {
            xVelocity=0;
            yVelocity=0;
        }
        
// by setting the x and y position we can place the snake head at any place        
// we can also set the gameOver to true if we want to make the game over when the snake hit the borders
        if (nodes.get(0).x==50+width*16){
            x=50; 
//            gameOver=true;
        }
        if (nodes.get(0).x==50-width){
            x=50-width+width*16;
//            gameOver=true;

        }
        
        if (nodes.get(0).y==60+height*8){
            y=60; 
//            gameOver=true;

        }
        if (nodes.get(0).y==60-height){
            y=60-height+height*8;
//            gameOver=true;
        }
        
        
        for(int i=1; i<nodes.size(); i++)
        {
        nodes.get(i).prevX=nodes.get(i).x;
        nodes.get(i).prevY=nodes.get(i).y;
        
        nodes.get(i).x=nodes.get(i-1).prevX;
        nodes.get(i).y=nodes.get(i-1).prevY;
        }
        Frame.decide=true;
    
        }
    } 
}