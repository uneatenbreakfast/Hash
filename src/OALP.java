import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class OALP {

    /**
	 * @param args
     * @throws MyException 
	 */
	public static void main(String[] args) {
		HashTable ht = new HashTable(1000);
		//
		HashTable.testMode = false;
		//
		Scanner fs = new Scanner( System.in );
		int orderNum = 0;
		
		ArrayList<Integer> decreasingSequence = new ArrayList<Integer>(); 
        while(fs.hasNext()){
        	String n = fs.nextLine().trim();
        	
        	if(orderNum == 0){
        		// first line
        		//
        		orderNum =  Integer.parseInt(n);
        		decreasingSequence = new ArrayList<Integer>();
        		
        		if(n.equals("0")){ // End of sequence, close all
             	   fs.close();
             	   break;
                }
        	}else{
        		// put through adjacency lists
        		//trace.println("n:"+n+" d:"+ds);
        		if(n.equals("")){
            		decreasingSequence.add(0);
        		}else{
        			int xx = n.replaceAll("\\s+",",").split(",").length;
            		decreasingSequence.add(xx);
        		}
        		
        		orderNum--;
        	}
        	
        	if(orderNum == 0){
        		// finished collecting all ds strings   
        		Comparator<Integer> des = Collections.reverseOrder();
        		Collections.sort(decreasingSequence, des);
        		
        		String ds = decreasingSequence.toString().replaceAll(", ", "").replace("[", "").replace("]", "");
        		ht.add(ds);
        	}
        }
        
        if(!HashTable.testMode){
        	System.out.println(ht.output());
        }
        
        
	}
}

class HashTable{
	private int[] store;
	public static Boolean testMode = true;
	
	public HashTable(int tsize){
		store = new int[tsize];
	}
	
	public void setTestMode(boolean b) {
		testMode = b;	
		trace(testMode);
	}

	

	public void add(String ds){
		int slotNum = hash(ds);
		store[slotNum] = 1;
		
		trace("Storing:"+ds+" @:"+slotNum);
	}
	
	public int hash(String ds){
		trace("--ds:"+ds);
		String dsx = getMid(ds);
		
		trace("fMid:"+dsx);
		
		int dsquared = Integer.parseInt(dsx);
		dsquared = dsquared * dsquared;
		
		trace("fMid2:"+dsquared);
		String newDsx = getMid(dsquared+"");
		
		trace("newDsx:"+newDsx);
		int slotNumx = Integer.parseInt(newDsx);
		int wrap = 1000;
		for(int i=0;i<1000;i++){
			if(store[(slotNumx+i)%wrap] == 0){
				slotNumx = (slotNumx+i)%wrap;
				break;
			}
		}
		return slotNumx; 
	}
	private String getMid(String ds){
		String dsx = ds;
		
		if(dsx.length() % 2 == 0){
			dsx += "0";
		}
		while(dsx.length() < 3){
			dsx = dsx+"0";
		}
		
		trace("-ds:"+dsx);
		
		int mid = (int)(dsx.length()/2)-1;
		return dsx.substring(mid,mid+3);		
	}
	
	public int getEntry(int slotNum){
		return store[slotNum];
	}
	public String output(){
		StringBuilder r = new StringBuilder();
		
		for(int i : store){
            r.append(i+"\n");
		}		
		
		return r.toString();		
	}
	
	
	public void trace(String d){
		//main trace
		if(testMode){
			System.out.println(d);
		}		
	}
	public void trace(Boolean d) {
		trace(d+"");		
	}
	public void trace(String d, String t){
		trace(d+","+t);
	}
}
