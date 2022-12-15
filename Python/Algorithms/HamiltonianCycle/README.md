# Hamiltonian Cycle Algorithm

A Hamiltonian Cycle is a cycle where all vertices in a graph are met exactly once. It starts and ends at the starting vertex.

In the algorithm, it uses a Depth-First-Search that behaves like a Backtracking algorithm. Any cycles found will be appended to an array containing Hamiltonian Cycles within the graph.
If the array is empty, the graph is not Hamiltonian. If it has at least one element, the graph is Hamiltonian.
