import java.util.*;

class DFSAdjList {
    private int v;
    private LinkedList<Integer>[] adj;
    public DFSAdjList(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    public void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src); // for undirected graph
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int vis = stack.pop();
            System.out.print(vis + " ");
            for (int neighbor : adj[vis]) 
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = scanner.nextInt();
        DFSAdjList graph = new DFSAdjList(v);

        System.out.print("Enter the number of edges: ");
        int e = scanner.nextInt();
        System.out.println("Enter the edges (source and destination): ");
        for (int i = 0; i < e; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            graph.addEdge(src, dest);
        }

        System.out.print("Enter the starting vertex for DFS: ");
        int start = scanner.nextInt();
        System.out.println("DFS traversal starting from vertex " + start + ":");
        graph.DFS(start);
        
        scanner.close();
    }
}

// Output
// Enter the number of vertices: 6
// Enter the number of edges: 9
// Enter the edges (source and destination): 
// 0 1
// 0 2
// 0 3
// 0 4
// 1 3
// 2 3
// 2 4
// 2 5
// 3 5
// Enter the starting vertex for DFS: 1
// DFS traversal starting from vertex 1:
// 1 3 5 2 4 0 
