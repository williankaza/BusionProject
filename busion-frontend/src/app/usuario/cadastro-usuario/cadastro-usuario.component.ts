import { Component, OnInit, Input } from "@angular/core";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { PoNotificationService } from "@po-ui/ng-components";
import { Generics } from "src/app/core/generics";
import { HttpService } from "src/app/services/http.service";
import { Validators } from "@angular/forms";

@Component({
	selector: "app-cadastro-usuario",
	templateUrl: "./cadastro-usuario.component.html",
	styleUrls: ["./cadastro-usuario.component.scss"],
})
export class CadastroUsuarioComponent implements OnInit {
	userId: string;
	@Input() name: string;
	email: string;
	senha: string;
	birthDate: Date = new Date();
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
		let userId = this.route.snapshot.paramMap.get("idUsuario");
		this.tipoOp = this.route.snapshot.data["opUsuario"];

		if (userId != null) {
			this.userId = userId;
			this.getUser(this.userId);
		} else {
			this.initDadosUsuario();
		}
	}

	initDadosUsuario() {}

	getUser(uid: string) {
		this.httpService.get("UserServices/" + uid, "msusuario/").subscribe((response) => {
			let userLocalizado = response;
			if (userLocalizado == undefined) {
				this.poNotification.error("Não foi possível cadastrar com sucesso!");
			} else {
				this.name = userLocalizado.nome;
				this.email = userLocalizado.email;
				this.senha = userLocalizado.senha;
				this.birthDate = userLocalizado.dataNascimento;
			}
		});
	}

	createBodyUser(): BodyCadastro {
		return {
			nome: this.name,
			email: this.email,
			senha: this.senha,
			dataNascimento: new Date(this.birthDate).toJSON(),
		};
	}

	restore() {
		this.userId = undefined;
		this.name = undefined;
		this.email = undefined;
		this.senha = undefined;
		this.birthDate = undefined;
		this.ngForm = undefined;
	}

	goBack() {
		this.router.navigateByUrl("/usuarios");
	}

	salvar() {
		this.blockSave = true;
		let json = this.createBodyUser();
		if (this.validaDados()) {
			if (this.tipoOp == "inclusao") {
				this.postUsuario(json);
			} else {
				this.putUsuario(json);
			}
		} else {
			this.blockSave = false;
		}
	}

	postUsuario(body) {
		this.httpService.post("UserServices", JSON.stringify(body), "msusuario/").subscribe((response) => {
			this.blockSave = false;
			this.poNotification.success("Novo usuário cadastrado com sucesso!");
			this.router.navigateByUrl("/usuarios/" + response.uid);
		});
	}

	putUsuario(body) {
		this.httpService.put("UserServices/" + this.userId, JSON.stringify(body), "msusuario/").subscribe((response) => {
			this.blockSave = false;
			this.poNotification.success("Usuário alterado com sucesso!");
			this.router.navigateByUrl("/usuarios/" + response.uid);
		});
	}
	validaDados() {
		let lOk: boolean = true;

		if (this.name == undefined) {
			this.poNotification.error("Informe o nome do Usuário!");
			lOk = false;
		}

		if (this.email == undefined) {
			this.poNotification.error("Informe o email do Usuário!");
			lOk = false;
		}

		if (this.tipoOp == "inclusao" && this.senha == undefined) {
			this.poNotification.error("Informe a senha do Usuário!");
			lOk = false;
		}

		if (this.birthDate == undefined) {
			this.poNotification.error("Informe a data de nascimento do Usuário!");
			lOk = false;
		}

		return lOk;
	}
}

interface BodyCadastro {
	nome: string;
	email: string;
	senha: string;
	dataNascimento: string;
}
