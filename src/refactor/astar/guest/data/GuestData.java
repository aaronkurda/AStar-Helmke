package refactor.astar.guest.data;

public class GuestData {

    String name;
    int time;
    int duration;

    public GuestData(String name, int time, int duration) {
        this.name = name;
        this.time = time;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[" + name + "," + time + "," + duration + "]";
    }
}
