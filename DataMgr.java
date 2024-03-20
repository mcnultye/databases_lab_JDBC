import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataMgr {
    // Database names
    private static final String MEAL_PLANNING_DB_NAME = "MealPlanning";
    private static final String ARCADE_GAMES_DB_NAME = "ArcadeGames";
    private static final String VIDEO_GAME_SYSTEMS_DB_NAME = "VideoGameSystems";

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";

    // Get database credentials from environment variables
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Connections
    private static Connection mealPlanningConnection;
    private static Connection arcadeGamesConnection;
    private static Connection videoGameSystemsConnection;

    // Load the MySQL JDBC driver
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Get connection to MealPlanning database
    public static Connection getMealPlanningConnection() {
        if (mealPlanningConnection == null) {
            try {
                mealPlanningConnection = DriverManager.getConnection(DB_URL + MEAL_PLANNING_DB_NAME, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mealPlanningConnection;
    }

    // Get connection to ArcadeGames database
    public static Connection getArcadeGamesConnection() {
        if (arcadeGamesConnection == null) {
            try {
                arcadeGamesConnection = DriverManager.getConnection(DB_URL + ARCADE_GAMES_DB_NAME, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arcadeGamesConnection;
    }

    // Get connection to VideoGameSystems database
    public static Connection getVideoGameSystemsConnection() {
        if (videoGameSystemsConnection == null) {
            try {
                videoGameSystemsConnection = DriverManager.getConnection(DB_URL + VIDEO_GAME_SYSTEMS_DB_NAME, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return videoGameSystemsConnection;
    }
}
