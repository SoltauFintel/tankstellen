package github.soltaufintel.tankstellen.actions;

import java.util.List;

import com.google.inject.Inject;

import de.mwvb.maja.web.Action;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Index extends Action {
	@Inject
	private TankstelleDAO dao;

	@Override
	protected void execute() {
		String userId = TankstellenApp.getUserId(req);
		List<Tankstelle> tankstellen = dao.findByUser(userId);
		put("tankstellen", tankstellen);
	}
}
