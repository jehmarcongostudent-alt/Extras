
package rpggame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class UtilityTool {
    
    //scales images to make file run faster for somereason that idk
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); //buffered image constructore (int width, int height, int imageType)
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        
        return scaledImage;
    }
}
