package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Frame extends JFrame implements MouseListener,ActionListener{
    JLabel label;
    JLabel label2;
    JLabel label3;
    Clip clip;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    ImageIcon icon=new ImageIcon("space_ship.png");
    ImageIcon ship=new ImageIcon("901787.png");
    ImageIcon image=new ImageIcon("moon.png");
    Image enemy;
    Random random=new Random();
    int score=0;
    int x=0;
    int y=0;
    int xVelocity=2;
    int yVelocity=3;
    boolean gameOver=false;

    Frame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);

        enemy=new ImageIcon("enemy.png").getImage();


        Timer timer=new Timer(400,e ->colorchange());
        timer.start();

        Timer time=new Timer(10,this);
        time.start();


        File file=new File("action-loop-e-90-bpm-brvhrtz-233462.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        clip=AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);

        label3=new JLabel();
        label3.setBounds(0,-30,200,100);
        label3.setForeground(Color.WHITE);
        label3.setBackground(Color.BLACK);
        label3.setFont(new Font("MV Boli",Font.BOLD,13));
        label3.setText("Click SpaceShip to change");

        label2=new JLabel();
        label2.setIcon(image);
        label2.setBounds(180,0,150,150);

        label=new JLabel();
        label.setIcon(icon);
        label.setBounds(0,400,150,150);
        label.addMouseListener(this);

        upAction= new UpAction();
        downAction=new DownAction();
        leftAction=new LeftAction();
        rightAction=new RightAction();

        label.getInputMap().put(KeyStroke.getKeyStroke("UP"),"UpKey");
        label.getActionMap().put("UpKey",upAction);

        label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"DownKey");
        label.getActionMap().put("DownKey",downAction);

        label.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"LeftKey");
        label.getActionMap().put("LeftKey",leftAction);

        label.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"RightKey");
        label.getActionMap().put("RightKey",rightAction);


        this.add(label);
        this.add(label2);
        this.add(label3);
        this.setLayout(null);
        this.setVisible(true);
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D=(Graphics2D) g;
        g2D.drawImage(enemy,x,y,null);
    }

    boolean isColliding(){

        return label.getBounds().intersects(label2.getBounds());
    }

    boolean Colliding(){
        Rectangle enemyRect=new Rectangle(x,y,enemy.getWidth(null),enemy.getHeight(null));
        return label.getBounds().intersects(enemyRect);
    }

    public void resetGame(){
        gameOver=false;
        label.setLocation(0,400);
        x=180;
        y=0;
        repaint();

    }

public void colorchange(){
        int r=random.nextInt(56);
        int g=random.nextInt(26);
        int b=random.nextInt(30);
        this.getContentPane().setBackground(new Color(r,g,b));



}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            if(x>=600-enemy.getWidth(null)||x<0){
                xVelocity=xVelocity*-1;
            }
            x += xVelocity;
            if(y>=600-enemy.getHeight(null)||y<0){
                yVelocity=yVelocity*-1;
            }
            y += yVelocity;
            repaint();
        }
    }



    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(),label.getY()-10);
            if(isColliding()){
                score++;
                JOptionPane.showMessageDialog(null,"LANDED :) score: "+score);
                label.setLocation(0,400);
                resetGame();

            }

            if(Colliding()){
                gameOver=true;
                score--;
                JOptionPane.showMessageDialog(null,"GAME OVER :( score: "+score);
                resetGame();
            }

        }
    }

    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(),label.getY()+10);
            if(isColliding()){
                score++;
                JOptionPane.showMessageDialog(null,"LANDED :) score: "+score);
                label.setLocation(0,400);
                resetGame();
            }
            if(Colliding()){
                gameOver=true;
                score--;
                JOptionPane.showMessageDialog(null,"GAME OVER :( score: "+score);
                resetGame();
            }
        }
    }

    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()-10,label.getY());
            if(isColliding()){
                score++;
                JOptionPane.showMessageDialog(null,"LANDED :) score: "+score);
                label.setLocation(0,400);
                resetGame();
            }
            if(Colliding()){
                gameOver=true;
                score--;
                JOptionPane.showMessageDialog(null,"GAME OVER :( score: "+score);
                resetGame();
            }
        }
    }

    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()+10,label.getY());
            if(isColliding()){
                score++;
                JOptionPane.showMessageDialog(null,"LANDED :) score: "+score);
                label.setLocation(0,400);
                resetGame();
            }
            if(Colliding()){
                gameOver=true;
                score--;
                JOptionPane.showMessageDialog(null,"GAME OVER :( score: "+score);
                resetGame();
            }
        }

    }




    @Override
    public void mouseClicked(MouseEvent e) {
        label.setIcon(ship);

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setIcon(icon);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
