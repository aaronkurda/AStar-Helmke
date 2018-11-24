package util;
import tree.AStar;
import tree.Node;
import tree.Tree;
import tree.data.GuestEntityData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		FileDataReader fdr = new FileDataReader(new File("D:\\Uni\\C++\\AStar-Helmke\\Badbelegungsplanung2"));
		List<GuestEntityData> dsis = fdr.read();
		List<GuestEntityData> lista = new ArrayList<>();
        List<GuestEntityData> listb = new ArrayList<>();
        for(int i = 0; i < dsis.size() && i < 3; i++) {
            lista.add(dsis.get(i));
            listb.add(dsis.get(i + 3));
        }
        //System.out.println(lista);
        //System.out.println(listb);

		AStar<GuestEntityData> astar = new AStar<>(dsis);
        //Node<GuestEntityData> node = new Node<>(null, GuestEntityData.ROOT,0,0);
        //node.expand(lista);
        //System.out.println(node.subtreeToString());
        //node.expand(listb);
        //System.out.println(node.subtreeToString());
		System.out.println(astar.fracExpand(11,2));
        System.out.println(astar.fracExpand(11,2).size());
        //System.out.println(astar.getTree().getNodePathToRoot(null));
		//TimeUnit.SECONDS.sleep(1);
		System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
	}

}
