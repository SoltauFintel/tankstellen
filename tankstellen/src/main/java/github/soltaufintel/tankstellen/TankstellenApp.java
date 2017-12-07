package github.soltaufintel.tankstellen;

import de.mwvb.maja.auth.AuthPlugin;
import de.mwvb.maja.auth.facebook.FacebookAuthorization;
import de.mwvb.maja.auth.facebook.FacebookFeature;
import de.mwvb.maja.auth.rememberme.KnownUser;
import de.mwvb.maja.auth.rememberme.RememberMeInMongoDB;
import de.mwvb.maja.mongo.Database;
import de.mwvb.maja.web.AbstractWebApp;
import github.soltaufintel.tankstellen.actions.Bearbeiten;
import github.soltaufintel.tankstellen.actions.BearbeitenSpeichern;
import github.soltaufintel.tankstellen.actions.Index;
import github.soltaufintel.tankstellen.actions.Loeschen;
import github.soltaufintel.tankstellen.actions.Neu;
import github.soltaufintel.tankstellen.actions.Speichern;
import github.soltaufintel.tankstellen.model.Tankstelle;
import spark.Request;

/**
 * Example web app for Maja web framework
 */
public class TankstellenApp extends AbstractWebApp {
	public static final String VERSION = "0.1.0";
	public static Database database;
	public static final String DBNAME = "tankstellen";
	
	public static void main(String[] args) {
		new TankstellenApp().start(VERSION);
	}

	@Override
	protected void routes() {
		_get("/", Index.class);
		_get("/neu", Neu.class);
		_get("/speichern", Speichern.class);
		_get("/tankstelle/:id/:title", Bearbeiten.class);
		_get("/tankstellespeichern/:id", BearbeitenSpeichern.class);
		_get("/loeschen/:id", Loeschen.class);
	}

	@Override
	protected void initDatabase() {
		database = new Database(config.get("dbhost", "localhost"), config.get("dbname", DBNAME),
				config.get("dbuser"), config.get("dbpw"), Tankstelle.class, KnownUser.class);
	}
	
	@Override
	protected String getDatabaseInfo() {
		return "MongoDB database: " + config.get("dbname", DBNAME) + "@" + config.get("dbhost", "localhost")
			+ (config.get("dbpw") == null ? "" : " with password");
	}
	
	@Override
	protected void init() {
		auth = new AuthPlugin(
				new FacebookAuthorization(),
				new FacebookFeature(),
				new RememberMeInMongoDB(database, "tankstellen"));
	}

	public static String getUserId(Request req) {
		String userId = AuthPlugin.getUserId(req.session());
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User Id ist leer!"); // Programmschutz
		}
		return userId;
	}
}
