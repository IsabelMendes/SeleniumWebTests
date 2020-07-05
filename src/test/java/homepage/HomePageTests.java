package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    ProdutoPage produtoPage;
    String nomeProduto_ProdutoPage;
    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValor(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

        produtoPage = homePage.clicarProduto(indice);

        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();

        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
        assertThat(precoProduto_HomePage, is (precoProduto_ProdutoPage));
    }
    LoginPage loginPage;
    @Test
    public void testLoginComSucesso_UsuarioLogado(){
        //Clicar no botão Sign In
        loginPage = homePage.clicarBotaoSignIn();

        //Preencher usuario e senha
        loginPage.preencherEmail("doralice@teste.com");
        loginPage.preencherPassword("123456");

        //Clicar no botão para logar
        loginPage.clicarBotaoSignIn();

        //Validar o login
        assertThat(homePage.estaLogado("Doralice Mendes"),is (true));

        carregarPaginaInicial();
    }
    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso(){
        String tamanhoProduto = "M";
        String corProduto = "Black";
        int quantidadeProduto = 2;

        // Pre-condição
        //usuario logado
        if(!homePage.estaLogado("Doralice Mendes")){
            testLoginComSucesso_UsuarioLogado();
        }
        //selecionando produto
        testValidarDetalhesDoProduto_DescricaoEValor();

        //selecionar tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropdownSize(tamanhoProduto);
        listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //selecionar cor
        produtoPage.selecionarCorPreta(corProduto);

        //selecionar quantidade
        produtoPage.alterarQuantidade(quantidadeProduto);

        //adicionar no carrinho
        ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddtoCard();

        //Validações
        //assertThat(modalProdutoPage.obterMensagemProdutoAdicionado(), is ("Product successfully added to your shopping cart"));
        assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));

        assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is (nomeProduto_ProdutoPage.toUpperCase()));

        String precoProdutoString = modalProdutoPage.obterPrecoProduto();
        precoProdutoString = precoProdutoString.replace("$","");
        Double precoProduto = Double.parseDouble(precoProdutoString);

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPage.obterCorProduto(), is (corProduto));
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));

        String subtotalString = modalProdutoPage.obterSubtotal();
        subtotalString = subtotalString.replace("$", "");
        Double subtotal = Double.parseDouble(subtotalString);

        Double subtotalCalculado = quantidadeProduto * precoProduto;
        assertThat(subtotal, is (subtotalCalculado));
    }


}
