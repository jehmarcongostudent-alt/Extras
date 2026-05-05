package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Chest extends Entity{
    
    public OBJ_Chest(GamePanel gp){
        super(gp);
        
        name = "Chest";
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
       
        collision = true;
    }
}
