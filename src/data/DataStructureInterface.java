package data;

import util.Time;

public interface DataStructureInterface{
    public static DataStructureInterface ROOT = new DataStructureInterface() {
        @Override
        public Time getTime() {
            return Time.ZERO;
        }

        @Override
        public Time getDuration() {
            return Time.ZERO;
        }

        @Override
        public String getName() {
            return "ROOT";
        }

        @Override
        public boolean equals(DataStructureInterface o) {
            return false;
        }

        @Override
        public String toString() {
            return getName();
        }
    };

	public Time getTime();
	
	public Time getDuration();
	
	public String getName();

	public boolean equals(DataStructureInterface o);

	public String toString();
}
