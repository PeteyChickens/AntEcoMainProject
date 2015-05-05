import java.util.*;

public class Ants
{
	public static void main(String[]args) throws InterruptedException
	{
			System.out.println("Hello World v 2.0");     // printstatement for debugging only
			
			int row = 60;							     // these variables are place holders for a larger grid later
			int col = 60;
			BoardFrame run = new BoardFrame(row, col);   //boardframe is where the nuts and bolts of drawing, as well as the movements will be done later
			
			bug2 swarm[] = new bug2[5];
			int h_posR = 13;
			int h_posC = 13;
	
			run.populate(h_posR, h_posC , (h_posR*2), (h_posC*2), swarm.length);	 //populate the grid with data, including walls
		
			Runnable b, c, d, e, f;
			/*b = new bug2(h_posR, h_posC, row, col, run);
			c = new bug2(h_posR, h_posC, row, col, run);
			d = new bug2(h_posR, h_posC, row, col, run);
			e = new bug2(h_posR, h_posC, row, col, run);
			f = new bug2(h_posR, h_posC, row, col, run);*/
			b = new AntAlgo(h_posR, h_posC+1, row, col, run, h_posR, h_posC);
			c = new AntAlgo(h_posR, h_posC-1, row, col, run, h_posR, h_posC);
			d = new AntAlgo(h_posR-1, h_posC, row, col, run, h_posR, h_posC);
			e = new AntAlgo(h_posR-1, h_posC-1, row, col, run, h_posR, h_posC);
			f = new AntAlgo(h_posR-1, h_posC, row, col, run, h_posR, h_posC);
			Thread t = new Thread (b);
			Thread u = new Thread (c);
			Thread v = new Thread (d);
			Thread w = new Thread (e);
			Thread x = new Thread (f);
			t.start();
			u.start();
			v.start();
			w.start();
			x.start();
			double ST = System.nanoTime();
			double totalT = 0;
			while(t.isAlive() && u.isAlive() && v.isAlive() && w.isAlive() && x.isAlive()) {
				Thread.sleep(1000);
			}
			totalT= (System.nanoTime() - ST);
			 double need = 1000000000; //divisor for seconds i think
			  totalT = totalT / need;
			  System.out.println("the run time is " +totalT);
			  
			 // System.exit(0);
	}
	public static int g_rand()
	{
		Random t = new Random();
		int m = t.nextInt();
		if(m < 0)
		{
			m = m*-1;
		}
		return m;
	}
}