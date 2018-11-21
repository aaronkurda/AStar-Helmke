package data;

import util.Time;

public class GuestData implements DataStructureInterface{
	String bezeichner;
	Time aufstehzeit, dauer;
	
	public GuestData(String bez, Time wake, Time dur) {
		this.bezeichner = bez;
		this.aufstehzeit = wake;
		this.dauer = dur;
	}
	
	@Override
	public String toString() {
		return bezeichner + "," + aufstehzeit + "," + dauer;
	}

	@Override
	public Time getTime() {
		return aufstehzeit;
	}

	@Override
	public Time getDuration() {
		return dauer;
	}

	@Override
	public String getName() {
		return bezeichner;
	}
}
