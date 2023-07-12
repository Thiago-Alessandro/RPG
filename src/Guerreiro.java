public class Guerreiro extends Personagem{

    public Guerreiro(){
        super(0,2,5,0);
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
