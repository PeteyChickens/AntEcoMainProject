import java.util.Random;


public class bug implements Runnable{

	int r_pos;
	int c_pos;
	int row;
	int col;
	boolean with_food;
	space[][] grid;
	boolean flag;
	int prev_r;
	int prev_c;
	int prev_r1;
	int prev_c1;
	
	boardFrame wind;
	
	public bug(int rowP, int colP, int x, int y, boardFrame frame)
	{
		flag = false;
		r_pos = rowP;
		c_pos = colP;
		with_food = false;
		grid = new space[x][y];
		row  = x;
		col = y;
		prev_r = rowP;
		prev_c = colP;
		prev_r1 = rowP;
		prev_c1 = colP;
		
		grid = frame.get_board();
		wind = frame;
		
	}
		
	public void run()
	{
		long startTime = System.nanoTime();
		long est  = 0;
		do{
			if(!with_food)
			{
				flag = false;
				int fol = g_rand(4);
				if(fol == 3) // not following
				{
					int dir = g_rand(4);
					if(dir ==0 && r_pos-1 >=0 && !grid[r_pos-1][c_pos].is_blocked  )
					{
						if(grid[r_pos-1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos-1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(with_food)
							{
								grid[r_pos][c_pos].scent++;
							}
						}
						
					}
					else if(dir ==1 && c_pos+1 <col && !grid[r_pos][c_pos+1].is_blocked)
					{
						if(grid[r_pos][c_pos+1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos+1]!= grid[prev_r1][prev_c1] )
						{
						
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(with_food)
							{
								grid[r_pos][c_pos].scent++;
							}
						}
					}
										}
					else if(dir ==2 && r_pos+1 <row && !grid[r_pos+1][c_pos].is_blocked)
					{
						if(grid[r_pos+1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos+1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(with_food)
							{
								grid[r_pos][c_pos].scent++;
							}
						}
						
					}
					else if(dir ==3 && c_pos-1 >=0 && !grid[r_pos][c_pos-1].is_blocked)
					{
						if(grid[r_pos][c_pos-1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos-1]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(with_food)
							{
								grid[r_pos][c_pos].scent++;
							}
						}
					}
				}
				else // following
				{
					double max = 0;
					int dir = 0;
					
					
					if(r_pos-1 >=0)
					{
											max = Math.max(grid[r_pos-1][c_pos].scent, max);
						if(max == grid[r_pos-1][c_pos].scent)
						{
							dir = 0;
							
						}
					}
					
					if(r_pos+1 < row)
					{
						max = Math.max(grid[r_pos+1][c_pos].scent, max);
						if(max == grid[r_pos+1][c_pos].scent)
						{
							dir = 2;
						}
					}
					if(c_pos-1 >= 0)
					{
						max = Math.max(grid[r_pos][c_pos-1].scent, max);
						if(max == grid[r_pos][c_pos-1].scent){
							dir = 3;
							
						}
					}
					if(c_pos+1 < col)
						{
							max = Math.max(grid[r_pos][c_pos+1].scent, max);
							if(max == grid[r_pos][c_pos+1].scent)
							{
								dir = 1;
								
							}
						}
				//coppy paset move code
					if(dir ==0 && r_pos-1 >=0 && !grid[r_pos-1][c_pos].is_blocked  )
					{
						if(grid[r_pos-1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos-1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
														else
							{
								grid[r_pos][c_pos].scent++;	
							}	
						}
						
					}
					else if(dir ==1 && c_pos+1 <col && !grid[r_pos][c_pos+1].is_blocked)
					{
						if(grid[r_pos][c_pos+1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos+1]!= grid[prev_r1][prev_c1] )
						{
						
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
					}
					else if(dir ==2 && r_pos+1 <row && !grid[r_pos+1][c_pos].is_blocked)
					{
						if(grid[r_pos+1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos+1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
						
					}
										else if(dir ==3 && c_pos-1 >=0 && !grid[r_pos][c_pos-1].is_blocked)
					{
						if(grid[r_pos][c_pos-1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos-1]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
					}
				}
			}	
			
			if(with_food)
			{
				flag = false;
				int fol = g_rand(4);
				if(fol == 3) // not following
				{
					int dir = g_rand(4);
					if(dir ==0 && r_pos-1 >=0 && !grid[r_pos-1][c_pos].is_blocked  )
					{
						if(grid[r_pos-1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos-1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
															grid[r_pos][c_pos].scent++;	
							}	
						}
						
					}
					else if(dir ==1 && c_pos+1 <col && !grid[r_pos][c_pos+1].is_blocked)
					{
						if(grid[r_pos][c_pos+1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos+1]!= grid[prev_r1][prev_c1] )
						{
						
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
					}
					else if(dir ==2 && r_pos+1 <row && !grid[r_pos+1][c_pos].is_blocked)
					{
						if(grid[r_pos+1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos+1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
												
					}
					else if(dir ==3 && c_pos-1 >=0 && !grid[r_pos][c_pos-1].is_blocked)
					{
						if(grid[r_pos][c_pos-1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos-1]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
					}
				}
				else // following
				{
					double max =0;
					int dir = 0;
					
					
					if(r_pos-1 >=0)
					{
						max = Math.max(grid[r_pos-1][c_pos].h_scent, max);
						if(max == grid[r_pos-1][c_pos].h_scent)
						{
							dir = 0;
							
						}
					}
					
					if(r_pos+1 < row)
					{
						max = Math.max(grid[r_pos+1][c_pos].h_scent, max);
						if(max == grid[r_pos+1][c_pos].h_scent )
						{
							dir = 2;
						}
					}
					
					if(c_pos+1 < col)
					{
											max = Math.max(grid[r_pos][c_pos+1].h_scent, max);
						if(max == grid[r_pos][c_pos+1].h_scent)
						{
							dir = 1;	
						}
					}
					if(c_pos-1 >= 0)
					{
						max = Math.max(grid[r_pos][c_pos-1].h_scent, max);
						if(max == grid[r_pos][c_pos-1].h_scent )
						{
							dir = 3;
							
						}
					}
					
				//coppy paset move code
					if(dir ==0 && r_pos-1 >=0 && !grid[r_pos-1][c_pos].is_blocked  )
					{
						if(grid[r_pos-1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos-1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}	
						}
						
					}
					else if(dir ==1 && c_pos+1 <col && !grid[r_pos][c_pos+1].is_blocked)
					{
						if(grid[r_pos][c_pos+1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos+1]!= grid[prev_r1][prev_c1] )
						{
						
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos +1;
							grid[r_pos][c_pos].add_ant();
														//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
					}
					else if(dir ==2 && r_pos+1 <row && !grid[r_pos+1][c_pos].is_blocked)
					{
						if(grid[r_pos+1][c_pos]!= grid[prev_r][prev_c] && grid[r_pos+1][c_pos]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							r_pos =r_pos +1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
						}
						
					}
					else if(dir ==3 && c_pos-1 >=0 && !grid[r_pos][c_pos-1].is_blocked)
					{
						if(grid[r_pos][c_pos-1]!= grid[prev_r][prev_c] && grid[r_pos][c_pos-1]!= grid[prev_r1][prev_c1] )
						{
							
							prev_r1 = prev_r;	
							prev_c1 = prev_c;
							prev_r = r_pos;	
							prev_c = c_pos;
							grid[r_pos][c_pos].remove_ant();
							c_pos =c_pos -1;
							grid[r_pos][c_pos].add_ant();
							//if(with_food&& grid[r_pos][c_pos].scent < grid[r_pos][c_pos].max_scent)
							if(!with_food)
							{
								grid[r_pos][c_pos].h_scent++;
							}
							else
							{
								grid[r_pos][c_pos].scent++;	
							}
													}
					}
				}
			}	
			
			if(grid[r_pos][c_pos].food_count > 0 && !with_food)
			{
				grid[r_pos][c_pos].food_count --;
				with_food = true;
			}
			if(grid[r_pos][c_pos].is_nest && with_food)
			{
				with_food = false;
				System.out.println("got food");
			    est= est+(System.nanoTime() - startTime);
			    double need = 1000000000;
			    double ms = est / need;
			    System.out.println("food got in: "+ms+" S");
			 
			    for(int i = 0; i < row; i++)
			    {
			    	for(int j = 0; j < col; j++)
			    	{
			    		grid[i][j].scent= ((grid[i][j].scent)*.9)*(ms);
			    	}
			    }
			    
			    startTime = System.nanoTime();
			}
			
				wind.repaint();
				try
				{
					Thread.sleep(5);
				}
				catch (InterruptedException e)
				{
					System.out.println("Problem?");
				}
				
			
		}while(true);
	}	
	
	public static int g_rand(int max)
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