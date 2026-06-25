package object.equipments;

import entity.Entity;
import java.awt.image.BufferedImage;
import rpggame.GamePanel;

public class Equipment extends Entity{
    
    public Equipment(GamePanel gp) {
        super(gp);
        

        spriteRotation = Math.toRadians(-45);   // rotates all image icons when atacking
        weaponGripX = 3  * (gp.tileSize / 16);  //weapons handle X coord
        weaponGripY = 14  * (gp.tileSize / 16); //weapons handle Y coords
        motion1_duration = 5;   //windup time
        motion2_duration = 25;  //afterStrikeTime
        
        weaponArcDistance = gp.tileSize;    // distance of weapon from player
        
        //Slash animation
        this.slashSize = 3; 
        slashFrames = new BufferedImage[3];
        slashFrames[0] = setup("/vfx/slash1", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[1] = setup("/vfx/slash2", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[2] = setup("/vfx/slash3", gp.tileSize*slashSize, gp.tileSize*slashSize);
    }
    
}
