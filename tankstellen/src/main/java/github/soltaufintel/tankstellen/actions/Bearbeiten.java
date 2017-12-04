package github.soltaufintel.tankstellen.actions;

import com.google.inject.Inject;

import de.mwvb.maja.web.Action;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Bearbeiten extends Action {
	@Inject
	private TankstelleDAO dao;
	
	@Override
	protected void execute() {
		String id = req.params("id"); // $unsafe (Query injection?)
		Tankstelle ta = dao.get(id);
		if (ta == null || !ta.getUserId().equals(getUserId())) {
			throw new RuntimeException("Sie haben keinen Zugriff auf diesen Datensatz!");
		}
		put("ta", ta);
	}
}
