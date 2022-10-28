package arg.com.pruebaPortfolioAP.Portfolio.Controller;

import arg.com.pruebaPortfolioAP.Portfolio.Dto.DtoHys;
import arg.com.pruebaPortfolioAP.Portfolio.Dto.DtoProyecto;
import arg.com.pruebaPortfolioAP.Portfolio.Entity.Hys;
import arg.com.pruebaPortfolioAP.Portfolio.Entity.Proyecto;
import arg.com.pruebaPortfolioAP.Portfolio.Security.Controller.Mensaje;
import arg.com.pruebaPortfolioAP.Portfolio.Service.SProyecto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")

@RestController
@CrossOrigin(origins = "https://frontportfolioap-59f70.web.app")
@RequestMapping("/proyectos")
public class CProyecto {
    @Autowired
    SProyecto sProyecto;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);

        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
        if(StringUtils.isBlank(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProyecto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombre(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);

        Proyecto proyecto = new Proyecto(dtoProyecto.getNombre(), dtoProyecto.getDescripcion(), dtoProyecto.getImg());
        sProyecto.save(proyecto);

        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoProyecto){
        // Validacion del ID
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        // Comparar nombres de skill
        if(sProyecto.existsByNombre(dtoProyecto.getNombre()) && sProyecto.getByNombre(dtoProyecto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        // No puede estar vacio
        if(StringUtils.isBlank(dtoProyecto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoProyecto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);

        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombre(dtoProyecto.getNombre());
        proyecto.setDescripcion(dtoProyecto.getDescripcion());
        proyecto.setImg(dtoProyecto.getImg());

        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}
