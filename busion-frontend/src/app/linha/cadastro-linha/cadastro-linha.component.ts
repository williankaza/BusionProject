import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { PoNotificationService, PoTableColumn, PoTableDetail } from "@po-ui/ng-components";
import { Onibus, Rota } from "src/app/core/generics";
import { HttpService } from "src/app/services/http.service";

@Component({
	selector: "app-cadastro-linha",
	templateUrl: "./cadastro-linha.component.html",
	styleUrls: ["./cadastro-linha.component.scss"],
})
export class CadastroLinhaComponent implements OnInit {
	lineId: number;
	lineCod: string;
	enabled: boolean = true;
	onibus: Array<Onibus>;
	rotas: Array<Rota>;

	ngForm: any;
	blockSave: boolean = false;
	tipoOp: string;

	constructor(
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private router: Router,
		private route: ActivatedRoute
	) {}

	ngOnInit(): void {
		this.restore();
		let lineId = this.route.snapshot.paramMap.get("idLinha");
		this.tipoOp = this.route.snapshot.data["opLinha"];
		this.onibus = [];
		this.rotas = [];
		if (lineId != null) {
			this.lineId = parseInt(lineId);
			this.getLine(this.lineId);
		} else {
			this.initDadosLines();
		}
	}

	initDadosLines() {
		this.enabled = true;
	}

	getLine(lineId: number) {
		this.httpService.get("linha/" + lineId, "mslinha/").subscribe((response) => {
			let linhaLocalizada = response;
			if (linhaLocalizada == undefined) {
				this.poNotification.error("Não foi possível cadastrar com sucesso!");
			} else {
				this.lineCod = linhaLocalizada.codigoLinha;
				this.enabled = linhaLocalizada.ativo;

				this.getOnibus(this.lineId);
				this.getRotas(this.lineId);
			}
		});
	}

	getOnibus(lineId: number) {
		this.httpService.get("linha/" + lineId + "/onibus", "mslinha/").subscribe((response) => {
			response.forEach((onibus) => {
				let onibusLocalizado: Onibus = {
					busId: onibus.id,
					busCod: onibus.codigo,
					enabled: onibus.ativo,
					actions: ["edit"],
				};

				this.onibus.push(onibusLocalizado);
			});
		});
	}

	gridBusCols: Array<PoTableColumn> = [
		{
			label: "Bus Id",
			property: "busId",
			visible: true,
		},
		{
			label: "Bus Cod",
			property: "busCod",
		},
		{
			label: "Active",
			property: "enabled",
		},
		{
			property: "actions",
			label: " ",
			type: "icon",
			icons: [
				{
					action: (row: Onibus) => {
						this.goToOnibus(row.busId);
					},
					icon: "po-icon-export",
					tooltip: "edit",
					value: "edit",
				},
			],
		},
	];

	goToOnibus(onibusId: number) {
		this.router.navigateByUrl(this.router.url + "/onibus/" + onibusId);
	}

	getRotas(lineId: number) {
		this.httpService.get("linha/" + lineId + "/rota", "mslinha/").subscribe((response) => {
			response.forEach((rota) => {
				let rotaLocalizada: Rota = {
					rotaId: rota.id,
					latitude: rota.latitude,
					longitude: rota.longitude,
					ordem: rota.ordem,
					actions: ["edit"],
				};

				this.rotas.push(rotaLocalizada);
			});
		});
	}

	gridRouteCols: Array<PoTableColumn> = [
		{
			label: "Id",
			property: "rotaId",
			visible: true,
			type: "number",
			width: "10%",
		},
		{
			label: "Latitude",
			property: "latitude",
		},
		{
			label: "Longitude",
			property: "longitude",
		},
		{
			label: "Ordem",
			property: "ordem",
			type: "number",
		},
		{
			property: "actions",
			label: " ",
			type: "icon",
			icons: [
				{
					action: (row: Rota) => {
						this.goToRotas(row.rotaId);
					},
					icon: "po-icon-export",
					tooltip: "edit",
					value: "edit",
				},
			],
		},
	];

	goToRotas(onibusId: number) {
		this.router.navigateByUrl(this.router.url + "/rota/" + onibusId);
	}

	createBodyLine(): BodyCadastro {
		return {
			codigoLinha: this.lineCod,
			ativo: this.enabled,
		};
	}

	restore() {
		this.lineId = undefined;
		this.lineCod = undefined;
		this.enabled = undefined;
		this.ngForm = undefined;
	}

	goBack() {
		this.router.navigateByUrl("/linhas");
	}

	goNewBus() {
		this.router.navigateByUrl(this.router.url + "/onibus");
	}

	goNewRoute() {
		this.router.navigateByUrl(this.router.url + "/rota");
	}

	salvar() {
		this.blockSave = true;
		let json = this.createBodyLine();
		if (this.validaDados()) {
			if (this.tipoOp == "inclusao") {
				this.postLinha(json);
			} else {
				this.putLinha(json);
			}
		} else {
			this.blockSave = false;
		}
	}

	postLinha(body) {
		this.httpService.post("linha", JSON.stringify(body), "mslinha/").subscribe((response) => {
			this.blockSave = false;
			this.poNotification.success("Nova linha cadastrada com sucesso!");
			this.router.navigateByUrl("/linhas/" + response.id);
		});
	}

	putLinha(body) {
		this.httpService.put("linha/" + this.lineId, JSON.stringify(body), "mslinha/").subscribe((response) => {
			this.blockSave = false;
			this.poNotification.success("Linha alterada com sucesso!");
			this.router.navigateByUrl("/linhas/" + response.id);
		});
	}

	validaDados() {
		let lOk: boolean = true;

		if (this.lineCod == undefined) {
			this.poNotification.error("Informe o Código da Linha!");
			lOk = false;
		}

		return lOk;
	}
}

interface BodyCadastro {
	codigoLinha: string;
	ativo: boolean;
}
