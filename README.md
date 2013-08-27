# Double Sweep

## Diameter

>The distance dG(u, v) between two (not necessary distinct) vertices u and v in a graph G is the length of a shortest path between them. The subscript G is usually dropped when there is no danger of confusion. When u and v are identical, their distance is 0. When u and v are unreachable from each other, their distance is defined to be infinity ∞.
>The eccentricity εG(v) of a vertex v in a graph G is the maximum distance from v to any other vertex. The diameter diam(G) of a graph G is the maximum eccentricity over all vertices in a graph;

## Algorithm

* Choose a random node, lets say a
* Compute a Depth-first search from that node and choose a node at max distance from a, lets say b
* Reverse the orientation of all arcs
* From b compute another Depth-first search
* The distance from b to the node at max distance from it using the second Depth-first search is out lower bound