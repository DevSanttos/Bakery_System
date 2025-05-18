package model.dao;

import connection.ConnectionFactory;
import model.bean.Cliente;
import model.bean.ItemVenda;
import model.bean.Produto;
import model.bean.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private static final String SQL_INSERT_VENDA = "INSERT INTO Venda (data_venda, id_cliente) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL_VENDAS = "SELECT * FROM ItemVenda WHERE id_venda = ?";
    private static final String SQL_UPDATE_VENDAS = "UPDATE Venda SET data_venda = ?, id_cliente = ? WHERE id_venda = ?";
    private static final String SQL_DELETE_VENDA = "DELETE FROM Venda WHERE id_venda = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Venda WHERE id_venda = ?";
    private final ItemVendaDAO itemDAO = new ItemVendaDAO();

    public Long create(Venda venda) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(false);


            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_VENDA, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, venda.getDataVendaSQL());
            stmt.setLong(2, venda.getCliente().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                venda.setIdVenda(rs.getLong(1));
            }

            for (ItemVenda item : venda.getItens()) {
                item.setVenda(venda); // importante
                itemDAO.create(item, con);
            }

            con.commit();
            return venda.getIdVenda();

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }

    public Venda findById(Long id) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        Venda venda = null;

        PreparedStatement stmt = con.prepareStatement(SQL_FIND_BY_ID);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            venda = new Venda();
            venda.setIdVenda(rs.getLong("id_venda"));
            venda.setDataVenda(rs.getDate("data_venda").toLocalDate());

            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("id_cliente"));
            venda.setCliente(cliente);

            // Buscar itens da venda
            venda.setItens(read(id, con));
        }

        con.close();
        return venda;
    }

    public List<ItemVenda> read(Long idVenda, Connection con) throws Exception {
        PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL_VENDAS);
        stmt.setLong(1, idVenda);
        ResultSet rs = stmt.executeQuery();

        List<ItemVenda> itens = new ArrayList<>();

        while (rs.next()) {
            ItemVenda item = new ItemVenda();
            item.setIdItemVenda(rs.getLong("id_item_venda"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setPrecoUnitario(rs.getDouble("preco_unitario"));

            Produto produto = new Produto();
            produto.setIdProduto(rs.getLong("id_produto"));
            item.setProduto(produto);

            itens.add(item);
        }

        return itens;
    }

    public void delete(Long idVenda) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(false);

            itemDAO.excluirPorVenda(idVenda, con);

            PreparedStatement stmt = con.prepareStatement(SQL_DELETE_VENDA);
            stmt.setLong(1, idVenda);
            stmt.executeUpdate();

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }

    public void update(Venda venda) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(false);

            PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_VENDAS);
            stmt.setDate(1, venda.getDataVendaSQL());
            stmt.setLong(2, venda.getCliente().getId());
            stmt.setLong(3, venda.getIdVenda());
            stmt.executeUpdate();

            // Atualizar os itens: remover e adicionar novamente
            itemDAO.excluirPorVenda(venda.getIdVenda(), con);
            for (ItemVenda item : venda.getItens()) {
                item.setVenda(venda);
                itemDAO.create(item, con);
            }
            con.commit();

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }
}
