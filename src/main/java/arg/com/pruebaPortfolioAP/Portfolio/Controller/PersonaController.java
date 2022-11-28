package arg.com.pruebaPortfolioAP.Portfolio.Controller;

import arg.com.pruebaPortfolioAP.Portfolio.Dto.DtoPersona;
import arg.com.pruebaPortfolioAP.Portfolio.Entity.Persona;
import arg.com.pruebaPortfolioAP.Portfolio.Security.Controller.Mensaje;
import arg.com.pruebaPortfolioAP.Portfolio.Service.ImpPersonaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontportfolioap-59f70.web.app")
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);

        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }
    */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion de la persona es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getDescripcion()))
            return new ResponseEntity(new Mensaje("La imagen de la persona es obligatoria"), HttpStatus.BAD_REQUEST);

        if(personaService.existsByNombre(dtoPersona.getNombre()))
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        Persona persona = new Persona(dtoPersona.getNombre(), dtoPersona.getApellido(), dtoPersona.getCargo(),
                dtoPersona.getDescripcion(), dtoPersona.getImg());
        personaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPersona){
        // Validacion del ID
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        // Comparar nombres de skill
        if(personaService.existsByNombre(dtoPersona.getNombre()) && personaService.getByNombre(dtoPersona.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        // No puede estar vacio
        if(StringUtils.isBlank(dtoPersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoPersona.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion de la persona es obligatoria"), HttpStatus.BAD_REQUEST);

        Persona persona = personaService.getOne(id).get();

        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setCargo(dtoPersona.getCargo());
        persona.setDescripcion(dtoPersona.getDescripcion());
        persona.setImg(dtoPersona.getImg());

        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }

}
