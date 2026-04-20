/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

import object.*;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        gp.obj[0] = new OBJ_Door();
        gp.obj[0].worldX = 24 * gp.tileSize;
        gp.obj[0].worldY = 25 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 4 * gp.tileSize;
        gp.obj[1].worldY = 6 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 26 * gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 4 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Key();
        gp.obj[4].worldX = 7 * gp.tileSize;
        gp.obj[4].worldY = 6 * gp.tileSize;
    }
}
