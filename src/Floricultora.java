public class Floricultora extends Humano{

    public Floricultora(Jogador jogador){
        super("Floricultora",5, 0, 3, 2, jogador);
        this.descricao = """
                            Floricultora
                Amante de toda as flores e plantas.
                Não subestime seu poder, as vezes ela
                só precisa de um espaço para fazer seu
                jardim;
                """;
    }

    @Override
    public void atacar(Partida partida) {
        Personagem aliadoADireita = getAliadoADireita(partida);
        Personagem aliadoAEsquerda = getAliadoAEsquerda(partida);
        if(this.y == 1 || this.y ==2) {
            if (aliadoADireita == null) {
                aliadoADireita = new Roseira(partida.jogadorDaVez);
            }
            if (aliadoAEsquerda == null) {
                aliadoAEsquerda = new Roseira(partida.jogadorDaVez);
            }
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
