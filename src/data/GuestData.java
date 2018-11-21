package data;

import util.Time;

public class GuestData implements DataStructureInterface{
    public static GuestData ROOT = new GuestData("ROOT", new Time(00,00), new Time(00,00));

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

    @Override
    public boolean equals(DataStructureInterface o) {
        if(o instanceof DataStructureInterface) {
            DataStructureInterface dsi = (DataStructureInterface) o;
            if(dsi.getTime().compareTo(this.getTime()) == 0 && dsi.getDuration().compareTo(this.getDuration()) == 0 && dsi.getName().equals(this.getName())) {
                return true;
            }
        }
        return false;
    }
}
