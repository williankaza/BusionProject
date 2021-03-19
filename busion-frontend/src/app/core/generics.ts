export interface User{
    id: number,
    name: String
    email: String
    birthDate: string | Date 
    ultimoCadastro: Array<UltimoCadastro>
}
  
export interface UltimoCadastro{
    name: string
    email: string
    birthDate: string | Date
    dataAtualizacao: string
}

export interface Linha{
    lineId: number
    lineCod: number
    enabled: boolean
    dataAtualizacao: string
}

export interface UltimoCadastroLines{
    lineId: number
    lineCod: number
    enabled: boolean
    dataAtualizacao: string
}

export interface UltimoCadastroBus{
    busId: number
    busCod: number
    enabled: boolean
    posicao: null
    //linha: Array<Linha>
    dataAtualizacao: string
}

export interface Onibus{
    busId: number
    busCod: string
    enabled: boolean
}

export interface Rota{
    rotaId: number
    latitude:string
    longitude:string
    ordem: number
  
}

export class Generics{
    static getDadosUltimoCadastro(cadastroUsuario: any): UltimoCadastro{
        let ultimoCadastro = cadastroUsuario.sort((a,b) => a.dataAtualizacao < b.dataAtualizacao ? 1:-1)
    
        if (ultimoCadastro != undefined){
            return {
                name: ultimoCadastro[0].name,
                email: ultimoCadastro[0].email,
                birthDate: ultimoCadastro[0].birthDate,
                dataAtualizacao: ultimoCadastro[0].dataAtualizacao
           }
        }
    }
}

export class GenericsLines{
    static getDadosUltimoCadastroLine(cadastroLines: any): UltimoCadastroLines{
        let ultimoCadastro = cadastroLines.sort((a,b) => a.dataAtualizacao < b.dataAtualizacao ? 1:-1)
    
        if (ultimoCadastro != undefined){
            return {
                lineId: ultimoCadastro[0].lineId,
                lineCod: ultimoCadastro[0].lineCod,
                enabled: ultimoCadastro[0].enabled,
                // onibus: ultimoCadastro[0].onibus,
                // rotas: ultimoCadastro[0].rotas,
                dataAtualizacao: ultimoCadastro[0].dataAtualizacao
           }
        }
    }
}

export class GenericsBus{
    static getDadosUltimoCadastroBus(cadastroLines: any): UltimoCadastroBus{
        let ultimoCadastro = cadastroLines.sort((a,b) => a.dataAtualizacao < b.dataAtualizacao ? 1:-1)
    
        if (ultimoCadastro != undefined){
            return {
                busId: ultimoCadastro[0].busId,
                busCod: ultimoCadastro[0].busCod,
                enabled: ultimoCadastro[0].enabled,
                posicao: ultimoCadastro[0].posicao,
                // linha: ultimoCadastro[0].linha,
                dataAtualizacao: ultimoCadastro[0].dataAtualizacao
           }
        }
    }
}