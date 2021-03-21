import { Component, OnInit } from "@angular/core";

import { PoTableColumn, PoTableDetail } from "@po-ui/ng-components";
import { Router, ActivatedRoute } from "@angular/router";
import { HttpService } from "src/app/services/http.service";

export interface Users {
	id: string;
	name: string;
	email?: string;
	birthDate?: string | Date;
	actions: Array<string>;
}

export interface Cadastro {
	id: number;
	name: string;
	email?: string;
	birthDate?: string | Date;
}

@Component({
	selector: "app-consulta-usuario",
	templateUrl: "./consulta-usuario.component.html",
	styleUrls: ["./consulta-usuario.component.css"],
})
export class ConsultaUsuarioComponent implements OnInit {
	gridCols: Array<PoTableColumn> = [
		{
			label: "User",
			property: "id",
			visible: false,
		},
		{
			label: "Name",
			property: "name",
		},
		{
			label: "Email",
			property: "email",
		},
		{
			label: "Birth Date",
			property: "birthDate",
		},
		{
			property: "actions",
			label: " ",
			type: "icon",
			icons: [
				{
					action: (row: Users) => {
						this.goToUsuario(row.id);
					},
					icon: "po-icon-export",
					tooltip: "Alterar",
					value: "edit",
				},
			],
		},
	];

	goToUsuario(uidUsuario: string) {
		this.router.navigate([uidUsuario], { relativeTo: this.route });
	}

	listUsers: Array<Users>;

	constructor(private httpService: HttpService, private router: Router, private route: ActivatedRoute) {
		this.listUsers = new Array();
	}

	ngOnInit(): void {
		this.loadGrid();
	}

	goToCadastro() {
		this.router.navigateByUrl(this.router.url + "/novo");
	}

	loadGrid() {
		this.httpService.restore();
		this.httpService.get("UserServices", "msusuario/").subscribe((response) => {
			//alterar
			response.forEach((user) => {
				let newUser: Users = {
					id: user.uid,
					name: user.nome,
					email: user.email,
					birthDate: user.dataNascimento,
					actions: ["edit"],
				};

				this.listUsers.push(newUser);
			});
		});
	}
}
