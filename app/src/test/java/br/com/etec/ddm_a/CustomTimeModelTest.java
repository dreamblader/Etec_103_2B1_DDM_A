package br.com.etec.ddm_a;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import br.com.etec.ddm_a.model.CustomTime;

import static org.junit.Assert.assertEquals;

public class CustomTimeModelTest {

    CustomTime testTime;

    @Before
    public void setup(){
        testTime = new CustomTime(12,30);
    }

    @Test
    public void testSetFromCurrentDiff(){
        Calendar current = Calendar.getInstance();
        int currentHour = current.get(Calendar.HOUR_OF_DAY);
        int currentMinute = current.get(Calendar.MINUTE);
        CustomTime calendarTime = new CustomTime(currentHour, currentMinute);

        CustomTime expected = testTime.setFromDiff(calendarTime);
        CustomTime actual = testTime.setFromCurrentDiff();

        assertEquals(expected.getHour(), actual.getHour());
        assertEquals(expected.getMinutes(), actual.getMinutes());
    }

    @Test
    public void testSetFromDiff_OnlyMinutes(){
        CustomTime diffTime = new CustomTime(12, 0);

        CustomTime expected = new CustomTime(0, 30);
        CustomTime actual = testTime.setFromDiff(diffTime);

        assertEquals(expected.getHour(), actual.getHour());
        assertEquals(expected.getMinutes(), actual.getMinutes());
    }

    @Test
    public void testSetFromDiff_RollbackADay(){
        CustomTime diffTime = new CustomTime(13, 0);

        CustomTime expected = new CustomTime(23, 30);
        CustomTime actual = testTime.setFromDiff(diffTime);

        assertEquals(expected.getHour(), actual.getHour());
        assertEquals(expected.getMinutes(), actual.getMinutes());
    }

    @Test
    public void testSetFromDiff_OnlyHours(){
        CustomTime diffTime = new CustomTime(11, 30);

        CustomTime expected = new CustomTime(1, 0);
        CustomTime actual = testTime.setFromDiff(diffTime);

        assertEquals(expected.getHour(), actual.getHour());
        assertEquals(expected.getMinutes(), actual.getMinutes());
    }
}
