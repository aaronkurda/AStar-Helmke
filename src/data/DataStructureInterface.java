package data;

import util.Time;

/**
 * Ein Interface mit Basisfunktionen zur Kostenberechnung im Tree.
 *
 */
public interface DataStructureInterface{
    /**
     * Definition eines Statischen ROOT-Objektes. Dieses Objekt wird von der TreeNode benutzt, die als root des Baumes dient.
     * Das Objekt hat den Namen ROOT, die Zeit Time.ZERO und die dauer Time.ZERO. Zudem ist es zu jedem anderen Objekt (inklusive sich selbst) ungleich.
     */
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
        public String toString() {
            return getName();
        }
    };

	public Time getTime();
	
	public Time getDuration();
	
	public String getName();

	public String toString();
}
