package github.soltaufintel.tankstellen.model;

import java.util.List;

import de.mwvb.maja.mongo.AbstractDAO;
import de.mwvb.maja.mongo.Database;
import github.soltaufintel.tankstellen.TankstellenApp;

public class TankstelleDAO extends AbstractDAO<Tankstelle> {

	public TankstelleDAO(Database database) {
		super(TankstellenApp.database, Tankstelle.class);
	}

	public List<Tankstelle> findByUser(String userId) {
		List<Tankstelle> list = ds.createQuery(Tankstelle.class).field("userId").equal(userId).asList();
		list.sort((a,b) -> a.getBezeichnung().toLowerCase().compareTo(b.getBezeichnung().toLowerCase()));
		return list;
	}

	public Tankstelle findById(String id) {
		return ds.createQuery(Tankstelle.class).field("id").equal(id).get();
	}
}
