package util;
import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		FileDataReader fdr = new FileDataReader(new File("C:\\Uni\\Eclipse Workspace\\AStarHelmke\\Badbelegungsplanung2"));
		System.out.println(fdr.read().toString());
	}
}
