public class Sangramento extends Efeito{

    private int fatorSangramento;

    public Sangramento( Personagem personagem) {
        super(2, personagem, "Sangramento");
        this.setDescricao("""
                        Sangramento
                Você não acha que o nome é
                autoexplicativo?
                """);
        this.fatorSangramento = 1;
    }

    @Override
    public void efetuar() {
        Personagem personagem = this.getPersonagem();
        personagem.setVida(personagem.getVida() - this.fatorSangramento);
        if(personagem.getVida()<=0){
            this.setPersonagem(null);
            Jogador jogador = this.getPersonagem().getJogador();
            jogador.setTotalRecursos(jogador.getTotalRecursos() + 1);
        }
        this.fatorSangramento++;
    }
}
