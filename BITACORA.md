\# Bitacora de desarrollo - Hito 1



\## Entrada 



Avance : Armé la pantalla «Ver equipos de ejemplo» con un RecyclerView y cinco equipos definidos en `EquiposEjemplo.java`.



Dificultad: los dos TextView tenían el mismo ID y Android Studio los marcaba en rojo porque se estaban superponiendo los ID



Solución: Los nombres eran muy repetidos asique dejé "tvNombreEquipo" para el nombre y "tvCategoriaEquipo" para la categoría. Luego usé `EquipoAdapter` asi renombrando mis ID para ser mas claros en estos 

\# Bitacora - Hito 2

\# 10/11/2026 - Hito 2 

Avance: Agregado un resumen al formulario de partido que muestra el equipo tanto local, visitante,cancha y sus fecha y horas sin necesidad de guardar el partido 

Dificultd: El formulario solo mostraba un aviso de validacion esta tenia que mostrar datos ingresados del codigo puestos

Solucion: Importé AlertDialog en PartidoActivity.java y reemplacé el aviso de confirmación por un diálogo con los datos del formulario.