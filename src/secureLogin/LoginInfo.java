package secureLogin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoginInfo {
	private HashMap<String, String> loginData;
    private static final String FILE_PATH = "C:\\Users\\jai\\eclipse-workspace\\Login_System\\src\\LS\\user_credentials.txt";

    LoginInfo() {
        loginData = new HashMap<>();
        readUserDataFromFile();
    }

    public HashMap<String, String> getData() {
        return loginData;
    }

    private void readUserDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    loginData.put(parts[0], parts[1]);
                } else {
                    // Handle invalid format or empty lines
                    System.err.println("Invalid line format: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
