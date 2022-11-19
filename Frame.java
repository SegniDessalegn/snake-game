
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Frame extends JFrame implements KeyListener{
    
    Panel panel = new Panel();
    
    public Frame() {
        this.setPreferredSize(new Dimension(1500,800));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.add(panel);
        this.setTitle("Snake");
        this.setVisible(true);
        this.pack();
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        if (e.getKeyCode()==)

    }
    
    public static boolean decide=true;
    @Override
    public void keyPressed(KeyEvent e) {
        
if (e.getKeyCode()==37){
    if (Panel.yVelocity!=0 && decide){
        decide=false;
    Panel.yVelocity=0;
    Panel.xVelocity=-1*Panel.block.getWidth(null);}

}
if (e.getKeyCode()==38){
    if (Panel.xVelocity!=0 && decide){
        decide=false;
    Panel.xVelocity=0;
Panel.yVelocity=-1*Panel.block.getWidth(null);}
}
if (e.getKeyCode()==39 ){
    if(Panel.yVelocity!=0 && decide){
        decide=false;
    Panel.yVelocity=0;
Panel.xVelocity=Panel.block.getWidth(null);}
}
if (e.getKeyCode()==40 ){
    if(Panel.xVelocity!=0&& decide){
        decide=false;
    Panel.xVelocity=0;
Panel.yVelocity=Panel.block.getWidth(null);}
}
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("You released something");
    }
    
}
