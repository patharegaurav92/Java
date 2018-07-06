package graphs;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSAllGraphs {
	private int V;
	private LinkedList<Integer> adj[];
	DFSAllGraphs(int v){
		V=v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++){
			adj[i] = new LinkedList();
		}
	}
	public void addEdge(int u, int v){
		adj[u].add(v);
	}
	
	public void DFS(){
		boolean visited[] = new boolean[V];
		for(int i=0;i<V;i++){
			if(!visited[i])
				DFS(i,visited);	
		}
		
	}
	
	private void DFS(int src, boolean[] visited) {
		visited[src] = true;
		System.out.print(src+" ");
		
		Iterator<Integer> it = adj[src].iterator();
		while(it.hasNext()){
			int n = it.next();
			if(!visited[n]){
				DFS(n,visited);
			}
		}
		
	}
	public static void main(String args[])
    {
		DFSAllGraphs g = new DFSAllGraphs(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 2)");
 
        g.DFS();
    }
}
