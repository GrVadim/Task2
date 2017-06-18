import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Администратор on 10.06.2017.
 */
public class Task2 {
    private int r; //the number of paths to find
    private int n; //the number of vertices in a directed graph
    private int[][] paths; // list of paths

    private Map<String, Integer> cities = new HashMap<String, Integer>(); //list of cities
    private static int INF = Integer.MAX_VALUE / 2;
    private ArrayList<Integer> adj[]; //adjacency list
    private ArrayList<Integer> weight[]; //weight of the edge in the digraph
    private boolean used[]; //an array to store information about passed and not passed vertices
    private int dist[]; //an array for storing the distance from the starting vertex

    private BufferedReader cin;
    private StringTokenizer tokenizer;

    //Dijkstra's algorithm is the procedure to start from the starting vertex
    private void dejkstra(int s) {

        used = new boolean[n];
        Arrays.fill(used, false);

        dist = new int[n];
        Arrays.fill(dist, INF);

        dist[s] = 0; //the shortest distance to the starting vertex is 0
        for (int iter = 0; iter < n; ++iter) {
            int v = -1;
            int distV = INF;
            //choose the top, the shortest distance to which have not yet found
            for (int i = 0; i < n; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v = i;
                distV = dist[i];
            }
            //We consider all edges emanating from a given node
            for (int i = 0; i < adj[v].size(); ++i) {
                int u = adj[v].get(i);
                int weightU = weight[v].get(i);
                //reduce the weight of the tops
                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                }
            }
            //We mark the vertex v as a review, before it found the shortest distance
            used[v] = true;
        }
    }

    private void readData() throws IOException {
        int p; //the number of neighbours of city NAME

        n = Integer.parseInt(cin.readLine()); //We read the number of vertices

        //initialize the list of the graph adjacency dimension n
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            adj[i] = new ArrayList<Integer>();

        //initialization of the list in which the weight of edges kept
        weight = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            weight[i] = new ArrayList<Integer>();


        for (int i = 0; i < n; ++i) {

            cities.put(cin.readLine(),i);

            p = Integer.parseInt(cin.readLine()); //the number of neighbours of city NAME

            for (int j = 0; j < p; ++j) {

                tokenizer = new StringTokenizer(cin.readLine());

                adj[i].add(Integer.parseInt(tokenizer.nextToken())-1);   //the number of neighbours of city NAME
                weight[i].add(Integer.parseInt(tokenizer.nextToken()));  //the number of neighbours of city NAME
            }
        }
    }

    private void readDataTest() throws IOException {

        r = Integer.parseInt(cin.readLine()); //the number of paths to find

        paths = new int[r][2];

        for (int i = 0; i < r; ++i) {

            tokenizer = new StringTokenizer(cin.readLine());

            int city1  = cities.get(tokenizer.nextToken()); //starting point
            int city2  = cities.get(tokenizer.nextToken()); //ending point

            paths[i][0] = city1;
            paths[i][1] = city2;

        }
    }

    private void run() throws IOException {
        int start; //No.  starting vertex
        int end;   //No.  final vertex

        cin = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(cin.readLine()); // the number of tests

        readData(); // reads the data on cities and routes between them

        for (int i = 0; i < s; ++i) {

            readDataTest(); // We read the test data
            System.out.println();

            for (int j = 0; j < r; ++j) {

                start = paths[j][0];    //get the start vertex of the current test
                end = paths[j][1];      //get the final vertex of the current test

                dejkstra(start);        // We obtain the minimum cost routes to all points of the starting point (Dijkstra's algorithm)
                System.out.println(dist[end]);
            }
            System.out.println();
        }
        cin.close();
    }

    public static void main(String[] args) throws IOException {
        Task2 task2 = new Task2();
        task2.run();
    }
}
