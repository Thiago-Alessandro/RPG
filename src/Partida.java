public class Partida {

    Personagem[][] tabuleiro = new Personagem[5][4];

    Jogador jogador1;
    Jogador jogador2;

    Jogador vencedor;

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
    public void setVencedor(Jogador jogador){
        this.vencedor = jogador;
    }
}
