package clases.services.api.entities;

import clases.services.api.entities.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class respuestaCiudades {


    public pagination pagination;

    public List<Ciudad> data;

    public class pagination{
        int limit;
        int offset;
        int count;
        int total;

        public int getTotal() {
            return total;
        }

    }

    public List<Ciudad> getListaDeVuelos() {
        return data;
    }


}
