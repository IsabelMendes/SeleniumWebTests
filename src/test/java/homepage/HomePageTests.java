package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.ProdutoPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests {
    @Test
    public void testContarProdutos_oitoProdutosDiferentes(){
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is (8));
    }
    @Test
    public void testValidarCarrinhoZerado_ZeroItensNoCarrinho(){
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        //System.out.println(produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }
    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValor(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        ProdutoPage produtoPage = homePage.clicarProduto(indice);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
        assertThat(precoProduto_HomePage, is (precoProduto_ProdutoPage));
    }
    @Test
    public void testLoginComSucesso_UsuarioLogado(){
        //Clicar no botão Sign In

        //Preencher usuario e senha

        //Clicar no botão para logar

        //Validar o login

    }
}
