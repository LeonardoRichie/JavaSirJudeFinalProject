//importing
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

//keylistener is to show that keyboard are pressed, released or typed
//actionlistener the action after the user do something
//JPanel represent visuals
public class Gameplay extends JPanel implements KeyListener , ActionListener {
    //stating variable
    private boolean play = false; //stop gameplay
    private boolean gameplayOn = false; //stop paddle from moving
    private int score = 0; //starting score
    private boolean frontPage = true; //to identify main page

    private int totalworldBricks;
    private int totalBricks = totalworldBricks; //total bricks in game
    private Timer timer; //create delay for the game.
    private int delay = 8; //delay time 8

    private int playerX = 310; //player position X
    private int playerY = 550;

    private int paddleSize = 100; //size of paddle in normal size
    private String nameLength = "normal"; //used for string in setting
    private int worldSpeedX = -1; //speed for setting
    private int worldSpeedY = -2; //speed for setting

    private int worldNum = 1; //world number for indexing
    private int page = 1; //indicates which page

    ball1 ball1 = new ball1(); //create ball class
    ball2 ball2 = new ball2(); //create ball class
    ball3 ball3 = new ball3(); //create ball class

    private int numberofBall = 1; //number of ball generated default

    private MapGenerator map; //create class for map

    public Gameplay(){
        map = new MapGenerator(10, 10, 9); //empty map
        worldNum = 9; //world num
        addKeyListener(this); //add key listener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this); //create object for timer
        timer.start();//start the timer
        

    }

    public void resetGame(){
        //method to reset the stats when the game ends
        gameplayOn = true; //create paddle from moving again
        //ballposX = 350;
        //ballposY = 450;

        //reset ball position
        ball1.resetPosition(); 
        ball2.resetPosition();
        ball3.resetPosition();

        //set direction ball
        ball1.setDir(worldSpeedX, worldSpeedY); 
        ball2.setDir(worldSpeedX, worldSpeedY);
        ball3.setDir(worldSpeedX, worldSpeedY);

        //set total bricks depending on each level
        totalBricks = totalworldBricks;

        //reset player position and score
        playerX = 310;
        playerY = 550;
        score = 0;
    }
    public void paint(Graphics g){//to draw object into the screen
        
        //bg
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);//location & size
        
        //draw map
        map.draw((Graphics2D)g);
        //extend graphic class to get more
        //control in changing position and rendering 2D

        

        if (page == 1){
            //front main page
            frontPage = true;
            //set title
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 60));
            g.drawString("Welcome to brick breaker" , 10, 230);
            
            //text below title
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press a number in keyboard 1-9 for different levels" , 60, 265);
            g.drawString("Press right arrow to go to settings" , 60, 295);
            }
            
        if (page == 2){
            //setting page
            frontPage = false;
            //set text
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
            //set text with variable
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Use keyboard keys to customize", 10, 400);
            g.drawString("Ball Speed: " + -worldSpeedX /*worldspeed minus*/+ "x", 10, 420);
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
        g.fillOval(ball1.posX(), ball1.posY(), 20, 20);//oval shaped

        if (numberofBall == 2){
        g.setColor(Color.green);
        g.fillOval(ball2.posX(), ball2.posY(), 20, 20);
        }
        if (numberofBall == 3){
        g.setColor(Color.green);
        g.fillOval(ball2.posX(), ball2.posY(), 20, 20);

        g.setColor(Color.blue);
        g.fillOval(ball3.posX(), ball3.posY(), 20, 20);
        }
        }

            if (numberofBall ==1 && ball1.posY() > 570 ||numberofBall ==2 &&  ball1.posY() > 570 && ball2.posY() > 570 ||
            numberofBall ==3 && ball1.posY() > 570 && ball2.posY() > 570 && ball3.posY() > 570){
                gameplayOn = false; //stops the paddle from moving when loses or finished
                play = false;
                //reset the direction of the ball (freeze the ball)
                ball1.resetDir();
                ball2.resetDir();
                ball3.resetDir();
                
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
        public void actionPerformed(ActionEvent e){ //codes that react to action

            //when the game is still running
            if (play == true){
                if (new Rectangle(ball1.posX(), ball1.posY(), 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ball1.flipYdir();
                    ball1.addY(-2); //move the ball up 1 pixel to avoid crashing with paddle
                }
                if (new Rectangle(ball2.posX(), ball2.posY(), 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ball2.flipYdir();
                    ball2.addY(-2);
                }
                if (new Rectangle(ball3.posX(), ball3.posY(), 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))){
                    ball3.flipYdir();
                    ball3.addY(-2);
                }
                A: for (int i = 0; i<10; i++){
                    for (int j = 0; j<10; j++){
                        //use loop  to create image rect when ball collide with bricks
                        if(map.map[worldNum][i][j]>0){
                            int brickX = j*map.brickWidth + 80;
                            int brickY = i*map.brickHeight + 50;
                            int brickWidth = map.brickWidth;
                            int brickHeight = map.brickHeight;

                            //create rectangle for each object
                            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                            Rectangle ballRect = new Rectangle(ball1.posX(), ball1.posY(), 20, 20);
                            Rectangle ball2Rect = new Rectangle(ball2.posX(), ball2.posY(), 20, 20);
                            Rectangle ball3Rect = new Rectangle(ball3.posX(), ball3.posY(), 20, 20);
                            Rectangle brickRect = rect;

                            //when ball collide bricks
                            if (ballRect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ball1.posX() + 19<= brickRect.x || ball1.posX() + 1 >= brickRect.x + brickRect.width){
                                    ball1.flipXdir();
                                }//from the left and right
                                else{
                                    ball1.flipYdir();
                                }//top and bottom
                                break A; //break the loop
                            }
                            if (ball2Rect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ball2.posX() + 19<= brickRect.x || ball2.posX() + 1 >= brickRect.x + brickRect.width){
                                    ball2.flipXdir();
                                }//from the left and right
                                else{
                                    ball2.flipYdir();
                                }//top and bottom
                                break A; //break the loop
                            }

                            if (ball3Rect.intersects(brickRect)){
                                //delete bricks when hitted
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                //ball bouncing off the brick
                                if(ball3.posX() + 19<= brickRect.x || ball3.posX() + 1 >= brickRect.x + brickRect.width){
                                    ball3.flipXdir();
                                }//from the left and right
                                else{
                                    ball3.flipYdir();
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
                
                //add position to the ball so it will move
                ball1.addX(ball1.dirX());
                ball1.addY(ball1.dirY());

                //if there are 2 balls inside the game
                if (numberofBall ==2){
                    ball2.addX(ball2.dirX());
                    ball2.addY(ball2.dirY());
                }

                //if there are 3 balls inside the game
                if (numberofBall ==3){
                    ball2.addX(ball2.dirX());
                    ball2.addY(ball2.dirY());
                    ball3.addX(ball3.dirX());
                    ball3.addY(ball3.dirY());
                    }

                //bouncing
                if (ball1.posX()<0){
                    ball1.flipXdir();
                }
                if (ball1.posY()<0){
                    ball1.flipYdir();
                }
                if (ball1.posX()>670){
                    ball1.flipXdir();
                }

                    //second ball
                if (ball2.posX()<0){
                    ball2.flipXdir();
                }
                if (ball2.posY()<0){
                    ball2.flipYdir();
                }
                if (ball2.posX()>670){
                    ball2.flipXdir();
                }
                    //third ball
                if (ball3.posX()<0){
                    ball3.flipXdir();
                }
                if (ball3.posY()<0){
                    ball3.flipYdir();
                }
                if (ball3.posX()>670){
                    ball3.flipXdir();
                }
            }
            repaint(); //redraw all components inside the game
        }

        //override the function for interface keylistener
        @Override 
        public void keyTyped(KeyEvent e){}
        
        @Override
        public void keyReleased(KeyEvent e){}

        @Override
        public void keyPressed(KeyEvent e){
            //right arrow key
            if(e.getKeyCode()== KeyEvent.VK_RIGHT){
                if (page == 1){
                    //move from main page to setting page
                    frontPage =false;
                    page = 2;
                }
                //prevent the paddle from going out of screen
                if(playerX >= 600){
                    playerX = 600;
                }
                else{
                    moveRight(); //move paddle to right
                }
            }

            //left arrow key
            if(e.getKeyCode()== KeyEvent.VK_LEFT){
                if (page == 2){
                    //move from the setting to the main page
                    frontPage = true;
                    page = 1;
                }
                //prevent the paddle from going out of screen
                if(playerX < 10){
                    playerX = 10;
                }
                else{
                    moveLeft(); //move paddle to left
                }     
            }

            if(e.getKeyCode()== KeyEvent.VK_UP){
                //prevent the paddle from going out of screen
                if(playerY <= 475){
                    playerY = 475;
                }
                else{
                    moveUp(); //move the paddle up
                } 
            }

            if(e.getKeyCode()== KeyEvent.VK_DOWN){
                //prevent the paddle from going out of screen
                if(playerY >= 550){
                    playerY = 550;
                }
                else{
                    moveDown(); //move the paddle down
                }  
            }

            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                //reset the game
                if(play == false){
                    resetGame();
                    map = new MapGenerator(10, 10, worldNum); //depending on the level
                    repaint();
                }
                
            }

            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                //go back to the main front page
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
                //set speed
                if (page == 2){
                    worldSpeedX = -1;
                    worldSpeedY = -2;
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_F){
                //set speed
                if (page == 2){
                    worldSpeedX = -2;
                    worldSpeedY = -4;
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_N){
                //set paddle size
                if (page == 2){
                    paddleSize = 100;
                    nameLength = "Normal";
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_S){
                //set paddle size
                if (page == 2){
                    paddleSize = 50;
                    nameLength = "Short";
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_E){
                //set paddle size
                if (page == 2){
                    paddleSize = 25;
                    nameLength = "Extreme";
                }
            }
            
            if(e.getKeyCode()==KeyEvent.VK_1){
                if (page == 2){
                    //set the number of ball inside the game
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
                    //set the number of ball inside the game
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
                    //set the number of ball inside the game
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
            if (worldNum!=9 && gameplayOn ==true){ //if the player is inside a level
            play = true;
            playerX += 20; //add player position
            }
        }

        public void moveLeft(){
            //paddle movements to the left
            if (worldNum!=9 && gameplayOn ==true){ //if the player is inside a level
            play = true;
            playerX -= 20;}
        }

        public void moveUp(){
            //paddle movements to the top
            if (worldNum!=9 && gameplayOn ==true){ //if the player is inside a level
                play = true;
                playerY -= 20;}
        }

        public void moveDown(){
            //paddle movements down
            if (worldNum!=9 && gameplayOn ==true){ //if the player is inside a level
                play = true;
                playerY += 20;}
        }

    
}
