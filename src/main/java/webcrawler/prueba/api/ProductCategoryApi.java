package webcrawler.prueba.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ProductCategoryBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductCategoryDto;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageTwo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/productCategory")
public class ProductCategoryApi {

    private ProductCategoryBl productCategoryBl;
    private TransactionBl transactionBl;

    private ComputerPageTwo computerPageTwo;


    @Autowired
    public ProductCategoryApi (ProductCategoryBl productCategoryBl, TransactionBl transactionBl, ComputerPageTwo computerPageTwo){
        this.productCategoryBl = productCategoryBl;
        this.transactionBl = transactionBl;
        this.computerPageTwo = computerPageTwo;
    }

/**
    //lista de ProductCategory
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductCategoryDto> selectProductCategory(HttpServletRequest request){
        return productCategoryBl.selectProductCategory();
    }
*/
/**
    //encontrar por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productCategoryBl.findProductCategoryById(id);
    }
*/
    //Crear ProductCategory
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDto createProductCategory(@RequestBody ProductCategoryDto productCategoryDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductCategoryDto pcDtoResponse = productCategoryBl.createProductCategory(productCategoryDto, transaction);
        return pcDtoResponse;
    }



///////////////////////////
    //LISTA DE PRODUCTOS SIN BASE DE DATOS
    //Prueba de listado de productos Sin base de datos
/**
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProducts(HttpServletRequest request)throws IOException {
        //dirección producto 1
        String url="https://www.dismac.com.bo/o85pd.html";  //PC1
        //dirección producto 2
        String url2="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        //dirección producto 3
        String url3="https://www.dismac.com.bo/82a2007lm.html";  //Pc3


        //dirección producto 4
        String url4="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //PC4
        // dirección producto 5
        String url5="https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";  //PC5
        //dirección producto 6
        String url6="https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";  //PC6
        //dirección producto 7
        String url7="https://www.multilaptops.net/store2/191";  //PC7
        //dirección producto 8
        String url8="https://www.multilaptops.net/store2/194";  //PC9
        //dirección producto 9
        String url9="https://www.multilaptops.net/store2/181";  //PC9

        return computerPageTwo.productListAll(url, url2, url3, url4, url5, url6, url7, url8, url9);
    }
    //FIN
*/
/**
    //ENCONTRAR PRODUCT ID SIN BASE DE DATOS
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findById(@PathVariable("id") Integer id, HttpServletRequest request)throws IOException{
        //dirección producto 1
        String url="https://www.dismac.com.bo/o85pd.html";  //PC1
        //dirección producto 2
        String url2="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        //dirección producto 3
        String url3="https://www.dismac.com.bo/82a2007lm.html";  //Pc3


        //dirección producto 4
        String url4="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //PC4
        // dirección producto 5
        String url5="https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";  //PC5
        //dirección producto 6
        String url6="https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";  //PC6
        //dirección producto 7
        String url7="https://www.multilaptops.net/store2/191";  //PC7
        //dirección producto 8
        String url8="https://www.multilaptops.net/store2/194";  //PC9
        //dirección producto 9
        String url9="https://www.multilaptops.net/store2/181";  //PC9
        return computerPageTwo.findProductById(id, url, url2, url3, url4, url5, url6, url7, url8, url9);
    }
    //fin
*/
    //listado de productos por categoria,
    //JOIN de tabla product con productCategory
    @RequestMapping(path ="/productsByCategory/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProductsByCategory(@PathVariable("categoryId") Integer categoryId, HttpServletRequest request)throws IOException{
        //dirección producto 1, PC.com
        String urlS1="https://www.pc.com.bo/assets/html/hp15-ef0025wm.html";  //PC1
        //dirección producto 2
        String urlS2="https://www.pc.com.bo/assets/html/hp15-dy1751ms.html";  //Pc2
        //dirección producto 3
        String urlS3="https://www.pc.com.bo/assets/html/hp14-dk1022wm.html";  //Pc3
        //dirección producto 4
        String urlS4="https://www.pc.com.bo/assets/html/hp13-ag0003la.html";  //Pc4

        //Categoria Gamer, compucenter
        //dirección producto 1, +
        String urlG1="https://compucenter.store/product/2049-gamer-dell-portatil-inspiron-gaming-g3-3500";  //PC4
        // dirección producto 2
        String urlG2="https://compucenter.store/product/2570-equipo-msi-gaming-gf65-thin";  //PC5
        //dirección producto 3
        String urlG3="https://compucenter.store/product/2631-equipo-dell-gaming-inspiron-gaming-g5-5510";  //PC6
        // dirección producto 4
        String urlG4="https://compucenter.store/product/2674-gamer-asus-gaming-tuf-516pr-ds77-wh";  //PC5
        //dirección producto 5
        String urlG5="https://compucenter.store/product/teclado-gamer-retroiluminado-marvo-k632-usb";  //PC6
        //Fin categoria gamer

        //dirección producto 7, Pc.com, compucenter
        String url7="https://compucenter.store/product/2578-equipo-dell-laptop-inspiron-5502";  //PC7
        //dirección producto 8
        String url8="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //PC9
        //dirección producto 9
        String url9="https://www.pc.com.bo/assets/html/hp15-cs3073cl.html";  //PC9
        return computerPageTwo.selectProductsByCategory(categoryId,urlS1,urlS2,urlS3,urlS4,  urlG1,urlG2,urlG3, urlG4, urlG5  ,url7,url8,url9);
    }
    //FIN

///////////////////////////




}
