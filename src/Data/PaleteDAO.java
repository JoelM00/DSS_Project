package Data;


import Business.Palete;

import java.util.Map;

/*
public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    private PaleteDAO() {
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Paletes (" +
                    "codPalete varchar(10) NOT NULL PRIMARY KEY," +
                    "Estado bool DEFAULT," +
                    "Altura int(4) DEFAULT 0)"+
                    "Localizacao varchar(10), foreign key(Localizacao) references Localizacao(IdLocal))";;
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Localizacao (" +
                    "ID varchar(10) NOT NULL PRIMARY KEY," +
                    "Corredor int(4) DEFAULT NOT NULL," +
                    "Prateleira int(4) DEFAULT NOT NULL)" ;
            stm.executeUpdate(sql);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
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
    public boolean containsKey(Object key) {
        Connection conn = ConfigDAO.connect();
        boolean r;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs =
                    stm.executeQuery("SELECT codPalete FROM Paletes WHERE codPalete='"+key.toString()+"'");
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    //falta comparar a localização
    @Override
    public boolean containsValue(Object value) {
        Palete p = (Palete) value;
        boolean r = false;
        Palete c = this.get(p.getCodigo());
        if (c!=null){
            if((p.getDisponivel() == c.getDisponivel()))
                r = p.getAltura() == c.getAltura();
        }
        return r;
    }

    @Override
    public Palete get(Object key) {
        Connection conn = ConfigDAO.connect();
        Palete p = null;
        Localizacao loc = null;
        try  {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Paletes p WHERE codPalete='"+key.toString()+"' INNER JOIN Localizacao l  on p.Localizacao = l.ID");
            if (rs.next()) {  // A chave existe na tabela
                loc = new Localizacao(rs.getInt("Corredor"), rs.getInt("Prateleira"));
                p = new Palete(rs.getString("codPalete"), rs.getInt("Altura"), loc, rs.getBoolean("Estado"));
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
    public Palete put(String key, Palete value) {
        Palete pl = null;
        String sql;
        Connection conn = ConfigDAO.connect();
        try {
            Statement stm = conn.createStatement();
            if (this.containsKey(key)) {
                stm.executeUpdate("UPDATE Paletes SET " +
                        "Estado = '" + value.getDisponivel() +
                        "', Altura = '" + value.getAltura() +
                        "'WHERE codPalete = '" + key + "'");
                return value;
            }
            else {
                sql =
                        "INSERT INTO Paletes (codPalete, Estado, Altura, Localizacao) VALUES" +
                                " ('" + key + "'," +
                                "'" + value.getDisponivel() +
                                "'," + value.getAltura() +
                                "'," + value.getLoc() + "')";
            }
            int i = stm.executeUpdate(sql);
            return new Palete(value.getCodigo(), value.getAltura(), value.getLoc(), value.getDisponivel());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            ConfigDAO.close(conn);
        }
    }

    @Override
    public Palete remove(Object key) {
        Connection conn = ConfigDAO.connect();
        try {
            Palete p = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM Paletes WHERE (`codPalete` = " +
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
            stm.executeUpdate("DELETE FROM Paletes");
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
            ResultSet rs = stm.executeQuery("select distinct codPalete from" +
                    " Paletes");
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
*/