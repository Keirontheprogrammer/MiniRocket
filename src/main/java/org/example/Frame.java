package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame implements MouseListener {
    JLabel label;
    JLabel label2;
    JLabel label3;
    Clip clip;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    boolean isColliding;
    int score=0;
    ImageIcon icon=new ImageIcon("space_ship.png");
    ImageIcon ship=new ImageIcon("901787.png");
    ImageIcon image=new ImageIcon("moon.png");
    Frame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.getContentPane().setBackground(Color.BLACK);


        File file=new File("Gemini - The Soundlings.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        clip=AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

        label3=new JLabel();
        label3.setBackground(Color.BLACK);
        label3.setBounds(0,-30,200,100);
        label3.setForeground(Color.white);
        label3.setFont(new Font("MV Boli",Font.BOLD,13));
        label3.setOpaque(true);
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

    boolean isColliding(){

        return label.getBounds().intersects(label2.getBounds());
    }


//    @Override
//    public void keyTyped(KeyEvent e) {
//        switch (e.getKeyChar()){
//            case 'w':label.setLocation(label.getX(),label.getY()-10);
//                break;
//
//            case 's':label.setLocation(label.getX(),label.getY()+10);
//                break;
//
//            case 'a':label.setLocation(label.getX()-10,label.getY());
//                break;
//
//            case 'd':label.setLocation(label.getX()+10,label.getY());
//                break;
//
//            case :label.setLocation(label.getX()+10,label.getY()-10);
//                break;
//
//            case :label.setLocation(label.getX()-10,label.getY()+10);
//                break;
//        }
//        if(isColliding()){
//            score++;
//            JOptionPane.showMessageDialog(null, "YOU LANDED ON THE MOON :) "+score);
//        }
//    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        switch (e.getKeyCode()){
//            case 38:label.setLocation(label.getX(),label.getY()-10);
//                break;
//
//            case 40:label.setLocation(label.getX(),label.getY()+10);
//                break;
//
//            case 37:label.setLocation(label.getX()-10,label.getY());
//                break;
//
//            case 39:label.setLocation(label.getX()+10,label.getY());
//                break;
//        }
//        if(isColliding()){
//            score++;
//            JOptionPane.showMessageDialog(null, "YOU LANDED ON THE MOON :) score: "+score);
//            label.setLocation(0,400);
//
//    }
//    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(),label.getY()-10);
            if(isColliding()){
                score++;
                JOptionPane.showMessageDialog(null,"LANDED :) score: "+score);
                label.setLocation(0,400);
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
