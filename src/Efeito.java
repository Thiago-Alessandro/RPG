public abstract class Efeito{

    public  Efeito(int duracao,Personagem personagem){
        this.duracao = duracao;
        this.personagem = personagem;
    }
    int duracao;
    Personagem personagem;

    String descricao;

    public abstract void efetuar();

    public boolean verificarPossuiEfeito(Personagem personagem, Efeito efeito){
        for(Efeito efeitoSofrido : personagem.listaEfeitosSofridos){
            if(efeito.equals(efeitoSofrido)){
                efeitoSofrido.duracao+=efeito.duracao;
                return true;
            }
        }
        return false;
    }

    public String getDescricao(){
        return descricao;
    }

    @Override
    public String toString() {
        return  "\t" + this.getClass() +
                "\nDuracao: " + duracao;
    }
}
