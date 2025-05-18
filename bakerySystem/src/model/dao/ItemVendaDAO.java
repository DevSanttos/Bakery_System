package model.dao;


import model.bean.ItemVenda;
import model.bean.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemVendaDAO {

    public void create(ItemVenda item, Connection con) throws SQLException {
        String sql = "INSERT INTO ItemVenda (id_venda, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, item.getVenda().getIdVenda());
        stmt.setLong(2, item.getProduto().getIdProduto());
        stmt.setInt(3, item.getQuantidade());
        stmt.setDouble(4, item.getPrecoUnitario());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirPorVenda(Long idVenda, Connection con) throws SQLException {
        String sql = "DELETE FROM ItemVenda WHERE id_venda = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, idVenda);
        stmt.executeUpdate();
        stmt.close();
    }
}
