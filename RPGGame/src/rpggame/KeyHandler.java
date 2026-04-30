package rpggame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
    //DEBUG
    boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e){   //have to add this method when implementing keyListener
    }
    
    @Override
    public void keyPressed(KeyEvent e){ //have to add this method when implementing keyListener
        
        int code = e.getKeyCode();  //returns the the key that is pressed
        
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            
            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                    gp.ui.commandNum --;
                    if(gp.ui.commandNum <0){
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum ++;
                    if(gp.ui.commandNum >2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        //add later
                    }
                    if(gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }
            
            //CHARACTER SELECTION
            else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                    gp.ui.commandNum --;
                    if(gp.ui.commandNum <0){
                        gp.ui.commandNum = 4;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum ++;
                    if(gp.ui.commandNum >4){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                    if(gp.ui.commandNum == 0){
                        System.out.println("Do some archer specific stuuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1){
                        System.out.println("Do some warroior specific stuuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2){
                        System.out.println("Do some mage specific stuuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3){
                        System.out.println("Do some tank specific stuuff");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 4){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
            
        }
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
           
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_SPACE){
                spacePressed = true;
            }

            //DEBUG
            if(code == KeyEvent.VK_T){
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true){
                    checkDrawTime = false;
                }
            }
            
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_SPACE){
                gp.gameState = gp.playState;
            }
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e){    //have to add this method when implementing keyListener
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
