package com.savko.pool;

import com.savko.pool.configuration.ConnectionPoolConfig;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String URL = "db.url";
    private static final String USER_NAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.connections.pool.size";
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static AtomicBoolean isClosing = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private String url;
    private String username;
    private String password;
    private int poolSize;

    private BlockingQueue<ConnectionProxy> busyConnections;
    private BlockingQueue<ConnectionProxy> freeConnections;

    private ConnectionPool() {
        ConnectionPoolConfig connectionPoolConfig = ConnectionPoolConfig.getInstance();
        setPoolSize(Integer.parseInt(connectionPoolConfig.getString((ConnectionPool.POOL_SIZE))));
        setUrl(connectionPoolConfig.getString(ConnectionPool.URL));
        setUsername(connectionPoolConfig.getString(ConnectionPool.USER_NAME));
        setPassword(connectionPoolConfig.getString(ConnectionPool.PASSWORD));
        init();
    }

    /**
     * Lazy initialization of connection pool
     *
     * @return instance of connection pool
     */
    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Creates connections and fills freeConnections queue with it
     */
    private void init() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            busyConnections = new ArrayBlockingQueue<>(getPoolSize());
            freeConnections = new ArrayBlockingQueue<>(getPoolSize());

            for (int i = 0; i < poolSize; i++) {
                ConnectionProxy connection = new ConnectionProxy(
                        DriverManager.getConnection(getUrl(), getUsername(), getPassword())
                );
                freeConnections.add(connection);
            }
            LOG.info("ConnectionPool is initialized");
        } catch (SQLException e) {
            LOG.fatal("ConnectionPool's initialization error", e);
            throw new RuntimeException();
        }
    }

    /**
     * Takes free connection from pool
     *
     * @return taken connection
     */
    public ConnectionProxy takeConnection() {
        ConnectionProxy connection = null;
        try {
            if (!isClosing.get()) {
                connection = freeConnections.take();
                busyConnections.put(connection);
            }
        } catch (InterruptedException e) {
            LOG.error("Pool connection error", e);
        }
        return connection;
    }

    /**
     * Gets connection back to pool
     *
     * @param connection connection for releasing
     */
    public void releaseConnection(ConnectionProxy connection) {
        try {
            if (connection != null) {
                busyConnections.remove(connection);
                freeConnections.put(connection);
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException", e);
        }
    }

    /**
     * After clearing connections queues assign a value null to variable instance
     */
    public void closePool() {
        isClosing.set(true);

        try {
            if (instance != null) {
                instance.clearConnectionQueue();
            }
            instance = null;
            LOG.info("ConnectionPool is closed");
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
    }

    /**
     * Clears busyConnections and freeConnections queues and terminates connections
     *
     * @throws SQLException
     */
    private void clearConnectionQueue() throws SQLException {
        ConnectionProxy connection;

        while ((connection = busyConnections.poll()) != null) {
            connection.terminateConnection();
        }

        while ((connection = freeConnections.poll()) != null) {
            connection.terminateConnection();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}