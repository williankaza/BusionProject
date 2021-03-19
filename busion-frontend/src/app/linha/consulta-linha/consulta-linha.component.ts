import { Component, OnInit } from '@angular/core';

import { PoTableColumn, PoTableDetail } from '@po-ui/ng-components';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

export interface Lines{
  id: number
  lineCod: number
  enabled: boolean
  //onibus: Array<Onibus>
  //rotas: Array<Rotas>
  dataAtualizacao?: string
  cadastros: Cadastro[]
}

export interface Cadastro{
  id: number
  lineId: number
  lineCod: number
  enabled: boolean
  //onibus: Array<Onibus>
  //rotas: Array<Rotas>
  dataAtualizacao?: string
}

@Component({
  selector: 'app-consulta-linha',
  templateUrl: './consulta-linha.component.html',
  styleUrls: ['./consulta-linha.component.scss']
})

export class ConsultaLinhaComponent implements OnInit {

  cadastroDetail: PoTableDetail = {
    columns: [
      { property: 'lineId', label: 'Sequencial' },
      { property: 'lineCod', label: 'Line Cod', type: 'string'},
      { property: 'enabled', label: 'Ativo', type: 'boolean' },
      // { property: 'onibus', label: 'Birth Date', type: 'number' },  //verificar
      // { property: 'rotas', label: 'Birth Date', type: 'number' },
      { property: 'dataAtualizacao', label: 'Data atualização', type: 'string'}
    ], 
    typeHeader: 'top'
  };

  gridCols: Array<PoTableColumn> = [
    {
      label: 'Line Id',
      property: 'lineId',
      visible: true
    },
    {
      label: 'Line Cod',
      property: 'lineCod', 
    },
    {
      label: 'Active',
      property: 'enabled', 
    },
    // {
    //   label: 'Onibus',
    //   property: 'onibus', 
    // },
    // {
    //   label: 'Rotas',
    //   property: 'rotas', 
    // },
    { property: 'medicoes', label: 'Details', type: 'detail', detail: this.cadastroDetail }
  ]

  listLines: Array<Lines>

   constructor(private httpService: HttpService,
    private router: Router, private route: ActivatedRoute) { 
    this.listLines = new Array();
  }

  ngOnInit(): void {
    this.loadGrid()
  }

  goToCadastro(){
    window.open(this.router.url + '/cadastro-linhas/')
    //this.router.navigate(['./cadastro'], { relativeTo: this.route })
  }

  loadGrid(){
    this.httpService.restore();
    this.httpService.get('drones', '/med').subscribe((response)=>{ //alterar
      response.forEach(line => {
        let cadastros = line.casdastros
        let lastCadastroIndex = Math.max(...cadastros.filter(cadastro => cadastro.enabled).map(cadastro => cadastro.lineId))-1
        
        if (lastCadastroIndex > -1){
          let newLine: Lines = {
            id: line.lineId,
            lineCod: line.cadastros[lastCadastroIndex].lineCod,
            enabled: line.cadastros[lastCadastroIndex].enabled,
            // onibus: line.cadastros[lastCadastroIndex].onibus,
            // rotas: line.cadastros[lastCadastroIndex].rotas,
            cadastros: (<Array<any>>line.cadastros).filter(cadastro => cadastro.enabled).map((cadastro)=>{
              return <Cadastro> {
                lineCod: cadastro.lineCod,
                enabled: cadastro.enabled,
                // onibus: cadastro.onibus,
                // rotas: cadastro.rotas,
                dataAtualizacao: new Date(cadastro.dataAtualizacao).toLocaleString()
              }            
            })
          }
          
          this.listLines.push(newLine)
        }
      });
    })
  }
}
  
