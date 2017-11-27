package github.soltaufintel.tankstellen.actions;

import de.mwvb.maja.web.ActionBase;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class BearbeitenSpeichern extends ActionBase {

	@Override
	public String run() {
		String id = req.params("id"); // $unsafe (Query injection?)
		TankstelleDAO dao = new TankstelleDAO(TankstellenApp.database);
		Tankstelle ta = dao.findById(id);
		if (ta == null || !ta.getUserId().equals(TankstellenApp.getUserId(req))) {
			throw new RuntimeException("Sie haben keinen Zugriff auf diesen Datensatz!");
		}

		String bezeichnung = req.queryParams("bezeichnung"); // $unsafe
		if (bezeichnung == null || bezeichnung.trim().isEmpty()) {
			throw new RuntimeException("Bitte Bezeichnung eingeben!");
		}
		int nummer;
		try {
			nummer = Integer.parseInt(req.queryParams("nummer"));
			if (nummer < 1) {
				throw new RuntimeException("Bitte geben Sie eine Nummer größer 0 ein!");
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("Bitte geben Sie eine Nummer größer 0 ein!");
		}

		ta.setBezeichnung(bezeichnung);
		ta.setNummer(nummer);

		dao.save(ta);

		res.redirect("/");
		return "";
	}
}
