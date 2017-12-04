package github.soltaufintel.tankstellen.actions;

import java.util.List;

import com.google.inject.Inject;

import de.mwvb.maja.web.Action;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Index extends Action {
	@Inject
	private TankstelleDAO dao;

	@Override
	protected void execute() {
		List<Tankstelle> tankstellen = dao.findByUser(getUserId());
		put("tankstellen", tankstellen);
	}
}
