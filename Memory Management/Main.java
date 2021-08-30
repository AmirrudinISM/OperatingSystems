import java.util.*;

class Data{  
	int memory_size;  
	String availability;  

	Data(int memory_size,String availability){  
	 this.memory_size=memory_size;  
	 this.availability=availability;  
  
	}  
  }  

  
public class Main{
	public static void main(String[] args) {
		
		ArrayList<Data> memorySegment=new ArrayList<Data>();		   
        ArrayList<Integer> memory = new ArrayList<Integer>();
        ArrayList<Integer> filter = new ArrayList<Integer>();
		
		Scanner input= new Scanner(System.in);  
	
		int total_sagment,segment_val,total_memory,memory_val,new_sagment_value,minSize,x = 0;
		
		System.out.println("Please enter total number of memory sagment");
	    total_sagment=input.nextInt();
	    
	    for(int i=0; i< total_sagment; i++){
	        
	        System.out.println("Please enter value for memory segment "+ (i+1));
			segment_val=input.nextInt();
			Data memory_data=new Data(segment_val,"Unoccupied");  
	        memorySegment.add(memory_data);
	         
		}
		
		System.out.println("Please enter total number of memory");
	    total_memory=input.nextInt();
	    
	    for(int i=0; i< total_memory; i++){
	        
	         System.out.println("Please enter size of memory "+ (i+1));
	         memory_val=input.nextInt();
	         memory.add(memory_val);
	         
        } 
		for(int i=0; i<memory.size(); i++){

			for(int j=0; j<memorySegment.size(); j++){

				if(memory.get(i) < memorySegment.get(j).memory_size && memorySegment.get(j).availability =="Unoccupied"){
				//store unoccupied memory size
                    filter.add(memorySegment.get(j).memory_size);		
                }
                
            }

            minSize= Collections.min(filter);

        for(int k = 0; k < memorySegment.size(); k++){
        
            
            if (memorySegment.get(k).memory_size == minSize){
                x = k;

                new_sagment_value= memorySegment.get(x).memory_size - memory.get(i);

                Data memory_data=new Data( memory.get(i),"Occupied");  
                memorySegment.add(x,memory_data);
        
            	if(new_sagment_value>0){
					Data memory_data_update= new Data(new_sagment_value,"Unoccupied");  
					memorySegment.set((x+1),memory_data_update);
				}else{
					memorySegment.remove((x+1));
				}
        
                memory.set(i,0);
           } 
          
        }
        filter.clear();
		}

		System.out.println("//////////////////////////////");
		for(Data n : memorySegment)
		{
			
			System.out.println(n.memory_size+ "  "+ n.availability);
		}

		System.out.println("/////////////end////////////");
		
		memory.forEach((n)->{
			if(n!=0){
				System.out.println("Unallocated memory is "+ n);
			}
		});
		
        input.close(); //to avoid memory leakage
	 
	    
	}
}