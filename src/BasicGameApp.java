//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import java.util.*;

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 800;
    final int HEIGHT = 800;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    private int prevX, prevY;
    private Color currentColor = Color.BLACK;
    private int markerThickness = 3; // Marker thickness
    private DrawingGame.DrawingPanel drawingPanel;
    int screen = 0;

    public BufferStrategy bufferStrategy;

    ArrayList<ArrayList<Point>> presets = new ArrayList<>();
    ArrayList<Point> userDraw = new ArrayList<>();

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setBackground(Color.WHITE);

        if(screen == 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font( "SansSerif", Font.PLAIN, 18 ));
            g.drawString("Instructions:\n" +
                    "1. An image will pop up on your screen for a few seconds.\n", 20, 200);
            g.drawString("2. After the image disappears, recreate the image to the best of your memory in the white box. You will be graded on your drawing's accuracy.\n", 20, 300);
            g.drawString("3. Click ENTER to submit for grading. Submit ENTER to move to next level after, or click r to restart level. \n", 20, 400);
            g.drawString("4. Click SPACE to start", 20, 500);
        }

        else if(screen == 1) {
            for (int i = 0; i < userDraw.size() - 1; i++) {
                g.setStroke(new BasicStroke(markerThickness));
                g.drawLine(userDraw.get(i).x, userDraw.get(i).y, userDraw.get(i + 1).x, userDraw.get(i + 1).y);
            }
        }


        //draw the images

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == ' ') {
                    screen ++;
                    screen %= 2;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int prevX = e.getX();
                int prevY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
//                System.out.println("TF?");
                int x = e.getX();
                int y = e.getY();

                userDraw.add(new Point(x, y));

                prevX = x;
                prevY = y;
            }
        });

        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == ' ') {
            screen ++;
            screen %= 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("TF?");
        int x = e.getX();
        int y = e.getY();

        userDraw.add(new Point(x, y));

//        render(x, y);
//        Graphics2D g2d = (Graphics2D) getGraphics();
//        g2d.setColor(currentColor);
//        g2d.setStroke(new BasicStroke(markerThickness)); // Set marker thickness
//        g2d.drawLine(prevX, prevY, x, y);

        prevX = x;
        prevY = y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public static ArrayList<Point> findBlack(String filePath) throws IOException {
        ArrayList<Point> result = new ArrayList<>();
        // Load the image
        BufferedImage image = ImageIO.read(new File(filePath));

        // Get image dimensions
        int width = image.getWidth();
        int height = image.getHeight();

        // Iterate over all pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                // Decompose the color into RGB components
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // Check if the pixel is black
                if (red <= 5 && green <= 5 && blue <= 5) {
                    result.add(new Point(x, y));
                }
            }
        }
        return result;
    }
}





////Basic Game Application
//// Basic Object, Image, Movement
//// Threaded
//
////*******************************************************************************
////Import Section
////Add Java libraries needed for the game
////import java.awt.Canvas;
//
////Graphics Libraries
//import java.awt.Graphics2D;
//import java.awt.event.*;
//import java.awt.image.BufferStrategy;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
////*******************************************************************************
//
//public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener {
//
//    //Variable Definition Section
//    //Declare the variables used in the program
//    //You can set their initial values too
//
//    //Sets the width and height of the program window
//    final int WIDTH = 800;
//    final int HEIGHT = 800;
//
//    //Declare the variables needed for the graphics
//    public JFrame frame;
//    public Canvas canvas;
//    public JPanel panel;
//
//    private int prevX, prevY;
//    private Color currentColor = Color.BLACK;
//    private int markerThickness = 3; // Marker thickness
//    private DrawingGame.DrawingPanel drawingPanel;
//    int screen = 0;
//
//    public BufferStrategy bufferStrategy;
//
//    ArrayList<ArrayList<Point>> presets = new ArrayList<>();
//    ArrayList<Point> userDraw = new ArrayList<>();
//
//    // Main method definition
//    // This is the code that runs first and automatically
//    public static void main(String[] args) {
//        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
//        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
//    }
//
//
//    // This section is the setup portion of the program
//    // Initialize your variables and construct your program objects here.
//    public BasicGameApp() { // BasicGameApp constructor
//
//        setUpGraphics();
//
//        //variable and objects
//        //create (construct) the objects needed for the game
//
//    } // end BasicGameApp constructor
//
//
////*******************************************************************************
////User Method Section
////
//// put your code to do things here.
//
//    // main thread
//    // this is the code that plays the game after you set things up
//    public void run() {
//        //for the moment we will loop things forever.
//        while (true) {
//            moveThings();  //move all the game objects
//            render();  // paint the graphics
//            pause(10); // sleep for 10 ms
//        }
//    }
//
//    public void moveThings() {
//        //call the move() code for each object
//    }
//
//    //Paints things on the screen using bufferStrategy
//    private void render() {
//        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
//        g.clearRect(0, 0, WIDTH, HEIGHT);
//        g.setBackground(Color.WHITE);
//
//        for(int i = 0; i<userDraw.size()-1; i++) {
//            g.setStroke(new BasicStroke(markerThickness));
//            g.drawLine(userDraw.get(i).x, userDraw.get(i).y, userDraw.get(i+1).x, userDraw.get(i+1).y);
//        }
//        //draw the images
//
//        g.dispose();
//        bufferStrategy.show();
//    }
//
//    //Pauses or sleeps the computer for the amount specified in milliseconds
//    public void pause(int time ) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//        }
//    }
//
//    //Graphics setup method
//    private void setUpGraphics() {
//        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
//
//        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
//        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
//        panel.setLayout(null);   //set the layout
//
//        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
//        // and trap input events (Mouse and Keyboard events)
//        canvas = new Canvas();
//        canvas.setBounds(0, 0, WIDTH, HEIGHT);
//        canvas.setIgnoreRepaint(true);
//
//        panel.add(canvas);  // adds the canvas to the panel.
//
//        // frame operations
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
//        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
//        frame.setResizable(false);   //makes it so the frame cannot be resized
//        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
//
//        // sets up things so the screen displays images nicely.
//        canvas.createBufferStrategy(2);
//        bufferStrategy = canvas.getBufferStrategy();
//        canvas.requestFocus();
//        canvas.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//        canvas.addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int prevX = e.getX();
//                int prevY = e.getY();
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//
//        canvas.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
////                System.out.println("TF?");
//                int x = e.getX();
//                int y = e.getY();
//
//                userDraw.add(new Point(x, y));
//
//                prevX = x;
//                prevY = y;
//            }
//        });
//
//        System.out.println("DONE graphic setup");
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(e.getKeyChar() == ' ') {
//            screen ++;
//            screen %= 2;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        System.out.println("TF?");
//        int x = e.getX();
//        int y = e.getY();
//
//        userDraw.add(new Point(x, y));
//
////        render(x, y);
////        Graphics2D g2d = (Graphics2D) getGraphics();
////        g2d.setColor(currentColor);
////        g2d.setStroke(new BasicStroke(markerThickness)); // Set marker thickness
////        g2d.drawLine(prevX, prevY, x, y);
//
//        prevX = x;
//        prevY = y;
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }
//
//
//    public static ArrayList<Point> findBlack(String filePath) throws IOException {
//        ArrayList<Point> result = new ArrayList<>();
//        // Load the image
//        BufferedImage image = ImageIO.read(new File(filePath));
//
//        // Get image dimensions
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        // Iterate over all pixels
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int pixel = image.getRGB(x, y);
//                // Decompose the color into RGB components
//                int red = (pixel >> 16) & 0xff;
//                int green = (pixel >> 8) & 0xff;
//                int blue = (pixel) & 0xff;
//
//                // Check if the pixel is black
//                if (red <= 5 && green <= 5 && blue <= 5) {
//                    result.add(new Point(x, y));
//                }
//            }
//        }
//        return result;
//    }
//}
//
//
//
