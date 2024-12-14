package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {
    JLabel label;
    JLabel label2;
    ImageIcon icon=new ImageIcon("space_ship.png");
    ImageIcon image=new ImageIcon("moon.png");
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);

        label2=new JLabel();
        label2.setIcon(image);
        label2.setBounds(180,0,150,150);

        label=new JLabel();
        label.setIcon(icon);
        label.setBounds(0,400,150,150);

        this.add(label);
        this.add(label2);
        this.setLayout(null);
        this.setVisible(true);
    }

    boolean isColliding(){
        return label.getBounds().intersects(label2.getBounds());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'w':label.setLocation(label.getX(),label.getY()-10);
                break;

            case 's':label.setLocation(label.getX(),label.getY()+10);
                break;

            case 'a':label.setLocation(label.getX()-10,label.getY());
                break;

            case 'd':label.setLocation(label.getX()+10,label.getY());
                break;

//            case :label.setLocation(label.getX()+10,label.getY()-10);
//                break;
//
//            case :label.setLocation(label.getX()-10,label.getY()+10);
//                break;
        }
        if(isColliding()){
            JOptionPane.showMessageDialog(null, "YOU LANDED ON THE MOON :)");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 38:label.setLocation(label.getX(),label.getY()-10);
                break;

            case 40:label.setLocation(label.getX(),label.getY()+10);
                break;

            case 37:label.setLocation(label.getX()-10,label.getY());
                break;

            case 39:label.setLocation(label.getX()+10,label.getY());
                break;
        }
        if(isColliding()){
            JOptionPane.showMessageDialog(null, "YOU LANDED ON THE MOON :)");

    }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
