import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService, PoTableColumn, PoTableDetail } from '@po-ui/ng-components';
import { Rota } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-cadastro-onibus',
  templateUrl: './cadastro-onibus.component.html',
  styleUrls: ['./cadastro-onibus.component.scss']
})
export class CadastroOnibusComponent implements OnInit {

  busId: number
  busCod: string
  enabled: boolean = true
  rotas: Array<Rota>

  ngForm: any;
  blockSave: boolean = false
  tipoOp: string;

  constructor(private httpService: HttpService, 
    private poNotification: PoNotificationService,
    private router: Router, 
    private route: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.restore();
    let busId = this.route.snapshot.paramMap.get("idBus");
    this.tipoOp = this.route.snapshot.data['opBus']

    if (busId != null){
      this.busId = parseInt(busId)
      this.getBus(this.busId)
    } else {
      this.initDadosBus()
    }
  }

  initDadosBus(){
    this.busCod = '123'
    this.enabled = true
  }

  getBus(busId: number){
    this.httpService.get('Busca Ônibus', 'mslinha/linha/' + busId.toString()).subscribe( //alterar aqui
      (response)=>{
        let onibusLocalizada = response
        if (onibusLocalizada == undefined){
          this.poNotification.error("Não foi possível cadastrar com sucesso!")
        } else {
            this.busCod = onibusLocalizada.busCod
            this.enabled = onibusLocalizada.enabled

            this.getRotas(this.busId)
        }
      }
    )
  }

  getRotas(rotaId: number){
    this.httpService.get('Busca Rotas', 'mslinha/linha/' + rotaId.toString() + '/rota').subscribe(
      (response)=>{
        response.forEach(rota => {
          let rotaLocalizada: Rota ={
            rotaId: rota.id,
            latitude: rota.latitude,
            longitude: rota.longitude,
            ordem: rota.ordem
          }

          this.rotas.push(rotaLocalizada)
        });
      }
    )
  }

  cadastroRouteDetail: PoTableDetail = {
    columns: [
      { property: 'rotaId', label: 'Sequencial' },
      { property: 'latitude', label: 'Latitude', type: 'string'},
      { property: 'longitude', label: 'Longitude', type: 'string'},
      { property: 'ordem', label: 'Ordem', type: 'string'},
    ], 
    typeHeader: 'top'
  };

  gridRouteCols: Array<PoTableColumn> = [
    {
      label: 'Route Id',
      property: 'rotaId',
      visible: true
    },
    {
      label: 'Latitude',
      property: 'latitude', 
    },
    {
      label: 'Longitude',
      property: 'longitude', 
    },
    {
    label: 'Ordem',
    property: 'ordem', 
    },
    { property: 'medicoes', label: 'Details', type: 'detail', detail: this.cadastroRouteDetail }
  ]

  createBodyLine(): BodyCadastro{
    return {
      busId : this.busId,
      busCod: this.busCod,
      enabled: this.enabled,
      dataAtualizacao: new Date().toISOString(),
    }
  }

  restore() {
    this.busId = undefined;
    this.busCod = undefined;
    this.enabled = undefined;
    this.ngForm = undefined;
  }

  goBack(){
    this.router.navigateByUrl('/usuarios') //alterar
  }

  goAlterRoute(){
    window.open(this.router.url + '/rota/')
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyLine()
    if (this.validaDados()){
      this.httpService.post('usuario', JSON.stringify(json), 'med/').subscribe( //alterar aqui
        response=>{ 
          this.blockSave = false  
          this.poNotification.success("Novo ônibus cadastrada com sucesso!")
          this.router.navigateByUrl('/linhas/' + response.id)
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

    return lOk
  }
}

interface BodyCadastro {
  busId: number,
  busCod: string,
  enabled: boolean,
  dataAtualizacao: string
}

