import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { Generics } from 'src/app/core/generics';
import { HttpService } from 'src/app/services/http.service';

@Component({
  selector: 'app-consulta-linha',
  templateUrl: './consulta-linha.component.html',
  styleUrls: ['./consulta-linha.component.scss']
})

export class ConsultaLinhaComponent implements OnInit {


  ngOnInit(): void {
  }
  
}
