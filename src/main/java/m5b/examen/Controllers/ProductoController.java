package m5b.examen.Controllers;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import m5b.examen.Config.RespuestaGenerica;
import m5b.examen.Models.producto;
import m5b.examen.Repositories.ProductoRepository;
import net.bytebuddy.asm.Advice.Return;

@RestController
public class ProductoController {
    
    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping("/ListaProductos")
    public ResponseEntity<RespuestaGenerica> getUsuarios(){
        List<producto> lista = productoRepository.findAll();
        RespuestaGenerica<producto>respuesta = new RespuestaGenerica<>("Listado generado exitosamente",lista,0);
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
        
    }



    @PostMapping("/CrearProducto")
    public ResponseEntity<RespuestaGenerica> CrearUsuario(@RequestBody producto producto){
        
        List<producto> data = new ArrayList<producto>();
        RespuestaGenerica<producto> respuesta = new RespuestaGenerica<>();


        try{

            producto proInsert=null;
            System.out.println("El nombre es "+producto.getDescripcion());

            if(producto.getCantidad()>0 && producto.getPrecio()>0){
                
                double preciot= producto.getCantidad()*producto.getPrecio();
                double calculado =preciot;
                if((producto.getCantidad()*producto.getPrecio())>50){
                    calculado=(preciot*0.12)+((preciot)-(preciot*0.10));
                }else{
                    calculado=(preciot)+(preciot*0.12);
                }

                
                producto.setCalculado(calculado);
                proInsert= productoRepository.save(producto);

                System.out.println(producto.toString());
                System.out.println("IVA: "+(producto.getCalculado()*0.12));
                System.out.println("A PAGAR: "+producto.getCalculado());
            }else{
                data.add(proInsert);
                respuesta.setMensaje("Producto NO ingresado exitosamente por validaciones");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }
            
            if(proInsert!=null){
                data.add(proInsert);
                respuesta.setMensaje("Producto ingresado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                data.add(proInsert);
                respuesta.setMensaje("Producto NO ingresado exitosamente por valores nulos");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }
            
            
           
        }
        catch(Exception ex){
            respuesta.setMensaje("Hubo un problema al insertar el Producto");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println(ex.getMessage());
            //return new ResponseEntity<>("El vehículo no ha sido ingresado",HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.CREATED);    

    }



    @PutMapping("/EditarProducto/{id}")
    public ResponseEntity<RespuestaGenerica> EditarUsuario(@RequestBody producto newProducto,@PathVariable Long id){
        List<producto> data = new ArrayList<producto>();
        RespuestaGenerica<producto> respuesta = new RespuestaGenerica<>();
        try{
            producto v1 =productoRepository.findById(id).map(pro->{
                pro.setDescripcion(newProducto.getDescripcion());
                pro.setPrecio(newProducto.getPrecio());
                pro.setCantidad(newProducto.getCantidad());
                return productoRepository.save(pro);
            })
            .orElseGet(()->{
                return new producto();
            });
            if (v1!=null) {
                data.add(newProducto);
                respuesta.setMensaje("Producto Actualizado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0); 
            }else{
                data.add(newProducto);
                respuesta.setMensaje("Prodcuto No Actualizado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(1); 
                
            }


            
        }
        catch(Exception ex){
            respuesta.setMensaje("Hubo un problema al actualizar el Producto");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println("No se pudo almacenar el objeto en la base de datos");
            //return new ResponseEntity<>("El vehículo no ha sido ingresado",HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
    }




    @DeleteMapping("/BorrarProducto/{id}")
    public ResponseEntity BorrarPlan(@PathVariable Long id ){
        List<producto> data = new ArrayList<producto>();
        RespuestaGenerica<producto> respuesta = new RespuestaGenerica<>();
        try {
            
            productoRepository.deleteById(id);
            if(productoRepository!=null){
                data.add(new producto());
                respuesta.setMensaje("Producto Eliminado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                data.add(new producto());
                respuesta.setMensaje("Producto NO Eliminado exitosamente");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un problema al eliminar el Producto");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println("No se pudo almacenar el objeto en la base de datos");
        }
        
        return new ResponseEntity<RespuestaGenerica>(respuesta,HttpStatus.OK);
    }
}

