package vfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import rpggame.GamePanel;

public class SlashEffect {
    
    GamePanel gp;

    int x, y;
    int life;
    int maxLife = 6;

    BufferedImage[] frames;
    int frameIndex = 0;
    String direction;
    int size;

    public SlashEffect(GamePanel gp, int x, int y, BufferedImage[] frames, String direction, int size){
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.frames = frames;
        this.life = maxLife;
        this.direction = direction; 
         this.size = size;
    }

    public void update(){
        life--;

        // change frame over time
        frameIndex = (maxLife - life) / 2;

        if(frameIndex >= frames.length){
            frameIndex = frames.length - 1;
        }
    }

    public void draw(Graphics2D g2) {

        // 1. Find the exact pixel center of the player's current tile on screen
        int playerCenterX = x - gp.player.worldX + gp.player.screenX + (gp.tileSize / 2);
        int playerCenterY = y - gp.player.worldY + gp.player.screenY + (gp.tileSize / 2);

        if (frames != null && frameIndex < frames.length && frames[frameIndex] != null) {
            
            BufferedImage currentFrame = frames[frameIndex];

            // 2. 🔥 PIXEL CENTERING MATH: 
            // Read the real pixel size of the image and subtract half of it.
            // This guarantees the image center snaps directly to the player center!
            int drawX = playerCenterX - (currentFrame.getWidth() / 2);
            int drawY = playerCenterY - (currentFrame.getHeight() / 2);

            Graphics2D g2d = (Graphics2D) g2.create();

            double angle = 0;
            switch(direction){
                case "down":  angle = Math.toRadians(90); break;  
                case "left":  angle = Math.toRadians(180); break; 
                case "up":    angle = Math.toRadians(270); break; 
                case "right": angle = Math.toRadians(0); break;
            }

            // 3. Rotate exactly around the shared center point
            g2d.rotate(angle, playerCenterX, playerCenterY);

            // 4. Draw using the pixel-perfect coordinates
            g2d.drawImage(currentFrame, drawX, drawY, null);

            g2d.dispose();
        }
    }




    public boolean isAlive(){
        return life > 0;
    }
}