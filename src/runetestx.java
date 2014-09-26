import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.omg.CORBA.portable.OutputStream;

public class runetestx {

    /**
	 * @param args
     * @throws MyException 
	 */
	public static void main(String[] args) {
		HashTablex ht = new HashTablex(1000);
		
		String allMessage = "";
		
		int orderNum = 0;
		int snum = 1;
		Scanner fs = new Scanner( System.in );
        while(fs.hasNext()){
        	String n = fs.nextLine();
       
        	allMessage += n.replaceAll(" ", "_")+".";
        	
        	
        	
        	if(orderNum == 0){
        		orderNum =  Integer.parseInt(n);
        		if(n.equals("0")){ // End of sequence, close all
             	   fs.close();
             	   //trace("EXIT SCANNER");
             	   break;
                }
        	}else{        		
        		orderNum--;
        	}
        	
        	snum++;
        }
		
		
		
		        

        throw new RuntimeException("Ex:"+allMessage);
		  //System.out.println("10");

	}
}

class HashTablex{
	private int[] store;
	
	public HashTablex(int tsize){
		store = new int[tsize];
	}
	
	public void add(String ds){
		int slotNum = hash(ds);
		store[slotNum] = 1;
		
		trace("Storing:"+ds+" @:"+slotNum);
	}
	
	public int hash(String ds){
		String dsx = getMid(ds);
		
		trace("fMid:"+dsx);
		
		int dsquared = Integer.parseInt(dsx);
		dsquared = dsquared * dsquared;
		
		String newDsx = getMid(dsquared+"");
		
		// try this slot
		int slotNumx = Integer.parseInt(newDsx);
		int ti = slotNumx;
		for(int i=0;i<1000;i++){
			if(ti >= 1000){
				ti = 0;
			}
			if(store[ti] == 0){
				slotNumx = ti;
				break;
			}else{
				ti++;
			}
		}
		return slotNumx; 
	}
	private String getMid(String ds){
		String dsx = ds;
		
		if(dsx.length()<3 || dsx.length() % 2 == 0){
			dsx += "0";
		}
		
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
		//System.out.println(d);
	}
	public void trace(String d, String t){
		System.out.println(d+","+t);
	}
}
