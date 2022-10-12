package kg.proquality.e2e.db;

import static kg.proquality.e2e.utils.Utils.loadSqlFromFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import kg.proquality.trader.model.Stock;
import kg.proquality.trader.model.User;
import org.springframework.stereotype.Service;

@Service
public class PostgresClient extends AbstractPostgresClient {

    private static String CLEAN_DATABASE_SQL = loadSqlFromFile("sql/clean_database.sql");
    private static String ADD_CASCADE_DELETE_SQL = loadSqlFromFile("sql/add_cascade_delete.sql");

    private static String CREATE_USER_SQL = loadSqlFromFile("sql/create_user.sql");
    private static String CREATE_STOCK_SQL = loadSqlFromFile("sql/create_stock.sql");
    private static String USER_HAS_STOCK_SQL = loadSqlFromFile("sql/user_has_stock.sql");

    private static String FIND_STOCK_BY_TICKER_SQL = loadSqlFromFile("sql/find_stock_by_ticker.sql");
    private static String FIND_USER_STOCKS_SQL = loadSqlFromFile("sql/find_user_stocks.sql");

    @PostConstruct
    public void addCascadeDelete() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(ADD_CASCADE_DELETE_SQL);
        } catch (SQLException e) {
            throw new IllegalStateException("Could not add cascade delete", e);
        }
    }

    public void cleanDatabase() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_DATABASE_SQL);
        } catch (SQLException e) {
            throw new IllegalStateException("Could not clean database", e);
        }
    }

    public void createUser(User user) {
        try(PreparedStatement statement = connection.prepareStatement(CREATE_USER_SQL)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Could not create user", e);
        }
    }

    public void createStock(Stock stock) {
        try(PreparedStatement statement = connection.prepareStatement(CREATE_STOCK_SQL)) {
            statement.setString(1, stock.getTicker());
            statement.setDouble(2, stock.getSellPrice());
            statement.setDouble(3, stock.getSellPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Could not create stock", e);
        }
    }

    public Stock findStockByTicker(String ticker) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_STOCK_BY_TICKER_SQL)) {
            statement.setString(1, ticker);

            ResultSet rs = statement.executeQuery();
            rs.next();

            Stock stock = new Stock()
                .setId(rs.getInt("id"))
                .setTicker(ticker)
                .setBuyPrice(rs.getDouble("buy_price"))
                .setSellPrice(rs.getDouble("sell_price"));

            return stock;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Could not extract stock with ticker: " + ticker, e);
        }
    }

    public void userHasStock(Integer userId, Integer stockId, Integer amount) {
        try(PreparedStatement statement = connection.prepareStatement(USER_HAS_STOCK_SQL)) {
            statement.setInt(1, userId);
            statement.setInt(2, stockId);
            statement.setInt(3, amount);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Could not add stocks to user", e);
        }
    }

    public Map<String, Integer> findUserStocks(Integer userId) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_USER_STOCKS_SQL)) {
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            Map<String, Integer> userStocks = new HashMap<>();

            while (rs.next()) {
                userStocks.put(
                    rs.getString("ticker"),
                    rs.getInt("amount")
                );
            }
            return userStocks;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Could not get stocks of user with id: " + userId, e);
        }
    }

}
