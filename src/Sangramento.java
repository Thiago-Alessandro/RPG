public class Sangramento extends Efeito{

    int fatorSangramento;

    public Sangramento( Personagem personagem) {
        super(2, personagem);
        this.descricao = """
                        Sangramento
                Você não acha que o nome é
                autoexplicativo?
                """;
        this.fatorSangramento = 1;
    }

    @Override
    public void efetuar() {
        this.personagem.vida -= this.fatorSangramento;
        if(this.personagem.vida<=0){
            this.personagem = null;
        }
        this.fatorSangramento++;
    }
}
