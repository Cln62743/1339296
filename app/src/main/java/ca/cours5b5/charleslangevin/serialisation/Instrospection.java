package ca.cours5b5.charleslangevin.serialisation;

import java.util.List;
import ca.cours5b5.charleslangevin.exceptions.ErreurInstrospection;

public class Instrospection {

    // TODO Finir la classe
    private static final List<String> PAQUETS = null;

    public static <T> Class<T> trouverClasse(String nomClasse) throws ErreurInstrospection{
        Class classReturn = nomClasse.getClass();

        return classReturn;
    }
}
