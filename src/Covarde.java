public class Covarde extends Humano{

    public Covarde(){
        super("Covarde",3,2,1,0);
        this.descricao = """
                            Covarde
                Seguidor da famosa filosofia de "dar
                o tapa e esconder a mão", o covarde
                se esconde atrás de seus aliados, ele
                não é muito forte, mas sabe exatamente
                em quem bater... (Obs: ele odeia gregos)
                """;
    }

    @Override
    public void atacar(Partida partida) {
        partida.setPontuacao(this.jogador, this.ataque);
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
