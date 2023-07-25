public abstract class Efeito{

    public  Efeito(int duracao,Personagem personagem, String nome){
        this.duracao = duracao;
        this.personagem = personagem;
        this.nome = nome;
    }
    private final String nome;
    private int duracao;
    private Personagem personagem;
    private String descricao;

    public abstract void efetuar();

    //region getters e setters
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }
    //endregion
    @Override
    public String toString() {
        return   descricao +
                "\nDuracao: " + duracao;
    }
}
