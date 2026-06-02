package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Door_Iron extends Entity{
    
    public static final String objName = "Iron Door";
    
    GamePanel gp;
    
    public OBJ_Door_Iron(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        name = objName;
        down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
        
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
        
        dialogues[0][0] = "It won't budge";
    }
    public void interact(){
        
        startDialogue(this,0);
    }
    
}
