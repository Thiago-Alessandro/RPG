public class Catapulta extends Personagem{

    public Catapulta(Jogador jogador){
        super("Catapulta",2,2,4,2, jogador);
        this.setDescricao("""
                            Catapulta
                Muitos obstáculos no seu caminho?
                Os seus problemas acabaram! Com
                essa invenção grega do século IV
                você pode sair por cima de qualquer
                inimigo. Vai uma pedrada ai?
                (possui dano extra em dinossauros)
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {
        Personagem inimigo = getInimigoDistante(partida);
        if(inimigo!= null){
            this.atacar(partida, inimigo);
        } else {
            partida.setPontuacao(this.getJogador(), this.getAtaque());
        }
    }
}
