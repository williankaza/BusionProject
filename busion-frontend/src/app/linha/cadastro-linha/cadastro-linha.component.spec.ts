import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroLinhaComponent } from './cadastro-linha.component';

describe('CadastroLinhaComponent', () => {
  let component: CadastroLinhaComponent;
  let fixture: ComponentFixture<CadastroLinhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroLinhaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroLinhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
