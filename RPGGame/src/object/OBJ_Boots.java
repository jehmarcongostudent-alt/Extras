package object;

import java.io.IOException;
import rpggame.GamePanel;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
    
    GamePanel gp;
    
    public OBJ_Boots(GamePanel gp){
        
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shoes1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
