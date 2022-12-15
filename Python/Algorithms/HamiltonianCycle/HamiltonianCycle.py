class Graph:
    def __init__(self):
        self.adjacencyLists = {}
        self.nodeCount = 0
        self.hamiltonianCyclesFound = []

    def initializeVertices(self, upperBound):
        for i in range(0, upperBound):
            self.adjacencyLists.update({i: []})
            self.nodeCount += 1

    def addAdjecents(self, node, adjacents):
        adjacents = adjacents.split()
        for adj in adjacents:
            self.adjacencyLists[node].append(int(adj))

    def findHamCycles(self):
        print("Graph Represented by Adjacency List: {}".format(self.adjacencyLists))

        order = []
        visited = [False] * self.nodeCount

        # Searches for all possible Hamiltonian Cycles
        for node in self.adjacencyLists:
            self.depthFirstSearch(node, node, visited, order)

        if len(self.hamiltonianCyclesFound) > 0 and self.nodeCount >= 3:
            print("Hamiltonian Cycle Found")
            print("Cycle Found: {}\n".format(self.hamiltonianCyclesFound[0]))

        else:
            print("No Hamiltonian Cycle Found\n")

    # Depth-First-Search-based algorithm to find cycles
    def depthFirstSearch(self, initialNode, targetNode, nodesVisited, order):
        nodesVisited[targetNode] = True
        order.append(targetNode)

        for node in self.adjacencyLists[targetNode]:
            if not nodesVisited[node]:
                self.depthFirstSearch(initialNode, node, nodesVisited, order)

                nodesVisited[node] = False
                order.remove(node)
            else:
                if node == initialNode:
                    if False not in nodesVisited:
                        foundCycle = order * 1
                        foundCycle.append(initialNode)
                        self.hamiltonianCyclesFound.append(foundCycle)


if __name__ == '__main__':
    print("Hamiltonian Graph\n")
    hamGraph = Graph()
    hamGraph.initializeVertices(5)

    hamGraph.addAdjecents(0, "1 2 3 4")
    hamGraph.addAdjecents(1, "0 2 3 4")
    hamGraph.addAdjecents(2, "0 1 3 4")
    hamGraph.addAdjecents(3, "0 1 2 4")
    hamGraph.addAdjecents(4, "0 1 2 3")

    hamGraph.findHamCycles()

    print("Non-Hamiltonian Graph\n")
    nonHamGraph = Graph()
    nonHamGraph.initializeVertices(5)

    nonHamGraph.addAdjecents(0, "1 3")
    nonHamGraph.addAdjecents(1, "0 2 3 4")
    nonHamGraph.addAdjecents(2, "1 4")
    nonHamGraph.addAdjecents(3, "0 1")
    nonHamGraph.addAdjecents(4, "1 2")

    nonHamGraph.findHamCycles()

# References
# (1) Textbook & Slides
# (2) Hamiltonian Cycle | Backtracking-6 https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/
