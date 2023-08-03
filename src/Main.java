import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        static Scanner sc = new Scanner(System.in);

        private static final ArrayList<Jogador> jogadores = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 1; i <= 2; i++) {

            System.out.println("\tInsira o nome do jogador " + i + ": ");
            String nome = sc.next();
            jogadores.add( new Jogador(nome));
        }

        Partida partida = new Partida(jogadores);

        System.out.println("\n\t" + jogadores.get(0).getNome() + " faça +10 pontos para vencer");
        System.out.println("\n\t" + jogadores.get(1).getNome() + " faça -10 pontos para vencer");
        do{
            Jogador jogadorDaVez = partida.getJogadorDaVez();
            System.out.println("\n\tTurno: " + partida.getTurno());
            System.out.println("\tPontuação: " + partida.getPontuacao() + "\n");
            System.out.println("\t" + jogadorDaVez.getNome() + " é a sua vez!\n");
            boolean primeiraJogada = true;
            boolean terminarTurno = false;
            partida.personagensAtacar(partida);
            do {
                System.out.println(partida.mostrarTabuleiro());
                switch (menuOpcoes()) {
                    case 1 -> {
                        Personagem cartaSelecionada = selecionarCarta(jogadorDaVez);
                        if (cartaSelecionada != null) {
                            boolean voltar = false;

                            do {
                                switch (menuCarta(cartaSelecionada)) {
                                    case 1 -> {
                                        if (jogadorDaVez.getTotalRecursos() - cartaSelecionada.getCusto() >= 0) {
                                            System.out.println(partida.mostrarTabuleiro());
                                            posicionarCarta(partida, cartaSelecionada);
                                            primeiraJogada = false;
                                            voltar = true;
                                        } else {
                                            System.out.println("\tVocê não possui recursos suficientes!\n");
                                        }
                                    }
                                    case 2 -> System.out.println(cartaSelecionada.mostrarDetalhes());
                                    case 3 -> System.out.println(cartaSelecionada.getDescricao());
                                    case 4 -> {
                                        int indice;
                                        do {
                                            System.out.println(cartaSelecionada.mostrarEfeitosCausados());
                                            if(cartaSelecionada.getListaEfeitosCausados()!=null) {
                                            indice = sc.nextInt();
                                            if (indice != 0) {
                                                    if (indice > cartaSelecionada.getListaEfeitosCausados().size() ||
                                                            indice < 0) {
                                                        System.out.println("\tInsira uma opcao válida\n");
                                                    } else {
                                                        System.out.println(cartaSelecionada.getListaEfeitosCausados().get(indice - 1));
                                                    }
                                                }
                                            } else {
                                                indice = 0;
                                            }
                                        }while(indice != 0);
                                    }
                                    case 5 ->
                                    {
                                        int indice;
                                        do {
                                            System.out.println(cartaSelecionada.mostrarEfeitosSofridos());
                                            if(cartaSelecionada.getListaEfeitosSofridos()!= null) {
                                            indice = sc.nextInt();
                                            if (indice != 0) {
                                                    if (indice > cartaSelecionada.getListaEfeitosSofridos().size() ||
                                                            indice < 0) {
                                                        System.out.println("\tInsira uma opcao válida\n");
                                                    } else {
                                                        System.out.println(cartaSelecionada.getEfeitosSofridos().get(indice - 1));
                                                    }
                                                }
                                            } else {
                                                indice = 0;
                                            }
                                        }while(indice != 0);
                                    }
                                    case 0 -> voltar = true;
                                    default -> System.out.println("\tInsira uma opção válida!\n");
                                }
                            } while (!voltar);
                        } else {
                            System.out.println("Você não possui cartas na mão, compre uma!");
                        }
                    }
                    case 2 -> {
                        primeiraJogada = !sacrificarPersonagem(partida);
                    }
                    case 3 -> {
                        if (jogadorDaVez.getCartasDeck().size() > 0) {
                            Personagem cartaComprada = jogadorDaVez.comprarCarta();
                            if (cartaComprada != null) {
                                System.out.println("\tVocê comprou: " + cartaComprada.getNome() + "!");
                                System.out.println("\tSeus recursos agora são: " + jogadorDaVez.getTotalRecursos() + "\n");
                                System.out.println("\tSeu baralho agora tem: " + jogadorDaVez.getCartasDeck().size() + " cartas\n");
                                primeiraJogada = false;
                            } else {
                                System.out.println("""
                                        \tVocê não tem recursos suficientes!
                                         \tTente sacrificar um personagem ou procurar recursos
                                        """);
                            }
                        } else {
                            System.out.println("""
                                    \tOps parece que acabaram suas cartas
                                    \tEspere por um guerreiro corajoso que se junte à batalha!
                                    """);
                        }
                    }
                    case 4 -> System.out.println(jogadorDaVez.mostrarEstatisticas());
                    case 5 -> System.out.println(partida.mostrarEstatisticas());
                    case 6 -> {
                        if (primeiraJogada) {
                            partida.getJogadorDaVez().setTotalRecursos(partida.getJogadorDaVez().getTotalRecursos()+1);
                            terminarTurno = true;
                        } else {
                            System.out.println("\tVocê só pode procurar recursos na primeira jogada de cada turno!\n");
                        }
                    }
                    case 0 -> {
                        if (primeiraJogada) {
                            System.out.println("\tTente realizar uma jogada ou gastar seu turno procurando recursos!\n");
                        } else {
                            terminarTurno = true;
                        }
                    }
                    default -> System.out.println("\nInsira uma opção válida!\n");
                }
            }while(!terminarTurno);
            partida.alternarJogadorDaVez();
            //cada 2 turnos sem contar o primneiro adiciona um guerreiro
            if(partida.getTurno() != 1 && partida.getTurno() % 2 == 1){
                ArrayList<Personagem> maoAtualizada = jogadorDaVez.getCartasNaMao();
                maoAtualizada.add(new Guerreiro(jogadorDaVez));
                jogadorDaVez.setCartasNaMao(maoAtualizada);
                System.out.println("\tUm guerreiro se juntou à batalha!\n");
            }
        }while(partida.getVencedor() == null);
        System.out.println("\tParabéns " + partida.getVencedor().getNome() + "! Você ganhou!\n");
    }

    private static int menuOpcoes(){
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

    private static Personagem selecionarCarta(Jogador jogador){
        if(jogador.getCartasNaMao().size()>0) {
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

    private static int menuCarta(Personagem carta){
        System.out.printf("""
                    %s
                1 - Posicionar carta
                2 - Detalhes da carta
                3 - Ver descrição
                4 - Ver efeitos
                5 - Ver efeitos sofridos
                0 - Voltar
                \n
                """, carta.getNome());
        return sc.nextInt();
    }

    private static void posicionarCarta(Partida partida, Personagem carta){
        int coluna;
        int linha = 3;
        if(partida.getJogador1() == partida.getJogadorDaVez()) {
            linha = 0;
        }
            do {
                System.out.println("\tEm qual coluna deseja posicionar sua carta");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("\tInsira uma coluna valida");
                } else if (partida.getTabuleiro()[linha][coluna - 1] != null) {
                    System.out.println("\tInsira uma posica que esteja livre!\n");
                }
            } while (coluna < 1 || coluna > 5 || partida.getTabuleiro()[linha][coluna - 1] != null);
            partida.getTabuleiro()[linha][coluna-1] = carta;
            carta.setY(linha);

//       } else{
//            do {
//                System.out.println("\tEm qual coluna deseja posicionar sua carta");
//                coluna = sc.nextInt();
//                if (coluna < 1 || coluna > 5) {
//                    System.out.println("\tInsira uma coluna valida");
//                } else if (partida.getTabuleiro()[3][coluna - 1] != null) {
//                    System.out.println("\tInsira uma posica que esteja livre!\n");
//                }
//            } while (coluna < 1 || coluna > 5 || partida.getTabuleiro()[3][coluna - 1] != null);
//            partida.getTabuleiro()[3][coluna-1] = carta;
//            carta.setY(3);
//        }
        carta.setX(coluna-1);
        carta.setJogador(partida.getJogadorDaVez()); //da p tirar isso daqui no futuro e colocar qnd as cartas forem atribuidas ao jogador
        ArrayList<Personagem> arrayAtualizaddo = partida.getJogadorDaVez().getCartasNaMao();
        arrayAtualizaddo.remove(carta);
        partida.getJogadorDaVez().setCartasNaMao(arrayAtualizaddo);
        partida.getJogadorDaVez().setTotalRecursos(partida.getJogadorDaVez().getTotalRecursos() - carta.getCusto());

    }

    private static boolean verificaLinhaInvalida(Partida partida, int linha){
        return partida.getJogadorDaVez() == partida.getJogador1() && linha != 1 && linha != 2 ||
                partida.getJogadorDaVez() == partida.getJogador2() && linha != 3 && linha != 4;
    }

    private static boolean sacrificarPersonagem(Partida partida){
        int linha;
        int coluna;
        do {
            System.out.println(partida.mostrarTabuleiro());
            do {
                System.out.println("\tDeseja sacrificar o personagem de qual linha?");
                linha = sc.nextInt();
                if(verificaLinhaInvalida(partida, linha)){
                    System.out.println("\tSelecione uma linha em que possa haver um personagem seu!");
                }
            } while (verificaLinhaInvalida(partida, linha));
            do {
                System.out.println("\tDeseja sacrificar o personagem de qual coluna?");
                coluna = sc.nextInt();
                if (coluna < 1 || coluna > 5) {
                    System.out.println("\tInsira uma coluna valida\n");
                }
            }while(coluna < 1 || coluna > 5);
            if (partida.getTabuleiro()[linha-1][coluna-1] == null) {
                System.out.println("\tVocê precisa selecionar uma posição que possua um personagem!\n");
                int opcao;
                do {
                    System.out.println("\tDeseja cancelar o sacrificio?\n\t1 - Não\n\t2 - Sim");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1 -> {}
                        case 2 -> {
                            return false;
                        }
                        default -> System.out.println("\tInsira uma opção válida!\n");
                    }
                }while(opcao != 1);
            }else{
                partida.getTabuleiro()[linha-1][coluna-1].sacrificar(partida);
                System.out.println("\tVocê conseguiu +1 recurso(s)!\n");
                return true;
            }
        }while(partida.getTabuleiro()[linha-1][coluna-1] == null);
        return false;
    }
}
