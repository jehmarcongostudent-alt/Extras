/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

import entity.NPC_shopKeeper;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        gp.obj[1] = new OBJ_Boots(gp);
        gp.obj[1].worldX = gp.tileSize*15;
        gp.obj[1].worldY = gp.tileSize*3;
        
        gp.obj[2] = new OBJ_Boots(gp);
        gp.obj[2].worldX = gp.tileSize*16;
        gp.obj[2].worldY = gp.tileSize*3;
        
    }
    public void setNPC(){
        
        gp.npc[0] = new NPC_shopKeeper(gp);
        gp.npc[0].worldX = gp.tileSize*14;
        gp.npc[0].worldY = gp.tileSize*11;
    }
    public void setMonster(){
        
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*37;
        gp.monster[i].worldY =gp.tileSize*43;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*1;
        gp.monster[i].worldY =gp.tileSize*15;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*4;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*5;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*6;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*6;
        gp.monster[i].worldY =gp.tileSize*6;
    }
}
