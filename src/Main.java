import java.util.Scanner;
public class Main{
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        String op;
        boolean a = true;
        while(a) {
            System.out.println("===== GESTÂO DE ESTOQUE ====");
            System.out.println();
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produto");
            System.out.println("3 - Buscar Produto");
            System.out.println("4 - Atualizar Estoque");
            System.out.println("5 - Remover Produto");
            System.out.println("6 - Exibir Relatório");
            System.out.println("0 - Sair");
            System.out.println();
            System.out.println("Escolha uma Opção: ");
            op = input.nextLine();

            switch (op){

                case "1": {
                    cadastrarProd();
                } case "2": {
                    listarProd();
                } case "3": {
                    buscarProd();
                } case "4": {
                    atualizarES();
                } case "5": {
                    removerProd();
                } case "6": {
                    exibirRE();
                } case "0": {
                    a = false;
                    System.out.println("encerrando sistema...");
                } default -> {
                    System.out.println("opcao inexistente.");
                }
            }
        }
    }
}
