import java.util.Scanner;

public class Main {
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Jogador jogador1;
        Jogador jogador2;

        System.out.println("\tinsira o nome do jogador 1: ");
        String nome = sc.next();
        jogador1 = new Jogador(nome);

        System.out.println("\tinsira o nome do jogador 2: ");
        nome = sc.next();
        jogador2 = new Jogador(nome);

        Partida partida = new Partida(jogador1, jogador2);

        System.out.println("\n\t" + jogador1.nome + "Faça +10 pontos para vencer");
        System.out.println("\n\t" + jogador2.nome + "Faça -10 pontos para vencer");
        do{
            System.out.println("\n\tTurno: " + partida.turno + "\n");
            System.out.println("\t" + partida.jogadorDaVez.nome + " é a sua vez!");
            switch(menuOpcoes()){

                case 1:
                    Personagem cartaSelecionada = selecionarCarta(partida.jogadorDaVez);
                    boolean voltar = false;
                    do {
                        switch (menuCarta(cartaSelecionada)) {
                            case 1:
                                posicionarCarta();
                                break;
                            case 2:
                                System.out.println(cartaSelecionada);
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
                    }while(!voltar);
                    break;
                case 2:
                    sacrificar();
                    break;
                case 3:
                    partida.jogadorDaVez.ComprarCarta();
                    break;
                case 4:
                    partida.jogadorDaVez.mostrarEstatistica();
                    break;
                case 5:
                    partida.mostrarEstatisticas();
            }
        }while(partida.vencedor == null);

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
                """, carta.getClass());
        return sc.nextInt();
    }

}
