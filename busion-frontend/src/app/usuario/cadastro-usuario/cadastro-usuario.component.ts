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
	uid: number;
	@Input() name: string;
	email: string;
	senha: string;
	birthDate: Date = new Date();
	ngForm: any;
	blockSave: boolean = false;

	constructor(
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private router: Router,
		private route: ActivatedRoute
	) {}

	ngOnInit(): void {
		this.restore();
		let userId = this.route.snapshot.paramMap.get("uid");

		if (userId != "novo") {
			this.uid = parseInt(userId);
			this.getUser(this.uid);
		} else {
			this.initDadosUsuario();
		}
	}

	initDadosUsuario() {
		this.name = "Teste";
		this.email = "teste@teste.com.br";
		this.senha = "1234";
		(this.birthDate = <any>new Date()), Validators.required;
	}

	getUser(uid: number) {
		this.httpService.get("linha/" + uid, "msusuario/").subscribe((response) => {
			let userLocalizado = response;
			if (userLocalizado == undefined) {
				this.poNotification.error("Não foi possível cadastrar com sucesso!");
			} else {
				this.uid = userLocalizado.uid;
				this.email = userLocalizado.email;
				this.senha = userLocalizado.senha;
				this.birthDate = userLocalizado.dataNascimento;
			}
		});
	}

	createBodyUser(): BodyCadastro {
		return {
			uid: this.uid,
			name: this.name,
			email: this.email,
			senha: this.senha,
			dataNascimento: new Date(),
		};
	}

	restore() {
		this.uid = undefined;
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
			this.httpService.post("usuario", JSON.stringify(json), "med/").subscribe(
				//alterar aqui
				(response) => {
					this.blockSave = false;
					this.poNotification.success("Novo usuário cadastrado com sucesso!");
					this.router.navigateByUrl("/usuario/" + response.id);
				}
			);
		} else {
			this.blockSave = false;
		}
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

		if (this.senha == undefined) {
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
	uid?: number;
	name: string;
	email: string;
	senha: string;
	dataNascimento: Date;
}
