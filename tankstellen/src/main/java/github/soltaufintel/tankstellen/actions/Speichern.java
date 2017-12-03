package github.soltaufintel.tankstellen.actions;

import com.google.inject.Inject;

import de.mwvb.maja.web.ActionBase;
import github.soltaufintel.tankstellen.TankstellenApp;
import github.soltaufintel.tankstellen.model.Tankstelle;
import github.soltaufintel.tankstellen.model.TankstelleDAO;

public class Speichern extends ActionBase {
	@Inject
	private TankstelleDAO dao;

	@Override
	public String run() {
		String bezeichnung = req.queryParams("bezeichnung"); // $unsafe (HTML/JS injection?)
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

		Tankstelle ta = new Tankstelle();
		ta.setId(TankstelleDAO.code6(TankstelleDAO.genId()));
		ta.setUserId(TankstellenApp.getUserId(req));
		ta.setBezeichnung(bezeichnung);
		ta.setNummer(nummer);
		
		dao.save(ta);

		res.redirect("/");
		return "";
	}
}
