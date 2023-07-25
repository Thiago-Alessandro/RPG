import java.util.ArrayList;

public class Roseira extends Planta{

    public Roseira(Jogador jogador){
        super("Roseira",0,1,3,1,jogador);
        ArrayList<Efeito> listaEfeitos = new ArrayList<>();
        listaEfeitos.add(new Espinhos(null));
        this.setListaEfeitosCausados(listaEfeitos);
        this.setDescricao("""
                          Roseira
                Toda rosa tem seu espinho...
                Obs: são as favoritinhas da
                floricultora
                """);
    }

    @Override
    public void getInimigoAAtacar(Partida partida) {}
}
