package br.com.gerenciador.servicos;

import br.com.gerenciador.entidades.Aluno;
import br.com.gerenciador.entidades.Professor;
import br.com.gerenciador.entidades.Usuario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Sistema {
    ArrayList<Professor> professores = new ArrayList<>();
    ArrayList<Aluno> alunos = new ArrayList<>();

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario instanceof Aluno) {
            alunos.add((Aluno) usuario);
        } else if (usuario instanceof Professor) {
            professores.add((Professor) usuario);
        }
        System.out.println(usuario.getClass().getSimpleName() + " cadastrado com sucesso!");
    }


    public void visuCadastroAlunos(){
        System.out.println("ALUNOS CADASTRADOS");
        for (Aluno al : alunos){
            al.mostraDados();
        }
    }

    public void visuCadastroProfessores(){
        System.out.println("PROFESSORES CADASTRADOS");
        for (Professor pf : professores){
            pf.mostraDados();
        }
    }

    public void enviarMensagem(int idRemetente, int idDestinatario, String conteudo, boolean isAluno) {
        // Verificar se o remetente é um aluno ou professor
        if (isAluno) {
            Aluno aluno = null;
            for (Aluno a : alunos) {
                if (a.getId() == idRemetente) {
                    aluno = a;
                    break;
                }
            }

            Professor professor = null;
            for (Professor p : professores) {
                if (p.getId() == idDestinatario) {
                    professor = p;
                    break;
                }
            }

            if (aluno != null && professor != null) {
                // Verificar horário e login do professor
                LocalTime horaAtual = LocalTime.now();
                if (horaAtual.isAfter(professor.getHorarioDisponivel().getHoraInicio()) &&
                        horaAtual.isBefore(professor.getHorarioDisponivel().getHoraFim())) {
                    if (professor.isLogado()) {
                        // Criando a mensagem
                        LocalDateTime agora = LocalDateTime.now();
                        Mensagem mensagem = new Mensagem(conteudo, agora, professor);

                        // Adicionando a mensagem ao professor e aluno
                        professor.getMensagensRecebidas().add(mensagem);
                        aluno.getMensagensEnviadas().add(mensagem);
                        System.out.println("Mensagem enviada com sucesso de " + aluno.getNome() + " para " + professor.getNome());
                    } else {
                        // Armazenando a mensagem caso o professor não esteja logado
                        Mensagem mensagem = new Mensagem(conteudo, LocalDateTime.now(), professor);
                        professor.getMensagensNaoLidas().add(mensagem);
                        System.out.println("Mensagem guardada para o professor " + professor.getNome() + " receber quando logar.");
                    }
                } else {
                    System.out.println("Mensagem não pode ser enviada fora do horário de disponibilidade do professor.");
                }
            } else {
                System.out.println("Aluno ou professor não encontrado.");
            }
        } else {
            // Lógica para o envio de mensagens de um professor para um aluno
            Professor professor = null;
            for (Professor p : professores) {
                if (p.getId() == idRemetente) {
                    professor = p;
                    break;
                }
            }

            Aluno aluno = null;
            for (Aluno a : alunos) {
                if (a.getId() == idDestinatario) {
                    aluno = a;
                    break;
                }
            }

            if (professor != null && aluno != null) {
                // Criando a mensagem
                LocalDateTime agora = LocalDateTime.now();
                Mensagem mensagem = new Mensagem(conteudo, agora, aluno);

                // Adicionando a mensagem ao aluno e professor
                aluno.getMensagensRecebidas().add(mensagem);
                professor.getMensagensEnviadas().add(mensagem);
                System.out.println("Mensagem enviada com sucesso de " + professor.getNome() + " para " + aluno.getNome());
            } else {
                System.out.println("Professor ou aluno não encontrado.");
            }
        }
    }

}
