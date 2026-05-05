package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Door extends Entity{
    
    public OBJ_Door(GamePanel gp){
        super(gp);
        
        name = "Door";
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
    
}
