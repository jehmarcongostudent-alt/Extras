package object.equipments;

import entity.Entity;
import java.awt.image.BufferedImage;
import rpggame.GamePanel;


public class OBJ_Sushiblade extends Equipment{
    
    public static final String objName = "Sushiblade";
    
    public OBJ_Sushiblade(GamePanel gp){
        super(gp);
        
        type = type_sword;
        name = objName;
        down1 = setup("/objects/sushiblade", gp.tileSize*3, gp.tileSize*3);
        displayImage = setup("/objects/sushiblade", gp.tileSize, gp.tileSize);
        attackValue = 6;
        attackArea.width = 100;
        attackArea.height = 100;
        description = "[" + name + "]\nA weapon of an ancient \nsushi warrior";
        price = 2500;
        knockBackPower = 9;
        motion1_duration = 10;
        motion2_duration = 20;
        
        weaponGripX = 6  * (gp.tileSize / 16);
        weaponGripY = ((16*2)+9)  * (gp.tileSize / 16);
        
        //custome slashsize
        this.slashSize = 6;
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

//package object.equipments;
//
//import entity.Entity;
//import java.awt.image.BufferedImage;
//import rpggame.GamePanel;
//
//
//public class OBJ_Sushiblade extends Equipment{
//    
//    public static final String objName = "Divine Axe Rhitta";
//    
//    public OBJ_Sushiblade(GamePanel gp){
//        super(gp);
//        
//        type = type_axe;
//        name = objName;
//        down1 = setup("/objects/sushiblade", gp.tileSize*1, gp.tileSize*(int)2);
//        displayImage = setup("/objects/sushiblade", gp.tileSize, gp.tileSize);
//        attackValue = 6;
//        attackArea.width = 100;
//        attackArea.height = 100;
//        description = "[" + name + "]\nA weapon of an ancient sushi warrior";
//        price = 2500;
//        knockBackPower = 9;
//        motion1_duration = 10;
//        motion2_duration = 20;
//        
//        weaponGripX = 4  * (gp.tileSize / 16);
//        weaponGripY = ((16*1)+12)  * (gp.tileSize / 16);
//        
//        //custome slashsize
//        this.slashSize = 6;
//        slashFrames = new BufferedImage[3];
//        slashFrames[0] = setup("/vfx/slash1", gp.tileSize*slashSize, gp.tileSize*slashSize);
//        slashFrames[1] = setup("/vfx/slash2", gp.tileSize*slashSize, gp.tileSize*slashSize);
//        slashFrames[2] = setup("/vfx/slash3", gp.tileSize*slashSize, gp.tileSize*slashSize);
//        
//        // Held weapon display (idle, non-attacking)
//        showHeld = true;
//        heldOffsetX = 0;
//        heldOffsetY = 0;
//        heldRotation = 225.0;
//        heldFlipHorizontal = false;
//        
//        heldAnchorX = 20;  // grip sits 10px to the right of player when facing down/right
//        heldAnchorY = 0;   // and 5px below
//    }
//}

