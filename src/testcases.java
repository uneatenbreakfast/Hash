import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class testcases {
	public static void main(String[] args) {
		HashTable3 ht = new HashTable3(1000);
		//
		ht.testMode = true;
		
		String n = "  1      ";
		
		if(n.equals("")){
			System.out.println("D00000");
		}
		
		int c = n.split("\\s+").length;
		System.out.println( c );
        
		for(int i=0;i<0;i++){
			ht.add(""+20319);
		}
		
	}
}



class HashTable3{
	private int[] store;
	public static Boolean testMode = true;
	
	public HashTable3(int tsize){
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
		//trace("--ds:"+ds);
		String dsx = getMid(ds);
		
		//trace("fMid:"+dsx);
		
		int dsquared = Integer.parseInt(dsx);
		dsquared = dsquared * dsquared;
		
		//trace("fMid2:"+dsquared);
		String newDsx = getMid(dsquared+"");
		
		//trace("newDsx:"+newDsx);
		
		// try this slot
		int slotNumx = Integer.parseInt(newDsx);
		int ti = slotNumx;
		for(int i=0;i<1000;i++){
			if(store[(slotNumx+i)%1000] == 0){
				slotNumx = (slotNumx+i)%1000;
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
		
		//trace("-ds:"+dsx);
		
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
