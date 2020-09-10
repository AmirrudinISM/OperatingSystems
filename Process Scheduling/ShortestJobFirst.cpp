#include <iostream>
#include <stdio.h>
#include <string>

struct process{
  int ID = 0;
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
        proc[i].ID = i+1;
        std::cout << "Enter burst value for process[" << proc[i].ID << "]: ";
        std::cin >> proc[i].burst;
    }

    process temp;
    //sort from smallest to largest process burst
    for (int i = 0; i < procCount; i++){
        for (int j = 0; j < procCount; j++){
            //swap
            if (proc[j].burst > proc[i].burst){
                temp = proc[j];
                proc[j] = proc[i];
                proc[i] = temp;
            }
        }
    }
    
    //waitTime = (start - arival)
    int waitTime = 0;
    //accumulated wait-time for all processes
    int totalWaitTime = 0;
    //the current time
    int procTime = 0;
    
    std::cout << "Proc | Burst  | Arrival | Start | Complete | waitTime |" << std::endl ;
    //SJF begins here
    for (int i = 0; i < procCount; i++){
        std::cout << "[" << proc[i].ID <<"]  |   " << proc[i].burst << "   |   "<< proc[i].arrival << "     |   " << procTime;  
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
    	std::cout << "proc[" << proc[i].ID << "] = " << proc[i].waitTime << std::endl;
    	std::cout << std::endl;
    }
    std::cout << "===========================================================" << std:: endl;
    std::cout << "AVERAGE WAIT TIME = " << totalWaitTime << "/" << procCount << std::endl;
    std::cout << "AVERAGE WAIT TIME = " << averageWaitTime << std::endl;
    return 0;
}


