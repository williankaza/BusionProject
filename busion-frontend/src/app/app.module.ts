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
import { GoogleMapsModule } from '@angular/google-maps';
import { AgmCoreModule } from '@agm/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
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
    FormsModule,
    ReactiveFormsModule,
    PoModule,
    RouterModule.forRoot([]),
    GoogleMapsModule,
    AgmCoreModule.forRoot({
      // please get your own API key here:
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en
      apiKey: 'CHAVES_GOOGLE_MAPS'      
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
