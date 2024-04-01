//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

//*******************************************************************************

public class BasicGameApp extends JFrame {



    ArrayList<Point> userDraw = new ArrayList<>();
    int prevX, prevY;
    public BasicGameApp() {
        setTitle("Drawing Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.WHITE);

        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
                userDraw.add(new Point(prevX, prevY));
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                Graphics g = drawingPanel.getGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(prevX, prevY, x, y);

                prevX = x;
                prevY = y;
                userDraw.add(new Point(prevX, prevY));
            }
        });

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(drawingPanel, BorderLayout.CENTER);
        contentPane.add(drawingPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BasicGameApp game = new BasicGameApp();
                game.setVisible(true);
            }
        });
    }

    public static class Point {
        double x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}