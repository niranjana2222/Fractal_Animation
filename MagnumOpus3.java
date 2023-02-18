/**
 * Niranjana Sankar
 * MagnumOpus3.java
 * 
 * This program draws the different seasons: winter, spring, summer, and fall.
 * The winter season displays a lightning, tree, and snowflake fractals.
 * The spring season displays a sunflower, tree, and dragon fractals.
 * The summer season displays a tree, and dragon fractals.
 * The fall season displays a maple leaf, tree, and dragon fractals.
 * The program uses recursion and StdDraw with animation.  
 * */
import java.awt.Color;
import java.util.*;

public class MagnumOpus3  {

	//The limit for the detail of the dragon fractal.
	double detail=0.5;

    //The main method that calls the run method
    public static void main(String[] args) {
		MagnumOpus3 mag = new MagnumOpus3();
		mag.run();
    }
    
    //This method calls the methods to draw all seasons while the program runs
	public void run() 
	{
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setXscale(700, 0);
		StdDraw.setYscale(700, 0);
		
		while (true) {
			winter();
			spring();
			summer();
			fall();
		}
	}
	
	/*
	This method draws the winter season.
	It draws hills and randomly changes the background color.
	The method also calls methods that draw the ligthning, snowflake, and tree fractals.
	The method changes the x and y coordinates randomly to include animation.
	*/
	public void winter() 
	{
		for (int i = 0; i < 10; i++) {
			StdDraw.enableDoubleBuffering();
			
			StdDraw.clear(new Color((int)(Math.random()*21)+105, (int)(Math.random()*21+105), (int)(Math.random()*21+105)));
			StdDraw.setPenColor(Color.WHITE);
			drawLightning(300,0,200,700,150);

			//StdDraw.setPenColor(new Color(95, 133, 117));
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.filledCircle(350,1100,500);
			StdDraw.filledCircle(50,750,200);
			StdDraw.filledCircle(650,750,150);
		
			for (int j = 0; j < 200; j ++) {

				StdDraw.setPenColor(Color.BLACK);
			
				int y = (int)(Math.random()*300+50);
				Color color = new Color((int)(Math.random()*20+200), (int)(Math.random()*20+200), (int)(Math.random()*20+200));
				
				drawKoch(5,20+j*50,y+i*5,50+j*50,y+i*5, color);
				drawKoch(5,50+j*50,y+i*5,30+j*50,(y-30)+i*5, color);
				drawKoch(5,30+j*50,(y-30)+i*5,20+j*50,y+i*5, color);
				
				drawTree(400, 700, -90, 7, false);
				drawTree(200, 700, -90, 6, false);
				drawTree(500, 700, -90, 5, false);
				StdDraw.pause(1);
			}
			
			StdDraw.show();
			StdDraw.disableDoubleBuffering();
			StdDraw.pause(1);
		}
	}

	/*
	This method is the recursive fractal method to draw the trees in each season.
	The method takes in the x and y coordinates of the tree and the depth of the tree.
	*/
	public void drawTree(double x1, double y1, double angle, int depth, boolean pause) {
        StdDraw.setPenRadius(0.01);
		if (depth == 0) return;
        
		double x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        double y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);
        StdDraw.line(x1, y1, x2, y2);
        drawTree(x2, y2, angle - 20, depth - 1, pause);
        drawTree(x2, y2, angle + 20, depth - 1, pause);
		
		if (pause) {
			StdDraw.pause(4);
		}
	}
	
	/*
	This method is a recursive fractal method to draw the lightning randomly.
	This method takes in the x and y coordinates of the start and end of the lightning.
	*/	
	public void drawLightning(double x1, double y1, double x2, double y2, double disp)
	{
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(Color.YELLOW);
		double midX, midY;
		
		if (disp < detail)
			StdDraw.line(x1,y1,x2,y2);
		
		else {
			midX = (x1+x2)/2;
			midY = (y1+y2)/2;
			midX = midX + (Math.random()-0.5) * disp;
			midY = midY + (Math.random()-0.5) * disp;
			drawLightning(x1,y1,midX,midY,disp/2.0);
			drawLightning(x2,y2,midX,midY,disp/2.0);
		}
	}
	
	/*
	This method draws the spring season.
	It draws hills and a light blue color for the background.
	The method also calls methods that draw the sunflowers, dragon, and tree fractals.
	The method changes the x and y coordinates randomly to include animation.
	*/
	public void spring() 
	{
		ArrayList<double[]> points = new ArrayList<>();
		ArrayList<int[]> points2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) 
		{
			points.add(new double [] {Math.random()*1200-400, Math.random()*300});
			points2.add(new int [] {(int)(Math.random()*650+50), (int)(Math.random()*400+50)});
		}

		for (int j = 0; j < 15; j++) {
			StdDraw.enableDoubleBuffering();
			StdDraw.clear(new Color(137, 207, 240));
			StdDraw.setPenColor(new Color(175, 225, 175));
			StdDraw.filledCircle(350,1100,500);
			StdDraw.filledCircle(50,750,200);
			StdDraw.filledCircle(650,750,150);
			
			StdDraw.setPenColor(Color.MAGENTA);
			StdDraw.setPenRadius(0.01);
			
			drawTree(200, 700, -90, 2+j/4, false);
			drawTree(500, 700, -90, 3+j/3, false);
			drawTree(400, 700, -90, 3+j/2, false);
		
			for (int i = 0; i < 10; i++) {
				sunflower(points.get(i)[0]+(int)(Math.random()*80), points.get(i)[1]+ j*70,1, (int)(Math.random()*5+2));
				wind(points2.get(i)[0]-j*20, points2.get(i)[1], (int)(Math.random()+13));
			}
			StdDraw.show();
			StdDraw.disableDoubleBuffering();
			StdDraw.pause(100);
			if (j>10) StdDraw.pause(300);
		}
	}

	/*
	This method calls the recursive fractal method to draw the dragon fractal.
	It chooses the number of iterations--the depth--of the fractal
	*/
	public void wind(int x1, int y1, int iter2) {
        StdDraw.setPenColor(Color.WHITE);
        int iter = iter2;
        List<Integer> turns = getSequence(8);
        double startingAngle = -iter * (Math.PI / 4);
        double side = 400 / Math.pow(2, iter / 2.);
		drawWind(x1, y1, side, startingAngle, turns);
    }

	/*
	This method creates an arrayList of the coordinates needed for the 
	dragon fractal. It is based on the depth of the fractal.
	*/
    public List<Integer> getSequence(int iterations) {
        List<Integer> turnSequence = new ArrayList<Integer>();
        
        for (int i = 0; i < iterations; i++) {
            List<Integer> copy = new ArrayList<Integer>(turnSequence);
            Collections.reverse(copy);
            turnSequence.add(1);
            
            for (Integer turn : copy) {
                turnSequence.add(-turn);
            }
        }
        return turnSequence;
    }

	/*
	This method is the recursive fractal method to draw the dragon fractals or wind.
	This method takes in the x and y coordinates and the arrayList of the turns.
	*/
    public void drawWind(int x1, int y1, double side, double startingAngle, List<Integer> turns) {
        double angle = startingAngle;
        StdDraw.setPenRadius(0.008);
        
        int x2 = x1 + (int) (Math.cos(angle) * side);
        int y2 = y1 + (int) (Math.sin(angle) * side);
        StdDraw.line(x1, y1, x2, y2);
        x1 = x2;
        y1 = y2;
        for (Integer turn : turns) {
            angle += turn * (Math.PI / 2);
            x2 = x1 + (int) (Math.cos(angle) * side);
            y2 = y1 + (int) (Math.sin(angle) * side);
            StdDraw.line(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }

	/*
	This method draws the maple leaves in the fall season.
	It takes in the x and y coordinates and size of the leaf.
	It creates an array of points of the maple leaf before drawing it.
	*/
	public void mapleLeaf(double x, double y, double size) {
		 Point[] vertices = {
			new Point(50*size, 110*size), new Point(50*size, 90*size), new Point(30*size, 90*size),
			new Point(0*size, 70*size), new Point(10*size, 60*size), new Point(0*size, 50*size),
			new Point(20*size, 40*size), new Point(10*size, 30*size), new Point(40*size, 50*size),
			new Point(30*size, 10*size), new Point(40*size, 20*size), new Point(50*size, 0*size),
			new Point(60*size, 0*size), new Point(70*size, 20*size), new Point(80*size, 10*size),
			new Point(70*size, 50*size), new Point(100*size, 30*size), new Point(90*size, 40*size),
			new Point(110*size, 50*size), new Point(100*size, 60*size), new Point(110*size, 70*size),
			new Point(80*size, 90*size), new Point(60*size, 90*size), new Point(60*size, 110*size),
			new Point(50*size, 110*size)
		};
		
		double [] X = new double[25];
		double [] Y = new double[25];
		int i = 0;
		for (Point p: vertices) 
			{
				X[i] = x+p.getX();
				Y[i++] = y+p.getY();
			}
			StdDraw.filledPolygon(X,Y);
	}

	/*
	This recursive method draws the sunflower fractals in the spring season.
	This method takes in the width and height of the sunflower and the depth of the fractal.
	*/
	public void sunflower(double winWidth, double winHeight, double diskRatio, int iter){
		double factor = .5 + Math.sqrt(1.25);
		double r;
		double theta;
		double x = winWidth/2.0+200;
		double y = winHeight/2.0+200;
	
		StdDraw.setPenColor(new Color((int)(Math.random()*100+100), (int)(Math.random()*100+100), (int)(Math.random()*100+100)));
		
		for(int i = 0; i <= iter; i++) {
			 r = Math.pow(i,factor)/iter;
			
			theta = 2*Math.PI*factor*i;
			StdDraw.setPenRadius(0.01);
			StdDraw.circle(x + r*Math.sin(theta), y + r*Math.cos(theta), 15 * i/(1.0*iter));
		}
	}
	
	//draws summer season 
	public void summer() 
	{
		ArrayList<int[]> points2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) 
		{
			points2.add(new int [] {(int)(Math.random()*600+50), (int)(Math.random()*400+50)});
		}
		for (int j = 0; j < 15; j++) {
			StdDraw.enableDoubleBuffering();
			StdDraw.clear(new Color(0, 150, 255));
			StdDraw.setPenColor(new Color(0, 128, 0));
		
			//StdDraw.filledRectangle(0,650,700,50);
			StdDraw.filledCircle(350,1100,500);
			StdDraw.filledCircle(50,750,200);
			StdDraw.filledCircle(650,750,150);

			StdDraw.setPenColor(Color.YELLOW);
			StdDraw.filledCircle(70,70,30);
			
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.01);
		
			drawTree(200, 700, -90, 2+j/4, false);
			drawTree(500, 700, -90, 4+j/2, false);
			drawTree(400, 700, -90, 3+j/3, true);

			for (int i = 0; i < 10; i++) 
			wind(points2.get(i)[0]+j*20, points2.get(i)[1], (int)(Math.random())+13);
			
			StdDraw.show();
			StdDraw.disableDoubleBuffering();
			StdDraw.pause(50);
		}
	}
	
	/*
	This method draws the fall season.
	It draws hills and chooses a blue color for the background.
	The method also calls methods that draw the maple leafs, dragon, and tree fractals.
	The method changes the x and y coordinates randomly to include animation.
	*/
	public void fall() 
	{
		ArrayList<int[]> points2 = new ArrayList<>();
		ArrayList<double[]> points = new ArrayList<>();
		for (int i = 0; i < 10; i++) 
		{
			points2.add(new int [] {(int)(Math.random()*350+50), (int)(Math.random()*200+50)});
			points.add(new double[] {Math.random()*500+100, Math.random()*200+300, Math.random()*.5});
		}
		for (int k = 0; k < 15; k++) {
			StdDraw.enableDoubleBuffering();
			StdDraw.clear(new Color(115, 147, 179));
			StdDraw.setPenColor(new Color(138, 154, 91));
			
			//StdDraw.filledRectangle(0,650,700,50);
			StdDraw.filledCircle(350,1100,500);
			StdDraw.filledCircle(50,750,200);
			StdDraw.filledCircle(650,750,150);
			
			StdDraw.setPenColor(Color.ORANGE);
			StdDraw.setPenRadius(0.01);
			
			drawTree(200, 700, -90, 6, false);
			drawTree(500, 700, -90, 6, false);
			drawTree(400, 700, -90, 7, true);

			for (int i = 0; i < 10; i++) {
				StdDraw.setPenColor(new Color(204, 85, 0));
				if (k>=8) mapleLeaf(points.get(i)[0]+(int)(Math.random()*80), points.get(i)[1]+k*20, points.get(i)[2]);
				if (i%2==0 && k <8) wind(points2.get(i)[0]-k*40, points2.get(i)[1], 14);
			}
			
			StdDraw.show();
			StdDraw.disableDoubleBuffering();
			//StdDraw.pause(5);
		}
	}
	
	/*
	This recursive method draws the snowflakes in the winter season.
	This method takes in the depth of teh snowflake, color, and coordinates.
	*/
    public void drawKoch ( int level, int a1, int b1, int a5, int b5, Color color){
		StdDraw.setPenRadius(.01);
		
		StdDraw.setPenColor(color);
        int delX, delY, a2, b2, a3, b3, a4, b4;
        if (level == 0){
            StdDraw.line(a1, b1, a5, b5);
        }
        else{
            delX = a5 - a1;
            delY = b5 - b1;
            a2 = a1 + delX / 3;
            b2 = b1 + delY / 3;
            a3 = (int) (0.5 * (a1+a5) + Math.sqrt(3) * (b1-b5)/6);
            b3 = (int) (0.5 * (b1+b5) + Math.sqrt(3) * (a5-a1)/6);
            a4 = a1 + 2 * delX /3;
            b4 = b1 + 2 * delY /3;
            drawKoch (level-1, a1, b1, a2, b2, color);
            drawKoch (level-1, a2, b2, a3, b3, color);
            drawKoch (level-1, a3, b3, a4, b4, color);
            drawKoch (level-1, a4, b4, a5, b5, color);
        }
    }

}

/*
Point.class
This class creates a point object that stores the x and y coordiante
*/
class Point 
{
	// The x and y coordiante of the point
	double x, y;

	/*
	Sets the x and y coordinates of the Point object.
	*/
	public Point (double X, double Y) { x = X; y = Y; }

	//This method returns x coordinate
	public double getX(){ return x; }

	//This method returns y coordinate
	public double getY() { return y; }
}
