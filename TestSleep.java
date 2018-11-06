package test;

public class TestSleep {

	public static void main(String[] args) {
		long oldtime = System.currentTimeMillis();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long currenttime = System.currentTimeMillis();
		System.out.println(currenttime-oldtime);

	}

}
