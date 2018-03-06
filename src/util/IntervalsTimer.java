package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IntervalsTimer {

	IntervalsTimerDelegate delegate;
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	void start() {
		next();
	}

	void stop() {
		executor.shutdownNow();
	}

	private synchronized void next() {
		if (delegate != null) {
			final long time = delegate.tick();
			if (time > 0) {
				executor.schedule(() -> next(), time, TimeUnit.MILLISECONDS);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntervalsTimerDelegate delegate = new IntervalsTimerDelegate() {
			private int[] periods = { 1000, 2000, 3000, 0 };
			private int current;

			@Override
			public long tick() {
				// TODO Auto-generated method stub
				GlobalFunctions.print("tick");
				return periods[current++];
			}
		};

		IntervalsTimer it = new IntervalsTimer();
		it.delegate = delegate;
		it.start();

		GlobalFunctions.setTimeout(() -> {
			it.stop();
		}, 2000);
	}

}
