package br.gov.sp.fatec.projetoia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.dtos.UserDTO;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> getByid(@PathVariable("id") Long id){
        UserEntity user = userService.getById(id).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<UserEntity> insert(@RequestBody UserDTO data){
        if(data == null) return ResponseEntity.badRequest().body(null);

        userService.insert(data);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable("id") Long id, @RequestBody UserDTO data){
        if(data == null) return ResponseEntity.badRequest().body(null);

        userService.update(id, data);

        return ResponseEntity.ok().build();
    }
}

