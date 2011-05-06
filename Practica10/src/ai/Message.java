package ai;

public class Message {
	
	public static final int moveUp = 1;
	public static final int moveDown = 2;
	public static final int moveLeft = 3;
	public static final int moveRight = 4;
	public static final int recivedImpact = 5;
	
	public double senderId;
	public double reciverId;
	public int type;
	public double deliveryTime;
	
	// Constructor por defecto.
	public Message() {
		
	}
	
	// Constructor sobrecargado.
	public Message(double senderId, double reciverId, int type, double deliveryTime) {
		this.senderId = senderId;
		this.reciverId = reciverId;
		this.type = type;
		this.deliveryTime = deliveryTime;
	}
}
