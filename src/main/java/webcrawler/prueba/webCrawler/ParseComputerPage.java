package webcrawler.prueba.webCrawler;

import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.BrandDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Brand;
import webcrawler.prueba.model.Transaction;

import java.io.IOException;

@Service
public class ParseComputerPage {

    private BrandDao brandDao;
    private TransactionDao transactionDao;
    //
    //private BrandDto brandDto;
    //private Transaction transaction;

    @Autowired
    public ParseComputerPage (BrandDao brandDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.transactionDao = transactionDao;
        //this.brandDto = brandDto;
        //this.transaction = transaction;
    }

    public ParseComputerPage() {

    }

    //Crear marca

    public void listPages(String url, Transaction transaction) throws IOException {
        System.out.println("Ejemplo , obteniendo links de pagina " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements header = doc.select(" div.seen-collection");

        String titulo="";
        for (Element e : header.select("div.article"))
        {
            titulo = e.select("h1.header").text();
            //titulo = e.select("div.byline").text();
            System.out.println(titulo);
        }

        //brandBl
        Brand brand = new Brand();
        brand.setName(titulo);

        brand.setTxId(transaction.getTxId());
        brand.setTxHost(transaction.getTxHost());
        brand.setTxUserId(transaction.getTxUserId());
        brand.setTxDate(transaction.getTxDate());
        brand.setStatus(1);
        brandDao.create(brand);
        //Integer getLastId = transactionDao.getLastInsertId();
        //brandDto.setBrandId(getLastId);

    }


    /**
    public void listAllPages(String url) throws IOException {
        System.out.println("Ejemplo 1, obteniendo links de paginas de computadoras " + url + "...");

        Document doc = Jsoup.connect(url).timeout(10000).get();
        Elements body = doc.select("ul.products");

        print("\nBody: (%d)", body.size());
        for (Element e : body.select("li"))
        {
            String ranking = e.select("span.nxowoo-box a").attr("href");
            System.out.println(ranking);
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }


    public void listAllPagesExampleTwo(String url) throws IOException {
        System.out.println("Ejemplo 2, obteniendo links de paginas de computadoras " + url + "...");
        Document doc = Jsoup.connect(url).timeout(6000).get();
        Elements body = doc.select("tbody.lister-list");

        int cnt = 0;
        for (Element e : body.select("tr"))
        {

            String ranking = e.select("td.posterColumn span").attr("data-value");
            String img  = e.select("td.posterColumn img").attr("src");
            String titulo = e.select("td.posterColumn img").attr("alt");
            String anio = e.select("td.titleColumn span.secondaryInfo").text();
            System.out.println(titulo);
            System.out.println(ranking);
            System.out.println(anio.replaceAll( "[^\\d]", "" ));
        }
        // Insertar en la DB
        ArrayList<String>  marca = new ArrayList<String>();
        marca.add("marca desde backend");
        System.out.println("marca: "+ marca);
    }

*/

}
