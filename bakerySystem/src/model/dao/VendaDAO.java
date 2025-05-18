package model.dao;

import connection.ConnectionFactory;
import model.bean.Cliente;
import model.bean.ItemVenda;
import model.bean.Produto;
import model.bean.Venda;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Para RETURN_GENERATED_KEYS
import java.sql.Types;     // Para java.sql.Types.BIGINT ao setar nulo
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    // SQL para a tabela 'venda'
    private static final String SQL_INSERT_VENDA = "INSERT INTO venda (data_venda, id_cliente) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL_VENDAS = "SELECT id_venda, data_venda, id_cliente FROM venda";
    private static final String SQL_UPDATE_VENDA = "UPDATE venda SET data_venda = ?, id_cliente = ? WHERE id_venda = ?";
    private static final String SQL_DELETE_VENDA = "DELETE FROM venda WHERE id_venda = ?";
    private static final String SQL_FIND_VENDA_BY_ID = "SELECT id_venda, data_venda, id_cliente FROM venda WHERE id_venda = ?";

    // SQL para a tabela 'item_venda'
    private static final String SQL_INSERT_ITEM_VENDA = "INSERT INTO item_venda (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ITEMS_BY_VENDA_ID = "SELECT id_item_venda, id_produto, quantidade FROM item_venda WHERE id_venda = ?";
    private static final String SQL_DELETE_ITEMS_BY_VENDA_ID = "DELETE FROM item_venda WHERE id_venda = ?";

    // DAOs para entidades relacionadas
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;

    public VendaDAO() {
        this.clienteDAO = new ClienteDAO();
        this.produtoDAO = new ProdutoDAO();
    }

    /**
     * Cria uma nova venda no banco de dados, incluindo seus itens.
     *
     * @param venda O objeto Venda a ser persistido.
     * @return O objeto Venda com seu ID e IDs dos itens preenchidos, ou null em
     * caso de falha.
     */
    public Venda create(Venda venda) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmtVenda = null;
        ResultSet generatedKeysVenda = null;
        PreparedStatement stmtItemVenda = null;

        // Validação básica do objeto Venda
        if (venda == null) {
            JOptionPane.showMessageDialog(null, "Objeto Venda não pode ser nulo.");
            return null;
        }

        try {
            // Inserir Venda principal
            // Idealmente, usar transação explícita (connection.setAutoCommit(false))
            stmtVenda = connection.prepareStatement(SQL_INSERT_VENDA, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setDate(1, venda.getDataVendaSQL()); // Usa o método que retorna java.sql.Date

            if (venda.getCliente() != null && venda.getCliente().getId() != null) {
                stmtVenda.setLong(2, venda.getCliente().getId());
            } else {
                stmtVenda.setNull(2, Types.BIGINT); // Permite cliente nulo na venda
            }

            int affectedRowsVenda = stmtVenda.executeUpdate();

            if (affectedRowsVenda == 0) {
                JOptionPane.showMessageDialog(null, "Falha ao criar a venda! Nenhuma linha afetada na tabela 'venda'.");
                // connection.rollback(); // Se transacional
                return null;
            }

            generatedKeysVenda = stmtVenda.getGeneratedKeys();
            if (generatedKeysVenda.next()) {
                venda.setIdVenda(generatedKeysVenda.getLong(1));
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao obter o ID da venda após inserção!");
                // connection.rollback(); // Se transacional
                return null;
            }

            // Inserir Itens da Venda
            if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                stmtItemVenda = connection.prepareStatement(SQL_INSERT_ITEM_VENDA, Statement.RETURN_GENERATED_KEYS);
                for (ItemVenda item : venda.getItens()) {
                    item.setVenda(venda); // Garante que o item tem a referência correta da venda com ID

                    if (item.getProduto() == null || item.getProduto().getIdProduto() == null) {
                        JOptionPane.showMessageDialog(null, "Item da venda sem produto associado ou ID do produto ausente. Item não inserido.");
                        // connection.rollback(); // Falha crítica
                        // return null; // Aborta criação da venda
                        continue; // Pula este item problemático (decisão de design)
                    }

                    stmtItemVenda.setLong(1, venda.getIdVenda());
                    stmtItemVenda.setLong(2, item.getProduto().getIdProduto());
                    stmtItemVenda.setInt(3, item.getQuantidade());

                    int affectedRowsItem = stmtItemVenda.executeUpdate();
                    if (affectedRowsItem == 0) {
                        JOptionPane.showMessageDialog(null, "Falha ao inserir item da venda para o produto: " + item.getProduto().getNome() + ". A venda pode estar incompleta.");
                        // connection.rollback(); // Falha crítica, idealmente abortaria toda a venda
                        // Para simplificar, pode-se optar por continuar ou retornar erro.
                        // Se retornar null, a venda principal já foi inserida. Exigiria rollback.
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
            // connection.commit(); // Se transacional
            JOptionPane.showMessageDialog(null, "Venda criada com sucesso! ID: " + venda.getIdVenda());
            return venda;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a Venda: " + ex.getMessage());
            // try { if (connection != null && !connection.getAutoCommit()) connection.rollback(); } catch (SQLException e) { /* log */ }
            return null;
        } finally {
            // Fechar recursos na ordem inversa da abertura
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
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Carrega os itens de uma venda específica. Este é um método auxiliar para
     * ser usado por read() e findById().
     *
     * @param venda Objeto Venda para o qual os itens serão carregados (deve ter
     * ID).
     * @param connection Conexão JDBC a ser utilizada.
     * @return Lista de ItemVenda populada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    private List<ItemVenda> loadItensForVenda(Venda venda, Connection connection) throws SQLException {
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
                // ProdutoDAO.findById irá gerenciar sua própria conexão conforme o padrão do ProdutoDAO fornecido
                Produto produto = produtoDAO.findById(idProduto);

                if (produto == null || produto.getIdProduto() == null) {
                    System.err.println("AVISO: Produto com ID " + idProduto + " não encontrado para o item " + item.getIdItemVenda() + " da venda " + venda.getIdVenda() + ". Item não será adicionado com produto completo.");
                    // Define um produto placeholder ou nulo, dependendo da lógica de negócio
                    // item.setProduto(null); // Ou um produto com dados limitados
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

    /**
     * Retorna todas as vendas do banco de dados.
     *
     * @return Uma lista de objetos Venda.
     */
    public List<Venda> read() {
        List<Venda> vendas = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rsVenda = null;

        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL_VENDAS);
            rsVenda = stmt.executeQuery();

            while (rsVenda.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rsVenda.getLong("id_venda"));

                java.sql.Date sqlDate = rsVenda.getDate("data_venda");
                if (sqlDate != null) {
                    venda.setDataVenda(sqlDate.toLocalDate());
                }

                Long idCliente = rsVenda.getLong("id_cliente");
                if (!rsVenda.wasNull()) {
                    // ClienteDAO.findById irá gerenciar sua própria conexão
                    Cliente cliente = clienteDAO.findById(idCliente);
                    if (cliente == null || cliente.getId() == null) {
                        System.err.println("AVISO: Cliente com ID " + idCliente + " não encontrado para a venda " + venda.getIdVenda() + ".");
                        venda.setCliente(null); // Ou tratar como erro, dependendo dos requisitos
                    } else {
                        venda.setCliente(cliente);
                    }
                } else {
                    venda.setCliente(null); // Cliente é opcional na venda
                }

                // Carrega os itens para esta venda usando a mesma conexão principal
                List<ItemVenda> itens = loadItensForVenda(venda, connection);
                venda.setItens(itens);

                vendas.add(venda);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler Vendas: " + ex.getMessage());
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

    /**
     * Busca uma venda específica pelo seu ID.
     *
     * @param idVenda O ID da venda a ser buscada.
     * @return O objeto Venda encontrado, ou null se não existir.
     */
    public Venda findById(Long idVenda) {
        Venda venda = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rsVenda = null;

        if (idVenda == null || idVenda <= 0) {
            JOptionPane.showMessageDialog(null, "ID da Venda inválido para busca.");
            return null;
        }

        try {
            stmt = connection.prepareStatement(SQL_FIND_VENDA_BY_ID);
            stmt.setLong(1, idVenda);
            rsVenda = stmt.executeQuery();

            if (rsVenda.next()) {
                venda = new Venda();
                venda.setIdVenda(rsVenda.getLong("id_venda"));

                java.sql.Date sqlDate = rsVenda.getDate("data_venda");
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

                List<ItemVenda> itens = loadItensForVenda(venda, connection);
                venda.setItens(itens);
            } else {
                // JOptionPane.showMessageDialog(null, "Nenhuma venda encontrada com o ID: " + idVenda); // Opcional
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Venda por ID: " + ex.getMessage());
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

    /**
     * Atualiza uma venda existente no banco de dados. Os itens da venda são
     * substituídos pelos novos itens fornecidos.
     *
     * @param venda O objeto Venda com os dados atualizados.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    public boolean update(Venda venda) {
        if (venda == null || venda.getIdVenda() == null || venda.getIdVenda() <= 0L) {
            JOptionPane.showMessageDialog(null, "Venda para atualização é nula ou não tem um ID válido!");
            return false;
        }

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmtUpdateVenda = null;
        PreparedStatement stmtDeleteItens = null;
        PreparedStatement stmtInsertItem = null;

        try {
            // Idealmente, usar transação explícita (connection.setAutoCommit(false))

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
            // affectedRowsVenda pode ser 0 se os dados forem os mesmos ou se o ID não existir.
            // Considerar se "nenhuma linha afetada" é um erro ou não.
            // if (affectedRowsVenda == 0) {
            //     JOptionPane.showMessageDialog(null, "Nenhuma venda foi encontrada com o ID " + venda.getIdVenda() + " para atualizar, ou os dados são os mesmos.");
            //     // connection.rollback(); // Se transacional e nada foi alterado/encontrado
            //     // return false; // Depende da lógica desejada
            // }

            // 2. Remover ItensVenda antigos associados a esta Venda
            stmtDeleteItens = connection.prepareStatement(SQL_DELETE_ITEMS_BY_VENDA_ID);
            stmtDeleteItens.setLong(1, venda.getIdVenda());
            stmtDeleteItens.executeUpdate();

            // 3. Inserir os novos ItensVenda
            if (venda.getItens() != null && !venda.getItens().isEmpty()) {
                stmtInsertItem = connection.prepareStatement(SQL_INSERT_ITEM_VENDA, Statement.RETURN_GENERATED_KEYS);
                for (ItemVenda item : venda.getItens()) {
                    item.setVenda(venda); // Garante referência correta

                    if (item.getProduto() == null || item.getProduto().getIdProduto() == null) {
                        JOptionPane.showMessageDialog(null, "Item da venda (durante atualização) sem produto associado ou ID do produto ausente. Item não (re)inserido.");
                        // connection.rollback(); // Falha crítica
                        // return false;
                        continue;
                    }

                    stmtInsertItem.setLong(1, venda.getIdVenda());
                    stmtInsertItem.setLong(2, item.getProduto().getIdProduto());
                    stmtInsertItem.setInt(3, item.getQuantidade());

                    int affectedRowsItem = stmtInsertItem.executeUpdate();
                    if (affectedRowsItem == 0) {
                        JOptionPane.showMessageDialog(null, "Falha ao (re)inserir item da venda: " + item.getProduto().getNome() + ". Atualização pode estar incompleta.");
                        // connection.rollback(); // Falha crítica
                        return false;
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
            // if (!connection.getAutoCommit()) connection.commit();
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a Venda: " + ex.getMessage());
            // try { if (connection != null && !connection.getAutoCommit()) connection.rollback(); } catch (SQLException e) { /* log */ }
            return false;
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
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Exclui uma venda e seus itens associados do banco de dados.
     *
     * @param idVenda O ID da venda a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long idVenda) {
        if (idVenda == null || idVenda <= 0) {
            // Seguindo ProdutoDAO que lança IllegalArgumentException
            throw new IllegalArgumentException("O ID da Venda para exclusão é inválido: " + idVenda);
        }

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmtDeleteItens = null;
        PreparedStatement stmtDeleteVenda = null;

        try {
            // Idealmente, usar transação explícita (connection.setAutoCommit(false))

            // 1. Excluir ItensVenda associados
            // (Se o BD tiver ON DELETE CASCADE configurado na FK, este passo pode ser redundante, mas é mais seguro explicitamente)
            stmtDeleteItens = connection.prepareStatement(SQL_DELETE_ITEMS_BY_VENDA_ID);
            stmtDeleteItens.setLong(1, idVenda);
            stmtDeleteItens.executeUpdate(); // Não verificamos affectedRows aqui, pois pode não haver itens.

            // 2. Excluir a Venda principal
            stmtDeleteVenda = connection.prepareStatement(SQL_DELETE_VENDA);
            stmtDeleteVenda.setLong(1, idVenda);
            int affectedRowsVenda = stmtDeleteVenda.executeUpdate();

            if (affectedRowsVenda > 0) {
                // if (!connection.getAutoCommit()) connection.commit();
                JOptionPane.showMessageDialog(null, "Venda ID " + idVenda + " excluída com sucesso!");
                return true;
            } else {
                // if (!connection.getAutoCommit()) connection.rollback(); // Nada foi excluído, talvez rollback
                JOptionPane.showMessageDialog(null, "Nenhuma venda com o ID " + idVenda + " foi encontrada para exclusão.");
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a Venda ID " + idVenda + ": " + ex.getMessage());
            // try { if (connection != null && !connection.getAutoCommit()) connection.rollback(); } catch (SQLException e) { /* log */ }
            return false;
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
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }
}
