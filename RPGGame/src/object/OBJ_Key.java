package object;

import entity.Entity;
import rpggame.GamePanel;


public class OBJ_Key extends Entity{
    
    
    public OBJ_Key(GamePanel gp){
        super(gp);
        
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]It opens a door.";
    }
}
