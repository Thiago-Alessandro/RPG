public abstract class Personagem {

    int vidaMax;
    int vida;
    int ataque;
    int defesa;
    int defesaMax;
    //int movimento;
    //int alcance;
    int custo;
     int x;
     int y;
     Jogador jogador;

    public abstract void atacar(Partida partida);

    public abstract Personagem verificarInimigoNoCaminho(Partida partida);
    public abstract void defender();
    // public abstract Personagem[][] mover(int x,int y,Personagem listaPersonagens[][]);
    public abstract void curar();

}
