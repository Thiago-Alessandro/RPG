public class Guerreiro extends Humano{

    public Guerreiro(){
        super(0,2,5,0);
        this.descricao = """
                            Guerreiro
                O guerreiro é a tropa inicial
                fundamental de qualoquer exército,
                de vez em quando aparece um querendo
                se juntar à batalha.
                """;
    }

    @Override
    public void atacar(Partida partida) {
        Personagem inimigo = getInimigoAFrente(partida);
        if(inimigo != null){
            atacarOficial(partida, inimigo);
        }else{
            partida.setPontuacao(this.jogador,this.ataque);
        }
    }

    @Override
    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa += 1;
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
