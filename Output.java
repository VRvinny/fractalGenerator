import java.awt.*;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//http://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
public class Output extends JFrame implements ActionListener, MouseListener{  
    private JFrame frameGUI;    

    private JButton zoomButton, resetButton, saveButton, menuButton;
    private int xClick ,x2Click ,yClick , y2Click;
    private int clickCounter = 0;
    private int runTimeCounter = 0;

    private String choiceFractal;
    private BufferedImage fractalImage;
    private int screenWidth, screenHeight;
    private JLabel fractalImageJ; 

    protected double[] minCoord = new double[2];
    protected double[] maxCoord = new double[2];
    protected JButton menuClicked, zoomClicked, resetClicked; 
    public void outputScreen(BufferedImage fractalImage, int screenWidth, int screenHeight, String choiceFractal, double[] minCoord, double[] maxCoord){
        constructors(fractalImage, screenWidth, screenHeight, choiceFractal, minCoord, maxCoord);
        resetValues();

        switch(runTimeCounter){
            case 0:
            //if a fractal has been created afresh 
            //this only needs to be run once
            setValues();
            frameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(frameGUI.getContentPane());

            case 1:
            // if you have zoomed in, take the current bufferedimage out of the jframe
            // put in the new one

            Container parent = fractalImageJ.getParent();
            parent.remove(fractalImageJ);

            fractalImageJ = new JLabel(new ImageIcon(fractalImage));

            frameGUI.getContentPane().setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = 12;
            c.gridheight = 3;
            c.gridx=0;
            c.gridy=0;

            frameGUI.getContentPane().add(fractalImageJ ,c);
            parent.validate();
            parent.repaint();

            break;
        }     

        frameGUI.setResizable(false);
        frameGUI.pack();
        frameGUI.setVisible(true);

        runTimeCounter++;
    }

    public void resetValues(){
        // reset values to make sure no bugs can happen
        xClick=0;
        x2Click=0;
        yClick=0;
        y2Click=0;
        clickCounter=0;
    }

    public void setValues(){
        frameGUI = new JFrame("Fractal Generator- Fractal");
        zoomButton = new JButton("Zoom"); 
        resetButton = new JButton("Reset"); 
        saveButton = new JButton("Save");
        menuButton = new JButton("Menu");

        zoomClicked = new JButton("zoom button clicked");
        resetClicked = new JButton("reset button clicked");
        menuClicked = new JButton("menu button clicked");
    }

    public void constructors(BufferedImage fractalImage, int screenWidth, int screenHeight, String choiceFractal, double[] minCoord, double[] maxCoord){
        // make the variables in this class equal to the variables passed from Main
        this.fractalImage = fractalImage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.choiceFractal = choiceFractal;
        this.minCoord[0] = minCoord[0];
        this.minCoord[1] = minCoord[1];
        this.maxCoord[0] = maxCoord[0];
        this.maxCoord[1] = maxCoord[1];
    }

    public void convertPixel(){
        //interpolation
        //add a percentage to the minimum x and y coordinates
        //the percentage is the 'distance' from the minimum coordinates divided by the screen width/height then multiplied by the difference in the maximum and minimum coordinates
        double minX = minCoord[0]+ (((double)xClick/(double)screenWidth)*(maxCoord[0]-minCoord[0]));
        double maxX = minCoord[0]+ (((double)x2Click/(double)screenWidth)*(maxCoord[0]-minCoord[0]));
        double minY = minCoord[1]+ (((double)yClick/(double)screenHeight)*(maxCoord[1]-minCoord[1]));
        double maxY = minCoord[1]+ (((double)y2Click/(double)screenHeight)*(maxCoord[1]-minCoord[1]));

        double temp1;
        //swap values if minX/minY is greater than maxX/maxY
        if(minX>maxX){
            temp1 = minX;
            minX=maxX;
            maxX=temp1;
        }
        if(minY>maxY){
            temp1 = minY;
            minY=maxY;
            maxY=temp1;
        }

        minCoord[0] = minX;
        minCoord[1] = minY;
        maxCoord[0] = maxX;
        maxCoord[1] = maxY;
    }

    public void setLayout(Container pane){
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // 'put' the bufferedimage onto a jlabel by first making an ImageIcon out of it
        // then you can put the jlabel onto the jframe
        fractalImageJ= new JLabel(new ImageIcon(fractalImage));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 12;
        c.gridheight = 3;
        c.gridx=0;
        c.gridy=0;
        pane.add(fractalImageJ ,c);

        c.gridwidth = 2;
        c.gridheight = 1;

        //add menu button to the window
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,15);
        c.gridx=0;
        c.gridy=4;
        pane.add(menuButton, c);

        //add reset button to the window
        c.insets = new Insets(0,0,0,15);
        c.gridx=2;
        pane.add(resetButton, c);

        //add zoom button to the window
        c.insets = new Insets(0,0,0,15);
        c.gridx=4;
        pane.add(zoomButton, c);

        //add save button to the window
        c.gridx=6;
        c.gridy=4;
        pane.add(saveButton, c);

        //add action listeners to the buttons
        zoomButton.addActionListener(this);
        resetButton.addActionListener(this);
        menuButton.addActionListener(this);
        saveButton.addActionListener(this);
        //add a mouse listener to the gui
        frameGUI.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e){
        //need to take away pixels to make fractal image in line with the jframe mouselistener
        int xVal = e.getX()-4;
        int yVal = e.getY()-25;
        // validation to make sure 2 clicks aren't on the same line
        if(clickCounter%2==0 && xVal!=x2Click && yVal != y2Click){
            // every odd click
            xClick=xVal;
            yClick=yVal;
            JOptionPane.showMessageDialog(null, "Click received at pixel ("+xVal+","+yVal+").", "Fractal Generator - Click recieved", 1);
            clickCounter++;
        }else if(clickCounter%2==1 && xVal!=xClick && yVal!=yClick){
            // every even click
            x2Click=xVal;
            y2Click=yVal;
            JOptionPane.showMessageDialog(null, "Click received at pixel ("+xVal+","+yVal+").", "Fractal Generator - Click recieved", 1);
            clickCounter++;
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==zoomButton){
            // if 2 successful clicks have been made and a click on the zoom button has been made
            if(clickCounter>=2 && x2Click!=0){
                convertPixel();
                clickCounter=0;
                //trigger a button which sets off an actionlistener in the class main
                zoomClicked.doClick();
            }else{
                //error message
                JOptionPane.showMessageDialog(null, "You have not selected two valid points on the fractal. Please try again.", "Fractal Generator - Zoom Unsuccessful", 1);
            }
        } else if(e.getSource()==resetButton){
            //trigger a button which sets off an actionlistener in the class main
            resetClicked.doClick();
        } else if(e.getSource()==saveButton){
            doSave();
        } else if(e.getSource()==menuButton){
            frameGUI.setVisible(false);
            menuClicked.doClick();
        }
    }

    public void doSave(){
        // set file name 
        // date and time followed by fractal type
        DateFormat dateFormat = new SimpleDateFormat("HH.mm.ss dd MM yy ");
        String timeDate = dateFormat.format(new Date());
        try{
            ImageIO.write(fractalImage, "PNG",new File(timeDate+choiceFractal+".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "The fractal has been successfully saved.", "Fractal Generator-Save Image", 1);
    }
    // these methods are not needed for the program but are necessary to work 
    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}//e.getClickCount(), e);     

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}
}