import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ComboBoxDemoProject/src/components/ComboBoxDemo.java
//https://www.youtube.com/watch?v=uTVe8JrgIg0
//http://jmathtex.sourceforge.net/javadoc/be/ugent/caagt/jmathtex/TeXFormula.html
public class Tutorial extends JFrame implements ActionListener{
    private JFrame[] screen = new JFrame[8];
    private JFrame[] tutorialImageJ = new JFrame[2];

    private JLabel[] titleJ = new JLabel[8];
    private JLabel[][] bodyJ = new JLabel[8][5];
    private JLabel[] subTitleJ = new JLabel[4];
    private JLabel[] latexJ = new JLabel[8];

    private String[] title = new String[8];
    private String[][] body = new String[8][5];
    private String[] subTitle = new String[4];   
    private String[] latexT = new String[8];

    private BufferedImage[] tutorialImage = new BufferedImage[2];
    private ImageIcon[] tutorialImageIcon = new ImageIcon[2];

    private JButton[] backButton = new JButton[8];
    private JButton[] nextButton = new JButton[8];
    protected JButton tutorialComplete = new JButton("Complete");

    private int screenHeight, screenWidth;

    public void outputTutorial(int screenWidth, int screenHeight){  
        //set up all the windows
        constructor(screenHeight, screenWidth);
        setText();
        setLabel();
        setFont();
        setLatex();
        setImage();
        for(int i = 0;i<=7;i++){
            latexJ[i] = new JLabel();
            createLatex(i);
        }
        for(int i = 0;i<=7;i++){
            setScreen(i);
        }
        //display the first window
        screen[0].setVisible(true);
    }

    public void constructor(int screenHeight, int screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void setLatex(){
        //set text for the LaTeX
        latexT[0] = "i^2 = -1";
        latexT[1] = "Z_{n+1}= Z_n^2 + C";
        latexT[2] = "Z_{n+1}= Z_n^2 + C";
        latexT[3] = "Z_0 = 0+0i";
        latexT[4] = "Z_1 = (0+0i)^2 + (-1+0i) = -1";
        latexT[5] = "Z_2 = (-1+0i)^2 + (-1+0i) = 0";
        latexT[6] = "Z_3 = (0+0i)^2 + (-1+0i) = -1";
        latexT[7] = "Z_4 = (-1+0i)^2 + (-1+0i) = 0";
    }

    public void createLatex(int i){
        // create images with LaTeX formatting 
        TeXFormula formula = new TeXFormula(latexT[i]);
        TeXIcon latexIcon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
        BufferedImage textImage = new BufferedImage(latexIcon.getIconWidth(), latexIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        latexIcon.paintIcon(new JLabel(), textImage.getGraphics(), 0, 0);
        latexJ[i].setIcon(latexIcon);
    }    

    public void setText(){
        //set the text
        title[0] = "<html>What is a Fractal?</html>";

        body[0][0] = "<html>Fractals are beautiful, delicate patterns that are created using maths."+
        "<br> consisting of infinite shapes with self-repeating patterns as you"+
        "<br> zoom in further into the fractal.This is because fractals"+
        "<br> are not your typical shapes made up of connecting straight"+
        "<br> lines, they are usually made up of curves or straight lines."+
        "<br> Fractals are everywhere in nature in snowflakes, pineapples, trees"+
        "<br> and even clouds! In this tutorial, this program will teach you how to"+
        "<br> generate one of the most famous fractals, the Mandelbrot Set."+
        "<br> Click on \"Next\" to continue.<br></html>";

        title[1] = "<html>What is the Mandelbrot set?</html>";

        body[1][0]= "<html><br>The Mandelbrot set uses complex numbers, numbers in the form of </html>";
        body[1][1] = "<html>x+yi where x and y are integers and i is defined as </html>";
        //latex[0] will go here on the window
        body[1][2] = "<html>These points are then iterated through the formula </html>";
        //latex[1] will go here on the window
        body[1][3] = "<html>where Z is a complex number and C is a constant, complex number"+
        "<br> that will not change when we use the formula for a set of iterations."+
        "<br> This constant will be the co-ordinate.  The Mandelbrot Set is a set of"+
        "<br> numbers which does not diverge to infinity after a set amount of"+
        "<br> iterations, a value which you can choose.<br></html>";

        title[2] = "<html>Drawing the Mandelbrot Set</html>";

        subTitle[0] = "Conversion";

        body[2][0] = "<html><br>The first step in creating the Mandelbrot set is the process of"+
        "<br> converting between screen co-ordinates to x and y co-ordinates."+
        "<br> This is because programming languages start co-ordinates from"+
        "<br> the top left corner of the window whereas we need to be shifted"+
        "<br> and scaled so that the centre of the screen is the origin."+
        "<br> One way we can do this is by interpolation."+
        "<br> Since the Mandelbrot set will be contained approximately within a"+
        "<br> rectangular region between -4+2i to 4-2i, we can add a"+
        "<br> percentage of the difference between the two values"+
        "<br> and add it to -4+2i. The percentage is referring to the ratio"+
        "<br> between the current pixel's height/width position and the"+
        "<br> total number of pixels in the row/column.<br></html>";

        title[3] =  "<html>Drawing the Mandelbrot Set</html>";
        subTitle[1] = "Calculations";

        body[3][0] = "<html><br>Next we have to iterate each coordinate, which we";
        body[3][1] = "<html>calculated earlier, through the formula </html>";
        //latex[2] will go here on the window
        body[3][2] = "<html>In order to do calculations in the complex plane,"+
        "<br> you must gather all the real parts and imaginary parts"+
        "<br> together separately when multiplying two complex"+
        "<br> numbers. For example (x+yi)^2  =  x^2 + 2xyi+ y^2*i^2 ."+
        "<br> Since i^2=-1, this now becomes x^2-y^2 +2xyi."+
        "<br> When you add two complex numbers together,"+
        "<br> you can simply add the real parts together"+
        "<br> and the imaginary parts together.<br></html>";

        title[4] =  "<html>Drawing the Mandelbrot Set</html>";

        subTitle[2] = "Iteration";

        body[4][0] = "<html><br>When we iterate through the formula, we must set a"+
        "<br> condition. The condition is to find the set of numbers"+
        "<br> which do not diverge to infinity, however computers"+
        "<br> cannot deal with infinity easily.</html>";
        body[4][1] = "<html>To overcome this problem, we check the modulus,"+
        "<br> the distance between the origin, and see if it is"+
        "<br> greater than 2. If it is, the number will diverge"+
        "<br> to infinity, if not, iterate the formula again until you"+
        "<br> reach the max number of iterations which is a value"+
        "<br> that you can choose.<br></html>";

        title[5] =  "<html>Drawing the Mandelbrot Set</html>";

        subTitle[3] = "Colouring";

        body[5][0] = "<html><br>Then we need to colour the pixels, for"+
        "<br> now let's keep it black and white as it is easier to implement."+
        "<br> In order to add the colour scheme, colour the pixel black"+
        "<br> if the pixel does not diverge to infinity or colour"+
        "<br> the pixel white if it did diverge to infinity. For more complex"+
        "<br> colour schemes such as red and orange, the colour is "+
        "<br> dependenton the number of iterations for the  "+
        "<br> complex number takes to diverge, if it does, to infinity.</html>";

        title[6] = "Example";

        body[6][0] = "<html><br>To show an example, let’s choose a co-ordinate, (1+0i)"+
        "<br> and calculate and iterate it through the formula. This"+
        "<br> means that C, the constant, will be -1+0i. Remember that </html>";
        //latex[3] will go here on the window
        body[6][1] = "<html>Now we can iterate through the formula:<html>";
        // 4 lines of latex[4] to [7] will go here on the window
        body[6][2] = "<html>You can see this iteration will not diverge to infinity"+
        "<br> as Z just alternates between -1 and 0. This means"+
        "<br> we can colour the pixel corresponding to the "+
        "<br> co-ordinate (0+0i) black. The program has coloured the"+
        "<br> pixel and the surrounding pixels to make it easier to"+
        "<br> see. Now that we have seen an example of an iteration,"+
        "<br> we still have all the other co-ordinates to iterate"+
        "<br> through as well, it’s best to let a computer do"+
        "<br> all the work!</html>";
        body[6][3] = "<html>Click on the next button to calculate the rest of the"+ 
        "<br> values and colour in the pixels.<br></html>";

        title[7] = "Finish";

        body[7][0] = "<html><br>Here is the finished image. Beautiful isn’t it?"+
        "<br> The black pixels show the set of numbers which"+
        "<br> belong in the Mandelbrot set. There are lots"+
        "<br> of ways to create fractals, such as different variations of"+
        "<br> the Mandelbrot set; the Julia set where C is constant for"+
        "<br> all pixels and not just for a co-ordinate;"+
        "<br> variations where the exponent is different; fractals with"+
        "<br> different colour schemes. All of these variations are "+
        "<br> included in the program for you to look at."+
        "<br> Click on “Finish” to end the tutorial and"+
        "<br> create your own fractal!<br></html>";

        //set the text for the buttons
        for(int i = 0;i<8;i++){
            backButton[i] = new JButton("Back");
            if(i==7){
                nextButton[i]= new JButton("Finish");
                break;
            }
            nextButton[i] = new JButton("Next");
        }
    }

    public void setLabel(){
        //create jlabels using the strings from  setText()
        for(int i = 0;i<=7;i++){
            if(i<=3){
                subTitleJ[i] = new JLabel(subTitle[i]);
            }
            titleJ[i] = new JLabel(title[i]);
        }

        bodyJ[0][0] = new JLabel(body[0][0]);

        bodyJ[1][0] = new JLabel(body[1][0]);  
        bodyJ[1][1] = new JLabel(body[1][1]);
        bodyJ[1][2] = new JLabel(body[1][2]);
        bodyJ[1][3] = new JLabel(body[1][3]);

        bodyJ[2][0] = new JLabel(body[2][0]); 

        bodyJ[3][0] = new JLabel(body[3][0]);  
        bodyJ[3][1] = new JLabel(body[3][1]);  
        bodyJ[3][2] = new JLabel(body[3][2]);

        bodyJ[4][0] = new JLabel(body[4][0]); 
        bodyJ[4][1] = new JLabel(body[4][1]);  

        bodyJ[5][0] = new JLabel(body[5][0]);   

        bodyJ[6][0] = new JLabel(body[6][0]); 
        bodyJ[6][1] = new JLabel(body[6][1]); 
        bodyJ[6][2] = new JLabel(body[6][2]); 
        bodyJ[6][3] = new JLabel(body[6][3]); 

        bodyJ[7][0] = new JLabel(body[7][0]); 
    }

    public void setFont(){
        for(int i = 0;i<=7;i++){
            if(i<=3){
                subTitleJ[i].setFont(new Font("Arial", Font.BOLD, 22));
            }
            titleJ[i].setFont(new Font("Arial", Font.BOLD, 28));
        }

        bodyJ[0][0].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[1][0].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[1][1].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[1][2].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[1][3].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[2][0].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[3][0].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[3][1].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[3][2].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[4][0].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[4][1].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[5][0].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[6][0].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[6][1].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[6][2].setFont(new Font("Arial", Font.PLAIN, 18));
        bodyJ[6][3].setFont(new Font("Arial", Font.PLAIN, 18));

        bodyJ[7][0].setFont(new Font("Arial", Font.PLAIN, 18));
    }

    public void setImage(){
        //create the windows for the tutorialimage 
        for(int i = 0;i<=1;i++){
            tutorialImageJ[i] = new JFrame("Fractal Generator-Tutorial Image "+ i);
            tutorialImage[i] = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
            for(int col = 0;col<screenWidth;col++){
                for(int row = 0;row<screenHeight;row++){
                    //set all pixels to white in the bufferedimage
                    tutorialImage[i].setRGB(col, row, 0xFFFFFF);
                }
            }
        }

        //colour the point -1+0i
        int pixelX = (int) (((double)26*(double)screenWidth)/81D);
        int pixelY = (int) screenHeight/2;
        for(int col=(pixelX-10);col<(pixelX+10);col++){
            for(int row=(pixelY-10);row<(pixelY+10);row++){
                tutorialImage[0].setRGB(col, row, 0x000000);
            } 
        }

        // draw the black and white mandelbrot z^2 set
        tutorialImage[1] = new Calculate().calculation("Mandelbrot Z^2", 3, 1000, tutorialImage[1], screenWidth, screenHeight, new double[] {(-41D/15D), -2.05D}, new double[] {41D/15D, 2.05});
        for(int i = 0;i<=1;i++){
            for(int col = 0;col<screenWidth;col++){
                //draw the y axis
                tutorialImage[i].setRGB(col, screenHeight/2, 0x2F4F4F);
            }
            for(int row = 0;row<screenHeight;row++){
                //draw the x axis
                tutorialImage[i].setRGB(screenWidth/2, row, 0x2F4F4F);
            }
        }

        //convert the buffered image to an image icon then put it on a jlabel and then onto the jframe
        for(int i = 0;i<=1;i++){
            tutorialImageIcon[i] = new ImageIcon(tutorialImage[i]);
            tutorialImageJ[i].add(new JLabel(tutorialImageIcon[i]));
            tutorialImageJ[i].setResizable(false);
            tutorialImageJ[i].pack();
        }
    }

    public void setScreen(int i){
        //dynamically set up the screen
        screen[i] = new JFrame("Fractal Generator- Tutorial Screen " + i);
        screen[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen[i].setResizable(false);
        screen[i].setLocationRelativeTo(null);
        setLayout(screen[i].getContentPane(), i);
        screen[i].pack();
    }

    public void setLayout(Container pane, int i){
        GridBagConstraints c = new GridBagConstraints();
        pane.setLayout(new GridBagLayout());
        pane.removeAll();

        // add the title
        c.anchor= GridBagConstraints.PAGE_START;
        c.gridwidth =5;
        c.gridheight= 1;
        c.gridx=0;
        c.gridy=1;
        pane.add(titleJ[i], c);

        switch(i){
            case 0:
            //What is a fractal?

            c.anchor= GridBagConstraints.LINE_START;
            c.gridy = 2;
            pane.add(bodyJ[0][0], c);

            break;
            case 1:
            //What is the Mandelbrot set?

            c.anchor= GridBagConstraints.LINE_START;
            c.gridy=2;
            pane.add(bodyJ[1][0], c);

            c.gridwidth = 4;
            c.gridy=3;
            pane.add(bodyJ[1][1], c);

            c.gridwidth = 1;
            c.gridx=4;
            pane.add(latexJ[0], c);

            c.gridwidth = 4;
            c.gridx=0;
            c.gridy=5;
            pane.add(bodyJ[1][2], c);

            c.gridwidth = 1;
            c.gridx=4;            
            pane.add(latexJ[1], c);

            c.gridwidth = 5;
            c.gridx=0;
            c.gridy=6;
            pane.add(bodyJ[1][3], c);

            break;
            case 2:
            //Drawing the Mandelbrot set- Conversion

            c.gridy=2;
            pane.add(subTitleJ[0], c);

            c.anchor= GridBagConstraints.LINE_START;
            c.gridwidth=5;
            c.gridy=3;
            pane.add(bodyJ[2][0], c);   

            break;
            case 3:
            //Drawing the Mandelbrot set- Calculation

            c.gridy = 2;
            pane.add(subTitleJ[1], c);

            c.anchor= GridBagConstraints.LINE_START;
            c.gridy = 3;            
            pane.add(bodyJ[3][0], c);   

            c.gridwidth = 4;
            c.gridy=4;
            pane.add(bodyJ[3][1], c);

            c.gridwidth=1;
            c.gridx=4;
            pane.add(latexJ[2], c);

            c.gridwidth = 5;
            c.gridx = 0;
            c.gridy = 5;
            pane.add(bodyJ[3][2], c);   

            break;
            case 4:
            //Drawing the Mandelbrot set- Iteration

            c.gridy=2;
            pane.add(subTitleJ[2], c);

            c.anchor = GridBagConstraints.LINE_START;
            c.gridy = 3;
            pane.add(bodyJ[4][0], c);
            c.gridy = 4;
            pane.add(bodyJ[4][1], c);

            break;
            case 5:
            //Drawing the Mandelbrot set-Colouring

            c.gridy=2;
            pane.add(subTitleJ[3], c);

            c.anchor= GridBagConstraints.LINE_START;
            c.gridy=3;
            pane.add(bodyJ[5][0], c);

            break;
            case 6:            
            //Example

            c.anchor = GridBagConstraints.LINE_START;
            c.gridy=2;
            pane.add(bodyJ[6][0], c);

            c.gridy=3;
            pane.add(latexJ[3], c);

            c.gridy=4;
            pane.add(bodyJ[6][1], c);

            c.gridy=5;
            pane.add(latexJ[4], c);

            c.gridy=6;
            pane.add(latexJ[5], c);

            c.gridy=7;
            pane.add(latexJ[6], c);

            c.gridy=8;
            pane.add(latexJ[7], c);

            c.gridy=9;
            pane.add(bodyJ[6][2], c);

            c.gridy=10;
            pane.add(bodyJ[6][3], c);

            break;

            case 7:
            //Finish

            c.anchor= GridBagConstraints.LINE_START;
            c.gridy = 2;
            pane.add(bodyJ[7][0], c);

            break;
        }

        // add the next and back buttons onto the window
        if(i!=0){
            c.anchor= GridBagConstraints.LAST_LINE_START;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx=2;
            c.gridy++;
            pane.add(backButton[i-1], c);
            c.anchor = GridBagConstraints.LAST_LINE_END;
            c.gridx=4;
            pane.add(nextButton[i], c);
        } else{
            c.anchor= GridBagConstraints.PAGE_END;
            c.gridwidth =1;
            c.gridx=4;
            c.gridy=3;
            pane.add(nextButton[0], c); 
        }

        // add the action listeners to the buttons
        nextButton[i].addActionListener(this);
        if(i!=0){
            backButton[i-1].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e){
        //if the next button is clicked on any of the screens
        if(e.getSource()==nextButton[0]){
            screen[0].setVisible(false);
            screen[1].setVisible(true);
        }  else if(e.getSource()==nextButton[1]){
            screen[1].setVisible(false);
            screen[2].setVisible(true);
        }  else if(e.getSource()==nextButton[2]){
            screen[2].setVisible(false);
            screen[3].setVisible(true);
        }  else if(e.getSource()==nextButton[3]){
            screen[3].setVisible(false);
            screen[4].setVisible(true);
        }  else if(e.getSource()==nextButton[4]){
            screen[4].setVisible(false);
            screen[5].setVisible(true);     
        }   else if(e.getSource()==nextButton[5]){
            screen[5].setVisible(false);
            screen[6].setVisible(true); 
            tutorialImageJ[0].setVisible(true);
        }  else if(e.getSource()==nextButton[6]){
            screen[6].setVisible(false);
            screen[7].setVisible(true);     
            tutorialImageJ[1].setVisible(true);
            tutorialImageJ[0].setVisible(false);
        }   else if(e.getSource()==nextButton[7]){
            //final window 
            screen[7].setVisible(false);    
            tutorialImageJ[1].setVisible(false);
            tutorialComplete.doClick();
        }

        // if the back button is clicked on any of the screens
        if(e.getSource()==backButton[0]){
            screen[1].setVisible(false);
            screen[0].setVisible(true);
        }  else if(e.getSource()==backButton[1]){
            screen[2].setVisible(false);
            screen[1].setVisible(true);
        }  else if(e.getSource()==backButton[2]){
            screen[3].setVisible(false);
            screen[2].setVisible(true);
        }  else if(e.getSource()==backButton[3]){
            screen[4].setVisible(false);
            screen[3].setVisible(true);
        }   else if(e.getSource()==backButton[4]){
            screen[5].setVisible(false);
            screen[4].setVisible(true);
        }   else if(e.getSource()==backButton[5]){
            screen[6].setVisible(false);
            screen[5].setVisible(true);
            tutorialImageJ[0].setVisible(false);
        }   else if(e.getSource()==backButton[6]){
            screen[7].setVisible(false);
            screen[6].setVisible(true);
            tutorialImageJ[0].setVisible(true);
            tutorialImageJ[1].setVisible(false);
        }
    }
}