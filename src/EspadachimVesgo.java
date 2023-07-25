public class EspadachimVesgo extends Humano{

    public EspadachimVesgo(Jogador jogador){
        super("Espadachim Vesgo",2,1,3,0, jogador);
        this.setDescricao("""
                        Espadachim Vesgo
                Se você for para o campo de batalha
                e se deparar com um espadachim em sua
                frente você vai tremer como vara verde,
                a não ser que você encontre este aqui...
                O espadachim vesgo possui a habilidade
                incrivel de manter seus olhos em dois
                inimigos de uma vez só! entretanto ele
                não é muito bom em olhar para frente...
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {
        if(this.getY() == 1 || this.getY() == 2) {//se estiver na linha de frente
            Personagem inimigoADireita = getInimigoADireita(partida);//cpa da p passar isso p atacarOficial tbm
            Personagem inimigoAEsquerda = getInimigoAEsquerda(partida);

            if (inimigoADireita != null) {
                atacar(partida, inimigoADireita);
            } else {
                partida.setPontuacao(this.getJogador(), this.getAtaque());
            }

            if (inimigoAEsquerda != null) {
                atacar(partida, inimigoAEsquerda);
            } else {
                partida.setPontuacao(this.getJogador(), this.getAtaque());
            }
        }
    }

}
