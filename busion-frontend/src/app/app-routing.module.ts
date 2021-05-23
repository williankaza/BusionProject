import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CadastroLinhaComponent } from "./linha/cadastro-linha/cadastro-linha.component";
import { ConsultaLinhaComponent } from "./linha/consulta-linha/consulta-linha.component";
import { CadastroOnibusComponent } from "./onibus/cadastro-onibus/cadastro-onibus.component";
import { CadastroRotaComponent } from "./rota/cadastro-rota/cadastro-rota.component";
import { CadastroUsuarioComponent } from "./usuario/cadastro-usuario/cadastro-usuario.component";
import { ConsultaUsuarioComponent } from "./usuario/consulta-usuario/consulta-usuario.component";

const routes: Routes = [
	{
		path: "",
		children: [
			{
				path: "",
				pathMatch: "full",
				redirectTo: "linhas",
			},/*
			{
				path: "usuarios",
				children: [
					{
						path: "",
						pathMatch: "full",
						component: ConsultaUsuarioComponent,
					},
					{
						path: "novo",
						data: { opUsuario: "inclusao" },
						component: CadastroUsuarioComponent,
					},
					{
						path: ":idUsuario",
						data: { opUsuario: "alteracao" },
						component: CadastroUsuarioComponent,
					},
				],
			},*/
			{
				path: "linhas",
				children: [
					{
						path: "",
						pathMatch: "full",
						component: ConsultaLinhaComponent,
					},
					{
						path: "novo",
						data: { opLinha: "inclusao" },
						component: CadastroLinhaComponent,
					},
					{
						path: ":idLinha",
						data: { opLinha: "alteracao" },
						children: [
							{
								path: "",
								pathMatch: "full",
								component: CadastroLinhaComponent,
							},
							{
								path: "onibus",
								children: [
									{
										path: "",
										data: { opOnibus: "inclusao" },
										component: CadastroOnibusComponent,
									},
									{
										path: ":idOnibus",
										data: { opOnibus: "alteracao" },
										component: CadastroOnibusComponent,
									},
								],
							},
							{
								path: "rota",
								children: [
									{
										path: "",
										data: { opRota: "inclusao" },
										component: CadastroRotaComponent,
									},
									{
										path: ":idRota",
										data: { opRota: "alteracao" },
										component: CadastroRotaComponent,
									},
								],
							},
						],
					},
				],
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
