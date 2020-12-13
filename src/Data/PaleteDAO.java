package Data;


import Business.Palete;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    private PaleteDAO() {
        try (Connection com = DriverManager.getConnection(ConfigDAO.URL,ConfigDAO.DATABASE,ConfigDAO.CREDENTIALS)) {
            Statement stm = com.createStatement();

            stm.executeUpdate("create table Palete (" +
                    "Codigo varchar(10) not null primary key," +
                    "Altura float not null," +
                    "Localizacao varchar(10) not null foreign key references Localizacao(Codigo)," +
                    "Disponivel boolean not null");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PaleteDAO getInstance() {
        if (PaleteDAO.getInstance()==null) {
            PaleteDAO.singleton = new PaleteDAO();
        }
        return PaleteDAO.singleton;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Palete get(Object key) {
        return null;
    }

    @Override
    public Palete put(String key, Palete value) {
        return null;
    }

    @Override
    public Palete remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Palete> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Palete> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Palete>> entrySet() {
        return null;
    }
}
