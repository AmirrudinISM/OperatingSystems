#include <iostream>
#include <stdio.h>

struct process{
  int burst = 0;
  int arrival = 0;
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
    
    int totalWaitTime = 0;
    //the current time
    int procTime = 0;
    
    //Round Robin begins here
    
    //keep looping while there are incomplete processes
    while(completionCount < procCount){
        //a process is still not done
        if (proc[i].burst > 0){
            
            //calculate total waitTime = (start time - arrival time) 
            totalWaitTime += (procTime - proc[i].arrival);
            
            //passage of time after acting on a process
            //adds burst time if burst time is less than quantum
            //adds quantum time instead if burst value is greater, so that every task is treated equally
            //without prioritiing any task, thus captures the concept of round robin
            if (proc[i].burst < quantum){
                procTime += proc[i].burst;
            }
            else{
                procTime += quantum;
            }
            
            //new arrival time for the current process for the next cycle
            proc[i].arrival = procTime;
            //process has been carried out
            proc[i].burst -= quantum;
            //check if process is complete
            if (proc[i].burst <= 0){
                completionCount++;
            }
        }
        
        //the cycle counter
        i++;
        i = (i % procCount);
    }
    
    double averageWaitTime = (double) totalWaitTime/procCount;
    std::cout << "AVERAGE WAIT TIME =" << averageWaitTime << std::endl;
    return 0;
}


