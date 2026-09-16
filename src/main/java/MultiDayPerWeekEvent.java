import java.util.Calendar;
import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class MultiDayPerWeekEvent extends CalendarEvent {
	
	
	private GregorianCalendar repeatUntil;
	private int[] days;

	public MultiDayPerWeekEvent(String description, String location, GregorianCalendar startTime,
			GregorianCalendar endTime, GregorianCalendar repeat, int[] days) {
		
		super(description, location, startTime, endTime);
		
		this.repeatUntil = repeat;
		this.days = days;
	}
	
	public void scheduleEvent(MeetingCalendar cal) {
		//get info
		GregorianCalendar start = getStartTime();
		GregorianCalendar end = getEndTime();

		int days[] = getDays();
		
		GregorianCalendar currentStart = (GregorianCalendar) start.clone();
		GregorianCalendar currentEnd = (GregorianCalendar) end.clone();
		
		//look through the first week
		int trackDay = start.get(Calendar.DAY_OF_WEEK);
		
		for(int i = 0; i < 7; i++) {
			for(int day: days) {
				if(day == trackDay) {
					//schedule meeting Weekly!!!
					scheduleWeekly(cal, currentStart, currentEnd);
				}
			}
			currentStart.add(Calendar.DATE, 1);
			currentEnd.add(Calendar.DATE, 1);
			trackDay = currentStart.get(Calendar.DAY_OF_WEEK);
			}
		}
	
	//schedule weekly event
	public void scheduleWeekly(MeetingCalendar cal1, GregorianCalendar start, GregorianCalendar end) {
		//info
		String desc1 = getDescription();
		String loc1 = getLocation();
		GregorianCalendar repeat1 = getRepeatUntil();
		
		GregorianCalendar currentStart = (GregorianCalendar) start.clone();
		GregorianCalendar currentEnd = (GregorianCalendar) end.clone();
		
		//new event
		Meeting newEvent = new Meeting(desc1, loc1, currentStart, currentEnd);
		
		while(currentStart.before(repeat1)) {
			//schedule
			cal1.addMeeting(newEvent);
			//print
			//System.out.println(newEvent.toString());
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

	/**
	 * @return the days
	 */
	public int[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int[] days) {
		this.days = days;
	}

	
}
