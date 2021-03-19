import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { GenericsBus } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-cadastro-onibus',
  templateUrl: './cadastro-onibus.component.html',
  styleUrls: ['./cadastro-onibus.component.scss']
})
export class CadastroOnibusComponent implements OnInit {

  busId: number
  busCod: number
  enabled: boolean = true
  //linha: Array<Linha>
  posicao: null
  ngForm: any;
  blockSave: boolean = false

  constructor(private httpService: HttpService, 
    private poNotification: PoNotificationService,
    private router: Router, 
    private route: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.restore();
    let busId = this.route.snapshot.paramMap.get("codUser"); //alterar

    if (busId != null){
      this.busId = parseInt(busId)
      this.getBus(this.busId)
    } else {
      this.initDadosLines()
    }
  }

  initDadosLines(){
    this.busCod = 123
    this.enabled = true
    this.posicao = null
    //this.linha = 
  }

  getBus(busId: number){
    this.httpService.get('user', '/user').subscribe( //alterar aqui
      (response)=>{
        response.forEach(bus => {
          if (bus.busId == this.busId){
            let ultimoCadastro = GenericsBus.getDadosUltimoCadastroBus(bus.cadastros)

            if (ultimoCadastro != undefined){
              this.busCod = ultimoCadastro.busCod
              this.enabled = ultimoCadastro.enabled
              this.posicao = ultimoCadastro.posicao
              //this.linha = ultimoCadastro.linha
            }
          }
        });
      }
    )
  }

  createBodyLine(): BodyCadastro{
    return {
      busId : this.busId,
      busCod: this.busCod,
      enabled: this.enabled,
      posicao: this.posicao,
      //linha: this.linha,
      dataAtualizacao: new Date().toISOString(),
    }
  }


  restore() {
    this.busId = undefined;
    this.busCod = undefined;
    this.enabled = undefined;
    this.posicao = undefined;
    //this.linha = undefined;
    this.ngForm = undefined;
  }

  goBack(){
    this.router.navigateByUrl('/usuarios') //alterar
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyLine()
    if (this.validaDados()){
      this.httpService.post('usuario', JSON.stringify(json), 'med/').subscribe( //alterar aqui
        response=>{ 
          this.blockSave = false  
          this.poNotification.success("Novo ônibus cadastrada com sucesso!")
        }
      )
    } else {
      this.blockSave = false
    }
  }

  validaDados(){
    let lOk: boolean = true
    if (this.busId == undefined){
      this.poNotification.error("Informe um código do Ônibus!")
      lOk = false;
    }

    if (this.busCod == undefined){
      this.poNotification.error("Informe o código do Ônibus!")
      lOk = false;
    }

    if (this.posicao == undefined){
      this.poNotification.error("Informe a posição do Ônibus!")
      lOk = false;
    }

    // if (this.linha == undefined){
    //   this.poNotification.error("Informe a linha do Ônibus!")
    //   lOk = false;
    // }

    return lOk
  }
}

interface BodyCadastro {
  busId?: number,
  busCod: number,
  enabled: boolean,
  posicao: number,
  //linha: number,
  dataAtualizacao: string
}

