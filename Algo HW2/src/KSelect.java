import java.util.Arrays;
import java.util.Random;

/**
* 
* KSelect.java
* @author Trushank
* Date Dec 13, 2012
* Version 1.0
*
 * 
 */

 /**
 * @author Trushank
 *
 */
 public class KSelect {
     public static void main(String args[]){
	 int arr[]={0,1,8,5,5,5,6,7,8,8};
	 System.out.println(sort(8,arr));
     }
  
     public static int bubbleSort(int arr[]){
	 for(int i=0;i<arr.length-1;i++){
	     for(int j=1;j<arr.length;j++){
		 
		 if(arr[i]>arr[j]){
		 int a=arr[i];
		 arr[i]=arr[j];
		 arr[j]=a;
		 }
	     }
	 }
	 return arr[arr.length/2];
     }
     public static int generateSeed(int arr[]){
	 int len=0;
	 if(arr.length%5==0){
	     len=arr.length/5;
	 }
	 else{
	     len=(arr.length/5)+1;
	 }
	 int arrOfMedians[]=new int[len];
	 System.out.println("Array Length/5: "+len);
	 System.out.println("Array Length:"+arr.length);
	//if(len==1)
	  //  return arr[0];
	 int medianIndex=0;
	for(int i=0;i<arr.length;){
	    int smallArr[]=new int[5];
	    for(int j=0;j<5 && i<arr.length;j++){
		smallArr[j]=arr[i++];
	    }
	    arrOfMedians[medianIndex++]=bubbleSort(smallArr);
	}
	
	return bubbleSort(arrOfMedians);
     }
   
public static int sort(int position,int arr[]){
    
    int seed=generateSeed(arr);
    System.out.println("Seed: "+seed);
    int smaller[]=new int[arr.length];
    int larger[]=new int[arr.length];
    int eqaul[]=new int[arr.length];
    int smallerIndex=0;
    int largerIndex=0;
    int equalIndex=0;
    int currPosition=0;
    for(int i=0;i<arr.length;i++){
	if(arr[i]>seed){
	    larger[largerIndex++]=arr[i];
	}else if(arr[i]<seed) {
	    smaller[smallerIndex++]=arr[i];
	}
	else if(arr[i]==seed){
	    eqaul[equalIndex++]=arr[i];
	    //larger[largerIndex++]=arr[i];
	}
    }
    for(int i=0;i<arr.length;i++){
	System.out.print(arr[i]+" ");
    }
    System.out.println();
    currPosition=smallerIndex;
    System.out.println("Small: ");
    for(int i=0;i<smallerIndex;i++){
       System.out.print(smaller[i]+" ");
       
   }
   System.out.println();
   
   System.out.println("Large: ");
   for(int i=0;i<largerIndex;i++){
      System.out.print(larger[i]+" ");
      
  }
  System.out.println();
  System.out.println("Current Position: "+currPosition+"\nReq position: "+position);
 int last[]=new int[largerIndex+equalIndex+smallerIndex];
 int lastIndex=0;
 for(int i=0;i<smallerIndex;i++){
     last[lastIndex++]=smaller[i];
 }
 
 for(int i=0;i<equalIndex;i++){
     last[lastIndex++]=eqaul[i];
 }
 
 for(int i=0;i<largerIndex;i++){
     last[lastIndex++]=larger[i];
 }
 int i1=smallerIndex;
 int j1=smallerIndex+equalIndex-1;
 if(arr.length==0){
	return eqaul[0];
 }
if(currPosition==position){
return last[position];
    //return arr[position];
}
else  if(currPosition>position){
   
    int small[]=new int[smallerIndex];
    for(int i=0;i<smallerIndex;i++){
	small[i]=smaller[i];
    }
  return sort(position,small);
    

}else{
    
    int large[]=new int[largerIndex];
    for(int i=0;i<largerIndex;i++){
	
	large[i]=larger[i];
    }
   return sort(position-(currPosition+1),large);
}
}
 }
 
