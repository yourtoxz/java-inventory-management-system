import java.util.Scanner;
public class Main{
    static Scanner scanner = new Scanner(System.in);

    //Product registration
    static void registerProduct(){

    }
    //Product listing
    static void listProducts(){

    }
    //Product search
    static void findProduct(){

    }
    //Stock update
    static void updateStock(){

    }
    //Report generation
    static void displayReport(){

    }
    //Product removal
    static void removeProduct(){

    }
    public static void main(String[] args){
        String option;
        boolean running = true;
        while(running) {
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
            option = scanner.nextLine();

            switch (option){

                case "1": {
                    registerProduct();
                    break;
                } case "2": {
                    listProducts();
                    break;
                } case "3": {
                    findProduct();
                    break;
                } case "4": {
                    updateStock();
                    break;
                } case "5": {
                    removeProduct();
                    break;
                } case "6": {
                    displayReport();
                    break;
                } case "0": {
                    running = false;
                    System.out.println("encerrando sistema...");
                    break;
                } default: {
                    System.out.println("opcao inexistente.");
                }
            }
        }
    }
}
