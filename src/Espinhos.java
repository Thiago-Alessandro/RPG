import java.util.ArrayList;

public class Espinhos extends Efeito{

    public Espinhos(Personagem personagem) {
        super(1, personagem);
        this.descricao = """
                  Espinhos
                Doi. Bastante.
                """;
    }

    @Override
    public void efetuar() {
        this.personagem.vida -= 1;
        if(this.personagem.vida <= 0){
            this.personagem = null;
            return;
        }
        this.duracao --;
        if (this.personagem instanceof Humano) {
            ArrayList<Efeito> listaEfeitos = this.personagem.listaEfeitosSofridos;
            Efeito sangramento = new Sangramento(this.personagem); //provavelmente vai dar erro na comparacao pq aponta p outro objeto
            if(listaEfeitos.contains(sangramento)){
                int indiceDoEfeito;
                indiceDoEfeito = listaEfeitos.indexOf(sangramento);
                Efeito sangramentoPersonagem = listaEfeitos.get(indiceDoEfeito);
                sangramentoPersonagem.duracao += sangramento.duracao;
                return;
            }
                listaEfeitos.add(this);
            System.out.println("teste listaEfeitos");
            System.out.println(listaEfeitos);
        }
    }
}
