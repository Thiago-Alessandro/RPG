public class Catapulta extends Personagem{

    public Catapulta(Jogador jogador){
        super("Catapulta",2,2,4,2, jogador);
        this.descricao = """
                            Catapulta
                Muitos obstáculos no seu caminho?
                Os seus problemas acabaram! Com
                essa invenção grega do século IV
                você pode sair por cima de qualquer
                inimigo. Vai uma pedrada ai?
                (possui dano extra em dinossauros)
                """;
    }

    @Override
    public void atacar(Partida partida) {
        Personagem inimigo = getInimigoDistante(partida);
        if(inimigo!= null){
            this.atacarOficial(partida, inimigo);
        } else {
            partida.setPontuacao(this.jogador, this.ataque);
        }
    }

    @Override
    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa++;
        }
    }

    @Override
    public void curar(int cura) {
        if(this.vida + cura >= this.vidaMax){
            this.vida = this.vidaMax;
        } else {
            this.vida += cura;
        }
    }

}
