/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.CaixaController;
import controller.GerenteController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.bean.Caixa;
import model.bean.Produto;
import model.dao.CaixaDAO;
import model.dao.GerenteDAO;
import model.dao.impl.CaixaDAOImpl;
import model.dao.impl.GerenteDAOImpl;
import service.CaixaService;
import service.GerenteService;

/**
 *
 * @author onata
 */
public class TelaGerentePrincipal extends javax.swing.JFrame {
    
    GerenteDAO gerenteDAO = new GerenteDAOImpl();
    GerenteService gerenteService = new GerenteService(gerenteDAO);
    GerenteController gerenteController = new GerenteController(gerenteService);
    
    CaixaDAO caixaDAO = new CaixaDAOImpl();
    CaixaService caixaService = new CaixaService(caixaDAO);
    CaixaController caixaController = new CaixaController(caixaService);
    
    

    public TelaGerentePrincipal() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza
        readTable();
    }
    
    private void personalizarTabela() {
        tabelaFuncionarios.getTableHeader().setBackground(new java.awt.Color(164,113,72)); 
        tabelaFuncionarios.getTableHeader().setForeground(java.awt.Color.WHITE); // Letras brancas
        tabelaFuncionarios.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)); // Fonte
        
        JTableHeader header = tabelaFuncionarios.getTableHeader();
        header.setBackground(new Color(191, 131, 74));
        header.setForeground(Color.WHITE);             
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setOpaque(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        botaoCadFunc = new javax.swing.JButton();
        botaoCadProd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        botaoEstoque = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        botaoCadClientes = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Padaria Rezende's Gerente");
        setBounds(new java.awt.Rectangle(0, 0, 600, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(650, 400));

        jPanel1.setBackground(new java.awt.Color(245, 235, 221));
        jPanel1.setForeground(new java.awt.Color(245, 235, 221));

        jPanel2.setBackground(new java.awt.Color(164, 113, 72));
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/minilogo_padaria.png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(245, 235, 221));
        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(164, 87, 44));
        jButton4.setText("Sair");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 474, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        botaoCadFunc.setBackground(new java.awt.Color(164, 87, 44));
        botaoCadFunc.setForeground(new java.awt.Color(164, 113, 72));
        botaoCadFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/icone_func.png"))); // NOI18N
        botaoCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadFuncActionPerformed(evt);
            }
        });

        botaoCadProd.setBackground(new java.awt.Color(164, 87, 44));
        botaoCadProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/icons8-produto-50 (1).png"))); // NOI18N
        botaoCadProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadProdActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(164, 87, 44));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(164, 87, 44));
        jLabel2.setText("CAD Funcionários");

        botaoEstoque.setBackground(new java.awt.Color(164, 87, 44));
        botaoEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/icons8-boxes-64.png"))); // NOI18N
        botaoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstoqueActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(164, 87, 44));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(164, 87, 44));
        jLabel3.setText("Cadastrar Produto");

        jLabel4.setBackground(new java.awt.Color(164, 87, 44));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(164, 87, 44));
        jLabel4.setText("Estoque");

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Cargo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFuncionariosMouseClicked(evt);
            }
        });
        tabelaFuncionarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaFuncionariosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaFuncionariosKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tabelaFuncionarios);

        botaoCadClientes.setBackground(new java.awt.Color(164, 87, 44));
        botaoCadClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/icons8-cliente-50.png"))); // NOI18N
        botaoCadClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadClientesActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(164, 87, 44));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(164, 87, 44));
        jLabel5.setText("CAD Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(botaoCadFunc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(botaoCadClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel5)))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoCadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel3)))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoEstoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(40, 40, 40))))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCadClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadFuncActionPerformed
        TelaGerenteCadastrarFunc telaCadastrarFunc = new TelaGerenteCadastrarFunc();
        telaCadastrarFunc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoCadFuncActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void botaoCadProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadProdActionPerformed
        TelaGerenteCadastrarProd telaCadastrarProd = new TelaGerenteCadastrarProd();
        telaCadastrarProd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoCadProdActionPerformed

    private void botaoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstoqueActionPerformed
        TelaGerenteEstoque telaEstoque = new TelaGerenteEstoque();
        telaEstoque.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoEstoqueActionPerformed

    private void tabelaFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFuncionariosMouseClicked
       
    }//GEN-LAST:event_tabelaFuncionariosMouseClicked

    private void tabelaFuncionariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaFuncionariosKeyPressed
        
    }//GEN-LAST:event_tabelaFuncionariosKeyPressed

    private void tabelaFuncionariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaFuncionariosKeyReleased
       
    }//GEN-LAST:event_tabelaFuncionariosKeyReleased

    private void botaoCadClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadClientesActionPerformed
        TelaGerenteCADCliente telaCADClienteGerente = new TelaGerenteCADCliente();
        telaCADClienteGerente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoCadClientesActionPerformed

    public void readTable() {
    DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
    modelo.setNumRows(0);

    try {
        List<Caixa> caixas = caixaController.readCaixa();
        if (caixas != null) { // Importante verificar se não é nulo
            for (Caixa c : caixas) {
                modelo.addRow(new Object[]{
                    c.getNome(),
                    c.getTelefone(),
                    c.getCargo(),
 
                });
            }
        } else {
            System.out.println("ProdutoController.readProduto() retornou null.");
        }
    } catch (Exception e) {
        System.err.println("Erro ao carregar produtos na tabela: " + e.getMessage());
        e.printStackTrace();  
    }
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaGerentePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerentePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerentePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerentePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerentePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadClientes;
    private javax.swing.JButton botaoCadFunc;
    private javax.swing.JButton botaoCadProd;
    private javax.swing.JButton botaoEstoque;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration//GEN-END:variables
}
