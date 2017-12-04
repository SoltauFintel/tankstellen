package github.soltaufintel.tankstellen.actions;

import com.google.inject.Inject;

import de.mwvb.maja.web.ActionBase;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Loeschen extends ActionBase {
	@Inject
	private TankstelleDAO dao;

	@Override
	public String run() {
		String id = req.params("id"); // $unsafe (Query injection?)
		Tankstelle ta = dao.get(id);
		if (ta == null || !ta.getUserId().equals(getUserId())) {
			throw new RuntimeException("Sie haben keinen Zugriff auf diesen Datensatz!");
		}
		
		dao.delete(ta);

		res.redirect("/");
		return "";
	}
}
