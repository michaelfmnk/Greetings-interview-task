import com.michaelfmnk.greeting.DayPart;
import com.michaelfmnk.greeting.TimeZoneUtils;
import org.joda.time.DateTimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.TimeZone;

import static com.michaelfmnk.greeting.DayPart.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class TZUtilsTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                    1522517945964L, // Saturday, March 31, 2018 5:50:17 PM GMT
                    "kiev", // +2 hours
                    EVENING,
                    TimeZone.getTimeZone("GMT+2")
                },
                {
                    1331549192000L, // March 12, 2012 10:46:32 AM GMT
                    "new york", // -4 hours
                    MORNING,
                    TimeZone.getTimeZone("GMT-4")

                },
                {
                    1522479324000L, // Saturday, March 31, 2018 6:55:24 AM GMT
                    "Mexico City", // -6 hours
                    NIGHT,
                    TimeZone.getTimeZone("GMT-6")
                },
                {
                    1270079724000L, // Wednesday, March 31, 2010 11:55:24 PM GMT
                    "honolulu", // -10 hours
                     DAYTIME,
                     TimeZone.getTimeZone("GMT-10")
                }
        });
    }
    String city;
    DayPart dayPart;
    TimeZone timeZone;

    public TZUtilsTest(long currentTimeMillis, String city, DayPart dayPart, TimeZone timeZone){
        DateTimeUtils.setCurrentMillisFixed(currentTimeMillis);
        this.city = city;
        this.dayPart = dayPart;
        this.timeZone = timeZone;
    }

    @Test
    public void dayPartByCityNameTest(){
        DayPart dayPart = TimeZoneUtils.getDayPart(city);
        assertEquals(this.dayPart, dayPart);
    }

    @Test
    public void dayPartByTimeZoneTest(){
        DayPart dayPart = TimeZoneUtils.getDayPart(timeZone);
        assertEquals(this.dayPart, dayPart);
    }

}
