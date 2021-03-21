package com.mytransfport.UserServices.Service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.mytransfport.UserServices.Entity.Usuario;
import com.mytransfport.UserServices.Utils.EmailValidate;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    public Usuario createUser(String nome, String email, String senha, LocalDateTime dataNascimento) throws FirebaseAuthException {
        if(!EmailValidate.isValidEmailAddress(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email invalido, por favor entre com email valido");
        }else if (senha.length()<6){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email invalido, por favor entre com email valido");
        }else{
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setEmailVerified(true)
                    .setPassword(senha)
                    .setDisabled(false);
            try {
                UserRecord userRecord = auth.createUser(request);
                Usuario usuario = new Usuario();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strdataNascimento = dataNascimento.format(formatter);

                usuario.setNome(nome);
                usuario.setEmail(userRecord.getEmail());
                usuario.setSenha("");
                usuario.setEmailVerificado(userRecord.isEmailVerified());
                usuario.setUid(userRecord.getUid());
                usuario.setDataNascimento(strdataNascimento);

                writeUserName(usuario);
                SlackService slackService = new SlackService();
                slackService.sendMessageToSlack("Usuário :" + usuario.getNome() + " criado com sucesso" +
                        "\nUID: " + usuario.getUid() +
                        "\nEmail: " + usuario.getEmail());
                return usuario;
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
            }
        }
    }
    public String createUserAPP(String uid,String nome,String email,LocalDateTime dataNascimento)  {

            try {
                Usuario usuario = new Usuario();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strdataNascimento = dataNascimento.format(formatter);

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha("");
                usuario.setEmailVerificado(true);
                usuario.setUid(uid);
                usuario.setDataNascimento(strdataNascimento);
                writeUserName(usuario);
                return "Usuário criado com sucesso";

            }catch (Exception e){
                return e.getMessage();
            }
    }


    public Usuario editUser(String uid, String nome, String email, LocalDateTime dataNascimento) throws FirebaseAuthException {
        if(!EmailValidate.isValidEmailAddress(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email invalido, por favor entre com email valido");
        }else if(uid.isEmpty()|| uid == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email invalido, por favor entre com email valido");
        }else{
            try {
                UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                        .setEmail(email)
                        .setDisabled(false);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strdataNascimento = dataNascimento.format(formatter);

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setUid(uid);
                usuario.setDataNascimento(strdataNascimento);

                UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
                writeUserName(usuario);
                return usuario;
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
            }

        }

    }

    public String deleteUser(String uid) throws FirebaseAuthException {
        Firestore dbfirestore = FirestoreClient.getFirestore();
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(getUserInfo(uid).getNome());
            auth.deleteUser(uid);
            ApiFuture<WriteResult> writeResultApiFuture = dbfirestore.collection("users").document(uid).delete();
            SlackService slackService = new SlackService();
            slackService.sendMessageToSlack("Usuário: " + usuario.getNome() + " deletado com sucesso");

            return "Usuário deletado com sucesso";
        }catch (Exception e){
            return "Erro ao deletar usuário";
        }

    }
    public List<Usuario> getAllUsers() throws FirebaseAuthException, ExecutionException, InterruptedException {
        List<Usuario> listaUser = new ArrayList();
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        page = FirebaseAuth.getInstance().listUsers(null);
        for (ExportedUserRecord user : page.iterateAll()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(user.getEmail());
            usuario.setUid(user.getUid());
            usuario.setEmailVerificado(user.isEmailVerified());
            usuario.setSenha(user.getPasswordHash());
            usuario.setNome(getUserInfo(user.getUid()).getNome());
            usuario.setDataNascimento(getUserInfo(user.getUid()).getDataNascimento());
            listaUser.add(usuario);
        }
        return listaUser;
    }
    public Usuario getUserInfo(String uid) throws ExecutionException, InterruptedException {
        Usuario usuario = new Usuario();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(uid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document   = future.get();

        usuario.setNome(document.getString("nome"));
        usuario.setDataNascimento(document.getString("dataNascimento"));

        return usuario;

    }
    public void writeUserName(Usuario usuario){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collecApiFuture = dbFirestore.collection("users").document(usuario.getUid()).set(usuario);
    }

    public Usuario getUserByID(String uid) throws FirebaseAuthException {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser( uid);
            Usuario usuario = new Usuario();
            usuario.setEmail(userRecord.getEmail());
            usuario.setUid(userRecord.getUid());
            usuario.setEmailVerificado(userRecord.isEmailVerified());
            usuario.setNome(getUserInfo(uid).getNome());
            usuario.setDataNascimento(getUserInfo(uid).getDataNascimento());
            return usuario;
        }catch(Exception e){
            return null;
        }
    }

    public String getUserUidByEmail(String email) throws FirebaseAuthException {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            return userRecord.getUid();
        }catch(Exception e){
            return null;
        }
    }

}
