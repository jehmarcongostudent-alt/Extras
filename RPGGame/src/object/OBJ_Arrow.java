package object;

import entity.Entity;
import entity.Projectile;
import rpggame.GamePanel;

public class OBJ_Arrow extends Projectile{
    
    GamePanel gp;
    
    public OBJ_Arrow(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        name = "Arrow";
        speed = 15;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/projectile/arrow_up0", gp.tileSize,gp.tileSize);
        up2 = setup("/projectile/arrow_up1", gp.tileSize,gp.tileSize);
        down1 = setup("/projectile/arrow_down0", gp.tileSize,gp.tileSize);
        down2 = setup("/projectile/arrow_down1", gp.tileSize,gp.tileSize);
        left1 = setup("/projectile/arrow_left0", gp.tileSize,gp.tileSize);
        left2 = setup("/projectile/arrow_left1", gp.tileSize,gp.tileSize);
        right1 = setup("/projectile/arrow_right0", gp.tileSize,gp.tileSize);
        right2 = setup("/projectile/arrow_right1", gp.tileSize,gp.tileSize);
    }
    public boolean haveResource(Entity user){
        
        boolean haveResource = false;
        if(user.energy >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.energy -= useCost;
    }
}
