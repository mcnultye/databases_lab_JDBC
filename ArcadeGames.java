
import java.sql.*;

public class ArcadeGames {
    
    private Connection getMySQLConnection(String databaseName, String user, String password)
    {
        try
        {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        } 
        catch (SQLException exception)
        {
            System.out.println("Failed to connect to the database" + exception.getMessage());
            return null;
        }
    }
      
    public boolean TryExecutingAQuery(String databaseName, String query, String user, String password) {
        try {
            Connection myConnection = getMySQLConnection(databaseName, user, password);
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM Game");

            while (myResultSet.next()) {
                int gameId = myResultSet.getInt("Id");
                String gameName = myResultSet.getString("GameName");
                String developerName = myResultSet.getString("DeveloperName");
                Date releaseDate = myResultSet.getDate("ReleaseDate");
                Timestamp lastMaintenanceWindow = myResultSet.getTimestamp("LastMaintenanceWindow");

                System.out.println("Game ID: " + gameId);
                System.out.println("Game Name: " + gameName);
                System.out.println("Developer Name: " + developerName);
                System.out.println("Release Date: " + releaseDate);
                System.out.println("Last Maintenance Window: " + lastMaintenanceWindow);
                System.out.println("--------------------");
            }
            myResultSet.close();
            myStatement.close();
            myConnection.close();
            return true;
        } catch (SQLException exception) {
            System.out.println("Failed to execute statement: " + exception.getMessage());
            return false;
        }
    }

    public boolean TryPreparedStatement(String databaseName, String user, String password, int gameId) {
        try {
            Connection myConnection = getMySQLConnection(databaseName, user, password);
            PreparedStatement myPreparedStatement = myConnection.prepareStatement("SELECT * FROM Score WHERE GameId = ?");
            myPreparedStatement.setInt(1, gameId);
            ResultSet myResultSet = myPreparedStatement.executeQuery();

            while (myResultSet.next()) {
                int scoreId = myResultSet.getInt("Id");
                int playerId = myResultSet.getInt("PlayerId");
                int score = myResultSet.getInt("Score");
                Timestamp gameDate = myResultSet.getTimestamp("GameDate");

                System.out.println("Score ID: " + scoreId);
                System.out.println("Player ID: " + playerId);
                System.out.println("Score: " + score);
                System.out.println("Game Date: " + gameDate);
                System.out.println("--------------------");
            }
            myResultSet.close();
            myPreparedStatement.close();
            myConnection.close();
            return true;
        } catch (SQLException exception) {
            System.out.println("Failed to execute prepared statement: " + exception.getMessage());
            return false;
        }
    }

    public boolean TryCallableStatement(String databaseName, String user, String password) {
        try {
            Connection myConnection = getMySQLConnection(databaseName, user, password);
            CallableStatement myCallableStatement = myConnection.prepareCall("{Call GetPlayerDetails(?)}");
            myCallableStatement.setInt(1, 1); // Assuming 1 is the player ID
            ResultSet myResultSet = myCallableStatement.executeQuery();

            while (myResultSet.next()) {
                int playerId = myResultSet.getInt("Id");
                String userName = myResultSet.getString("UserName");
                int favoriteGameId = myResultSet.getInt("FavoriteGame");

                System.out.println("Player ID: " + playerId);
                System.out.println("User Name: " + userName);
                System.out.println("Favorite Game ID: " + favoriteGameId);
                System.out.println("--------------------");
            }
            myResultSet.close();
            myCallableStatement.close();
            myConnection.close();
            return true;
        } catch (SQLException exception) {
            System.out.println("Failed to execute callable statement: " + exception.getMessage());
            return false;
        }
    }
    
}
