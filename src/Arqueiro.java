public class Arqueiro extends Personagem{

    public Arqueiro(){
        this.ataque = 7;
        this.vidaMax = 6;
        this.defesa = 0;
        this.movimento = 1;
        this.vida = vidaMax;
        this.alcance = 3;
    }

    @Override
    public void atacar(Personagem inimigo) {
        double forcaAtaque = this.vida/this.vidaMax;
        double ataqueTotal = Math.ceil(this.ataque * forcaAtaque) - inimigo.defesa;
        inimigo.vida -= ataqueTotal;
        if(inimigo.defesa > 0){
            inimigo.defesa --;
        }
    }

    @Override
    public void defender() {
        this.defesa+=2;
    }

    @Override
    public Personagem[][] mover(int x, int y,Personagem listaPersonagens[][]) {

        if(verificarMovimentoPossivel(x,y,listaPersonagens)){
            listaPersonagens[x][y] = this;
            return listaPersonagens;
        }else{
            return null;
        }
    }

    public boolean verificarMovimentoPossivel(int x, int y, Personagem listaPersonagens[][]){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                Personagem personagem = listaPersonagens[i][j];
                if(personagem.x == x && personagem.y == y){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void curar() {
        if(this.vida + this.vidaMax*20/100 >= this.vidaMax){
            this.vida = this.vidaMax;
        }else{
            this.vida += this.vidaMax*20/100;
        }
    }
}
