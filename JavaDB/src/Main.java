import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/";
		String user = "";
		String password = "";
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,password);
			
			Statement stt = con.createStatement();
			
			// Create and select db
			stt.execute("CREATE DATABASE IF NOT EXISTS javaDB");
			stt.execute("USE javaDB");
			
			// Create out table
			stt.execute("DROP TABLE IF EXISTS people");
			stt.execute("CREATE TABLE people("
					+ "id INT NOT NULL AUTO_INCREMENT,"
					+ "fname VARCHAR(25),"
					+ "lname VARCHAR(25),"
					+ "PRIMARY KEY(id)"
					+ ")");
			
			// Add some entries
			stt.execute("INSERT INTO people (fname, lname) VALUES"
					+ "('Joe','Bloggs'), ('Mary','Bloggs'), ('Jill','Hill'), ('Hristo','Tsekin'), ('Tony','Bloggs')");
			
			// Get people with surname Bloggs
			ResultSet res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Bloggs'");
			while(res.next()){
				System.out.println("ID "+res.getInt("id") + ": " +res.getString("fname") + " " + res.getString("lname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
