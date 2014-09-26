import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class OADH {

    /**
	 * @param args
	 */
	public static void main(String[] args) {
	
		HashTable2 ht = new HashTable2(1000);
		//
		HashTable2.testMode = false;
		//
		
		Scanner fs = new Scanner( System.in );
		int orderNum = 0;
		ArrayList<Integer> decreasingSequence = new ArrayList<Integer>(); 
        while(fs.hasNext()){
        	String n = fs.nextLine();
        	
        	if(orderNum == 0){
        		// first line
        		//
        		orderNum =  Integer.parseInt(n);
        		decreasingSequence = new ArrayList<Integer>();
        		
        		if(n.equals("0")){ // End of sequence, close all
             	   fs.close();
             	   //trace("EXIT SCANNER");
             	   break;
                }
        	}else{
        		// put through adjacency lists
        		//trace.println("n:"+n+" d:"+ds);
        		if(n.equals("")){
            		decreasingSequence.add(0);
        		}else{
        			int xx = n.split(" ").length;
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
        
        if(!HashTable2.testMode){
        	System.out.println(ht.output());
        }
	}
}

class HashTable2{
	private int[] store;
	public static Boolean testMode = true;
	public HashTable2(int tsize){
		store = new int[tsize];
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
		// try this slot
		if(store[slotNumx] == 0){
			return slotNumx;
		}
		
		// rehash
		return hash2(slotNumx, ds); 
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
	public int hash2(int slotNum, String ds){
		String dsx = getFirst(ds);
		int slotNumx = Integer.parseInt(dsx) + 1;
		
		trace("h2: ds:"+ds+"  dsx:"+dsx);
		trace("slotNumx:"+slotNumx);
		
		// rehash
		int sChange = slotNumx;
		int np = slotNum + sChange;
		for(int i=0;i<1000;i++){
			if(store[np % 1000] == 0){
				slotNumx = np % 1000;
				break;
			}else{
				sChange--;
				if(sChange<1){
					sChange = 1;
				}
				
				np += sChange;				
			}
		}
		return slotNumx; 
	}
	private String getFirst(String ds){
		String dsx = ds;
		while(dsx.length() < 3){
			dsx = "0"+dsx;
		}
		return dsx.substring(0,3);		
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
		//main
		if(testMode){
			System.out.println(d);
		}
		
	}
	public void trace(int d){
		trace(d+"");
	}
	public void trace(String d, String t){
		trace(d+","+t);
	}
}
