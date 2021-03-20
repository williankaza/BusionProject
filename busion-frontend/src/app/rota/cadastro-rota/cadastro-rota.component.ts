import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService, PoTableColumn, PoTableDetail } from '@po-ui/ng-components';
import { Onibus } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-cadastro-rota',
  templateUrl: './cadastro-rota.component.html',
  styleUrls: ['./cadastro-rota.component.scss']
})
export class CadastroRotaComponent implements OnInit {

  rotaId: number
  @Input() latitude: string
  @Input() longitude: string
  @Input() ordem: string
  @Input() codigo: string
  enabled: boolean = true
  onibus: Array<Onibus>

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
    let rotaId = this.route.snapshot.paramMap.get("rotaId");
    this.tipoOp = this.route.snapshot.data['opRoute']

    if (rotaId != null){
      this.rotaId = parseInt(rotaId)
      this.getRotas(this.rotaId)
    } else {
      this.initDadosRoute()
    }
  }

  initDadosRoute(){
    this.codigo = '123'
    this.enabled = true
  }

  getRotas(rotaId: number){
    this.httpService.get('Busca Rota', 'mslinha/linha/' + rotaId.toString()).subscribe( //alterar aqui
      (response)=>{
        let onibusLocalizada = response
        if (onibusLocalizada == undefined){
          this.poNotification.error("Não foi possível cadastrar com sucesso!")
        } else {
            this.codigo = onibusLocalizada.codigo
            this.enabled = onibusLocalizada.enabled

            this.getRotas(this.rotaId)
        }
      }
    )
  }

  getOnibus(lineId: number){
    this.httpService.get('Busca Onibus', 'mslinha/linha/' + lineId.toString() + '/onibus').subscribe(
      (response)=>{
        response.forEach(onibus => {
          let onibusLocalizado: Onibus ={
            busId: onibus.id,
            busCod: onibus.codigo,
            enabled: onibus.ativo
          }

          this.onibus.push(onibusLocalizado)
        });
      }
    )
  }

  cadastroBusDetail: PoTableDetail = {
    columns: [
      { property: 'busId', label: 'Sequencial' },
      { property: 'busCod', label: 'Line Cod', type: 'string'},
      { property: 'enabled', label: 'Ativo', type: 'boolean' },
    ], 
    typeHeader: 'top'
  };

  gridBusCols: Array<PoTableColumn> = [
    {
      label: 'Bus Id',
      property: 'busId',
      visible: true
    },
    {
      label: 'Bus Cod',
      property: 'busCod', 
    },
    {
      label: 'Active',
      property: 'enabled', 
    },
    { property: 'medicoes', label: 'Details', type: 'detail', detail: this.cadastroBusDetail }
  ]

  createBodyRoute(): BodyCadastro{
    return {
      rotaId : this.rotaId,
      latitude: this.latitude,
      longitude: this.longitude,
      ordem: this.ordem,
      codigo: this.codigo,
      enabled: this.enabled,
      dataAtualizacao: new Date().toISOString(),
    }
  }

  restore() {
    this.rotaId = undefined;
    this.latitude = undefined;
    this.longitude = undefined;
    this.ordem = undefined;
    this.codigo = undefined;
    this.enabled = undefined;
    this.ngForm = undefined;
  }

  goBack(){
    this.router.navigateByUrl('/usuarios') //alterar
  }

  goAlterOnibus(){
    window.open(this.router.url + '/onibus/')
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyRoute()
    if (this.validaDados()){
      this.httpService.post('usuario', JSON.stringify(json), 'med/').subscribe( //alterar aqui
        response=>{ 
          this.blockSave = false  
          this.poNotification.success("Nova Rota cadastrada com sucesso!")
          this.router.navigateByUrl('/linhas/' + response.id)
        }
      )
    } else {
      this.blockSave = false
    }
  }

  validaDados(){
    let lOk: boolean = true
    if (this.rotaId == undefined){
      this.poNotification.error("Informe um código da Rota!")
      lOk = false;
    }

    if (this.rotaId == undefined){
      this.poNotification.error("Informe o código da Rota!")
      lOk = false;
    }

    return lOk
  }
}

interface BodyCadastro {
  rotaId: number,
  latitude: string,
  longitude: string,
  ordem: string,
  codigo: string,
  enabled: boolean,
  dataAtualizacao: string
}