import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HippodromeTest {
    @Test
    public void constructorShouldReturnExceptionWhenParamIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void constructorShouldEqualsExceptionMessageWhenParamIsNull() {
        try {
            new Hippodrome(null);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Horses cannot be null.");
        }
    }

    @Test
    public void constructorShouldReturnExceptionWhenParamIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void constructorShouldEqualsExceptionMessageWhenParamIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        try {
            new Hippodrome(horses);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Horses cannot be empty.");
        }
    }

    @Test
    public void getHorsesShouldReturnSameListHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Hors" + i, 1 + i, 2 + i));
        }
        List<Horse> horseList = new Hippodrome(horses).getHorses();
        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), horseList.get(i));
        }
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void moveShouldCallMoveEachHorse() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(hippodrome.getHorses().get(i)).move();
        }
    }

    @Test
    public void getWinnerShouldReturnHorseWithMaxDistance() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            horses.add(new Horse("Hors" + i, 1 + i, 2 + i));
        }
        Horse winner = new Hippodrome(horses).getWinner();
        assertEquals(11, winner.getDistance());
    }
}