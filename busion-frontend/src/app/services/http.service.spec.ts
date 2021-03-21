import { getTestBed, TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";

import { HttpService } from "./http.service";
import { HttpClient } from "@angular/common/http";

describe("HttpService", () => {
	let injector: TestBed;
	let service: HttpClient;
	let httpMock: HttpTestingController;

	const mockLine = [
		{
			id: 2,
			codigoLinha: "123456",
			ativo: true,
			onibus: [],
			rotas: [],
		},
		{
			id: 3,
			codigoLinha: "222222",
			ativo: true,
			onibus: [],
			rotas: [],
		},
	];

	const mockBus = [
		{
			id: 2,
			codigo: "814710",
			linha: {
				id: 2,
				codigo: "123456",
				ativo: true,
				rotas: [],
				onibus: [],
			},
			ativo: true,
			posicao: null,
		},
	];

	const mockRoute = [
		{
			id: 2,
			ordem: 2,
			latitude: "4623",
			longitude: "5122",
			ativo: true,
			linha: {
				id: 2,
				codigo: "123456",
				ativo: true,
				rotas: [],
				onibus: [],
			},
		},
	];

	const mockUser = [
		{
			uid: "0fSslt0QC9OebeT7N03MgZimIRZ2",
			nome: "Willian Kazahaya",
			email: "williankaza@hotmail.com",
			senha: "K1XD7nGvzQIG3kIGRRY3ELyJ0qKdjXANnP5xIO8vM860ogrE9l1-yJv7AW4w86TyUWAdd9GQKQpognx-yU4O3g==",
			emailVerificado: true,
		},
	];

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule],
			providers: [HttpService],
		});
		injector = getTestBed();
		service = injector.inject(HttpClient);
		httpMock = injector.inject(HttpTestingController);
	});

	it("should return a list of Lines", () => {
		let url = "http://localhost:8080/linha";
		service.get<any>(url).subscribe((response) => {
			expect(response[0].id).toEqual(2);
		});

		const httpRequest = httpMock.expectOne(url);
		expect(httpRequest.request.method).toEqual("GET");
		expect(httpRequest.request.responseType).toEqual("json");

		httpRequest.flush(mockLine);
	});

	it("should return a list of Bus", () => {
		let url = "http://localhost:8080/linha/1/onibus";
		service.get<any>(url).subscribe((response) => {
			expect(response[0].codigo).toEqual("814710");
		});

		const httpRequest = httpMock.expectOne(url);
		expect(httpRequest.request.method).toEqual("GET");
		expect(httpRequest.request.responseType).toEqual("json");

		httpRequest.flush(mockBus);
	});

	it("should return a list of Route", () => {
		let url = "http://localhost:8080/linha/1/onibus/2/rota";
		service.get<any>(url).subscribe((response) => {
			expect(response[0].ordem).toEqual(2);
		});

		const httpRequest = httpMock.expectOne(url);
		expect(httpRequest.request.method).toEqual("GET");
		expect(httpRequest.request.responseType).toEqual("json");

		httpRequest.flush(mockRoute);
	});

	it("should return a list of User", () => {
		let url = "http://localhost:8090/usuario";
		service.get<any>(url).subscribe((response) => {
			expect(response[0].uid).toEqual("0fSslt0QC9OebeT7N03MgZimIRZ2");
		});

		const httpRequest = httpMock.expectOne(url);
		expect(httpRequest.request.method).toEqual("GET");
		expect(httpRequest.request.responseType).toEqual("json");

		httpRequest.flush(mockUser);
	});

	afterEach(() => {
		httpMock.verify();
	});
});
