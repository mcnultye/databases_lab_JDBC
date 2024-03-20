// Author:  Wendy-Beth Minton
// Class:   3810 Database
// Lab:     Introduction to Database Connectivity

// This is an example of a presentation layer. Notice it includes all the needed prompts needed.
// This class also happens to have the main method. It is common to have the entry to a program 
// exist in the presentation layer, and actually each application tends to have its own entry point,
// if the program/product has multiple applications. 

import java.util.Scanner;

public class IntroToPresentationLayer
{
    public static void main(String[] args)
    {
        Scanner userInformation = new Scanner(System.in);
        System.out.println("Enter username and password:");
        // String input
        String userName = userInformation.nextLine();
        String password = userInformation.nextLine();

        // Let's start simple. How do we connect to and access a database?
        // Well, the presentation layer can't do it. We need an instance of the DAL!
        Scanner scanner = new Scanner(System.in);
        IntroToDAL dal = new IntroToDAL();
        ArcadeGames games = new ArcadeGames();

        // Now we can use the dal object, so let's print
        // out some rows from the Meal table, in the MealPlanningDatabase.
        // We need to pass the dal method everything it needs to run a query, including
        // the database name, the query, and the user's sql credentials.
        /*if (dal.TryExecutingAQuery("MealPlanning", "Select * from Meal", userName, password))
        {
            System.out.println("Successfully connected to the database");
        }
        else
        {
            System.out.println("Failed to connect to the database");
        }

        // Let's try calling a stored procedure, and let's start simple.
        // I made a new stored procedure that just returns everything in the 
        // Recipe table, called GetRecipes. No parameters, just a simple call.
        if (dal.TryExecutingAStoredProcedure("MealPlanning", userName, password))
        {
            System.out.println("Successfully ran a stored procedure");
        }
        else
        {
            System.out.println("Failed to run a stored procedure");
        }
        */
       

        while (true) {
            System.out.println("Choose a database:");
            System.out.println("1. Meal Planning Database");
            System.out.println("2. Arcade Games Database");
            System.out.println("3. Exit");

            int databaseChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (databaseChoice == 3) {
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
            }

            System.out.println("Choose an operation:");
            System.out.println("1. Run a single query");
            System.out.println("2. Run GetRecipes stored procedure");
            System.out.println("3. Run a method with a statement");
            System.out.println("4. Run a method with a prepared statement");
            System.out.println("5. Run a method with a callable statement");

            int operationChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (databaseChoice) {
                case 1:
                    // Meal Planning Database
                    switch (operationChoice) {
                        case 1:
                        dal.TryExecutingAQuery("MealPlanningDB", "SELECT * FROM Recipes", "username", "password");
                            break;
                        case 2:
                            dal.TryExecutingAStoredProcedure("MealPlanningDB", "user", "password");
                            break;
                        default:
                            System.out.println("Invalid operation choice.");
                    }
                    break;
                case 2:
                    // Arcade Games Database
                    switch (operationChoice) {
                        case 3:
                            games.TryExecutingAQuery("ArcadeGames", "SELECT * FROM Game", "username", "password");
                            break;
                        case 4:
                            games.TryPreparedStatement("ArcadeGames", "username", "password", 1);
                            break;
                        case 5:
                            games.TryCallableStatement("ArcadeGames", "user", "password");
                            break;
                        default:
                            System.out.println("Invalid operation choice.");
                    }
                    break;
                default:
                    System.out.println("Invalid database choice.");
            }
        }
    }
    }
