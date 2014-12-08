package model.database;

import model.Model;
import model.QueryBuild.QueryBuilder;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import model.calendar.*;

public class DatabaseInit extends Model {

	CalendarMethods cm = new CalendarMethods();
	QueryBuilder qb = new QueryBuilder();
    public static void main(String[] args) throws IOException, SQLException {
        new DatabaseInit().go();
    }
/**
 * først sletter den dit schema hvis du har et som hedder cbscalendar
 * Derefter opretter den cbscalendar og alle de påkrævede tabeller
 * Så tjekker den om der er blevet oprettet tabeller og hvis ikke, proever den at koere igen
 * @throws SQLException
 * @throws IOException
 */
    public void go() throws SQLException, IOException {
 	
    	readfromSqlFile("src/SQLFiles/createDBscript.sql");
    	cm.export2Database();
        resultSet = qb.selectFrom("users").all().ExecuteQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("email"));
        }
        resultSet.close();
        if (doesDatabaseExist()) {
            System.out.print("Database environment does exist");
        } else {
            System.out.print("Database environment does NOT exist");
            readfromSqlFile("src/SQLFiles/createDBscript.sql");
        }

    }
}
