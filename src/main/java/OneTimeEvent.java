import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent {

	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	//constructor
	public OneTimeEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		
		//get info to make meeting
		Meeting newEvent = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		
		//schedule event if no existing event present
		cal.addMeeting(newEvent);
		
		return;
	}
}
