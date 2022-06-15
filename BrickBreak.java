

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BrickBreak {

    public static void main(String[] args){
        File GameSound = new File("Game.wav"); //put wav into the program variable
        
        //creating the game board
        JFrame obj = new JFrame();
        //create object from class
        Gameplay gamePlay = new Gameplay();

        //set size
        obj.setBounds(10, 10, 700, 600);

        obj.setTitle("Breakout Ball"); //title
        obj.setResizable(false); //resize false
        obj.setVisible(true); //set the program to be seen
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button
        obj.add(gamePlay); //add class gameplay

        PlaySound(GameSound); //run the play sound function


        //https://chortle.ccsu.edu/java5/notes/chap56/ch56_9.html


    }

    static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip(); //create clip refference
            clip.open(AudioSystem.getAudioInputStream(Sound)); //open the audiostream object
            clip.start(); //start the audiofile
            clip.loop(Clip.LOOP_CONTINUOUSLY); //loop the sound until game ends

        }catch(Exception e){} //if there is no song file
    }


    
    
}
