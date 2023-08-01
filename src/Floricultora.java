public class Floricultora extends Humano{

    public Floricultora(Jogador jogador){
        super("Floricultora",5, 0, 3, 2, jogador);
        this.setDescricao("""
                            Floricultora
                Amante de toda as flores e plantas.
                Não subestime seu poder, as vezes ela
                só precisa de um espaço para fazer seu
                jardim;
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {
        Personagem aliadoADireita = getAliadoADireita(partida);
        Personagem aliadoAEsquerda = getAliadoAEsquerda(partida);
        if(this.getY() == 1 || this.getY() ==2) {
            if (aliadoADireita == null && this.getJogador() == partida.getJogador1() && this.getX()+1<5 ) {
                partida.getTabuleiro()[this.getY()][this.getX()+1] = new Roseira(partida.getJogadorDaVez());
            } else if (aliadoADireita == null && this.getX()-1>=0){
                partida.getTabuleiro()[this.getY()][this.getX()-1] = new Roseira(partida.getJogadorDaVez());
            }
            if (aliadoAEsquerda == null) {
                if(aliadoAEsquerda == null && this.getJogador() == partida.getJogador1() && this.getX()-1>=0 ){
                    partida.getTabuleiro()[this.getY()][this.getX()-1] = new Roseira(partida.getJogadorDaVez());
                } else if (aliadoAEsquerda == null && this.getX()+1<5){
                    partida.getTabuleiro()[this.getY()][this.getX()+1] = new Roseira(partida.getJogadorDaVez());
                }
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
}
