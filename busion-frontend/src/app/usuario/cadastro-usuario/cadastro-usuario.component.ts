import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { Generics } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.scss']
})
export class CadastroUsuarioComponent implements OnInit {

  userId: number
  @Input() name: string
  email: string
  @Input() birthDate: string | Date
  ngForm: any;
  blockSave: boolean = false

  constructor(private httpService: HttpService, 
    private poNotification: PoNotificationService,
    private router: Router, 
    private route: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.restore();
    let userId = this.route.snapshot.paramMap.get("codUser");

    if (userId != null){
      this.userId = parseInt(userId)
      this.getUser(this.userId)
    } else {
      this.initDadosUsuario()
    }
  }

  initDadosUsuario(){
    this.name = 'Teste'
    this.email = 'teste@teste.com.br'
    this.birthDate = new Date(1995,11,17), Validators.required
  }

  getUser(userId: number){
    this.httpService.get('user', '/user').subscribe( //alterar aqui
      (response)=>{
        response.forEach(user => {
          if (user.userId == this.userId){
            let ultimoCadastro = Generics.getDadosUltimoCadastro(user.cadastros)

            if (ultimoCadastro != undefined){
              this.name = ultimoCadastro.name
              this.email = ultimoCadastro.email
              this.birthDate = ultimoCadastro.birthDate
            }
          }
        });
      }
    )
  }

  createBodyUser(): BodyCadastro{
    return {
      userId : this.userId,
      name: this.name,
      email: this.email,
      birthDate: this.birthDate,
      dataAtualizacao: new Date().toISOString(),
    }
  }

  restore() {
    this.userId = undefined;
    this.name = undefined;
    this.email = undefined;
    this.birthDate = undefined;
    this.ngForm = undefined;
  }

  goBack(){
    this.router.navigateByUrl('/usuarios')
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyUser()
    if (this.validaDados()){
      this.httpService.post('usuario', JSON.stringify(json), 'med/').subscribe( //alterar aqui
        response=>{ 
          this.blockSave = false  
          this.poNotification.success("Novo usuário cadastrado com sucesso!")
        }
      )
    } else {
      this.blockSave = false
    }
  }

  validaDados(){
    let lOk: boolean = true
    if (this.userId == undefined){
      this.poNotification.error("Informe um código do Usuário!")
      lOk = false;
    }

    if (this.name == undefined){
      this.poNotification.error("Informe o nome do Usuário!")
      lOk = false;
    }

    if (this.email == undefined){
      this.poNotification.error("Informe o email do Usuário!")
      lOk = false;
    }


    if (this.birthDate == undefined){
      this.poNotification.error("Informe a data de nascimento do Usuário!")
      lOk = false;
    }

    return lOk
  }
}

interface BodyCadastro {
  userId?: number,
  name: string,
  email: string,
  birthDate: string | Date,
  dataAtualizacao: string
}