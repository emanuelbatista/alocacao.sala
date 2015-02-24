package com.br.ifpb.persiste.dao.implementacao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.conexao.banco.ConexaoBanco;
import com.br.ifpb.persiste.dao.abstracao.SalaDaoIf;
import com.br.ifpb.value.object.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emanuel
 */
public class SalaDao implements SalaDaoIf {

    @Override
    public void adicionar(Sala sala) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "INSERT INTO Sala(tipo,apelido,capacidade,identificacao) VALUES (?,?,?,?)";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, sala.getTipo());
            stat.setString(2, sala.getApelido());
            stat.setInt(3, sala.getCapacidade());
            stat.setString(4, sala.getIdentificacao());
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void editar(Sala sala) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "UPDATE Sala SET tipo=?, apelido=?, capacidade=?,identificacao=? WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, sala.getTipo());
            stat.setString(2, sala.getApelido());
            stat.setInt(3, sala.getCapacidade());
            stat.setString(4, sala.getIdentificacao());
            stat.setInt(5, sala.getId());
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void remover(Integer id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "DELETE FROM Sala WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Sala> listar() throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT * FROM Sala";
            PreparedStatement stat = con.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            List<Sala> listaSala = new ArrayList<>();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setApelido(rs.getString("apelido"));
                sala.setCapacidade(rs.getInt("capacidade"));
                sala.setId(rs.getInt("id"));
                sala.setIdentificacao(rs.getString("identificacao"));
                sala.setTipo(rs.getString("tipo"));
                listaSala.add(sala);
            }
            return listaSala.isEmpty() ? null : listaSala;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public Sala getSala(Integer id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
           String sql="SELECT * FROM Sala WHERE id=?";
           PreparedStatement stat=con.prepareStatement(sql);
           stat.setInt(1, id);
           ResultSet rs=stat.executeQuery();
           if(rs.next()){
               Sala sala=new Sala();
               sala.setApelido(rs.getString("apelido"));
               sala.setCapacidade(rs.getInt("capacidade"));
               sala.setId(rs.getInt("id"));
               sala.setIdentificacao(rs.getString("identificacao"));
               sala.setTipo(rs.getString("tipo"));
               return sala;
           }else return null;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

}
