import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Vector;


public class convertFile {

	  static Vector<Vector<Vector<Integer>>> getIntegersFromSnap(String fileName) throws IOException {
		  FileInputStream fstream = new FileInputStream(fileName);
	      DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String str;
		  Vector<Vector<Vector<Integer>>> list=new Vector<Vector<Vector<Integer>>>();
		  Vector<Vector<Integer>> nodiOut=new Vector<Vector<Integer>>();
		  Vector<Vector<Integer>> nodiIn=new Vector<Vector<Integer>>();


	      try {
		     while ((str = br.readLine()) != null) {
			 String[] array= str.split("\\s+");
			if (array.length==5){
				
				int numNodi=new Integer(array[2]);
				for (int i=0;i<numNodi;i++){
					Vector<Integer> nodiU=new Vector<Integer>();
					Vector<Integer> nodiE=new Vector<Integer>();
					nodiOut.add(i,nodiU);
					nodiIn.add(i,nodiE);
				}
				
				
			}
			 else if(array.length==2) {
				   
			     int nodoUscente=new Integer(array[0]);
			     int nodoEntrante=new Integer(array[1]);
			     nodiOut.get(nodoUscente).add(nodoEntrante);
			     nodiIn.get(nodoEntrante).add(nodoUscente);
		          } else {}
	       } 
		  
	     } catch (NumberFormatException e) {
	        System.out.println("Error '" );
	       }
	       list.add(0,nodiOut);
	       list.add(1,nodiIn);
	       return list;
	  }
	  
	  
	  static void writeCorrectFormat(Vector<Vector<Integer>> ccOut,Vector<Vector<Integer>> ccIn,String fileName){
		    try {
		    	String outputFile=fileName;
		        FileOutputStream file = new FileOutputStream(outputFile);
		        PrintStream Output = new PrintStream(file);
		        Output.println(ccOut.size());
		        
		        for(int i=0;i<ccOut.size();i++){
		        	Output.print(i+" ");

		        	Output.print(ccOut.get(i).size()+" ");
		        	Output.println(ccIn.get(i).size());
		        }
		        
		        for(int i=0;i<ccOut.size();i++){
		        	for(int j=0;j<ccOut.get(i).size();j++){
		        		Output.print(i+" ");
		        		Output.println(ccOut.get(i).get(j));
		        	}
		        	
		        }
		       

		      } catch (IOException e) {
		        System.out.println("Error: " + e);
		        System.exit(1);
		      }	  
	}
	  
	  static Vector<Vector<Vector<Integer>>> getIntegersFromSnapIf(String fileName) throws IOException {
		  FileInputStream fstream = new FileInputStream(fileName);
	      DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String str;
		  Vector<Vector<Vector<Integer>>> list=new Vector<Vector<Vector<Integer>>>();
		  Vector<Vector<Integer>> nodiOut=new Vector<Vector<Integer>>();
		  Vector<Vector<Integer>> nodiIn=new Vector<Vector<Integer>>();
		  int maxnum=0;
		  try{
			  while((str=br.readLine())!=null){
					 String[] array= str.split("\\s+");

				 if (array.length==5){
					 maxnum=new Integer(array[2]);
				 } else if(array.length==2){
//					 System.out.println("ci sno");
					 int max1=new Integer(array[0]);
					 int max2=new Integer(array[1]);
					 if (max1>max2){
						 if (max1>maxnum){
							 maxnum=max1;
						 }
					 } else if(max2>max1) {
						 if(max2>maxnum){
							 maxnum=max2;
						 }
					 }
				 }
			  }
//			  System.out.println("valore massimo"+maxnum);
			  for (int i=0;i<maxnum+1;i++){
				  Vector<Integer> nodiU=new Vector<Integer>();
					Vector<Integer> nodiE=new Vector<Integer>();
				  nodiOut.add(nodiU);
				  nodiIn.add(nodiE);
			  }
//			  System.out.println("size"+nodiOut.size());
			  
		  
		
			  FileInputStream fstream2 = new FileInputStream(fileName);
		      DataInputStream in2 = new DataInputStream(fstream2);
			  BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			  String str2;


		     while ((str2 = br2.readLine()) != null) {

			 String[] array= str2.split("\\s+");
		 if(array.length==2) {
			     int nodoUscente=new Integer(array[0]);
			     int nodoEntrante=new Integer(array[1]);
			     nodiOut.get(nodoUscente).add(nodoEntrante);
			     nodiIn.get(nodoEntrante).add(nodoUscente);
		          } 
	       } 
		  
	     } catch (NumberFormatException e) {
	        System.out.println("Error '" );
	       }
	       list.add(0,nodiOut);
	       list.add(1,nodiIn);
	       return list;
	  
	  
	  }
	  
	  
	  
	  static void writeCorrectFormatIf(Vector<Vector<Integer>> ccOut,Vector<Vector<Integer>> ccIn,String fileName){
		    try {
		    	String outputFile=fileName;
		        FileOutputStream file = new FileOutputStream(outputFile);
		        PrintStream Output = new PrintStream(file);
//		        int nonvuoti=0;
		  
//		        System.out.println(nonvuoti);
		        Output.println(ccOut.size());
		        
		        for(int i=0;i<ccOut.size();i++){

		        	Output.print(i+" ");

		        	Output.print(ccOut.get(i).size()+" ");
		        	Output.println(ccIn.get(i).size());
		        	
		        }
		        for(int i=0;i<ccOut.size();i++){
		        

		        	for(int j=0;j<ccOut.get(i).size();j++){
		        		Output.print(i+" ");
		        		Output.println(ccOut.get(i).get(j));
		        	}
		        
		        }
		       

		      } catch (IOException e) {
		        System.out.println("Error: " + e);
		        System.exit(1);
		      }	  
	}
	
}
