public class Guerreiro extends Humano{

    public Guerreiro(Jogador jogador){
        super("Guerreiro",0,2,5,0, jogador);
        this.setDescricao("""
                            Guerreiro
                O guerreiro é a tropa inicial
                fundamental de qualoquer exército,
                de vez em quando aparece um querendo
                se juntar à batalha.
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {
        System.out.println("entrei ataque");
        System.out.println("y " + this.getY());
        System.out.println("x " + this.getY());
        if (this.getY() == 1 || this.getY() == 2) {
            System.out.println("entrei if");
            Personagem inimigo = getInimigoAFrente(partida);
            if (inimigo != null) {
                atacar(partida, inimigo);
            } else {
                partida.setPontuacao(this.getJogador(), this.getAtaque());
            }
        }
    }
}
