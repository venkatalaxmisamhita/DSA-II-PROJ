import java.util.*;

class ZombieInfectionSimulation {

    static class Pair {

        String node;
        int depth;

        Pair(String node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static void bfsInfection(
            Map<String, List<String>> graph,
            String source,
            int maxDepth) {

        Queue<Pair> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        queue.add(new Pair(source, 0));

        visited.add(source);

        System.out.println("Zombie Infection Spread:\n");

        while (!queue.isEmpty()) {

            Pair current = queue.poll();

            System.out.println(
                    "Zone: " + current.node +
                    " | Infection Depth: " + current.depth);

            if (current.depth == maxDepth)
                continue;

            for (String neighbor :
                    graph.getOrDefault(current.node,
                            new ArrayList<>())) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    queue.add(
                            new Pair(
                                    neighbor,
                                    current.depth + 1));
                }
            }
        }

        System.out.println(
                "\nTotal Infected Zones: "
                        + visited.size());
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new HashMap<>();

        graph.put("A",
                Arrays.asList("B", "C"));

        graph.put("B",
                Arrays.asList("D", "E"));

        graph.put("C",
                Arrays.asList("E", "F"));

        graph.put("D",
                Arrays.asList("G"));

        graph.put("E",
                Arrays.asList("G", "H"));

        graph.put("F",
                Arrays.asList("H", "I"));

        bfsInfection(graph, "A", 3);
    }
}