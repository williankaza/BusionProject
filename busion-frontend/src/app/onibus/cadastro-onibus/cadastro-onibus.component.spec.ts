import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroOnibusComponent } from './cadastro-onibus.component';

describe('CadastroOnibusComponent', () => {
  let component: CadastroOnibusComponent;
  let fixture: ComponentFixture<CadastroOnibusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroOnibusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroOnibusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
