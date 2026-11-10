\# Bitacora de desarrollo - Hito 1



\## Entrada 



Avance : Armé la pantalla «Ver equipos de ejemplo» con un RecyclerView y cinco equipos definidos en `EquiposEjemplo.java`.



Dificultad: los dos TextView tenían el mismo ID y Android Studio los marcaba en rojo porque se estaban superponiendo los ID



Solución: Los nombres eran muy repetidos asique dejé "tvNombreEquipo" para el nombre y "tvCategoriaEquipo" para la categoría. Luego usé `EquipoAdapter` asi renombrando mis ID para ser mas claros en estos 

