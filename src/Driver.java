import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Driver {
	public static void main(String args[]) {
		MapController map = new MapController();
		map.loadTrees();
		Set<String> dataSet = map.getData().keySet();
		
		
		
		/*System.out.println(dataSet.toString());
		for(String elem : dataSet) {
		System.out.println(elem);
		}
		TreeLocation added = new TreeLocation("900", "550", "Ash Tree 12", "A great tree", "Dying");
		map.add(added);
		Set<String> dataSe = map.getData().keySet();
		System.out.println(dataSe.toString());
		map.saveTrees();
		map.delete("Ash Tree 12");
		Set<String> dataSets = map.getData().keySet();
		System.out.println(dataSets.toString());
		TreeLocation search = new TreeLocation("1000", "500", "Maple Tree 162", "New Maple Tree", "Healthy");
		map.add(search);
		Set<TreeLocation> searchTest = map.search("Maple");
		for(TreeLocation x : searchTest) {
			System.out.println(x.getName());
		}
		System.out.println(map.distance(0,  0,  2,  2));
		*/
	
		//TreeLocation added2 = new TreeLocation("400", "400", "Ash Tree 3", "A great tree", "Dying");
		//map.add(added2);
		
		
		

	}

}
