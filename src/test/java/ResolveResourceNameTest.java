import com.michaelfmnk.greeting.DayPart;
import com.michaelfmnk.greeting.HelloMessageProvider;
import com.michaelfmnk.greeting.Locale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static com.michaelfmnk.greeting.DayPart.*;
import static com.michaelfmnk.greeting.Locale.EN;
import static com.michaelfmnk.greeting.Locale.RU;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class ResolveResourceNameTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { MORNING, RU, "message.morning.ru"}, { DAYTIME, EN, "message.daytime.en"}, { EVENING, EN, "message.evening.en"},
                { NIGHT, RU, "message.night.ru"}

        });
    }


    DayPart dayPart;
    Locale locale;
    String expectedVal;

    public ResolveResourceNameTest(DayPart dayPart, Locale locale, String expectedVal){
        this.dayPart = dayPart;
        this.locale = locale;
        this.expectedVal = expectedVal;
    }

    @Test
    public void test() throws Exception {

        HelloMessageProvider mock = new HelloMessageProvider("");
        Method method = HelloMessageProvider.class.getDeclaredMethod("resolveResourceName",DayPart.class, Locale.class);
        method.setAccessible(true);
        assertEquals(expectedVal, method.invoke(mock, dayPart, locale));

    }
}
