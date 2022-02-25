package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import webcrawler.prueba.bl.ProductBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ComputerPageThree;
import webcrawler.prueba.webCrawler.ComputerPageTwo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/product")
public class ProductApi extends Thread{

    private ProductBl productBl;
    private TransactionBl transactionBl;
    private ComputerPageOne computerPageOne;
    private ComputerPageTwo computerPageTwo;
    private ComputerPageThree computerPageThree;



    @Autowired
    public ProductApi (ProductBl productBl, ComputerPageOne computerPageOne,ComputerPageTwo computerPageTwo, ComputerPageThree computerPageThree, TransactionBl transactionBl){
        this.productBl = productBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.computerPageThree = computerPageThree;
        this.transactionBl = transactionBl;
    }
/**
    //listado de productos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProducts(HttpServletRequest request){
        return productBl.selectProducts();
    }
*/
/**
    //listado de productos por categoria,
    //JOIN de tabla product con productCategory
    @RequestMapping(path ="/productsByCategory/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProductsByCategory(@PathVariable("categoryId") Integer categoryId, HttpServletRequest request){
        return productBl.selectProductsByCategory(categoryId);
    }
*/
    /**
    // encontrar producto por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productBl.findProductById(id);
    }
    */

    //Crear producto
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createBrand(@RequestBody ProductDto productDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductDto productDtoResponse = productBl.createProduct(productDto, transaction);
        return productDtoResponse;
    }



///////////////////////////
    //COMPUCENTER, LISTA DE PRODUCTOS SIN BASE DE DATOS
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //List<ProductDto>
    public List<ProductDto> selectProducts(HttpServletRequest request)throws IOException {
        //tienda ,CompuCenter,Equipos Laptos
        String urlCompuCenter = "https://compucenter.store/category/23-equipo/77-laptop";

        //dirección producto 4
        String url4="https://compucenter.store/product/2502-equipo-asus-laptop-f512j-vivobook";  //PC4
        //dirección producto 5
        String url5="https://compucenter.store/product/2283-equipo-dell-laptop-inspiron-5593";  //PC5
        //dirección producto 6
        String url6="https://compucenter.store/product/2530-equipo-hp-laptop-15-dy1003ca";  //PC6

        //Creacion del Hilo 1
       //Thread hilo1 = new Thread(computerPageOne);
        //hilo1.start();

        return computerPageOne.productListAllPrueba(urlCompuCenter, url4, url5, url6);
    }
    //FIN


    //Pc.com, Marcas
    @RequestMapping( path = "/productsPc" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProductsPc(HttpServletRequest request)throws IOException {
        //tienda ,CompuCenter,Equipos Laptos
        String urlPcHp = "https://www.pc.com.bo/assets/html/notebooks-hp.html";
        String urlPcDell = "https://www.pc.com.bo/assets/html/notebooks-dell.html";
        String urlPcLenovo = "https://www.pc.com.bo/assets/html/notebooks-lenovo.html";
        String urlPcAsus ="https://www.pc.com.bo/assets/html/notebooks-asus.html";

        String urlCreativoHp = "https://creativocomputacion.ecwid.com/Notebooks-HP-c10840325";
        String urlCreativoDell = "https://creativocomputacion.ecwid.com/Notebooks-Dell-c10840283";
        String urlCreativoLenovo = "https://creativocomputacion.ecwid.com/Lenovo-Notebooks-c20149225";

        return computerPageOne.productListAllMarcas(urlPcHp, urlPcDell, urlPcLenovo, urlPcAsus,   urlCreativoHp, urlCreativoDell, urlCreativoLenovo);
    }
    //FIN


    //ENCONTRAR PRODUCT ID SIN BASE DE DATOS
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findById(@PathVariable("id") Integer id, HttpServletRequest request)throws IOException{

        //tienda 2, Compucenter
        //Direccion Compucenter Productos
        String urlCompuCenter = "https://compucenter.store/category/23-equipo/77-laptop";

        //dirección producto 4
        String url4="https://compucenter.store/product/2502-equipo-asus-laptop-f512j-vivobook";  //PC4
        //dirección producto 5
        String url5="https://compucenter.store/product/2283-equipo-dell-laptop-inspiron-5593";  //PC5
        //dirección producto 6
        String url6="https://compucenter.store/product/2530-equipo-hp-laptop-15-dy1003ca";  //PC6


        return computerPageOne.findProductById(id, url4, url5, url6);
    }
    //fin

///////////////////////////






/**
    //TIENDA 1,
    // producto 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://www.dismac.com.bo/o85pd.html";  //PC1
        computerPageOne.extractProduct(url, productDto, transaction);
    }


    //producto 2
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        computerPageOne.extractProduct2(url, productDto, transaction);
    }

    //producto 3
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://www.dismac.com.bo/82a2007lm.html";  //Pc3
        computerPageOne.extractProduct3(url, productDto, transaction);
    }

//TIENDA 2,
    // producto 1
    @RequestMapping(path ="/crawler4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler4(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //PC1
        computerPageTwo.extractProduct(url, productDto, transaction);
    }

    // producto 2
    @RequestMapping(path ="/crawler5", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler5(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";  //PC2
        computerPageTwo.extractProduct2(url, productDto, transaction);
    }

    // producto 3
    @RequestMapping(path ="/crawler6", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler6(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";  //PC3
        computerPageTwo.extractProduct3(url, productDto, transaction);
    }


//TIENDA 3,
    // producto 1
    @RequestMapping(path ="/crawler7", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler7(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://www.multilaptops.net/store2/191";  //PC1
        computerPageThree.extractProduct(url, productDto, transaction);
    }

    // producto 2
    @RequestMapping(path ="/crawler8", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler8(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://www.multilaptops.net/store2/194";  //PC2
        computerPageThree.extractProduct2(url, productDto, transaction);
    }

    // producto 3
    @RequestMapping(path ="/crawler9", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler9(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://www.multilaptops.net/store2/181";  //PC3
        computerPageThree.extractProduct3(url, productDto, transaction);
    }
*/


/**
//ACTUALIZACIONES
    //TIENDA 1,
    //producto 1
    @RequestMapping(path ="/updateCrawler1", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler1(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://www.dismac.com.bo/o85pd.html";  //PC1
        ProductDto Response = computerPageOne.updateProduct(url, productDto, transaction);
        return Response;
    }

// producto 2
    @RequestMapping(path ="/updateCrawler2", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler2(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        ProductDto Response = computerPageOne.updateProduct2(url, productDto, transaction);
        return Response;
    }

// producto 3
    @RequestMapping(path ="/updateCrawler3", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler3(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://www.dismac.com.bo/82a2007lm.html";  //Pc3
        ProductDto Response = computerPageOne.updateProduct3(url, productDto, transaction);
        return Response;
    }

//TIENDA 2,
// producto 1
    @RequestMapping(path ="/updateCrawler4", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler4(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //PC1
        ProductDto Response = computerPageTwo.updateProduct(url, productDto, transaction);
        return Response;
    }

    // producto 2
    @RequestMapping(path ="/updateCrawler5", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler5(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";  //PC2
        ProductDto Response = computerPageTwo.updateProduct2(url, productDto, transaction);
        return Response;
    }

    // producto 3
    @RequestMapping(path ="/updateCrawler6", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler6(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";  //PC3
        ProductDto Response = computerPageTwo.updateProduct3(url, productDto, transaction);
        return Response;
    }


//TIENDA 3,
// producto 1
    @RequestMapping(path ="/updateCrawler7", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler7(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://www.multilaptops.net/store2/191";  //PC1
        ProductDto Response = computerPageThree.updateProduct(url, productDto, transaction);
        return Response;
    }

// producto 2
    @RequestMapping(path ="/updateCrawler8", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler8(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://www.multilaptops.net/store2/194";  //PC2
        ProductDto Response = computerPageThree.updateProduct2(url, productDto, transaction);
        return Response;
    }

// producto 3
    @RequestMapping(path ="/updateCrawler9", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateCrawler9(@RequestBody ProductDto productDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://www.multilaptops.net/store2/181";  //PC3
        ProductDto Response = computerPageThree.updateProduct3(url, productDto, transaction);
        return Response;
    }
*/


}
