package refactor.main;

import refactor.astar.guest.GuestAStar;
import refactor.astar.guest.data.GuestData;
import refactor.astar.guest.tree.GuestNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileDataReader fld = new FileDataReader(new File("D:\\Uni\\C++\\AStar-Helmke\\Badbelegungsplanung2"));
        GuestData[] gds = fld.read();
        List<GuestData> gdss = new ArrayList<>();
        System.out.println("All GuestDatas: Size =" + gds.length);
        for(GuestData gd: gds) {
            System.out.println(gd);
            gdss.add(gd);
        }
        GuestAStar astar = new GuestAStar(gds);
        astar.tsExpand(11,2);
        System.out.println(astar.path);
        //System.out.println(astar.getPathIncludeingRoot(astar.lazyGreedyExpand(gdss)));
    }
}
