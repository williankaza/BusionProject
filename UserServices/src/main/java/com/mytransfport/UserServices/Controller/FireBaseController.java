package com.mytransfport.UserServices.Controller;

import com.mytransfport.UserServices.DTO.Create.CreateUsuarioAppDTO;
import com.mytransfport.UserServices.DTO.Create.CreateUsuarioDTO;
import com.mytransfport.UserServices.DTO.Update.UpdateUsuarioDTO;
import com.mytransfport.UserServices.Entity.Usuario;
import com.mytransfport.UserServices.Service.FirebaseInitializer;
import com.mytransfport.UserServices.Service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("UserServices")
public class FireBaseController {

    @Autowired
    FirebaseInitializer db;

    @GetMapping("/getAllUsers")
    public List<Usuario> getAllUsers(){
        FirebaseService fbs = new FirebaseService();
        try {
            return fbs.getAllUsers();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/createUser")
    @ResponseStatus( HttpStatus.CREATED )
    public String createUser(@RequestBody CreateUsuarioDTO createUsuarioDTO) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.createUser(createUsuarioDTO.getNome(), createUsuarioDTO.getEmail(), createUsuarioDTO.getSenha(),createUsuarioDTO.getDataNascimento());
    }

    @PostMapping("/editUser")
    public String editUser(@RequestBody UpdateUsuarioDTO updateUsuarioDTO) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.editUser(updateUsuarioDTO.getUid(), updateUsuarioDTO.getNome(), updateUsuarioDTO.getEmail());
    }
    @DeleteMapping("/deleteUser")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public String deleteUser(@RequestParam String uid) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.deleteUser(uid);
    }
    @PostMapping("/createUserAPP")
    public String createUserAPP(@RequestBody CreateUsuarioAppDTO createUsuarioAppDTO) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.createUserAPP(createUsuarioAppDTO.getUid(), createUsuarioAppDTO.getNome(), createUsuarioAppDTO.getEmail(),createUsuarioAppDTO.getDataNascimento());
    }
    @GetMapping("{uid}")
    public Usuario getUserById(@PathVariable String uid) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.getUserByID(uid);
    }
}
