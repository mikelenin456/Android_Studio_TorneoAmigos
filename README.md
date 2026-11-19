\# Torneos Amigos — Hito 1



Torneos Amigos es una app Android pensada para organizar partidos de fútbol amateur. En este primer avance hice la pantalla de inicio, los formularios de equipo y partido, y una lista con cinco equipos de ejemplo. Al tocar un equipo se puede ver su nombre, categoría y delegado.



Los formularios muestran un aviso si falta completar un campo obligatorio. Por ahora, la app valida los datos ingresados y funciona sin base de datos.



\## Cómo probarlo



Abre el proyecto en Android Studio y ejecútalo en el emulador. Desde el inicio puedes entrar a los formularios, abrir el menú de tres puntos o ir a la lista de equipos. Prueba pulsar \*\*Validar formulario\*\* con un campo vacío y luego complétalo para ver el mensaje de confirmación.



\## Capturas



\### Inicio y menú -



!\[Pantalla de inicio](evidencias/capturas/01\_inicio.png)

!\[Menú de tres puntos abierto](evidencias/capturas/02\_menu\_abierto.png)



\### Formulario de equipo -



!\[Aviso cuando falta el nombre del equipo](evidencias/capturas/03\_formulario\_requerido.jpg)

!\[Formulario de equipo validado](evidencias/capturas/04\_formulario\_completo.jpg)



\### Formulario de partido -



!\[Aviso cuando falta la cancha](evidencias/capturas/05\_partido\_requerido.jpg)

!\[Formulario de partido validado](evidencias/capturas/06\_partido\_completo.jpg)



\### Lista y detalle del equipo -



!\[Lista con los cinco equipos de ejemplo](evidencias/capturas/08\_lista\_equipos.png)

!\[Detalle de Amigos UDH](evidencias/capturas/07\_detalle\_equipo.png)



\## Bitácora



\[Ver la bitácora de desarrollo](BITACORA.md)


## HITO 2

### FORMULARIO DE PARTIDO CON SU VALIDACION Y RESUMEN

En este avance se añadio un resumen al formulario de partido.
El aplicativo muestra al equipo con sus respectivos campos en un dialogo, sin necesidad de guardar el partido donde para probar esto sera en formulario de partido llenado sus campos y esto
"validara los formularios"

-(BITACORA.md)
-(evidencias/caputas_hito2/09_resumen_partido.png)
-PLAN_DE_AVANCE.docx
## Hito 3

### Registro y consulta de equipos con Room

Agregué Room al proyecto para guardar los equipos que se registran desde el formulario. Ahora se pueden consultar en **Ver equipos registrados** y aparecen como opciones para elegir al local y al visitante en **Programar partido**.

Para probarlo, registra un equipo, marca la confirmación y pulsa **Guardar equipo**. Abre **Ver equipos registrados** para buscarlo. Luego cierra la app, vuelve a abrirla y entra otra vez a la lista para comprobar que el equipo sigue ahí.

## HITO 3 AVANCE

Avance:
- Complete el registro, la consulta, la edicion y la eliminacion de equipos con Room.
- Agregue el guardado de partidos y una pantalla para ver los partidos programados.
- Separe 2 reglas del formulario en la clase ReglasPartido porque tiene que haber al menos 2 equipos registrados y el local y visitantes tienen q ser distintos

Dificultad:
- Al guardar una edicion, el detalle del equipo seguia mostrando los datos anteriores.
- Para guardar los partidos tenia que agregar una tabla sin perder los equipos que ya habia registrado.
- Al conectar las reglas con el formulario, deje repetidas las variables del local y el visitante.

Solucion:
- Use el ID para actualizar el mismo equipo y volvi a consultar Room al regresar al detalle o a la lista.
- Agregue una tabla en Room para guardar los partidos sin borrar los equipos que ya tenia registrados.
- Quite las variables repetidas y conecte el formulario con ReglasPartido.
