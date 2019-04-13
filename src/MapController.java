import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MapController {
	
	private Map<String, TreeLocation> data;
	public MapController() {
		this.data = new HashMap<String, TreeLocation>();
	}
	 
	
	public Set<TreeLocation> search(String name) {
		Set<TreeLocation> answer = new HashSet<TreeLocation>();
		Collection<String> treeNames = data.keySet();
		for(String t: treeNames) {
			if(t.contains(name)) {
				answer.add(data.get(t));
			}
		}
		return answer;
	}
	
	public boolean add(TreeLocation tree) {
		if(data.containsKey(tree.getName())) {
			return false;
		}else {
			data.put(tree.getName(), tree);
			return true;
		}
	}
	
	public boolean delete(String treeName) {
		if(data.containsKey(treeName)){
			data.remove(treeName);
			return false;
		}else {
			return false;
		}
		
	}
	
	public double distance(int x1, int y1, int x2, int y2) {
		double x = Math.pow(x2 - x1, 2);
		double y = Math.pow(y2 - y1, 2);
		double z = x + y;
		return Math.sqrt(z);
	}
	
	public void loadTrees() {
		
		try {
		File file = new File("TreeInfo.txt");
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()){
			String name = sc.nextLine();
			String x = sc.nextLine();
			String y = sc.nextLine();
			String status = sc.nextLine();
			String description = sc.nextLine();
		while(!sc.nextLine().equals("*")) {
			description = (description + sc.nextLine() + "");
		}
		TreeLocation currentTree = new TreeLocation(x, y, name, description, status);
		data.put(name, currentTree);
		}
		
		sc.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("this didnt work");
		}
		
		
		
	}
	
	public void saveTrees() {
		Collection<TreeLocation> trees = data.values();
		try {
			PrintWriter pw = new PrintWriter("TreeInfo.txt");
			for(TreeLocation currentTree: trees) {
				pw.println(currentTree.getName());
				pw.println(currentTree.getX());
				pw.println(currentTree.getY());
				pw.println(currentTree.getStatus());
				pw.println(currentTree.getDescription());
				pw.println("*");
			}
			pw.close();
			
		}catch(FileNotFoundException fnfe) 
		{
			
		}
	}
	
	public void displayTrees() {
		
	}
	public Map<String, TreeLocation> getData() {
		return this.data;
	}

}
