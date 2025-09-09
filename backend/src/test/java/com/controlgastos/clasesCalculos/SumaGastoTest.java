package com.controlgastos.clasesCalculos;
import com.controlgastos.clasesCalculos.SumaGasto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumaGastoTest {
    @Test
    void SumarGastos(){
        SumaGasto sumaGasto = new SumaGasto();
        sumaGasto.sumarGastos(100);
        sumaGasto.sumarGastos(50);
        assertEquals(150,sumaGasto.obtenerSumaGastos());
    }
}
