package com.mytransfport.UserServices.Controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.mytransfport.UserServices.DTO.Create.CreateAgendamentoDTO;
import com.mytransfport.UserServices.DTO.Update.UpdateAgendamentoDTO;
import com.mytransfport.UserServices.Entity.Agendamento;
import com.mytransfport.UserServices.Service.FirebaseInitializer;
import com.mytransfport.UserServices.Service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("AgendamentoServices")
public class AgendamentoController {

    @Autowired
    FirebaseInitializer db;

    @PostMapping("")
    @ResponseStatus( HttpStatus.CREATED )
    public Agendamento createUserSchedule(@RequestBody CreateAgendamentoDTO createAgendamentoDTO) throws FirebaseAuthException, ExecutionException, InterruptedException {
        FirebaseService fbs = new FirebaseService();
        return fbs.createUserSchedule(createAgendamentoDTO.getUsuarioUid(), createAgendamentoDTO.getDataAgendamento(), createAgendamentoDTO.getOrigemGeo(), createAgendamentoDTO.getDestinoGeo());
    }

    @GetMapping("{uidUserSchedule}")
    public ArrayList getUserScheduleByUid(@PathVariable String uidUserSchedule) throws FirebaseAuthException, ExecutionException, InterruptedException {
        FirebaseService fbs = new FirebaseService();
        return fbs.getUserSchedule(uidUserSchedule);
    }

    @DeleteMapping("{uidUserSchedule}/{idSchedule}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public String deleteUserSchedule(@PathVariable String uidUserSchedule,
                                     @RequestParam String idAgendamento) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.deleteUserSchedule(uidUserSchedule,idAgendamento);
    }

    @PutMapping("{uidUserSchedule}")
    public Agendamento editUserSchedule(@PathVariable String uidUserSchedule,
                                        @RequestBody UpdateAgendamentoDTO updateAgendamentoDTO ) throws FirebaseAuthException {
        FirebaseService fbs = new FirebaseService();
        return fbs.editUserSchedule(uidUserSchedule, updateAgendamentoDTO.getDataAgendamento(), updateAgendamentoDTO.getOrigemGeo(), updateAgendamentoDTO.getDestinoGeo(),updateAgendamentoDTO.getIdAgendamento());
    }
}
