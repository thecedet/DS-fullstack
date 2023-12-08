import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, mergeMap, of } from 'rxjs';
import { IRestaurant, IRestaurantCreate, IRestaurantSummary, IRestaurantUpdate } from '../models/restaurant.models';
import { IMessage } from '../models/message.models';
import { ITag } from '../models/tag.models';
import { IEvaluationCreate } from '../models/evaluation.models';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(
    private readonly httpClient : HttpClient
  ) { }

  private readonly baseURL : string = "http://localhost:8080/restaurants"

  public getRestaurants() : Observable<IRestaurantSummary[]> {
    return this.httpClient.get<IRestaurantSummary[]>(this.baseURL);
  }

  public getRestaurant(id : string) : Observable<IRestaurant> {
    return this.httpClient.get<IRestaurant>(`${this.baseURL}/${id}`)
  }

  public getPresignedUrl(id: number): Observable<IMessage> {
    return this.httpClient.get<IMessage>(`${this.baseURL}/${id}/image`).pipe(
        catchError(error => of(error.error))
    );
  }

  public createRestaurant(restaurant : IRestaurantCreate) : Observable<IRestaurant> {
    return this.httpClient.post<IRestaurant>(this.baseURL, restaurant);
  }

  public updateRestaurant(id : string, restaurant: IRestaurantUpdate) : Observable<IRestaurant> {
    return this.httpClient.put<IRestaurant>(`${this.baseURL}/${id}`, restaurant);
  }

  public uploadImage(id: string, file: File) : Observable<IMessage> {
    return this.httpClient.put<IMessage>(`${this.baseURL}/${id}/image`, {}).pipe(
      mergeMap((response : IMessage) => {
        return this.httpClient.put<IMessage>(response.message, file)
      }), catchError(error => of(error.error))
    );
  }

  public addTag(id: number, tags : ITag[]) : Observable<IRestaurant> {
    let params = {tag: [] as string[]}
    params.tag = tags.map(tag => tag.nom)
    return this.httpClient.put<IRestaurant>(`${this.baseURL}/${id}/tags`, {}, {params})
  }

  public createEvaluation(id : string, evaluation : IEvaluationCreate) : Observable<IRestaurant> {
    return this.httpClient.post<IRestaurant>(`${this.baseURL}/${id}/evaluations`, evaluation);
  }
  

}