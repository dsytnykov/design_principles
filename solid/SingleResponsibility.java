
public class SingleResponsibility {

}


//Wrong
/*public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Bad practice: Handling file storage within the User class
    public void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(name + ".txt")) {
            fileWriter.write("Name: " + name + "\n");
            fileWriter.write("Email: " + email + "\n");
            System.out.println("User data saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/


//CORRECT
/*public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// UserFileManager.java
import java.io.FileWriter;
import java.io.IOException;

public class UserFileManager {
    public void saveToFile(User user) {
        try (FileWriter fileWriter = new FileWriter(user.getName() + ".txt")) {
            fileWriter.write("Name: " + user.getName() + "\n");
            fileWriter.write("Email: " + user.getEmail() + "\n");
            System.out.println("User data saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", "john.doe@example.com");
        UserFileManager fileManager = new UserFileManager();
        fileManager.saveToFile(user);
    }
}*/
