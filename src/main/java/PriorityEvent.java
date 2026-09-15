import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class PriorityEvent extends CalendarEvent {

	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public PriorityEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
	}
	
	public void scheduleEvent(MeetingCalendar cal) {
		//make new meeting with info
		Meeting newEvent = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		//replace meeting (always)
		cal.addMeeting(newEvent, true);
		return;
	}
}
