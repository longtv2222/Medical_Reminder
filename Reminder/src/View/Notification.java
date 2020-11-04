package View;

public interface Notification {
	
	
	public default void notification(String msg) {
		System.out.println(msg);
	}
}
