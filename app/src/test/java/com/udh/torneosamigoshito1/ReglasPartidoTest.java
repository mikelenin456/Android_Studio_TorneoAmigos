package com.udh.torneosamigoshito1;

import com.udh.torneosamigoshito1.model.ReglasPartido;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReglasPartidoTest {

    @Test
    public void exigeAlMenosDosEquipos() {
        assertFalse(ReglasPartido.hayEquiposSuficientes(0));
        assertFalse(ReglasPartido.hayEquiposSuficientes(1));
        assertTrue(ReglasPartido.hayEquiposSuficientes(2));
    }

    @Test
    public void localYVisitanteDebenSerDistintos() {
        assertFalse(ReglasPartido.sonEquiposDistintos(1, 1));
        assertTrue(ReglasPartido.sonEquiposDistintos(1, 2));
    }
}