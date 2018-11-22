package tree.data;

import util.Time;

/**
 * Ein Interface mit Basisfunktionen zur Kostenberechnung im Tree.
 *
 */
public interface EntityData {
    /**
     * Definition eines Statischen ROOT-Objektes. Dieses Objekt wird von der Node benutzt, die als root des Baumes dient.
     * Das Objekt hat den Namen ROOT, die Zeit Time.ZERO und die dauer Time.ZERO. Zudem ist es zu jedem anderen Objekt (inklusive sich selbst) ungleich.
     */
    public static EntityData ROOT_ELEM = new EntityData() {
        @Override
        public int getTime() {
            return 0;
        }

        @Override
        public int getDuration() {
            return 0;
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

	public int getTime();
	
	public int getDuration();
	
	public String getName();

	public String toString();
}
