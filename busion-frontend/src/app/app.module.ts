import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PoModule } from '@po-ui/ng-components';
import { RouterModule } from '@angular/router';
import { ConsultaUsuarioComponent } from './usuario/consulta-usuario/consulta-usuario.component';
import { CadastroUsuarioComponent } from './usuario/cadastro-usuario/cadastro-usuario.component';
import { ConsultaLinhaComponent } from './linha/consulta-linha/consulta-linha.component';
import { CadastroLinhaComponent } from './linha/cadastro-linha/cadastro-linha.component';
import { CadastroOnibusComponent } from './onibus/cadastro-onibus/cadastro-onibus.component';
import { CadastroRotaComponent } from './rota/cadastro-rota/cadastro-rota.component';
@NgModule({
  declarations: [
    AppComponent,
    ConsultaUsuarioComponent,
    CadastroUsuarioComponent,
    ConsultaLinhaComponent,
    CadastroLinhaComponent,
    CadastroOnibusComponent,
    CadastroRotaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PoModule,
    RouterModule.forRoot([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
