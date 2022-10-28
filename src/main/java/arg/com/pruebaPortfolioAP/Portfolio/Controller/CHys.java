package arg.com.pruebaPortfolioAP.Portfolio.Controller;

import arg.com.pruebaPortfolioAP.Portfolio.Dto.DtoHys;
import arg.com.pruebaPortfolioAP.Portfolio.Entity.Hys;
import arg.com.pruebaPortfolioAP.Portfolio.Security.Controller.Mensaje;
import arg.com.pruebaPortfolioAP.Portfolio.Service.SHys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")

@RestController
@CrossOrigin(origins = "https://frontportfolioap-59f70.web.app")
@RequestMapping("/skill")
public class CHys {
    @Autowired
    SHys sHys;

    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Hys hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);

        sHys.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHys dtoHys){
        if(StringUtils.isBlank(dtoHys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHys.existsByNombre(dtoHys.getNombre()))
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);

        Hys hys = new Hys(dtoHys.getNombre(), dtoHys.getPorcentaje());
        sHys.save(hys);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHys dtoHys){
        // Validacion del ID
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        // Comparar nombres de skill
        if(sHys.existsByNombre(dtoHys.getNombre()) && sHys.getByNombre(dtoHys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        // No puede estar vacio
        if(StringUtils.isBlank(dtoHys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);

        Hys hys = sHys.getOne(id).get();
        hys.setNombre(dtoHys.getNombre());
        hys.setPorcentaje(dtoHys.getPorcentaje());

        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Hys actualizada"), HttpStatus.OK);
    }

}
