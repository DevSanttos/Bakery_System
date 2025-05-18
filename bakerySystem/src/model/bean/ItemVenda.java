package model.bean;

public class ItemVenda {

    private Long idItemVenda;
    private Venda venda;
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemVenda() {}

    public ItemVenda(Long idItemVenda, Venda venda, Produto produto, int quantidade) {
        this.idItemVenda = idItemVenda;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco(); // captura o preço no momento da venda
    }

    public Long getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(Long idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "idItemVenda=" + idItemVenda +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
