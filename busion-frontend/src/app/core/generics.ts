export interface User{
    id: string,
    name: String
    email: String
    birthDate: String
    ultimoCadastro: Array<UltimoCadastro>
}
  
export interface UltimoCadastro{
    name: string
    email: string
    birthDate: string
    dataAtualizacao: string
}

export class Generics{
    static getDadosUltimoCadastro(cadastroUsuario: any): UltimoCadastro{
        let ultimoCadastro = cadastroUsuario.sort((a,b) => a.dataAtualizacao < b.dataAtualizacao ? 1:-1)
    
        if (ultimoCadastro != undefined){
            return {
                name: ultimoCadastro[0].name,
                email: ultimoCadastro[0].email,
                birthDate: ultimoCadastro[0].birthDate,
                dataAtualizacao: ultimoCadastro[0].umidade
           }
        }
    }
}