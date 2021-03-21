import { TestBed } from "@angular/core/testing";

import { HttpService } from "./http.service";

describe("HttpService", () => {
	let service: HttpService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(HttpService);
	});

	it("should be created", () => {
		expect(service).toBeTruthy();
	});

	it("#getObservableValue perform a get on Usuário", (done: DoneFn)=>{
		service.get("linha", "mslinha/")
	})
});
