package Pck_Control;

import Pck_Model.PrateleiraModel;
import Pck_Persistencia.PrateleiraPersistencia;

public class PrateleiraControl {
    PrateleiraPersistencia oPrateleiraPersistencia = new PrateleiraPersistencia();

    public void inserirPrateleira(int numero, String tipo, String lugar) {
        PrateleiraModel oPrateleiraModel = new PrateleiraModel();

        oPrateleiraModel.setNumero(numero);
        oPrateleiraModel.setTipo(tipo);
        oPrateleiraModel.setLugar(lugar);


        oPrateleiraPersistencia.inserirPrateleira(oPrateleiraModel);
    }
}