import com.michaelfmnk.greeting.utils.HelloMessageProvider;
import com.michaelfmnk.greeting.utils.TimeZoneUtils;
import org.joda.time.DateTimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.michaelfmnk.greeting.utils.DayPart.DAYTIME;
import static com.michaelfmnk.greeting.utils.DayPart.NIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Time;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MessageProviderTest {
    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {
                        1522517945964L, // Saturday, March 31, 2018 5:50:17 PM GMT
                        "london",
                        "Etc/GMT",
                        "en",
                        "Good Day, London!"
                },
                {
                        1270079724000L, // Wednesday, March 31, 2010 11:55:24 PM GMT
                        "honolulu", // -10 hours,
                        null,
                        "ua",
                        "Доброго дня, Honolulu!"
                },
                {
                        1522479324000L, // Saturday, March 31, 2018 6:55:24 AM GMT
                        "mexico CITY", // -6 hours
                        null,
                        "ru",
                        "Доброй ночи, Mexico City!"
                }
        });
    }

    private String city;
    private String message;
    private String timeZone;

    public MessageProviderTest(long currentTimeMillis, String city, String timeZone, String locale, String message) {
        DateTimeUtils.setCurrentMillisFixed(currentTimeMillis);
        Locale.setDefault(new Locale(locale));
        this.city = city;
        this.timeZone = timeZone;
        this.message = message;
    }

    @Test
    public void messageProviderTest(){
        HelloMessageProvider messageProvider = new HelloMessageProvider(city, timeZone);
        assertEquals(message, messageProvider.getMessage());

    }
}
