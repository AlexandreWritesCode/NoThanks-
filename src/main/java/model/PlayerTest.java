package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class PlayerTest {
    @Test
    void testNewPlayer(){
        Player p = new Player(7, "Berry Bankey");
        assertEquals(p.getName(), "Berry Bankey");
    }
}
