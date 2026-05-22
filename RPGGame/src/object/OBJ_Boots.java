package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Boots extends Entity{
    
    public static final String objName = "Boots";
    
    GamePanel gp;
    
    public OBJ_Boots(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_pickupOnly;
        name = objName;
        value = 2;
        down1 = setup("/objects/shoes1", gp.tileSize, gp.tileSize);
        description = "currently a temporary item";
        price = 25;
        
        //collision = true;
    }
    public boolean use(Entity entity){
        
        gp.playSE(1);
        gp.ui.addMessage("Speed +" + value);
        gp.player.speed += value;
        return true;
    }
}
