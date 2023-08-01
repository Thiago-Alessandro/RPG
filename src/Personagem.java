import java.util.ArrayList;

public abstract class Personagem {

    //region atributos
    private int vidaMax;
    private int vida;
    private int ataque;
    private int defesa;
    private int defesaMax;
    private int custo;
    private int x;
    private int y;
    private Jogador jogador;
    private String descricao;
    private final String nome;
    private int turnosParaRecuperarDefesa;
    private ArrayList<Efeito> listaEfeitosSofridos;
    private ArrayList<Efeito> listaEfeitosCausados;
    //endregion

     public Personagem(String nome, int custo, int ataque, int vidaMax, int defesaMax, Jogador jogador){
         this.custo = custo;
         this.ataque = ataque;
         this.vidaMax = vidaMax;
         this.vida = vidaMax;
         this.defesaMax = defesaMax;
         this.defesa = defesaMax;
         this.turnosParaRecuperarDefesa = 2;
         this.nome = nome;
         this.jogador = jogador;
     }

    public abstract void getInimigoAAtacar(Partida partida);

    public void sacrificar(Partida partida){
        partida.getTabuleiro()[this.y][this.x] = null;
        this.jogador.setTotalRecursos(this.jogador.getTotalRecursos()+1);
    }

    public void atacar(Partida partida, Personagem inimigo){

        this.setListaEfeitosSofridos(inimigo.listaEfeitosCausados);

        System.out.println("atacar oficial");
        int ataqueTotal = this.ataque - inimigo.defesa;
        if(inimigo.defesa - this.ataque >= 0){
            inimigo.defesa -= this.ataque;
            System.out.println("ataca defesa");
        }else{
            inimigo.defesa = 0;
            if(inimigo.vida-ataqueTotal > 0){
                inimigo.vida-=ataqueTotal;
                System.out.println("ataca vida personagem");
            }else if(inimigo.vida - ataqueTotal == 0){
                partida.getTabuleiro()[inimigo.y][inimigo.x] = null;
                Jogador jogador = inimigo.getJogador();
                jogador.setTotalRecursos(jogador.getTotalRecursos()+1);
                System.out.println("ataca mata o personagem");
                this.jogador.setTotalRecursos(this.jogador.getTotalRecursos()+1);
            }else{
                partida.getTabuleiro()[inimigo.y][inimigo.x] = null;
                Jogador jogador = inimigo.getJogador();
                jogador.setTotalRecursos(jogador.getTotalRecursos()+1);
                partida.setPontuacao(this.jogador, (inimigo.vida - ataqueTotal)*-1);
                System.out.println("ataca jogador");
            }
        }
    }

    public void defender() {
        if(this.defesa < this.defesaMax){
            this.defesa += 1;
        }
    }

    public void curar(int cura) {
        if(this.vida + cura >= this.vidaMax){
            this.vida = this.vidaMax;
        } else {
            this.vida += cura;
        }
    }

    private boolean verificarPossuiEfeito(Efeito efeitoAdicional){//usar isso p atribuir efeito no atacar
        for(Efeito efeitoSofrido : this.listaEfeitosSofridos){
            if(efeitoAdicional.equals(efeitoSofrido)){
                efeitoSofrido.setDuracao(efeitoSofrido.getDuracao() + efeitoAdicional.getDuracao());
                return true;
            }
        }
        return false;
    }

    public String mostrarDetalhes() {
        return
                this.nome +
                "\nVida Máxima: " + vidaMax +
                "\nVida: " + vida +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nDefesa Máxima: " + defesaMax +
                "\nCusto: " + custo;
    }

    public String mostrarEfeitosSofridos(){
        if(this.listaEfeitosSofridos!=null) {
            String efeitosSofridos = "";
            int indice = 1;
            for (Efeito efeito : this.listaEfeitosSofridos) {
                efeitosSofridos += "\n" + indice + " - " + efeito.getNome();
                indice++;
            }
            return efeitosSofridos;
        } else {
            return this.nome + " não possui efeitos sofridos\n";
        }
    }

    public String mostrarEfeitosCausados(){
        if(this.listaEfeitosCausados!=null) {
            String efeitosCausados = "";
            int indice = 1;
            for (Efeito efeito : this.listaEfeitosCausados) {
                efeitosCausados += "\n" + indice + " - " + efeito.getNome();
                indice++;
            }
            return efeitosCausados;
        } else {
            return this.nome + " não possui efeitos\n";
        }

    }

    //region getter e setters
    public Personagem getInimigoAFrente(Partida partida) {
        if(partida.getJogador1()==this.jogador){

            if(partida.getTabuleiro()[this.y+1][this.x]!=null){
                return partida.getTabuleiro()[this.y+1][this.x];
            } else if (partida.getTabuleiro()[this.y+2][this.x]!=null) {
                return partida.getTabuleiro()[this.y+2][this.x];
            }
        }else{
            if(partida.getTabuleiro()[this.y-1][this.x]!=null){
                return partida.getTabuleiro()[this.y-1][this.x];
            } else if (partida.getTabuleiro()[this.y-2][this.x]!=null) {
                return partida.getTabuleiro()[this.y-2][this.x];
            }
        }
        return null;
    }

    public Personagem getInimigoADireita(Partida partida){
        if(partida.getJogador1()==this.jogador){
            if(this.x+1 < 5){

                if(partida.getTabuleiro()[this.y+1][this.x+1]!=null){
                    return partida.getTabuleiro()[this.y+1][this.x+1];

                } else if(partida.getTabuleiro()[this.y+2][this.x+1]!=null){
                    return partida.getTabuleiro()[this.y+2][this.x+1];
                }
            }
        } else {
            if(this.x-1 >= 0){
                if(partida.getTabuleiro()[this.y+1][this.x-1]!=null){
                    return partida.getTabuleiro()[this.y-1][this.x-1];

                } else if(partida.getTabuleiro()[this.y - 2][this.x-1]!=null) {
                    return partida.getTabuleiro()[this.y - 2][this.x-1];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoAEsquerda(Partida partida){
        if(partida.getJogador1()==this.jogador){
            if(this.x-1 >= 0){

                if(partida.getTabuleiro()[this.y+1][this.x-1]!=null){
                    return partida.getTabuleiro()[this.y+1][this.x-1];

                } else if(partida.getTabuleiro()[this.y+2][this.x-1]!=null){
                    return partida.getTabuleiro()[this.y+2][this.x-1];
                }
            }
        } else {
            if(this.x+1 < 5){
                if(partida.getTabuleiro()[this.y-1][this.x+1]!=null){
                    return partida.getTabuleiro()[this.y-1][this.x+1];

                } else if(partida.getTabuleiro()[this.y-2][this.x + 1]!=null) {
                    return partida.getTabuleiro()[this.y-2][this.x + 1];
                }
            }
        }
        return null;
    }

    public Personagem getInimigoDistante(Partida partida){

        if(partida.getJogador1() == this.jogador){

            if(partida.getTabuleiro()[this.y+2][this.x]!=null){
                return partida.getTabuleiro()[this.y+2][this.x];
            }
        } else {

            if(partida.getTabuleiro()[this.y-2][this.x]!=null){
                return partida.getTabuleiro()[this.y-2][this.x];
            }
        }
        return null;
    }

    public Personagem getAliadoADireita(Partida partida){

        if(partida.getJogador1() == this.jogador){
            if(this.getX()+1<5 && partida.getTabuleiro()[this.y][this.x+1]!=null){
                return partida.getTabuleiro()[this.y][this.x+1];
            }
        } else {
            if(this.getX()-1>0 && partida.getTabuleiro()[this.y][this.x-1]!=null){
                return partida.getTabuleiro()[this.y][this.x-1];
            }
        }
        return  null;
    }

    public Personagem getAliadoAEsquerda(Partida partida){

        if(partida.getJogador1() == this.jogador){
            if(this.getX()-1>0 && partida.getTabuleiro()[this.y][this.x-1]!=null){
                return partida.getTabuleiro()[this.y][this.x-1];
            }
        } else {
            if(this.getX()+1<5 && partida.getTabuleiro()[this.y][this.x+1]!=null){
                return partida.getTabuleiro()[this.y][this.x+1];
            }
        }
        return  null;
    }

    public String getDescricao(){
        return descricao;
    }

    public ArrayList<Efeito> getEfeitosSofridos(){
        return listaEfeitosSofridos;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getCusto() {
        return custo;
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getTurnosParaRecuperarDefesa() {
        return turnosParaRecuperarDefesa;
    }

    public void setTurnosParaRecuperarDefesa(int turnosParaRecuperarDefesa) {
        this.turnosParaRecuperarDefesa = turnosParaRecuperarDefesa;
    }

    public ArrayList<Efeito> getListaEfeitosSofridos() {
        return listaEfeitosSofridos;
    }

    public void setListaEfeitosSofridos(ArrayList<Efeito> efeitosASofrer) {
        if(efeitosASofrer!=null) {
            if (this.listaEfeitosSofridos != null) {
                for (Efeito efeitoAdicional : efeitosASofrer) {
                    if (!verificarPossuiEfeito(efeitoAdicional)) {
                        efeitoAdicional.setPersonagem(this);
                        this.listaEfeitosSofridos.add(efeitoAdicional);
                    }
                }
            } else {
                this.listaEfeitosSofridos = efeitosASofrer;
            }
        }
    }

    public ArrayList<Efeito> getListaEfeitosCausados() {
        return listaEfeitosCausados;
    }

    public void setListaEfeitosCausados(ArrayList<Efeito> listaEfeitosCausados) {
        this.listaEfeitosCausados = listaEfeitosCausados;
    }
    //endregion
    @Override
    public String toString() {
        return nome;
    }
}
