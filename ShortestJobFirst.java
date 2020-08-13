import java.util.Scanner;  

class Process{
	public int procID;
	public int burst;
	public int arrival;
	public int waitTime;
	String waitTimeList;

};

class ShortestJobFirst{
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

        Process temp;
        //sort from smallest to largest process burst
        for (int i = 0; i < procCount; i++){
            System.out.println("-->Counter i: "+ i +" proc["+proc[i].procID+"].burst = "+proc[i].burst);
            for (int j = 0; j < procCount; j++){
                System.out.println("==>Counter j: "+ j +" proc["+proc[j].procID+"].burst = "+proc[j].burst);
                //swap
                if (proc[j].burst > proc[i].burst){
                    System.out.println("proc["+proc[i].procID+"].burst = "+proc[i].burst+", proc["+proc[j].procID+"].burst = "+proc[j].burst);
                    temp = proc[j];
                    proc[j] = proc[i];
                    proc[i] = temp;
                }
            }
        }
    	
    	int waitTime = 0;
    	int totalWaitTime = 0;
    	int procTime = 0;

    	//FCFS begins
    	System.out.println("Proc | Burst  | Arrival | Start | Complete | waitTime |");

    	for (int i = 0; i < procCount; i++){
    		System.out.print("[" + proc[i].procID +"]  |   " + proc[i].burst + "   |   "+ proc[i].arrival + "     |   "+procTime);
    		//wait time for current process
        	proc[i].waitTime = procTime - proc[i].arrival;
        	//current process wait time added to total waitime
        	totalWaitTime += proc[i].waitTime;
        	//calculate when process ends
        	procTime += proc[i].burst;

        	System.out.println("   |   " + procTime + "      |     " + proc[i].waitTime + "    |     ");
    	}
    	System.out.println("===========================================================");

    	double averageWaitTime = (double) totalWaitTime/procCount;

    	for (int i = 0; i < procCount; i++){
    		System.out.println("proc[" + proc[i].procID + "].waitTime = " + proc[i].waitTime);
    	}

    	System.out.println("===========================================================");
    	System.out.println("AVERAGE WAIT TIME = " + totalWaitTime + "/" + procCount);
    	System.out.println("AVERAGE WAIT TIME = " + averageWaitTime);
  	}
};