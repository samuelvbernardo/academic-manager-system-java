package br.com.gerenciador.telas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.gerenciador.dal.GerenciadorDeArquivo;

import br.com.gerenciador.entidades.Usuario;

import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {


    public TelaUsuario() {
        initComponents();

    }

    private void consultar() {
        String id = txtUsuId.getText();
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o ID do usuário");
            return;
        }

        // a linha abaixo ler as linhas do arquivo
        List<String> linhas = GerenciadorDeArquivo.lerArquivo(caminhoArquivo);
        if (linhas != null) {
            boolean usuarioEncontrado = false;

            // a linha abaixo percorre as linhas do arquivo
            for (String linha : linhas) {
                String[] dados = linha.split("\\|");

                // a linha abaixo verifica se o ID corresponde ao usuário
                if (dados[0].equals(id)) {
                    // as linhas abaixo preenchem os campos com os dados do usuário encontrado
                    txtUsuNome.setText(dados[1]);
                    txtUsuCpf.setText(dados[2]);
                    txtUsuLogin.setText(dados[3]);
                    txtUsuSenha.setText(dados[4]);
                    cboUsuPerfil.setSelectedItem(dados[5]);
                    usuarioEncontrado = true;
                    break;
                }
            }

            // Se o usuário não for encontrado
            if (!usuarioEncontrado) {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // as linhas abaixo "limpam" os campos
                txtUsuNome.setText(null);
                txtUsuCpf.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de usuários");
        }
    }


    // metodo para adicionar usuarios
    private void adicionar() {
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        // validação dos campos obrigatórios
        if (txtUsuId.getText().isEmpty() || txtUsuNome.getText().isEmpty() ||
                txtUsuLogin.getText().isEmpty() || txtUsuSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            return;
        }

        // instanciado um objeto do tipo Usuario e "pegando" os dados dos campos digitados
        Usuario novoUsuario = new Usuario(
                Integer.parseInt(txtUsuId.getText().trim()),  // Conversão para inteiro
                txtUsuNome.getText().trim(),
                txtUsuCpf.getText().trim(),
                txtUsuLogin.getText().trim(),
                txtUsuSenha.getText().trim(),
                cboUsuPerfil.getSelectedItem().toString().trim()
        ) {
            @Override
            public void mostraDados() {

            }
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            // a linha abaixo adiciona um novo usuário no arquivo
            GerenciadorDeArquivo arquivo = new GerenciadorDeArquivo();
            arquivo.adicionarUsuario(caminhoArquivo, novoUsuario.toString());

            JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");

            // as linhas abaixo "limpam" os campos depois que o usuario for adicionado
            txtUsuId.setText(null);
            txtUsuNome.setText(null);
            txtUsuCpf.setText(null);
            txtUsuLogin.setText(null);
            txtUsuSenha.setText(null);
            cboUsuPerfil.setSelectedItem(null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o arquivo: " + e.getMessage());
        }
    }

    private void alterar() {
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        String nome = txtUsuNome.getText();
        String cpf = txtUsuCpf.getText();
        String login = txtUsuLogin.getText();
        String senha = txtUsuSenha.getText();
        String perfil = cboUsuPerfil.getSelectedItem().toString();
        String id = txtUsuId.getText();

        // a linha abaixo verifica se algum campo obrigatório está vazio
        if (id.isEmpty() || nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            return;
        }

        List<String> linhas = GerenciadorDeArquivo.lerArquivo(caminhoArquivo);
        if (linhas != null) {
            boolean usuarioAlterado = false;
            StringBuilder arquivoModificado = new StringBuilder();

            for (String linha : linhas) {
                String[] dados = linha.split("\\|");

                // a linha abaixo verifica se o ID corresponde ao usuário a ser alterado
                if (dados[0].equals(id)) {
                    // as linhas abaixo altera os dados do usuário
                    dados[1] = nome;  // Nome
                    dados[2] = cpf;   // CPF
                    dados[3] = login; // Login
                    dados[4] = senha; // Senha
                    dados[5] = perfil; // Perfil

                    // a linha abaixo cria a linha alterada e adiciona ao conteúdo
                    String linhaAlterada = String.join("|", dados);
                    arquivoModificado.append(linhaAlterada).append("\n");

                    usuarioAlterado = true;
                } else {
                    // se não for o usuário a ser alterado, apenas adicionar a linha original
                    arquivoModificado.append(linha).append("\n");
                }
            }

            // se o usuário foi alterado, escrever as modificações de volta no arquivo
            if (usuarioAlterado) {
                GerenciadorDeArquivo.escreverNoArquivo(caminhoArquivo, arquivoModificado.toString());
                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                // as linhas abaixo "limpam" os campos depois que os dados forem modificados
                txtUsuId.setText(null);
                txtUsuNome.setText(null);
                txtUsuCpf.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de usuários");
        }
    }
    // metodo responsavel por remover usuarios do arquivo
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";
            String id = txtUsuId.getText();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o ID do usuário");
                return;
            }

            List<String> linhas = GerenciadorDeArquivo.lerArquivo(caminhoArquivo);
            if (linhas != null) {
                boolean usuarioRemovido = false;
                StringBuilder arquivoModificado = new StringBuilder();

                for (String linha : linhas) {
                    String[] dados = linha.split("\\|");

                    // a linha abaixo erifica se o ID não corresponde ao usuário a ser removido
                    if (!dados[0].equals(id)) {
                        arquivoModificado.append(linha).append("\n");
                    } else {
                        usuarioRemovido = true;
                    }
                }

                // se o usuário foi removido, o arquivo sera atualizado
                if (usuarioRemovido) {
                    GerenciadorDeArquivo.escreverNoArquivo(caminhoArquivo, arquivoModificado.toString());
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    // as linhas abaixo "limpam" os campos depois que o usuario for removido
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de usuários");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtUsuCpf = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(616, 0));

        jLabel1.setText("*ID");

        jLabel2.setText("*Nome");

        jLabel3.setText("*Login");

        jLabel4.setText("*Senha");

        jLabel5.setText("*Perfil");

        txtUsuId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIdActionPerformed(evt);
            }
        });

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });

        txtUsuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuLoginActionPerformed(evt);
            }
        });

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "professor", "aluno" }));

        jLabel6.setText("CPF");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerenciador/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerenciador/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerenciador/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerenciador/icones/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("*Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(55, 55, 55)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel3))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel1)
                                                                                .addGap(16, 16, 16))))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel2)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(37, 37, 37))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(50, 50, 50)))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(39, 39, 39)
                                                                                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel6)
                                                                                        .addComponent(jLabel5))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(cboUsuPerfil, 0, 176, Short.MAX_VALUE)
                                                                                        .addComponent(txtUsuCpf)))))
                                                        .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel7)
                                                                .addGap(54, 54, 54))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(82, 82, 82)
                                                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(52, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsuDelete, btnUsuRead, btnUsuUpdate});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel7))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtUsuCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(70, Short.MAX_VALUE))
        );

        setBounds(0, 0, 616, 449);
    }// </editor-fold>

    private void txtUsuIdActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtUsuLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {
        // chamando o metodo consultar
        consultar();
    }

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {
        // chamando o metodo adicionar
        adicionar();
    }

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        // chamando o metodo alterar
        alterar();
    }

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        // chamando o metodo remover
        remover();
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUsuCpf;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration
}
