package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play=false;
    private int score =0;
    private int totalbricks =21;
    private Timer time;
    private int delay=8;
    private int playerX=310;
    private int ballposX=120;
    private int ballposY=350;
    private int ballxdir=-1;
    private int ballydir=-2;
    private MapGenerator map;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    time.start();
    if (play){

        if (new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
            ballydir=-ballydir;
        }
        for (int i=0;i<map.map.length;i++){
            for(int j=0;j<map.map[0].length;j++){
                if (map.map[i][j]>0){
                    int brickx=j*map.brickwidth+80;
                    int bricky=i*map.brickheight+50;
                    int brickwidth=map.brickwidth;
                    int brickheight=map.brickheight;
                    Rectangle rect=new Rectangle(brickx,bricky,brickwidth,brickheight);
                    Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickrect=rect;
                    A:if (ballrect.intersects(brickrect)){
                        map.setBrickvalue(0,i,j);
                        totalbricks--;
                        score+=5;
                        if (ballposX+19<=brickrect.x || ballposX+1>=brickrect.x+brickrect.width){
                            ballxdir=-ballxdir;
                        }
                        else {
                            ballydir=-ballydir;
                        }
                        break A;
                    }

                }
            }
        }

        ballposX+=ballxdir;
        ballposY+=ballydir;
        if (ballposX<0){
            ballxdir=-ballxdir;
        }
        if (ballposY<0){
            ballydir=-ballydir;
        }
        if (ballposX>670){
            ballxdir=-ballxdir;
        }

    repaint();
    }}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
if (keyEvent.getKeyCode()==KeyEvent.VK_RIGHT)
{
    if (playerX>=600){
        playerX=600;
    }
    else {
        moveright();
    }
}
        if (keyEvent.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if (playerX<10){
                playerX=10;
            }
            else {
                moveleft();
            }
        }
        if (keyEvent.getKeyCode()==KeyEvent.VK_ENTER){
            if (!play){
                play=true;
                ballposX=120;
                ballposY=350;
                ballxdir=-1;
                ballydir=-2;
                playerX=310;
                score=0;
                totalbricks=21;
                map=new MapGenerator(3,7);
                repaint();
            }
        }





    }



    public gameplay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        time.start();
    }
    //drawing things like slider,balls ,boxes,
    public void paint(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);


        //border
        g.setColor(Color.lightGray);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);


        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //the  ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);
        if (totalbricks<=0){
            play=false;
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You won your score is "+score,190,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to restart",190,400);

        }
        if (ballposY>570){
            play=false;
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over Score is,your score is "+score,190,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to restart",190,400);


        }

        //drawing map
        map.draw((Graphics2D)g);


        //scoreboard
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);


    }
    public void moveright(){
        play=true;
        playerX+=20;
    }
    public void moveleft(){
        play=true;
        playerX-=20;
    }
}
