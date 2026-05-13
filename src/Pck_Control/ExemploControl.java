package Pck_Control;

import Pck_Model.ExemploModel;
import Pck_Persistencia.ExemploPersistencia;

public class ExemploControl {

    /* Cria um objeto da classe de persistência.
       Esse objeto será usado para enviar os dados ao banco. */
    ExemploPersistencia oExemploPersistencia = new ExemploPersistencia();

    /* Método chamado pela View quando o botão Inserir for pressionado.
       Ele recebe o texto digitado na tela. */
    public void inserirExemplo(String sNome) {

        /* Cria um novo objeto Model.
           Esse objeto irá transportar os dados até a camada de persistência. */
        ExemploModel oExemploModel = new ExemploModel();

        /* Coloca no Model o nome digitado na tela. */
        oExemploModel.setA01_nome(sNome);

        /* Envia o Model para a persistência inserir no banco. */
        oExemploPersistencia.inserirExemplo(oExemploModel);
    }
}