package object.equipments;

import entity.Entity;
import java.awt.image.BufferedImage;
import rpggame.GamePanel;


public class OBJ_Axe_Rusty extends Equipment{
    
    public static final String objName = "old used up Axe";
    
    public OBJ_Axe_Rusty(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = objName;
        down1 = setup("/objects/rustyaxe", gp.tileSize, gp.tileSize);
        attackDown0 = down1;
        spriteRotation = Math.toRadians(-45);
        weaponGripX = 3 * (gp.tileSize / 16);
        weaponGripY = 14 * (gp.tileSize / 16);
        attackValue = 1;
        attackArea.width = 20;
        attackArea.height = 20;
        description = "[" + name + "]\ncuts but painful to use";
        price = 5;
        motion1_duration = 6;
        motion2_duration = 25;
        
        //Slash animation
        this.slashSize = 3; 
        slashFrames = new BufferedImage[3];
        slashFrames[0] = setup("/vfx/slash1", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[1] = setup("/vfx/slash2", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[2] = setup("/vfx/slash3", gp.tileSize*slashSize, gp.tileSize*slashSize);
    }
}
