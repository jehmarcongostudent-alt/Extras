package rpggame;

import data.Progress;
import entity.Entity;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol ,tempRow;
    
    public EventHandler(GamePanel gp){
        this.gp = gp;
        
        eventMaster = new Entity(gp);
        
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 17;
            eventRect[map][col][row].y = 17;
            eventRect[map][col][row].width = 14;
            eventRect[map][col][row].height = 14;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
        
        setDialogue();
    }
    public void setDialogue(){
        
        eventMaster.dialogues[0][0] = "You tripped and fell";
        
        eventMaster.dialogues[1][0] = "You drink the purified water\nYou're life has been recovered"
                    + "\n(The progess has been saved)";
        eventMaster.dialogues[1][1] = "Damn,this so good water";
    }
    public void checkEvent(){
        
        //check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);    //Math.abs returns the absolute values of "gp.player.worldX - previousEventX" calculation
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);  //Math.max picks the greater value
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        
        if(canTouchEvent == true){
            //ALL EVENTS HERE
            if(hit(0,37,43,"right") == true){damagePit(gp.dialogueState); }   //x, y, facing posittion
            else if(hit(0,13,2,"right") == true){damagePit(gp.dialogueState); }
            else if(hit(0,1,15,"any") == true){damagePit(gp.dialogueState); }
            else if(hit(0,13,3,"any") == true){healingPool(gp.dialogueState); }
            else if(hit(0,21,24,"any") == true){teleport(1,26,27,gp.indoor); }    //to shop
            else if(hit(1,26,27,"any") == true){teleport(0,21,24,gp.outside); }    // to outside
            else if(hit(1,26,24,"up") == true){speak(gp.npc[1][0]);}
            else if(hit(0,31,47,"any") == true){teleport(2,22,3,gp.cave); }     //to cave
            else if(hit(2,22,3,"any") == true){teleport(0,31,47,gp.outside); }     // to outside
            else if(hit(2,30,48,"any") == true){teleport(3,3,3,gp.cave); }     //to cave2
            else if(hit(3,3,3,"any") == true){teleport(2,30,48,gp.cave); }     // to back cave1
            else if(hit(3,22,3,"any") == true){teleport(2,30,48,gp.cave); }
            else if(hit(3,34,45,"any") == true){crystalGolem(); }   //BOSS cutscene
            
        }
        
    }
    //OBJECT THAT DETECTS EVENT COLLISION
    public boolean hit(int map, int col, int row, String reqDirection){
        
        boolean hit = false;
        
        if(map == gp.currentMap){ 
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        
        
        return hit;
        
    }
    public void teleport(int map, int col, int row, int area){
        
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }
    public void damagePit(int gameState){
        
        gp.gameState = gameState;
        gp.playSE(6);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        canTouchEvent = false;
    }
    public void healingPool(int gameState){
        
        if(gp.keyH.enterPressed == true || gp.keyH.spacePressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.energy = gp.player.maxEnergy;
            gp.aSetter.setMonster();    //respawns monsters
            gp.saveload.save();
        }
    }
    public void speak(Entity entity){
        
        if(gp.keyH.enterPressed == true || gp.keyH.spacePressed){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public void crystalGolem(){
        
        if(gp.bossBattleOn == false && Progress.crystalGolemDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.crystalGolem;
        }
    }
    
}
