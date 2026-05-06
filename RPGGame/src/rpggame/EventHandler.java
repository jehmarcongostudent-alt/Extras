package rpggame;

import java.awt.Rectangle;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(GamePanel gp){
        this.gp = gp;
        
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        
        
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
            if(hit(37,43,"right") == true){damagePit(37,43,gp.dialogueState); }   //x, y, facing posittion
            if(hit(13,2,"right") == true){damagePit(13,2,gp.dialogueState); }
            if(hit(1,15,"any") == true){damagePit(13,2,gp.dialogueState); }
            if(hit(13,3,"any") == true){healingPool(gp.dialogueState); }
            if(hit(16,4,"any") == true){teleport(gp.dialogueState); }
            
        }
        
    }
    //OBJECT THAT DETECTS EVENT COLLISION
    public boolean hit(int col, int row, String reqDirection){
        
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
        
        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        
        return hit;
        
    }
    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize*46;
        gp.player.worldY = gp.tileSize*46;
    }
    public void damagePit(int col, int row, int gameState){
        
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialogue = "You tripped and fell";
        gp.player.life -= 1;
        canTouchEvent = false;
    }
    public void healingPool(int gameState){
        
        if(gp.keyH.enterPressed == true || gp.keyH.spacePressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            gp.ui.currentDialogue = "You drink the purified water\nYou're life has been recovered";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();    //respawns monsters
        }
        
        
    }
    
}
