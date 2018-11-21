package util;

public class Time implements Comparable<Time>{
	public static Time ZERO = new Time(00,00);
	
	int min, hour;
	public Time(int hour, int min) {
		this.min = min;
		this.hour = hour;
	}
	
	public static Time fromString(String s) {
		String[] sa = s.split(":");
		return new Time(Integer.parseInt(sa[0]), Integer.parseInt(sa[1]));
	}
	
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
	
	public static Time add(Time a, Time b) {
		int rMin = a.min + b.min;
		int rHour = a.hour + b.hour + rMin / 60;
		return new Time(rHour,rMin % 60);
	}

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
	
	public int toCost() {
		return hour * 60 + min;
	}
	
	@Override
	public String toString() {
		return hour + ":" + min;
	}
}
