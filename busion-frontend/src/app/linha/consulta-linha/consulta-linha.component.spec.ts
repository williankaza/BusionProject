import { ComponentFixture, TestBed } from "@angular/core/testing";

import { ConsultaLinhaComponent } from "./consulta-linha.component";

describe("ConsultaLinhaComponent", () => {
	let component: ConsultaLinhaComponent;
	let fixture: ComponentFixture<ConsultaLinhaComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [ConsultaLinhaComponent],
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(ConsultaLinhaComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it("should create", () => {
		expect(component).toBeTruthy();
	});
});
