#include <iostream>
#include <stdio.h>
#include <string>

struct process{
  int burst = 0;
  int arrival = 0;
  int waitTime = 0;
};

int main(){
    //number of processes to carry out
    int procCount = 0;
    std::cout << "Enter number of processes: ";
    std::cin >> procCount;
    
    //process initialization
    process proc[procCount];
    for (int i = 0; i < procCount; i++){
        std::cout << "Enter burst value for process[" << i+1 << "]: ";
        std::cin >> proc[i].burst;
    }
        
    int waitTime = 0;

    int totalWaitTime = 0;
    //the current time
    int procTime = 0;
    
    //FCFS begins here
    
    std::cout << "Proc | Burst  | Arrival | Start | Complete | waitTime |" << std::endl ;
    
    for (int i = 0; i < procCount; i++){
        std::cout << "[" << i+1 <<"]  |   " << proc[i].burst << "   |   "<< proc[i].arrival << "     |   " << procTime;  
        //wait time for current process
        proc[i].waitTime = procTime - proc[i].arrival;
        //current process wait time added to total waitime
        totalWaitTime += proc[i].waitTime;
        //calculate when process ends
        procTime += proc[i].burst;

        std::cout << "   |   " << procTime << "      |     " << proc[i].waitTime << "    |     " << std::endl;

    }
    std::cout << "===========================================================" << std:: endl;
    double averageWaitTime = (double) totalWaitTime/procCount;
    
    for (int i = 0; i < procCount; i++){
    	std::cout << "proc[" << i+1 << "].waitTime = " << proc[i].waitTime << std::endl;
    	std::cout << std::endl;
    }
    std::cout << "===========================================================" << std:: endl;
    std::cout << "AVERAGE WAIT TIME = " << totalWaitTime << "/" << procCount << std::endl;
    std::cout << "AVERAGE WAIT TIME = " << averageWaitTime << std::endl;
    return 0;
}


