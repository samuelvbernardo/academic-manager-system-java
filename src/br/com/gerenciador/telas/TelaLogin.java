package br.com.gerenciador.telas;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    private final String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

    public void logar() {
        // Lógica para pesquisar no arquivo de usuários
        String usuarioDigitado = txtUsuario.getText();
        String senhaDigitada = new String(txtSenha.getPassword());

        try {
            // Abrir o arquivo
            BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;
            boolean usuarioEncontrado = false;

            // Ler o arquivo linha por linha
            while ((linha = br.readLine()) != null) {
                // Dividir a linha usando o delimitador '|'
                String[] dados = linha.split("\\|");
                String nome = dados[1];  // Nome do usuário na posição 2
                String login = dados[3];  // Login na posição 4
                String senha = dados[4];  // Senha na posição 5
                String perfil = dados[5]; // Perfil na posição 6

                // Verificar se o usuário e a senha são válidos
                if (login.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
                    usuarioEncontrado = true;

                    // Verificar o perfil e redirecionar conforme o tipo de usuário
                    if (perfil.equals("admin")) {
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.menRel.setEnabled(true);
                        TelaPrincipal.menCadUsu.setEnabled(true);
                        TelaPrincipal.lblUsuario.setText(nome); // Exibe o nome
                        TelaPrincipal.lblUsuario.setForeground(Color.red);
                        this.dispose();
                    } else if (perfil.equals("professor")) {
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.menRel.setEnabled(true);
                        TelaPrincipal.lblUsuario.setText(nome); // Exibe o nome
                        this.dispose();
                    } else {
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.lblUsuario.setText(nome); // Exibe o nome
                        this.dispose();
                    }
                    break;
                }
            }

            // Caso não encontre o usuário
            if (!usuarioEncontrado) {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
            }

            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo de usuários");
        }
    }

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        File arquivo = new File("C:/Users/Admin/Downloads/GerenciadorAcademico.txt");
        if (arquivo.exists() && !arquivo.isDirectory()) {
            // Se o arquivo de usuários existir, mudar o texto de status
            lblStatus.setText("Arquivo de usuários encontrado");
            lblStatus.setForeground(Color.GREEN);  // Cor verde
        } else {
            // Se o arquivo não existir, mostrar mensagem de erro
            lblStatus.setText("Arquivo de usuários não encontrado");
            lblStatus.setForeground(Color.RED);  // Cor vermelha
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setText("Usuário");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel3.setText("Senha");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStatus.setText("");  // Texto inicial, será alterado conforme o arquivo é encontrado

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLogin))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(325, 325, 325)))
                                .addGap(50, 50, 50))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(34, 34, 34)
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addComponent(jLabel2))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnLogin)
                                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // chamando o método logar
        logar();
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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration
}
