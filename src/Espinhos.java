import java.util.ArrayList;

public class Espinhos extends Efeito{

    public Espinhos(Personagem personagem) {
        super(1, personagem, "Espinhos");
        this.setDescricao("""
                  Espinhos
                Doi. Bastante.
                """);
    }

    @Override
    public void efetuar() {
        Personagem personagem = this.getPersonagem();
        personagem.setVida(personagem.getVida()-1);
        if(personagem.getVida() <= 0){
            System.out.println("O " + this.getPersonagem().getNome() + " morreu"); //passar como string e dar jeito de dar sout no main
            this.setPersonagem(null);
            Jogador jogador = this.getPersonagem().getJogador();
            jogador.setTotalRecursos(jogador.getTotalRecursos() + 1);
            return;
        }
        this.setDuracao(this.getDuracao()-1);
        if (personagem instanceof Humano) {
            ArrayList<Efeito> listaEfeitos = personagem.getListaEfeitosSofridos();
            Efeito sangramento = new Sangramento(personagem); //provavelmente vai dar erro na comparacao pq aponta p outro objeto
            if(listaEfeitos.contains(sangramento)){
                int indiceDoEfeito;
                indiceDoEfeito = listaEfeitos.indexOf(sangramento);
                Efeito sangramentoPersonagem = listaEfeitos.get(indiceDoEfeito);
                sangramentoPersonagem.setDuracao(sangramento.getDuracao() + sangramento.getDuracao());
                return;
            }
                listaEfeitos.add(this);
            System.out.println("teste listaEfeitos");
            System.out.println(listaEfeitos);
        }
    }
}
