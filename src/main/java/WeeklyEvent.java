import java.util.Calendar;
import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent {
	
	private GregorianCalendar repeatUntil;

	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public WeeklyEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar repeat) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
		this.repeatUntil = repeat;
	}
	
	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		//get info
		scheduleWeekly(cal);
		return;
	}
	
	//schedule weekly event
	public void scheduleWeekly(MeetingCalendar cal) {
		//info
		String desc = getDescription();
		String loc = getLocation();
		GregorianCalendar start = getStartTime();
		GregorianCalendar end = getEndTime();
		GregorianCalendar repeat = getRepeatUntil();
		//clone
		GregorianCalendar currentStart = (GregorianCalendar) start.clone();
		GregorianCalendar currentEnd = (GregorianCalendar) end.clone();
		
		//new event
		Meeting newEvent = new Meeting(desc, loc, start, end);
		
		while(currentStart.before(repeat)) {
			//schedule
			cal.addMeeting(newEvent);
			//update start and end
			currentStart.add(Calendar.DATE, 7);
			currentEnd.add(Calendar.DATE, 7);
			newEvent.setStartTime(currentStart);
			newEvent.setEndTime(currentEnd);
		}
		
	}

	//getters and setters
	/**
	 * @return the repeatUntil
	 */
	public GregorianCalendar getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * @param repeatUntil the repeatUntil to set
	 */
	public void setRepeatUntil(GregorianCalendar repeatUntil) {
		this.repeatUntil = repeatUntil;
	}
	
	
}
