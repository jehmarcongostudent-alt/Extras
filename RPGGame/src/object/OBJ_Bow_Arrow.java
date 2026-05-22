package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Bow_Arrow extends Entity{
    
    public static final String objName = "Bow and Arrow";

    GamePanel gp;

    public OBJ_Bow_Arrow(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value =1;
        down1 = setup("/objects/bow_arrow", gp.tileSize, gp.tileSize);
        image = setup("/objects/bow_arrow", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bow_empty", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity){

        gp.playSE(2);
        gp.ui.addMessage("Arrow +"+value);
        entity.energy += value;
        return true;
    }
}
