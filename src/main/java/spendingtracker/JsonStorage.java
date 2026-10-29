package spendingtracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class JsonStorage {

    private static final String FILE_PATH = "transactions.json";
    private final Gson gson = new Gson();

    public void save(List<Transaction> transactions) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(transactions, writer);
        } catch (Exception e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public List<Transaction> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Transaction>>(){}.getType();
            List<Transaction> list = gson.fromJson(reader, listType);
            return list != null ? list : List.of();
        } catch (Exception e) {
            return List.of();
        }
    }
}