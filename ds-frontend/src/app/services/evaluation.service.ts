import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IEvaluation, IEvaluationCreate, IEvaluationUpdate } from '../models/evaluation.models';
import { IMessage } from '../models/message.models';
import { Observable, mergeMap, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  constructor(
    private readonly httpClient : HttpClient
  ) { }

  private readonly baseURL : string = "http://localhost:8080/evaluations"

  public getEvaluations() : Observable<IEvaluation[]> {
    return this.httpClient.get<IEvaluation[]>(this.baseURL);
  }

  public getEvaluation(id : string) : Observable<IEvaluation> {
    return this.httpClient.get<IEvaluation>(`${this.baseURL}/${id}`)
  }

  public getPresignedUrl(id: number): Observable<IMessage> {
    return this.httpClient.get<IMessage>(`${this.baseURL}/${id}/image`).pipe(
        catchError(error => of(error.error))
    );
  }

  public updateEvaluation(id : string, evaluation: IEvaluationUpdate) : Observable<IEvaluation> {
    return this.httpClient.put<IEvaluation>(`${this.baseURL}/${id}`, evaluation);
  }

  public uploadImage(id: string, file: File) : Observable<IMessage> {
    return this.httpClient.put<IMessage>(`${this.baseURL}/${id}/image`, {}).pipe(
      mergeMap((response : IMessage) => {
        return this.httpClient.put<IMessage>(response.message, file)
      }), catchError(error => of(error.error))
    );
  }

}
