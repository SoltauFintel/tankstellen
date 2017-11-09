package github.soltaufintel.tankstellen.model;

import org.junit.BeforeClass;
import org.junit.Test;

import github.soltaufintel.tankstellen.TankstellenApp;

public class TankstelleTest {
	private static TankstellenApp app;
	
	@BeforeClass
	public static void openDatabase() {
		app = new TankstellenApp();
		app.startForTest();
	}
	
	@Test
	public void test() {
		TankstelleDAO dao = new TankstelleDAO(TankstellenApp.database);

		Tankstelle star = new Tankstelle();
		star.setNummer(1);
		star.setBezeichnung("Star");
		star.setId(TankstelleDAO.code6(TankstelleDAO.genId()));
		star.setUserId("TEST");
		
		dao.save(star);
	}
}
