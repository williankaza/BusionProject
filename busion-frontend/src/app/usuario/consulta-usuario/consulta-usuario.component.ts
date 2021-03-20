import { Component, OnInit } from '@angular/core';

import { PoTableColumn, PoTableDetail } from '@po-ui/ng-components';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from 'src/app/services/http.service';

export interface Users{
  id: number,
  name: string
  email?: string
  birthDate?: string | Date
  dataAtualizacao?: string
  cadastros: Cadastro[]
}

export interface Cadastro{
  id: number,
  name: string
  email?: string
  birthDate?: string | Date
  dataAtualizacao?: string
}

@Component({
  selector: 'app-consulta-usuario',
  templateUrl: './consulta-usuario.component.html',
  styleUrls: ['./consulta-usuario.component.css']
})
export class ConsultaUsuarioComponent implements OnInit {

  cadastroDetail: PoTableDetail = {
    columns: [
      { property: 'id', label: 'Sequencial' },
      { property: 'name', label: 'Nome', type: 'string'},
      { property: 'email', label: 'Email', type: 'string' },
      { property: 'birthDate', label: 'Birth Date', type: 'date' },
      { property: 'dataAtualizacao', label: 'Data atualização', type: 'string'}
    ], 
    typeHeader: 'top'
  };

  gridCols: Array<PoTableColumn> = [
    {
      label: 'User',
      property: 'userid',
      visible: true
    },
    {
      label: 'Name',
      property: 'name', 
    },
    {
      label: 'Email',
      property: 'email', 
    },
    {
      label: 'Birth Date',
      property: 'birthDate', 
    },
    { property: 'medicoes', label: 'Details', type: 'detail', detail: this.cadastroDetail }
  ]

  listUsers: Array<Users>

   constructor(private httpService: HttpService,
    private router: Router, private route: ActivatedRoute) { 
    this.listUsers = new Array();
  }

  ngOnInit(): void {
    this.loadGrid()
  }

  goToCadastro(){
    window.open(this.router.url + '/novo')
    //this.router.navigate(['./cadastro'], { relativeTo: this.route })
  }

  loadGrid(){
    this.httpService.restore();
    this.httpService.get('drones', '/med').subscribe((response)=>{ //alterar
      response.forEach(user => {
        let cadastros = user.casdastros
        let lastCadastroIndex = Math.max(...cadastros.filter(cadastro => cadastro.rastreamento).map(cadastro => cadastro.idCadastro))-1
        
        if (lastCadastroIndex > -1){
          let newUser: Users = {
            id: user.idCadastro,
            name: user.cadastros[lastCadastroIndex].name,
            email: user.cadastros[lastCadastroIndex].email,
            birthDate: user.cadastros[lastCadastroIndex].birthDate,
            cadastros: (<Array<any>>user.cadastros).filter(cadastro => cadastro.rastreamento).map((cadastro)=>{
              return <Cadastro> {
                name: cadastro.name,
                email: cadastro.email,
                birthDate: cadastro.birthDate,
                dataAtualizacao: new Date(cadastro.dataAtualizacao).toLocaleString()
              }            
            })
          }
          
          this.listUsers.push(newUser)
        }
      });
    })
  } 
}
