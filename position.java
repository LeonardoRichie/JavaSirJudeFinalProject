class position{
        //class for ball position
        private int ballposX;//position of ball in x
        private int ballposY;//position of ball in y
        private int ballXdir;//direction of ball in x
        private int ballYdir;//direction of ball in y
    
        public int posX(){
            return ballposX;
            //return ball position x
        }
    
        public int posY(){
            return ballposY;
            //return ball position y
        }
    
        public void resetPosition(){
            setPosition(0,0);
            //reset the position of the ball
        }
        public void addX(int x){
            this.ballposX += x;
            //add position X for the ball to move;
        }
    
        public void addY(int y){
            this.ballposY += y;
            //add position Y for the ball to move;
        }
    
        public void setPosition(int x, int y){
            this.ballposX = x;
            this.ballposY = y;
            //set position in both x and y
        }
    
        public int dirX(){
            return ballXdir;
            //return the direction of the ball in x
        }
    
        public int dirY(){
            return ballYdir;
            //return the direction of the ball in y
        }
    
        public void flipXdir(){
            ballXdir = ballXdir*-1;
            //flip the direction ball in x (when ball is bouncing)
        }
    
        public void flipYdir(){
            ballYdir = ballYdir*-1;
            //flip the direction ball in y (when ball is bouncing)
        }
    
        public void setDir(int x, int y){
            this.ballXdir = x;
            this.ballYdir = y;
            //set direction of the ball;
        }
    
        public void resetDir(){
            this.ballXdir = 0;
            this.ballYdir = 0;
            //reset direction of the ball
        }
    }