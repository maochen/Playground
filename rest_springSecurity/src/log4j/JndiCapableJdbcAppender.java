package log4j;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.ErrorCode;

/**
 * This appender comes from http://blog.iprofs.nl/2011/02/07/database-logging-with-log4j/
 * 
 * @author MaochenG
 * 
 */
public class JndiCapableJdbcAppender extends JDBCAppender {

    private String jndiDataSource;

    @Override
    protected Connection getConnection() throws SQLException {
        if (jndiDataSource == null) {
            return super.getConnection();
        }
        else {
            return lookupDataSource().getConnection();
        }
    }

    private DataSource lookupDataSource() {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup(jndiDataSource);
            return ds;
        }
        catch (NamingException e) {
            throw new RuntimeException("Cannot find JNDI DataSource: " + jndiDataSource, e);
        }
    }

    @Override
    protected void closeConnection(Connection con) {
        
        try {
            con.close();
        }
        catch (SQLException e) {
            errorHandler.error("Failed to close connection", e, ErrorCode.CLOSE_FAILURE);
        }
    }

    @Override
    protected void execute(String sql) throws SQLException {
        super.execute(sql); // single quotation does not need to be escaped if using super.execute.
    }

    public String getJndiDataSource() {
        return jndiDataSource;
    }

    public void setJndiDataSource(String jndiDataSource) {
        this.jndiDataSource = jndiDataSource;
    }
}
