import { IEvaluation } from "./evaluation.models"
import { ITag } from "./tag.models"

export interface IRestaurant {
    id: number,
    nom: string,
    addresse: string,
    note_moyenne: number,
    evaluations: IEvaluation[],
    illustration?: string,
    tag: ITag[],
    etoile?: number
}

export interface IRestaurantSummary {
    id: number,
    nom: string,
    addresse: string,
    note_moyenne: number,
    etoile?: number
}

export interface IRestaurantCreate {
    nom: string,
    addresse: string
}

export interface IRestaurantUpdate {
    nom?: string,
    addresse?: string
}