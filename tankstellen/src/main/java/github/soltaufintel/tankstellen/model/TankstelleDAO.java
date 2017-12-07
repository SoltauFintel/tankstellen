package github.soltaufintel.tankstellen.model;

import java.util.List;

import de.mwvb.maja.mongo.AbstractDAO;

public class TankstelleDAO extends AbstractDAO<Tankstelle> {

	public List<Tankstelle> findByUser(String userId) {
		List<Tankstelle> list = createQuery().field("userId").equal(userId).asList();
		list.sort((a,b) -> a.getBezeichnung().toLowerCase().compareTo(b.getBezeichnung().toLowerCase()));
		return list;
	}

	@Override
	protected Class<Tankstelle> getEntityClass() {
		return Tankstelle.class;
	}
}
