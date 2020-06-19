package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList();

    private By produtos = By.className("product-description");
    private By textoProdutosNoCarrinho = By.className("cart-products-count");
    private By descricaoDosProdutos = By.cssSelector(".product-description a");
    private By precoDosProdutos = By.className("price");
    private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");

    public HomePage(WebDriver driver){

        this.driver = driver;
    }
    public int contarProdutos(){
        carregarListaProdutos();
        return listaProdutos.size();
    }
    private void carregarListaProdutos (){
        listaProdutos = driver.findElements(produtos);

    }
    public int obterQuantidadeProdutosNoCarrinho(){
        String quantidadeProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText();
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(","");
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")","");

        int qtdProdutosNoCarriho = Integer.parseInt(quantidadeProdutosNoCarrinho);

        return qtdProdutosNoCarriho;
    }
    public String obterNomeProduto(int indice){
        return driver.findElements(descricaoDosProdutos).get(indice).getText();
    }
    public String obterPrecoProduto(int indice){
        return driver.findElements(precoDosProdutos).get(indice).getText();

    }
    public ProdutoPage clicarProduto(int indice){
        driver.findElements(descricaoDosProdutos).get(indice).click();
        return new ProdutoPage(driver);
    }
    public LoginPage clicarBotaoSignIn(){
        driver.findElement(botaoSignIn).click();
        return new LoginPage(driver);
    }

}
