import org.junit.jupiter.api.Test;
import ru.m210projects.IntArrayList;
import ru.m210projects.Main;

import java.util.Arrays;

public class MainTest {

    @Test
    public void test() {
        IntArrayList list = new IntArrayList(Main.generateRandomArray(100_000));

        long start = System.currentTimeMillis();
        list.sortBubble(list.toArray()); // 38943 ms
        System.out.println("sortBubble " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        list.sortSelection(list.toArray()); // 7743 ms
        System.out.println("sortSelection " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        list.sortInsertion(list.toArray()); // 11997 ms
        System.out.println("sortInsertion " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        Arrays.sort(list.toArray()); // 39 ms
        System.out.println("Arrays.sort " + (System.currentTimeMillis() - start) + " ms");
    }

}
