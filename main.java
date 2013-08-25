import java.io.*;
import java.util.*;


//import javax.imageio.IIOException;
public class Main {

	static int[] cc;
	static int ccindex=-1;
	static Stack<Integer> partial;
	static Stack<Integer> representatives;
	static int counter;
	static boolean[] complete;
	static int[] dfsnumber;
	
	static void resetCCComputation(){
		cc=null;
		ccindex=-1;
		partial=null;
		representatives=null;
		counter=-1;
		complete=null;
		dfsnumber=null;
	}
	
	
	static int[] stronglyConnectedComponent(int[][] g){
		
		cc=new int[g.length];
		
		
		 for(int i=0;i<cc.length;i++){
			cc[i]=-1;
		 }

		 complete=new boolean[g.length];
		 dfsnumber=new int[g.length];
		 
		 partial=new Stack<Integer>();
		 representatives=new Stack<Integer>();
		 counter=0;

		 for (int i=0;i<g.length;i++){
			dfsnumber[i]=-1;
			complete[i]=false;
		 }
	
		 for(int i=0;i<g.length;i++){
			if(dfsnumber[i]==-1) recursiveExtendedDepthFirstSearch(g,i);
		 }
		 
		 int ncc=ccindex+1;
//		 System.out.println("cc"+ncc);
		 int[] fcc=new int[ncc];
		 for(int i=0; i< fcc.length; i++){
			 fcc[i]=0;
		 }
		 for(int i=0; i< g.length; i++){
			 fcc[cc[i]]++;
		 }
		 int mostfrequent=getIndexOfMax(fcc);
		 //System.out.println("Numero nodi in cc piu grande"+fcc[mostfrequent]);
		 int[] res=new int[fcc[mostfrequent]];
		 int j=-1;
		 for(int i=0; i< g.length; i++){
			 if(cc[i]==mostfrequent){
				 j++;
				 res[j]=i;
			 }
		 }
		 return res;
	}
	
  static void recursiveExtendedDepthFirstSearch(int[][] g, int u){
		dfsnumber[u]=counter;
		counter++;
		partial.push(u);
		representatives.push(u);
		for ( int x=0;x<g[u].length;x++){
			int v =g[u][x];
			if (dfsnumber[v]==-1){
				recursiveExtendedDepthFirstSearch(g,v);
			} else if(complete[v]==false){
				while(dfsnumber[representatives.peek()]>dfsnumber[v])
					representatives.pop();
				
			}
		
		}

		if (u==representatives.peek()){
		    int z;
		    ccindex++;
		   
			do {
			   z=partial.pop();
//			   System.out.println(z);
			   cc[z]=ccindex;
			   complete[z]=true;
			
			} while (z!=u);
			representatives.pop();
//			System.out.println("Nuova componente fortemente connessa"+ ccindex+" "+tyu);
	    }
	}
  
  static int getDiametro(int[][] g){
	  int max=0;
	  for(int i=0;i<g.length; i++){
		  
		  int[] r=BFS2(g,i);
				 int j= r[getIndexOfMax(r)];
		  if(j>max){
			  max=j;
			  }
	  }
	  return max;
  }
  
  static int[] BFS2(int[][] g,int s){
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited=new boolean[g.length];
		int[] dist=new int[g.length];
		for(int i=0;i<g.length;i++){
			visited[i]=false;
			dist[i]=Integer.MAX_VALUE;
		}
		    q.offer(s);
		    dist[s]=0;
		    visited[s]=true;
		   
			while(q.isEmpty()==false){
				int u=q.remove();
					for ( int x=0;x<g[u].length;x++){
						int v =g[u][x];
						if(visited[v]==false) {
							visited[v]=true;
							q.offer(v);
							dist[v]=dist[u]+1;
							}
						
					}
			}
			return dist;
			
			
 	}
  
  static int getIndexOfMax(int[] s){
	  int max=0;
	  int index=-1;
	  for(int i=0; i<s.length; i++){
		  if(max<s[i]){
			  index=i;
			  max=s[i];
		  }
	  }
	  return index;
  }
		
  
  static void print(int[] c){
	
	for (int i=0;i<c.length;i++){
		
	    System.out.print(c[i]+" ");
		
	}
	System.out.println("");
   }

  static int[][] vectorCC(int[] a,int[][] g){
       int [] arrayNodi=new int[g.length];
       int[][] CClist=new int[a.length][];
       for (int i=0;i<arrayNodi.length;i++){
    	   arrayNodi[i]=-1;
       }
       for (int i=0;i<a.length;i++){
    	   arrayNodi[a[i]]=i;
       }
       
       for (int i=0;i<a.length;i++){
    	   int contatore=0;
    	   for (int j=0;j<g[a[i]].length;j++){
    		   if (arrayNodi[g[a[i]][j]]!=-1) contatore++;
    		   
    	   }
//    	   System.out.println(contatore);
    	   int[] figli=new int[contatore];
    	   CClist[i]=figli;
    	   
       }
       for (int i=0;i<a.length;i++){
    	   int contatore=0;
    	   for(int j=0;j<g[a[i]].length;j++){
    		   if (arrayNodi[g[a[i]][j]]!=-1){
    			   CClist[i][contatore]=arrayNodi[g[a[i]][j]];
    			   contatore++;
 			   
    		   }
    	   }
       }

return CClist;
	  
	  
  }

  static public void bubbleSort(int[] x) {
	  int temp = 0;
	  for(int j=0;j<x.length;j++) {
	    for(int i=j;i<x.length;i++) {
		  if(x[i]!=-1 && x[j]!=-1){
	       if(x[j]>x[i]) {
	       temp=x[j]; 
	       x[j]=x[i];
	       x[i]=temp;
	       }
	      }
	    }
	  }
  }
  
  
  static int[][][] getIntegersFromFile(String fileName) throws IOException {
	  FileInputStream fstream = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fstream);
	  BufferedReader br = new BufferedReader(new InputStreamReader(in));
	  String str;
	  int[][][] list=new int[2][][];
	  int[][] nodiOut=new int[0][];
	  int[][] nodiIn=new int[0][];
	 
	  int[] arraydinU=new int[0];
	  int[] arraydinE=new int[0];

	 

      try {

	     while ((str = br.readLine()) != null) {
 
		 String[] array= str.split("\\s+");
		if (array.length==1){
			int numNodi=new Integer(array[0]);
			   nodiOut=new int[numNodi][];
			   nodiIn=new int[numNodi][];
			   arraydinU=new int[numNodi];
			   arraydinE=new int[numNodi];

			  
		}
		 
		else if(array.length==3){
		      int nodeIndex=new Integer(array[0]);
		      int degreeOut=new Integer(array[1]);
		      int degreeIn=new Integer(array[2]);

			 int[] figliU=new int[degreeOut];
			  int[] figliE=new int[degreeIn];

		      nodiOut[nodeIndex]=figliU;
		      nodiIn[nodeIndex]=figliE;
		   } 
		   else if(array.length==2) {
			
		     int nodoUscente=new Integer(array[0]);
		     int nodoEntrante=new Integer(array[1]);
		     nodiOut[nodoUscente][arraydinU[nodoUscente]]=nodoEntrante;
		     nodiIn[nodoEntrante][arraydinE[nodoEntrante]]=nodoUscente;
		   
		     arraydinU[nodoUscente]++;
		     arraydinE[nodoEntrante]++;
	          }
       } 
	  
     } catch (NumberFormatException e) {
        System.out.println("Error '" );
       }
       list[0]=nodiOut;
       list[1]=nodiIn;
       return list;
  }
  
   
  static void writeFile(int[][] ccIn,int[][] cc,String fileName){
	    try {
	    	String outputFile=fileName;
	        FileOutputStream file = new FileOutputStream(outputFile);
	        PrintStream Output = new PrintStream(file);
	        Output.println(cc.length);
	        
	        for(int i=0;i<cc.length;i++){
	        	Output.print(i+" ");
//	        	int counter=0;
//	        	for(int j=0;j<cc.size();j++){
//	        		for(int z=0;z<cc.get(j).size();z++){
//	        			if (cc.get(j).get(z)==i) counter++;
//	        		}
//	        	}
	        	Output.print(cc[i].length+" ");
//	        	Output.println(counter);
	        	Output.println(ccIn[i].length);
	        }
	        
	        for(int i=0;i<cc.length;i++){
	        	for(int j=0;j<cc[i].length;j++){
	        		Output.print(i+" ");
	        		Output.println(cc[i][j]);
	        	}
	        	
	        }
	       

	      } catch (IOException e) {
	        System.out.println("Error: " + e);
	        System.exit(1);
	      }	  
  }

  
    
  static int getLast(int[] a){
	  
	  int last=0;
	  for(int i=0;i<a.length;i++){
		  if(a[i]==-1){
			  last=a[i-1];
		  }
	  }
	  return last;
  }
  



  
  
  public static  void main(String[] args) throws IOException{
	  
 Vector<Vector<Integer>> snapOut=convertFile.getIntegersFromSnapIf("/users/valerio/Desktop/grafi/soc-sign-Slashdot081106.txt").get(0);  
 Vector<Vector<Integer>> snapIn=convertFile.getIntegersFromSnapIf("/users/valerio/Desktop/grafi/soc-sign-Slashdot081106.txt").get(1);  
////
 convertFile.writeCorrectFormatIf(snapOut,snapIn,"/users/valerio/Desktop/dle.txt");
 System.out.println("File convertito");
/////////////////////////////////////////////////////////////////////////////////////////////	   
 int[][][] graph=getIntegersFromFile("/users/valerio/Desktop/dle.txt");
 int[][] graphout=graph[0];
 int[][] graphin=graph[1];
//int[]comp=stronglyConnectedComponent(graphout);
 



 System.out.println("Calcolo componente connessa");
 int[][] compConnOut=vectorCC(stronglyConnectedComponent(graphout),graphout);
 int[][] compConnIn=vectorCC(stronglyConnectedComponent(graphin),graphin);
 System.out.println("Componente connessa calcolata");

 

 writeFile(compConnIn,compConnOut,"/users/valerio/Desktop/CCgrafo.txt");
 System.out.println("Componente connessa stampata");
 
int[][] graphfw=getIntegersFromFile("/users/valerio/Desktop/CCgrafo.txt")[0];
int[][] graphbw=getIntegersFromFile("/users/valerio/Desktop/CCgrafo.txt")[1];
//	
System.out.println("Calcolo diametro");
System.out.println("Diametro esatto " + getDiametro(graphfw));
//
int r=(int) (Math.random()*graphfw.length);
int[] s=BFS2(graphfw,r);
int a=getIndexOfMax(s);
int[] t=BFS2(graphbw,a);
int b=getIndexOfMax(t);
System.out.println("First lb " + s[a]+" "+t[r]);
System.out.println("Second lb " + t[b]);
//
//	


  }
}
		

	


