package Pck_Control;

import Pck_Model.PrateleiraModel;
import Pck_Persistencia.PrateleiraPersistencia;
import java.util.ArrayList;

public class PrateleiraControl {

    private PrateleiraPersistencia oPrateleiraPersistencia = new PrateleiraPersistencia();

    public ArrayList<PrateleiraModel> listarPrateleiras() {
        return oPrateleiraPersistencia.listarPrateleiras();
    }

    public void inserirPrateleira(int numero, String tipo, String lugar) {
        PrateleiraModel oPrateleiraModel = new PrateleiraModel();

        oPrateleiraModel.setNumero(numero);
        oPrateleiraModel.setTipo(tipo);
        oPrateleiraModel.setLugar(lugar);

        oPrateleiraPersistencia.inserirPrateleira(oPrateleiraModel);
    }

    public void atualizarPrateleira(int id, int numero, String tipo, String lugar) {
        PrateleiraModel oPrateleiraModel = new PrateleiraModel();

        oPrateleiraModel.setId_prateleira(id);
        oPrateleiraModel.setNumero(numero);
        oPrateleiraModel.setTipo(tipo);
        oPrateleiraModel.setLugar(lugar);

        oPrateleiraPersistencia.atualizarPrateleira(oPrateleiraModel);
    }

    public void removerPrateleira(int id) {
        oPrateleiraPersistencia.removerPrateleira(id);
    }
}