package util;
import tree.AStar;
import tree.data.GuestEntityData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		FileDataReader fdr = new FileDataReader(new File("C:\\Uni\\C++\\AStarGuest\\Badbelegungsplanung2"));
		List<GuestEntityData> dsis = fdr.read();


		AStar<GuestEntityData> astar = new AStar<>(dsis);
		//System.out.println(astar.getMinCostLeafNode());
        System.out.println(astar.getMinCostPath());
		//TimeUnit.SECONDS.sleep(1);
		System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
	}

}
