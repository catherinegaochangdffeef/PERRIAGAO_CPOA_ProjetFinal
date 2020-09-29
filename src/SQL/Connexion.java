package SQL;
import java.sql.*;

public class Connexion {

	public static Connection creeConnexion() {
		String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/perria1u_cpoa";
				url += "?serverTimezone=Europe/Paris";
		String login = "perria1u_appli";
		String pwd = "31913861";
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("Connexion établie");
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;}
	
	
	private static Connexion instance;
	private Connexion() {}
	public static Connexion getInstance() {
		if (instance==null) {
			instance = new Connexion();
		}
		return instance;
	}
	
		}
		
