public class Produto {
    private String nome;
    private int codigo;
    private double preco;
    private int quantidade;
    // construtor
    public Produto(String nome, int codigo, double preco, int quantidade){
        if (nome.isEmpty()){
            System.out.println("o nome não pode ser vazio.");
        }
        /*this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;K
        this.quantidade = quantidade;*/
    }
    //  SESSÃ0 GETTERS
    public String getNome() {
        return this.nome;
    } public int getCodigo(){
        return this.codigo;
    }
    public double getPreco(){
        return this.preco;
    }public int getQuantidade(){
        return this.quantidade;
    }
    // SESSÃO SETTERS
    public void setNome(String newname){
        if(newname.isBlank()){
            System.out.println("não deixe o nome vazio.");
        }else{
            this.nome = newname;
        }
    }public void setCodigo(int newcodigo){
        if(newcodigo == codigo){
            System.out.println("codigo já registrado. insira outro.");
        }else{
            this.codigo = newcodigo;
        }

    }
    public void setPreco(double newpreco){
        if(newpreco < 0){
            System.out.println("insira um preco válido.");
        }else{
            this.preco = newpreco;
        }
    }public void setQuantidade(int newquantidade){
        if(newquantidade < 0){
            System.out.println("quantidade inválida.");
        }else{
            this.quantidade = newquantidade;
        }

    }
    // EXIBIÇÃO TOTAL
    public void exibirPROD(){
        System.out.println("========= INFORMAÇÕES DO PRODUTO =========");
        System.out.println();
        System.out.println("Nome: " + getNome());
        System.out.println("Código: " + getCodigo());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Quantidade: " + getQuantidade() + " unidades");
    }

}
