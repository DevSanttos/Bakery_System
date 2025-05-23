package model.dao.impl;

import connection.ConnectionFactory;
import model.bean.Cliente;
import model.bean.ItemVenda;
import model.bean.Produto;
import model.bean.Venda;
// import javax.swing.JOptionPane; // Removido
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.dao.VendaDAO;
import model.dao.ClienteDAO; // Importar a interface para melhor prática
import model.dao.ProdutoDAO; // Importar a interface para melhor prática

public class VendaDAOImpl implements VendaDAO {

    // SQL para a tabela 'venda'
    private static final String SQL_INSERT_VENDA = "INSERT INTO venda (data_venda, id_cliente) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL_VENDAS = "SELECT id_venda, data_venda, id_cliente FROM venda";
    private static final String SQL_UPDATE_VENDA = "UPDATE venda SET data_venda = ?, id_cliente = ? WHERE id_venda = ?";
    private static final String SQL_DELETE_VENDA = "DELETE FROM venda WHERE id_venda = ?";
    private static final String SQL_FIND_VENDA_BY_ID = "SELECT id_venda, data_venda, id_cliente FROM venda WHERE venda.id_venda = ?";

    // SQL para a tabela 'item_venda'
    private static final String SQL_INSERT_ITEM_VENDA = "INSERT INTO itemvenda (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ITEMS_BY_VENDA_ID = "SELECT id_item_venda, id_produto, quantidade FROM itemvenda WHERE itemvenda.id_venda = ?";
    private static final String SQL_DELETE_ITEMS_BY_VENDA_ID = "DELETE FROM itemvenda WHERE itemvenda.id_venda = ?";

    // DAOs para entidades relacionadas (usando interfaces para melhor prática)
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;

    public VendaDAOImpl() {
        this.clienteDAO = new ClienteDAOImpl();
        this.produtoDAO = new ProdutoDAOImpl();
    }

    @Override
    public Venda create(Venda venda) {
        Connection connection = null;
        PreparedStatement stmtVenda = null;
        ResultSet generatedKeysVenda = null;
        PreparedStatement stmtItemVenda = null;

        // Validação básica do objeto Venda
        if (venda == null) {
            throw new IllegalArgumentException("Objeto Venda não pode ser nulo.");
        }

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false); // Inicia a transação

            // Inserir Venda principal
            stmtVenda = connection.prepareStatement(SQL_INSERT_VENDA, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setDate(1, venda.getDataVendaSQL()); // Usa o método que retorna java.sql.Date

            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                stmtVenda.setLong(2, venda.getCliente().getId());
            } else {
                stmtVenda.setNull(2, Types.BIGINT); // Permite cliente nulo na venda
            }

            int affectedRowsVenda = stmtVenda.executeUpdate();

            if (affectedRowsVenda == 0) {
                throw new SQLException("Falha ao criar a venda! Nenhuma linha afetada na tabela 'venda'.");
            }

            generatedKeysVenda = stmtVenda.getGeneratedKeys();
            if (generatedKeysVenda.next()) {
                venda.setIdVenda(generatedKeysVenda.getLong(1));
            } else {
                throw new SQLException("Falha ao obter o ID da venda após inserção!");
            }

            // Inserir Itens da Venda
            if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                stmtItemVenda = connection.prepareStatement(SQL_INSERT_ITEM_VENDA, Statement.RETURN_GENERATED_KEYS);
                for (ItemVenda item : venda.getItens()) {
                    item.setVenda(venda); // Garante que o item tem a referência correta da venda com ID

                    if (item.getProduto() == null || item.getProduto().getIdProduto() == null) {
                        throw new IllegalArgumentException("Item da venda sem produto associado ou ID do produto ausente. Item não pode ser inserido.");
                    }

                    stmtItemVenda.setLong(1, venda.getIdVenda());
                    stmtItemVenda.setLong(2, item.getProduto().getIdProduto());
                    stmtItemVenda.setInt(3, item.getQuantidade());

                    int affectedRowsItem = stmtItemVenda.executeUpdate();
                    if (affectedRowsItem == 0) {
                        throw new SQLException("Falha ao inserir item da venda para o produto: " + item.getProduto().getNome() + ". A venda pode estar incompleta.");
                    } else {
                        ResultSet generatedKeysItem = stmtItemVenda.getGeneratedKeys();
                        try {
                            if (generatedKeysItem.next()) {
                                item.setIdItemVenda(generatedKeysItem.getLong(1));
                            } else {
                                System.err.println("Falha ao obter o ID do item da venda para o produto: " + item.getProduto().getNome());
                            }
                        } finally {
                            if (generatedKeysItem != null) {
                                generatedKeysItem.close();
                            }
                        }
                    }
                }
            }
            connection.commit(); // Confirma a transação
            return venda;

        } catch (SQLException | IllegalArgumentException ex) {
            if (connection != null) {
                try {
                    connection.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException rollbackEx) {
                    System.err.println("Erro durante o rollback da transação: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Erro ao cadastrar a Venda: " + ex.getMessage(), ex);
        } finally {
            if (generatedKeysVenda != null) {
                try {
                    generatedKeysVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet generatedKeysVenda: " + e.getMessage());
                }
            }
            if (stmtItemVenda != null) {
                try {
                    stmtItemVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar PreparedStatement stmtItemVenda: " + e.getMessage());
                }
            }
            if (stmtVenda != null) {
                try {
                    stmtVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar PreparedStatement stmtVenda: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restaura o auto-commit
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<ItemVenda> loadItensForVenda(Venda venda) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        List<ItemVenda> itens = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL_SELECT_ITEMS_BY_VENDA_ID);
            stmt.setLong(1, venda.getIdVenda());
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                item.setIdItemVenda(rs.getLong("id_item_venda"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setVenda(venda); // Associa o item à venda principal

                Long idProduto = rs.getLong("id_produto");
                // ProdutoDAO.findById gerenciará sua própria conexão conforme o padrão do ProdutoDAO
                Produto produto = produtoDAO.findById(idProduto);

                if (produto == null || produto.getIdProduto() == null) {
                    System.err.println("AVISO: Produto com ID " + idProduto + " não encontrado para o item " + item.getIdItemVenda() + " da venda " + venda.getIdVenda() + ". Item não será adicionado com produto completo.");
                    // Define um produto placeholder ou nulo, dependendo da lógica de negócio
                } else {
                    item.setProduto(produto);
                }
                itens.add(item);
            }
        } finally {
            // ResultSet e PreparedStatement são locais para este método auxiliar e devem ser fechados aqui.
            // A conexão é gerenciada pelo método chamador (read, findById).
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet em loadItensForVenda: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar PreparedStatement em loadItensForVenda: " + e.getMessage());
                }
            }
        }
        return itens;
    }

    @Override
    public List<Venda> read() {
        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rsVenda = null;

        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.prepareStatement(SQL_SELECT_ALL_VENDAS);
            rsVenda = stmt.executeQuery();

            while (rsVenda.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rsVenda.getLong("id_venda"));

                Date sqlDate = rsVenda.getDate("data_venda");
                if (sqlDate != null) {
                    venda.setDataVenda(sqlDate.toLocalDate());
                }

                Long idCliente = rsVenda.getLong("id_cliente");
                if (!rsVenda.wasNull()) {
                    // ClienteDAO.findById gerenciará sua própria conexão
                    Cliente cliente = clienteDAO.findById(idCliente);
                    if (cliente == null || cliente.getId() == null) {
                        //System.err.println("AVISO: Cliente com ID " + idCliente + " não encontrado para a venda " + venda.getIdVenda() + ".");
                        venda.setCliente(null); // Ou tratar como erro, dependendo dos requisitos
                    } else {
                        venda.setCliente(cliente);
                    }
                } else {
                    venda.setCliente(null); // Cliente é opcional na venda
                }

                // Carrega os itens para esta venda usando a mesma conexão principal
                List<ItemVenda> itens = loadItensForVenda(venda);
                venda.setItens(itens);

                vendas.add(venda);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao ler Vendas: " + ex.getMessage(), ex);
        } finally {
            if (rsVenda != null) {
                try {
                    rsVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
        return vendas;
    }

    @Override
    public Venda findById(Long idVenda) {
        Venda venda = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rsVenda = null;

        if (idVenda == null || idVenda <= 0) {
            throw new IllegalArgumentException("ID da Venda inválido para busca: " + idVenda);
        }

        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.prepareStatement(SQL_FIND_VENDA_BY_ID);
            stmt.setLong(1, idVenda);
            rsVenda = stmt.executeQuery();

            if (rsVenda.next()) {
                venda = new Venda();
                venda.setIdVenda(rsVenda.getLong("id_venda"));

                Date sqlDate = rsVenda.getDate("data_venda");
                if (sqlDate != null) {
                    venda.setDataVenda(sqlDate.toLocalDate());
                }

                Long idCliente = rsVenda.getLong("id_cliente");
                if (!rsVenda.wasNull()) {
                    Cliente cliente = clienteDAO.findById(idCliente);
                    if (cliente == null || cliente.getId() == null) {
                        System.err.println("AVISO: Cliente com ID " + idCliente + " não encontrado para a venda " + venda.getIdVenda() + ".");
                        venda.setCliente(null);
                    } else {
                        venda.setCliente(cliente);
                    }
                } else {
                    venda.setCliente(null);
                }

                List<ItemVenda> itens = loadItensForVenda(venda);
                venda.setItens(itens);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar Venda por ID " + idVenda + ": " + ex.getMessage(), ex);
        } finally {
            if (rsVenda != null) {
                try {
                    rsVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
        return venda;
    }

    @Override
    public boolean update(Venda venda) {
        if (venda == null || venda.getIdVenda() == null || venda.getIdVenda() <= 0L) {
            throw new IllegalArgumentException("Venda para atualização é nula ou não tem um ID válido.");
        }

        Connection connection = null;
        PreparedStatement stmtUpdateVenda = null;
        PreparedStatement stmtDeleteItens = null;
        PreparedStatement stmtInsertItem = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false); // Inicia a transação

            // 1. Atualizar dados da Venda principal
            stmtUpdateVenda = connection.prepareStatement(SQL_UPDATE_VENDA);
            stmtUpdateVenda.setDate(1, venda.getDataVendaSQL());
            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                stmtUpdateVenda.setLong(2, venda.getCliente().getId());
            } else {
                stmtUpdateVenda.setNull(2, Types.BIGINT);
            }
            stmtUpdateVenda.setLong(3, venda.getIdVenda());

            int affectedRowsVenda = stmtUpdateVenda.executeUpdate();
            if (affectedRowsVenda == 0) {
                // Se a venda não foi encontrada, pode ser um erro ou o ID não existe
                throw new SQLException("Nenhuma venda foi encontrada com o ID " + venda.getIdVenda() + " para atualizar, ou os dados são os mesmos.");
            }

            // 2. Remover ItensVenda antigos associados a esta Venda
            stmtDeleteItens = connection.prepareStatement(SQL_DELETE_ITEMS_BY_VENDA_ID);
            stmtDeleteItens.setLong(1, venda.getIdVenda());
            stmtDeleteItens.executeUpdate(); // Não precisa verificar affectedRows, pois pode não haver itens anteriores

            // 3. Inserir os novos ItensVenda
            if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                stmtInsertItem = connection.prepareStatement(SQL_INSERT_ITEM_VENDA, Statement.RETURN_GENERATED_KEYS);
                for (ItemVenda item : venda.getItens()) {
                    item.setVenda(venda); // Garante referência correta

                    if (item.getProduto() == null || item.getProduto().getIdProduto() == null) {
                        throw new IllegalArgumentException("Item da venda (durante atualização) sem produto associado ou ID do produto ausente. Item não pode ser (re)inserido.");
                    }

                    stmtInsertItem.setLong(1, venda.getIdVenda());
                    stmtInsertItem.setLong(2, item.getProduto().getIdProduto());
                    stmtInsertItem.setInt(3, item.getQuantidade());

                    int affectedRowsItem = stmtInsertItem.executeUpdate();
                    if (affectedRowsItem == 0) {
                        throw new SQLException("Falha ao (re)inserir item da venda: " + item.getProduto().getNome() + ". Atualização pode estar incompleta.");
                    }
                    ResultSet generatedKeysItem = stmtInsertItem.getGeneratedKeys();
                    try {
                        if (generatedKeysItem.next()) {
                            item.setIdItemVenda(generatedKeysItem.getLong(1));
                        } else {
                            System.err.println("Falha ao obter o ID do item da venda (re-inserido) para o produto: " + item.getProduto().getNome());
                        }
                    } finally {
                        if (generatedKeysItem != null) {
                            generatedKeysItem.close();
                        }
                    }
                }
            }

            connection.commit(); // Confirma a transação
            return true;

        } catch (SQLException | IllegalArgumentException ex) {
            if (connection != null) {
                try {
                    connection.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException rollbackEx) {
                    System.err.println("Erro durante o rollback da transação na atualização da venda: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Erro ao atualizar a Venda: " + ex.getMessage(), ex);
        } finally {
            if (stmtInsertItem != null) {
                try {
                    stmtInsertItem.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtInsertItem: " + e.getMessage());
                }
            }
            if (stmtDeleteItens != null) {
                try {
                    stmtDeleteItens.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtDeleteItens: " + e.getMessage());
                }
            }
            if (stmtUpdateVenda != null) {
                try {
                    stmtUpdateVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtUpdateVenda: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restaura o auto-commit
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public boolean delete(Long idVenda) {
        if (idVenda == null || idVenda <= 0) {
            throw new IllegalArgumentException("O ID da Venda para exclusão é inválido: " + idVenda);
        }

        Connection connection = null;
        PreparedStatement stmtDeleteItens = null;
        PreparedStatement stmtDeleteVenda = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false); // Inicia a transação

            // 1. Excluir ItensVenda associados
            stmtDeleteItens = connection.prepareStatement(SQL_DELETE_ITEMS_BY_VENDA_ID);
            stmtDeleteItens.setLong(1, idVenda);
            stmtDeleteItens.executeUpdate(); // Não precisa verificar affectedRows, pois pode não haver itens

            // 2. Excluir a Venda principal
            stmtDeleteVenda = connection.prepareStatement(SQL_DELETE_VENDA);
            stmtDeleteVenda.setLong(1, idVenda);
            int affectedRowsVenda = stmtDeleteVenda.executeUpdate();

            if (affectedRowsVenda > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                throw new SQLException("Nenhuma venda com o ID " + idVenda + " foi encontrada para exclusão.");
            }

        } catch (SQLException | IllegalArgumentException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Erro durante o rollback da transação na exclusão da venda: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Erro ao excluir a Venda ID " + idVenda + ": " + ex.getMessage(), ex);
        } finally {
            if (stmtDeleteItens != null) {
                try {
                    stmtDeleteItens.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtDeleteItens: " + e.getMessage());
                }
            }
            if (stmtDeleteVenda != null) {
                try {
                    stmtDeleteVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtDeleteVenda: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }
}