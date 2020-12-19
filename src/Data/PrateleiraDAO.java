package Data;

import Business.Prateleira;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrateleiraDAO implements Map<String, Prateleira> {
    private static PrateleiraDAO singleton = null;

    private PrateleiraDAO() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS `Prateleira` (" +
                    "`Codigo` VARCHAR(45) NOT NULL," +
                    "  `Altura` float NOT NULL," +
                    "  `Estado` TINYINT NOT NULL," +
                    "  PRIMARY KEY (`Codigo`))" ;
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS `Prateleira_Localizacao` (" +
                    "  `seccao` INT NOT NULL," +
                    "  `Prateleira_Codigo` VARCHAR(45) NULL," +
                    "  `Localizacao_Numero` INT NOT NULL," +
                    "  PRIMARY KEY (`seccao`)," +
                    "  CONSTRAINT `fk_Prateleira_Localizacao_Prateleira`" +
                    "    FOREIGN KEY (`Prateleira_Codigo`)" +
                    "    REFERENCES `Prateleira` (`Codigo`)," +
                    "  CONSTRAINT `fk_Prateleira_Localizacao_Localizacao1`" +
                    "    FOREIGN KEY (`Localizacao_Numero`)" +
                    "    REFERENCES `Localizacao` (`Numero`))";


            stm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    public static PrateleiraDAO getInstance() {
        if (PrateleiraDAO.singleton==null) {
            PrateleiraDAO.singleton = new PrateleiraDAO();
        }
        return PrateleiraDAO.singleton;
    }

    @Override
    public int size() throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        int i = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM Prateleira") ;
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }  finally {
            ConfigDAO.close(conn);
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        boolean r;
        try {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT Codigo FROM Prateleira WHERE Codigo='"+key.toString()+"'");

            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Prateleira ptl = (Prateleira) value;
        boolean r = false;
        Prateleira c = this.get(ptl.getCodigo());
        if (c!=null){
            if(ptl.getDisponivel() == c.getDisponivel())
                r = ptl.getAltura() == c.getAltura();
        }
        return r;
    }

    @Override
    public Prateleira get(Object key) throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        Prateleira ptl = null;
        try  {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Prateleira WHERE Codigo ='"+key.toString()+"' ");
            if (rs.next()) {  // A chave existe na tabela
                ptl = new Prateleira(rs.getString("Codigo"), rs.getInt("Altura"),  rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
        return ptl;
    }

    @Override
    public Prateleira put(String key, Prateleira value) throws NullPointerException{
        Prateleira ptl = null;
        String sql;
        int estado;
        if(value.getDisponivel()) estado = 1;
        else estado=0;
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();
            if (this.containsKey(key)) {
                stm.executeUpdate("UPDATE Prateleira SET " +
                        "Altura = '" + value.getAltura() +
                        "', Estado = '" + estado +
                        "'WHERE Codigo = '" + key + "'");
                return value;
            } else {
                sql = "INSERT INTO Prateleira (Codigo, Altura, Estado) VALUES" +
                                " ('" + key + "'," +
                                "'" + value.getAltura() +
                                "','" + estado + "')";
            }
            int i = stm.executeUpdate(sql);
            return new Prateleira(value.getCodigo(), value.getAltura(), value.getDisponivel());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public Prateleira remove(Object key) throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        try {
            Prateleira p = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Prateleira WHERE (`Codigo` = " +
                    "'" + key + "');";
            int i = stm.executeUpdate(sql);
            return p;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public void putAll(Map<? extends String, ? extends Prateleira> m) {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public void clear() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Prateleira");
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public Set<String> keySet() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select distinct Codigo from" +
                    " Prateleira");
            Set<String> res = new HashSet<>();
            while (rs.next())
                res.add(rs.getString(1));
            return res;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public Collection<Prateleira> values() {
        Collection<Prateleira> col = new HashSet<>();
        Set<String> keys = this.keySet();
        keys.forEach(x -> col.add(this.get(x)));
        return col;
    }

    @Override
    public Set<Entry<String, Prateleira>> entrySet() {
        throw new NullPointerException("Not implemented!");
    }
}
