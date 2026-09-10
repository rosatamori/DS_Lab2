import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;


class CalendarEventTest {
	//variables
	
	CalendarEvent A;
	CalendarEvent B;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startB;
	GregorianCalendar endB;
	
	GregorianCalendar repeatA;
	int[] days;
	
	MeetingCalendar calA;
	
	//setting up
	@BeforeEach
	void setUp() throws Exception
	{
		calA = new MeetingCalendar();
		startA = new GregorianCalendar(2026, 2, 4, 4, 0);
		endA = new GregorianCalendar(2026, 2, 4, 5, 0);
		startB = new GregorianCalendar(2006, 12, 19, 14, 30);
		endB = new GregorianCalendar(2006, 12, 19, 16, 30);
		
		A = new OneTimeEvent("A", "Aloc", startA, endA);
		B = new OneTimeEvent("B", "Bloc", startB, endB);
		
		
	}
	
	@Test
	void testOneTimeEventGetters()
	{
		assertEquals("A", A.getDescription());
		assertEquals("Aloc", A.getLocation());
		assertEquals(startA, A.getStartTime());
		assertEquals(endA, A.getEndTime());
	}
	
	@Test
	void testOneTimeEventSetters()
	{
		A.setDescription("newA");
		A.setLocation("newlocA");
		A.setStartTime(startB);
		A.setEndTime(endB);
		
		assertEquals("newA", A.getDescription());
		assertEquals("newlocA", A.getLocation());
		assertEquals(startB, A.getStartTime());
		assertEquals(endB, A.getEndTime());
	}
	
	@Test
	void testScheduleEvent() 
	{
		assertEquals(A, MeetingCalendar.findMeeting(startA));
	}

}
