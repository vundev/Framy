package util;

import java.sql.Timestamp;

public class GlobalFunctions {
	public static void print(Object... objects) {
		String result = "";
		for (Object object : objects) {
			result += ", " + object.toString();
		}
		System.out.println("[" + new Timestamp(System.currentTimeMillis()) + "]" + result.substring(1));
	}

	/**
	 * http://tutorials.jenkov.com/java/lambda-expressions.html
	 * 
	 * Matching Lambdas to Interfaces
	 * 
	 * A single method interface is also sometimes referred to as a functional
	 * interface. Matching a Java lambda expression against a functional interface
	 * is divided into these steps:
	 * 
	 * Does the interface have only one method? Does the parameters of the lambda
	 * expression match the parameters of the single method? Does the return type of
	 * the lambda expression match the return type of the single method? If the
	 * answer is yes to these three questions, then the given lambda expression is
	 * matched successfully against the interface.
	 * 
	 * {@code} // lamda's match Runnable because the interface has only one method.
	 * setTimeout(() -> print("done"), 1000); setTimeout(() -> { print("done"); },
	 * 2000);
	 * 
	 * @param runnable
	 * @param delay
	 */
	public static void setTimeout(Runnable runnable, int delay) {
		new Thread() {
			@Override
			public void run() {
				try {
					this.sleep(delay);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e);
				}
				runnable.run();
			}
		}.start();
	}

	public static void test() {

	}
}
