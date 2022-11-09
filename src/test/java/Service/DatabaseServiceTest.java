/*
 * Author Name: Philip Meshach
 * Date: 06-11-2022
 * Praise The Lord
 */
package Service;

import com.niit.jdp.Service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DatabaseServiceTest {
    DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        databaseService = new DatabaseService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printConnection() {
        String expected = "true";
        String actual = String.valueOf(databaseService.printConnectionStatus());
        assertEquals(expected, actual);
    }

    @Test
    void printConnectionFailure() {
        String expected = "false";
        String actual = String.valueOf(databaseService.printConnectionStatus());
        assertNotEquals(expected, actual);
    }

}
