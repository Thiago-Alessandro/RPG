import java.util.ArrayList;
import java.util.Collections;

public class Jogador {
    private final String nome;
    private ArrayList<Personagem> cartasDeck = new ArrayList<>();
    private ArrayList<Personagem> cartasNaMao = new ArrayList<>();
    private int totalRecursos = 0;

    public Jogador(String nome){

        this.nome = nome;
        for(int i = 0; i < 2; i++){
            this.cartasDeck.add(new Catapulta(this));
            this.cartasDeck.add(new Covarde(this));
            this.cartasDeck.add(new Floricultora(this));
            this.cartasNaMao.add(new Guerreiro(this));
        }
        for(int i =0;i<3;i++){
            this.cartasDeck.add(new EspadachimVesgo(this));
            this.cartasDeck.add(new Guerreiro(this));
        }
    }

    public Personagem comprarCarta(){
        if(this.totalRecursos > 0){

            Collections.shuffle(this.cartasDeck);
            Personagem cartaComprada = this.cartasDeck.get(0);

            this.cartasNaMao.add(cartaComprada);
            this.cartasDeck.remove(cartaComprada);

            this.totalRecursos --;
            return cartaComprada;
        }
        return null;
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
            cartasNaMao += "\n" + indice + " - " + personagem.getNome();
            indice++;
        }
        return cartasNaMao;
    }

    public String mostrarEstatisticas(){
        return "Total Recursos: " + this.totalRecursos +
               "\nCartas restantes no baralho: " + this.cartasDeck.size();
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Personagem> getCartasDeck() {
        return cartasDeck;
    }

    public void setCartasDeck(ArrayList<Personagem> cartasDeck) {
        this.cartasDeck = cartasDeck;
    }

    public void setCartasNaMao(ArrayList<Personagem> cartasNaMao) {
        this.cartasNaMao = cartasNaMao;
    }

    public int getTotalRecursos() {
        return totalRecursos;
    }

    public void setTotalRecursos(int totalRecursos) {
        this.totalRecursos = totalRecursos;
    }
}
