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
  latitude: number
  longitude: number
  ordem: number
  codigo: number
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
    this.codigo = 123
    this.enabled = true
  }

  getRotas(rotaId: number){
    this.httpService.get('linha/' + lineId, 'mslinha/' + rotaId).subscribe(
      (response)=>{
        let routeLocalizada = response
        if (routeLocalizada == undefined){
          this.poNotification.error("Não foi possível cadastrar com sucesso!")
        } else {
            this.codigo = routeLocalizada.codigo
            this.enabled = routeLocalizada.ativo
        }
      }
    )
  }

  createBodyRoute(): BodyCadastro{
    return {
      latitude: this.latitude,
      longitude: this.longitude,
      ordem: this.ordem,
      codigo: this.codigo,
      ativo: this.enabled,
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
    this.router.navigateByUrl('/linhas') //alterar
  }

  salvar(){
    this.blockSave = true
    let json = this.createBodyRoute()
    if (this.validaDados()){
      this.httpService.post('linha', JSON.stringify(json), 'mslinha/').subscribe(
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
      this.poNotification.error("Informe o código da Rota!")
      lOk = false;
    }

    return lOk
  }
}

interface BodyCadastro {
  latitude: number,
  longitude: number,
  ordem: number,
  codigo: number,
  ativo: boolean,
}