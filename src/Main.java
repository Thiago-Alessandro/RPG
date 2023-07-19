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
            System.out.println("\n\tTurno: " + partida.turno);
            System.out.println("\tPontuação: " + partida.pontuacao + "\n");
            System.out.println("\t" + partida.jogadorDaVez.nome + " é a sua vez!\n");
            boolean primeiraJogada = true;
            boolean terminarTurno = false;
            partida.personagensAtacar(partida);
            partida.trazerPersonagensParaFrente();
            partida.personagensRecuperarDefesa();
            do {
                System.out.println(partida.mostrarTabuleiro());
                switch (menuOpcoes()) {
                    case 1:
                        Personagem cartaSelecionada = selecionarCarta(partida.jogadorDaVez);
                        if(cartaSelecionada != null) {
                            boolean voltar = false;

                            do {
                                switch (menuCarta(cartaSelecionada)) {
                                    case 1:
                                        if (partida.jogadorDaVez.totalRecursos - cartaSelecionada.custo >= 0) {
                                            System.out.println(partida.mostrarTabuleiro());
                                            posicionarCarta(partida, cartaSelecionada);
                                            primeiraJogada = false;
                                            voltar = true;
                                        } else {
                                            System.out.println("\tVocê não possui recursos suficientes!\n");
                                        }
                                        break;
                                    case 2:
                                        System.out.println(cartaSelecionada.mostrarDetalhes());
                                        break;
                                    case 3:
                                        System.out.println(cartaSelecionada.getDescricao());
                                        break;
                                    case 4:
                                        System.out.println(cartaSelecionada.mostrarEfeitosCausados());
                                        break;
                                    case 5:
                                        System.out.println(cartaSelecionada.mostrarEfeitosSofridos());
                                        break;
                                    case 0:
                                        voltar = true;
                                        break;
                                    default:
                                        System.out.println("\tInsira uma opção válida!\n");
                                        break;
                                }
                            } while (!voltar);
                        } else {
                            System.out.println("Você não possui cartas na mão, compre uma!");
                        }
                        break;
                    case 2:
                        if(sacrificarPersonagem(partida)){
                            primeiraJogada = false;
                        }
                        break;
                    case 3:
                        if(partida.jogadorDaVez.cartasDeck.size()>0) {
                            Personagem cartaComprada = partida.jogadorDaVez.comprarCarta();
                            if (cartaComprada != null) {
                                System.out.println("\tVocê comprou: " + cartaComprada.nome + "!");
                                System.out.println("\tSeus recursos agora são: " + partida.jogadorDaVez.totalRecursos + "\n");
                                System.out.println("\tSeu baralho agora tem: " + partida.jogadorDaVez.cartasDeck.size() + " cartas\n");
                                primeiraJogada = false;
                            } else {
                                System.out.println("\tVocê não tem recursos suficientes!\n" +
                                        " \tTente sacrificar um personagem ou procurar recursos\n");
                            }
                        } else {
                            System.out.println("\tOps parece que acabaram suas cartas\n" +
                                    "\tEspere por um guerreiro corajoso que se junte à batalha!\n");
                        }
                        break;
                    case 4:
                        System.out.println(partida.jogadorDaVez.mostrarEstatisticas());
                        break;
                    case 5:
                        System.out.println(partida.mostrarEstatisticas());
                        break;
                    case 6:
                        if(primeiraJogada){
                            partida.jogadorDaVez.totalRecursos++;
                            terminarTurno = true;
                        } else {
                            System.out.println("\tVocê só pode procurar recursos na primeira jogada de cada turno!\n");
                        }
                        break;
                    case 0:
                        if(primeiraJogada){
                            System.out.println("\tTente realizar uma jogada ou gastar seu turno procurando recursos!\n");
                        }else{
                            terminarTurno = true;
                        }
                        break;
                    default:
                        System.out.println("\nInsira uma opção válida!\n");
                        break;
                }
            }while(!terminarTurno);
            partida.alternarJogadorDaVez();
            //cada 2 turnos sem contar o primneiro adiciona um guerreiro
            if(partida.turno != 1 && partida.turno % 2 == 1){
                partida.jogadorDaVez.cartasDeck.add(new Guerreiro(partida.jogadorDaVez));
            }
        }while(partida.vencedor == null);
        System.out.println("\tParabéns " + partida.vencedor.nome + "! Você ganhou!\n");
    }

    public static int menuOpcoes(){
        System.out.println("""
                    O que deseja fazer
                1 - Selecionar carta na mão
                2 - Sacrificar Personagem
                3 - Comprar cartas
                4 - Ver minhas estatísticas
                5 - Ver estatísticas da partida
                6 - Procurar recursos
                0 - Terminar turno
                """);
        return sc.nextInt();
    }

    public static Personagem selecionarCarta(Jogador jogador){
        if(jogador.cartasNaMao.size()>0) {
            Personagem cartaSelecionada;
            do {
                System.out.println("\n\tSuas cartas: " + jogador.mostarCartasMao());
                int indiceDaCarta = sc.nextInt();
                cartaSelecionada = jogador.selecionarCartaDaMao(indiceDaCarta - 1);
                if (cartaSelecionada == null) {
                    System.out.println("\tInsira um indice de carta valido!\n");
                }
            } while (cartaSelecionada == null);
            return cartaSelecionada;
        }
        return null;
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
        if(partida.jogador1 == partida.jogadorDaVez) {
            do {
                System.out.println("\tEm qual coluna deseja posicionar sua carta");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("\tInsira uma coluna valida");
                } else if (partida.tabuleiro[0][coluna - 1] != null) {
                    System.out.println("\tInsira uma posica que esteja livre!\n");
                }
            } while (coluna < 1 || coluna > 5 || partida.tabuleiro[0][coluna - 1] != null);
            partida.tabuleiro[0][coluna-1] = carta;
            carta.y = 0;
        } else{
            do {
                System.out.println("\tEm qual coluna deseja posicionar sua carta");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("\tInsira uma coluna valida");
                } else if (partida.tabuleiro[3][coluna - 1] != null) {
                    System.out.println("\tInsira uma posica que esteja livre!\n");
                }
            } while (coluna < 1 || coluna > 5 || partida.tabuleiro[3][coluna - 1] != null);
            partida.tabuleiro[3][coluna-1] = carta;
            carta.y = 3;
        }
        carta.x = coluna-1;
        carta.jogador = partida.jogadorDaVez; //da p tirar isso daqui no futuro e colocar qnd as cartas forem atribuidas ao jogador
        partida.jogadorDaVez.cartasNaMao.remove(carta);
        partida.jogadorDaVez.totalRecursos-=carta.custo;

    }

    public static boolean sacrificarPersonagem(Partida partida){
        int linha;
        int coluna;
        boolean sair = false;
        do {
            System.out.println(partida.mostrarTabuleiro());
            do {
                System.out.println("\tDeseja sacrificar o personagem de qual linha?");
                linha = sc.nextInt();
                if(partida.jogadorDaVez == partida.jogador1 && linha != 1 && linha != 2 ||
                   partida.jogadorDaVez == partida.jogador2 && linha != 3 && linha != 4){
                    System.out.println("\tSelecione uma linha em que possa haver um personagem seu!");
                }
            } while (partida.jogadorDaVez == partida.jogador1 && linha != 1 && linha != 2 ||
                     partida.jogadorDaVez == partida.jogador2 && linha != 3 && linha != 4);
            do {
                System.out.println("\tDeseja sacrificar o personagem de qual coluna?");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("\tInsira uma coluna valida\n");
                }
            }while(coluna < 1 || coluna > 5);
            if (partida.tabuleiro[linha-1][coluna-1] == null) {
                System.out.println("\tVocê precisa selecionar uma posição que possua um personagem!\n");
                int opcao;
                do {
                    System.out.println("\tDeseja cancelar o sacrificio?\n\t1 - Não\n\t2 - Sim");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            sair = false;
                            break;
                        case 2:
                            sair = false;
                            break;
                        default:
                            System.out.println("\tInsira uma opção válida!\n");
                            break;
                    }
                }while(opcao != 1 && opcao != 2);
            }else{
                partida.tabuleiro[linha-1][coluna-1].sacrificar(partida);
                System.out.println("\tVocê conseguiu +1 recurso(s)!\n");
                sair = true;
                return true;
            }
        }while(partida.tabuleiro[linha-1][coluna-1] == null && !sair);
        return false;
    }
}
