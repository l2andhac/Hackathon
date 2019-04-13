import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapController implements ActionListener, MouseListener{
	
	private Map<String, TreeLocation> data;
	private JPanel top, bottom;
	private JButton viewAllTrees, save, add, delete, search, findDistance;
	
	private JFrame frame;
	JLabel picLabel;
	
	
	public MapController() {
		this.data = new HashMap<String, TreeLocation>();
		frame = new JFrame();
		top = new JPanel();
		bottom = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);
	    frame.getContentPane().add(bottom, BorderLayout.SOUTH);
	    this.viewAllTrees = new JButton("View All Trees");
	    this.save = new JButton("Save");
	    this.add = new JButton("Add");
	    this.delete = new JButton("Delete");
	    this.search = new JButton("Search");
	    this.findDistance = new JButton("Find Distance");
	    this.viewAllTrees.setActionCommand("Call View All Trees");
	    this.viewAllTrees.addActionListener(this);
	    save.setActionCommand("Call Save");
	    this.save.addActionListener(this);
	    add.setActionCommand("Call Add");
	    this.add.addActionListener(this);
	    delete.setActionCommand("Call Delete");
	    this.delete.addActionListener(this);
	    search.setActionCommand("Call Search");
	    this.search.addActionListener(this);
	    findDistance.setActionCommand("Call Distance");
	    this.findDistance.addActionListener(this);
	    bottom.add(viewAllTrees);
	    bottom.add(save);
	    bottom.add(add);
	    bottom.add(delete);
	    bottom.add(search);
	    bottom.add(findDistance);
	    //top.addMouseListeners(this);
	    top.addMouseListener(this);
	    
	    
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("ArbMap.JPG"));
			
		}catch (IOException e) {
			
		}
		picLabel = new JLabel(new ImageIcon(img));
		picLabel.setLayout(null);
		//frame = new JFrame();
		top.add(picLabel);
		top.setVisible(true);
		frame.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
	    //Creates frame with locations to move to
	    if(command.equals("Call View All Trees")){
	    Set<String> allTrees = this.viewAllTrees();
	    String[] treeNames = new String[allTrees.size()];
		int i = 0;
		for(String x : allTrees) {
			treeNames[i] = x;
			i++;
		}
		if(allTrees.size() == 0) {
    		JOptionPane.showMessageDialog(null, "There are no trees to display.");
    	}else {
    		i = JOptionPane.showOptionDialog(null, "Choose a tree to view more information for: ", "Tree", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, treeNames, treeNames[0]);
    		TreeLocation info = this.data.get(treeNames[i]);
    		JOptionPane.showMessageDialog(null,  treeNames[i] + ":\n Located at (" + info.getX() + "," + info.getY() + ")\n With a "
    				+ "Status of " + info.getStatus() + "\n Description: " + info.getDescription());
    	}
	    
	    }
	    else if(command.equals("Call Save")) {
	    	this.saveTrees();
	    }
	    else if(command.equals("Call Add")) {
	    	String name = JOptionPane.showInputDialog("Enter a name for the tree");
	    	String x = JOptionPane.showInputDialog("Enter a x-coordinate");
	    	String y = JOptionPane.showInputDialog("Enter a y-coordinate");
	    	String status = JOptionPane.showInputDialog("Enter Tree Status");
	    	String description = JOptionPane.showInputDialog("Enter tree description");
	    	TreeLocation treeToAdd = new TreeLocation(x, y, name, description, status);
	    	this.add(treeToAdd);
	    }
	    else if(command.equals("Call Delete")) {
	    	String treeName = JOptionPane.showInputDialog("Enter tree to delete");
	    	this.delete(treeName);
	    }
	    else if(command.equals("Call Search")) {
	    	String treeName = JOptionPane.showInputDialog("Enter tree to search for");
	    	Set<TreeLocation> answer = this.search(treeName);
	    	String[] trees = new String[answer.size()];
	    	int i =0;
	    	for(TreeLocation x: answer) {
	    		trees[i] = x.getName();
	    		i++;
	    	}
	    	if(answer.size() == 0) {
	    		JOptionPane.showMessageDialog(null, "There are no matches for your search.");
	    	}else {
	    		i = JOptionPane.showOptionDialog(null, "Choose a tree that matches your search: ", "Tree", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, trees, trees[0]);
	    		TreeLocation info = this.data.get(trees[i]);
	    		JOptionPane.showMessageDialog(null,  trees[i] + ":\n Located at (" + info.getX() + "," + info.getY() + ")\n With a "
	    				+ "Status of " + info.getStatus() + "\n Description: " + info.getDescription());
	    	}
	    }
	    else if(command.equals("Call Distance")) {
	    	String x1 = JOptionPane.showInputDialog("Enter x1");
	    	String y1 = JOptionPane.showInputDialog("Enter y1");
	    	String x2 = JOptionPane.showInputDialog("Enter x2");
	    	String y2 = JOptionPane.showInputDialog("Enter y2");
	    	double x = this.distance(Integer.parseInt(x1), Integer.parseInt(y1), Integer.parseInt(x2), Integer.parseInt(y2));
	    	x = Math.round(x);
	    	JOptionPane.showMessageDialog(null, "The distance between the two points is " + x);
	    }
	}
	public void mouseClicked(MouseEvent e) {
	    int x=e.getX();
	    int y=e.getY();
	    String name = JOptionPane.showInputDialog("Please enter a name for the tree: ");
	    String status = JOptionPane.showInputDialog("Please enter the status of the tree: ");
	    String description = JOptionPane.showInputDialog("Plese enter the description for the tree: ");
	    x = x - 630;
	    y = y - 10;
	    TreeLocation add = new TreeLocation(Integer.toString(x), Integer.toString(y), name, description, status);
	    System.out.println(x);
	    System.out.println(y);
	    this.add(add);
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
		int x = Integer.parseInt(tree.getX());
		int y = Integer.parseInt(tree.getY());
		if(data.containsKey(tree.getName())) {
			JOptionPane.showMessageDialog(null, "Tree must have a unique name");
			return false;
		}else if(x >= 50 && x <= 600 && y >= 20 && y <= 700){
			data.put(tree.getName(), tree);
			this.saveTrees();
			this.loadTrees();
			
			/*BufferedImage img2 = null;
			try {
				img2 = ImageIO.read(new File("pin.jpg"));
				
			}catch (IOException e) {
				
			}
			JLabel point= new JLabel(new ImageIcon(img2));
			point.setSize(10, 10);
			picLabel.add(point);
			point.setLocation(Integer.parseInt(tree.getX()), Integer.parseInt(tree.getY()));*/
			this.displayTrees();
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter an x between 50 and 600 and a y between 20 and 700");
			return false;
		}
	}
	
	public boolean delete(String treeName) {
		if(data.containsKey(treeName)){
			data.remove(treeName);
			this.saveTrees();
			this.loadTrees();
			
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
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("ArbMap.JPG"));
			
		}catch (IOException e) {
			
		}
		top.removeAll();
		picLabel = new JLabel(new ImageIcon(img));
		picLabel.setLayout(null);
		//frame = new JFrame();
		top.add(picLabel);
		top.setVisible(true);
		frame.setVisible(true);
		
		
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
		
		displayTrees();
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
			for(TreeLocation currentTree: trees) 
			{
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
		Collection<TreeLocation> trees = data.values();
		for(TreeLocation tree: trees) {
			BufferedImage img2 = null;
			try {
				img2 = ImageIO.read(new File("pin.jpg"));
				
			}catch (IOException e) {
				
			}
			JLabel point= new JLabel(new ImageIcon(img2));
			point.setSize(10, 10);
			picLabel.add(point);
			point.setLocation(Integer.parseInt(tree.getX()), Integer.parseInt(tree.getY()));
		}
	}
	public Map<String, TreeLocation> getData() {
		return this.data;
	}
	
	public Set<String> viewAllTrees() {
		return this.data.keySet();
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
