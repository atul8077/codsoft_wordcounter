import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class wordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Counting Application");
        System.out.println("1. Enter text");
        System.out.println("2. Provide a file");
        System.out.print("Choose an option (1/2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String text = "";

        if (choice == 1) {
            System.out.print("Enter text: ");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        String[] words = text.split("[\\s\\p{Punct}]+");
        int totalWords = words.length;
        System.out.println("Total word count: " + totalWords);

        // Additional features:
        // Count unique words and their frequencies
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
        }

        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}