import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
/**********************************************************************************************************************************
 *                                                THINGS TO DO
 *                                                
 * add back button code DONE
 * add a loading screen when calculating pixels DONE
 * tutorial DONE
 * ADD BUFFERED IMAGES DONE
 * reset button DONE
 * ADD CODE TO ZOOM IN MULTIPLE TIMES DONE
 * ADD CODE FOR MENU BUTTON IN OUTPUT  DONE
 * ADD CODE FOR MORE JULIA SET CO-ORDINATES
 * CLICKING ON MENU GENERATES MORE MENU SCREENS DONE
 * add inputs for custom julia set coordinates DONE
 * 
 *
 * work out the real and imaginary parts of calculations again?
 * WAY TO IMPROVE, ADD USER INPUT FOR JULIA SET COORDINATES
 * ADD LOADING SCREENS 
 * Calculation class can become cmore efficient, make all the mandz2/3/4/5 into one method and add a switch statement
 * 
 * choicetutorial/colour changed from strings to integers as there are a finite number of options, easier to use ints
 * 
 * 
 ***********************************************************************************************************************************/

 
 public class Main implements ActionListener{
    private Input getInput = new Input();
    private Tutorial runTutorial = new Tutorial();
    private Calculate doCalculations = new Calculate();
    private Output getOutput = new Output();

    private String choiceFractal;
    private int choiceIteration, choiceTutorial, choiceColour;
    private BufferedImage fractalImage;

    private int screenWidth, screenHeight;
    private int runTimeCounter = 0;

    private double[] minCoord = new double[2];
    private double[] maxCoord = new double[2];

    public static void main(String[] args){
        //create instance of Main
        (new Main()).runMain(0);
    }

    public void runMain(int i){
        switch (i){
            case 0:
            setDimension();
            //initialise the buffered image
            fractalImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
            //set coordinates to default
            resetCoords();
            getInput.getValues();
            //set action listener to say getting inputs is complete
            getInput.inputComplete.addActionListener(this);

            break;
            case 1:

            setValues();
            if(choiceFractal.equals("Mandelbrot Z^2") && choiceTutorial==1){
                // run the tutorial
                runTutorial.outputTutorial(screenWidth, screenHeight);
                //set action listener to say tutorial is now complete
                runTutorial.tutorialComplete.addActionListener(this);
            }else{
                // do the calculations and output
                generate();
            }
            break;
        }
    }

    public void setDimension(){
        //get the screen dimensions and store a scaled version of it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (((int) screenSize.getWidth())/4)*2;
        screenHeight = (((int) screenSize.getHeight())/3)*2;
    }

    public void generate(){
        // do the calculations and run the output
        fractalImage = doCalculations.calculation(choiceFractal, choiceColour, choiceIteration, fractalImage, screenWidth, screenHeight, minCoord, maxCoord);
        showOutput(fractalImage, screenWidth, screenHeight, choiceFractal, minCoord, maxCoord);
    }

    public void resetCoords(){
        //original coordinates 
        minCoord[0] = -41D/15D;
        minCoord[1] = -2.05D;
        maxCoord[0] = 41D/15D;
        maxCoord[1] = 2.05D;
    }

    public void setValues(){
        //gets the values from the input class
        choiceFractal = getInput.selectedFractal;
        choiceTutorial = getInput.tutorialValue;
        choiceColour = getInput.colourScheme;
        choiceIteration = getInput.iterationNo;
    }

    public void showOutput(BufferedImage fractalImage, int screenWidth, int screenHeight, String choiceFractal, double[] minCoord, double[] maxCoord){         
        //run the output screen
        getOutput.outputScreen(fractalImage, screenWidth, screenHeight, choiceFractal, minCoord, maxCoord);
        if(runTimeCounter==0){
            //make sure the action listeners are only set once
            getOutput.zoomClicked.addActionListener(this);
            getOutput.resetClicked.addActionListener(this);
            getOutput.menuClicked.addActionListener(this);
        }
        runTimeCounter++;
    }

    public void doZoom(){
        // get the new coordinates to zoom into from the output class
        this.minCoord[0] = getOutput.minCoord[0];
        this.minCoord[1] = getOutput.minCoord[1];
        this.maxCoord[0] = getOutput.maxCoord[0];
        this.maxCoord[1] = getOutput.maxCoord[1];

        generate();
    }

    public void doReset(){
        //run code to reset fractal
        resetCoords();
        generate();
    }

    public void doMenu(){
        // run code to run the menu again
        resetCoords();
        getInput.getValues(1);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==getInput.inputComplete){
            //if user has finished inputting values
            runMain(1);
        }else if(e.getSource()==runTutorial.tutorialComplete){
            //if the user has finished the tutorial
            generate();
        }
        if(e.getSource()==getOutput.zoomClicked){
            // if the user has clicked on the zoom button on the Output screen
            doZoom();
        } else if(e.getSource()==getOutput.resetClicked){
            // if the user has clicked on the reset button on the Output screen
            doReset();
        } else if(e.getSource()==getOutput.menuClicked){
            // if the user has clicked on the menu button on the Output screen
            doMenu();
        }
    }
}