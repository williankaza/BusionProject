import { ComponentFixture, TestBed } from "@angular/core/testing";

import { CadastroRotaComponent } from "./cadastro-rota.component";

describe("CadastroRotaComponent", () => {
	let component: CadastroRotaComponent;
	let fixture: ComponentFixture<CadastroRotaComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [CadastroRotaComponent],
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(CadastroRotaComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it("should create", () => {
		expect(component).toBeTruthy();
	});
});
