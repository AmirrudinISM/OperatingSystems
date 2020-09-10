#include <iostream>
#include <vector>
/*
First-Fit memory management program

*/

//process to be allocated to memory
struct process{
	int procID;
	int size;
};

//block of memory with each having a vector to contain process
//allocated to it
struct memoryBlock{
	int size;
	std::vector<process>processList;
};


int main(int argc, char const *argv[]){
	//process initialization
	process processInstance[4];

	processInstance[0].procID = 1;
	processInstance[0].size = 225;
	
	processInstance[1].procID = 2;
	processInstance[1].size = 380;
	
	processInstance[2].procID = 3;
	processInstance[2].size = 80;
	
	processInstance[3].procID = 4;
	processInstance[3].size = 280;

	//memory initialization
	memoryBlock memoryBlockInstance[5];

	memoryBlockInstance[0].size = 100;
	memoryBlockInstance[1].size = 500;
	memoryBlockInstance[2].size = 200;
	memoryBlockInstance[3].size = 300;
	memoryBlockInstance[4].size = 600;

	//every process
	for (int i = 0; i < 4; i++){
		//every memory block
		for (int j = 0; j < 5; j++){
			//if process is less than memory hole
			if (processInstance[i].size <= memoryBlockInstance[j].size){
				//reduce the size of memory by size of process
				memoryBlockInstance[j].size -= processInstance[i].size;
				
				//push the process into memory block
				memoryBlockInstance[j].processList.push_back(processInstance[i]); 
				
				//breaks out of loop for checking every memory block as process has been allocated
				//and moves on to the next process
				break;
			}
		}
	}

	//vector forming final list of occupied & unoccupied memory
	std::vector<int> partition;
	//keeps track of which memory space has been allocated
	std::vector<bool> isOccupied;
	
	//create final list by accessing the process list contained in the memory block
	//and push process size into partition vector and occupied status in isOccupied
	for (int i = 0; i < 5; i++){
		
		if (memoryBlockInstance[i].processList.size() > 0){
			for (int j = 0; j < memoryBlockInstance[i].processList.size(); j++){
				//std::cout << memoryBlockInstance[i].processList[j].size << std::endl;
				partition.push_back(memoryBlockInstance[i].processList[j].size);
				isOccupied.push_back(true);
			}
		}
		//std::cout << memoryBlockInstance[i].size << std::endl;
		if (memoryBlockInstance[i].size >= 0){
			partition.push_back(memoryBlockInstance[i].size);
			isOccupied.push_back(false);	
		}
		 
	}

	//display final results
	for (int i = 0; i < partition.size(); i++){
		std::cout << i << " " << partition[i] << " " << isOccupied[i] << std::endl;
	}
	return 0;
}