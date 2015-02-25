package com.br.ifpb.persiste.dao.implementacao;

import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.persiste.conexao.banco.ConexaoBanco;
import com.br.ifpb.persiste.dao.abstracao.EventoDaoIf;
import com.br.ifpb.value.object.Evento;
import com.br.ifpb.value.object.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 * @date 04/02/2015
 */
public class EventoDao implements EventoDaoIf {

    @Override
    public void adicionar(Evento... eventos) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            for (Evento evento : eventos) {
                String sql = "INSERT INTO Evento(nome,descricao,dataInicio,dataFinal,responsavel"
                        + ",totalParticipantes,status,id_sala) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setString(1, evento.getNome());
                stat.setString(2, evento.getDescricao());
                stat.setTimestamp(3, evento.getDataInicio());
                stat.setTimestamp(4, evento.getDataFinal());
                stat.setString(5, evento.getResponsavel());
                stat.setInt(6, evento.getTotalParticipantes());
                stat.setString(7, evento.getStatus());
                stat.setObject(8, evento.getSala() == null ? null : evento.getSala().getId(), Types.INTEGER);
                stat.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }

    }

    @Override
    public void mudarStatus(Integer id, String status) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "UPDATE Evento SET status=? WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, status);
            stat.setInt(2, id);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void alocar(Integer sala, Evento... eventos) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            for (Evento evento : eventos) {
                String sql = "UPDATE Evento SET id_sala=?, status=? WHERE id=?";
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setInt(1, sala);
                stat.setString(2, evento.getStatus());
                stat.setInt(3, evento.getId());
                stat.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void desalocar(Integer id_evento) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "UPDATE Evento SET id_sala=?, status=? WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setObject(1, null, Types.INTEGER);
            stat.setString(2, Evento.STATUS_PENDENTE_LOCAL);
            stat.setInt(3, id_evento);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Sala> listarSalasDisponiveisEvento(Evento... eventos) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            if (eventos != null && eventos.length > 0) {
                StringBuilder sql = new StringBuilder();
                sql = sql.append("(SELECT S.tipo,S.id,S.capacidade,S.identificacao,S.apelido FROM Sala S)");
                for (Evento evento : eventos) {
                    sql = sql.append("EXCEPT (SELECT S.tipo,S.id,S.capacidade,S.identificacao,S.apelido FROM Sala S JOIN Evento E ON S.id=E.id_sala WHERE ");
                    sql = sql.append("E.dataInicio>=? AND E.dataFinal<=? AND status<>? OR E.dataInicio<=? AND E.dataFinal>=? AND status<>? OR ");
                    sql = sql.append("E.dataInicio>=?AND E.dataFinal>=? AND status<>? AND E.dataInicio<=? AND E.dataFinal<=?) ");
                }
                sql = sql.append("EXCEPT (SELECT S.tipo,S.id,S.capacidade,S.identificacao,S.apelido FROM Sala S WHERE S.capacidade<?)");
                PreparedStatement stat = con.prepareStatement(sql.toString());
                int cont = 0;
                for (Evento evento : eventos) {
                    stat.setTimestamp(++cont, evento.getDataInicio());
                    stat.setTimestamp(++cont, evento.getDataInicio());
                    stat.setString(++cont, Evento.STATUS_CANCELADO);
                    stat.setTimestamp(++cont, evento.getDataFinal());
                    stat.setTimestamp(++cont, evento.getDataFinal());
                    stat.setString(++cont, Evento.STATUS_CANCELADO);
                    stat.setTimestamp(++cont, evento.getDataInicio());
                    stat.setTimestamp(++cont, evento.getDataInicio());
                    stat.setString(++cont, Evento.STATUS_CANCELADO);
                    stat.setTimestamp(++cont, evento.getDataFinal());
                    stat.setTimestamp(++cont, evento.getDataFinal());
                }
                stat.setInt(++cont, eventos[0].getTotalParticipantes());
                ResultSet rs = stat.executeQuery();
                List<Sala> salas = new ArrayList<>();
                while (rs.next()) {
                    Sala sala = new Sala();
                    sala.setApelido(rs.getString("apelido"));
                    sala.setId(rs.getInt("id"));
                    sala.setCapacidade(rs.getInt("capacidade"));
                    sala.setIdentificacao(rs.getString("identificacao"));
                    sala.setTipo(rs.getString("tipo"));
                    salas.add(sala);
                }
                return salas;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Evento> buscarEvento(String nome, String descricao,
            Timestamp data, String responsavel, String... status) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            StringBuilder sql = new StringBuilder();
            sql = sql.append("SELECT * FROM Evento E WHERE nome ilike ? AND descricao ilike ?").
                    append(" AND responsavel ilike ? ");
            if (status != null) {
                sql = sql.append("AND status IN (");
            }
            for (int i = 0; status != null && i < status.length; i++) {
                if (!(i == status.length - 1)) {
                    sql = sql.append("?,");
                } else {
                    sql = sql.append("?) ");
                }
            }
            if (data != null) {
                sql = sql.append("AND dataInicio<=? AND dataFinal>=?");
            }
            PreparedStatement stat = con.prepareStatement(sql.toString());
            int cont = 0;
            stat.setString(++cont, nome == null ? "%%" : "%" + nome + "%");
            stat.setString(++cont, descricao == null ? "%%" : "%" + descricao + "%");
            stat.setString(++cont, responsavel == null ? "%%" : "%" + responsavel + "%");
            for (int i = 0; status != null && i < status.length; i++) {
                stat.setString(++cont, status[i] == null ? "" : status[i]);
            }
            if (data != null) {
                stat.setTimestamp(++cont, data);
                stat.setTimestamp(++cont, data);
            }
            ResultSet rs = stat.executeQuery();
            List<Evento> eventos = new LinkedList<>();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setId(rs.getInt("id"));
                evento.setResponsavel(rs.getString("responsavel"));
                evento.setDataInicio(rs.getTimestamp("dataInicio"));
                evento.setDataFinal(rs.getTimestamp("dataFinal"));
                evento.setStatus(rs.getString("status"));
                eventos.add(evento);
            }
            return eventos.isEmpty() ? null : eventos;

        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Evento> listarEventos() throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT * FROM Evento WHERE status<>?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, Evento.STATUS_CANCELADO);
            ResultSet rs = stat.executeQuery();
            List<Evento> eventos = new LinkedList<>();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setDataFinal(rs.getTimestamp("datafinal"));
                evento.setDataInicio(rs.getTimestamp("datainicio"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setNome(rs.getString("nome"));
                evento.setResponsavel(rs.getString("responsavel"));
                evento.setId(rs.getInt("id"));
                evento.setStatus(rs.getString("status"));
                evento.setTotalParticipantes(rs.getInt("totalparticipantes"));
                SalaDao salaDao = new SalaDao();
                evento.setSala(salaDao.getSala(rs.getInt("id_sala")));
                eventos.add(evento);
            }
            return eventos.isEmpty() ? null : eventos;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Evento> eventosRealizados() throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT * FROM Evento WHERE dataFinal<? AND status<>? AND status<>?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(2, Evento.STATUS_REALIZADO);
            stat.setString(3, Evento.STATUS_CANCELADO);
            ResultSet rs = stat.executeQuery();
            List<Evento> eventos = new LinkedList<>();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setDataFinal(rs.getTimestamp("datafinal"));
                evento.setDataInicio(rs.getTimestamp("datainicio"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setNome(rs.getString("nome"));
                evento.setResponsavel(rs.getString("responsavel"));
                evento.setId(rs.getInt("id"));
                evento.setStatus(rs.getString("status"));
                evento.setTotalParticipantes(rs.getInt("totalparticipantes"));
                SalaDao salaDao = new SalaDao();
                evento.setSala(salaDao.getSala(rs.getInt("id_sala")));
                eventos.add(evento);
            }
            return eventos.isEmpty() ? null : eventos;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public List<Evento> listarEventoStatus(String status) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT * FROM Evento WHERE status=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, status);
            ResultSet rs = stat.executeQuery();
            List<Evento> eventos = new LinkedList<>();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setDataFinal(rs.getTimestamp("datafinal"));
                evento.setDataInicio(rs.getTimestamp("datainicio"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setNome(rs.getString("nome"));
                evento.setResponsavel(rs.getString("responsavel"));
                evento.setId(rs.getInt("id"));
                evento.setStatus(rs.getString("status"));
                evento.setTotalParticipantes(rs.getInt("totalparticipantes"));
                SalaDao salaDao = new SalaDao();
                evento.setSala(salaDao.getSala(rs.getInt("id_sala")));
                eventos.add(evento);
            }
            return eventos.isEmpty() ? null : eventos;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean possuiEventosRealizados() throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT COUNT(*) quantidade FROM Evento WHERE dataFinal<? AND status<>? AND status<>?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(2, Evento.STATUS_REALIZADO);
            stat.setString(3, Evento.STATUS_CANCELADO);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantidade") > 0;
            }
            return false;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public Evento getEvento(Integer id) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "SELECT * FROM Evento WHERE id=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                Evento evento = new Evento();
                evento.setDataFinal(rs.getTimestamp("datafinal"));
                evento.setDataInicio(rs.getTimestamp("datainicio"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setNome(rs.getString("nome"));
                evento.setResponsavel(rs.getString("responsavel"));
                evento.setId(rs.getInt("id"));
                evento.setStatus(rs.getString("status"));
                evento.setTotalParticipantes(rs.getInt("totalparticipantes"));
                return evento;
            }
            return null;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public void desalocarPelaSala(Integer id_sala) throws PersistenciaException {
        try (Connection con = ConexaoBanco.getConexao()) {
            String sql = "UPDATE Evento SET id_sala=?, status=? WHERE id_sala=?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setObject(1, null, Types.INTEGER);
            stat.setString(2, Evento.STATUS_PENDENTE_LOCAL);
            stat.setInt(3, id_sala);
            stat.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

}
