import java.util.ArrayList;
import java.util.Collections;

public class Jogador {
    String nome;
    ArrayList<Personagem> cartasDeck = new ArrayList<>();
    ArrayList<Personagem> cartasNaMao = new ArrayList<>();
    int totalRecursos = 0;

    public Jogador(String nome){

        this.nome = nome;
        for(int i = 0; i < 2; i++){
            this.cartasDeck.add(new Catapulta());
            this.cartasDeck.add(new Covarde());
            this.cartasDeck.add(new Floricultora());
            this.cartasNaMao.add(new Guerreiro());
        }
        for(int i =0;i<3;i++){
            this.cartasDeck.add(new EspadachimVesgo());
            this.cartasDeck.add(new Guerreiro());
        }
    }

    public void comprarCarta(){
        Collections.shuffle(this.cartasDeck);

        this.cartasNaMao.add(this.cartasDeck.get(0));
        this.cartasDeck.remove(0);

        this.totalRecursos --;

    }

    public Personagem selecionarCartaDaMao(int indice){
        if(cartasNaMao.size() - 1 < indice || indice < 0){
            return null;
        }
        return this.cartasNaMao.get(indice);
    }
    public ArrayList<Personagem> getCartasNaMao() {
        return cartasNaMao;
    }

    public String mostarCartasMao(){
        String cartasNaMao = "";
        int indice = 1;
        for(Personagem personagem : this.cartasNaMao){
            cartasNaMao += "\n" + indice + " - " + personagem.getClass();
            indice++;
        }
        return cartasNaMao;
    }

    public String mostrarEstatisticas(){
        return "Total Recursos: " + this.totalRecursos +
               "Cartas restantes no baralho: " + this.cartasDeck.size();
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
