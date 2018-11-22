package tree.data;

import util.Time;

/**
 * Deine Wrapper-Klasse für alle Daten die von einer Node als Stammdaten verwaltet werden sollen.
 * Gilt nur für die Badaufgabe. Muss für Flügzeuge erneuert werden.
 */
public class GuestEntityData implements EntityData {
    public static GuestEntityData ROOT = new GuestEntityData("ROOT", 0,0);
	String bezeichner;
	int aufstehzeit, dauer;

    /**
     * Erzeugt eine neue Datenstruktur mit einem Bezeichner, einer Aufstehzeit und einer Dauer.
     * @param bez : Bezeichner dieser Datenstruktur.
     * @param wake : Die Aufstehzeit der Person
     * @param dur . Die Aufenthaltsdauer im Bad.
     */
	public GuestEntityData(String bez, int wake, int dur) {
		this.bezeichner = bez;
		this.aufstehzeit = wake;
		this.dauer = dur;
	}
	
	@Override
	public String toString() {
		return bezeichner + "," + aufstehzeit + "," + dauer;
	}

	@Override
	public int getTime() {
		return aufstehzeit;
	}

	@Override
	public int getDuration() {
		return dauer;
	}

	@Override
	public String getName() {
		return bezeichner;
	}
}
