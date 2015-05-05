import mpi.*;

public class AntsMPI {
	
	public static void main(String[] args) throws Exception {
		
		MPI.Init(args);		//Initialize MPI
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		MPI.Finalize();
	}

}
