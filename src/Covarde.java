public class Covarde extends Personagem{

    public Covarde(){
        super(3,2,1,0);
    }

    @Override
    public void atacar(Partida partida) {
        partida.setPontuacao(this.jogador, this.ataque);
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
