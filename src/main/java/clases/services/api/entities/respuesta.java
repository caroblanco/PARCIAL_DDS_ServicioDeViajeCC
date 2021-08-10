package clases.services.api.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class respuesta {

    public Paginacion paginacion;

    public List<VueloApi> data;

    public class Paginacion{
        int limit;
        int offset;
        int count;
        int total;
    }

        public List<VueloApi> getListaDeVuelos() {
            return data;
        }

}
