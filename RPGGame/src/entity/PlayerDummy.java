package entity;

import rpggame.GamePanel;

public class PlayerDummy extends Entity{
    
    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);
        
        name = npcName;
        getImage();
        
    }
    String avatar = gp.player.avatar;
    public void getImage(){
        up0 = setup("/player/"+avatar+"/"+avatar+"_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/player/"+avatar+"/"+avatar+"_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/"+avatar+"/"+avatar+"_up2", gp.tileSize, gp.tileSize);
        down0 = setup("/player/"+avatar+"/"+avatar+"_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/player/"+avatar+"/"+avatar+"_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/"+avatar+"/"+avatar+"_down2", gp.tileSize, gp.tileSize);
        left0 = setup("/player/"+avatar+"/"+avatar+"_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/player/"+avatar+"/"+avatar+"_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/"+avatar+"/"+avatar+"_left2", gp.tileSize, gp.tileSize);
        right0 = setup("/player/"+avatar+"/"+avatar+"_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/player/"+avatar+"/"+avatar+"_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/"+avatar+"/"+avatar+"_right2", gp.tileSize, gp.tileSize);
    }
    
}
