#include <iostream>
#include <stdio.h>
#include <string>

struct process{
  int burst = 0;
  int arrival = 0;
  int waitTime = 0;
  std::string waitList = "";
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

    int quantum = 0;
    std::cout << "Enter quantum number: ";
    std::cin >> quantum;

    //cycle counter
    int i = 0;
    //number of processes completed
    int completionCount = 0;
    
    int waitTime = 0;

    int totalWaitTime = 0;
    //the current time
    int procTime = 0;
    
    //Round Robin begins here
    std::cout << "Proc | Burst | Arrival | Start | Complete | waitTime | Remainder " << std::endl ;
    //keep looping while there are incomplete processes
    while(completionCount < procCount){
        //a process is still not done
        if (proc[i].burst > 0){
        	//process | burst | arrival | start | end | waiting | remainder

            std::cout << "[" << i+1 <<"]  |   " << proc[i].burst << "   |   "<< proc[i].arrival << "     |   " << procTime;  
            //calculate total waitTime = (start time - arrival time) 
            waitTime = procTime - proc[i].arrival;
            
            totalWaitTime += waitTime;
            proc[i].waitTime += waitTime;
            proc[i].waitList += std::to_string(waitTime) + ", ";
            
            //passage of time after acting on a process
            //adds burst time if burst time is less than quantum
            //adds quantum time instead if burst value is greater, so that every task is treated equally
            //without prioritizing any task, thus captures the concept of round robin
            if (proc[i].burst < quantum){
                procTime += proc[i].burst;
            }
            else{
                procTime += quantum;
            }
            
            
            //new arrival time for the current process for the next cycle
            proc[i].arrival = procTime;
            //process has been carried out
            if (proc[i].burst > quantum ){
            	proc[i].burst -= quantum;
            }
            else{
            	proc[i].burst = 0;
            }

            std::cout << "   |   " << procTime << "      |     " << waitTime << "    |     " << proc[i].burst << std::endl;
            
            //check if process is complete
            if (proc[i].burst <= 0){
                completionCount++;
            }
            
        }
        if (i == (procCount-1)){
            std::cout << "==================================================================" << std:: endl;
        }
        //the cycle counter
        i++;
        i = (i % procCount);
    }
    
    double averageWaitTime = (double) totalWaitTime/procCount;
    
    for (int i = 0; i < procCount; i++){
    	std::cout << "proc[" << i+1 << "].waitList = " << proc[i].waitList << std::endl;
    	std::cout << "proc[" << i+1 << "].waitTime = " << proc[i].waitTime << std::endl;
    	std::cout << std::endl;
    }
    std::cout << "===========================================================" << std:: endl;
    std::cout << "AVERAGE WAIT TIME = " << totalWaitTime << "/" << procCount << std::endl;
    std::cout << "AVERAGE WAIT TIME = " << averageWaitTime << std::endl;
    return 0;
}


