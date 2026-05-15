package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_BootE extends Entity{
    
    GamePanel gp;
    
    public OBJ_BootE(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_boots;
        name = "Boots";
        speedValue = 3;
        down1 = setup("/objects/shoes1", gp.tileSize, gp.tileSize);
        description = "[Old Boots]\ncurrently in\ntesting phase.";
        price = 25;
        
        //collision = true;
    }
    public boolean use(Entity entity){
        
//        gp.playSE(1);
//        gp.ui.addMessage("Speed +" + value);
//        gp.player.speed += value;
        return true;
    }
}
