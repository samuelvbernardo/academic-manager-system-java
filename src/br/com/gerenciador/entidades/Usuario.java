package br.com.gerenciador.entidades;

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String cpf;
    protected String login;
    protected String senha;
    protected String perfil;

    public Usuario(){

    }

    public Usuario(int id, String nome, String cpf, String login, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public abstract void mostraDados();

    @Override
    public String toString() {
        return id +"|"+ nome +"|"+cpf +"|"+login +"|"+ senha +"|"+ perfil;
    }
}


