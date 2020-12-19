package Data;


import Business.Palete;
import Business.Localizacao;
import Business.Posicao;
import Business.Prateleira;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    private PaleteDAO() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS `Palete` (" +
                    "  `Codigo` VARCHAR(45) NOT NULL," +
                    "  `Estado` TINYINT NOT NULL," +
                    "  `Estado_Transporte` TINYINT NOT NULL," +
                    "  `Altura` INT(4) NOT NULL," +
                    "  `Localizacao_Numero` INT NOT NULL," +
                    "  PRIMARY KEY (`Codigo`)," +
                    "  INDEX `fk_Palete_Localizacao1_idx` (`Localizacao_Numero` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_Palete_Localizacao1`" +
                    "    FOREIGN KEY (`Localizacao_Numero`)" +
                    "    REFERENCES `Localizacao` (`Numero`))";
            stm.executeUpdate(sql);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    public static PaleteDAO getInstance() {
        if (PaleteDAO.singleton == null) {
            PaleteDAO.singleton = new PaleteDAO();
        }
        return PaleteDAO.singleton;
    }

    @Override
    public int size() throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        int i = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM Paletes") ;
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
            ResultSet rs =
                    stm.executeQuery("SELECT Codigo FROM Palete WHERE Codigo='"+key.toString()+"'");
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
        Palete p = (Palete) value;
        boolean r = false;
        Palete c = this.get(p.getCodigo());
        if (c!=null){
            if(p.getDisponivel() == c.getDisponivel())
                if(p.getAltura() == c.getAltura())
                    r = p.getLoc() == c.getLoc();
        }
        return r;
    }

    @Override
    public Palete get(Object key) throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        Palete p = null;
        Localizacao loc = new Posicao();
        Prateleira ptl = null;
        try  {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM Palete as p " +
                    "INNER JOIN Localizacao as l on p.Localizacao_Numero = l.Numero " +
                    "WHERE p.Codigo = '"+key+"'");
            if (rs.next()) {  // A chave existe na tabela
                loc.setNumero(rs.getInt("Numero"));
                p = new Palete(rs.getString("Codigo"), rs.getInt("Altura"), loc, rs.getBoolean("Estado"),rs.getBoolean("Estado_Transporte"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
        return p;
    }

    @Override
    public Palete put(String key, Palete value) throws NullPointerException{
        Palete pl = null;
        String sql;
        int estado;
        if(value.getDisponivel()) estado = 1;
        else estado=0;
        int estadoT;
        if(value.getEmTransporte()) estadoT = 1;
        else estadoT=0;
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();

            if (this.containsKey(key)) {
                stm.executeUpdate("UPDATE Palete SET " +
                        "   Estado = '" + estado +
                        "', Estado_Transporte = '" + value.getEmTransporte() +
                        "', Altura = '" + value.getAltura() +
                        "', Localizacao_Numero = '" + value.getLoc().getNumero() +
                        "'WHERE Codigo = '" + key + "'");
                return value;

            } else {

                sql = "INSERT INTO Palete (Codigo, Estado, Estado_Transporte, Altura, Localizacao_Numero) VALUES" +
                                " ('" + key + "'," +
                                "'" + estado +
                                "','" + estadoT +
                                "','" + value.getAltura() +
                                "','" + value.getLoc().getNumero() + "')";
            }

            int i = stm.executeUpdate(sql);

            return new Palete(value.getCodigo(), value.getAltura(), value.getLoc(), value.getDisponivel(), value.getEmTransporte());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public Palete remove(Object key) throws NullPointerException{
        Connection conn = ConfigDAO.connect();
        try {
            Palete p = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Palete WHERE (`Codigo` = " +
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
    public void putAll(Map<? extends String, ? extends Palete> m) {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public void clear() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Palete");
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
                    " Palete");
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
    public Collection<Palete> values() {
        Collection<Palete> col = new HashSet<>();
        Set<String> keys = this.keySet();
        keys.forEach(x -> col.add(this.get(x)));
        return col;
    }

    @Override
    public Set<Entry<String, Palete>> entrySet() {
        throw new NullPointerException("Not implemented!");
    }
}
