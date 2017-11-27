package github.soltaufintel.tankstellen.actions;

import de.mwvb.maja.web.ActionBase;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Loeschen extends ActionBase {

	@Override
	public String run() {
		String id = req.params("id"); // $unsafe (Query injection?)
		TankstelleDAO dao = new TankstelleDAO(TankstellenApp.database);
		Tankstelle ta = dao.findById(id);
		if (ta == null || !ta.getUserId().equals(TankstellenApp.getUserId(req))) {
			throw new RuntimeException("Sie haben keinen Zugriff auf diesen Datensatz!");
		}
		
		dao.delete(ta);

		res.redirect("/");
		return "";
	}
}
