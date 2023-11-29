import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @ParameterizedTest
    @CsvSource({", 1,1.1"})
    public void constructorShouldReturnExceptionWhenNameIsNull(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource(value = {"null, 1, 1.1"}, nullValues = "null")
    public void constructorShouldEqualsExceptionMassageWhenNameIsNull(String name, double speed, double distance) {
        try {
            new Horse(name, speed, distance);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Name cannot be null.");
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"'  ', 1,1.1", "'', 1,1.1", "'        ', 1,1.1"})
    public void constructorShouldEReturnExceptionWhenNameIsSpace(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource(value = {"'  ', 1,1.1", "'', 1,1.1", "'        ', 1,1.1"})
    public void constructorShouldEqualsExceptionMassageWhenNameIsSpace(String name, double speed, double distance) {
        try {
            new Horse(name, speed, distance);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Name cannot be blank.");
        }
    }

    @ParameterizedTest
    @CsvSource({"name, -1, 1.1"})
    public void constructorShouldReturnExceptionWhenSpeedIsNegative(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource({"name, -1, 1.1"})
    public void constructorShouldEqualsExceptionMassageWhenSpeedIsNegative(String name, double speed, double distance) {
        try {
            new Horse(name, speed, distance);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Speed cannot be negative.");
        }
    }

    @ParameterizedTest
    @CsvSource({"name, 1, -1.1"})
    public void constructorShouldReturnExceptionWhenDistanceIsNegative(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource({"name, 1, -1.1"})
    public void constructorShouldEqualsExceptionMassageWhenDistanceIsNegative(String name, double speed, double distance) {
        try {
            new Horse(name, speed, distance);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Distance cannot be negative.");
        }
    }

    @Test
    public void getNameShouldReturnName() {
        String name = "Buran";
        assertEquals(name, new Horse(name, 1.1, 1.1).getName());
    }

    @Test
    public void getSpeedShouldReturnSpeed() {
        double speed = 1.1;
        assertEquals(speed, new Horse("Buran", speed, 1.1).getSpeed());
    }

    @Test
    public void getDistanceShouldReturnDistance() {
        double distance = 1.1;
        assertEquals(distance, new Horse("Buran", 1.1, distance).getDistance());
        assertEquals(0, new Horse("Buran", 1.1).getDistance());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void moveShouldCallStaticMethodGetRandomDouble() {
        Horse mockHorse = new Horse("Lipa", 1, 2);
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            mockHorse.move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ExtendWith(MockitoExtension.class)
    @CsvSource(value = {"2,3,1,5", "3,4,2,10", "4,5,3,17"})
    public void moveShouldPutCorrectVariableForDistanceValue(double speed, double distance, double randomValue, double expected) {
        Horse mockHorse = new Horse("Lipa", speed, distance);
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            mockHorse.move();
            assertEquals(expected, mockHorse.getDistance());
        }
    }
}
