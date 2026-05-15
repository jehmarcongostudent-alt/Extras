package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Torch extends Entity{
    
    public OBJ_Torch(GamePanel gp){
        super(gp);
        
        type = type_light;
        name = "Lantern";
        down1 = setup("/objects/torch", gp.tileSize, gp.tileSize);
        description = "[Lantern]\nIlluminates your \nsurroundings a bit.";
        price = 250;
        lightRadius = 80;
    }
}
