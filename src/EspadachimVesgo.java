public class EspadachimVesgo extends Personagem{

    public EspadachimVesgo(){
        super(2,1,3,0);
    }

    @Override
    public void atacar(Partida partida) {

        Personagem inimigoADireita = getInimigoADireita(partida);//cpa da p passar isso p atacarOficial tbm
        Personagem inimigoAEsquerda = getInimigoAEsquerda(partida);

        if(inimigoADireita!=null){
            atacarOficial(partida, inimigoADireita);
        } else {
            partida.setPontuacao(this.jogador, this.ataque);
        }

        if(inimigoAEsquerda!=null){
            atacarOficial(partida, inimigoAEsquerda);
        } else {
            partida.setPontuacao(this.jogador, this.ataque);
        }

    }

    @Override
    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa ++;
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
