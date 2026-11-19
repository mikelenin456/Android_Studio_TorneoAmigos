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

Probé el registro con **Amigos Michael**. Lo vi en la lista de la app y en el formulario de partido junto a **Gianca**. Tambien abri Database Inspector y comprobe que los dos estaban en la tabla `equipos`. [Lista en la app](evidencias/capturas/capturas_hito3/13_lista_equipos_registrados.png) · [Tabla en Database Inspector](evidencias/capturas/capturas_hito3/15_equipos_database_inspector.png)

## HITO 3 AVANCE 

Avance:
- Complete el registro, la consulta, la edicion y la eliminacion de equipos con Room.
- Agregue el guardado de partidos y una pantalla para ver los partidos programados.
- Separe 2 reglas del formulario en la clase ReglasPartido porque tiene que haber al menos 2 equipos registrados y el local y visitantes tienen q ser distintos 

Dificultad:
- Al guardar una edicion, el detalle del equipo seguia mostrando los datos anteriores.
- Para guardar los partidos tenia que agregar una tabla sin perder los equipos que ya habia registrado.
- Al conectar las reglas con el formulario deje repetidas las variables del local y del visitante ya que estas daban errores en el Android Studio

Solucion:
- Use el ID para actualizar el mismo equipo y volvi a consultar Room al regresar al detalle o a la lista.
- Agregue una tabla en Room para guardar los partidos sin borrar los equipos que ya tenia registrados.
- Quite las variables repetidas y conecte el formulario con ReglasPartido.

## Hito 3 Completo 

Avance: Agregado una opcion para guardar el marcador desde la lista de partidos. Tambien puse dos botones en el detalle del equipo para agregar jugadores y ver quienes estan registrados.

Dificultad: Al conectar el toque de cada partido, la lista quedo en blanco porque quite la linea que mostraba el texto de la fila ademas se tubo que agregar los goles y los jugadores a Room conservando los datos que ya habia guardado.


Solucion: Volvi a poner la linea que muestra el texto de la fila y actualice la base de datos. Probe guardando el 2 - 1 de Gianca contra Legion. Cerre y volvi a abrir la app, y el marcador seguia ahi. Tambien revise en Database Inspector que Luis y Marko quedaron registrados en Gianca.