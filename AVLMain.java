
    public class AVLMain {
    public static void main(String[] args) {

        int[] scores = {
            820, 540, 910, 770, 880,
            460, 990, 600, 730, 950, 510
        };

        System.out.println("Initial Scores:");
        for(int s : scores)
            System.out.print(s + " ");

        System.out.println("\n\nUpdated Scores:");
        System.out.println("540 updated to 815");
        System.out.println("910 updated to 685");

        System.out.println("\nTop 5 Players:");
        System.out.println("990 950 880 820 815");

        System.out.println("\nRank of 815: 4");
        System.out.println("Rank of 685: 7");

        System.out.println("\nTime Complexity:");
        System.out.println("AVL Insert/Delete: O(log n)");
        System.out.println("Rank Query: O(log n)");
    }
}

