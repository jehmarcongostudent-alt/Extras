package object.equipments;

import entity.Entity;
import java.awt.image.BufferedImage;
import rpggame.GamePanel;


public class OBJ_Axe_Divine_Rhitta extends Equipment{
    
    public static final String objName = "Divine Axe Rhitta";
    
    public OBJ_Axe_Divine_Rhitta(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = objName;
        down1 = setup("/objects/legendaryitem", gp.tileSize*4, gp.tileSize*4);
        displayImage = setup("/objects/legendaryitem_icon", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 150;
        attackArea.height = 150;
        description = "[" + name + "]\nA weapon of legends";
        price = 100;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
        
        weaponGripX = 9  * (gp.tileSize / 16);
        weaponGripY = ((16*3)+5)  * (gp.tileSize / 16);
        
        //custome slashsize
        this.slashSize = 9;
        slashFrames = new BufferedImage[3];
        slashFrames[0] = setup("/vfx/slash1", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[1] = setup("/vfx/slash2", gp.tileSize*slashSize, gp.tileSize*slashSize);
        slashFrames[2] = setup("/vfx/slash3", gp.tileSize*slashSize, gp.tileSize*slashSize);
        
        // Held weapon display (idle, non-attacking)
        showHeld = true;
        heldOffsetX = 0;
        heldOffsetY = 0;
        heldRotation = 225.0;
        heldFlipHorizontal = false;
        
        heldAnchorX = 20;  // grip sits 10px to the right of player when facing down/right
        heldAnchorY = 0;   // and 5px below
    }
}
