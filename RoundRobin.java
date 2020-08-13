import java.util.Scanner;

class Process{
	public int procID;
	public int burst = 0;
	public int arrival = 0;
	public int waitTime = 0;
	String waitTimeList = "";

};

class RoundRobin{
	public static void main(String[] args) {
    	int procCount = 0;
    	//get number of processes
    	System.out.print("Enter number of processes: ");
    	procCount = new Scanner(System.in).nextInt();

    	//process initialization
    	Process [] proc = new Process [procCount];
   
    	for(int i = 0; i < procCount; i++){
    		proc[i] = new Process();
    		proc[i].procID = i+1;
    		System.out.print("Please enter burst value for proc["+proc[i].procID+"]: ");
    		proc[i].burst = new Scanner(System.in).nextInt();
    	}
    	
        //get quantum number
    	int quantum = 0;
        System.out.print("Enter quantum number: ");
        quantum = new Scanner(System.in).nextInt();

        //cycle counter
        int i = 0;
        
        //number of processes completed
        int completionCount = 0;
        
        int waitTime = 0;
        
        int totalWaitTime = 0;
        
        //the current time, to be used
        //as a timeline for the entire flow of the program
        int procTime = 0;
        
        System.out.println("==================================================================");
        System.out.println("Proc | Burst | Arrival | Start | Complete | waitTime | Remainder ");
        
        //RoundRobin begins
        
        //keep looping while there are incomplete processes
        while(completionCount < procCount){
            //a process is still not done
            if (proc[i].burst > 0){

                System.out.print("[" + proc[i].procID +"]  |   " + proc[i].burst + "   |   "+ proc[i].arrival + "     |   "+procTime); 
                
                //calculate waitTime = (start time - arrival time) 
                waitTime = procTime - proc[i].arrival;
                
                //wait time for the current iteration is added to the totalWaitTime for the whole program
                totalWaitTime += waitTime;
                
                //wait time for the current iteration is added to the current process's total wait time
                proc[i].waitTime += waitTime;
                
                //the wait time is added to a list that keeps track of every wait time for the current process
                proc[i].waitTimeList += Integer.toString(waitTime) + ", ";
                
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
                
                
                //new arrival time for the current process to prepare it for the next cycle
                proc[i].arrival = procTime;
                
                //process has been carried out
                if (proc[i].burst > quantum ){
                    proc[i].burst -= quantum;
                }
                //change the burst to zero so that the burst value doesn't fall below negative
                else{
                    proc[i].burst = 0;
                }

                System.out.println("   |   " + procTime + "      |     " + proc[i].waitTime + "    |     " + proc[i].burst);
                
                //check if process is complete
                if (proc[i].burst == 0){
                    completionCount++;
                }
                
            }

            //prints border to separate distinguish between different cycles
            if (i == (procCount-1)){
                System.out.println("==================================================================");
            }

            //the cycle counter
            i++;
            i = (i % procCount);
        }
        
        System.out.println("==================================================================");
    	double averageWaitTime = (double) totalWaitTime/procCount;
    	
        //displays the wait times for every process inclusing its total
        for (int j = 0; j < procCount; j++){
    		System.out.println("proc[" + proc[j].procID + "].waitTimeList = " + proc[j].waitTimeList);
            System.out.println("proc[" + proc[j].procID + "].waitTime = " + proc[j].waitTime);
            System.out.println("");
    	}

        //final result
        System.out.println("==================================================================");   	
        System.out.println("AVERAGE WAIT TIME = " + totalWaitTime + "/" + procCount);
    	System.out.println("AVERAGE WAIT TIME = " + averageWaitTime);
  	}
};