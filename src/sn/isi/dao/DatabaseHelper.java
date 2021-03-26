package sn.isi.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseHelper {
    private Connection con;
    //private Statement stmt;
    private PreparedStatement pstmt;
    private static DatabaseHelper db;
    private DatabaseHelper()
    {

    }

    public static DatabaseHelper getInstance()
    {
        if(db==null)
        {
            db = new DatabaseHelper();
        }

        return db;
    }

    private void openConnection() throws Exception
    {
        try
        {
            if(con == null || con.isClosed())
            {
                Class.forName("com.mysql.jdbc.Driver");
                String user ="root",mp = "",url = "jdbc:mysql://localhost:3306/mailing";
                con = DriverManager.getConnection(url,user, mp);
                //stmt = con.createStatement();
                System.out.println("bien connecte");
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void myPrepareStatement(String sql) throws Exception
    {
        try
        {
            openConnection();
            if(sql.trim().toLowerCase().startsWith("insert"))
            {
                pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            else
            {
                pstmt = con.prepareStatement(sql);
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
    }

    public void addParameters(Object [] parameters) throws SQLException
    {
        try {
            for (int i = 0; i < parameters.length; i++)
            {
                pstmt.setObject(i+1, parameters[i]);
                System.out.println("\t"+ parameters[i]);

            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    public ResultSet myExecuteQuery() throws Exception
    {
        try
        {
            return pstmt.executeQuery();
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    public int myExecuteUpdate() throws Exception
    {
        int retour = -1;
        try
        {
            retour = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                retour = rs.getInt(1);
            }
            rs.close();
        }
        catch (Exception e)
        {
            throw e;
        }
        return retour;
    }

    public void closeConnection() throws Exception
    {
        try
        {
            pstmt.close();
            con.close();
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    public void begingTransaction() throws Exception
    {
        try
        {
            con.setAutoCommit(false);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void endTransaction() throws Exception
    {
        try
        {
            con.setAutoCommit(true);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void myCommit() throws Exception
    {
        try
        {
            con.commit();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void myRollBack() throws Exception
    {
        try
        {
            con.rollback();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
