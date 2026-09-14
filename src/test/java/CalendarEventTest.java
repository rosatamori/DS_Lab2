import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.MeetingCalendar;

class CalendarEventTest {
	//variables
	MeetingCalendar calA;
	
	CalendarEvent A;
	CalendarEvent D;
	WeeklyEvent B;
	MultiDayPerWeekEvent C;
	PriorityEvent E;
	WeeklyEvent F;
	MultiDayPerWeekEvent G;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startD;
	GregorianCalendar endD;
	GregorianCalendar startB;
	GregorianCalendar endB;
	GregorianCalendar startC;
	GregorianCalendar endC;
	GregorianCalendar startG;
	GregorianCalendar endG;
	GregorianCalendar repeatA;
	GregorianCalendar repeatB;
	GregorianCalendar repeatC;

	int[] days = {1, 2, 4};
	int[] daysB = {2, 3, 5};
	
//setting up -----------------------------------------------------
	@BeforeEach
	void setUp() throws Exception
	{
		calA = new MeetingCalendar();
		
		startA = new GregorianCalendar(2026, 1, 4, 4, 0);
		endA = new GregorianCalendar(2026, 1, 4, 5, 0);
		startD = new GregorianCalendar(2026, 1, 4, 4, 30);
		endD = new GregorianCalendar(2026, 1, 4, 5, 30);
		
		startB = new GregorianCalendar(2006, 10, 6, 14, 30);
		endB = new GregorianCalendar(2006, 10, 6, 16, 30);
		startC = new GregorianCalendar(2027, 3, 5, 4, 0);
		endC = new GregorianCalendar(2027, 3, 5, 5, 0);
		
		startG = new GregorianCalendar(2026, 0, 7, 4, 0);
		endG = new GregorianCalendar(2026, 0, 7, 5, 0);
		
		repeatA = new GregorianCalendar(2027, 4, 20, 12, 00);
		repeatB = new GregorianCalendar(2006, 10, 28, 12, 00);
		repeatC = new GregorianCalendar(2027, 3, 20, 12, 00);
		
		A = new OneTimeEvent("A", "Aloc", startA, endA);
		D = new OneTimeEvent("existingA", "existingAloc", startD, endD);
		E = new PriorityEvent("priority", "priorityLoc", startA, endA);
		F = new WeeklyEvent("newF", "newFloc", startA, endA, repeatA);
		B = new WeeklyEvent("B", "Bloc", startB, endB, repeatB);
		C = new MultiDayPerWeekEvent("C", "Cloc", startC, endC, repeatC, daysB);
		G = new MultiDayPerWeekEvent("newG", "newGloc", startG, endG, repeatA, days);
	
	}
	
//TEST GETTERS AND SETTERS ---------------------------------------
//ONETIMEEVENT
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
//WEEKLY EVENT
	@Test
	void testWeeklyEventGetters()
	{
		assertEquals("B", B.getDescription());
		assertEquals("Bloc", B.getLocation());
		assertEquals(startB, B.getStartTime());
		assertEquals(endB, B.getEndTime());
		assertEquals(repeatB, B.getRepeatUntil());
	}
	
	@Test
	void testWeeklyEventSetters()
	{
		B.setDescription("newB");
		B.setLocation("newlocB");
		B.setStartTime(startA);
		B.setEndTime(endA);
		B.setRepeatUntil(repeatA);
		
		assertEquals("newB", B.getDescription());
		assertEquals("newlocB", B.getLocation());
		assertEquals(startA, B.getStartTime());
		assertEquals(endA, B.getEndTime());
		assertEquals(repeatA, B.getRepeatUntil());
	}

//MULTIDAYPERWEEKEVENT
	@Test
	void testMultiDayPerWeekEventGetters()
	{
		assertEquals("C", C.getDescription());
		assertEquals("Cloc", C.getLocation());
		assertEquals(startC, C.getStartTime());
		assertEquals(endC, C.getEndTime());
		assertEquals(repeatC, C.getRepeatUntil());
		assertEquals(daysB, C.getDays());
	}
	
	@Test
	void testMultiDayPerWeekEventSetters()
	{
		C.setDescription("newB");
		C.setLocation("newlocB");
		C.setStartTime(startB);
		C.setEndTime(endB);
		C.setRepeatUntil(repeatB);
		C.setDays(daysB);
		
		assertEquals("newB", C.getDescription());
		assertEquals("newlocB", C.getLocation());
		assertEquals(startB, C.getStartTime());
		assertEquals(endB, C.getEndTime());
		assertEquals(repeatB, C.getRepeatUntil());
		assertEquals(daysB, C.getDays());
	}

//DISPLACING EVENTS
//OneTimeEvent should not displace existing meetings
	@Test
	void testOneTimeEventNoDisplace()
	{
		//Existing meeting = D, New meeting = A
		D.scheduleEvent(calA);
		//try to schedule A
		A.scheduleEvent(calA);
		//check that details are D
		assertEquals("existingA", calA.findMeeting(startD).getDescription());
		assertEquals("existingAloc", calA.findMeeting(startD).getLocation());
		
	}

//PriorityEvent should displace existing meetings
	@Test
	void testPriorityEventYesDisplace() 
	{
		//existing = D, new = E
		D.scheduleEvent(calA);
		//schedule E
		E.scheduleEvent(calA);
		//check that details are E
		assertEquals("priority", calA.findMeeting(startD).getDescription());
		assertEquals("priorityLoc", calA.findMeeting(startD).getLocation());
	}

//WeeklyEvent should not displace existing meetings
	@Test
	void testWeeklyEventNoDisplace() 
	{
		//Existing meeting = D, New meeting = F
		D.scheduleEvent(calA);
		//try to schedule F
		F.scheduleEvent(calA);
		//check that details are D
		assertEquals("existingA", calA.findMeeting(startD).getDescription());
		assertEquals("existingAloc", calA.findMeeting(startD).getLocation());
	}
	
//MultiDayPerWeekEvent should not displace existing meetings
	@Test
	void testMultiDayPerWeekEventNoDisplace() 
	{
		//Existing meeting = D, New meeting = G
		D.scheduleEvent(calA);
		//try to schedule G
		G.scheduleEvent(calA);
		//check that details are D
		assertEquals("existingA", calA.findMeeting(startD).getDescription());
		assertEquals("existingAloc", calA.findMeeting(startD).getLocation());
	}
	
//REAPEATED EVENTS CHECK
//check that all repeated events are there
	@Test
	void checkAllRepeatedEvents() 
	{
		//schedule repeated event 2006 November 6 - 28
		B.scheduleEvent(calA);
		//check all days
		GregorianCalendar a = new GregorianCalendar(2006, 10, 13, 14, 30);
		GregorianCalendar b = new GregorianCalendar(2006, 10, 20, 14, 30);
		GregorianCalendar c = new GregorianCalendar(2006, 10, 27, 14, 30);
		assertEquals("B", calA.findMeeting(startB).getDescription()); //6th
		assertEquals("B", calA.findMeeting(a).getDescription()); //13th
		assertEquals("B", calA.findMeeting(b).getDescription()); //20th
		assertEquals("B", calA.findMeeting(c).getDescription()); //27th
		
	}

//make sure that repeated events stop when scheduled
	@Test
	void checkRepeatedEventsStop() 
	{
		//schedule repeated event 2006 November 6 - 28
		B.scheduleEvent(calA);
		//check if stopped
		GregorianCalendar d = new GregorianCalendar(2006, 11, 4, 14, 30);
		assertNull(calA.findMeeting(d)); //December 4th
		
	}
	
//make sure multi-day events are not scheduled everyday
	@Test
	void MultiDayDoesNotBookEveryDay()
	{
		//schedule repeated event 2027 April 5 - 20 (Mon, Tue, Thur)
		C.scheduleEvent(calA);
		//check the first week is false
		GregorianCalendar a = new GregorianCalendar(2027, 3, 7, 4, 0); //7th
		GregorianCalendar b = new GregorianCalendar(2027, 3, 9, 4, 0); //9th
		GregorianCalendar c = new GregorianCalendar(2027, 3, 10, 4, 0); //10th
		GregorianCalendar d = new GregorianCalendar(2027, 3, 11, 4, 0); //11th
		assertNull(calA.findMeeting(a));
		assertNull(calA.findMeeting(b));
		assertNull(calA.findMeeting(c));
		assertNull(calA.findMeeting(d));
		
	}
	
}
