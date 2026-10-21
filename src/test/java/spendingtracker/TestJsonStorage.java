package spendingtracker;

import org.junit.jupiter.api.Test;

import spendingtracker.Category;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.beans.Transient;
import java.io.File;

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
}