import java.util.Scanner;
/*
This is a simple implementation of the Round-robin scheduling algorithm.
Only displays the total wait time of all processes and the average wait time.
*/
class SimpleRoundRobin{
	public static void main(String[] args) {
    	int procCount = 0;
    	//get number of processes
    	System.out.print("Enter number of processes: ");
    	procCount = new Scanner(System.in).nextInt();

    	//process initialization
        int [] burst = new int[procCount];
        int [] arrival = new int[procCount];
   
    	for(int i = 0; i < procCount; i++){
    		System.out.print("Please enter burst value for proc["+(i+1)+"]: ");
    		burst[i] = new Scanner(System.in).nextInt();
            arrival[i] = 0;
    	}
    	
        //get quantum number
    	int quantum = 0;
        System.out.print("Enter quantum number: ");
        quantum = new Scanner(System.in).nextInt();

        //cycle counter
        int i = 0;
        //for tracking number of processes completed
        int completionCount = 0;
        //timeline for the entire flow of the program
        int timeLine = 0;
        //total wait time for the entire program
        int totalWaitTime = 0;

        //RoundRobin begins
        
        //keep looping while there are incomplete processes
        while(completionCount < procCount){
            //a process is still not done
            if (burst[i] > 0){
                //Timeline = START
                //calculate waitTime = (start - arrival), then add to the process's total wait time
                totalWaitTime += (timeLine - arrival[i]);
                            
                //passage of time after acting on a process
                //adds burst time if burst time is less than quantum
                //adds quantum time instead if burst value is greater, so that every task is treated equally
                //without prioritizing any task, thus captures the concept of round robin
                if (burst[i] < quantum){
                    timeLine += burst[i];
                }
                else{
                    timeLine += quantum;
                }
                
                //new arrival time for the current process to prepare it for the next cycle
                //Timeline = COMPLETE
                arrival[i] = timeLine;
                
                //process has been carried out
                burst[i] -= quantum;
                
                //increase if process is complete
                //needs to be inside while so that the counter doesn't increase when the loop checks an already completed process again 
                if(burst[i] <= 0){
                    completionCount++;
                }   
            }

            //the cycle counter
            i++;
            i = (i % procCount);
        }

        //final result
        System.out.println("==================================================================");   	
        System.out.println("TOTAL WAIT TIME = " + totalWaitTime);
    	double averageWaitTime = (double) totalWaitTime/procCount;
        System.out.println("AVERAGE WAIT TIME = " + averageWaitTime);
  	}
};