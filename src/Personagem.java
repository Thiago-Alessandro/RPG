public abstract class Personagem {

    int vidaMax;
    int vida;
    int ataque;
    int defesa;
    int movimento;
    int alcance;
    int x;
    int y;

    public abstract void atacar(Personagem inimigo);
    public abstract void defender();
    public abstract Personagem[][] mover(int x,int y,Personagem listaPersonagens[][]);
    public abstract void curar();

}
