package Data;

import Business.Prateleira;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
public class PrateleiraDAO implements Map<String, Prateleira> {
    private static PrateleiraDAO singleton = null;

    private PrateleiraDAO() {
        try (Connection com = ConfigDAO.connect()) {
            Statement stm = com.createStatement();

            stm.executeUpdate("create table Prateleira (" +
                    "Codigo varchar(10) not null primary key," +
                    "Altura float not null," +
                    "Localizacao varchar(10) not null foreign key references Localizacao(Codigo))");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PrateleiraDAO getInstance() {
        if (PrateleiraDAO.singleton == null) {
            PrateleiraDAO.singleton = new PrateleiraDAO();
        }
        return PrateleiraDAO.singleton;
    }


    @Override
    public int size() {
        int size = 0;
        try (Connection com = ConfigDAO.connect()) {
            Statement stm = com.createStatement();

            ResultSet rset = stm.executeQuery("select count(*) from Prateleira");

            if (rset.next()) size = rset.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        try (Connection com = ConfigDAO.connect()) {
            Statement stm = com.createStatement();

            ResultSet rset = stm.executeQuery("select Codigo from Prateleiras where Codigo = '"+ key.toString()+"'");

            res = rset.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean containsValue(Object value) {
        Prateleira p = (Prateleira) value;
        return this.containsKey(p.getCodigo());
     }


    @Override
    public Prateleira get(Object key) {
        Prateleira p = null;
        try (Connection com = ConfigDAO.connect()) {
            Statement stm = com.createStatement();

            ResultSet rset = stm.executeQuery("select * from Prateleira where Codigo ='"+key.toString()+"'");

           /* if (rset.next()) {

                while (rset.next()) {
                    p = new Prateleira(rset.getString("Codigo"),
                            rset.getFloat("Altura"),
                            rset.getS)
                }


             }
/*
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public Prateleira put(String key, Prateleira value) {
        return null;
    }
*//*
    public Prateleira remove(Object key) {
        Prateleira p = this.get(key);
        try (Connection conn =ConfigDAO.connect()) {
             Statement stm = conn.createStatement();

             stm.executeUpdate("DELETE FROM Prateleira WHERE Id='"+key+"'");

        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Prateleira> m) {
        for (Prateleira p : m.values()) {
            this.put(p.getCodigo(),p);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = ConfigDAO.connect()) {
             Statement stm = conn.createStatement();
            stm.executeUpdate("TRUNCATE Prateleira");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Prateleira> values() {
        Collection<Prateleira> res = new HashSet<>();
        try (Connection conn = ConfigDAO.connect();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Codigo FROM Prateleira")) {
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                Prateleira t = this.get(codigo);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<Entry<String, Prateleira>> entrySet() {
        return null;
    }

}
*/