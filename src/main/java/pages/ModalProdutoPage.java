package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ModalProdutoPage {
    private WebDriver driver;

    private By mensagemProdutoAdicionado = By.id("myModalLabel");
    private By descricaoProduto = By.className("product-name");
    private By preco = By.cssSelector("div.modal-body p.product-price");


    public ModalProdutoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterMendagemProdutoAdicionado (){
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));

        return driver.findElement(mensagemProdutoAdicionado).getText();
    }
}
