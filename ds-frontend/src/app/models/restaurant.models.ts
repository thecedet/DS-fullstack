import { IEtoile, IEvaluation } from "./evaluation.models"
import { ITag } from "./tag.models"

export interface IRestaurant {
    id: number,
    nom: string,
    addresse: string,
    note_moyenne: number,
    evaluations: IEvaluation[],
    illustration?: string,
    tag: ITag[],
    etoile?: IEtoile
}

export interface IRestaurantSummary {
    id: number,
    nom: string,
    addresse: string,
    note_moyenne: number,
    etoile?: IEtoile
}

export interface IRestaurantCreate {
    nom: string,
    addresse: string
    tags?: ITag[]
}

export interface IRestaurantUpdate {
    nom?: string,
    addresse?: string
}