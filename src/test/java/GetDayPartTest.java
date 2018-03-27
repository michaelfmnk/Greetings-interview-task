import com.michaelfmnk.greeting.DayPart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static com.michaelfmnk.greeting.DayPart.EVENING;
import static com.michaelfmnk.greeting.DayPart.NIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class GetDayPartTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, NIGHT }, { 17, DayPart.DAYTIME}, {24, NIGHT}, {20,EVENING}
        });
    }

    private int hour;
    private DayPart expectedDaypart;

    public GetDayPartTest(int hour, DayPart expectedDaypart){
        this.hour = hour;
        this.expectedDaypart = expectedDaypart;
    }



    @Test
    public void hell() throws Exception{
        Method method = DayPart.class.getDeclaredMethod("getDayPart", int.class);
        method.setAccessible(true);
        assertEquals(expectedDaypart, method.invoke(null, hour));
    }
}
