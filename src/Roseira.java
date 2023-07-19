public class Roseira extends Planta{

    public Roseira(Jogador jogador){
        super("Roseira",0,1,3,1,jogador);this.listaEfeitosCausados.add(new Espinhos(null));
        this.descricao = """
                          Roseira
                Toda rosa tem seu espinho...
                Obs: são as favoritinhas da
                floricultora
                """;
    }

    @Override
    public void atacar(Partida partida) {
        //implementar "efeitos" e/ou pocoes
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
