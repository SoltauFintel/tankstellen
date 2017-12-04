package github.soltaufintel.tankstellen.actions;

import org.quartz.SchedulerException;

import com.google.inject.Inject;

import de.mwvb.maja.timer.BaseTimer;
import de.mwvb.maja.web.AppConfig;

public class TestTimer extends BaseTimer {
	@Inject
	private AppConfig config;
	
	@Override
	protected void config() throws SchedulerException {
		start("*/5 * * * * ?");
	}

	@Override
	protected void timerEvent() throws Exception {
		System.out.println("timer event:    " + config.get("host"));
	}
}
