import org.example.domainmodel.Character;
import org.example.domainmodel.GrabAction;
import org.junit.jupiter.api.Test;
import org.example.domainmodel.*;
import static org.junit.jupiter.api.Assertions.*;

public class DomainModelTest {

    @Test
    void testGrabAction() {
        Character trilian = new Character("Триллиан");
        GrabAction grabAction = new GrabAction(trilian, "руку");
        grabAction.execute();
        assertTrue(trilian.isGrabbed(), "Триллиан не схватила руку");
    }

    @Test
    void testPullAction() {
        Character trilian = new Character("Триллиан");
        PullAction pullAction = new PullAction(trilian, "дверь");
        pullAction.execute();
        assertTrue(trilian.isPulled(), "Триллиан не потянула дверь");
    }

    @Test
    void testTryToOpenAction() {
        Character ford = new Character("Форд");
        Character zafod = new Character("Зафод");
        TryToOpenAction tryToOpenAction = new TryToOpenAction(ford, "дверь");
        TryToOpenAction tryToOpenAction2 = new TryToOpenAction(zafod, "дверь");
        tryToOpenAction.execute();
        tryToOpenAction2.execute();
        assertTrue(ford.isTriedToOpen() && zafod.isTriedToOpen(), "Форд и Зафод не пытались открыть дверь");
    }

    @Test
    void testHypnotizeAction() {
        Character arthur = new Character("Артур");
        HypnotizeAction hypnotizeAction = new HypnotizeAction(arthur);
        hypnotizeAction.execute();
        assertTrue(arthur.isHypnotized(), "Артур не был загипнотизирован");
    }
}
