package com.mytransfport.UserServices.Service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.mytransfport.UserServices.Entity.Usuario;
import com.mytransfport.UserServices.Utils.EmailValidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    FirebaseAuth auth = FirebaseAuth.getInstance();



    public String createUser(String nome,String email, String senha) throws FirebaseAuthException {
        if(!EmailValidate.isValidEmailAddress(email)){
            return "Email invalido, por favor entre com email valido";
        }else if (senha.length()<6){
            return "Por favor, digite uma senha com no minimo 6 caracteres";
        }else{
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setEmailVerified(true)
                    .setPassword(senha)
                    .setDisabled(false);
            try {
                UserRecord userRecord = auth.createUser(request);
                Usuario usuario = new Usuario();

                usuario.setNome(nome);
                usuario.setEmail(userRecord.getEmail());
                usuario.setSenha("");
                usuario.setEmailVerificado(userRecord.isEmailVerified());
                usuario.setUid(userRecord.getUid());

                writeUserName(usuario);
                SlackService slackService = new SlackService();
                slackService.sendMessageToSlack("Usuário :" + usuario.getNome() + " criado com sucesso" +
                        "\nUID: " + usuario.getUid() +
                        "\nEmail: " + usuario.getEmail());
                return "Usuário criado com sucesso: UID " + userRecord.getUid() + " Email: " + userRecord.getEmail();
            }catch (Exception e){
                    return e.getMessage();
            }
        }
    }
    public String createUserAPP(String uid,String nome,String email)  {

            try {
                Usuario usuario = new Usuario();

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha("");
                usuario.setEmailVerificado(true);
                usuario.setUid(uid);
                writeUserName(usuario);
                return "Usuário criado com sucesso";

            }catch (Exception e){
                return e.getMessage();
            }
    }


    public String editUser(String uid,String nome, String email) throws FirebaseAuthException {
        if(!EmailValidate.isValidEmailAddress(email)){
            return "Email invalido, por favor entre com email valido";
        }else if(uid.isEmpty()|| uid == null){
            return "É necessário o envio do UID do usuário";
        }else{
            try {
                UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                        .setEmail(email)
                        .setDisabled(false);

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setUid(uid);

                UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
                writeUserName(usuario);
                return "Usuário:" + nome + " editado atualizado com sucesso";
            }catch (Exception e){
                return e.getMessage();
            }

        }

    }

    public String deleteUser(String uid) throws FirebaseAuthException {
        Firestore dbfirestore = FirestoreClient.getFirestore();
        try {
            Usuario usuario = new Usuario();
            usuario = getUserByID(uid);
            auth.deleteUser(uid);
            ApiFuture<WriteResult> writeResultApiFuture = dbfirestore.collection("users").document(uid).delete();
            SlackService slackService = new SlackService();
            slackService.sendMessageToSlack("Usuário :" + usuario.getNome() + " deletado com sucesso");

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
            usuario.setNome(getUserInfo(user.getUid()));
            listaUser.add(usuario);
        }
        return listaUser;
    }
    public String getUserInfo(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(uid);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document   = future.get();
        return document.getString("nome");

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
            usuario.setNome(getUserInfo(uid));
            return usuario;
        }catch(Exception e){
            return null;
        }
    }

}
