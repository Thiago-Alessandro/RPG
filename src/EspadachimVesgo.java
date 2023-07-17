public class EspadachimVesgo extends Humano{

    public EspadachimVesgo(){
        super(2,1,3,0);
        this.descricao = """
                        Espadachim Vesgo
                Se você for para o campo de batalha
                e se deparar com um espadachim em sua
                frente você vai tremer como vara verde,
                a não ser que você encontre este aqui...
                O espadachim vesgo possui a habilidade
                incrivel de manter seus olhos em dois
                inimigos de uma vez só! entretanto ele
                não é muito bom em olhar para frente... 
                """;
    }

    @Override
    public void atacar(Partida partida) {

        Personagem inimigoADireita = getInimigoADireita(partida);//cpa da p passar isso p atacarOficial tbm
        Personagem inimigoAEsquerda = getInimigoAEsquerda(partida);

        if(inimigoADireita!=null){
            atacarOficial(partida, inimigoADireita);
        } else {
            partida.setPontuacao(this.jogador, this.ataque);
        }

        if(inimigoAEsquerda!=null){
            atacarOficial(partida, inimigoAEsquerda);
        } else {
            partida.setPontuacao(this.jogador, this.ataque);
        }

    }

    @Override
    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa ++;
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
