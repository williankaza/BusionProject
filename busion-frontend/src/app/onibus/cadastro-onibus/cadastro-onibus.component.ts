import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { PoNotificationService } from "@po-ui/ng-components";
import { Rota } from "src/app/core/generics";
import { HttpService } from "src/app/services/http.service";

@Component({
	selector: "app-cadastro-onibus",
	templateUrl: "./cadastro-onibus.component.html",
	styleUrls: ["./cadastro-onibus.component.scss"],
})
export class CadastroOnibusComponent implements OnInit {
	busId: number;
	busCod: string;
	enabled: boolean = true;
	rotas: Array<Rota>;

	ngForm: any;
	blockSave: boolean = false;
	tipoOp: string;
	lineId: number;

	constructor(
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private router: Router,
		private route: ActivatedRoute
	) {}

	ngOnInit(): void {
		this.restore();
		let busId = this.route.snapshot.paramMap.get("idOnibus");
		let lineId = this.route.snapshot.paramMap.get("idLinha");
		this.tipoOp = this.route.snapshot.data["opBus"];

		if (busId != null) {
			this.busId = parseInt(busId);
			this.lineId = parseInt(lineId);
			this.getBus(this.busId, this.lineId);
		} else {
			this.initDadosBus();
		}
	}

	initDadosBus() {
		this.busCod = "123";
		this.enabled = true;
	}

	getBus(busId: number, lineId: number) {
		this.httpService.get("linha/" + lineId, "mslinha/" + busId).subscribe((response) => {
			let onibusLocalizada = response;
			if (onibusLocalizada == undefined) {
				this.poNotification.error("Não foi possível cadastrar com sucesso!");
			} else {
				this.busCod = onibusLocalizada.codigo;
				this.enabled = onibusLocalizada.ativo;
			}
		});
	}

	createBodyBus(): BodyCadastro {
		return {
			codigo: this.busCod,
			ativo: this.enabled,
		};
	}

	restore() {
		this.busId = undefined;
		this.busCod = undefined;
		this.enabled = undefined;
		this.ngForm = undefined;
	}

	goBack() {
		this.router.navigateByUrl("/linhas");
	}

	salvar() {
		this.blockSave = true;
		let json = this.createBodyBus();
		if (this.validaDados()) {
			this.httpService.post("linha", JSON.stringify(json), "mslinha/").subscribe((response) => {
				this.blockSave = false;
				this.poNotification.success("Novo ônibus cadastrada com sucesso!");
				this.router.navigateByUrl("/linhas/" + response.id);
			});
		} else {
			this.blockSave = false;
		}
	}

	validaDados() {
		let lOk: boolean = true;

		if (this.busCod == undefined) {
			this.poNotification.error("Informe o código do Ônibus!");
			lOk = false;
		}

		return lOk;
	}
}

interface BodyCadastro {
	codigo: string;
	ativo: boolean;
}
