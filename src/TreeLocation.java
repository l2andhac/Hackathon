
public class TreeLocation {
	private String x;
	private String y;
	private String name;
	private String description;
	private String status;
	
	
	
	public TreeLocation(String x, String y, String name, String description, String status) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	
	public String getX() {
		return x;
		
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
