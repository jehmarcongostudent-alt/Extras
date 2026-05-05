package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Boots extends Entity{
    
    public OBJ_Boots(GamePanel gp){
        super(gp);
        
        name = "Boots";
        down1 = setup("/objects/shoes1", gp.tileSize, gp.tileSize);
        
        //collision = true;
    }
}
