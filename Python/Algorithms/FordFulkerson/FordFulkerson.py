class Graph:
    def __init__(self):
        self.adjacencyLists = {}
        self.weightByEdge = {}
        self.edgeCount = 0
        self.nodeCount = 0

    def initializeVertices(self, upperBound):
        # Method to initialize nodes with letters instead of numbers
        letter = 97

        for i in range(0, upperBound):
            if i == 0:
                self.adjacencyLists.update({'s': []})
            elif 0 < i < upperBound - 1:
                self.adjacencyLists.update({chr(letter): []})
                letter += 1
            else:
                self.adjacencyLists.update({'t': []})

            self.nodeCount += 1

    def addAdjecents(self, node, adjacents):
        adjacents = adjacents.split()
        for adj in adjacents:
            self.adjacencyLists[node].append(adj)

        self.edgeCount += len(self.adjacencyLists[node])

    def createEdges(self):
        for node in self.adjacencyLists:
            for adj in self.adjacencyLists[node]:
                self.weightByEdge.update({'({}, {})'.format(node, adj): 0})

    def addWeight(self, edge, weight):
        self.weightByEdge.update({edge: weight})

    def getNumberOfNodes(self):
        return self.nodeCount

    def getNumberOfEdges(self):
        return self.edgeCount

    def getWeight(self, edge):
        return self.weightByEdge[edge]

    def print(self):
        print(self.adjacencyLists)

    def depthFirstSearch(self, initialNode, targetNode, nodesVisited, order):
        # Depth First Search modified to only search for a single path

        index = list(self.adjacencyLists).index(targetNode)
        nodesVisited[index] = True

        order.append(targetNode)

        # If we hit a dead-end (i.e. The node cannot edge to any other ones) or we hit the sink node, return path
        if len(self.adjacencyLists[order[-1]]) == 0 or 't' in order:
            return

        for node in self.adjacencyLists[targetNode]:
            # Recursion Safety Net when a dead-end is hit
            if len(self.adjacencyLists[order[-1]]) == 0 or 't' in order:
                return

            index = list(self.adjacencyLists).index(node)

            if not nodesVisited[index]:
                self.depthFirstSearch(initialNode, node, nodesVisited, order)

    def convertToEdges(self, order):
        for i in range(len(order) - 1):
            for edge in self.weightByEdge:
                if order[i] in edge and order[i + 1] in edge:
                    order[i] = edge

        order.pop(len(order) - 1)
        return order

    def fordFulkerson(self):
        residual = copyGraph(self)
        flow = copyGraph(self)

        print("Original Graph: {}".format(self.weightByEdge))

        # Initializing Flow to Zero
        for edge in flow.weightByEdge:
            flow.weightByEdge.update({edge: 0})

        print("Zero-Flow Graph: {}".format(flow.weightByEdge))

        while True:
            # Checks for edges from source node
            if len(residual.adjacencyLists['s']) == 0:
                break

            # Checks for edges to sink node
            toSink = False

            for node in residual.adjacencyLists:
                if 't' in residual.adjacencyLists[node]:
                    toSink = True

            if not toSink:
                break

            # Ford Fulkerson Algorithm
            visited = [False] * self.getNumberOfNodes()
            order = []

            hasSource = False
            hasSink = False

            residual.depthFirstSearch('s', 's', visited, order)
            orderEdges = residual.convertToEdges(order)

            for edge in orderEdges:
                if 's' in edge:
                    hasSource = True
                if 't' in edge:
                    hasSink = True

            if hasSource and hasSink:  # Checks if our path starts from the source and ends at the sink
                augPath = orderEdges
                flowWeight = residual.getWeight(augPath[0])

                # (EC) Condition 2 and 3: Getting Vertices that Edge-In (Inverse Adjacency Lists)
                transposeGraph = copyGraph(residual)

                for key1 in residual.adjacencyLists:
                    edgeIns = []

                    for key2 in residual.adjacencyLists:
                        if key1 in residual.adjacencyLists[key2]:
                            edgeIns.append(key2)

                    transposeGraph.adjacencyLists.update({key1: edgeIns})

                # Checks for lower flow weight along path and updates
                for edge in augPath:
                    lockedWeight = False  # (EC) Condition 3: Safety Net for non-changing weight
                    edgeIn = edge[4]

                    # (EC) Condition 3: Check for other adjacent edges and if they have a lower weight
                    if len(transposeGraph.adjacencyLists[edgeIn]) > 1:
                        for node in transposeGraph.adjacencyLists[edgeIn]:
                            if transposeGraph.weightByEdge["({}, {})".format(node, edgeIn)] < flowWeight:
                                flowWeight = transposeGraph.weightByEdge["({}, {})".format(node, edgeIn)]
                                lockedWeight = True

                    # (EC) Condition 1: Standard Flow Weight Check
                    if not lockedWeight:
                        if residual.getWeight(edge) < flowWeight:
                            flowWeight = residual.getWeight(edge)
                        elif residual.getWeight(edge) > flowWeight:
                            residual.weightByEdge[edge] = flowWeight

                flowWeight = residual.getWeight(augPath[-1])

                # Update the weights in flow and residual graphs along the augmented path
                for edge in augPath:
                    flow.weightByEdge[edge] += flowWeight

                for edge in residual.weightByEdge:
                    if edge in augPath:
                        residual.weightByEdge[edge] -= flowWeight

                        if residual.weightByEdge[edge] < 0:
                            residual.weightByEdge[edge] *= (-1)

                # Getting rid of the zeroes
                zeroEdges = []

                for edge in residual.weightByEdge:
                    if residual.weightByEdge[edge] == 0:
                        zeroEdges.append(edge)

                # Updating our Transpose Graph
                transposeGraph = copyGraph(residual)

                for key1 in residual.adjacencyLists:
                    edgeIns = []

                    for key2 in residual.adjacencyLists:
                        if key1 in residual.adjacencyLists[key2]:
                            edgeIns.append(key2)

                    transposeGraph.adjacencyLists.update({key1: edgeIns})

                # Removing the appropriate zero edges
                for edge in zeroEdges:
                    sinkFound = False  # sinkFound kill switch to signify we do not need to search anymore
                    edgeOut = edge[1]
                    edgeIn = edge[4]

                    # (EC) Condition 2: Full Condition Encapsulated in this if-else block
                    if len(transposeGraph.adjacencyLists[edgeIn]) > 1:
                        for node in transposeGraph.adjacencyLists[edgeIn]:
                            # Checks for more paths leading to the current edge
                            if "({}, {})".format(node, edgeIn) in zeroEdges:
                                residual.weightByEdge.pop("({}, {})".format(node, edgeIn))
                                residual.adjacencyLists[node].remove(edgeIn)

                                # If the next edge leads to the sink node, restore its weight
                                if edgeIn != 't' and "({}, t)".format(edgeIn) in zeroEdges:
                                    residual.weightByEdge["({}, t)".format(edgeIn)] = flowWeight
                                    sinkFound = True

                                if sinkFound:
                                    break
                    else:   # Treat as normal edge
                        residual.weightByEdge.pop(edge)
                        residual.adjacencyLists[edgeOut].remove(edgeIn)

                    if sinkFound:
                        break

            elif hasSource and not hasSink:  # Checks if we hit our dead-end is the sink node
                toDelete = orderEdges[-1]

                edgeOut = toDelete[1]
                edgeIn = toDelete[4]

                residual.weightByEdge.pop(toDelete)
                residual.adjacencyLists[edgeOut].remove(edgeIn)

            else:  # Any other cases show no more source-to-sink paths exist
                break

        maximumFlow = 0

        # Calculating the Maximum Flow
        for edge in flow.weightByEdge:
            if 't' in edge:
                maximumFlow += flow.weightByEdge[edge]

        print("Final Flow Graph: {}".format(flow.weightByEdge))
        print("Residual Graph: {}\n".format(residual.weightByEdge))
        return maximumFlow


def copyGraph(graph):
    copy = Graph()
    copy.initializeVertices(graph.getNumberOfNodes())

    for node in copy.adjacencyLists:
        copy.adjacencyLists.update({node: graph.adjacencyLists[node]})

    for edge in graph.weightByEdge:
        copy.weightByEdge.update({edge: graph.weightByEdge[edge]})

    return copy


if __name__ == '__main__':
    ourGraph = Graph()

    ourGraph.initializeVertices(6)
    ourGraph.addAdjecents('s', "a b")
    ourGraph.addAdjecents('a', "c d b")
    ourGraph.addAdjecents('b', 'd')
    ourGraph.addAdjecents('c', 't')
    ourGraph.addAdjecents('d', 't')

    ourGraph.createEdges()
    ourGraph.addWeight('(s, a)', 4)
    ourGraph.addWeight('(s, b)', 2)
    ourGraph.addWeight('(a, c)', 2)
    ourGraph.addWeight('(a, d)', 4)
    ourGraph.addWeight('(a, b)', 1)
    ourGraph.addWeight('(b, d)', 2)
    ourGraph.addWeight('(c, t)', 3)
    ourGraph.addWeight('(d, t)', 3)

    ourMaximumFlow = ourGraph.fordFulkerson()
    print("Our Maximum Flow is {}".format(ourMaximumFlow))

# References:
# (1) Textbook and Slides
# (2) Max Flow Visualization | http://isabek.github.io/
