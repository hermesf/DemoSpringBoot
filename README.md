# Proyecto Demo Spring Boot

Api Rest  de prueba,  consume servicios de  https://jsonplaceholder.typicode.com/ y provee gestion de permisos locales

## Resumen

Provee servicios para acceder a:

1. Usuarios
2. Fotos 
3. Albunes
4. Comentarios

Y permite realizar de forma local:

5. Registrar un álbum compartido con un usuario y sus permisos
6. Cambiar los permisos de un usuario para un álbum determinado
7. Traer todos los usuarios que tienen un permiso determinado respecto a un álbum específico

### Prerequisitos

Maven v 3.5.3 ó superior 

Java SDK 8

### Empaquetdo

 En la carpeta raiz del proyecto, ejecutar 

```
   mvn clean package
```

### Ejecución

 Ubicarse en el directorio ./target/  y ejecutar  

```
   java -jar demo-0.0.1-SNAPSHOT.jar
```



### Probar los Servicios

Los servicios son publicados en :

```
http://localhost:8080/
```

**1.  Usuarios**
Basepath

```
http://localhost:8080/users
```
#####Metodo GET##### 

   *  **" /"**  Lista todos los Usuarios
   *  **" /{ID} "** Obtiene el usuario correspondiente al id de usuario  **{ID}** 

**2.  Fotos**

Basepath

```
http://localhost:8080/photos
```
#####Metodo GET##### 

   *  **" /"**  Lista todas las fotos del sistema
   *  **" /{ID} "** Obtiene la foto  correspondiente al id de foto  **{ID}** 
   *  **" /?userId={ID} "** Obtiene  un litado de  las fotos  que pertenecen al usuario con id   **{ID}**

**3. Albunes**

Basepath

```
http://localhost:8080/albums
```

#####Metodo GET##### 

   *  **" /"**  Lista todos los albunes del sistema
   *  **" /{ID} "** Obtiene el album  correspondiente al id de album  **{ID}** 
   *  **" /?userId={ID} "** Obtiene  un listado de  albunes que  pertenecen al usuario con id   **{ID}**

**4. Comentarios**

Basepath

```
http://localhost:8080/comments
```

#####Metodo GET##### 

   *  **" /"**  Lista todos los comentarios  del sistema
   *  **" /{ID} "** Obtiene el comentario  correspondiente al id de comentario  **{ID}** 
   *  **" /?name={name}&email={email} "** Obtiene  un listado de  los comentarios, filtrandolos por  atributo name **{name}**  o por el usuario que realizó dicho comentario, que se indetifica por el atributo email   **{email}**

   _Nota: la búsqueda de de comentarios no es case sensitive_

**5. Permisos**

Basepath

```
http://localhost:8080/permmits
```

#####Metodo _GET_##### 

   *  **" /"**  Lista todos los permisos  del disponibles
   *  **"   /user/{ALBUM_ID}/{PERMISSION_ID}"**  Lista todos los usuarios que tienen un permiso determinado respecto a un álbum específico

#####Metodo _POST_#####

   *  **" /user"**   Asocia un permiso  a  un usuario con respecto a un álbum específico 
     * **input:** Recibe como parametros

```
albumId
userId
permissionIds
```

Por ejemplo:


```
albumId : 1
userId : 4
permissionIds : 1
permissionIds : 2
```

   
* **output:**    Http Status code	  y en el body el número de permisos asignados

```
/* Http Code Status */
201  Creado con éxito
409 Conflicto (Por ejemplo si un usuario posee permisos asignados, no se podran sobreescribir con este metodo, para actualizarlo usar metodo PUT)
```

#####Metodo _PUT_#####

   *  **" /user"**   Actualiza los permisos de un  usuario con respecto a un álbum específico 
       	* **input:** Recibe como parametros

```
albumId
userId
permissionIds
```

Por ejemplo:

```
albumId : 1
userId : 4
permissionIds : 2
```

   * **output:**    Http Status code	  y en el body el número de permisos asignados

```
 /* Http Code Status */
200  OK  actualización completada con éxito
409 Conflicto (Por ejemplo si se intenta actualizar los permisos para un usuario que no posea al menos un permiso asignado)
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework


## Versioning

W[SemVer](http://semver.org/)

## Authors

* **Hermes Flores** - [GitHub](https://github.com/hermesf)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
