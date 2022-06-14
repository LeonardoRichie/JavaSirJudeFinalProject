import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.*;



public class Gameplay extends JPanel implements KeyListener, ActionListener {
    //stating variable
    private boolean play = false; //stop gameplay
    private boolean gameplayOn = false; //stop paddle from moving
    private int score = 0; //starting score
    private boolean frontPage = true;

    private int totalworldBricks;
    private int totalBricks = totalworldBricks; //total bricks in game
    private Timer timer;
    private int delay = 8;

    private int playerX = 310; //player position X
    private int playerY = 550;

    private int paddleSize = 100;
    private String nameLength = "normal";
    private int worldSpeedX = -1; //speed for setting
    private int worldSpeedY = -2; //speed for setting
    private int ballposX; //first position x for ball
    private int ballposY; //first position y for ball
    private int ballXdir; //x direction for ball
    private int ballYdir; //y direction for ball

    private int ball2posX; //first position x for ball
    private int ball2posY; //first position y for ball
    private int ball2Xdir; //x direction for ball
    private int ball2Ydir; //y direction for ball

    private int ball3posX; //first position x for ball
    private int ball3posY; //first position y for ball
    private int ball3Xdir; //x direction for ball
    private int ball3Ydir; //y direction for ball

    private int worldNum = 1; //world number for indexing
    private int page = 1; //indicates which page

    private int numberofBall = 1;

    private MapGenerator map; //create class for map

    public Gameplay(){
        map = new MapGenerator(10, 10, 9);
        worldNum = 9;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void resetGame(){
        //method to reset the stats when the game ends
        gameplayOn = true; //create paddle from moving again
        ballposX = 350;
        ballposY = 450;
        ballXdir = worldSpeedX;
        ballYdir = worldSpeedY;

        ball2posX = 350; //first position x for ball
        ball2posY = 10; //first position y for ball
        ball2Xdir = worldSpeedX; //x direction for ball
        ball2Ydir = worldSpeedY; //y direction for ball

        ball3posX = 450; //first position x for ball
        ball3posY = 10; //first position y for ball
        ball3Xdir = worldSpeedX; //x direction for ball
        ball3Ydir = worldSpeedY; //y direction for ball
        totalBricks = totalworldBricks;

        playerX = 310;
        playerY = 550;
        score = 0;
    }

    public void paint(Graphics g){
        
        //bg
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);//location & size
        
        //draw map
        map.draw((Graphics2D)g);

        

        if (page == 1){
            frontPage = true;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Welcome to brick breaker" , 10, 230);
    
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press a number in keyboard 1-9 for different levels" , 60, 265);
            g.drawString("Press right arrow to go to settings" , 60, 295);
            }
            
        if (page == 2){
            frontPage = false;
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 60));
            g.drawString("Settings", 10, 50);

            
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("--------------------------------------", 10, 90);
            g.drawString("How to play", 10, 120);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("All command in the game are using keyboard keys", 10, 150);
            g.drawString("Press any number from 1-9 to pick levels", 10, 170);
            g.drawString("ESC - Back to main menu", 10, 190);
            g.drawString("IN LEVEL COMMAND:", 10, 230);
            g.drawString("Press any number from 1-9 to change levels", 10, 250);
            g.drawString("Press ENTER to reset the game when loses", 10, 270);
            g.drawString("Paddle can be moved in 4 direction (UP, DOWN, LEFT, RIGHT)", 10, 290);
            g.drawString("Use Keyboard ARROW keys to move paddle", 10, 310);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("--------------------------------------", 10, 340);
            g.drawString("Customization", 10, 370);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Use keyboard keys to customize", 10, 400);
            g.drawString("Ball Speed: " +-worldSpeedX + "x", 10, 420);
            g.drawString("Width of Paddle: " + nameLength, 10, 440);
            g.drawString("Number of Ball: " + numberofBall, 10, 460);
            g.drawString("BallSpeed (G KEY = 1X, F KEY = 2X)", 10, 500);
            g.drawString("Paddle Width (N KEY = NORMAL, S KEY = SHORT, E KEY = EXTREME)" + numberofBall, 10, 520);
            g.drawString("Number of Ball (1 KEY = 1, 2 KEY = 2, 3 KEY = 3)", 10, 540);
        }

        //Welcome to brick breaker page on top left screen
        if (play == false && page == 0){

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 17));
        g.drawString("Press a number in keyboard 1-9 for different levels" , 10, 30);
        }

        //level name
        //the ninth worldnum is used as a cover
        //other than ninth worldnum level will appear
        if (worldNum!=9){
            //scores on top right screen
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("" + score , 590, 30);

            //world number
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("level: " + (worldNum+1) , 420, 30);
            
        }
        
        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592); //left border
        g.fillRect(0, 0, 692,3); //top border
        g.fillRect(681, 0, 3, 592); //right border

        //paddle
        if(page == 0){
        g.setColor(Color.green);
        g.fillRect(playerX, playerY, paddleSize, 8);

        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);//oval shaped

        if (numberofBall == 2){
        g.setColor(Color.green);
        g.fillOval(ball2posX, ball2posY, 20, 20);
        }
        if (numberofBall == 3){
        g.setColor(Color.green);
        g.fillOval(ball2posX, ball2posY, 20, 20);

        g.setColor(Color.blue);
        g.fillOval(ball3posX, ball3posY, 20, 20);
        }
        }

        /*if(ballposY > 570){
            //when ball fall from the screen
            ballStock -=1;
        }*/


            if (numberofBall ==1 && ballposY > 570 ||numberofBall ==2 &&  ballposY > 570 && ball2posY > 570 ||
            numberofBall ==3 && ballposY > 570 && ball2posY > 570 && ball3posY > 570){
                gameplayOn = false; //stops the paddle from moving when loses or finished
                play = false;

                ballXdir = 0;
                ballYdir = 0;

                ball2Xdir = 0;
                ball2Ydir = 0;

                ball3Xdir = 0;
                ball3Ydir = 0;
                
                //display text when loses the game
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Game Over, Scores: " + score , 350, 500);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart " , 350, 530);
            }
            
        g.dispose(); //remove component from the game
    }

    

    //17:50
    @Override
        public void actionPerformed(ActionEvent e){
            timer.start();

            //when the game is still running
            if (play == true){
                if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ballYdir = -ballYdir;
                }
                if (new Rectangle(ball2posX, ball2posY, 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ball2Ydir = -ball2Ydir;
                }
                if (new Rectangle(ball3posX, ball3posY, 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ball3Ydir = -ball3Ydir;
                }
                A: for (int i = 0; i<10; i++){
                    for (int j = 0; j<10; j++){
                        //use loop  to create image rect when ball collide with bricks
                        if(map.map[worldNum][i][j]>0){
                            int brickX = j*map.brickWidth + 80;
                            int brickY = i*map.brickHeight + 50;
                            int brickWidth = map.brickWidth;
                            int brickHeight = map.brickHeight;

                            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                            Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                            Rectangle ball2Rect = new Rectangle(ball2posX, ball2posY, 20, 20);
                            Rectangle ball3Rect = new Rectangle(ball3posX, ball3posY, 20, 20);
                            Rectangle brickRect = rect;


                            //when ball collide bricks
                            if (ballRect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ballposX + 19<= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                    ballXdir = -ballXdir;
                                }//from the left and right
                                else{
                                    ballYdir = -ballYdir;
                                }//top and bottom
                                break A; //break the loop
                            }
                            if (ball2Rect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ball2posX + 19<= brickRect.x || ball2posX + 1 >= brickRect.x + brickRect.width){
                                    ball2Xdir = -ball2Xdir;
                                }//from the left and right
                                else{
                                    ball2Ydir = -ball2Ydir;
                                }//top and bottom
                                break A; //break the loop
                            }

                            if (ball3Rect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ball3posX + 19<= brickRect.x || ball3posX + 1 >= brickRect.x + brickRect.width){
                                    ball3Xdir = -ball3Xdir;
                                }//from the left and right
                                else{
                                    ball3Ydir = -ball3Ydir;
                                }//top and bottom
                                break A; //break the loop
                            }
                        }
                    }
                }
                //when theres no brick the game finished
                if (totalBricks ==0){
                resetGame();
                page = 1;
                gameplayOn = false;
                map = new MapGenerator(10, 10, 9);
                worldNum = 9;
                play = false;
                }

                ballposX += ballXdir;
                ballposY += ballYdir;

                if (numberofBall ==2){
                ball2posX += ball2Xdir;
                ball2posY += ball2Ydir;
                }

                if (numberofBall ==3){
                    ball2posX += ball2Xdir;
                    ball2posY += ball2Ydir;
                    ball3posX += ball3Xdir;
                    ball3posY += ball3Ydir;
                    }
                //add the ball position for moving

                //bouncing
                if (ballposX<0){
                    ballXdir = -ballXdir;
                }
                if (ballposY<0){
                    ballYdir = -ballYdir;
                }
                if (ballposX>670){
                    ballXdir = -ballXdir;
                }

                    //second ball
                if (ball2posX<0){
                    ball2Xdir = -ball2Xdir;
                }
                if (ball2posY<0){
                    ball2Ydir = -ball2Ydir;
                }
                if (ball2posX>670){
                    ball2Xdir = -ball2Xdir;
                }
                    //third ball
                if (ball3posX<0){
                    ball3Xdir = -ball3Xdir;
                }
                if (ball3posY<0){
                    ball3Ydir = -ball3Ydir;
                }
                if (ball3posX>670){
                    ball3Xdir = -ball3Xdir;
                }
            }
            repaint(); //redraw all components inside the game
        }

        @Override
        public void keyTyped(KeyEvent e){}
        
        @Override
        public void keyReleased(KeyEvent e){}

        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()== KeyEvent.VK_RIGHT){
                if (page == 1){
                    frontPage =false;
                    page = 2;
                }
                //prevent the paddle from going out of screen
                if(playerX >= 600){
                    playerX = 600;
                }
                else{
                    moveRight();
                }
            }
            if(e.getKeyCode()== KeyEvent.VK_LEFT){
                if (page == 2){
                    frontPage = true;
                    page = 1;
                }
                //prevent the paddle from going out of screen
                if(playerX < 10){
                    playerX = 10;
                }
                else{
                    moveLeft();
                }     
            }

            if(e.getKeyCode()== KeyEvent.VK_UP){
                //prevent the paddle from going out of screen
                if(playerY <= 475){
                    playerY = 475;
                }
                else{
                    moveUp();
                } 
            }

            if(e.getKeyCode()== KeyEvent.VK_DOWN){
                //prevent the paddle from going out of screen
                if(playerY >= 550){
                    playerY = 550;
                }
                else{
                    moveDown();
                }  
            }

            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                //reset the game
                if(play == false){
                    resetGame();
                    map = new MapGenerator(10, 10, worldNum);
                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                resetGame();
                page = 1;
                gameplayOn = false;
                map = new MapGenerator(10, 10, 9);
                worldNum = 9;
                play = false;
                gameplayOn = false;
                score = 0; //starting score
            }

            if(e.getKeyCode()==KeyEvent.VK_G){
                if (page == 2){
                    worldSpeedX = -1;
                    worldSpeedY = -2;
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_F){
                if (page == 2){
                    worldSpeedX = -2;
                    worldSpeedY = -4;
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_N){
                if (page == 2){
                    paddleSize = 100;
                    nameLength = "Normal";
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_S){
                if (page == 2){
                    paddleSize = 50;
                    nameLength = "Short";
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_E){
                if (page == 2){
                    paddleSize = 25;
                    nameLength = "Extreme";
                }
            }
            
            if(e.getKeyCode()==KeyEvent.VK_1){
                if (page == 2){
                    numberofBall = 1;
                }
                //when press number 1 of the keyboard, it display level 1
                if(play == false && frontPage == true){
                    totalworldBricks = 100;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 0);
                    worldNum = 0;

                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_2){
                if (page == 2){
                    numberofBall =2;
                }
                //when press number 2 of the keyboard, it display level 2
                if(play == false && frontPage == true){
                    totalworldBricks = 40;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 1);
                    worldNum = 1;

                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_3){
                if (page == 2){
                    numberofBall =3;
                }
                //when press number 3 of the keyboard, it display level 3
                if(play == false && frontPage == true){
                    totalworldBricks = 20;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 2);
                    worldNum = 2;

                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_4){
                //when press number 4 of the keyboard, it display level 4
                if(play == false && frontPage == true){
                    totalworldBricks = 20;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 3);
                    worldNum = 3;

                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_5){
                //when press number 5 of the keyboard, it display level 5
                if(play == false && frontPage == true){
                    totalworldBricks = 60;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 4);
                    worldNum = 4;
                    repaint();
                }   
            }

            if(e.getKeyCode()==KeyEvent.VK_6){
                //when press number 6 of the keyboard, it display level 6
                if(play == false && page == 1){
                    totalworldBricks = 28;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 5);
                    worldNum = 5;
                    repaint();
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_7){
                //when press number 7 of the keyboard, it display level 7
                if(play == false && frontPage == true){
                    totalworldBricks = 52;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 6);
                    worldNum = 6;
                    repaint();
                }
            }  
            
            if(e.getKeyCode()==KeyEvent.VK_8){
                //when press number 8 of the keyboard, it display level 8
                if(play == false && frontPage == true){
                    totalworldBricks = 32;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 7);
                    worldNum = 7;

                    repaint();
                }
            
            
            }   

            if(e.getKeyCode()==KeyEvent.VK_9){
                //when press number 9 of the keyboard, it display level 9
                if(play == false && frontPage == true){
                    totalworldBricks = 58;
                    page = 0;
                    resetGame();
                    map = new MapGenerator(10, 10, 8);
                    worldNum = 8;

                    repaint();
                }
            } 
        
        }
        public void moveRight(){
            //paddle movements to the right
            if (worldNum!=9 && gameplayOn ==true){
            play = true;
            playerX += 20;
            }
        }

        public void moveLeft(){
            //paddle movements to the left
            if (worldNum!=9 && gameplayOn ==true){
            play = true;
            playerX -= 20;}
        }

        public void moveUp(){
            if (worldNum!=9 && gameplayOn ==true){
                play = true;
                playerY -= 20;}
        }

        public void moveDown(){
            if (worldNum!=9 && gameplayOn ==true){
                play = true;
                playerY += 20;}
        }

    
}
