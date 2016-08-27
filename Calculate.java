import java.awt.*;
import java.awt.image.BufferedImage;
public class Calculate{
    public static BufferedImage calculation(String choiceFractal, int choiceColour, int choiceIteration, BufferedImage fractalImage, int width, int height, double[] minCoord, double[] maxCoord){
        int iterationNo=0;
        double cImag = 0D;
        double cReal = 0D;
        double x =0D;
        double y =0D;

        //black orange colour scheme
        int[] colour = new int[choiceIteration];
        if(choiceColour==2){
            for(int i = 0; i<choiceIteration; i++) {
                colour[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
            }
        }

        for(int row=0; row<height; row++){//y
            for(int col=0; col<width; col++){//x
                // work out coordinates by using interpolation
                // state the two coordinates to 'create' a region which contains your fractal
                // then add a percentage of the differences between the two coordinates, add this to the smaller(smallest real part and highest imaginary part) coordinate
                // then iterate it through the specified formula

                switch(choiceFractal){
                    case "Julia -0.8 + 0.156i":         
                    
                    x = minCoord[0]+ (((double)col/(double)width)*((Math.abs(maxCoord[0]-minCoord[0])))); 
                    y = minCoord[1]+ (((double)row/(double)height)*((Math.abs(maxCoord[1]-minCoord[1]))));
                    cReal = -0.8;
                    cImag = 0.156;

                    break;
                    case "Julia -0.618":
                    
                    x = minCoord[0]+ (((double)col/(double)width)*(maxCoord[0]-minCoord[0])); 
                    y = minCoord[1]+ (((double)row/(double)height)*(maxCoord[1]-minCoord[1]));
                    cReal = -0.618;
                    cImag = 0;
                    
                    break;
                    case "Julia -0.4 + 0.6i":
                    
                    x = minCoord[0]+ (((double)col/(double)width)*(maxCoord[0]-minCoord[0])); 
                    y = minCoord[1]+ (((double)row/(double)height)*(maxCoord[1]-minCoord[1]));
                    cReal = -0.4;
                    cImag = 0.6;
                    
                    break;
                    case "Julia 0.285 - 0.01i":
                    
                    x = minCoord[0]+ (((double)col/(double)width)*(maxCoord[0]-minCoord[0])); 
                    y = minCoord[1]+ (((double)row/(double)height)*(maxCoord[1]-minCoord[1]));
                    cReal = 0.285;
                    cImag = -0.01;

                    break;
                    case "Mandelbrot Z^2":
                    case "Mandelbrot Z^3":
                    case "Mandelbrot Z^4":
                    case "Mandelbrot Z^5":

                    cReal = minCoord[0]+ (((double)col/(double)width)*(maxCoord[0]-minCoord[0]));
                    cImag = minCoord[1]+ (((double)row/(double)height)*(maxCoord[1]-minCoord[1]));
                    x = 0;
                    y = 0;

                    break;
                }

                iterationNo = 0;

                switch(choiceFractal){
                    case "Julia -0.8 + 0.156i":   
                    case "Julia -0.618":
                    case "Julia -0.4 + 0.6i":
                    case "Julia 0.285 - 0.01i":
                    case "Mandelbrot Z^2":
                    
                    iterationNo = mandZ2(cImag, cReal, x, y, iterationNo, choiceIteration);
                    
                    break;
                    case "Mandelbrot Z^3":
                    
                    iterationNo = mandZ3(cImag, cReal, x, y, iterationNo, choiceIteration);
                    
                    break;
                    case "Mandelbrot Z^4":
                    
                    iterationNo = mandZ4(cImag, cReal, x, y, iterationNo, choiceIteration);
                    
                    break;
                    case "Mandelbrot Z^5":
                    
                    iterationNo = mandZ5(cImag, cReal, x, y, iterationNo, choiceIteration);
                    
                    break;
                }
                fractalImage=colour(iterationNo, choiceIteration, choiceColour, fractalImage, col, row, colour);
            }
        }
        return fractalImage;
    }

    private static int mandZ2(double cImag, double cReal, double x, double y, int iterationNo, int choiceIteration){
        while((x*x)+(y*y) <=4.0 && iterationNo<=choiceIteration) {
            double xNew = (x*x)-(y*y)+ cReal;
            y = 2*x*y + cImag;
            x = xNew;
            iterationNo++;
        }
        return iterationNo;
    }

    private static int mandZ3(double cImag, double cReal, double x, double y, int iterationNo, int choiceIteration){
        while((x*x)+(y*y) <=4.0 && iterationNo<=choiceIteration) {
            double xNew =  x*((x*x)-(3*y*y))+ cReal;
            y = y*((3*x*x)-(y*y)) + cImag;
            x = xNew;
            iterationNo++;
        }
        return iterationNo;
    }

    private static int mandZ4(double cImag, double cReal, double x, double y, int iterationNo, int choiceIteration){
        while((x*x)+(y*y) <=4.0 && iterationNo<=choiceIteration) {
            double xNew = (x*x*x*x)-(6*x*x*y*y)+(y*y*y*y) + cReal;
            y = (4*x*y)*((x*x)-(y*y)) + cImag;
            x = xNew;
            iterationNo++;
        }
        return iterationNo;
    }

    private static int mandZ5(double cImag, double cReal, double x, double y, int iterationNo, int choiceIteration){
        while((x*x)+(y*y) <=4.0 && iterationNo<=choiceIteration) {
            double xNew = x*((x*x*x*x)-(10*x*x*y*y)+(5*y*y*y*y)) + cReal;
            y = y*((5*x*x*x*x)-(10*x*x*y*y)+(y*y*y*y))+ cImag;
            x = xNew;
            iterationNo++;
        }
        return iterationNo;
    }

    private static BufferedImage colour(int iterationNo, int choiceIteration, int choiceColour, BufferedImage fractalImage, int col, int row, int[] colour){
        if(choiceColour==1){
            //black&blue
            fractalImage.setRGB(col, row, (iterationNo) | iterationNo << 5);
        } else if(choiceColour==2){
            //black&orange
            if(iterationNo<choiceIteration){
                fractalImage.setRGB(col, row, colour[iterationNo]);
            } else{
                fractalImage.setRGB(col, row, 0x000000);
            }
        } else if(choiceColour==3){
            //black&white
            if(iterationNo<choiceIteration){
                fractalImage.setRGB(col, row, 0xFFFFFF); //white hex colour
            } else{
                fractalImage.setRGB(col, row, 0x000000); //black hex colour
            }
        }
        return fractalImage;
    }
}