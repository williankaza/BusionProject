import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { GenericsLines } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-cadastro-linha',
  templateUrl: './cadastro-linha.component.html',
  styleUrls: ['./cadastro-linha.component.scss']
})
export class CadastroLinhaComponent implements OnInit {

  lineId: number
  lineCod: number
  enabled: boolean = true
  //onibus: Array<Onibus>
  //rotas: Array<Rotas>

  ngForm: any;
  blockSave: boolean = false

  constructor(private httpService: HttpService, 
    private poNotification: PoNotificationService,
    private router: Router, 
    private route: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.restore();
    let lineId = this.route.snapshot.paramMap.get("codUser"); //alterar

    if (lineId != null){
      this.lineId = parseInt(lineId)
      this.getLine(this.lineId)
    } else {
      this.initDadosLines()
    }
  }

  initDadosLines(){
    this.lineCod = 123
    this.enabled = true
    //this.onibus = 
    //this.rotas = 
  }

  getLine(lineId: number){
    this.httpService.get('user', '/user').subscribe( //alterar aqui
      (response)=>{
        response.forEach(lines => {
          if (lines.lineId == this.lineId){
            let ultimoCadastro = GenericsLines.getDadosUltimoCadastroLine(lines.cadastros)

            if (ultimoCadastro != undefined){
              this.lineCod = ultimoCadastro.lineCod
              this.enabled = ultimoCadastro.enabled
              //this.onibus = ultimoCadastro.onibus
              //this.rotas = ultimoCadastro.rotas
            }
          }
        });
      }
    )
  }

  createBodyLine(): BodyCadastro{
    return {
      lineId : this.lineId,
      lineCod: this.lineCod,
      enabled: this.enabled,
      //onibus: this.onibus,
      //this.rotas = undefined;
      dataAtualizacao: new Date().toISOString(),
    }
  }

  restore() {
    this.lineId = undefined;
    this.lineCod = undefined;
    this.enabled = undefined;
    //this.onibus = undefined;
    //this.rotas = undefined;
    this.ngForm = undefined;
  }

  goBack(){
    this.router.navigateByUrl('/usuarios')
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyLine()
    if (this.validaDados()){
      this.httpService.post('usuario', JSON.stringify(json), 'med/').subscribe( //alterar aqui
        response=>{ 
          this.blockSave = false  
          this.poNotification.success("Nova linha cadastrada com sucesso!")
        }
      )
    } else {
      this.blockSave = false
    }
  }

  validaDados(){
    let lOk: boolean = true
    if (this.lineId == undefined){
      this.poNotification.error("Informe um código da Linha!")
      lOk = false;
    }

    if (this.lineCod == undefined){
      this.poNotification.error("Informe o Código da Linha!")
      lOk = false;
    }

    // if (this.onibus == undefined){
    //   this.poNotification.error("Informe o ônibus da linha!")
    //   lOk = false;
    // }

    // if (this.rotas == undefined){
    //   this.poNotification.error("Informe a rota da Linha!")
    //   lOk = false;
    // }

    return lOk
  }
}

interface BodyCadastro {
  lineId?: number,
  lineCod: number,
  enabled: boolean,
  //onibus: number,
  dataAtualizacao: string
}
