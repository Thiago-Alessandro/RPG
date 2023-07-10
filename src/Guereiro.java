public class Guereiro extends Personagem{


    @Override
    public void atacar(Partida partida) {
        Personagem inimigo = verificarInimigoNoCaminho(partida);
        if(inimigo != null){
            int ataqueTotal = this.ataque - inimigo.defesa;
            if(inimigo.defesa - this.ataque >= 0){
                inimigo.defesa -= this.ataque;
            }else{
                inimigo.defesa = 0;
                if(inimigo.vida-ataqueTotal > 0){
                    inimigo.vida-=ataqueTotal;
                }else if(inimigo.vida - ataqueTotal == 0){
                    partida.tabuleiro[inimigo.x][inimigo.y] = null;
                }else{
                    partida.tabuleiro[inimigo.x][inimigo.y] = null;
                    partida.setPontuacao(this.jogador, (inimigo.vida - ataqueTotal)*-1);
                }
            }
        }else{
            partida.setPontuacao(this.jogador,this.ataque);
        }
    }

    public Personagem verificarInimigoNoCaminho(Partida partida) {
        if(this.jogador.isJogador1){//partida.jogador == this.jogador

            if(partida.tabuleiro[this.x][this.y+1]!=null){
                return partida.tabuleiro[this.x][this.y+1];
            } else if (partida.tabuleiro[this.x][this.y+2]!=null) {
                return partida.tabuleiro[this.x][this.y+2];
            }
        }else{
            if(partida.tabuleiro[this.x][this.y-1]!=null){
                return partida.tabuleiro[this.x][this.y-1];
            } else if (partida.tabuleiro[this.x][this.y-2]!=null) {
                return partida.tabuleiro[this.x][this.y-2];
            }
        }
        return null;
    }

    @Override
    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa += 1;
        }
    }

    @Override
    public void curar() {

    }
}
