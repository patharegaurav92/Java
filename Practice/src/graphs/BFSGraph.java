package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



public class BFSGraph {
	private LinkedList<Integer> adj[]; //Linked list to store all the adjacent vertex
	private int V; //No of vertices in the graph
	
	public BFSGraph(int v){
		V=v;
		adj= new LinkedList[v];
		
		for(int i=0;i<v;i++){
			adj[i] = new LinkedList();
		}
		
	}
	
	public void addEdge(int u, int v){
		adj[u].add(v);
	}
	public void BFS(int src){
		
		boolean visited[] = new boolean[V];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		visited[src] = true;
		q.add(src);
		
		while(!q.isEmpty()){
			
			src = q.poll();
			System.out.print(src+" ");
			
			Iterator<Integer> it = adj[src].iterator();
			
			while(it.hasNext()){
				int next = it.next();
				if(!visited[next]){
					visited[next] = true;
					q.add(next);
				}
			}
			
			
			
		}
	
		
		
	}
	
	
	public static void main(String args[])
    {
        BFSGraph g = new BFSGraph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.BFS(2);
    }
}
