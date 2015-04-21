package org.jeffklein.turfwars.codes.dataaccess.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Jeff
 * Date: 4/20/15
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScriptRunnerWrapper {

    private static Log LOG = LogFactory.getLog(ScriptRunnerWrapper.class);

    public static void runScriptFromClasspath(String classpathLocation, DataSource dataSource) {
        Connection conn = null;
        InputStream resetScriptIS = null;
        try {
            conn = dataSource.getConnection();
            resetScriptIS = ScriptRunnerWrapper.class.getResourceAsStream(classpathLocation);
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.runScript(new InputStreamReader(resetScriptIS));
        }
        catch (Exception e) {
            throw new RuntimeException("Error while running script at classpath:"+classpathLocation, e);
        }
        finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                if (resetScriptIS != null) {
                    resetScriptIS.close();
                }
            }
            catch(Exception e) {
                LOG.error("Unable to close DB Connection or InputStream", e);
            }
        }
    }
}
