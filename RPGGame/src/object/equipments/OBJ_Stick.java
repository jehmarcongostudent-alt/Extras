package object.equipments;

import java.awt.image.BufferedImage;
import rpggame.GamePanel;

public class OBJ_Stick extends Equipment{
    
    public static final String objName = "Stick";
    
    public OBJ_Stick(GamePanel gp){
        super(gp);
        
        type = type_sword;
        name = objName;
        down1 = setup("/objects/stick", gp.tileSize, gp.tileSize);
        attackDown0 = down1;
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        weaponPivotDistance = 0;
        description = "[" + name + "]\nJust a stick";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
        attackDuration = motion1_duration + motion2_duration;
        weaponArmLength = 0;
        
        weaponGripX = 4  * (gp.tileSize / 16);
        weaponGripY = 13  * (gp.tileSize / 16);
        
        //custome slashsize
        this.slashSize = 2;
        slashFrames = new BufferedImage[3];
        slashFrames[0] = setup("/vfx/slash1", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[1] = setup("/vfx/slash2", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[2] = setup("/vfx/slash3", gp.tileSize*slashSize, gp.tileSize*slashSize);
        
    }
}
