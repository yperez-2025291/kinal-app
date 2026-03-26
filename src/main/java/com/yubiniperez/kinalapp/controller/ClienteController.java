package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.service.IClienteService;
import com.yubiniperez.kinalapp.entity.Cliente;
import com.yubiniperez.kinalapp.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;
import java.util.Optional;

@RestController
//@RestController = @controller + RequestBody
@RequestMapping("/clientes")
//Todas las rutas en este controlador deben empezar por /clientes
public class ClienteController {
    //Inyectamos el SERVICIO y NO el repositorio
    //El controlador solo debe tener conexion con el servicio

    private final IClienteService clienteService;
    //Como buena practica la inyeccion de dependeicas debe hacerse por el contructor

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Este resoibde peticiones GET
    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.listarClientes();
        //deleagamos el servicio
        return ResponseEntity.ok(clientes);
    }

    //{DPI} es una variable de ruta(valor a buscar)
    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarPorDPI(@PathVariable String dpi) {
        //@PathVariables toma el valor de la URL y lo asigana al DPI
        return clienteService.buscarPorDPI(dpi)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post crear un nuevo cliente
    @PostMapping ResponseEntity<?> guardar(@RequestBody Cliente cliente){
        //@RequesBody toma el JSON del cuerpo y lo convierte a un obejto de tipo Cliente
        //<?> significa "tipo generico" puede ser un cliente o String
        try {
            Cliente nuevoCliente = clienteService.guardar(cliente);
            //Intentaomos guarda el cliente para que puede lanzar una exception
            //de IllegalArgumentException
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            //Si hay error devuelve validacion
            //400 BAD REQueTS con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DELETE elimina un cliente
    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String dpi){
        //RequestEstity<Void>> no devuelve cuerpo en la respuesta
        try {
            if (!clienteService.existePorDPI(dpi)) {
                return ResponseEntity.notFound().build();
                //404 si no existe
            }
            clienteService.eliminar(dpi);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT (se ejecuto correctamente y bi sevuelve cuerpo)
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 not found
        }
    }

    //Actualizar cliente a travez del dpi
    @PutMapping("/{dpi}")
    public ResponseEntity<?> actualizar(@PathVariable String dpi, @RequestBody Cliente cliente) {
        try {
            // Verificar si el cliente existe antes de actualizar
            if (!clienteService.existePorDPI(dpi)) {
                // 404 Not Found si no existe
                return ResponseEntity.notFound().build();
            }

            // Actualizar el cliente usando el servicio
            Cliente clienteActualizado = clienteService.actualizar(dpi, cliente);

            // Retornar 200 OK con el cliente actualizado
            return ResponseEntity.ok(clienteActualizado);

        } catch (IllegalArgumentException e) {
            // 400 Bad Request si los datos son inválidos (nombre, apellido o DPI vacío)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // 404 Not Found para cualquier otro error relacionado con no encontrar el cliente
            return ResponseEntity.notFound().build();
        }
    }

    //Este resoibde peticiones GET
    @GetMapping ("/activos")
    public ResponseEntity<List<Cliente>> activos(){
        List<Cliente> activos = clienteService.listarActivos();
        //deleagamos el servicio
        return ResponseEntity.ok(activos);
    }
}


