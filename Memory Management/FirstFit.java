import java.util.Scanner;
import java.util.ArrayList;

//represents a process to be allocated in memory
class Process{
	public int procID = 0;
	public int size = 0;
	public boolean allocatedStatus = false;
};

//represents empty memory blocks that are available for allocation
class MemoryBlock{
	public int size = 0;
	//has an array list to contain Processes that can fit in it
	public ArrayList<Process> processList;

};


class FirstFit{
	public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
		
		//creates processes & prompts for size of each process
		int procCount = 0;
    	System.out.println("Enter number of processes: ");
    	procCount = input.nextInt();
    	Process [] processInst = processInitializer(procCount, input);
    	
    	//creates memory blocks & prompts size of each memory block
    	int blockCount = 0;
    	System.out.println("Enter number of memory blocks: ");
    	blockCount = input.nextInt();
    	MemoryBlock [] memoryBlockInst = memoryBlockInitializer(blockCount, input);
    	
    	//carry out first fit
    	memoryBlockInst = firstFit(processInst, procCount, memoryBlockInst, blockCount);

    	//forms final list of partitioned memory by containing allocated memory spaces
		ArrayList <Process> partition = partition(memoryBlockInst, blockCount);
		
		//displays allocated and unallocated memories
		printPartition(partition);
		printUnallocatedProcesses(processInst, procCount);
		
		input.close();

  	}

  	public static MemoryBlock [] firstFit(Process [] inpProc, int procNum, MemoryBlock [] inpMemBlock, int blockNum){
		//every process
		for (int i = 0; i < procNum; i++){
			//every memory block
			for (int j = 0; j < blockNum; j++){
				//if process is less than memory hole
				if (inpProc[i].size <= inpMemBlock[j].size){
					//reduce the size of memory by size of process
					inpMemBlock[j].size -= inpProc[i].size;
					
					//change allocated status
					inpProc[i].allocatedStatus = true;

					//add the process into available memory block
					inpMemBlock[j].processList.add(inpProc[i]); 
					
					//breaks out of loop of checking every memory block as process has been allocated
					//and can now move on to the next process
					break;
				}
			}
		}
		return inpMemBlock;
	}

  	public static Process [] processInitializer(int count, Scanner inp){
    	Process [] localProcess = new Process[count];
    	
    	for (int i = 0; i < count; i++){
    		localProcess[i] = new Process();
    		localProcess[i].procID = i+1;
    		System.out.println("Please enter size for proc["+localProcess[i].procID+"]: ");
    		localProcess[i].size = inp.nextInt();
    	}
    	return localProcess;
	}

	public static MemoryBlock [] memoryBlockInitializer(int count, Scanner inp){
    	MemoryBlock [] localmemoryBlock = new MemoryBlock[count];

    	for (int i = 0; i < count; i++){
    		localmemoryBlock[i] = new MemoryBlock();
    		System.out.println("Please enter size for memBlock["+(i+1)+"]: ");
    		localmemoryBlock[i].size = inp.nextInt();
    		localmemoryBlock[i].processList = new ArrayList<Process>();
    	}
    	return localmemoryBlock;
	}

	/*
	forms final list of partitioned memory by containing allocated memory spaces. This
	list treats unoccupied MemoryBlocks as Processes so that the type matches so that
	the unoccupied memory spaces can be added to the list
	*/
	public static ArrayList<Process> partition(MemoryBlock [] inpMemBlock, int blockNum){
		//array list forming final list of partitioned memory
		ArrayList <Process> localPartition = new ArrayList<Process>();
		
		//for every memory block
		for (int i = 0; i < blockNum; i++){
			//if the block contains a process(es)
			if (inpMemBlock[i].processList.size() > 0){
				//for every process already allocated in memory block
				for (int j = 0; j < inpMemBlock[i].processList.size(); j++){
					//add to partition list
					localPartition.add(inpMemBlock[i].processList.get(j));
				}
			}
			//if there's remaining memory space, add it to the partition list to represent unoccupied space
			if (inpMemBlock[i].size > 0){
				/*
				remaing memory is just empty space but we need to treat it as process so that it
				can be add to partition so that the type matches. The empty space's allocated status
				will always be false
				*/
				Process temp = new Process();
				temp.size = inpMemBlock[i].size;
				localPartition.add(temp);
			}
		}
		return localPartition;
	}

	public static void printPartition(ArrayList <Process> inputList){
		System.out.println("========================================");
		System.out.println("  Index  |  procID  |  size  |  status  ");
		for (int i = 0; i < inputList.size(); i++){
			System.out.println("     "+(i+1)+"        "+ inputList.get(i).procID+"         "+ inputList.get(i).size+"      "+ inputList.get(i).allocatedStatus);
		}
	}

	public static void printUnallocatedProcesses(Process [] inpProc, int count){
		System.out.println("--------Unallocated processes---------");
		for (int i = 0; i < count; i++){
			if(inpProc[i].allocatedStatus == false){
				System.out.println("processInst["+inpProc[i].procID+"] = " + inpProc[i].size);
			}
		}
	}

	
};