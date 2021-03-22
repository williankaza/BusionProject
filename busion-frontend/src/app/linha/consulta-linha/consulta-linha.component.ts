import { Component, OnInit } from "@angular/core";

import { PoTableColumn, PoTableDetail } from "@po-ui/ng-components";
import { Router, ActivatedRoute } from "@angular/router";
import { HttpService } from "src/app/services/http.service";

export interface Lines {
	id: number;
	lineCod: number;
	enabled: boolean;
	actions: Array<string>;
}

export interface Cadastro {
	id: number;
	lineId: number;
	lineCod: number;
	enabled: boolean;
	dataAtualizacao?: string;
}

@Component({
	selector: "app-consulta-linha",
	templateUrl: "./consulta-linha.component.html",
	styleUrls: ["./consulta-linha.component.scss"],
})
export class ConsultaLinhaComponent implements OnInit {
	cadastroDetail: PoTableDetail = {
		columns: [
			{ property: "lineId", label: "Sequencial" },
			{ property: "lineCod", label: "Line Cod", type: "string" },
			{ property: "enabled", label: "Ativo", type: "boolean" },
		],
		typeHeader: "top",
	};

	gridCols: Array<PoTableColumn> = [
		{
			label: "Line Id",
			property: "id",
			type: "number",
			visible: true,
			width: "20%",
		},
		{
			label: "Line Cod",
			property: "lineCod",
		},
		{
			label: "Active",
			property: "enabled",
			type: "boolean",
			boolean: {
				trueLabel: "Enabled",
				falseLabel: "Disabled",
			},
		},
		{
			property: "actions",
			label: " ",
			type: "icon",
			icons: [
				{
					action: (row: Lines) => {
						this.goToCadastroAlterar(row.id);
					},
					icon: "po-icon-export",
					tooltip: "edit",
					value: "edit",
				},
			],
		},
	];

	goToCadastroAlterar(lineId: number) {
		window.open(this.router.url + "/" + lineId);
	}

	listLines: Array<Lines>;

	constructor(private httpService: HttpService, private router: Router, private route: ActivatedRoute) {
		this.listLines = new Array();
	}

	ngOnInit(): void {
		this.loadGrid();
	}

	goToCadastro() {
		window.open(this.router.url + "/novo");
	}

	loadGrid() {
		/* 
  [{"id":1,"codigoLinha":"1234","ativo":true,"onibus":[],"rotas":[]},{"id":2,"codigoLinha":"9121","ativo":true,"onibus":[],"rotas":[]}]
*/

		this.httpService.restore();
		this.httpService.get("linha", "mslinha/").subscribe((response) => {
			response.forEach((line) => {
				let cadastros = line;
				let newLine: Lines = {
					id: line.id,
					lineCod: line.codigoLinha,
					enabled: line.ativo,
					actions: ["edit"],
				};

				this.listLines.push(newLine);
			});
		});
	}
}
