package rentasad.library.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * The MYSQLConnectionTest class contains unit tests to ensure the functionality of the MYSQLConnection class.
 * Setzt einen Docker Container vorraus.
 */
public class MYSQLConnectionTest {

	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_DATABASE = "testdb";
	private static final String MYSQL_USER = "dbUser";
	private static final String MYSQL_PASSWORD = "dbPassword";
	private static final String MYSQL_ENCODING = "utf8";
	private static final String MYSQL_PORT = "3308";

	private final String expectedParamString = "rewriteBatchedStatements=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=Europe/Berlin&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&useSSL=false";

	@Test
	public void testGetParamStringFromConnectionPropertiesMap() throws Exception {
		Map<String, String> connectionPropertiesMap = MYSQLConnection.getDefaultConnectionPropertiesMap();
		assertEquals(MYSQLConnection.getParamStringFromConnectionPropertiesMap(connectionPropertiesMap), expectedParamString);
	}

	@Test
	public void testDbConnectWithTimeZoneUTC() throws Exception {
		Connection con = MYSQLConnection.dbConnectWithTimeZoneUTC(getMySqlConfigMap(null));
		con.close();
	}

	@Test
	public void testDbConnectWithTimeZoneUTCWithPort3307() throws Exception {
		Connection con = MYSQLConnection.dbConnectWithTimeZoneUTC(getMySqlConfigMap(MYSQL_PORT));
		con.close();
	}

	@Test
	public void testDbConnectWithMap() throws Exception {
		Connection con = MYSQLConnection.dbConnect(getMySqlConfigMap(null));
		con.close();
	}

	@Test
	public void testDbConnectWithMapWithPort() throws Exception {
		Connection con = MYSQLConnection.dbConnect(getMySqlConfigMap(MYSQL_PORT));
		con.close();
	}

	private HashMap<String, String> getMySqlConfigMap(String port) {
		HashMap<String, String> mySqlConfigMap = new HashMap<>();
		mySqlConfigMap.put("MYSQL_HOST", MYSQL_HOST);
		mySqlConfigMap.put("MYSQL_DATABASE", MYSQL_DATABASE);
		mySqlConfigMap.put("MYSQL_USER", MYSQL_USER);
		mySqlConfigMap.put("MYSQL_PASSWORD", MYSQL_PASSWORD);
		mySqlConfigMap.put("MYSQL_ENCODING", MYSQL_ENCODING);
		if (port != null) {
			mySqlConfigMap.put("MYSQL_PORT", port);
		}
		return mySqlConfigMap;
	}
}