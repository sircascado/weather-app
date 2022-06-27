package com.cascado.client;

import javax.swing.*;

public class Window extends JFrame {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 200;
    private final String TITLE = "Weather";

    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(TITLE);

        Panel panel = new Panel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        this.add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
