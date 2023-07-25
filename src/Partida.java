public class Partida {

    public Partida(Jogador jogador1,Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.pontuacao = 0;
        this.turno = 1;
        this.jogadorDaVez = jogador1;
    }

    private Personagem[][] tabuleiro = new Personagem[4][5];
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorDaVez;
    private Jogador vencedor;
    private int turno;
    private int pontuacao;

    public void alternarJogadorDaVez(){
        if(this.jogadorDaVez == jogador1){
            this.jogadorDaVez = jogador2;
        } else {
            this.turno ++;
            jogadorDaVez = jogador1;
        }
    }
    private void trazerPersonagensParaFrente(){
        for(int x = 0; x < 5; x++){
            if(tabuleiro[0][x]!=null && tabuleiro[1][x] == null && jogadorDaVez == jogador1){
                tabuleiro[1][x] = tabuleiro[0][x];
                tabuleiro[1][x].setY(1);
                tabuleiro[0][x] = null;
            }
            if(tabuleiro[3][x]!=null && tabuleiro[2][x] == null && jogadorDaVez != jogador1){
                tabuleiro[2][x] = tabuleiro[3][x];
                tabuleiro[2][x].setY(2);
                tabuleiro[3][x] = null;
            }
        }
    }
    public void personagensAtacar(Partida partida){
        if(jogadorDaVez == jogador1){
            for(int x = 0; x < 5; x ++){
                for(int y = 0; y < 2; y++){
                    if(tabuleiro[y][x]!=null){
                        tabuleiro[y][x].getInimigoAAtacar(partida);
                    }
                }
            }
            inimigosSofrerEfeitos(jogador2);
        } else {
            for(int x = 0; x < 5; x ++){
                for(int y = 3; y > 1; y--){
                    if(tabuleiro[y][x]!= null){
                        tabuleiro[y][x].getInimigoAAtacar(partida);
                    }
                }
            }
            inimigosSofrerEfeitos(jogador1);
        }
        trazerPersonagensParaFrente();
        personagensRecuperarDefesa();
    }
    private void inimigosSofrerEfeitos(Jogador jogador){
        for(int x = 0; x < 5; x ++){
            for(int y  = 0; y < 4; y++){
                Personagem personagem = tabuleiro[y][x];
                if(personagem!=null){
                    for (Efeito efeito : personagem.getEfeitosSofridos()){
                        efeito.efetuar();
                    }
                }
            }
        }
    }
    private void personagensRecuperarDefesa(){
        if(jogadorDaVez == jogador1){
            for(int x = 0; x < 5; x ++){
                for(int y = 0; y < 2; y++){
                    Personagem carta = tabuleiro[y][x];
                    if(carta!=null && carta.getTurnosParaRecuperarDefesa()==0){
                        carta.defender();
                        carta.setTurnosParaRecuperarDefesa(2);
                    } else if (carta!=null) {
                        carta.setTurnosParaRecuperarDefesa(carta.getTurnosParaRecuperarDefesa()-1);
                    }
                }
            }
        } else {
            for(int x = 0; x < 5; x ++){
                for(int y = 3; y > 1; y--){
                    Personagem carta = tabuleiro[y][x];
                    if(carta!= null && carta.getTurnosParaRecuperarDefesa()==0){
                        carta.defender();
                        carta.setTurnosParaRecuperarDefesa(2);
                    } else if (carta!=null) {
                        carta.setTurnosParaRecuperarDefesa(carta.getTurnosParaRecuperarDefesa()-1);
                    }
                }
            }
        }
    }

    public String mostrarEstatisticas(){
        return "Turno: " + this.turno +
                "\nPontuação: " + this.pontuacao;
    }
    public String mostrarTabuleiro(){
        if(this.jogadorDaVez == this.jogador1){
            return "\t " + "\t1 | " + "\t2 | " + "\t3 | " + "\t4 | " + "\t5 | " + "\n" +
                    "\t 4 | " + this.tabuleiro[3][0] + " | " + this.tabuleiro[3][1] + " | " + this.tabuleiro[3][2] + " | " + this.tabuleiro[3][3] + " | " + this.tabuleiro[3][4] + "\n" +
                    "\t 3 | " + this.tabuleiro[2][0] + " | " + this.tabuleiro[2][1] + " | " + this.tabuleiro[2][2] + " | " + this.tabuleiro[2][3] + " | " + this.tabuleiro[2][4] + "\n" +
                    "\t 2 | " + this.tabuleiro[1][0] + " | " + this.tabuleiro[1][1] + " | " + this.tabuleiro[1][2] + " | " + this.tabuleiro[1][3] + " | " + this.tabuleiro[1][4] + "\n" +
                    "\t 1 | " + this.tabuleiro[0][0] + " | " + this.tabuleiro[0][1] + " | " + this.tabuleiro[0][2] + " | " + this.tabuleiro[0][3] + " | " + this.tabuleiro[0][4] + "\n";
        } else{
            return "\t " + "\t5 | " + "\t4 | " + "\t3 | " + "\t2 | " + "\t1 | " + "\n" +
                    "\t 1 | " + this.tabuleiro[0][4] + " | " + this.tabuleiro[0][3] + " | " + this.tabuleiro[0][2] + " | " + this.tabuleiro[0][1] + " | " + this.tabuleiro[0][0] + "\n" +
                    "\t 2 | " + this.tabuleiro[1][4] + " | " + this.tabuleiro[1][3] + " | " + this.tabuleiro[1][2] + " | " + this.tabuleiro[1][1] + " | " + this.tabuleiro[1][0] + "\n" +
                    "\t 3 | " + this.tabuleiro[2][4] + " | " + this.tabuleiro[2][3] + " | " + this.tabuleiro[2][2] + " | " + this.tabuleiro[2][1] + " | " + this.tabuleiro[2][0] + "\n" +
                    "\t 4 | " + this.tabuleiro[3][4] + " | " + this.tabuleiro[3][3] + " | " + this.tabuleiro[3][2] + " | " + this.tabuleiro[3][1] + " | " + this.tabuleiro[3][0] + "\n";
        }
    }

    //region getter e setters
    public Jogador getVencedor(){
        return vencedor;
    }
    private void setVencedor(Jogador jogador){
        this.vencedor = jogador;
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao (Jogador jogador, int pontuacao){ //nao parece estar funcionando
        System.out.println("Setando pontuacao");
        if(jogador == this.jogador1){
            this.pontuacao+=pontuacao;
        }else{
            this.pontuacao-=pontuacao;
        }
        if (this.pontuacao >= 10||this.pontuacao<=-10){
            this.setVencedor(jogador);
        }
    }
    public Personagem[][] getTabuleiro() {
        return tabuleiro;
    }
    public Jogador getJogador1() {
        return jogador1;
    }
    public Jogador getJogador2() {
        return jogador2;
    }
    public Jogador getJogadorDaVez() {
        return jogadorDaVez;
    }
    public int getTurno() {
        return turno;
    }
    //endregion
}
