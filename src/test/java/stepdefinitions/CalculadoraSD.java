package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraSD {

    List<Integer> numeros;
    int resultado;
    Logger logger;

    @Before
    public void setup(Scenario scenario) {
        numeros = new ArrayList<>();
        logger = LogManager.getLogger();
    }

    @Dado("^que recebo o número (\\d+)$")
    public void queReceboONumero(int numero) {
        numeros.add(numero);
        logger.info("Número " + numero + " armazenado");
    }

    @Quando("^que realizo \"(.*)\"$")
    public void queRealizo(String operacao) throws OperationNotSupportedException {
        switch(operacao.toUpperCase()) {
            case "ADIÇÃO":
            case "ADICAO":
                resultado = numeros.stream().reduce(0, Integer::sum);
                break;
            case "SUBTRAÇÃO":
            case "SUBTRACAO":
                resultado = numeros.stream().reduce(0, (sub, elem) -> sub - elem);
                break;
            default:
                throw new OperationNotSupportedException("A operação " + operacao + " não é suportada");
        }
        logger.info("Operação " + operacao + " selecionada. Resultado calculado é " + resultado);
    }

    @Então("^o resultado é (\\d+)$")
    public void oResultadoE(int resultado) {
        Assert.assertEquals("Resultado não bateu.", resultado, this.resultado);
        logger.error("Resultado ok");
    }
}
