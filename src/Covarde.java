public class Covarde extends Humano{

    public Covarde(Jogador jogador){
        super("Covarde",3,2,1,0, jogador);
        this.setDescricao("""
                            Covarde
                Seguidor da famosa filosofia de "dar
                o tapa e esconder a mão", o covarde
                se esconde atrás de seus aliados, ele
                não é muito forte, mas sabe exatamente
                em quem bater... (Obs: ele odeia gregos)
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {
        partida.setPontuacao(this.getJogador(), this.getAtaque());
    }

}
