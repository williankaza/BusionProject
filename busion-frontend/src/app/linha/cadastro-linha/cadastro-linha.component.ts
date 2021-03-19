import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { GenericsLines, Onibus, Rota } from 'src/app/core/generics';
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
  onibus: Array<Onibus>
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
    let lineId = this.route.snapshot.paramMap.get("idLinha"); //alterar
    this.tipoOp = this.route.snapshot.data['opLinha']

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
    this.httpService.get('Busca Linha', 'mslinha/linha/' + lineId.toString()).subscribe( //alterar aqui
      (response)=>{
        let linhaLocalizada = response
        if (linhaLocalizada == undefined){
          console.log('deu ruim')
        } else {
            this.lineCod = linhaLocalizada.lineCod
            this.enabled = linhaLocalizada.enabled

            this.getOnibus(this.lineId)
            this.getRotas(this.lineId)
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

  getRotas(lineId: number){
    this.httpService.get('Busca Rotas', 'mslinha/linha/' + lineId.toString() + '/rota').subscribe(
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
          this.router.navigateByUrl('/linhas/' + response.id)
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

