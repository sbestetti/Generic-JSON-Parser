package runners;

import java.util.Timer;

import business.GameGetter;

public class Runner {

	public static void main(String[] args) {
		
		final Long startFrom;
		
		if (args.length > 0) {
			 startFrom = Long.valueOf(args[0]);
		} else {
			startFrom = 1L;
		}
				
		Timer timer = new Timer();
		timer.schedule(new GameGetter(startFrom), 0L, 20000L);

	}

}
