package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Door extends Entity{
    
    public static final String objName = "Door";
    
    GamePanel gp;
    
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        name = objName;
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        
        collision = true;
        
        //sets the Doors collision
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You need a key to open this";
    }
    public void interact(){
        
        startDialogue(this,0);
    }
    
}
