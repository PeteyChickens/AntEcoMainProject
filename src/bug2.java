import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class bug2 implements Runnable{

	int r_pos;
	int c_pos;
	int row;
	int col;
	boolean with_food;
	Space[][] grid;
	boolean flag;
	int prev_r;
	int prev_c;
	int prev_r1;
	int prev_c1;
	
	BoardFrame wind;
	
	ArrayList <Space> path; 
	
	public bug2(int rowP, int colP, int x, int y, BoardFrame frame)
	{
		flag = false;
		r_pos = rowP;
		c_pos = colP;
		with_food = false;
		grid = new Space[x][y];
		row  = x;
		col = y;
		prev_r = rowP;
		prev_c = colP;
		prev_r1 = rowP;
		prev_c1 = colP;
		
		grid = frame.get_board();
		wind = frame;
		path = new ArrayList<Space>();
		
	}
	public void run()
	{
		double ST = System.nanoTime();
		double totalT = 0;
		int count  = 0;
		while(count <= 1000)
		{
					
			path.add(grid[r_pos][c_pos]);
			int i = 0;
			long startTime = System.nanoTime();
			
			long est = 0;
			do{
				r_pos = path.get(i).row;
				c_pos = path.get(i).col;
				path.add(det_dir(r_pos, c_pos, i));
				
				i++;
				if(grid[r_pos][c_pos].food_count > 0 && !with_food)
				{
					grid[r_pos][c_pos].food_count --;
					
					with_food = true;
					est= est+(System.nanoTime() - startTime);
				}
			}while(grid[r_pos][c_pos].food_count == 0);
			 double need = 1000000000; //divisor for seconds i think
			 double s = est / need;
		
			for(int j = 0; j <=i; j++)
			{
				
				grid[path.get(j).row][path.get(j).col].nScent = grid[path.get(j).row][path.get(j).col].nScent+(s);
			}
	
			double max = 0;
			double min = 300;
			for(int k = 0; k < row; k++)
			{
				for(int j = 0 ; j < col; j++)
				{
					if(max < grid[k][j].nScent)
						max = grid[k][j].nScent;
					if(min > grid[k][j].nScent)
						min = grid[k][j].nScent;
				}
			}
			
			for(int k = 0; k < row; k++)
			{
				for(int j = 0 ; j < col; j++)
				{
					grid[k][j].nScent = ((grid[k][j].nScent)/min)*((max - min)/max)*.95;
					if(grid[k][j].nScent <=0)
					{
						grid[k][j].nScent = ((max - min)/max);
					}
					if(grid[k][j].nScent >600)
										{
						grid[k][j].nScent = 600;
					}
					
				}
			}
			wind.repaint();
			
			with_food = false;
				try
				{
					
					Thread.sleep(15);
				}
				catch (InterruptedException e)
				{
					System.out.println("Problem?");
				}
		
			count++;	
				
		}	
		System.out.println(grid[row-1][col-1].food_count);
			
	}
	public Space det_dir(int x, int y, int i)
	{
		//x and y are the current position. this function figures out where to go next
		
		int r = -1;
		int c = -1;
		Space[] possible = new Space[4];
		
			if(x-1 >= 0)
			{
				if(!grid[x-1][y].is_blocked)
				{
					possible[0] = grid[x-1][y];
				}
				else
				{
					possible[0] = grid[x][y];
				}
			}
			else
			{
				if(!grid[row-1][y].is_blocked) {
					possible[0] = grid[row-1][y];
				}
				else {
					possible[0] = grid[x][y];
				}
			}
			if(y+1 < col)
			{
				if(!grid[x][y+1].is_blocked)
				{
					possible[1] = grid[x][y+1];
				}
				else
				{
					possible[1] = grid[x][y];
				}
			}
			else
			{
				if(!grid[x][0].is_blocked) {
					possible[1] = grid[x][0];
				}
				else {
					possible[1] = grid[x][y];
				}
			}
			if(x+1 < row)
			{
				if(!grid[x+1][y].is_blocked)
				{
					possible[2] = grid[x+1][y];
				}
				else
				{
					possible[2] = grid[x][y];
				}//	System.out.println(r + " " +c);
			}
			else
			{
				if(!grid[0][y].is_blocked) {
					possible[2] = grid[0][y];
				}
				else {
					possible[2] = grid[x][y];
				}
			}
			if(y-1 >=0)
			{
				if(!grid[x][y-1].is_blocked)
				{
					possible[3] = grid[x][y-1];
				}
				else
				{
					possible[3] = grid[x][y];
				}
			}
			else
			{
				if(!grid[x][col-1].is_blocked) {
					possible[3] = grid[x][col-1];
				}
				else {
					possible[3] = grid[x][y];
				}
			}
			boolean been = false;
			do
			{
				double chance = grid[x][y].nScent;
				double go = g_rand(chance);
					
				if(go <= chance/4)
				{
					int pick = g_int(4);
					r = possible[pick].row;
					c = possible[pick].col;
				}
				else
								{
					
					int index = 0;
					
					for(int k = 0; k < possible.length; k++)
					{
						
						if(possible[index].nScent< possible[k].nScent)
						{
							index = k;
						}
										
					}
					r = possible[index].row;
					c = possible[index].col;
					
				}
		
				
			}while(been);
		
	
		//System.out.println(Thread.currentThread());
		return grid[r][c];
	}
	
	
	public static double g_rand(double max)
	{
		Random t = new Random();
		double m;
		do{
			m = t.nextDouble();
			if(m < 0)
			{
				m = m*-1;
			}
		}while(m > max);
		return m;
	}
	
	public static int g_int(int max)
	{
		Random t = new Random();
		int m = t.nextInt(max);
		if(m < 0)
		{
			m = m*-1;
		}
		return m;
	}
	
}