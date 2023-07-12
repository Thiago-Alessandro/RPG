public abstract class Personagem {

    int vidaMax;
    int vida;
    int ataque;
    int defesa;
    int defesaMax;
    //int movimento;
    //int alcance;
     int custo;
     int x;
     int y;
     Jogador jogador;

     public Personagem(int custo, int ataque, int vidaMax, int defesaMax ){

         this.custo = custo;
         this.ataque = ataque;
         this.vidaMax = vidaMax;
         this.vida = vidaMax;
         this.defesaMax = defesaMax;
         this.defesa = defesaMax;

     }


    public abstract void defender();
    // public abstract Personagem[][] mover(int x,int y,Personagem listaPersonagens[][]);
    public abstract void curar(int cura);

    public abstract void atacar(Partida partida);

    public void atacarOficial(Partida partida, Personagem inimigo){

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
    }

    public Personagem getInimigoAFrente(Partida partida) {
        if(partida.jogador1==this.jogador){

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

    public Personagem getInimigoADireita(Partida partida){
        if(partida.jogador1==this.jogador){
            if(this.x+1 < 5){

                if(partida.tabuleiro[this.x+1][this.y+1]!=null){
                    return partida.tabuleiro[this.x+1][this.y+1];

                } else if(partida.tabuleiro[this.x+1][this.y+2]!=null){
                    return partida.tabuleiro[this.x+1][this.y+2];
                }
            }
        } else {
            if(this.x-1 >= 0){
                if(partida.tabuleiro[this.x+1][this.y-1]!=null){
                    return partida.tabuleiro[this.x-1][this.y-1];

                } else if(partida.tabuleiro[this.x-1][this.y-2]!=null) {
                    return partida.tabuleiro[this.x - 1][this.y - 2];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoAEsquerda(Partida partida){
        if(partida.jogador1==this.jogador){
            if(this.x-1 < 5){

                if(partida.tabuleiro[this.x-1][this.y+1]!=null){
                    return partida.tabuleiro[this.x-1][this.y+1];

                } else if(partida.tabuleiro[this.x-1][this.y+2]!=null){
                    return partida.tabuleiro[this.x-1][this.y+2];
                }
            }
        } else {
            if(this.x+1 >= 0){
                if(partida.tabuleiro[this.x+1][this.y-1]!=null){
                    return partida.tabuleiro[this.x+1][this.y-1];

                } else if(partida.tabuleiro[this.x+1][this.y-2]!=null) {
                    return partida.tabuleiro[this.x + 1][this.y - 2];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoDistante(Partida partida){

        if(partida.jogador1 == this.jogador){

            if(partida.tabuleiro[this.x][this.y+2]!=null){
                return partida.tabuleiro[this.x][this.y+2];
            }
        } else {

            if(partida.tabuleiro[this.x][this.y-2]!=null){
                return partida.tabuleiro[this.x][this.y-2];
            }
        }
      return null;
    }

    public Personagem getAliadoADireita(Partida partida){

        if(partida.jogador1 == this.jogador){
            if(partida.tabuleiro[this.x+1][this.y]!=null){
                return partida.tabuleiro[this.x+1][this.y];
            }
        } else {
            if(partida.tabuleiro[this.x-1][this.y]!=null){
                return partida.tabuleiro[this.x-1][this.y];
            }
        }
        return  null;
    }

    public Personagem getAliadoAEsquerda(Partida partida){

        if(partida.jogador1 == this.jogador){
            if(partida.tabuleiro[this.x-1][this.y]!=null){
                return partida.tabuleiro[this.x-1][this.y];
            }
        } else {
            if(partida.tabuleiro[this.x+1][this.y]!=null){
                return partida.tabuleiro[this.x+1][this.y];
            }
        }
        return  null;
    }

}
