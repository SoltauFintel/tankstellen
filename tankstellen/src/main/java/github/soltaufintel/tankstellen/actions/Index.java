package github.soltaufintel.tankstellen.actions;

import java.util.List;

import de.mwvb.maja.web.Action;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Index extends Action {

	@Override
	protected void execute() {
		String userId = TankstellenApp.getUserId(req);
		TankstelleDAO dao = new TankstelleDAO(TankstellenApp.database);
		List<Tankstelle> tankstellen = dao.findByUser(userId);
		put("tankstellen", tankstellen);
	}
}
