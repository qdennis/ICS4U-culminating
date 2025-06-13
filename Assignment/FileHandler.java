import java.io.*;
import java.util.*;

public class FileHandler {
    
    // Read all users from file (implement this based on your file format)
    public static List<user> readAllUsers(String filename) {
        List<user> users = new ArrayList<>();
        // TODO: parse file lines to create user objects and add to users list
        return users;
    }

    // Save all users back to file (overwrite entire file)
    public static void saveAllUsers(String filename, List<user> users) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (user u : users) {
                writer.write(formatUserData(u)); // write user data as string
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Format user object to the string matching your file format
    private static String formatUserData(user u) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(u.getName()).append("\n");
        sb.append("Password: ").append(u.getPassword()).append("\n");
        sb.append("Account Number: ").append(u.getCardNumber()).append("\n");
        for (Account acc : u.getAccount()) {
            if (acc.getAccountType().equals("Checking")) {
                sb.append("Checking Balance: ").append(acc.getBalance()).append("\n");
            } else if (acc.getAccountType().equals("Savings")) {
                sb.append("Savings Balance: ").append(acc.getBalance()).append("\n");
            }
        }
        sb.append("---------------------------\n");
        return sb.toString();
    }

    // Update one user in the list and write all back
    public static void updateUserInFile(String filename, user updatedUser) {
        List<user> users = readAllUsers(filename);
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCardNumber().equals(updatedUser.getCardNumber())) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }
        if (!found) {
            // User not found — optionally add it
            users.add(updatedUser);
        }
        saveAllUsers(filename, users);
    }
}