# Ford Fulkerson Algorithm

This algorithm is used to find the Maximum Flow in a graph G with weighted edges. 

Three graphs are used for executing the algorithm:
- Original
- Flow 
- Residual

Flow and Residual are copies of the Original graph. However, the weights in Flow are initialized to zero and the ones in Residual will change over time.

We start from our source node **s** and end at our sink node **t** taking note of the path found. Then, using the weight from the last node before reaching t,
we populate the corresponding edges in Flow with it. We take the weight put into our flow graph and subtract it by the weights in the Residual graph.
If any weights are zero, we delete the node. We repeat this process until there is no way to get from s to t.
Then, we find the Maximum Flow by summing the weights of the edges leading to t in our Flow graph.

However, we must keep these conditions in mind for when finding a path:
Suppose we have vertices x, y, and z in a path that starts at source node s and ends at sink node t where x and y cannot be s or t. 
- Condition 1: If edge (x, y) has a weight less than the weight of edge (y, z), then change (y, z)’s weight to be equal to (x, y)’s weight.
- Condition 2: If an edge (x, t) for some node x has an in-degree greater than one, there exists another path in the graph that leads to the sink node t. Delete the edge in the augmented path that leads to edge (x, t), but not (x, t) itself (i.e. decrease the in-degree of x before deleting (x, t)). 
- Condition 3: If the edge (x, z) has a weight that is less than an edge (y, z) such that x is not equal to y, change the weight of the edges coming from z to be (x, z)’s weight.

The algorithm is based off of the directed graph given below:

![alt text](https://i.imgur.com/kl10LVw.png)

(Diagram from Mark Allen Weiss Data Structures & Algorithm Analysis in C++)
