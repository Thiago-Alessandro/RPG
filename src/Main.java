import java.util.Scanner;

public class Main {
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Jogador jogador1;
        Jogador jogador2;

        System.out.println("\tInsira o nome do jogador 1: ");
        String nome = sc.next();
        jogador1 = new Jogador(nome);

        System.out.println("\tInsira o nome do jogador 2: ");
        nome = sc.next();
        jogador2 = new Jogador(nome);

        Partida partida = new Partida(jogador1, jogador2);

        System.out.println("\n\t" + jogador1.nome + " faça +10 pontos para vencer");
        System.out.println("\n\t" + jogador2.nome + " faça -10 pontos para vencer");
        do{
            System.out.println("\n\tTurno: " + partida.turno + "\n");
            System.out.println("\t" + partida.jogadorDaVez.nome + " é a sua vez!\n");
            boolean terminarTurno = false;
            partida.personagensAtacar();
            partida.trazerPersonagensParaFrente();
            partida.personagensRecuperarDefesa();
            do {
                System.out.println(partida.mostrarTabuleiro());
                switch (menuOpcoes()) {

                    case 1:
                        Personagem cartaSelecionada = selecionarCarta(partida.jogadorDaVez);
                        boolean voltar = false;
                        do {
                            switch (menuCarta(cartaSelecionada)) {
                                case 1:
                                    System.out.println(partida.mostrarTabuleiro());
                                    posicionarCarta(partida, cartaSelecionada);
                                    voltar = true;
                                    terminarTurno = true; //tirar da =qui e coolocar opcao terminar turno
                                    break;
                                case 2:
                                    System.out.println(cartaSelecionada.mostrarDetalhes());
                                    break;
                                case 3:
                                    System.out.println(cartaSelecionada.getDescricao());
                                    break;
                                case 4:
                                    System.out.println(cartaSelecionada.getEfeitosCausados());
                                    break;
                                case 5:
                                    System.out.println(cartaSelecionada.getEfeitosSofridos());
                                    break;
                                case 0:
                                    voltar = true;
                                    break;
                                default:
                                    System.out.println("\tInsira uma opção válida!");
                                    break;
                            }
                        } while (!voltar);
                        break;
                    case 2:
                        sacrificar(partida);
                        break;
                    case 3:
                        partida.jogadorDaVez.comprarCarta();
                        break;
                    case 4:
                        System.out.println(partida.jogadorDaVez.mostrarEstatisticas());
                        break;
                    case 5:
                        System.out.println(partida.mostrarEstatisticas());
                        break;
                    case 6:
                        partida.jogadorDaVez.totalRecursos++;
                        terminarTurno = true;
                        break;
                }
            }while(!terminarTurno);
            partida.alternarJogadorDaVez();
            //cada 2 turnos sem contar o primneiro adiciona um guerreiro
            if(partida.turno != 1 && partida.turno % 2 == 1){
                partida.jogadorDaVez.cartasDeck.add(new Guerreiro());
            }
        }while(partida.vencedor == null);
        System.out.println("Parabéns " + partida.vencedor.nome + "! Você ganhou!");
    }

    public static int menuOpcoes(){
        System.out.println("""
                    O que deseja fazer
                1 - Selecionar carta na mão
                2 - Sacrificar Personagem
                3 - Comprar cartas
                4 - Ver minhas estatísticas
                5 - Ver estatísticas da partida
                """);
        return sc.nextInt();
    }

    public static Personagem selecionarCarta(Jogador jogador){
        Personagem cartaSelecionada;
        do{
            System.out.println("\n\tSuas cartas: " + jogador.mostarCartasMao());
            int indiceDaCarta = sc.nextInt();
            cartaSelecionada = jogador.selecionarCartaDaMao(indiceDaCarta);
            if(cartaSelecionada==null){
                System.out.println("\tInsira um indice de carta valido!");
            }
        }while(cartaSelecionada==null);
        return cartaSelecionada;
    }

    public static int menuCarta(Personagem carta){
        System.out.printf("""
                    %s
                1 - Posicionar carta
                2 - Detalhes da carta
                3 - Ver descrição
                4 - Ver efeitos
                5 - Ver efeitos sofridos
                0 - Voltar
                \n
                """, carta.nome);
        return sc.nextInt();
    }

    public static void posicionarCarta(Partida partida, Personagem carta){
        int coluna;
        do {
            System.out.println("Em qual coluna deseja posicionar sua carta");
            coluna = sc.nextInt() -1;
            if (coluna < 1 || coluna > 5) {
                System.out.println("Insira uma coluna valida");
            } else if(partida.tabuleiro[0][coluna] != null){
                System.out.println("Insira uma posica que esteja livre!");
            }
        }while(coluna < 1 || coluna > 5 || partida.tabuleiro[0][coluna] != null);
        partida.tabuleiro[0][coluna] = carta;
        carta.x = coluna;
        carta.y = 0;
        carta.jogador = partida.jogadorDaVez; //da p tirar isso daqui no futuro e colocar qnd as cartas forem atribuidas ao jogador
        partida.jogadorDaVez.cartasNaMao.remove(carta);
        partida.jogadorDaVez.totalRecursos--;

    }

    public static void sacrificar(Partida partida){
        partida.mostrarTabuleiro();
        int linha;
        int coluna;
        do {
            do {
                System.out.println("Deseja sacrificar o personagem de qual linha?");
                linha = sc.nextInt();//avisar o cara se estiveer errado
                if(linha != 1 && linha != 2){
                    System.out.println("Selecione uma linha em que possa haver um personagem seu!");
                }
            } while (linha != 1 && linha != 2);
            do {
                System.out.println("Deseja sacrificar o personagem de qual coluna?");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("Insira uma coluna valida");
                }
            }while(coluna < 1 || coluna > 5);
            if (partida.tabuleiro[coluna-1][linha-1] == null) {
                System.out.println("Você precisa selecionar uma posição que possua um personagem!");
            }else{
                partida.tabuleiro[coluna-1][linha-1].sacrificar(partida);
            }
        }while(partida.tabuleiro[coluna-1][linha-1] == null);
    }
}
