import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { PoMenuItem } from '@po-ui/ng-components';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router){
  }
  readonly menus: Array<PoMenuItem> = [
    { label: 'Routes', action: ()=>this.goTo('linhas') },
    { label: 'Favorites', action: ()=>this.goTo('favoritos') },
    { label: 'Schedule', action: ()=>this.goTo('schedule') },
    { label: 'Account', action: ()=>this.goTo('usuarios') }
  ];

  private goTo(url: string) {
    this.router.navigate([url])
  }

}