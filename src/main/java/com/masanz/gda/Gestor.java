package com.masanz.gda;

import java.util.*;

/**
 * @author Nombre Apellidos
 */
public class Gestor {

    private TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro;

    public Gestor() {
        registro = new TreeMap<>();
    }

    //region get/setRegistro para los tests
    public void setRegistro(TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> registro){
        this.registro = registro;
    }
    public TreeMap<Grupo, HashMap<Asignatura, ArrayList<Estudiante>>> getRegistro() {
        return registro;
    }
    //endregion

    //region operaciones grupo 11-14

    /**
     * Si el grupo existe en el registro.
     * @param grupo Instancia de Grupo, puede ser null.
     * @return Devuelve null si el grupo es nulo o true o false dependiendo si la clave grupo existe en el registro.
     */
    public boolean existeGrupo(Grupo grupo) {
        // TODO HECHO: existeGrupo (11)
        if (grupo == null) {
            return false;
        }
        return registro.containsKey(grupo);
    }

    /**
     * Añade una entrada con el grupo y un HashMap nuevo asociado como valor
     * reemplazando a una entrada con la misma clave si ya existiese.
     * @param grupo Instancia de un grupo
     */
    public void anadirGrupo(Grupo grupo) {
        // TODO HECHO: anadirGrupo (12)
        if (grupo == null) {
            return;
        }
        registro.put(grupo, new HashMap<>());
    }

    /**
     * Devuelve un conjunto ordenado no repetido de grupos formado con
     * el conjunto de grupos que contiene el registro como claves.
     * @return TreeSet de grupos del registro
     */
    public TreeSet<Grupo> getGrupos() {
        // TODO HECHO: getGrupos (13)
        return new TreeSet<>(registro.keySet());
    }

    /**
     * Elimina la entrada del grupo indicado del registro.
     * @param grupo Instancia de Grupo.
     */
    public void borrarGrupo(Grupo grupo) {
        // TODO HECHO: borrarGrupo (14)
        if (grupo == null) {
            return;
        }
        registro.remove(grupo);
    }

    //endregion

    //region operaciones asignatura 21-25

    /**
     * Existe la asignatura en el grupo.
     * @param asignatura
     * @param grupo
     * @return Si existe la asignatura asociada al grupo indicado en el registro.
     */
    public boolean existeAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: existeAsignaturaGrupo (21)
        if (existeGrupo(grupo)) {
            return registro.get(grupo).containsKey(asignatura);
        }
        return false;
    }

    /**
     * Si el grupo no existe en el registro se deberá añadir dentro de este método.
     * Incorpora una lista de estudiantes nueva asociada a una asignatura al grupo indicado.
     * @param asignatura
     * @param grupo
     */
    public void anadirAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: anadirAsignaturaGrupo (22)
        if (!existeGrupo(grupo)) {
            anadirGrupo(grupo);
        }
        registro.get(grupo).put(asignatura, new ArrayList<>());
    }

    /**
     * Devuelve el conjunto de asignaturas asociadas al grupo indicado.
     * @param grupo
     * @return
     */
    public HashSet<Asignatura> getAsignaturas(Grupo grupo) {
        // TODO HECHO: getAsignaturas grupo (23)
        if (existeGrupo(grupo)) {
            return new HashSet<>(registro.get(grupo).keySet());
        }
        return null;
    }

    /**
     * Devuelve un conjunto de asignaturas no repetidas de todos los grupos.
     * @return
     */
    public HashSet<Asignatura> getAsignaturas() {
        // TODO HECHO: getAsignaturas todas (24)
        HashSet<Asignatura> asignaturas = new HashSet<>();
        for (Grupo grupo : registro.keySet()) {
            asignaturas.addAll(getAsignaturas(grupo));
        }
        return asignaturas;
    }

    /**
     * Si existe la asignatura asociada al grupo elimina la asignatura y la lista de estudiantes asociada.
     * @param asignatura
     * @param grupo
     */
    public void borrarAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: borrarAsignaturaGrupo (25)
        if (existeAsignaturaGrupo(asignatura, grupo)) {
            registro.get(grupo).remove(asignatura);
        }
    }

    //endregion

    //region operaciones estudiante 31-35

    /**
     * Devuelve la lista de estudiantes de la asignatura de un grupo si existe, sino devuelve null.
     * @param asignatura
     * @param grupo
     * @return
     */
    public ArrayList<Estudiante> getListaEstudiantesAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: getListaEstudiantesAsignaturaGrupo (31)
        if (existeAsignaturaGrupo(asignatura, grupo)) {
            return registro.get(grupo).get(asignatura);
        }
        return null;
    }

    /**
     * Si existe un/a estudiante en la asignatura de un grupo.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    public boolean existeEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: existeEstudianteAsignaturaGrupo (32)
        if (existeAsignaturaGrupo(asignatura, grupo)) {
            return registro.get(grupo).get(asignatura).contains(estudiante);
        }
        return false;
    }

    /**
     * Devuelve la instancia del/a estudiante asociado/a a la asignatura indicada de un grupo
     * con la nota que tiene o null si no existe.
     * @param estudiante
     * @param asignatura
     * @param grupo
     * @return
     */
    public Estudiante getEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: getEstudianteAsignaturaGrupo (33)
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            for (Estudiante est : registro.get(grupo).get(asignatura)) {
                if (est.equals(estudiante)) {
                    return est;
                }
            }
        }
        return null;
    }


    /**
     * Si el grupo no existiese se debería crear en este método.
     * Análogamente, si no existe la asignatura asociada al grupo.
     * Al añadir la/el estudiante a la lista de estudiantes asociado a la asignatura del grupo,
     * si la/el estudiante ya existía se debe actualizar su nota con la que hay en el parámetro del método y
     * sino agregar una referencia a esta instancia.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    public void anadirEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: anadirEstudianteAsignaturaGrupo (34)
        if (!existeGrupo(grupo)) {
            anadirGrupo(grupo);
        }
        if (!existeAsignaturaGrupo(asignatura, grupo)) {
            anadirAsignaturaGrupo(asignatura, grupo);
        }
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            getEstudianteAsignaturaGrupo(estudiante, asignatura, grupo).setNota(estudiante.getNota());
        } else {
            registro.get(grupo).get(asignatura).add(estudiante);
        }
    }

    /**
     * Si existe la o el estudiante asociada/o a la asignatura asociada al grupo elimina a esta/este de la lista de estudiantes.
     * @param estudiante
     * @param asignatura
     * @param grupo
     */
    public void borrarEstudianteAsignaturaGrupo(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: borrarEstudianteAsignaturaGrupo (35)
        if (existeEstudianteAsignaturaGrupo(estudiante, asignatura, grupo)) {
            registro.get(grupo).get(asignatura).remove(estudiante);
        }
    }

    //endregion

    //region listados estudiantes 41-43

    /**
     * Devuelve una lista de estudiantes no repetidos de un grupo.
     * No importa la nota del/a estudiante de qué asignatura sea (no se mostrará).
     * @param grupo
     * @return Devuelve una lista de estudiantes unicos.
     */
    public ArrayList<Estudiante> getEstudiantes(Grupo grupo) {
        // TODO: getEstudiantes grupo (41)
        if (!existeGrupo(grupo)) {
            return null;
        }
        HashSet<Estudiante> estudiantesUnicos = new HashSet<>();
        for (Asignatura asignatura : registro.get(grupo).keySet()) {
            estudiantesUnicos.addAll(registro.get(grupo).get(asignatura));
        }
        return new ArrayList<Estudiante>(estudiantesUnicos);
    }

    /**
     * Devuelve una lista con todas/os las/los estudiantes que cursan una asignatura independientemente
     * del grupo al que pertenezcan. Como un/a estudiante no estará matriculado/a en distintos grupos,
     * no puede estar en la misma asignatura en distintos grupos con distinta nota.
     * La nota de las/los estudiantes será la nota de esa asignatura.
     * @param asignatura
     * @return
     */
    public ArrayList<Estudiante> getEstudiantes(Asignatura asignatura) {
        // TODO: getEstudiantes asignatura (42)
        HashSet<Estudiante> estudiantesUnicos = new HashSet<>();
        for (Grupo grupo : registro.keySet()) {
            if (existeAsignaturaGrupo(asignatura, grupo)) {
                estudiantesUnicos.addAll(registro.get(grupo).get(asignatura));
            }
        }
        if (estudiantesUnicos.isEmpty()) {
            return null;
        }
        return new ArrayList<Estudiante>(estudiantesUnicos);
    }

    /**
     * Devuelve un mapa ordenado por estudiante, asociado al grupo al que pertenecen,
     * en el que aparecen las/los estudiantes que tienen una nota mayor o igual en la asignatura indicada.
     * @param asignatura
     * @param nota
     * @return
     */
    public TreeMap<Estudiante,Grupo> getEstudiantesConNotaMayorIgualQue(Asignatura asignatura, double nota) {
        // TODO HECHO: getEstudiantesConNotaMayorIgualQue (43)
        TreeMap<Estudiante, Grupo> estudiantes = new TreeMap<>();
        for (Grupo grupo : registro.keySet()) {
            if (existeAsignaturaGrupo(asignatura, grupo)) {
                for (Estudiante estudiante : registro.get(grupo).get(asignatura)) {
                    if (estudiante.getNota() >= nota) {
                        estudiantes.put(estudiante, grupo);
                    }
                }
            }
        }
        return estudiantes;
    }

    //endregion

    //region distribuciones notas 51-52

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura en ese grupo han obtenido esa nota. El sumatorio de todos los valores debe ser el número de estudiantes
     * que hay en la lista de estudiantes de la asignatura del grupo. Por ejemplo, si nadie tiene un 0 en PROG en DAW1,
     * el valor asociado a la clave 0 será 0. Si hay 5 personas que han sacado una nota entre 8 y 9 (sin incluir el 9),
     * el valor asociado a la clave 8 será 5.
     *
     *     Opción: 51
     *     Nombre del grupo: DAW1
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  0|  2|  2|  3|  1|  1|  1|  1|  5|  3|  0|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @param grupo
     * @return
     */
    public TreeMap<Integer,Integer> getDistribucionNotasAsignaturaGrupo(Asignatura asignatura, Grupo grupo) {
        // TODO HECHO: getDistribucionNotasAsignaturaGrupo (51)
        if (existeAsignaturaGrupo(asignatura, grupo)) {
            TreeMap<Integer, Integer> distribucion = new TreeMap<>();
            for (int i = 0; i <= 10; i++) {
                distribucion.put(i, 0);
            }
            for (Estudiante estudiante : registro.get(grupo).get(asignatura)) {
                distribucion.put((int) estudiante.getNota(), distribucion.get((int) estudiante.getNota()) + 1);
            }
            return distribucion;
        }
        return null;
    }

    /**
     * Devuelve un mapa en el que las claves son valores del 0 al 10 y los valores el número de personas que en esa
     * asignatura considerando todos los grupos han obtenido esa nota.
     *
     *     Opción: 52
     *     Nombre de la asignatura: PROG
     *     |  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *     |  5|  8|  5|  4|  4|  7|  1|  4| 13|  7|  1|
     *     |---|---|---|---|---|---|---|---|---|---|---|
     *
     * @param asignatura
     * @return
     */
    public TreeMap<Integer,Integer> getDistribucionNotasAsignatura(Asignatura asignatura) {
        // TODO HECHO: getDistribucionNotasAsignatura (52)
        TreeMap<Integer, Integer> distribucion = new TreeMap<>();
        for (int i = 0; i <= 10; i++) {
            distribucion.put(i, 0);
        }
        for (Grupo grupo : registro.keySet()) {
            if (existeAsignaturaGrupo(asignatura, grupo)) {
                distribucion = acumulaDistribucionesDeNotas(distribucion, getDistribucionNotasAsignaturaGrupo(asignatura, grupo));
            }
        }
        return distribucion;
    }

    private TreeMap<Integer,Integer> acumulaDistribucionesDeNotas(TreeMap<Integer,Integer> map1, TreeMap<Integer,Integer> map2) {
        // TODO HECHO: acumulaDistribucionesDeNotas
        TreeMap <Integer, Integer> distribucion = new TreeMap<>();
        for (int i = 0; i <= 10; i++) {
            distribucion.put(i, map1.get(i) + map2.get(i));
        }
        return distribucion;
    }

    //endregion

    //region info estudiante 61-62

    /**
     * Devuelve el grupo al que pertenece el estudiante o null. Un/a estudiante sólo pertenece a un grupo,
     * si se encuentra una coincidencia se puede devolver esa.
     *
     *     Opción: 61
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     La/El estudiante pertenece al grupo DAW1
     *
     * @param nombre
     * @param apellidos
     * @return
     */
    public Grupo grupoDelEstudiante(String nombre, String apellidos) {
        // TODO HECHO: grupoDelEstudiante (61)
        for (Grupo grupo : registro.keySet()) {
            for (Asignatura asignatura : registro.get(grupo).keySet()) {
                for (Estudiante estudiante : registro.get(grupo).get(asignatura)) {
                    if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)) {
                        return grupo;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devuelve un mapa de asignaturas y notas obtenidas de un/a estudiante o null si no tiene.
     *
     *     Opción: 62
     *     Nombre del/a estudiante: Samantha
     *     Apellidos del/a estudiante: Oag
     *     LMGI : 5.89
     *     BADA : 5.45
     *     ING  : 6.74
     *     SIN  : 3.49
     *     ENDE : 8.90
     *     PROG : 3.70
     *
     * @param nombre
     * @param apellidos
     * @return Devuelve un mapa de asignaturas y notas obtenidas de un/a estudiante o null si no tiene.
     */
    public HashMap<Asignatura, Double> notasEstudiante(String nombre, String apellidos) {
        // TODO HECHO: notasEstudiante (62)
        HashMap<Asignatura, Double> notas = new HashMap<>();
        for (Grupo grupo : registro.keySet()) {
            for (Asignatura asignatura : registro.get(grupo).keySet()) {
                for (Estudiante estudiante : registro.get(grupo).get(asignatura)) {
                    if (estudiante.getNombre().equals(nombre) && estudiante.getApellidos().equals(apellidos)) {
                        notas.put(asignatura, estudiante.getNota());
                    }
                }
            }
        }
        if (notas.size() == 0) {
            return null;
        }
        return notas;
    }

    //endregion

}

