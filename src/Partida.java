public class Partida {

    public Partida(Jogador jogador1,Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.pontuacao = 0;
        this.turno = 1;
        this.jogadorDaVez = jogador1;
    }

    Personagem[][] tabuleiro = new Personagem[5][4];

    Jogador jogador1;
    Jogador jogador2;
    Jogador jogadorDaVez;
    Jogador vencedor;
    int turno;
    int pontuacao;

    public void setPontuacao (Jogador jogador, int Pontuacao){
        if(jogador == this.jogador1){
            this.pontuacao+=pontuacao;
        }else{
            this.pontuacao-=pontuacao;
        }
        if (this.pontuacao >= 10||this.pontuacao<=-10){
            this.setVencedor(jogador);
        }
    }
    public void alternarJogadorDaVez(){
        if(this.jogadorDaVez == jogador1){
            this.jogadorDaVez = jogador2;
        } else {
            this.turno ++;
            jogadorDaVez = jogador1;
        }
    }
    public void setVencedor(Jogador jogador){
        this.vencedor = jogador;
    }
}
