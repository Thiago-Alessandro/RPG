public class Floricultora extends Personagem{

    public Floricultora(){
        super(5, 0, 3, 2);
    }

    @Override
    public void atacar(Partida partida) {
        Personagem aliadoADireita = getAliadoADireita(partida);
        Personagem aliadoAEsquerda = getAliadoAEsquerda(partida);
        if(aliadoADireita == null){
            aliadoADireita = new Roseira();
        }
        if(aliadoAEsquerda == null){
            aliadoAEsquerda = new Roseira();
        }
        if(aliadoADireita != null && aliadoAEsquerda != null){
            if(aliadoADireita instanceof Planta){
                aliadoADireita.curar(1);
            }
            if(aliadoAEsquerda instanceof Planta){
                aliadoAEsquerda.curar(1);
            }
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
