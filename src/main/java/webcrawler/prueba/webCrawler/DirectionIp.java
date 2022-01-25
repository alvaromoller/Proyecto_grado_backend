package webcrawler.prueba.webCrawler;

import org.springframework.stereotype.Service;
import webcrawler.prueba.dto.ProductDto;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Service
public class DirectionIp {


    //Direccion IP
    public void obtenerIp() {
        try {
            String hostDismac = "dismac.com.bo";
            String hostCompuCenter = "compucenter.store";
            String hostPc = "pc.com.bo";

            String hostMultiLaptos = "multilaptops.net";
            String hostIntecsa = "intecsa.com.bo";
            String hostEkipos = "ekipos.com";
            String hostTumomo = "tumomo.com";
            String hostSysbol = "sysbol.com";
            String hostCardozo = "cardozobolivia.com";
            String hostHp = "hp.com";
            String hostClasificados = "clasificados.one";

            //Obtencion de la direccion IP de Dismac
            System.out.printf("La dirección Ip de %s es:  %s \n", hostDismac, InetAddress.getByName(hostDismac).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de CompuCenter
            System.out.printf("La dirección Ip de %s es:  %s \n", hostCompuCenter, InetAddress.getByName(hostCompuCenter).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de Pc.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostPc, InetAddress.getByName(hostPc).getHostAddress());
            System.out.println();


            /////////////
            //Obtencion de la direccion IP de multilaptops.net
            System.out.printf("La dirección Ip de %s es:  %s \n", hostMultiLaptos, InetAddress.getByName(hostMultiLaptos).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de intecsa.com.bo
            System.out.printf("La dirección Ip de %s es:  %s \n", hostIntecsa, InetAddress.getByName(hostIntecsa).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de ekipos.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostEkipos, InetAddress.getByName(hostEkipos).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de tumomo.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostTumomo, InetAddress.getByName(hostTumomo).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de sysbol.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostSysbol, InetAddress.getByName(hostSysbol).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de cardozobolivia.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostCardozo, InetAddress.getByName(hostCardozo).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de hp.com
            System.out.printf("La dirección Ip de %s es:  %s \n", hostHp, InetAddress.getByName(hostHp).getHostAddress());
            System.out.println();

            //Obtencion de la direccion IP de clasificados.one
            System.out.printf("La dirección Ip de %s es:  %s \n", hostClasificados, InetAddress.getByName(hostClasificados).getHostAddress());
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error ->" + e.getMessage());
        }

    }



    public String ListaDireccionesIp(){
        //creamos una lista, guardamos el metodo obtenerIp en la lista
        List<String> direccionIp1 = new ArrayList<String>();
        //direccionIp1 = obtenerIp();


        //nueva Lista para almacenar las direcciones ip obtenidas
        List<String> productAll = new ArrayList<String>(); // Lista para guardar todos los productos
        productAll.addAll(direccionIp1);
        return null;

    }




}
