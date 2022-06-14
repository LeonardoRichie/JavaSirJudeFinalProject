import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

class MapStats{
    public int x; //world num
    public int brickWidth;
    public int brickHeight;
    public int numBricks =0;
    //map list to store map per levels
    int[][][] map ={
        //1
        {{1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1}},
        
        //2
        {{0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,0,0,0}},

        //3
        {{1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1}},
        
        //4
        {{1,0,0,0,0,0,0,0,0,1},
        {0,1,0,0,0,0,0,0,1,0},
        {0,0,1,0,0,0,0,1,0,0},
        {0,0,0,1,0,0,1,0,0,0},
        {0,0,0,0,1,1,0,0,0,0},
        {0,0,0,0,1,1,0,0,0,0},
        {0,0,0,1,0,0,1,0,0,0},
        {0,0,1,0,0,0,0,1,0,0},
        {0,1,0,0,0,0,0,0,1,0},
        {1,0,0,0,0,0,0,0,0,1}},
    
        //5
        {{1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,0,1,0,1}},
    
        //6
        {{1,1,0,0,0,0,0,0,0,0},
        {1,1,1,0,0,0,0,0,0,0},
        {0,1,1,1,0,0,0,0,0,0},
        {0,0,1,1,1,0,0,0,0,0},
        {0,0,0,1,1,1,0,0,0,0},
        {0,0,0,0,1,1,1,0,0,0},
        {0,0,0,0,0,1,1,1,0,0},
        {0,0,0,0,0,0,1,1,1,0},
        {0,0,0,0,0,0,0,1,1,1},
        {0,0,0,0,0,0,0,0,1,1}},

        //7
        {{1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1}},

        //8
        {{1,1,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,1,1},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,1,1,0,0,1,1,0,0},
        {0,0,1,1,0,0,1,1,0,0},
        {0,0,1,1,0,0,1,1,0,0},
        {0,0,0,0,1,1,0,0,0,0},
        {0,0,0,0,1,1,0,0,0,0}},

        //9
        {{1,0,1,0,1,0,1,0,1,0},
        {1,1,1,1,1,1,1,1,1,1},
        {0,1,0,0,0,0,0,0,1,0},
        {0,1,0,0,0,0,0,0,1,0},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {0,0,0,1,0,0,1,0,0,0},
        {0,0,0,1,0,0,1,0,0,0},
        {1,1,1,1,1,1,1,1,1,1},
        {0,1,0,1,0,1,0,1,0,1}},
    
        //10
        {{0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0}}};
}

public class MapGenerator extends MapStats {
    //public int map[][];
    
    public MapGenerator(int row, int col, int num){
        //generate number, totalbricks and sizes
        x = num;
        
        brickWidth = 540/col; //width of area divided by number of coloumns
        brickHeight = 350/row; //width of area divided by number of coloumns

    }
    public void draw(Graphics2D g){
        //loop into the list map to generate the map
        for(int i = 0; i < 10; i++){
            for(int j=0; j<10; j++){
                if(map[x][i][j]==1)//equals to 1 = bricks
                {   
                    //fill in bricks
                    g.setColor(Color.red);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    
                
                    //add stroke per bricks
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        //change the brick value so when it changed to zero, bricks will be empty
        map[x][row][col] = value;
    }


    
    public static void main(String[] args){}
}