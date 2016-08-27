import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ComboBoxDemoProject/src/components/ComboBoxDemo.java
public class Input implements ActionListener{
    private JFrame[] inputScreen= new JFrame[4];
    private JLabel[] titleJ = new JLabel[4];
    private JButton[] backButton = new JButton[4];
    private JButton[] continueButton = new JButton[4];

    private String[] fractalOption = new String[8];
    private JComboBox<String> comboBox;
    private JTextField textField = new JTextField(10);
    private JRadioButton bbButton, boButton, bwButton, yesButton, noButton;

    protected String selectedFractal;
    protected int iterationNo, tutorialValue, colourScheme;

    protected JButton inputComplete = new JButton("Complete");
    public void getValues(){
        //set up the windows for the user to input values
        resetValues();        
        setText();
        setFont();
        inputFractal();
        inputTutorial();
        inputColour();
        inputIteration();
        //make the input fractal screen visible
        inputScreen[0].setVisible(true);
    }

    //if this class has been run more than once
    public void getValues(int i){
        inputScreen[0].setVisible(true);
    }

    public void resetValues(){
        // resets the values
        selectedFractal = "Mandelbrot Z^2";
        tutorialValue = 0;
        colourScheme = 1;
        iterationNo= 1000;
    }

    public void setText(){
        // set the text for the jlabels and radio buttons
        fractalOption[0] = "Mandelbrot Z^2";
        fractalOption[1] = "Mandelbrot Z^3";
        fractalOption[2] = "Mandelbrot Z^4";
        fractalOption[3] = "Mandelbrot Z^5";
        fractalOption[4] = "Julia -0.8 + 0.156i";
        fractalOption[5] = "Julia -0.618";
        fractalOption[6] = "Julia -0.4 + 0.6i";
        fractalOption[7] = "Julia 0.285 - 0.01i";

        bbButton = new JRadioButton("Black & Blue");
        boButton = new JRadioButton("Black & Orange");
        bwButton = new JRadioButton("Black & White");  

        yesButton = new JRadioButton("Yes");
        noButton = new JRadioButton("No");

        for(int i = 0;i<=3;i++){
            backButton[i] = new JButton("Back");
            if(i==3){
                continueButton[i] = new JButton("Generate");
            }else{
                continueButton[i] = new JButton("Continue");
            }
        }
        titleJ[0] = new JLabel("   Please select a fractal:   ");
        titleJ[1] = new JLabel("Would you like a tutorial?");
        titleJ[2] = new JLabel("Please select a colour scheme: ");
        titleJ[3] = new JLabel("Please type in the number of iterations: ");
    }

    public void setFont(){
        //set the font size for the titles of the screens
        for(int i = 0;i<=3;i++){
            titleJ[i].setFont(new Font("", Font.BOLD, 25));
        }
    }

    public void inputFractal(){
        //set up screen to input the fractal type
        inputScreen[0] = new JFrame("Fractal Generator-Selection");
        // make program stop if you click on the close button
        inputScreen[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make window non resizeable
        inputScreen[0].setResizable(false);
        // make window appear in the center of the screen
        inputScreen[0].setLocationRelativeTo(null);
        // make a combo box with fixed options
        comboBox = new JComboBox<String>(fractalOption);
        // make default option  "Mandelbrot Z^2"
        comboBox.setSelectedIndex(0);

        setLayout(inputScreen[0].getContentPane(), 0);
        inputScreen[0].pack();
    }

    public void inputTutorial(){
        //set up screen to input whether the user wants a tutorial or not
        inputScreen[1] = new JFrame("Fractal Generator- Tutorial");
        // make program stop if you click on the close button
        inputScreen[1].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make window non resizeable
        inputScreen[1].setResizable(false);
        // make window appear in the center of the screen
        inputScreen[1].setLocationRelativeTo(null);

        setLayout(inputScreen[1].getContentPane(), 1);
        inputScreen[1].pack();
    }

    public void inputColour(){
        //set up screen to input the colour scheme
        inputScreen[2] = new JFrame("Fractal Generator- Colour Scheme");
        // make program stop if you click on the close button
        inputScreen[2].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make window non resizeable
        inputScreen[2].setResizable(false);
        // make window appear in the center of the screen
        inputScreen[2].setLocationRelativeTo(null);

        setLayout(inputScreen[2].getContentPane(), 2);
        inputScreen[2].pack();
    }

    public void inputIteration(){
        //set up screen to input iteration
        inputScreen[3] = new JFrame("Fractal Generator- Iteration number");
        // make program stop if you click on the close button
        inputScreen[3].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make window non resizeable
        inputScreen[3].setResizable(false);
        // make window appear in the center of the screen
        inputScreen[3].setLocationRelativeTo(null);
        //auto set textfield to 1000
        textField.setText("100");

        setLayout(inputScreen[3].getContentPane(), 3);
        inputScreen[3].pack();
    }

    public void setLayout(Container pane, int i){
        // create a new layout
        GridBagConstraints c = new GridBagConstraints();
        pane.setLayout(new GridBagLayout());
        pane.removeAll();

        //add titles
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 5;
        c.gridheight = 1;
        c.gridx=0;
        c.gridy=0;
        pane.add(titleJ[i], c);
        switch(i){
            case 0:
            // Layout for the input fractal window

            // add drop down box
            c.gridy=1;
            c.insets= new Insets(10,10,10,10);
            pane.add(comboBox, c);

            //add continue button
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx=2;
            c.gridy++;
            c.anchor = GridBagConstraints.PAGE_END;
            c.gridx=4;
            pane.add(continueButton[0], c);

            //add actionlistener
            comboBox.addActionListener(this);
            break;
            case 1:
            //Layout for the input tutorial window

            // add radio buttons
            c.anchor = GridBagConstraints.LINE_START;
            c.gridy=1;
            c.insets= new Insets(5,7,0,0);
            pane.add(yesButton, c);
            c.gridy=2;
            c.insets= new Insets(3,7,0,0);
            pane.add(noButton, c);

            // add back/continue buttons
            c.anchor = GridBagConstraints.LAST_LINE_START;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx=2;
            c.gridy++;
            c.insets= new Insets(5,0,0,0);
            pane.add(backButton[0], c);
            c.anchor = GridBagConstraints.PAGE_END;
            c.gridx=4;
            c.insets= new Insets(0,155,0,0);
            pane.add(continueButton[1], c);

            //add action listeners
            yesButton.addActionListener(this);
            noButton.addActionListener(this);

            break;
            case 2:
            //Layout for the input colour scheme window

            // add radio buttons
            c.anchor = GridBagConstraints.LINE_START;
            c.gridy=1;
            c.insets= new Insets(5,7,0,0);
            pane.add(bbButton, c);
            c.gridy=2;
            c.insets= new Insets(3,7,0,0);
            pane.add(boButton, c);
            c.gridy=3;
            c.insets = new Insets(3,7,0,0);
            pane.add(bwButton, c);

            //add back/continue buttons
            c.anchor = GridBagConstraints.LAST_LINE_START;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx=2;
            c.gridy++;
            c.insets= new Insets(5,0,0,0);
            pane.add(backButton[1], c);
            c.anchor = GridBagConstraints.PAGE_END;
            c.gridx=4;
            c.insets= new Insets(0,270,0,0);
            pane.add(continueButton[2], c);

            //add actionlisteners
            bbButton.addActionListener(this);
            boButton.addActionListener(this);
            bwButton.addActionListener(this);

            break; 
            case 3:
            //Layout for the input iteration number window

            //add text field for iteration number
            c.anchor = GridBagConstraints.CENTER;
            c.gridy=1;
            c.insets= new Insets(10,0,0,0);
            pane.add(textField, c);

            //add back/continue buttons
            c.anchor = GridBagConstraints.LAST_LINE_START;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.gridx=2;
            c.gridy++;
            c.insets= new Insets(5,0,0,0);
            pane.add(backButton[2], c);
            c.anchor = GridBagConstraints.PAGE_END;
            c.gridx=4;
            c.insets= new Insets(0,330,0,0);
            pane.add(continueButton[3], c);

            break;
        }

        //add action listeners to the buttons
        continueButton[i].addActionListener(this);
        if(i!=0){
            backButton[i-1].addActionListener(this);
        }
    }

    public static boolean validateIteration(String iterationNo){
        // check if input is a number
        if(iterationNo.equals("")){
            return false;
        }
        for(int i=0;i<(iterationNo.length());i++){
            if( (((int) iterationNo.charAt(i))>=48) && ((int)(iterationNo.charAt(i))<=57) ){
                //valid
            } else{
                //invalid
                return false;
            }
        }
        //check the the number is valid, positive and less than 10000
        if (iterationNo.length()>4){
            return false;
        }
        if((Integer.parseInt(iterationNo))==0){
            return false;
        }
        return true;
    }

    public static void errorMessage(String message){
        //JPanel error
        String errorString = "You have not "+message+" Please try again.";
        JOptionPane.showMessageDialog(null, errorString, "Error", 1);
    }

    public void actionPerformed(ActionEvent e) {
        // make sure only one radio button is selected
        if(e.getSource()== yesButton){
            noButton.setSelected(false);	
        } else if(e.getSource()== noButton){
            yesButton.setSelected(false);
        }
        // make sure only one radio button is selected
        if(e.getSource()== bbButton){
            boButton.setSelected(false);	
            bwButton.setSelected(false);	
        } else if(e.getSource()== boButton){
            bbButton.setSelected(false);	
            bwButton.setSelected(false);	
        } else if(e.getSource()== bwButton){
            bbButton.setSelected(false);	
            boButton.setSelected(false);	
        }

        // get the fractal type from the combo box
        if(e.getSource()==comboBox){
            // get fractal type from action event
            String cb = String.valueOf(e.getSource());
            selectedFractal = cb.substring(21 + cb.indexOf("selectedItemReminder"), cb.length()-1);
        }

        // if the back button is clicked
        if(e.getSource()==backButton[0]){
            // on tutorial screen
            inputScreen[1].setVisible(false);
            inputScreen[0].setVisible(true);
        } else if(e.getSource()==backButton[1]){
            //on colour scheme screen
            inputScreen[2].setVisible(false);
            if(selectedFractal.equals("Mandelbrot Z^2")){
                //go back to tutorial screen
                inputScreen[1].setVisible(true);
            }else{
                // go back to fractal type screen
                inputScreen[0].setVisible(true);
            }
        } else if(e.getSource()==backButton[2]){
            // on iteration number screen
            inputScreen[3].setVisible(false);
            inputScreen[2].setVisible(true);
        }

        //if the next/continue button is clicked
        if(e.getSource()==continueButton[0]){
            // on fractal type screen
            inputScreen[0].setVisible(false);
            if(selectedFractal.equals("Mandelbrot Z^2")){
                // make input tutorial screen visible
                inputScreen[1].setVisible(true);
            }else{
                // make input colour scheme visible
                inputScreen[2].setVisible(true);
            }
        } else if(e.getSource()==continueButton[1]){
            // on tutorial screen
            if(yesButton.isSelected() || noButton.isSelected()){
                // if option is valid
                if(yesButton.isSelected()==true){
                    // 1 means yes
                    tutorialValue=1;
                }else if(noButton.isSelected()==true){
                    // 0 means no
                    tutorialValue=0;
                }
                inputScreen[1].setVisible(false);
                inputScreen[2].setVisible(true);
            }else{
                // if option is not valid
                // make error message appear and make the tutorial screen visible again
                inputScreen[1].setVisible(false);
                errorMessage("selected an option from the tutorial.");
                inputScreen[1].setVisible(true);
            }
        } else if(e.getSource()==continueButton[2]){
            // on colour scheme screen
            if(bbButton.isSelected() || boButton.isSelected()|| bwButton.isSelected()){
                //if option is valid
                if(bbButton.isSelected()){
                    // 1 for black and blue
                    colourScheme= 1;
                } else if(boButton.isSelected()){
                    // 2 for black and orange
                    colourScheme = 2;
                } else if(bwButton.isSelected()){
                    // 3 for black and white
                    colourScheme = 3;
                }
                // make input iteration visible
                inputScreen[2].setVisible(false);
                inputScreen[3].setVisible(true);
            }else{
                // if option is not valid
                // make error message appear and make the input colour scheme screen visible again
                inputScreen[2].setVisible(false);
                errorMessage("selected an option from the colour scheme.");
                inputScreen[2].setVisible(true);
            }
        } else if(e.getSource()==continueButton[3]){
            //on iteration number screen
            //validate number
            if(validateIteration((textField.getText()))==true){
                // if number is valid
                // FINISHES HERE
                iterationNo= Integer.valueOf(textField.getText());
                inputScreen[3].setVisible(false);
                inputComplete.doClick();
            }else{
                // if option is not valid
                // make error message appear and make the input tutorial screen visible again
                inputScreen[3].setVisible(false);
                errorMessage("input a valid number for the number of iterations.");
                inputScreen[3].setVisible(true);
            }
        }
    }

}