import java.util.Random;
import java.util.Scanner;

public class NimGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Initialize three heaps with random sizes
        int heap1 = 3;
        int heap2 = 5;
        int heap3 = 7;

        System.out.println("Welcome to Nim! The heaps are: ");
        printHeaps(heap1, heap2, heap3);

        boolean player1Turn = true; // Player 1 starts

        while (true) {
            if (player1Turn) {
                // Player's turn
                playerMove(scanner, heap1, heap2, heap3);
            } else {
                // Computer's turn
                computerMove(random, heap1, heap2, heap3);
            }

            // Print the updated heaps
            printHeaps(heap1, heap2, heap3);

            // Check for a winner
            if (heap1 == 0 && heap2 == 0 && heap3 == 0) {
                System.out.println((player1Turn ? "Player 1" : "Computer") + " wins!");
                break;
            }

            // Switch turns
            player1Turn = !player1Turn;
        }

        scanner.close();
    }

    private static void printHeaps(int heap1, int heap2, int heap3) {
        System.out.println("Heap 1: " + displayHeaps(heap1) + "  Heap 2: " + displayHeaps(heap2) + "  Heap 3: " + displayHeaps(heap3));
    }

    private static String displayHeaps(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append("| ");
        }
        return result.toString().trim();
    }

    private static void playerMove(Scanner scanner, int heap1, int heap2, int heap3) {
        int heapIndex;
        int stonesToRemove;

        do {
            System.out.print("Player 1, enter heap index (1, 2, or 3): ");
            heapIndex = scanner.nextInt();
        } while (heapIndex < 1 || heapIndex > 3);

        do {
            System.out.print("Enter the number of stones to remove: ");
            stonesToRemove = scanner.nextInt();
        } while (stonesToRemove < 1 || stonesToRemove > Math.max(heap1, Math.max(heap2, heap3)));

        // Update the selected heap
        switch (heapIndex) {
            case 1:
                heap1 -= stonesToRemove;
                break;
            case 2:
                heap2 -= stonesToRemove;
                break;
            case 3:
                heap3 -= stonesToRemove;
                break;
        }
    }

    private static void computerMove(Random random, int heap1, int heap2, int heap3) {
        System.out.println("Computer's turn:");

        // Generate random move
        int heapIndex;
        do {
            heapIndex = random.nextInt(3) + 1;
        } while ((heapIndex == 1 && heap1 == 0) || (heapIndex == 2 && heap2 == 0) || (heapIndex == 3 && heap3 == 0));

        int stonesToRemove = random.nextInt(Math.max(heap1, Math.max(heap2, heap3))) + 1;

        // Update the selected heap
        switch (heapIndex) {
            case 1:
                heap1 -= stonesToRemove;
                break;
            case 2:
                heap2 -= stonesToRemove;
                break;
            case 3:
                heap3 -= stonesToRemove;
                break;
        }

        System.out.println("Computer removes " + stonesToRemove + " stones from Heap " + heapIndex);
    }
}
