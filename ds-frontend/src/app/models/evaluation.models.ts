export interface IEvaluation {
    id: number,
    nom: string,
    note: number,
    date_creation: Date,
    date_mise_a_jour: Date

}

export interface IEvaluationUpdate {
    nom?: number,
    commentaire?: string,
    note?: number,
    date?: Date
}

export interface IEvaluationCreate {
    nom: number,
    commentaire: string,
    note: number,
    date: Date
}

export interface IEtoile {
    auteur: string,
    note: number,
    description: string

}