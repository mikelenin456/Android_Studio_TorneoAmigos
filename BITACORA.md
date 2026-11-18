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
## Hito 3

### 17/11/2026 - Registro y consulta de equipos

Primero agregué las dependencias de Room al Gradle del módulo `app`. En el paquete `model`, adapté `Equipo` para que represente la tabla `equipos` y le agregué un ID que se genera al guardar. También creé `EquipoAccesoDatos` para insertar y listar equipos, y `AppDatabase` para abrir la base de datos `torneos.db`.

Al conectar el formulario, cambié por error el ID del botón en el XML y Java dejó de encontrarlo. Lo corregí conservando `btnValidarEquipo` como ID y cambiando el texto a **Guardar equipo**. Después conecté el botón a Room para guardar los datos cuando pasan las validaciones.

El formulario ya confirmaba el guardado, pero la lista y el formulario de partido seguían mostrando los equipos de ejemplo. Cambié ambas pantallas para que consulten los equipos guardados en Room. La consulta se ejecuta en segundo plano y luego se actualizan la lista y los desplegables.

Probé el registro con **Amigos Michael**. Lo vi en la lista de la app y en el formulario de partido junto a **Gianca**. También abrí Database Inspector y comprobé que los dos estaban en la tabla `equipos`. [Lista en la app](evidencias/capturas/capturas_hito3/13_lista_equipos_registrados.png) · [Tabla en Database Inspector](evidencias/capturas/capturas_hito3/15_equipos_database_inspector.png)
