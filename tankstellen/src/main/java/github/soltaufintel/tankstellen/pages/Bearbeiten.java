package github.soltaufintel.tankstellen.pages;

import de.mwvb.maja.web.Action;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Bearbeiten extends Action {

	@Override
	protected void execute() {
		String id = req.params("id"); // $unsafe (Query injection?)
		TankstelleDAO dao = new TankstelleDAO(TankstellenApp.database);
		Tankstelle ta = dao.findById(id);
		if (ta == null || !ta.getUserId().equals(TankstellenApp.getUserId(req))) {
			throw new RuntimeException("Sie haben keinen Zugriff auf diesen Datensatz!");
		}
		put("ta", ta);
	}
}
