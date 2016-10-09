package runners;

import java.util.Timer;

import business.GameGetter;
import dao.GameDao;
import util.Logger;

public class Runner {

	public static void main(String[] args) {
		
		final Long startFrom;
		startFrom = new GameDao().getLastGameId() + 1;
		System.out.println("WARNING: Starting from game number " + startFrom);
		Logger.log("WARNING: Starting from game number " + startFrom);
		Timer timer = new Timer();
		timer.schedule(new GameGetter(startFrom), 0L, 20000L);
	}

}
