package rpggame;

import entity.PlayerDummy;
import java.awt.Graphics2D;
import monster.MON_CrystalGolem_Boss;
import object.OBJ_Door_Iron;

public class CutsceneManager {
    
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    
    //Scene Number
    public final int NA = 0;
    public final int crystalGolem = 1;
    
    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        switch(sceneNum){
            case crystalGolem: scene_crystalGolem(); break;
        }
    }
    public void scene_crystalGolem(){
        
        if(scenePhase == 0){
            
            gp.bossBattleOn = true;
            
            //Shut the iron door
            for(int i = 0; i < gp.obj[1].length; i++){
                
                if(gp.obj[gp.currentMap][i] == null){
                    
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*34;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*46;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            //Search a vacant slot for the dummy   
            for(int i = 0; i < gp.npc[1].length; i++){
                
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            
            gp.player.drawing = false;
            
            scenePhase++;
        }
        if(scenePhase == 1){
            
            gp.player.worldY -= 2;
            
            if(gp.player.worldY < gp.tileSize*31){
                scenePhase++;
            }
        }
        if(scenePhase == 2){
            
            //searchh the boss
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name == MON_CrystalGolem_Boss.monName){
                
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){
            
            //The boss speaks
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 4){
            
            //Return to the player
            
            //Search the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    //Restore the player position
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    //Delete the dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            
            //Start deawing the player
            gp.player.drawing = true;
            
            //Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            
            //Chane the music
            gp.stopMusic();
            gp.playMusic(22);
        }
    }
}
