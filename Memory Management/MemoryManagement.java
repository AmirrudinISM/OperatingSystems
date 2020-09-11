import java.util.Scanner;
import java.util.ArrayList;

class Process{
	public int procID = 0;
	public int size = 0;
	public boolean allocatedStatus = false;
};

class MemoryBlock{
	public int size = 0;
	public ArrayList<Process> processList;

};

class MemoryManagement{
	public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
		int procCount = 0;
    	//get number of processes
    	System.out.print("Enter number of processes: ");
    	procCount = input.nextInt();
    	//initialize process
    	Process [] processInst = new Process[procCount];
    	
    	for (int i = 0; i < procCount; i++){
    		processInst[i] = new Process();
    		processInst[i].procID = i+1;
    		System.out.print("Please enter size for proc["+processInst[i].procID+"]: ");
    		processInst[i].size = input.nextInt();
    	}

    	int blockCount = 0;
    	//get number of memory blocks
    	System.out.print("Enter number of memory blocks: ");
    	blockCount = input.nextInt();
    	//initialize memory blocks
    	MemoryBlock [] memoryBlockInst = new MemoryBlock[blockCount];

    	for (int i = 0; i < blockCount; i++){
    		memoryBlockInst[i] = new MemoryBlock();
    		System.out.print("Please enter size for memBlock["+(i+1)+"]: ");
    		memoryBlockInst[i].size = input.nextInt();
    		memoryBlockInst[i].processList = new ArrayList<Process>();
    	}

    	//every process
		for (int i = 0; i < procCount; i++){
			//every memory block
			for (int j = 0; j < blockCount; j++){
				//if process is less than memory hole
				if (processInst[i].size <= memoryBlockInst[j].size){
					//reduce the size of memory by size of process
					memoryBlockInst[j].size -= processInst[i].size;
					
					//change allocated status
					processInst[i].allocatedStatus = true;

					//push the process into memory block
					memoryBlockInst[j].processList.add(processInst[i]); 
					
					//breaks out of loop of checking every memory block as process has been allocated
					//and can now move on to the next process
					break;
				}
			}
		}

		//array list forming final list of partitioned memory
		ArrayList <Process> partition = new ArrayList<Process>();
		
		//for every memory block
		for (int i = 0; i < blockCount; i++){
			//if the block contains a process(es)
			if (memoryBlockInst[i].processList.size() > 0){
				//for every process already allocated in memory block
				for (int j = 0; j < memoryBlockInst[i].processList.size(); j++){
					//add to partition list
					partition.add(memoryBlockInst[i].processList.get(j));
				}
			}
			//if there's remaining memory space, add it to the partition list
			if (memoryBlockInst[i].size >= 0){
				//remaing memory is just empty space but
				//we need to treat it as process so that it
				//can be add to partition so that the type
				//matches
				Process temp = new Process();
				temp.size = memoryBlockInst[i].size;
				partition.add(temp);
			}
		}

		//Print partitions
		System.out.println("========================================");
		System.out.println("  Index  |  procID  |  size  |  status  ");
		for (int i = 0; i < partition.size(); i++){
			System.out.println("     "+i+"        "+ partition.get(i).procID+"         "+ partition.get(i).size+"      "+ partition.get(i).allocatedStatus);
		}

		//print unallocated processes
		System.out.println("------Unallocated processes---------");
		for (int i = 0; i < procCount; i++){
			if(processInst[i].allocatedStatus == false){
				System.out.println("processInst["+processInst[i].procID+"]");
			}
		}

		input.close();
	}

};