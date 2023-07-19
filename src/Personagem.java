import java.util.ArrayList;

public abstract class Personagem {

    int vidaMax;
    int vida;
    int ataque;
    int defesa;
    int defesaMax;
     int custo;
     int x;
     int y;
     Jogador jogador;
     String descricao;
     String nome;
     int turnosParaRecuperarDefesa;
     ArrayList<Efeito> listaEfeitosSofridos;
     ArrayList<Efeito> listaEfeitosCausados;

     public Personagem(String nome, int custo, int ataque, int vidaMax, int defesaMax, Jogador jogador){

         this.custo = custo;
         this.ataque = ataque;
         this.vidaMax = vidaMax;
         this.vida = vidaMax;
         this.defesaMax = defesaMax;
         this.defesa = defesaMax;
         this.turnosParaRecuperarDefesa = 2;
         this.nome = nome;
         this.jogador = jogador;
     }

    public abstract void defender();
    // public abstract Personagem[][] mover(int x,int y,Personagem listaPersonagens[][]);
    public abstract void curar(int cura);

    public abstract void atacar(Partida partida);

    public void sacrificar(Partida partida){
        partida.tabuleiro[this.y][this.x] = null;
        this.jogador.totalRecursos ++;
    }

    public void atacarOficial(Partida partida, Personagem inimigo){

        int ataqueTotal = this.ataque - inimigo.defesa;
        if(inimigo.defesa - this.ataque >= 0){
            inimigo.defesa -= this.ataque;
        }else{
            inimigo.defesa = 0;
            if(inimigo.vida-ataqueTotal > 0){
                inimigo.vida-=ataqueTotal;
            }else if(inimigo.vida - ataqueTotal == 0){
                partida.tabuleiro[inimigo.y][inimigo.x] = null;
                this.jogador.totalRecursos ++;
            }else{
                partida.tabuleiro[inimigo.y][inimigo.x] = null;
                this.jogador.totalRecursos ++;
                partida.setPontuacao(this.jogador, (inimigo.vida - ataqueTotal)*-1);
            }
        }
    }

    public Personagem getInimigoAFrente(Partida partida) {
        if(partida.jogador1==this.jogador){

            if(partida.tabuleiro[this.y+1][this.x]!=null){
                return partida.tabuleiro[this.y+1][this.x];
            } else if (partida.tabuleiro[this.y+2][this.x]!=null) {
                return partida.tabuleiro[this.y+2][this.x];
            }
        }else{
            if(partida.tabuleiro[this.y-1][this.x]!=null){
                return partida.tabuleiro[this.y-1][this.x];
            } else if (partida.tabuleiro[this.y-2][this.x]!=null) {
                return partida.tabuleiro[this.y-2][this.x];
            }
        }
        return null;
    }

    public Personagem getInimigoADireita(Partida partida){
        if(partida.jogador1==this.jogador){
            if(this.x+1 < 5){

                if(partida.tabuleiro[this.y+1][this.x+1]!=null){
                    return partida.tabuleiro[this.y+1][this.x+1];

                } else if(partida.tabuleiro[this.y+2][this.x+1]!=null){
                    return partida.tabuleiro[this.y+2][this.x+1];
                }
            }
        } else {
            if(this.x-1 >= 0){
                if(partida.tabuleiro[this.y+1][this.x-1]!=null){
                    return partida.tabuleiro[this.y-1][this.x-1];

                } else if(partida.tabuleiro[this.y - 2][this.x-1]!=null) {
                    return partida.tabuleiro[this.y - 2][this.x-1];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoAEsquerda(Partida partida){
        if(partida.jogador1==this.jogador){
            if(this.x-1 >= 0){

                if(partida.tabuleiro[this.y+1][this.x-1]!=null){
                    return partida.tabuleiro[this.y+1][this.x-1];

                } else if(partida.tabuleiro[this.y+2][this.x-1]!=null){
                    return partida.tabuleiro[this.y+2][this.x-1];
                }
            }
        } else {
            if(this.x+1 < 5){
                if(partida.tabuleiro[this.y-1][this.x+1]!=null){
                    return partida.tabuleiro[this.y-1][this.x+1];

                } else if(partida.tabuleiro[this.y-2][this.x + 1]!=null) {
                    return partida.tabuleiro[this.y-2][this.x + 1];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoDistante(Partida partida){

        if(partida.jogador1 == this.jogador){

            if(partida.tabuleiro[this.y+2][this.x]!=null){
                return partida.tabuleiro[this.y+2][this.x];
            }
        } else {

            if(partida.tabuleiro[this.y-2][this.x]!=null){
                return partida.tabuleiro[this.y-2][this.x];
            }
        }
      return null;
    }

    public Personagem getAliadoADireita(Partida partida){

        if(partida.jogador1 == this.jogador){
            if(partida.tabuleiro[this.y][this.x+1]!=null){
                return partida.tabuleiro[this.y][this.x+1];
            }
        } else {
            if(partida.tabuleiro[this.y][this.x-1]!=null){
                return partida.tabuleiro[this.y][this.x-1];
            }
        }
        return  null;
    }

    public Personagem getAliadoAEsquerda(Partida partida){

        if(partida.jogador1 == this.jogador){
            if(partida.tabuleiro[this.y][this.x-1]!=null){
                return partida.tabuleiro[this.y][this.x-1];
            }
        } else {
            if(partida.tabuleiro[this.y][this.x+1]!=null){
                return partida.tabuleiro[this.y][this.x+1];
            }
        }
        return  null;
    }

    public String getDescricao(){
        return descricao;
    }

    public ArrayList<Efeito> getEfeitosCausados(){
        return listaEfeitosCausados;
    }

    public ArrayList<Efeito> getEfeitosSofridos(){
        return listaEfeitosSofridos;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String mostrarDetalhes() {
        return
                this.nome +
                "\nVida Máxima: " + vidaMax +
                "\nVida: " + vida +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nDefesa Máxima: " + defesaMax +
                "\nCusto: " + custo;
               // "\nJogador: " + jogador;

//                ", listaEfeitosSofridos=" + listaEfeitosSofridos + //cpa implementar os toString
//                ", getListaEfeitosCausados=" + getListaEfeitosCausados +
    }
    public String mostrarEfeitosSofridos(){
        String efeitosSofridos = "";
        int indice = 1;
        for(Efeito efeito : this.listaEfeitosSofridos){
            efeitosSofridos += "\n" + indice + " - " + efeito;
            indice++;
        }
        return efeitosSofridos;
    }

    public String mostrarEfeitosCausados(){
        String efeitosCausados = "";
        int indice = 1;
        for(Efeito efeito : this.listaEfeitosCausados){
            efeitosCausados += "\n" + indice + " - " + efeito;
            indice++;
        }
        return efeitosCausados;
    }

}
