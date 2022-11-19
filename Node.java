
import java.awt.Image;
import javax.swing.ImageIcon;


public class Node{
    public Image image;
    public int x;
    public int y;
    public int prevX;
    public int prevY;
    public static int length=0;

    Node(int x, int y){
        if (Panel.nodes.isEmpty()){
         this.image = new ImageIcon("greenBlock.png").getImage();
        }
        else{
        this.image = new ImageIcon("block.png").getImage();
        }
        
        
        this.x=x;
        this.y=y;
        Panel.nodes.add(this);
//        if (Panel.nodes.size()>=3){
//            Panel.nodes.get(-1+Panel.nodes.indexOf(this)).image=Panel.block;
//            this.image = Panel.blueBlock;
//        }
        Panel.score++;
    }
    
}
