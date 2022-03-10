package RockPaperScissors;
import java.sql.*;
import java.util.TreeMap;

public class DBHandler {
    private final String USER = "RPS_User";
    private final String PASS = "123RPS";
    private final String URL = "jdbc:mysql://localhost/rps_player";


    public void insertUser(String userName, String name){

        try{
            String query = "INSERT INTO User VALUES (?, ?, 0, 0);";
            Connection connect = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement prep = connect.prepareStatement(query);

            prep.setString(1,userName);
            prep.setString(2,name);
            prep.executeUpdate();
            //let's check if this works
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUserOnWin(int currentWins, String IDUsername){
        try{
            String query = "UPDATE User SET wins = ? WHERE ID = ?;";
            Connection connect = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement prep = connect.prepareStatement(query);

            prep.setInt(1, currentWins);
            prep.setString(2, IDUsername);
            prep.executeUpdate();
            //let's check if this works
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUserOnLoose(int currentLoose, String ID){
        try{
            String query = "UPDATE User SET Lose = ? WHERE ID = ?;";
            Connection connect = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement prep = connect.prepareStatement(query);

            prep.setInt(1, currentLoose);
            prep.setString(2, ID);
            prep.executeUpdate();
            //let's check if this works
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getIDUsername(String name){
        String ID = "";
        try{
            String query = "SELECT ID FROM user WHERE ?";
            Connection connect = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement prep = connect.prepareStatement(query);

            prep.setString(1, name);

            ResultSet result = prep.executeQuery();
            ID = result.getString("ID");
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return ID;
    }



    public TreeMap getData(){
        TreeMap<String, String> map = new TreeMap<>();
        try{
            String query = "SELECT * FROM User";
            Connection connect = DriverManager.getConnection(URL,USER,PASS);
            Statement state = connect.createStatement();
            ResultSet results = state.executeQuery(query);


            while(results.next()){
                map.put(results.getString("ID"), results.getString("Name")+","+results.getString("Wins")+","+results.getString("Lose"));
            }
            //let's check if this works
            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return map;
    }
}
