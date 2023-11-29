import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Test
    @Timeout(22)
    public void mainShouldExecuteFor22Second() {
        try {
            String[] arguments = new String[]{};
            Main.main(arguments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
