package spendingtracker;

import org.junit.jupiter.api.Test;

import spendingtracker.Category;
import spendingtracker.JsonStorage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.File;
import java.io.FileWriter;

public class TestJsonStorage  {

    @Test 
    void testSaveCreatesFile() {
        JsonStorage storage = new JsonStorage();
        List<Transaction> list = List.of(
            new Transaction(10, Category.TAXI, "21-10-2026 13:00")
        );
        storage.save(list);

        File f = new File("transactions.json");
        assertTrue(f.exists());
    }

    @Test 
    void testLoadReturnsTransaction() {
        JsonStorage storage = new JsonStorage();
        List<Transaction> list = List.of(
            new Transaction(10, Category.FUEL, "21-10-2026 13:30")
        );
        storage.save(list);

        List<Transaction> loaded = storage.load();

        assertEquals(1, loaded.size());
    }

    @Test
    void testLoadWhenFileMissingReturnsEmptyList() {
        File f = new File("transactions.json");
        if (f.exists()) f.delete();

        JsonStorage storage = new JsonStorage();
        List<Transaction> loaded = storage.load();

        assertEquals(0, loaded.size());
    }

    @Test
    void testLoadHandlesCorruptedJson() throws Exception {
        FileWriter writer = new FileWriter("transactions.json");
        writer.write("{ this is not valid json");
        writer.close();

        JsonStorage storage = new JsonStorage();
        List<Transaction> loaded = storage.load();

        assertEquals(0, loaded.size());
    }
}