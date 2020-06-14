package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame obj=new JFrame();
        gameplay gameplay=new gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick breaker 2d");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
