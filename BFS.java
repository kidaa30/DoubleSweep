  static int[] BFS(int[][] g,int s){
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