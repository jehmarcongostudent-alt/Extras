package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Bow_Arrow extends Entity{

    GamePanel gp;

    public OBJ_Bow_Arrow(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bow and Arrow";
        value =1;
        down1 = setup("/objects/bow_arrow", gp.tileSize, gp.tileSize);
        image = setup("/objects/bow_arrow", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bow_empty", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){

        gp.playSE(2);
        gp.ui.addMessage("Arrow +"+value);
        entity.energy += value;
    }
}
