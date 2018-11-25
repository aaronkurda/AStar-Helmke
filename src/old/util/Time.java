package old.util;

/**
 * Time Klasse zur Speicherung einer Zeitintervalls in Stunden und Minuten.
 */
public class Time implements Comparable<Time>{
	public static Time ZERO = new Time(00,00);
	
	int min, hour;

    /**
     * Erzeugt ein neues Time-Objekt mit den spezifizierten Felderm.
     * @param hour : Stunde
     * @param min : Minute
     */
	public Time(int hour, int min) {
		this.min = min;
		this.hour = hour;
	}

	public static int timeToInt(String s) {
		String[] ss = s.split(":");
		return Integer.parseInt(ss[0]) * 60 + Integer.parseInt(ss[1]);
	}

    /**
     * Erzeugt ein neues Time-Objekt aus einem String. Stunden und Minuten müssen mit : voneinander getrennt sein. (hh:mm) (ohne ())
     * @param s : Der String aus dem das Time-Objekt erstellt wird.
     * @return ein Objekt des Typs Time
     */
	public static Time fromString(String s) {
		String[] sa = s.split(":");
		return new Time(Integer.parseInt(sa[0]), Integer.parseInt(sa[1]));
	}

    /**
     * Erzeugt ein neues Time-Objekt welches der Betrag der Differenz der zwei Uhrzeiten repräsentiert.
     * Das neu erzeugte Objekt ist immer positiv.
     * Es gilt: deltaTime(a,b) == deltaTime(b,a) == |a-b|
     * @param a : Time a
     * @param b : Time b
     * @return ein neu erzeugte Time-Objekt.
     */
	public static Time deltaTime(Time a, Time b) {
		if(a.compareTo(b) <= 0) {
			int rHour = b.hour - a.hour;
			int rMin =  b.min - a.min;
			if(rMin < 0) {
				rHour--;
				rMin += 60;
			}
			return new Time(rHour, rMin);
		}
		return deltaTime(b,a);
	}

    /**
     * Erzeugt ein neues Time Objekt das den Wert der Summe der Time-Objekte a und b hat.
     * Es gilt: add(a,b) == add(b,a), add(a,b) > a && add(a,b) > b
     * Für das min-Attribut gilt: 0 <= min < 60
     * @param a : Time a
     * @param b : Time b
     * @return ein neues Time-Objekt
     */
	public static Time add(Time a, Time b) {
		int rMin = a.min + b.min;
		int rHour = a.hour + b.hour + rMin / 60;
		return new Time(rHour,rMin % 60);
	}

    /**
     * Vergleicht zwei Time-Objekte.
     * @param arg0 : Das Time-Objekt mit dem dieses vergleicht werden soll.
     * @return -1 wenn this < arg0
     *          1 wenn this > arg0
     *          0 wenn this == arg0
     */
	@Override
	public int compareTo(Time arg0) {
		if(this.hour < arg0.hour) {
			return -1;
		} else if(this.hour > arg0.hour){
			return 1;
		} else if(this.hour == arg0.hour) {
			if(this.min < arg0.min) {
				return -1;
			} else if(this.min > arg0.min) {
				return 1;
			}
		}
		return 0;
	}

    /**
     * Errechnet die Kosten des Time-Objekts. Die Kosten entsprechen der Zeit in Minuten.
     * @return das Zeit-Objek in Minuten-darstellung
     */
	public int toCost() {
		return hour * 60 + min;
	}

    /**
     * Gibt das Objekt als String zurück (hh:mm)
     * @return
     */
	@Override
	public String toString() {
		return hour + ":" + min;
	}
}
